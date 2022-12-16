package com.GUFL_kongliang.biz;

import com.GUFL_kongliang.entity.EmploymentRegistration;
import com.GUFL_kongliang.entity.RecruitmentInformation;
import com.GUFL_kongliang.mapper.RecruitmentInformationMapper;
import com.GUFL_kongliang.utils.RedisUtils;
import com.GUFL_kongliang.utils.UUIDUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 * 招聘信息
 *
 * @author 孔量
 * @date 2022-12-16 09:03:59
 */
@Service
@Slf4j
public class RecruitmentInformationBiz extends ServiceImpl<RecruitmentInformationMapper, RecruitmentInformation> {



    @Autowired
    RedisUtils redisUtils;


    private final String key = "RecruitmentInformation1-10";



    /**
     * @Desc:  添加招聘信息
     * @Auther: 孔量
     * @Date: 2022/12/16 17:32
     * @param: entity
     * @Return: boolean
    */

    public boolean add(RecruitmentInformation entity) {
        entity.setId(UUIDUtils.getUUID());
        entity.setCrtTime(new Date());
        redisUtils.deleteKey(key);
      return save(entity);
    }




    /**
     * @Desc:  删除招聘信息
     * @Auther: 孔量
     * @Date: 2022/12/16 17:32
     * @param: entity
     * @Return: boolean
     */
    public boolean del(String id) {
        int i = baseMapper.deleteById(id);
        if (i > 0) {
            redisUtils.deleteKey(key);
            return true;
        }
        return false;
    }


    /**
     * @Desc:  修改招聘信息
     * @Auther: 孔量
     * @Date: 2022/12/16 17:32
     * @param: entity
     * @Return: boolean
     */

    public boolean update(RecruitmentInformation entity) {
        int i = baseMapper.updateById(entity);
        if (i > 0) {
            redisUtils.deleteKey(key);
            return true;
        }
        return false;
    }




    /**
     * @Desc:  列表查询，可根据 工作地点  招聘公司 查询
     * @Auther: 孔量
     * @Date: 2022/12/16 17:46
     * @param: entity
     * @Return: Page<RecruitmentInformation>
    */
    public Page<RecruitmentInformation> getPageList(RecruitmentInformation entity) {

        boolean flag = false;

        if (entity.getPage() == 1 &&
                entity.getLimit() == 10 &&
                entity.getPosition() == null &&
                entity.getCompany() == null) {
            flag = true;
        }

        //获取缓存数据
        if (flag) {
            Object value = redisUtils.getValue(key);
            if (value != null) {
                log.info("---------------没查数据库-----------------------");
                return (Page<RecruitmentInformation>) value;
            }
        }
        String position = entity.getPosition();
        String company = entity.getCompany();
        //分页参数
        Page<RecruitmentInformation> rowPage = new Page(entity.getPage(), entity.getLimit());
        QueryWrapper<RecruitmentInformation> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("crt_time");
        if (StringUtils.isNotBlank(position)) {
            wrapper.like("position", "%" + position + "%");
        }
        if (StringUtils.isNotBlank(company)) {
            wrapper.like("company", "%" + company + "%");
        }
        Page<RecruitmentInformation> page = this.baseMapper.selectPage(rowPage, wrapper);


        log.info("--------，，，，，，，，，查数据库了，，，，，，，----------");
        //缓存数据
        if (flag) {
            redisUtils.setValue(key, page, 7000, TimeUnit.HOURS);
        }
        return page;
    }
}