package com.GUFL_kongliang.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @desc：就业登记列表查询Dto
 * @author: 孔量
 * @date：2022/12/13 9:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentRegistrationDto {

    private int page;

    private int limit;

    //工作城市

    private String city;


    //工作单位

    private String workUnit;


    //登记人

    private String registrant;

}
