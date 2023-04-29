package com.GUFL_kongliang.rest;


import com.GUFL_kongliang.biz.RecruitBiz;
import com.GUFL_kongliang.entity.Recruit;
import com.GUFL_kongliang.handler.TokenInterceptor;
import com.GUFL_kongliang.utils.BaseResponse;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 企业招聘
 *
 * @author 孔量
 * @date 2022-12-28 19:57:57
 */
@RestController
@RequestMapping("recruit")
public class RecruitController {

    @Autowired
    RecruitBiz recruitBiz;


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
    public BaseResponse<List<Recruit>> getPageList(@RequestBody Recruit dto) {
        Page<Object> result = PageHelper.startPage(dto.getPage() == null ? 1 : dto.getPage(), dto.getLimit() == null ? 10 : dto.getLimit());
        List<Recruit> page = recruitBiz.getPageList(dto);
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
    public BaseResponse<List<Recruit>> editSave(@RequestBody Recruit entity) {
        if (StringUtils.isNotBlank(entity.getId())) {
            Map<String, String> operationUser = tokenInterceptor.getOperationUser();
            if ("学生".equals(operationUser.get("type"))) {
                return new BaseResponse<>(500, "保存失败,您没有权限", null);
            }
        }
        recruitBiz.editSave(entity);
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
    public BaseResponse<List<Recruit>> delete(@RequestBody String id) {
        Map<String, String> operationUser = tokenInterceptor.getOperationUser();
        if("学生".equals(operationUser.get("type"))){
            return  new BaseResponse<>(500, "删除失败,您没有权限", null);
        }
        Integer i = recruitBiz.delete(id);
        if(i>0){
            return  new BaseResponse<>(200, "删除成功"+i+"条数据", null);
        }else {
            return  new BaseResponse<>(200, "删除失败", null);
        }
    }
}