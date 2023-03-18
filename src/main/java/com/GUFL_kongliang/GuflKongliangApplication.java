package com.GUFL_kongliang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.GUFL_kongliang.mapper")
@EnableAsync
public class GuflKongliangApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuflKongliangApplication.class, args);
    }

}
