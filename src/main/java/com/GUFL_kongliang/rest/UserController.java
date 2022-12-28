package com.GUFL_kongliang.rest;

import com.GUFL_kongliang.biz.UserBiz;
import com.GUFL_kongliang.entity.User;
import com.GUFL_kongliang.handler.NingException;
import com.GUFL_kongliang.utils.BaseResponse;
import com.GUFL_kongliang.utils.RedisUtils;
import com.GUFL_kongliang.utils.UUIDUtils;
import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 孔量
 * @date 2022-12-10 02:42:20
 */
@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    UserBiz userBiz;

    @Autowired
    RedisUtils redisUtils;


    /**
     * @Desc: 用户注册
     * @Auther: 孔量
     * @Date: 2022/12/10 10:59
     * @param: user
     * @Return: void
     */
    @PostMapping("register")
    public BaseResponse register(@RequestBody User user) {
        boolean flag = userBiz.addUser(user);
        if (flag) {
            return new BaseResponse<>(200, "Success", "注册成功");
        } else {
            return new BaseResponse<>(500, "false", "注册失败");
        }
    }



    /**
     * @Desc:  登录
     * @Auther: 孔量
     * @Date: 2022/12/10 21:50
     * @param: user
     * @Return: BaseResponse
    */
    @PostMapping("toLogin")
    public BaseResponse toLogin(@RequestBody User user) {
        List<User> users = userBiz.toLogin(user);
        if (users.size()==1) {
            String toKen = UUIDUtils.getUUID();
            //token有效时间为15分钟
            redisUtils.setValue(toKen,users,15, TimeUnit.MINUTES);
            users.get(0).setPassword("");
            return new BaseResponse<>(200, toKen, users.get(0));
        } else if(users.size()>1){
            return new BaseResponse<>(500, "false", "系统错误");
        }else {
            return new BaseResponse<>(500, "false", "账号或密码错误");
        }
    }



    /**
     * @Desc:  忘记密码
     * @Auther: 孔量
     * @Date: 2022/12/10 23:35
     * @param: user
     * @Return: BaseResponse
    */
    @PostMapping("updateUser")
    public BaseResponse updateUser(@RequestBody User user) {

        String newPassword = user.getNewPassword();
        String newPasswordRe = user.getNewPasswordRe();

        if(StringUtils.isEmpty(newPassword)||StringUtils.isEmpty(newPasswordRe)){
            throw  new NingException(400,"新密码和再次输入密码都不能为空");
        }
        boolean equals = newPassword.equals(newPasswordRe);
        if(!equals){
            return new BaseResponse<>(400, "false", "两次密码输入不一致");
        }
        if (userBiz.updateUser(user)) {
            return new BaseResponse<>(200, "Success", "修改成功，请继续登录");
        } else {
            return new BaseResponse<>(500, "false", "修改失败");
        }
    }


    @PostMapping("getPageList")
    public BaseResponse getPageList(@RequestBody User entity) {
        com.github.pagehelper.Page<Object> result = PageHelper.startPage(entity.getPage() == null ? 1 : entity.getPage(), entity.getLimit() == null ? 10 : entity.getLimit());
        List<User> pageList = userBiz.getPageList(entity);
        return new BaseResponse<>((int) result.getTotal(), "200", pageList);
    }


}