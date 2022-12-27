package com.GUFL_kongliang.rest;


import com.GUFL_kongliang.biz.RecruitmentInformationBiz;
import com.GUFL_kongliang.entity.RecruitmentInformation;
import com.GUFL_kongliang.utils.BaseResponse;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 招聘信息
 *
 * @author 孔量
 * @date 2022-12-16 09:03:59
 */
@RestController
@RequestMapping("recruitmentInformation")
public class RecruitmentInformationController{


    @Autowired
    RecruitmentInformationBiz recruitmentInformationBiz;

    /**
     * @Desc:  添加就招聘信息
     * @Auther: 孔量
     * @Date: 2022/12/13 9:44
     * @param: entity
     * @Return: BaseResponse<String>
     */
    @PostMapping("add")
    public BaseResponse<String> add(@RequestBody RecruitmentInformation entity) {

        boolean falg = recruitmentInformationBiz.add(entity);
        if(falg){
            return  new BaseResponse<>(200, "Success", "添加成功");
        }else {
            return  new BaseResponse<>(500, "Success", "添加失败");
        }
    }



    /**
     * @Desc:  删除就业登记
     * @Auther: 孔量
     * @Date: 2022/12/13 9:44
     * @param: id
     * @Return: BaseResponse<String>
     */
    @PostMapping("del")
    public BaseResponse<String> del(@RequestBody String id) {

        boolean falg = recruitmentInformationBiz.del(id);
        if(falg){
            return  new BaseResponse<>(200, "Success", "删除成功");
        }else {
            return  new BaseResponse<>(500, "Success", "删除失败");
        }
    }


    /**
     * @Desc:  修改就业登记
     * @Auther: 孔量
     * @Date: 2022/12/13 9:44
     * @param: entity
     * @Return: BaseResponse<String>
     */
    @PostMapping("update")
    public BaseResponse<String> update(@RequestBody RecruitmentInformation entity) {

        boolean falg = recruitmentInformationBiz.update(entity);
        if(falg){
            return  new BaseResponse<>(200, "Success", "修改成功");
        }else {
            return  new BaseResponse<>(500, "Success", "修改失败");
        }
    }


    /**
     * @Desc:  列表查询
     * @Auther: 孔量
     * @Date: 2022/12/13 10:58
     * @param: dto
     * @Return: BaseResponse<Page<EmploymentRegistration>>
     */
    @PostMapping("getPageList")
    public BaseResponse <List<RecruitmentInformation>> getPageList(@RequestBody RecruitmentInformation dto) {//
        Page<Object> result = PageHelper.startPage(dto.getPage() == null ? 1 : dto.getPage(), dto.getLimit() == null ? 10 : dto.getLimit());
        List<RecruitmentInformation> page = recruitmentInformationBiz.getPageList(dto);
        return  new BaseResponse<>(500, String.valueOf(result.getTotal()), page);
    }

}