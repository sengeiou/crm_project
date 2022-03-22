package com.javasm.commons.task.MyScheduling;

import com.javasm.sys.entity.CrmTask;
import com.javasm.sys.service.ICrmTaskService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class MySchedulingConfiguer implements SchedulingConfigurer {
    @Resource
    private  ICrmTaskService iCrmTaskService;

    private Map<String,ScheduledTask> tasks = new HashMap<>();

    private ScheduledTaskRegistrar scheduledTaskRegistrar;


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

        this.scheduledTaskRegistrar=scheduledTaskRegistrar;
//        scheduledTaskRegistrar：用来编码方式调度任务（启动，停止）
        //在这个方法中把running状态的任务注册。
        List<CrmTask> runningTasks = iCrmTaskService.getRunningTasks();
        for (CrmTask runningTask : runningTasks) {
            start(runningTask);
        }
    }


  //开启任务
    public void  start(CrmTask crmTask){
        String id = crmTask.getId();
        String method = crmTask.getTaskName();
        String taskClz = crmTask.getTaskClz();
        String taskStatus = crmTask.getTaskStatus();
        String taskCron = crmTask.getTaskCron();
        Object taskInstance = createTaskInstance(taskClz);
        Runnable runnable = createRunnable(taskInstance, method);
        CronTask t = new CronTask(runnable, taskCron);
        ScheduledTask scheduledTask = scheduledTaskRegistrar.scheduleCronTask(t);
        tasks.put(id,scheduledTask);
    }


    //停止任务
    public void stop(String id){
        ScheduledTask scheduledTask = tasks.get(id);
        if(scheduledTask!=null){
            scheduledTask.cancel();
            tasks.remove(id);//从Map中移出任务。
        }
    }




    public Runnable createRunnable(Object taskInstance,String method){

        Runnable  task =  new Runnable(){
            @Override
            public void run() {
                Class<?> aClass = taskInstance.getClass();
                try {
                    Method method1 = aClass.getMethod(method);
                    method1.invoke(taskInstance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        return task;

    }




    public Object createTaskInstance(String clzName){
        try {
            Class<?> aClass = Class.forName(clzName);
            Object o = aClass.newInstance();
            return o;
        } catch (Exception e) {
            return null;
        }
    }
}
