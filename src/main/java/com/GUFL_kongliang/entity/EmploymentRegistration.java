package com.GUFL_kongliang.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 就业登记表
 * 
 * @author 孔量
 * @date 2022-12-13 01:31:52
 */
@Table(name = "employment_registration")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmploymentRegistration implements Serializable {
	private static final long serialVersionUID = 1L;
	
	  //
    @Id
    private String id;
	
	  //登记人

    @Column(name = "registrant")
    private String registrant;
	
	  //登记时间

    @Column(name = "registration_date")
    private Date registrationDate;
	
	  //专业符合度

    @Column(name = "professional_compliance")
    private Integer professionalCompliance;
	
	  //工资

    @Column(name = "wages")
    private Integer wages;
	
	  //公司类型、单位性质

    @Column(name = "company_type")
    private String companyType;
	
	  //工作城市

    @Column(name = "city")
    private String city;
	
	  //工作单位

    @Column(name = "work_unit")
    private String workUnit;
	
	  //工作地点

    @Column(name = "work_place")
    private String workPlace;


    //是否删除 1是 0不是

    @TableLogic
    @Column(name = "deleted")
    private Integer deleted;
	

}
