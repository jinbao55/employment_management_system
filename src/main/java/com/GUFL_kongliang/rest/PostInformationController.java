package com.GUFL_kongliang.rest;


import com.GUFL_kongliang.biz.PostInformationBiz;
import com.GUFL_kongliang.entity.PostInformation;
import com.GUFL_kongliang.handler.TokenInterceptor;
import com.GUFL_kongliang.utils.BaseResponse;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 企业信息
 *
 * @author 孔量
 * @date 2022-12-28 11:11:13
 */
@RestController
@RequestMapping("postInformation")
public class PostInformationController{


    @Autowired
    PostInformationBiz postInformationBiz;


    @Autowired
    TokenInterceptor tokenInterceptor;


    /**
     * @Desc:  列表查询
     * @Auther: 孔量
     * @Date: 2022/12/13 10:58
     * @param: dto
     * @Return: BaseResponse<Page<EmploymentRegistration>>
     */
    @PostMapping("getPageList")
    public BaseResponse<List<PostInformation>> getPageList(@RequestBody PostInformation dto) {
        Page<Object> result = PageHelper.startPage(dto.getPage() == null ? 1 : dto.getPage(), dto.getLimit() == null ? 10 : dto.getLimit());
        List<PostInformation> page = postInformationBiz.getPageList(dto);
        return  new BaseResponse<>((int) result.getTotal(), "200", page);
    }

    /**
     * @Desc: 增加或修改
     * @Auther: 孔量
     * @Date: 2022/12/28 14:48
     * @param: entity
     * @Return: BaseResponse<List<PostInformation>>
    */
    @PostMapping("editSave")
    public BaseResponse<List<PostInformation>> editSave(@RequestBody PostInformation entity) {
        if (StringUtils.isNotBlank(entity.getId())) {
            Map<String, String> operationUser = tokenInterceptor.getOperationUser();
            if ("学生".equals(operationUser.get("type"))) {
                return new BaseResponse<>(500, "保存失败,您没有权限", null);
            }
        }
        postInformationBiz.editSave(entity);
        return  new BaseResponse<>(200, "保存成功", null);
    }



    /**
     * @Desc: 删除
     * @Auther: 孔量
     * @Date: 2022/12/28 14:48
     * @param: entity
     * @Return: BaseResponse<List<PostInformation>>
     */
    @PostMapping("delete")
    public BaseResponse<List<PostInformation>> delete(@RequestBody String id) {
        Map<String, String> operationUser = tokenInterceptor.getOperationUser();
        if("学生".equals(operationUser.get("type"))){
            return  new BaseResponse<>(500, "删除失败,您没有权限", null);
        }
        Integer i = postInformationBiz.delete(id);
        if(i>0){
            return  new BaseResponse<>(200, "删除成功"+i+"条数据", null);
        }else {
            return  new BaseResponse<>(200, "删除失败", null);
        }
    }
}