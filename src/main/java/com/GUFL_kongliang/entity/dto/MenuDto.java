package com.GUFL_kongliang.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;


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
public class MenuDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	  //id
    @Id
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


    /***************************************拓展字段******************************************************/

    @Transient
    private transient List<MenuDto> menus;

}
