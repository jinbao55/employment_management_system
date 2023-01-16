package com.GUFL_kongliang.biz;

import com.GUFL_kongliang.entity.Register;
import com.GUFL_kongliang.mapper.RegisterMapper;
import com.GUFL_kongliang.utils.SnowflakeIdWorker;
import com.GUFL_kongliang.utils.UUIDUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.javafaker.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * 就业登记
 *
 * @author 孔量
 * @date 2023-01-14 14:40:11
 */
@Service

public class RegisterBiz extends ServiceImpl<RegisterMapper, Register> {


    @Autowired
    RegisterMapper registerMapper;


    public Register selectByIda(String id) {
        return baseMapper.selectByIda(id);
    }


    /**
     * @Desc: 添加或修改
     * @Auther: 孔量
     * @Date: 2023/1/14 15:33
     * @param: entity
     * @Return: void
     */
    public void editSave(Register entity) {
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
        entity.setDeleted(0);
        String id = String.valueOf(entity.getId());
        if (StringUtils.isBlank(id)) {
            //添加
            entity.setId(idWorker.nextId());
            baseMapper.insert(entity);
        } else {
            //修改
            baseMapper.updateById(entity);
        }
    }

    /**
     * @Desc: 删除
     * @Auther: 孔量
     * @Date: 2023/1/14 15:34
     * @param: id
     * @Return: Integer
     */
    public Integer delete(String id) {
        Integer i = baseMapper.deleteById(id);
        return i;
    }


    /**
     * @Desc: 列表查询
     * @Auther: 孔量
     * @Date: 2023/1/14 15:37
     * @param: dto
     * @Return: List<Register>
     */
    public List<Register> getPageList(Register entity) {
        String college = entity.getCollege();
        String workType = entity.getWorkType();
        String major = entity.getMajor();
        String workCities = entity.getWorkCities();

        QueryWrapper<Register> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(college)) {
            wrapper.like("college", "%" + college + "%");
        }
        if (StringUtils.isNotBlank(workType)) {
            wrapper.like("work_type", "%" + workType + "%");
        }
        if (StringUtils.isNotBlank(major)) {
            wrapper.like("major", "%" + major + "%");
        }
        if (StringUtils.isNotBlank(workCities)) {
            wrapper.like("work_cities", "%" + workCities + "%");
        }
        wrapper.orderByDesc("graduation_year");
        return this.baseMapper.selectList(wrapper);
    }

    public HashMap<String, Object> getPageLista(Register entity) {
        String college = entity.getCollege();
        String workType = entity.getWorkType();
        String major = entity.getMajor();
        String workCities = entity.getWorkCities();
        Integer page = entity.getPage();
        Integer limit = entity.getLimit();
        HashMap<String, Object> selectMap = new HashMap<>();
        selectMap.put("college",college);
        selectMap.put("workType",workType);
        selectMap.put("major",major);
        selectMap.put("workCities",workCities);
        if(page!=null && limit!=null){
            Integer pages = (page - 1) * limit;
            if(pages<1){
                pages=0;
            }
            selectMap.put("page",pages);
            selectMap.put("limit",limit);
        }
        return getPage(selectMap);
    }

    public HashMap<String, Object> getPage(HashMap selectMap) {

        //count
        Integer tol=baseMapper.count(selectMap);

        //找id
        List<String> idList = baseMapper.selecyId(selectMap);
        //in id

        List<Register> list= baseMapper.selecyIdList(idList);

        HashMap<String, Object> hashMap = new HashMap<>();

        hashMap.put("tol",tol);
        hashMap.put("list",list);

        return hashMap;
    }
}