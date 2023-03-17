package com.GUFL_kongliang.biz;

import com.GUFL_kongliang.entity.Notice;
import com.GUFL_kongliang.entity.PostInformation;
import com.GUFL_kongliang.mapper.NoticeMapper;
import com.GUFL_kongliang.utils.UUIDUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 
 *
 * @author 孔量
 * @date 2023-02-28 15:18:54
 */
@Service
@Slf4j
public class NoticeBiz extends ServiceImpl<NoticeMapper, Notice> {



    /**
     * @Desc:  列表查询
     * @Auther: 孔量
     * @Date: 2023/2/28 15:33
     * @param: entity
     * @Return: List<Notice>
    */
    public List<Notice> getPageList() {
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();
        return this.baseMapper.selectList(wrapper);
    }


    /**
     * @Desc:  获取通知
     * @Auther: 孔量
     * @Date: 2023/2/28 15:35
     * @param: dto
     * @Return: Notice
    */
    public Notice getNotice() {
       return baseMapper.getNotice();
    }

    /**
     * @Desc:  删除
     * @Auther: 孔量
     * @Date: 2023/2/28 15:42
     * @param: id
     * @Return: Integer
    */
    public Integer delete(String id) {
        return this.baseMapper.deleteById(id);
    }

    /**
     * @Desc:  添加
     * @Auther: 孔量
     * @Date: 2023/2/28 15:48
     * @param: entity
     * @Return: boolean
    */
    public boolean add(Notice entity) {
        entity.setId(UUIDUtils.getUUID());
        entity.setDeleted(0);
        return save(entity);
    }


    /**
     * @Desc:  设置首页展示信息
     * @Auther: 孔量
     * @Date: 2023/2/28 18:10
     * @param: id
     * @Return: Integer
    */
    public Integer setShow(String id) {
        //修改其他通知为非展示状态
        baseMapper.setNoShow();
        //将本条信息修改为展示状态
      return baseMapper.setShow(id);
    }
}