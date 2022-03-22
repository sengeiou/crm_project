package com.javasm.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author:
 * @className:Logs
 * @description:
 * @date:2022/2/10 18:59
 * @version:0.1
 * @since:1.8
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Logs {
    String model();
    String ops();
}
