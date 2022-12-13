package com.GUFL_kongliang;

import com.GUFL_kongliang.utils.Md5Utils;
import com.GUFL_kongliang.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class GuflKongliangApplicationTests {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    void contextLoads() {
        String s = Md5Utils.md5Password("kl");
        System.out.println(s);
    }


    @Test
    void  redistest(){

    //    redisUtils.setValue("kl","杨幂",1000, TimeUnit.MINUTES);

       redisUtils.deleteKey("ym");



    }

    @Test
    void  redistestGet(){



        System.out.println(redisUtils.getValue("b9bd673f0f9c41c6ab197f1c15b376a2"));


    }



}
