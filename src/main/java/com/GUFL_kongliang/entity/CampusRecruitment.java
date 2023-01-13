package com.GUFL_kongliang.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 校园招聘
 * 
 * @author 孔量
 * @date 2023-01-13 16:05:30
 */
@Table(name = "campus_recruitment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampusRecruitment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	  //id
    @Id
    private String id;
	
	  //宣讲主题

    @Column(name = "lecture_theme")
    private String lectureTheme;
	
	  //宣讲时间
      @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "lecture_time")
    private Date lectureTime;
	
	  //招聘企业

    @Column(name = "company")
    private String company;
	
	  //地点

    @Column(name = "place")
    private String place;
	
	  //联系老师

    @Column(name = "teacher")
    private String teacher;
	
	  //企业类型

    @Column(name = "company_type")
    private String companyType;
	
	  //所属行业

    @Column(name = "industry")
    private String industry;
	
	  //工作地点

    @Column(name = "work_location")
    private String workLocation;
	
	  //公司邮箱

    @Column(name = "company_mail")
    private String companyMail;
	
	  //删除标识
      @TableLogic
    @Column(name = "deleted")
    private Integer deleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "crt_time")
      private Date crtTime;


      /***************************************拓展字段**********************************************/


    @Transient
    private transient Integer page;


    @Transient
    private transient Integer limit;

}
