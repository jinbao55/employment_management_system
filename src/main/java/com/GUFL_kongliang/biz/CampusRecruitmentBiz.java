package com.GUFL_kongliang.biz;

import com.GUFL_kongliang.entity.CampusRecruitment;
import com.GUFL_kongliang.entity.EmploymentRegistration;
import com.GUFL_kongliang.mapper.CampusRecruitmentMapper;
import com.GUFL_kongliang.utils.UUIDUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 校园招聘
 *
 * @author 孔量
 * @date 2023-01-13 16:05:30
 */
@Service
public class CampusRecruitmentBiz extends ServiceImpl<CampusRecruitmentMapper, CampusRecruitment> {

    /**
     * @Desc:  列表查询
     * @Auther: 孔量
     * @Date: 2023/1/13 16:25
     * @param: entity
     * @Return: Page<CampusRecruitment>
    */
    public List<CampusRecruitment> getPageList(CampusRecruitment entity) {
        //筛选条件
        String company = entity.getCompany();
        String place = entity.getPlace();
        String companyType = entity.getCompanyType();

        QueryWrapper<CampusRecruitment> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(company)) {
            wrapper.like("company", "%" + company + "%");
        }
        if (StringUtils.isNotBlank(place)) {
            wrapper.like("place", "%" + place + "%");
        }
        if (StringUtils.isNotBlank(companyType)) {
            wrapper.like("company_type", "%" + companyType + "%");
        }
        wrapper.orderByDesc("lecture_time");
        return baseMapper.selectList(wrapper);
    }

    /**
     * @Desc:  删除
     * @Auther: 孔量
     * @Date: 2023/1/13 16:48
     * @param: id
     * @Return: Integer
    */
    public Integer delete(String id) {
        Integer i = this.baseMapper.deleteById(id);
        return i;
    }


    /**
     * @Desc:  添加或修改
     * @Auther: 孔量
     * @Date: 2023/1/13 16:57
     * @param: entity
     * @Return: void
    */
    public void editSave(CampusRecruitment entity) {
        String id = entity.getId();
        if(StringUtils.isBlank(id)){
            //添加
            entity.setId(UUIDUtils.getUUID());
            entity.setDeleted(0);
            entity.setCrtTime(new Date());
            baseMapper.insert(entity);
        }else {
            //修改
            baseMapper.updateById(entity);
        }
    }
}