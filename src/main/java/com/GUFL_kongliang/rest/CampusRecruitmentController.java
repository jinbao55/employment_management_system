package com.GUFL_kongliang.rest;

import com.GUFL_kongliang.biz.CampusRecruitmentBiz;
import com.GUFL_kongliang.entity.CampusRecruitment;
import com.GUFL_kongliang.entity.PostInformation;
import com.GUFL_kongliang.handler.TokenInterceptor;
import com.GUFL_kongliang.utils.BaseResponse;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 校园招聘
 *
 * @author 孔量
 * @date 2023-01-13 16:05:30
 */
@RestController
@RequestMapping("campusRecruitment")
public class CampusRecruitmentController{

    @Autowired
    CampusRecruitmentBiz campusRecruitmentBiz;


    @Autowired
    TokenInterceptor tokenInterceptor;


    /**
     * @Desc:  列表查询
     * @Auther: 孔量
     * @Date: 2023/1/13 16:44
     * @param: entity
     * @Return: BaseResponse<List<CampusRecruitment>>
    */
    @PostMapping("getPageList")
    public BaseResponse<List<CampusRecruitment>> getPageList(@RequestBody CampusRecruitment entity) {
        com.github.pagehelper.Page<Object> result = PageHelper.startPage(entity.getPage() == null ? 1 : entity.getPage(), entity.getLimit() == null ? 10 : entity.getLimit());
        List<CampusRecruitment> page = campusRecruitmentBiz.getPageList(entity);
        return  new BaseResponse<>((int) result.getTotal(), "200", page);
    }


    /**
     * @Desc:删除
     * @Auther: 孔量
     * @Date: 2023/1/13 16:47
     * @param: id
     * @Return: BaseResponse<List<CampusRecruitment>>
    */
    @PostMapping("delete")
    public BaseResponse<List<CampusRecruitment>> delete(@RequestBody String id) {
        Map<String, String> operationUser = tokenInterceptor.getOperationUser();
        if("学生".equals(operationUser.get("type"))){
            return  new BaseResponse<>(500, "删除失败,您没有权限", null);
        }
        Integer i = campusRecruitmentBiz.delete(id);
        if(i>0){
            return  new BaseResponse<>(200, "删除成功"+i+"条数据", null);
        }else {
            return  new BaseResponse<>(200, "删除失败", null);
        }
    }



    /**
     * @Desc:  增加或修改
     * @Auther: 孔量
     * @Date: 2023/1/13 16:53
     * @param: entity
     * @Return: BaseResponse<List<PostInformation>>
    */
    @PostMapping("editSave")
    public BaseResponse<List<PostInformation>> editSave(@RequestBody CampusRecruitment entity) {
        if (StringUtils.isNotBlank(entity.getId())) {
            Map<String, String> operationUser = tokenInterceptor.getOperationUser();
            if ("学生".equals(operationUser.get("type"))) {
                return new BaseResponse<>(500, "保存失败,您没有权限", null);
            }
        }
        campusRecruitmentBiz.editSave(entity);
        return  new BaseResponse<>(200, "保存成功", null);
    }

}