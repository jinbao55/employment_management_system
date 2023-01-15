package com.GUFL_kongliang.rest;

import com.GUFL_kongliang.biz.RegisterBiz;
import com.GUFL_kongliang.entity.Recruit;
import com.GUFL_kongliang.entity.Register;
import com.GUFL_kongliang.utils.BaseResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 就业登记
 *
 * @author 孔量
 * @date 2023-01-14 14:40:11
 */
@RestController
@RequestMapping("register")
public class RegisterController{

    @Autowired
    RegisterBiz registerBiz;




    @GetMapping("editSave")
    public BaseResponse<Register> selectByida(String id) {
        Register register = registerBiz.selectByIda(id);
        return  new BaseResponse<>(200, "保存成功", register);
    }


    @PostMapping("editSave")
    public BaseResponse<List<Register>> editSave(@RequestBody Register entity) {
        registerBiz.editSave(entity);
        return  new BaseResponse<>(200, "保存成功", null);
    }



    /**
     * @Desc:  删除
     * @Auther: 孔量
     * @Date: 2023/1/14 15:35
     * @param: id
     * @Return: BaseResponse<List<Register>>
    */
    @PostMapping("delete")
    public BaseResponse<List<Register>> delete(@RequestBody String id) {
        Integer i = registerBiz.delete(id);
        if(i>0){
            return  new BaseResponse<>(200, "删除成功"+i+"条数据", null);
        }else {
            return  new BaseResponse<>(200, "删除失败", null);
        }
    }



    /**
     * @Desc:  列表查询
     * @Auther: 孔量
     * @Date: 2023/1/14 15:36
     * @param: entity
     * @Return: BaseResponse<List<Recruit>>
    */
    @PostMapping("getPageList")
    public BaseResponse<List<Register>> getPageList(@RequestBody Register entity) {
        Page<Object> result = PageHelper.startPage(entity.getPage() == null ? 1 : entity.getPage(), entity.getLimit() == null ? 10 : entity.getLimit());
        List<Register> page = registerBiz.getPageList(entity);
        return  new BaseResponse<>((int) result.getTotal(), "200", page);
    }




    @GetMapping("getPageLista")
    public BaseResponse<List<Register>> getPag6List() {
        registerBiz.addList();
        return  new BaseResponse<>(200, "200", null);
    }



}