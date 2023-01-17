package com.GUFL_kongliang.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 就业登记
 * 

 * @email 孔量
 * @date 2023-01-14 14:40:11
 */
@Table(name = "register")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Register implements Serializable {
	private static final long serialVersionUID = 1L;
	
	  //id
    @Id
    private String id;
	
	  //姓名

    @Column(name = "name")
    private String name;
	
	  //学号

    @Column(name = "sno")
    private Integer sno;
	
	  //学院

    @Column(name = "college")
    private String college;
	
	  //专业

    @Column(name = "major")
    private String major;
	
	  //班级

    @Column(name = "class_no")
    private Integer classNo;
	
	  //毕业年份

    @Column(name = "graduation_year")
    @JsonFormat(pattern = "yyyy")
    private int graduationYear;
	
	  //是否就业

    @Column(name = "is_work")
    private String isWork;
	
	  //就业公司

    @Column(name = "employment_company")
    private String employmentCompany;
	
	  //就业岗位

    @Column(name = "jobs")
    private String jobs;
	
	  //就业公司类型

    @Column(name = "work_type")
    private String workType;
	
	  //薪酬

    @Column(name = "pay")
    private Integer pay;

    //工作城市

    @Column(name = "work_cities")
    private String workCities;
	
	  //删除标识

    @Column(name = "deleted")
    private Integer deleted;



    /***********************************************拓展字段***********************************************************/

    @Transient
    private transient Integer page;


    @Transient
    private transient Integer limit;
	

}
