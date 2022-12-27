package com.GUFL_kongliang.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @desc：web 拦截器
 * @author: 孔量
 * @date：2022/12/17 11:13
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    /**
     * 配置token拦截器生效
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        // 配置自定义拦截器，使其生效，并且放行登陆请求
//        registry.addInterceptor(tokenInterceptor)
//                //拦截的地址
//                .addPathPatterns("/**")
//                //放行的地址
//                .excludePathPatterns("/user/toLogin");
    }

    /**
     * 解决因前后端的端口不一致导致的跨域问题
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET","POST","PUT","OPTIONS","DELETE","PATCH")
                .allowCredentials(true)
                .maxAge(3600);
    }

}