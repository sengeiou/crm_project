package com.javasm.sys.controller;


import com.javasm.commons.annotation.Logs;
import com.javasm.commons.task.MyScheduling.AsyncManager;
import com.javasm.commons.task.MyScheduling.MySchedulingConfiguer;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import com.javasm.sys.entity.CrmTask;
import com.javasm.sys.service.ICrmTaskService;
import org.springframework.web.bind.annotation.RestController;
import com.javasm.commons.base.BaseController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@RestController
@RequestMapping("/sys/crm-task")
public class CrmTaskController extends BaseController {
    @Autowired
    private ICrmTaskService iCrmTaskService;

    @Resource
    private MySchedulingConfiguer configuer;

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        CrmTask u = iCrmTaskService.getById(id);
        return suc(u);
    }

    @GetMapping
    public AxiosResult getList(CrmTask obj){
        startPage();
        QueryWrapper<CrmTask> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<CrmTask> list = iCrmTaskService.list(w);
        return tabledatas(list);
    }



   /* @GetMapping("firing/start")
    public AxiosResult getTrueTask(CrmTask obj){
        startPage();
        List<CrmTask> runningTasks = iCrmTaskService.getRunningTasks();
        configuer.configureTasks();

        return tabledatas();
    }*/

    @Logs(model = "定时任务",ops="进入界面")
    @GetMapping("into/{id}")
    public AxiosResult into(@PathVariable String id){
        return suc();
    }


    @Logs(model = "定时任务",ops="查询操作")
    @GetMapping("selectOps/{id}")
    public AxiosResult selectOps(@PathVariable String id){
        return suc();
    }





    //执行一次任务
    @GetMapping("executeOne/{id}")
    public AxiosResult executeOne(@PathVariable String id){
        CrmTask byId = iCrmTaskService.getById(id);
        String method = byId.getTaskName();
        String taskClz = byId.getTaskClz();
        Object taskInstance = configuer.createTaskInstance(taskClz);
        Runnable runnable = configuer.createRunnable(taskInstance, method);
        AsyncManager.execute(runnable);
        return suc();
    }

    //启动任务
    @GetMapping("start/{id}")
    public AxiosResult start(@PathVariable String id){
        CrmTask byId = iCrmTaskService.getById(id);
        configuer.start(byId);
        iCrmTaskService.updateTaskStatus(id,"true");
        return suc();
    }



    //关闭运行中的任务
    @GetMapping("stop/{id}")
    public AxiosResult stop(@PathVariable String id){
        configuer.stop(id);
        iCrmTaskService.updateTaskStatus(id,"false");
        return suc();
    }



    @PutMapping
    public AxiosResult updateById(@RequestBody CrmTask obj){
        boolean isok = iCrmTaskService.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids){
        String[] strs = ids.split(",");
        boolean isok = iCrmTaskService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }




}
