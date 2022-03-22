package com.javasm.sys.controller;


import com.javasm.commons.annotation.Logs;
import com.javasm.commons.entity.E;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import com.javasm.sys.entity.CrmDepartment;
import com.javasm.sys.service.ICrmDepartmentService;
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
@RequestMapping("/sys/crm-department")
public class CrmDepartmentController extends BaseController {
    @Autowired
    private ICrmDepartmentService iCrmDepartmentService;

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        CrmDepartment u = iCrmDepartmentService.getById(id);
        return suc(u);
    }

    @GetMapping
    public AxiosResult getList(CrmDepartment obj){
        startPage();
        QueryWrapper<CrmDepartment> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<CrmDepartment> list = iCrmDepartmentService.list(w);
        return tabledatas(list);
    }




    @GetMapping("getList/Name")
    public AxiosResult getListName(CrmDepartment obj){
        startPage();
        QueryWrapper<CrmDepartment> w = new QueryWrapper<>(obj);
        w.ne("d_status","0");
        w.orderByDesc("update_time");
        List<CrmDepartment> list = iCrmDepartmentService.list(w);
        return tabledatas(list);
    }



    @Logs(model = "部门管理",ops="进入界面")
    @GetMapping("into/{id}")
    public AxiosResult into(@PathVariable String id){
        return suc();
    }


    @Logs(model = "部门管理",ops="查询操作")
    @GetMapping("selectOps/{id}")
    public AxiosResult selectOps(@PathVariable String id){
        return suc();
    }




    @Logs(model = "部门管理",ops="添加操作")
    @PostMapping
    public AxiosResult add(@RequestBody CrmDepartment obj){
        boolean isok = iCrmDepartmentService.save(obj);
        return suc(isok);
    }

    @Logs(model = "部门管理",ops="修改操作")
    @PutMapping
    public AxiosResult updateById(@RequestBody CrmDepartment obj){
        boolean isok = iCrmDepartmentService.updateById(obj);
        return suc(isok);
    }

    @Logs(model = "部门管理",ops="删除操作")
    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids){
        Integer integer = iCrmDepartmentService.deleteDepartment(ids);
       if (integer==1)  return suc();
       return AxiosResult.error(E.DEPARTMENT_USERING);
    }
}
