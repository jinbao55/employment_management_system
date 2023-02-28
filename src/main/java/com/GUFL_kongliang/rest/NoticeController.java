package com.GUFL_kongliang.rest;


import com.GUFL_kongliang.biz.NoticeBiz;
import com.GUFL_kongliang.entity.Notice;
import com.GUFL_kongliang.entity.Recruit;
import com.GUFL_kongliang.utils.BaseResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 *
 * @author 孔量
 * @date 2023-02-28 15:18:54
 */
@Slf4j
@RestController
@RequestMapping("notice")
public class NoticeController{

    @Autowired
    NoticeBiz noticeBiz;


    /**
     * @Desc:  列表查询
     * @Auther: 孔量
     * @Date: 2023/2/28 15:33
     * @param: dto
     * @Return: BaseResponse<List<Notice>>
    */
    @PostMapping("getPageList")
    public BaseResponse<List<Notice>> getPageList(@RequestBody Recruit dto) {
        Page<Object> result = PageHelper.startPage(dto.getPage() == null ? 1 : dto.getPage(), dto.getLimit() == null ? 10 : dto.getLimit());
        List<Notice> page = noticeBiz.getPageList();
        return  new BaseResponse<>((int) result.getTotal(), "200", page);
    }


    /**
     * @Desc:  获取通知
     * @Auther: 孔量
     * @Date: 2023/2/28 15:34
     * @param: dto
     * @Return: BaseResponse<List<Notice>>
    */
    @GetMapping("getNotice")
    public BaseResponse<Notice> getNotice() {
        Notice notice = noticeBiz.getNotice();
        return  new BaseResponse<>(200, "200", notice);
    }



    /**
     * @Desc:  删除
     * @Auther: 孔量
     * @Date: 2023/2/28 15:42
     * @param: id
     * @Return: BaseResponse<List<Recruit>>
    */
    @PostMapping("delete")
    public BaseResponse<List<Recruit>> delete(@RequestBody String id) {
        Integer i = noticeBiz.delete(id);
        if(i>0){
            return  new BaseResponse<>(200, "删除成功"+i+"条数据", null);
        }else {
            return  new BaseResponse<>(200, "删除失败", null);
        }
    }


    /**
     * @Desc:  添加
     * @Auther: 孔量
     * @Date: 2023/2/28 15:48
     * @param: entity
     * @Return: BaseResponse<String>
    */
    @PostMapping("add")
    public BaseResponse<String> add(@RequestBody Notice entity) {
        boolean falg = noticeBiz.add(entity);
        if(falg){
            return  new BaseResponse<>(200, "添加成功", "添加成功");
        }else {
            return  new BaseResponse<>(500, "添加失败", "添加失败");
        }
    }



    /**
     * @Desc:  设置为展示
     * @Auther: 孔量
     * @Date: 2023/2/28 18:16
     * @param: id
     * @Return: BaseResponse<List<Recruit>>
    */
    @PostMapping("setShow")
    public BaseResponse<List<Recruit>> setShow(@RequestBody String id) {
        log.info(id);
        Integer i = noticeBiz.setShow(id);
        if(i>0){
            return  new BaseResponse<>(200, "设置成功", null);
        }else {
            return  new BaseResponse<>(200, "设置失败", null);
        }
    }
}