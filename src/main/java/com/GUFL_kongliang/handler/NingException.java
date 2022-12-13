package com.GUFL_kongliang.handler;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @Desc:  全局异常类
 * @Auther: 孔量
 * @Date: 2022/12/10 23:03
 * @param: null
 * @Return: null
*/
@Data
@NoArgsConstructor
public class NingException extends RuntimeException {
    int code;//错误码
    String massage;//错误提示信息

    //含参构造，用于接收数据
    public NingException(int code, String massage) {
        this.code = code;
        this.massage = massage;
    }
}
