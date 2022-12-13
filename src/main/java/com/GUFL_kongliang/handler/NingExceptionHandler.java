package com.GUFL_kongliang.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @desc：全局异常类返回信息
 * @author: 孔量
 * @date：2022/12/10 22:57
 */
@ControllerAdvice
public class NingExceptionHandler {
    //表示这个方法处理的就是 NingException中的错误信息
    @ExceptionHandler(value = NingException.class)
    @ResponseBody
    public Map ningError(NingException e){
        e.printStackTrace();
        //将错误信息封装到map中并返回
        Map<String , Object> map = new HashMap<>();
        map.put("code",e.getCode());
        map.put("massage",e.getMassage());
        return map;
    }
}
