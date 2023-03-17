package com.GUFL_kongliang.biz;

import com.GUFL_kongliang.entity.PostInformation;
import com.GUFL_kongliang.entity.RecruitmentInformation;
import com.GUFL_kongliang.entity.User;
import com.GUFL_kongliang.mapper.PostInformationMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 企业信息
 *
 * @author muyuan
 * @email dev@muyuanfoods.com
 * @date 2022-12-28 11:11:13
 */
@Service
public class PostInformationBiz extends ServiceImpl<PostInformationMapper, PostInformation> {




    /**
     * @Desc:  列表查询
     * @Auther: 孔量
     * @Date: 2022/12/28 11:23
     * @param: entity
     * @Return: List<PostInformation>
    */
    public List<PostInformation> getPageList(PostInformation entity) {
        String name = entity.getName();
        String type = entity.getType();
        String place = entity.getPlace();
        QueryWrapper<PostInformation> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            wrapper.like("name", "%" + name + "%");
        }
        if (StringUtils.isNotBlank(type)) {
            wrapper.like("type", "%" + type + "%");
        }
        if (StringUtils.isNotBlank(place)) {
            wrapper.like("place", "%" + place + "%");
        }
        wrapper.orderByDesc("updtime");
        return this.baseMapper.selectList(wrapper);
    }
    public void editSave(PostInformation entity) {
        entity.setUpdtime(new Date());
        entity.setDeleted(0);
        String id = entity.getId();
        if(StringUtils.isBlank(id)){
            //添加
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