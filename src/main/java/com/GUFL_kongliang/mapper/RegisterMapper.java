package com.GUFL_kongliang.mapper;

import com.GUFL_kongliang.entity.Register;
import com.GUFL_kongliang.entity.employmentStatisticsCircular;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 就业登记
 * 
 * @author 孔量
 * @date 2023-01-14 14:40:11
 */
public interface RegisterMapper extends BaseMapper<Register> {

    void insertList(List<Register> list);

    Register selectByIda(String id);


    Integer count(HashMap selectMap);


    List<String> selecyId(HashMap selectMap);

    List<Register> selecyIdList(List<String> idList);



    /**
     * @Desc:  统计毕业年
     * @Auther: 孔量
     * @Date: 2023/1/18 9:42
     * @param: null
     * @Return: null
    */
    List<Integer> selectGraduationYear(HashMap<String, String> map);

    /**
     * @Desc: 就业类型统计
     * @Auther: 孔量
     * @Date: 2023/1/18 11:24
     * @param: map
     * @Return: List<employmentStatisticsCircular>
    */
    List<employmentStatisticsCircular> workTypeStatistics(HashMap<String, String> map);
}
