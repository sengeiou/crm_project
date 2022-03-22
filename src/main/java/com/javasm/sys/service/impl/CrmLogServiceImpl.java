package com.javasm.sys.service.impl;

import cn.hutool.core.date.DateUtil;
import com.javasm.commons.annotation.Logs;
import com.javasm.commons.entity.E;
import com.javasm.commons.entity.Syslog;
import com.javasm.commons.exception.MvcException;
import com.javasm.commons.utils.LoginUser;
import com.javasm.sys.entity.CrmLog;
import com.javasm.sys.entity.CrmOps;
import com.javasm.sys.entity.CrmUser;
import com.javasm.sys.mapper.CrmLogMapper;
import com.javasm.sys.service.ICrmLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javasm.sys.service.ICrmOpsService;
import com.javasm.sys.service.ICrmRoleService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static java.time.LocalDate.now;


@Aspect
@Service
public class CrmLogServiceImpl extends ServiceImpl<CrmLogMapper, CrmLog> implements ICrmLogService {

    @Resource
     private ICrmLogService crmLogService;





    @Pointcut("@annotation(com.javasm.commons.annotation.Logs)")
    public void logPointcut(){

    }

    //环绕通知
    @Around("logPointcut()")
    public Object dolog(ProceedingJoinPoint jp){
        CrmLog crmLog =new CrmLog();
        CrmUser loginUser = LoginUser.getLoginUser();//登录用户
        String loginUser1 = loginUser.getLoginUser();// 登录名
        String id = loginUser.getId();


        Object result = null;
        Syslog l = new Syslog();

        try {
            //获取到logs注解的信息
            MethodSignature signature = (MethodSignature)jp.getSignature();//连接点方法
            Method method = signature.getMethod();
            Logs annotation = method.getAnnotation(Logs.class);
            String model = annotation.model();//操作模块
            String ops = annotation.ops();//操作类型
            //添加数据
            crmLog.setAccessMoudle(model);
            crmLog.setOpsContext(ops);
            crmLog.setOpsTime(LocalDateTime.now());

            crmLog.setUserId(id);
            crmLog.setLoginUser(loginUser1);



            result = jp.proceed();//执行连接点方法

            //操作时间
            //l.setOpsUser(LoginUser);//当前登录人
        } catch (Throwable e) {
            l.setOpsResult(false);
            throw (RuntimeException)e;
        }finally {
            crmLogService.save(crmLog);
            //把syslog对象插入库。。
            System.out.println("操作日志："+l);
            return result;
        }
    }

}
