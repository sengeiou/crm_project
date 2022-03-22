package com.javasm.commons.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletUtil {

    public static ServletRequestAttributes getRequestAttributes(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return requestAttributes;
    }

    public static HttpServletRequest getRequest(){
        return getRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse(){
        return getRequestAttributes().getResponse();
    }

    public static HttpSession getSession(){
        return getRequest().getSession();
    }

    public static String getParameter(String key,String defauleValue){
        HttpServletRequest request = getRequest();
        String value = request.getParameter(key);
        if(!StringUtils.hasLength(value))
            return defauleValue;
        return value;
    }

    public static Integer getParameter(String key,Integer defauleValue){
        HttpServletRequest request = getRequest();
        String value = request.getParameter(key);
        if(!StringUtils.hasLength(value))
            return defauleValue;
        return Integer.parseInt(value);
    }
}
