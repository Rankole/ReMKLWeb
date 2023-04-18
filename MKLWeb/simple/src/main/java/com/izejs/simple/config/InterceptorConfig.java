package com.izejs.simple.config;

import com.izejs.simple.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //注册loginInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(loginInterceptor);

        registration.addPathPatterns("/**")
                .excludePathPatterns("/page/login>login")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/page/login>register")
                .excludePathPatterns("/captcha")
                .excludePathPatterns("/api/register")
                .excludePathPatterns("/**/*.html")
                .excludePathPatterns("/layui/**")
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.woff")
                .excludePathPatterns("/**/*.ttf")
                .excludePathPatterns("/api/sendEmail")
                .excludePathPatterns("/images/**");
    }
}
