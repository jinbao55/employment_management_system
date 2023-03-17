package com.GUFL_kongliang.rest;

import com.GUFL_kongliang.biz.UserBiz;
import com.GUFL_kongliang.biz.VerificationBiz;
import com.GUFL_kongliang.entity.User;
import com.GUFL_kongliang.handler.NingException;
import com.GUFL_kongliang.utils.BaseResponse;
import com.GUFL_kongliang.utils.RedisUtils;
import com.GUFL_kongliang.utils.UUIDUtils;
import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.ramostear.captcha.HappyCaptcha;
import com.ramostear.captcha.support.CaptchaStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.ramostear.captcha.HappyCaptcha.SESSION_KEY;
import static com.ramostear.captcha.support.CaptchaType.ARITHMETIC_ZH;

/**
 * @author 孔量
 * @date 2022-12-10 02:42:20
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {


    @Autowired
    UserBiz userBiz;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    VerificationBiz verificationBiz;




    /**
     * @Desc: 用户注册
     * @Auther: 孔量
     * @Date: 2022/12/10 10:59
     * @param: user
     * @Return: void
     */
    @PostMapping("register")
    public BaseResponse addAndUpdUser(@RequestBody User user) {
        boolean flag = userBiz.addAndUpdUser(user);
        if (flag) {
            return new BaseResponse<>(200, "保存成功", "保存成功");
        } else {
            return new BaseResponse<>(500, "保存失败", "保存失败");
        }
    }


    /**
     * @Desc: 登录
     * @Auther: 孔量
     * @Date: 2022/12/10 21:50
     * @param: user
     * @Return: BaseResponse
     */
    @PostMapping("toLogin")
    public BaseResponse toLogin(@RequestBody User user) {
        String code = user.getCode();
        if (!this.code.equals(code)) {
            return new BaseResponse<>(500, "false", "验证码错误");
        }
        List<User> users = userBiz.toLogin(user);
        if (Objects.isNull(users) || users.size() < 1) {
            return new BaseResponse<>(500, "false", "账号或密码错误");
        }
        User entity = users.get(0).setPassword("");
        if (users.size() == 1) {
            String toKen = UUIDUtils.getUUID();
            //token有效时间为15分钟
            redisUtils.setValue(toKen, users, 15, TimeUnit.MINUTES);
            if (entity.getState().equals("0")) {
                return new BaseResponse<>(500, "false", "账号已冻结，请联系管理员处理");
            }
            return new BaseResponse<>(200, toKen, entity);
        } else if (users.size() > 1) {
            return new BaseResponse<>(500, "false", "系统错误");
        } else {
            return new BaseResponse<>(500, "false", "账号或密码错误");
        }
    }


    /**
     * @Desc: 忘记密码
     * @Auther: 孔量
     * @Date: 2022/12/10 23:35
     * @param: user
     * @Return: BaseResponse
     */
    @PostMapping("updateUser")
    public BaseResponse updateUser(@RequestBody User user) {
        String newPassword = user.getNewPassword();
        String newPasswordRe = user.getNewPasswordRe();
        if (StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(newPasswordRe)) {
            throw new NingException(400, "新密码和再次输入密码都不能为空");
        }
        boolean equals = newPassword.equals(newPasswordRe);
        if (!equals) {
            return new BaseResponse<>(400, "false", "两次密码输入不一致");
        }
        if (userBiz.updateUser(user)) {
            return new BaseResponse<>(200, "Success", "修改成功，请继续登录");
        } else {
            return new BaseResponse<>(500, "false", "修改失败");
        }
    }


    /**
     * @Desc: 获取用户列表
     * @Auther: 孔量
     * @Date: 2023/1/29 21:09
     * @param: entity
     * @Return: BaseResponse
     */
    @PostMapping("getPageList")
    public BaseResponse getPageList(@RequestBody User entity) {
        com.github.pagehelper.Page<Object> result = PageHelper.startPage(entity.getPage() == null ? 1 : entity.getPage(), entity.getLimit() == null ? 10 : entity.getLimit());
        List<User> pageList = userBiz.getPageList(entity);
        return new BaseResponse<>((int) result.getTotal(), "200", pageList);
    }

    /**
     * @Desc: 编辑用户
     * @Auther: 孔量
     * @Date: 2023/1/29 21:36
     * @param: entity
     * @Return: BaseResponse
     */
    @PostMapping("updUser")
    public BaseResponse updUser(@RequestBody User user) {
        int i = userBiz.updUser(user);
        if (i > 0) {
            return new BaseResponse<>(200, "保存成功", "保存成功");
        } else {
            return new BaseResponse<>(500, "保存失败", "保存失败");
        }
    }


    /**
     * @Desc: 删除用户
     * @Auther: 孔量
     * @Date: 2023/1/29 22:06
     * @param: null
     * @Return: null
     */
    @PostMapping("deleteUser")
    public BaseResponse deleteUser(@RequestBody String id) {
        int i = userBiz.deleteUser(id);
        if (i > 0) {
            return new BaseResponse<>(200, "删除成功", "保存成功");
        } else {
            return new BaseResponse<>(500, "删除失败", "保存失败");
        }
    }


    /**
     * @Desc: 重置密码
     * @Auther: 孔量
     * @Date: 2023/1/29 22:21
     * @param: id
     * @Return: BaseResponse
     */
    @PostMapping("resetPassword")
    public BaseResponse resetPassword(@RequestBody User user) {
        int i = userBiz.resetPassword(user);
        if (i > 0) {
            return new BaseResponse<>(200, "重置成功", "保存成功");
        } else {
            return new BaseResponse<>(500, "重置失败", "保存失败");
        }
    }


    /**
     * @Desc: 退出登录
     * @Auther: 孔量
     * @Date: 2023/1/30 16:06
     * @param: token
     * @Return: BaseResponse
     */
    @PostMapping("loginout")
    public BaseResponse loginout(@RequestBody String token) {
        boolean loginout = userBiz.loginout(token);
        if (loginout) {
            return new BaseResponse<>(200, "已退出登录", "成功");
        } else {
            return new BaseResponse<>(500, "失败", "失败");
        }
    }


    /**
     * @Desc: 修改用户状态
     * @Auther: 孔量
     * @Date: 2023/1/30 17:16
     * @param: token
     * @Return: BaseResponse
     */
    @PostMapping("updateUserstate")
    public BaseResponse updateUserstate(@RequestBody User user) {
        userBiz.updateUserstate(user);
        return new BaseResponse<>(200, "成功", "成功");
    }


    /**
     * @Desc: 获取验证码
     * @Auther: 孔量
     * @Date: 2023/1/30 19:52
     * @param: reqeust
     * @param: response
     * @Return: void
     */
    String code = "";
    @GetMapping("/captcha")
    public void happyCaptcha(HttpServletRequest reqeust, HttpServletResponse response) {
        HappyCaptcha.require(reqeust, response)
                .style(CaptchaStyle.ANIM)
                .type(ARITHMETIC_ZH)//设置展现样式为图片
                .length(4)                            //设置字符长度为4
                .width(220)                            //设置动画宽度为220
                .height(80)                            //设置动画高度为80
                .build().finish();                  //生成并输出验证码;
        code = (String) reqeust.getSession().getAttribute(SESSION_KEY);
        verificationBiz.removeCaptcha(reqeust);
    }

    /**
     * @Desc:  返回验证码到前端校验
     * @Auther: 孔量
     * @Date: 2023/1/31 16:14
     * @Return: String
    */
    @GetMapping("/getCode")
    public String getCode() {
        return code;
    }

}