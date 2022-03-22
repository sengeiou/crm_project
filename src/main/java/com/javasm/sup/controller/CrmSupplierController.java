package com.javasm.sup.controller;


import com.javasm.commons.annotation.Logs;
import com.javasm.sup.mapper.CrmSupplierMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.javasm.sup.entity.CrmSupplier;
import com.javasm.sup.service.ICrmSupplierService;
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
@RequestMapping("/sup/crm-supplier")
public class CrmSupplierController extends BaseController {
    @Autowired
    private ICrmSupplierService iCrmSupplierService;

    @Resource
    private CrmSupplierMapper crmSupplierMapper;

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        CrmSupplier u = iCrmSupplierService.getById(id);
        return suc(u);
    }


    @GetMapping
    public AxiosResult getList(CrmSupplier obj){
        startPage();
        QueryWrapper<CrmSupplier> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<CrmSupplier> list = iCrmSupplierService.list(w);
        return tabledatas(list);
    }

    @GetMapping("/getQuerySet")
    public AxiosResult getQuerySet(CrmSupplier obj){
        startPage();
        QueryWrapper<CrmSupplier> queryWrapper = new QueryWrapper<>(obj);
        queryWrapper.orderByDesc("update_time");

        queryWrapper.select("id","s_name");
        List<CrmSupplier> crmSuppliers = crmSupplierMapper.selectList(queryWrapper);
        return tabledatas(crmSuppliers);
    }

    @PostMapping
    public AxiosResult add(@RequestBody CrmSupplier obj){
        boolean isok = iCrmSupplierService.save(obj);
        return suc(isok);
    }

    @PutMapping
    public AxiosResult updateById(@RequestBody CrmSupplier obj){
        boolean isok = iCrmSupplierService.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids){
        String[] strs = ids.split(",");
        boolean isok = iCrmSupplierService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }

}
