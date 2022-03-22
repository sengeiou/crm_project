package com.javasm.sys.controller;


import com.javasm.commons.annotation.Logs;
import com.javasm.commons.entity.E;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import com.javasm.sys.entity.CrmRole;
import com.javasm.sys.service.ICrmRoleService;
import org.springframework.web.bind.annotation.RestController;
import com.javasm.commons.base.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@RestController
@RequestMapping("/sys/crm-role")
public class CrmRoleController extends BaseController {
    @Autowired
    private ICrmRoleService iCrmRoleService;

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        CrmRole u = iCrmRoleService.getById(id);
        return suc(u);
    }

    @GetMapping
    public AxiosResult getList(CrmRole obj){
        startPage();
        QueryWrapper<CrmRole> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<CrmRole> list = iCrmRoleService.list(w);
        return tabledatas(list);
    }

    @GetMapping("getList/name")
    public AxiosResult getListName(CrmRole obj){
        startPage();
        QueryWrapper<CrmRole> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        w.ne("role_status","0");
        List<CrmRole> list = iCrmRoleService.list(w);
        return tabledatas(list);
    }




    @Logs(model = "角色管理",ops="进入界面")
    @GetMapping("into/{id}")
    public AxiosResult into(@PathVariable String id){
        return suc();
    }


    @Logs(model = "角色管理",ops="查询操作")
    @GetMapping("selectOps/{id}")
    public AxiosResult selectOps(@PathVariable String id){
        return suc();
    }




    @Logs(model = "角色管理",ops="添加操作")
    @PostMapping
    public AxiosResult add(@RequestBody CrmRole obj){
        boolean isok = iCrmRoleService.save(obj);
        return suc(isok);
    }






    @Logs(model = "角色管理",ops="修改操作")
    @PutMapping
    public AxiosResult updateById(@RequestBody CrmRole obj){
        boolean isok = iCrmRoleService.updateById(obj);
        return suc(isok);
    }

    @Logs(model = "角色管理",ops="删除操作")
    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids){
        Integer integer = iCrmRoleService.deleteRole(ids);

        if (integer == 1) return suc(integer);

        return AxiosResult.error(E.ROLE_USERING);

    }
}
