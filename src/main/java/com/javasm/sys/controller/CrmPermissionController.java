package com.javasm.sys.controller;


import com.javasm.commons.annotation.Logs;
import com.javasm.commons.entity.E;
import com.javasm.commons.utils.LoginUser;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Permission;
import java.util.Arrays;
import java.util.List;
import com.javasm.sys.entity.CrmPermission;
import com.javasm.sys.service.ICrmPermissionService;
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
@RequestMapping("/sys/crm-permission")
public class CrmPermissionController extends BaseController {
    @Autowired
    private ICrmPermissionService iCrmPermissionService;

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        CrmPermission u = iCrmPermissionService.getById(id);
        return suc(u);
    }

    @GetMapping
    public AxiosResult getList(CrmPermission obj){
        startPage();
        QueryWrapper<CrmPermission> w = new QueryWrapper<>(obj);
        w.orderByAsc("create_time");
        w.ne("can_ops","0");
        List<CrmPermission> list = iCrmPermissionService.list(w);
        List<CrmPermission> tree = iCrmPermissionService.getTree(list, "0");
        if (tree.size()==0) return suc(list);
        return suc(tree);
    }

    @GetMapping("permission/getMenu")
    public AxiosResult getMenu(@RequestBody CrmPermission obj){
        QueryWrapper<CrmPermission> w = new QueryWrapper<>(obj);
        w.ne("can_ops","0");
        List<CrmPermission> list = iCrmPermissionService.list(w);
        List<CrmPermission> tree = iCrmPermissionService.getTree(list, "0");
        if (tree.size()==0) return tabledatas(list);
        return tabledatas(tree);
    }







    @GetMapping("permissionTree/{id}")
    public AxiosResult getList(@PathVariable String id){
        QueryWrapper<CrmPermission> w = new QueryWrapper();
        w.ne("p_status","0");
        w.ne("can_ops","0");
        List<CrmPermission> list = iCrmPermissionService.list(w);
        List<CrmPermission> tree = iCrmPermissionService.getTree(list, "0");
        return suc(tree);
    }



    @Logs(model = "菜单管理",ops="添加操作")
    @PostMapping
    public AxiosResult add(@RequestBody CrmPermission obj){
        String loginUserName = LoginUser.getLoginUserName();
        obj.setCreateBy(loginUserName);
        obj.setpStatus("1");
        obj.setCanOps("1");
        boolean isok = iCrmPermissionService.save(obj);
        return suc(isok);
    }



    @Logs(model = "菜单管理",ops="修改操作")
    @PutMapping
    public AxiosResult updateById(@RequestBody CrmPermission obj){
        System.out.println(obj);
        String loginUserName = LoginUser.getLoginUserName();
        obj.setUpdateBy(loginUserName);
        boolean isok = iCrmPermissionService.updateById(obj);
        return suc(isok);
    }



    @Logs(model = "菜单管理",ops="删除操作")
    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids){
        Integer integer = iCrmPermissionService.deletePermission(ids);
        if (integer == 1) return suc();
        return AxiosResult.error(E.PERMISSION_CHILD);
    }

    @Logs(model = "菜单管理",ops="进入界面")
    @GetMapping("into/{id}")
    public AxiosResult into(@PathVariable String id){
        return suc();
    }


    @Logs(model = "菜单管理",ops="查询操作")
    @GetMapping("selectOps/{id}")
    public AxiosResult selectOps(@PathVariable String id){
        return suc();
    }
}
