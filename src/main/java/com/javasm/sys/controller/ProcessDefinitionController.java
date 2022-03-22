package com.javasm.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import com.javasm.sys.entity.ProcessDefinition;
import com.javasm.sys.service.IProcessDefinitionService;
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
@RequestMapping("/sys/process-definition")
public class ProcessDefinitionController extends BaseController {
    @Autowired
    private IProcessDefinitionService iProcessDefinitionService;

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        ProcessDefinition u = iProcessDefinitionService.getById(id);
        return suc(u);
    }

    @GetMapping
    public AxiosResult getList(ProcessDefinition obj){
        startPage();
        QueryWrapper<ProcessDefinition> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<ProcessDefinition> list = iProcessDefinitionService.list(w);
        return tabledatas(list);
    }

    @PostMapping
    public AxiosResult add(@RequestBody ProcessDefinition obj){
        boolean isok = iProcessDefinitionService.save(obj);
        return suc(isok);
    }

    @PutMapping
    public AxiosResult updateById(@RequestBody ProcessDefinition obj){
        boolean isok = iProcessDefinitionService.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids){
        String[] strs = ids.split(",");
        boolean isok = iProcessDefinitionService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }
}
