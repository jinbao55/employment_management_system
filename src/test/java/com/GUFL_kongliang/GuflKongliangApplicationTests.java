package com.GUFL_kongliang;

import com.GUFL_kongliang.entity.Register;
import com.GUFL_kongliang.mapper.RegisterMapper;
import com.GUFL_kongliang.utils.Md5Utils;
import com.GUFL_kongliang.utils.RedisUtils;
import com.GUFL_kongliang.utils.UUIDUtils;
import com.github.javafaker.Address;
import com.github.javafaker.Company;
import com.github.javafaker.Faker;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class GuflKongliangApplicationTests {



    private static  ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("LogAspect-saveLogInfo-pool-%d").build();
    private static  ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) {

        for (int i = 0; i < 15; i++) {
            executor.execute(new SubThread());
        }
        System.out.println("qqq");

    }


    private static class SubThread implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getState()+"----------"+Thread.currentThread().getName());
        }
    }



}
