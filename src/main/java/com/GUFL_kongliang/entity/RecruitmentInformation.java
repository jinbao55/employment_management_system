package com.GUFL_kongliang.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * 招聘信息
 * 
 * @author 孔量
 * @date 2022-12-16 09:03:59
 */
@Table(name = "recruitment_information")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
@Builder
public class RecruitmentInformation implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    private String id;

	  //招聘标题
    @Id
    private String title;
	
	  //发布时间

    @Column(name = "crt_time")
    private Date crtTime;
	
	  //招聘公司

    @Column(name = "company")
    private String company;
	
	  //企业规模

    @Column(name = "scale")
    private String scale;
	
	  //行业

    @Column(name = "industry")
    private String industry;
	
	  //工作地点

    @Column(name = "position")
    private String position;
	
	  //邮箱

    @Column(name = "mail")
    private String mail;
	
	  //用户名

    @Column(name = "people_num")
    private String peopleNum;
	
	  //状态

    @Column(name = "state")
    private String state;


    //是否删除 1是 0不是

    @TableLogic
    @Column(name = "deleted")
    private Integer deleted;


    /***********************************************拓展字段***********************************************************/

    @Transient
    private transient Integer page;


    @Transient
    private transient Integer limit;
	

}
