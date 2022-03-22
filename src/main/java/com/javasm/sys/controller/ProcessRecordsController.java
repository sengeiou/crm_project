package com.javasm.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import com.javasm.sys.entity.ProcessRecords;
import com.javasm.sys.service.IProcessRecordsService;
import org.springframework.web.bind.annotation.RestController;
import com.javasm.commons.base.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-02-22
 */
@RestController
@RequestMapping("/sys/process-records")
public class ProcessRecordsController extends BaseController {
    @Autowired
    private IProcessRecordsService iProcessRecordsService;

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        ProcessRecords u = iProcessRecordsService.getById(id);
        return suc(u);
    }



    @GetMapping
    public AxiosResult getList(ProcessRecords obj){
        startPage();
        QueryWrapper<ProcessRecords> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<ProcessRecords> list = iProcessRecordsService.list(w);
        return tabledatas(list);
    }

    @PostMapping
    public AxiosResult add(@RequestBody ProcessRecords obj){
        boolean isok = iProcessRecordsService.save(obj);
        return suc(isok);
    }

    @PutMapping
    public AxiosResult updateById(@RequestBody ProcessRecords obj){
        boolean isok = iProcessRecordsService.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids){
        String[] strs = ids.split(",");
        boolean isok = iProcessRecordsService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }
}
