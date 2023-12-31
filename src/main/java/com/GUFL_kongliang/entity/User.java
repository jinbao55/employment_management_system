package com.GUFL_kongliang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author 孔量
 * @date 2022-12-10 02:42:20
 */
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
@Builder
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	  //id主键
    @Id
    private String id;
	
	  //姓名

    @Column(name = "name")
    private String name;
	
	  //登录名

    @Column(name = "login_name")
    private String loginName;
	
	  //密码

    @Column(name = "password")
    private String password;
	
	  //用户类型

    @Column(name = "type")
    private String type;
	
	  //图片地址

    @Column(name = "img_url")
    private String imgUrl;
	
	  //电话

    @Column(name = "tel")
    private String tel;
	
	  //学院

    @Column(name = "college")
    private String college;


    //专业
    @Column(name = "major")
    private String major;
	
	  //班级

    @Column(name = "class_no")
    private String classNo;
	
	  //职务

    @Column(name = "post")
    private String post;
	
	  //账号状态 1正常  2锁定

    @Column(name = "state")
    private String state;
	
	  //登录地址

    @Column(name = "login_host")
    private String loginHost;



    //是否删除 1是 0不是

    @TableLogic
    @Column(name = "deleted")
    private Integer deleted;



    @Column(name = "crt_time")
    private Date crtTime;

//************************************拓展字段****************************************************


    //新密码
    @Transient
    private transient  String newPassword;


    //重复密码
    @Transient
    private transient  String newPasswordRe;


    //验证码
    @Transient
    private transient  String code;


    @Transient
    private transient Integer page;


    @Transient
    private transient Integer limit;
}
