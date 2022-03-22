package com.javasm.sys.controller;


import com.javasm.commons.annotation.Logs;
import com.javasm.commons.utils.DateUtils;
import com.javasm.sys.entity.GetDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.javasm.sys.entity.CrmLog;
import com.javasm.sys.service.ICrmLogService;
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
@RequestMapping("/sys/crm-log")
public class CrmLogController extends BaseController {
    @Autowired
    private ICrmLogService iCrmLogService;

    @Resource
    private DateUtils dateUtils;



    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        CrmLog u = iCrmLogService.getById(id);
        return suc(u);
    }

    @PostMapping("date")
    public AxiosResult getDate(@RequestBody GetDate date){
        Date frontTime1 = DateUtils.getDate(date.getFrontTime());
        Date afterTime1 = DateUtils.getDate(date.getAfterTime());
        dateUtils.setFrontTime(frontTime1);
        dateUtils.setAfterTime(afterTime1);

        return suc();
    }

    @GetMapping
    public AxiosResult getList(CrmLog obj){
        startPage();
        QueryWrapper<CrmLog> w = new QueryWrapper<>(obj);
        Date frontTime = dateUtils.getFrontTime();
        Date afterTime = dateUtils.getAfterTime();
        w.between("ops_time",frontTime,afterTime);
        w.orderByDesc("ops_time");
        List<CrmLog> list = iCrmLogService.list(w);
        return tabledatas(list);
    }

    @Logs(model = "日志管理",ops="进入界面")
    @GetMapping("into/{id}")
    public AxiosResult into(@PathVariable String id){
        return suc();
    }

    @Logs(model = "日志管理",ops="查询操作")
    @GetMapping("selectOps/{id}")
    public AxiosResult selectOps(@PathVariable String id){

        return suc();
    }








    @PostMapping
    public AxiosResult add(@RequestBody CrmLog obj){
        boolean isok = iCrmLogService.save(obj);
        return suc(isok);
    }

    @PutMapping
    public AxiosResult updateById(@RequestBody CrmLog obj){
        boolean isok = iCrmLogService.updateById(obj);
        return suc(isok);
    }

    @Logs(model = "日志管理",ops="删除操作")
    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids){
        String[] strs = ids.split(",");
        boolean isok = iCrmLogService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }


}
