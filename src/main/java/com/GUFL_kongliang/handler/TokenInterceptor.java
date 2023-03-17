package com.GUFL_kongliang.handler;

import com.GUFL_kongliang.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @desc：token校验类
 * @author: 孔量
 * @date：2022/12/17 11:07
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        // 放行浏览器的预检请求
//        if (request.getMethod().equals("OPTIONS")) {
//            return true;
//        }
//        // 获取请求头中携带的token值
//        String token = request.getHeader("Authorization");
//        // token验证
//        if(token!=null) {
//
//            Object value =  redisUtils.getValue(token);
//
//            if (value!=null) {
//                //刷新token有效时间
//                redisUtils.setValue(token,value,15, TimeUnit.MINUTES);
//                return true;
//            }
//            // 拦截请求
//            throw new NingException(505, "token无效,请重新登录");
//        }
//        //拦截请求
//        throw new NingException(506, "请登录");
        return true;
    }
}