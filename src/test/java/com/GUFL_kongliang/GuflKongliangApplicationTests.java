package com.GUFL_kongliang;

import com.GUFL_kongliang.entity.Register;
import com.GUFL_kongliang.mapper.RegisterMapper;
import com.GUFL_kongliang.utils.Md5Utils;
import com.GUFL_kongliang.utils.RedisUtils;
import com.GUFL_kongliang.utils.UUIDUtils;
import com.github.javafaker.Address;
import com.github.javafaker.Company;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class GuflKongliangApplicationTests {

    @Autowired
    private RedisUtils redisUtils;


    @Autowired
    RegisterMapper registerMapper;

    @Test
    void contextLoads() {
        String s = Md5Utils.md5Password("kl");
        System.out.println(s);
    }


    @Test
    void redistest() {

        //    redisUtils.setValue("kl","杨幂",1000, TimeUnit.MINUTES);

        String s="eda2679095574999ac37eeca3fadd09f";

        redisUtils.deleteKey(s);


    }





}
