package com.GUFL_kongliang.biz;

import com.GUFL_kongliang.entity.employmentStatistics;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 就业统计
 *
 * @author 孔量
 * @date 2023-01-13 16:05:30
 */
@Service
public class EmploymentStatisticsBiz{




    /**
     * @Desc:  历年毕业人数
     * @Auther: 孔量
     * @Date: 2023/1/13 20:32
     * @Return: employmentStatistics
    */
    public employmentStatistics numberOfGraduates() {

        employmentStatistics ret = new employmentStatistics();

        List<String> x = new ArrayList<>();
        Collections.addAll(x,  "2013", "2014", "2015", "2016","2017", "2018", "2019", "2020", "2021", "2022", "2023");

        List<Integer> y = new ArrayList<>();
        Collections.addAll(y, 590, 701, 830, 460, 540, 311, 499, 640, 510, 464, 635);

        ret.setX(x);
        ret.setY(y);
        return ret;
    }


    //Number of graduates
    


}