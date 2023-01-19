package com.GUFL_kongliang.biz;

import com.GUFL_kongliang.entity.employmentStatistics;
import com.GUFL_kongliang.entity.employmentStatisticsCircular;
import com.GUFL_kongliang.mapper.PostInformationMapper;
import com.GUFL_kongliang.mapper.RegisterMapper;
import com.GUFL_kongliang.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * 就业统计
 *
 * @author 孔量
 * @date 2023-01-13 16:05:30
 */
@Service
@Slf4j
public class EmploymentStatisticsBiz{


    @Autowired
    RegisterMapper registerMapper;

    @Autowired
    PostInformationMapper postInformationMapper;


    @Autowired
    RedisUtils redisUtils;

    /**
     * @Desc: 统计历年毕业人数
     * @Auther: 孔量
     * @Date: 2023/1/18 10:38
     * @param: map
     * @Return: employmentStatistics
    */
    public employmentStatistics numberOfGraduates(HashMap<String,String> map){
        String year = map.get("year");
        String key= "numberOfGraduates"+year;
        employmentStatistics value = (employmentStatistics) redisUtils.getValue(key);
        if(Objects.nonNull(value)){
            log.info("统计历年毕业人数-缓存："+year);
            return value;
        }
        Integer s = Integer.valueOf(map.get("year"));
        List<String> x = new ArrayList<>();
        for (int i=s+1;i<=2023;i++){
            x.add(String.valueOf(i));
        }
        List<Integer> y = registerMapper.selectGraduationYear(map);
        employmentStatistics ret = new employmentStatistics();
        ret.setX(x);
        ret.setY(y);

        redisUtils.setValue(key,ret,7, TimeUnit.HOURS);
        return ret;
    }

    /**
     * @Desc:  历年就业人数
     * @Auther: 孔量
     * @Date: 2023/1/18 10:44
     * @param: map
     * @Return: employmentStatistics
    */
    public employmentStatistics workNumber(HashMap<String,String> map){

        String year = map.get("year");
        String key= "workNumber"+year;
        employmentStatistics value = (employmentStatistics) redisUtils.getValue(key);
        if(Objects.nonNull(value)){
            log.info("历年就业人数-缓存："+year);
            return value;
        }

        Integer s = Integer.valueOf(map.get("year"));
        map.put("isWork","是");
        List<String> x = new ArrayList<>();
        for (int i=s+1;i<=2023;i++){
            x.add(String.valueOf(i));
        }
        List<Integer> y = registerMapper.selectGraduationYear(map);
        employmentStatistics ret = new employmentStatistics();
        ret.setY(y);
        ret.setX(x);
        redisUtils.setValue(key,ret,7, TimeUnit.HOURS);
        return ret;
    }

    /**
     * @Desc:  历年不就业人数
     * @Auther: 孔量
     * @Date: 2023/1/18 10:44
     * @param: map
     * @Return: employmentStatistics
     */
    public employmentStatistics noWork(HashMap<String,String> map){
        String year = map.get("year");
        String key= "noWork"+year;
        employmentStatistics value = (employmentStatistics) redisUtils.getValue(key);
        if(Objects.nonNull(value)){
            log.info("历年不就业人数-缓存："+year);
            return value;
        }
        Integer s = Integer.valueOf(map.get("year"));
        map.put("isWork","否");
        List<String> x = new ArrayList<>();
        for (int i=s+1;i<=2023;i++){
            x.add(String.valueOf(i));
        }
        List<Integer> y = registerMapper.selectGraduationYear(map);
        employmentStatistics ret = new employmentStatistics();
        ret.setX(x);
        ret.setY(y);
        redisUtils.setValue(key,ret,7, TimeUnit.HOURS);
        return ret;
    }


    /**
     * @Desc: 就业类型统计
     * @Auther: 孔量
     * @Date: 2023/1/18 11:12
     * @param: map
     * @Return: employmentStatisticsCircular
    */
    public List<employmentStatisticsCircular> workTypeStatistics(HashMap<String,String> map){
        String year = map.get("year");
        String key= "workTypeStatistics"+year;
        List<employmentStatisticsCircular> value = (List<employmentStatisticsCircular>) redisUtils.getValue(key);
        if(Objects.nonNull(value)){
            log.info("就业类型统计-缓存："+year);
            return value;
        }
        map.put("isWork","是");
        List<employmentStatisticsCircular> t=registerMapper.workTypeStatistics(map);
        redisUtils.setValue(key,t,7, TimeUnit.HOURS);
        return t;
    }



    /**
     * @Desc: 合作企业统计
     * @Auther: 孔量
     * @Date: 2023/1/19 8:53
     * @Return: List<employmentStatisticsCircular>
    */
    public List<employmentStatisticsCircular>  cooperativeCompanies(){
        String key= "cooperativeCompanies";
        List<employmentStatisticsCircular> value = (List<employmentStatisticsCircular>) redisUtils.getValue(key);
        if(Objects.nonNull(value)){
            log.info("合作企业统计-缓存");
            return value;
        }
        List<employmentStatisticsCircular> t=postInformationMapper.analysis();
        redisUtils.setValue(key,t,7, TimeUnit.HOURS);
        return t;
    }



}