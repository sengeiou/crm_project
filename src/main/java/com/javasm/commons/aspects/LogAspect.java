package com.javasm.commons.aspects;

import com.javasm.commons.annotation.Logs;
import com.javasm.commons.entity.Syslog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class LogAspect {

   @Pointcut("@annotation(com.javasm.commons.annotation.Logs)")
    public void logPointcut(){

    }

    //环绕通知
    @Around("logPointcut()")
    public Object dolog(ProceedingJoinPoint jp){
        Object result=null;
        Syslog l = new Syslog();
        try {
            //获取到logs注解的信息
            Object[] args = jp.getArgs();//形参
            Object target = jp.getTarget();//目标对象
            MethodSignature signature = (MethodSignature)jp.getSignature();//连接点方法
            Method method = signature.getMethod();
            Logs annotation = method.getAnnotation(Logs.class);
            String model = annotation.model();//操作模块
            String ops = annotation.ops();//操作类型
            l.setModelName(model);
            l.setOpsName(ops);
            //l.setOpsTime(DateUtil.now()); //操作时间
            //l.setOpsUser(LoginUser);//当前登录人
            result = jp.proceed();//执行连接点方法
            l.setOpsResult(true);
        } catch (Throwable e) {
            l.setOpsResult(false);
            throw (RuntimeException)e;
        }finally {
            //把syslog对象插入库。。
            System.out.println("操作日志："+l);
        }
        return result;
    }




}
