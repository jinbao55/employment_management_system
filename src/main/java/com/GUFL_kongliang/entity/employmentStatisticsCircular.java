package com.GUFL_kongliang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @desc：就业统计展示类
 * @author: 孔量
 * @date：2023/1/13 20:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class employmentStatisticsCircular {
    private static final long serialVersionUID = 1L;

    //数量
    private Integer value;

    //类型
    private String name;

}
