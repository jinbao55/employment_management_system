package com.GUFL_kongliang.utils;

import java.util.UUID;

/**
 * @desc：uuid工具类
 * @author: 孔量
 * @date：2022/12/10 11:55
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
