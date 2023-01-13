package com.GUFL_kongliang.rest;


import com.GUFL_kongliang.biz.EmploymentStatisticsBiz;
import com.GUFL_kongliang.entity.employmentStatistics;
import com.GUFL_kongliang.utils.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("NumberOfGraduates")
    public BaseResponse<employmentStatistics> numberOfGraduates() {
        employmentStatistics employmentStatistics = employmentStatisticsBiz.numberOfGraduates();
        return  new BaseResponse<>(200, "获取成功", employmentStatistics);
    }



}