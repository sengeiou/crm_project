package com.javasm.commons.interceptors;

import com.javasm.commons.entity.E;
import com.javasm.commons.exception.MvcException;
import com.javasm.commons.utils.JwtUtils;
import com.javasm.commons.utils.LoginUser;
import com.javasm.sys.entity.CrmUser;
import com.javasm.sys.service.ICrmUserService;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class LoginInterceptors implements HandlerInterceptor {
    @Resource
    private ICrmUserService iCrmUserService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       String t = request.getHeader("admin-token");
        if (!StringUtils.hasLength(t)) {
            System.out.println("不存在长度");

            throw new MvcException(E.NO_LOGIN);
        }else {
            System.out.println(t);
            Claims c = JwtUtils.parse(t);

            if (c == null) {
                System.out.println("密码错误");
                throw new MvcException(E.NO_LOGIN);
            }else {

                String uid = JwtUtils.getUid(c);
                CrmUser sysUser = iCrmUserService.queryByPhone(uid);

                //把当前登录用户对象，保存到线程变量中
                LoginUser.setLoginUser(sysUser);

            //刷新token 避免使用过程中token失效
            String generate = JwtUtils.generate(uid);
            response.setHeader("admin-token", generate);

            return true;

    }}
    }}


