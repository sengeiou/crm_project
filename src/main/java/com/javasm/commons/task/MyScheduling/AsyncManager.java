package com.javasm.commons.task.MyScheduling;

import com.javasm.commons.utils.SpringUtil;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @auther: Jin
 * @classname: Zhe
 * @description:
 * @data: 2022/2/24 10:29
 * @version: 0.1
 * @since: 1.8
 */
public class AsyncManager {
    public static void execute(Runnable r){
        ThreadPoolTaskExecutor bean = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
        bean.execute(r);
    }
}
