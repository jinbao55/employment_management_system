package com.GUFL_kongliang.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;



/**
 * 菜单控制表
 * 
 * @author 孔量
 * @date 2023-01-31 09:02:37
 */
@Table(name = "menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	  //id
      @TableId
    private String menuid;
	
	  //图标

    @Column(name = "icon")
    private String icon;
	
	  //页面名

    @Column(name = "menuname")
    private String menuname;
	
	  //是否有第三个

    @Column(name = "has_third")
    private String hasThird;
	
	  //路径

    @Column(name = "url")
    private String url;
	
	  //父级id

    @Column(name = "parent_id")
    private String parentId;
	
	  //创建时间

    @Column(name = "crt_time")
    private Date crtTime;


        //排序
    @Column(name = "sort")
    private Integer sort;

	
	  //是否关键

    @Column(name = "is_crux")
    private String isCrux;
	
	  //删除标识

    @TableLogic
    @Column(name = "deleted")
    private Integer deleted;


    /***************************************拓展字段******************************************************/

    @Transient
    private transient Integer page;


    @Transient
    private transient Integer limit;


}
