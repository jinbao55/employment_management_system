package com.GUFL_kongliang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.persistence.Table;
import java.io.Serializable;



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
    private Integer type;
	
	  //图片地址

    @Column(name = "img_url")
    private String imgUrl;
	
	  //电话

    @Column(name = "tel")
    private String tel;
	
	  //学院

    @Column(name = "college")
    private String college;
	
	  //班级

    @Column(name = "class_no")
    private String classNo;
	
	  //职务

    @Column(name = "post")
    private String post;
	
	  //账号状态

    @Column(name = "state")
    private String state;
	
	  //登录地址

    @Column(name = "login_host")
    private String loginHost;


//************************************拓展字段****************************************************


    //新密码
    @Transient
    private transient  String newPassword;


    //重复密码
    @Transient
    private transient  String newPasswordRe;


}
