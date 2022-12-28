package com.GUFL_kongliang.biz;

import com.GUFL_kongliang.entity.PostInformation;
import com.GUFL_kongliang.entity.Recruit;
import com.GUFL_kongliang.mapper.RecruitMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 企业招聘
 *
 * @author 孔量
 * @date 2022-12-28 19:57:57
 */
@Service
public class RecruitBiz extends ServiceImpl<RecruitMapper, Recruit> {


    /**
     * @Desc:  列表查询
     * @Auther: 孔量
     * @Date: 2022/12/28 11:23
     * @param: entity
     * @Return: List<PostInformation>
     */
    public List<Recruit> getPageList(Recruit entity) {

        String company = entity.getCompany();
        String type = entity.getType();
        String place = entity.getPlace();

        QueryWrapper<Recruit> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(company)) {
            wrapper.like("company", "%" + company + "%");
        }
        if (StringUtils.isNotBlank(type)) {
            wrapper.like("type", "%" + type + "%");
        }
        if (StringUtils.isNotBlank(place)) {
            wrapper.like("place", "%" + place + "%");
        }
        wrapper.orderByDesc("date");
        return this.baseMapper.selectList(wrapper);
    }


    /**
     * @Desc:  编辑和添加
     * @Auther: 孔量
     * @Date: 2022/12/28 20:36
     * @param: entity
     * @Return: void
    */

    public void editSave(Recruit entity) {
        entity.setDeleted(0);
        String id = entity.getId();

        if(StringUtils.isBlank(id)){
            //添加
            entity.setDate(new Date());
            baseMapper.insert(entity);
        }else {
            //修改
            baseMapper.updateById(entity);
        }
    }


    /**
     * @Desc: 删除方法
     * @Auther: 孔量
     * @Date: 2022/12/28 14:49
     * @param: id
     * @Return: void
     */
    public Integer delete(String id) {
        Integer i = baseMapper.deleteById(id);
        return i;
    }
}