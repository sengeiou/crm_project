package com.javasm.sys.controller;


import com.javasm.commons.annotation.Logs;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import com.javasm.sys.entity.CrmDictType;
import com.javasm.sys.service.ICrmDictTypeService;
import org.springframework.web.bind.annotation.RestController;
import com.javasm.commons.base.BaseController;

/**
 * <p>
 * 系统管理-字典类型 前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-02-19
 */
@RestController
@RequestMapping("/sys/crm-dict-type")
public class CrmDictTypeController extends BaseController {
    @Autowired
    private ICrmDictTypeService iCrmDictTypeService;

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        CrmDictType u = iCrmDictTypeService.getById(id);
        return suc(u);
    }

    @GetMapping
    public AxiosResult getList(CrmDictType obj){
        startPage();
        QueryWrapper<CrmDictType> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        w.ne("dict_type_exist",0);
        List<CrmDictType> list = iCrmDictTypeService.list(w);
        return tabledatas(list);
    }

    @Logs(model = "字典管理",ops="添加操作")
    @PostMapping
    public AxiosResult add(@RequestBody CrmDictType obj){
        obj.setDictTypeExist("1");
        boolean isok = iCrmDictTypeService.save(obj);
        return suc(isok);
    }

    @Logs(model = "字典管理",ops="修改操作")
    @PutMapping
    public AxiosResult updateById(@RequestBody CrmDictType obj){
        boolean isok = iCrmDictTypeService.updateById(obj);
        return suc(isok);
    }

    @Logs(model = "字典管理",ops="删除操作")
    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids){
        int i = iCrmDictTypeService.deleteEixst(ids);
        return suc(i);
    }


    @Logs(model = "字典管理",ops="进入界面")
    @GetMapping("into/{id}")
    public AxiosResult into(@PathVariable String id){
        return suc();
    }


    @Logs(model = "字典管理",ops="查询操作")
    @GetMapping("selectOps/{id}")
    public AxiosResult selectOps(@PathVariable String id){
        return suc();
    }



}
