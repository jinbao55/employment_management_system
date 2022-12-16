package com.GUFL_kongliang.biz;

import com.GUFL_kongliang.entity.EmploymentRegistration;
import com.GUFL_kongliang.entity.dto.EmploymentRegistrationDto;
import com.GUFL_kongliang.mapper.EmploymentRegistrationMapper;
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
 * 就业登记表
 *
 * @author 孔量
 * @email 344119601@qq.com
 * @date 2022-12-13 01:31:52
 */
@Service
@Slf4j
public class EmploymentRegistrationBiz extends ServiceImpl<EmploymentRegistrationMapper, EmploymentRegistration> {


    @Autowired
    RedisUtils redisUtils;


    private final String key = "employmentRegistration1-10";

    /**
     * @Desc: 添加登记
     * @Auther: 孔量
     * @Date: 2022/12/13 9:39
     * @param: entity
     * @Return: void
     */
    public boolean addRegister(EmploymentRegistration entity) {
        entity.setId(UUIDUtils.getUUID());
        entity.setRegistrationDate(new Date());
        entity.setDeleted(0);
        redisUtils.deleteKey(key);
        return save(entity);
    }


    /**
     * @Desc: 删除登记
     * @Auther: 孔量
     * @Date: 2022/12/13 9:39
     * @param: entity
     * @Return: void
     */
    public boolean delRegister(String id) {
        int i = baseMapper.deleteById(id);
        if (i > 0) {
            redisUtils.deleteKey(key);
            return true;
        }
        return false;
    }

    /**
     * @Desc: 修改登记
     * @Auther: 孔量
     * @Date: 2022/12/13 9:39
     * @param: entity
     * @Return: void
     */
    public boolean updateRegister(EmploymentRegistration entity) {

        int i = baseMapper.updateById(entity);
        if (i > 0) {
            redisUtils.deleteKey(key);
            return true;
        }
        return false;
    }


    /**
     * @Desc: 列表查询
     * @Auther: 孔量
     * @Date: 2022/12/13 10:16
     * @param: dto
     * @Return: Page<EmploymentRegistration>
     */

    public Page<EmploymentRegistration> getPageList(EmploymentRegistrationDto dto) {

        boolean flag = false;

        if (dto.getPage() == 1 &&
                dto.getLimit() == 10 &&
                dto.getCity() == null &&
                dto.getRegistrant() == null &&
                dto.getWorkUnit() == null) {
            flag = true;
        }

        //获取缓存数据
        if (flag) {
            Object value = redisUtils.getValue(key);
            if (value != null) {
                log.info("---------------没查数据库-----------------------");
                return (Page<EmploymentRegistration>) value;
            }
        }

        //分页参数
        Page<EmploymentRegistration> rowPage = new Page(dto.getPage(), dto.getLimit());
        //查询条件
        String city = dto.getCity();
        String registrant = dto.getRegistrant();
        String workUnit = dto.getWorkUnit();

        QueryWrapper<EmploymentRegistration> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("registration_date");

        if (StringUtils.isNotBlank(city)) {
            wrapper.like("city", "%" + city + "%");
        }
        if (StringUtils.isNotBlank(registrant)) {
            wrapper.like("registrant", "%" + registrant + "%");
        }
        if (StringUtils.isNotBlank(workUnit)) {
            wrapper.like("work_unit", "%" + workUnit + "%");
        }
        Page<EmploymentRegistration> page = this.baseMapper.selectPage(rowPage, wrapper);
        log.info("--------，，，，，，，，，查数据库了，，，，，，，----------");
        //缓存数据
        if (flag) {
            redisUtils.setValue(key, page, 7000, TimeUnit.HOURS);
        }
        return page;
    }
}