package com.GUFL_kongliang.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * 通知表
 * 
 * @author 孔量
 * @date 2023-02-28 15:18:54
 */
@Table(name = "notice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notice implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    private String id;
	
	  //标题

    @Column(name = "title")
    private String title;
	
	  //内容

    @Column(name = "content")
    private String content;
	
	  //作者

    @Column(name = "author")
    private String author;
	
	  //日期

    @Column(name = "date")
    private String date;



    //是展示 1是 0不是

    @Column(name = "is_show")
    private Integer isShow;



    @TableLogic
    @Column(name = "deleted")
    private Integer deleted;

}
