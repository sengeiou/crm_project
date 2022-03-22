package com.javasm.commons.utils;

import java.util.UUID;

/**
 * @auther: Jin
 * @classname: Zhe
 * @description:
 * @data: 2022/1/3 19:54
 * @version: 0.1
 * @since: 1.8
 */
public class IDUtil {

    public static String UUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    public static String getCode(){
        String uuid = UUID();
        String code = uuid.substring(0, 6);
        return code;
    }
}
