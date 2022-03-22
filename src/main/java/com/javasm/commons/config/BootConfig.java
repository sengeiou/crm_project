package com.javasm.commons.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:
 * @className:BootConfig
 * @description:
 * @date:2022/2/10 16:39
 * @version:0.1
 * @since:1.8
 */
@Configuration
@MapperScan("com.javasm.*.mapper")
@EnableTransactionManagement//开启事务
@EnableAsync//异步注解识别，一般不使用@Async
@EnableScheduling
@EnableCaching//redis注解识别
@EnableAspectJAutoProxy//aop编程aspectj注解识别
public class BootConfig {

    @Resource
    private BootProperties bp;

    /**
    cors跨域配置
     */
    @Bean
    public FilterRegistrationBean regisCorsFilter() {
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setMaxAge(bp.getCors().getMaxAge());
        config.setAllowCredentials(bp.getCors().getCredentials());
        List<String> allowedHeaders = bp.getCors().getAllowedHeaders();
        if(allowedHeaders.size()==0){
            config.addAllowedHeader("*");
        }else{
            config.setAllowedHeaders(allowedHeaders);
        }
        List<String> allowedMethods = bp.getCors().getAllowedMethods();
        if(allowedMethods.size()==0)
            config.addAllowedMethod("*");
        else
            config.setAllowedMethods(allowedMethods);

        config.setAllowedOrigins(bp.getCors().getOrigins());
        config.setExposedHeaders(bp.getCors().getExposedHeader());
        configurationSource.registerCorsConfiguration(bp.getCors().getPath(), config);
        CorsFilter corsFilter = new CorsFilter(configurationSource);

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(corsFilter);
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;

    }






}
