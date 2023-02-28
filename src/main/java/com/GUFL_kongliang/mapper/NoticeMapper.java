package com.GUFL_kongliang.mapper;


import com.GUFL_kongliang.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 
 * 
 * @author 孔量
 * @date 2023-02-28 15:18:54
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    Notice getNotice();

    void setNoShow();

    Integer setShow(String id);
}
