package com.javasm.commons.config;

import com.javasm.commons.interceptors.LoginInterceptors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Component
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptors loginInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //设置拦截器访问限制
        registry.addInterceptor(loginInterceptors).addPathPatterns("/**").excludePathPatterns("/sys/crm-user/login/**","/sys/crm-user/phone/**","/sys/crm-user/passLogin/**","/excels/**.xlsx","/crm/product/export");
    }

}
