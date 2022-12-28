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
 * 企业招聘
 * 
 * @author 孔量
 * @date 2022-12-28 19:57:57
 */
@Table(name = "recruit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recruit implements Serializable {
	private static final long serialVersionUID = 1L;
	
	  //id
    @Id
    private String id;
	
	  //招聘主题

    @Column(name = "theme")
    private String theme;


    //发布时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date")
    private Date date;
	
	  //招聘企业

    @Column(name = "company")
    private String company;
	
	  //企业规模

    @Column(name = "scale")
    private String scale;
	
	  //企业类型

    @Column(name = "type")
    private String type;
	
	  //所属行业

    @Column(name = "industry")
    private String industry;
	
	  //工作地点

    @Column(name = "place")
    private String place;
	
	  //邮箱

    @Column(name = "mail")
    private String mail;
	
	  //招聘人数

    @Column(name = "number")
    private Integer number;
	
	  //招聘状态

    @Column(name = "state")
    private String state;
	

	
	  //删除
      @TableLogic
    @Column(name = "deleted")
    private Integer deleted;

    /***********************************************拓展字段***********************************************************/

    @Transient
    private transient Integer page;


    @Transient
    private transient Integer limit;
}
