package com.GUFL_kongliang.rest;

import com.GUFL_kongliang.biz.RegisterBiz;
import com.GUFL_kongliang.entity.Register;
import com.GUFL_kongliang.handler.TokenInterceptor;
import com.GUFL_kongliang.utils.BaseResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Autowired
    TokenInterceptor tokenInterceptor;


    /** 根据id查询
     * @Desc:
     * @Auther: 孔量
     * @Date: 2023/1/16 11:18
     * @param: id
     * @Return: BaseResponse<Register>
    */
    @GetMapping("selectById")
    public BaseResponse<Register> selectByida(String id) {
        Register register = registerBiz.selectByIda(id);
        return  new BaseResponse<>(200, "保存成功", register);
    }


    /**
     * @Desc: 修改或添加
     * @Auther: 孔量
     * @Date: 2023/1/16 11:18
     * @param: entity
     * @Return: BaseResponse<List<Register>>
    */
    @PostMapping("editSave")
    public BaseResponse<String> editSave(@RequestBody Register entity) {
        Map<String, String> operationUser = tokenInterceptor.getOperationUser();
        if("学生".equals(operationUser.get("type"))){
            String name = entity.getName();
            String user = operationUser.get("user");
            if(!user.equals(name)){
                return  new BaseResponse<>(500, "修改失败,您只能修改自己的数据", null);
            }
        }
        return  new BaseResponse<>(200, registerBiz.editSave(entity), registerBiz.editSave(entity));
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
        Map<String, String> operationUser = tokenInterceptor.getOperationUser();
        if("学生".equals(operationUser.get("type"))){
            return  new BaseResponse<>(500, "删除失败,您没有权限", null);
        }
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



    /**
     * @Desc: 手写分页列表查询
     * @Auther: 孔量
     * @Date: 2023/1/16 9:40
     * @param: entity
     * @Return: BaseResponse<List<Register>>
    */
    @PostMapping("getPageLista")
    public BaseResponse<List<Register>> getPagLista(@RequestBody Register entity) {
        HashMap<String, Object> pageLista = registerBiz.getPageLista(entity);
        List<Register> list = (List<Register>) pageLista.get("list");
        return  new BaseResponse<>((Integer) pageLista.get("tol"), "200", list);
    }


}