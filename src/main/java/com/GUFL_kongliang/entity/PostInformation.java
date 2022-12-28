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
 * 企业信息
 * 
 * @author 孔量
 * @date 2022-12-28 11:11:13
 */
@Table(name = "post_information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostInformation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	  //用户名
    @Id
    private String id;
	
	  //企业名称

    @Column(name = "name")
    private String name;
	
	  //企业类型

    @Column(name = "type")
    private String type;
	
	  //企业规模

    @Column(name = "scale")
    private Integer scale;
	
	  //行业

    @Column(name = "industry")
    private String industry;
	
	  //地点

    @Column(name = "place")
    private String place;
	
	  //合作学院

    @Column(name = "cooperation")
    private String cooperation;
	
	  //更新时间
      @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "updtime")
    private Date updtime;
	
	  //是否删除
      @TableLogic
    @Column(name = "deleted")
    private Integer deleted;


    /***********************************************拓展字段***********************************************************/

    @Transient
    private transient Integer page;


    @Transient
    private transient Integer limit;


}
