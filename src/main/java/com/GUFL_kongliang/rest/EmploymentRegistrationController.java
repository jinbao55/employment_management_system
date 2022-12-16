package com.GUFL_kongliang.rest;

import com.GUFL_kongliang.biz.EmploymentRegistrationBiz;
import com.GUFL_kongliang.entity.EmploymentRegistration;
import com.GUFL_kongliang.entity.dto.EmploymentRegistrationDto;
import com.GUFL_kongliang.utils.BaseResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 就业登记表
 *
 * @author 孔量
 * @email 344119601@qq.com
 * @date 2022-12-13 01:31:52
 */
@RestController
@RequestMapping("employmentRegistration")
public class EmploymentRegistrationController {


    @Autowired
    EmploymentRegistrationBiz employmentRegistrationBiz;


    /**
     * @Desc:  添加就业登记
     * @Auther: 孔量
     * @Date: 2022/12/13 9:44
     * @param: entity
     * @Return: BaseResponse<String>
    */

    @PostMapping("add")
    public BaseResponse<String> addRegister(@RequestBody EmploymentRegistration entity) {

        boolean falg = employmentRegistrationBiz.addRegister(entity);
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
    public BaseResponse<String> delRegister(@RequestBody String id) {

        boolean falg = employmentRegistrationBiz.delRegister(id);
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
    public BaseResponse<String> updateRegister(@RequestBody EmploymentRegistration entity) {

        boolean falg = employmentRegistrationBiz.updateRegister(entity);
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
    public BaseResponse<Page<EmploymentRegistration>> getPageList(@RequestBody EmploymentRegistrationDto dto) {
        Page<EmploymentRegistration> page = employmentRegistrationBiz.getPageList(dto);
        return  new BaseResponse<>(200, "Success", page);
    }

}