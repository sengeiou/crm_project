package com.javasm.commons.exception;

import com.javasm.commons.entity.AxiosResult;
import com.javasm.commons.entity.E;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author:
 * @className:GlobExceptionHandler
 * @description: 基于aop，对控制层做增强织入，异常通知。
 * @date:2021/12/28 19:57
 * @version:0.1
 * @since:1.8
 */
@RestControllerAdvice
public class GlobExceptionHandler {

    private static Logger l=LoggerFactory.getLogger(GlobExceptionHandler.class);

    @ExceptionHandler(ArithmeticException.class)
    public AxiosResult doArithmeticException(ArithmeticException e){
        //TODO 记录异常堆栈信息
       l.error(e.getMessage(),e);
        return AxiosResult.error(E.BY_ZERO);
    }

    @ExceptionHandler(MvcException.class)
    public AxiosResult doMvcException(MvcException e){
        E e1=e.getE();
        l.error(e1.getMsg(),e1);
        return AxiosResult.error(e1);
    }


    @ExceptionHandler(Exception.class)
    public AxiosResult doException(Exception e){
        l.error(e.getMessage(),e);
        return AxiosResult.error(E.ERROR);
    }


}
