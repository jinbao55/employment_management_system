package com.GUFL_kongliang.utils;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 孔量
 * @date 2022/12/10 12:28
 * @Desc redis 工具类
 */

@Slf4j
@Service
public class RedisUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @author 孔量
     * @date 2022/5/7 12:34
     * @param key
     * @return java.lang.Object
     * @Desc 根据key获取值
     */
    public Object getValue(String key){
        Object value = null;
        try {
            value = redisTemplate.opsForValue().get(key);
        }catch (Exception e){
            log.error("Redis获取Value，key为:{}报错：",key,e);
        }
        return value;
    }

    /**
     * @Desc:  批量查询
     * @Auther: 孔量
     * @Date: 2022/12/10 23:31
     * @param: keys
     * @Return: Object
    */
    public Object multiGet(List keys){
        Object value = null;
        try {
            value = redisTemplate.opsForValue().multiGet(keys);
        }catch (Exception e){
            log.error("Redis获取multiGet，key为:{}报错：",keys,e);
        }
        return value;
    }

    /**
     * @author 孔量
     * @date 2022/5/7 14:54
     * @param key, value, time, unit
     * @return void
     * @Desc redis 值的设置
     */
    public void setValue(String key, Object value, int time, TimeUnit unit){
        try {
            log.info("Redis setValue key:{},value:{},time:{}",key,value,time);
            redisTemplate.opsForValue().set(key,value, time, unit);
        }catch (Exception e){
            log.error("Redis存入Value，key为:{}报错：",key,e);
        }

    }

    /**
     * @author 孔量
     * @date 2022/5/5 15:46
     * @param prefix
     * @return void
     * @Desc 根据前缀删除key值  前缀需要 + *
     */
    public int deleteByPrefix(String prefix) {
        try {
            Set<String> keys = redisTemplate.keys(prefix + "*");
            if (CollectionUtils.isNotEmpty(keys)) {
                redisTemplate.delete(keys);
            }
            return keys.size();
        }catch (Exception e){
            log.error("Redis删除key，key为:{}报错：",prefix,e);
            return 0;
        }
    }


    /**
     * @param key
     * @return void
     * @author 孔量
     * @date 2022/5/5 15:46
     * @Desc 删除key值
     */
    public Boolean deleteKey(String key) {
        try {
            return redisTemplate.delete(key);
        }catch (Exception e){
            log.error("Redis删除key，key为:{}报错：",key,e);
        }
        return null;
    }

    /**
     * @author 孔量
     * @date 2022/5/5 15:05
     * @param map
     * @return java.util.Map
     * @Desc 将dto中的值转化为key的后缀
     */
    public String changeMapToString(HashMap<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        if (map != null && !map.isEmpty()) {
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                Object value = entry.getValue();
                String key = entry.getKey();
                sb.append(key).append(value);
            }
        }
        return sb.toString();
    }

}
