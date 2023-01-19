package com.GUFL_kongliang.rest;


import com.GUFL_kongliang.biz.EmploymentStatisticsBiz;
import com.GUFL_kongliang.entity.employmentStatistics;
import com.GUFL_kongliang.entity.employmentStatisticsCircular;
import com.GUFL_kongliang.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 就业统计
 *
 * @author 孔量
 * @date 2023-01-13 16:05:30
 */
@RestController
@RequestMapping("employmentStatistics")
public class EmploymentStatisticsController {

    @Autowired
    EmploymentStatisticsBiz employmentStatisticsBiz;


    /**
     * @Desc:  获取历年毕业人数
     * @Auther: 孔量
     * @Date: 2023/1/13 20:39
     * @Return: BaseResponse<employmentStatistics>
    */
    @PostMapping("NumberOfGraduates")
    public BaseResponse<employmentStatistics> numberOfGraduates(@RequestBody HashMap<String,String> map) {
        employmentStatistics employmentStatistics = employmentStatisticsBiz.numberOfGraduates(map);
        return  new BaseResponse<>(200, "获取成功", employmentStatistics);
    }


    /**
     * @Desc:  历年就业人数
     * @Auther: 孔量
     * @Date: 2023/1/18 10:47
     * @param: map
     * @Return: BaseResponse<employmentStatistics>
    */
    @PostMapping("workNumber")
    public BaseResponse<employmentStatistics> workNumber(@RequestBody HashMap<String,String> map) {
        employmentStatistics employmentStatistics = employmentStatisticsBiz.workNumber(map);
        return  new BaseResponse<>(200, "获取成功", employmentStatistics);
    }



    /**
     * @Desc: 历年不就业人数
     * @Auther: 孔量
     * @Date: 2023/1/18 10:48
     * @param: map
     * @Return: BaseResponse<employmentStatistics>
    */
    @PostMapping("noWork")
    public BaseResponse<employmentStatistics> noWork(@RequestBody HashMap<String,String> map) {
        employmentStatistics employmentStatistics = employmentStatisticsBiz.noWork(map);
        return  new BaseResponse<>(200, "获取成功", employmentStatistics);
    }


    /**
     * @Desc:  就业类型统计
     * @Auther: 孔量
     * @Date: 2023/1/18 11:26
     * @param: map
     * @Return: BaseResponse<List<employmentStatisticsCircular>>
    */
    @PostMapping("workTypeStatistics")
    public BaseResponse<List<employmentStatisticsCircular>> workTypeStatistics(@RequestBody HashMap<String,String> map) {
        List<employmentStatisticsCircular> employmentStatisticsCirculars = employmentStatisticsBiz.workTypeStatistics(map);
        return  new BaseResponse<>(200, "获取成功", employmentStatisticsCirculars);
    }


    /**
     * @Desc:  合作企业
     * @Auther: 孔量
     * @Date: 2023/1/19 8:42
     * @param: map
     * @Return: BaseResponse<List<employmentStatisticsCircular>>
    */
    @PostMapping("cooperativeCompanies")
    public BaseResponse<List<employmentStatisticsCircular>> cooperativeCompanies() {
        List<employmentStatisticsCircular> employmentStatisticsCirculars = employmentStatisticsBiz.cooperativeCompanies();
        return  new BaseResponse<>(200, "获取成功", employmentStatisticsCirculars);
    }

}