package com.javasm.sys.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import com.javasm.sys.entity.CrmOps;
import com.javasm.sys.service.ICrmOpsService;
import org.springframework.web.bind.annotation.RestController;
import com.javasm.commons.base.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-02-21
 */
@RestController
@RequestMapping("/sys/crm-ops")
public class CrmOpsController extends BaseController {
    @Autowired
    private ICrmOpsService iCrmOpsService;

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        CrmOps u = iCrmOpsService.getById(id);
        return suc(u);
    }

    @GetMapping
    public AxiosResult getList(CrmOps obj){
        startPage();
        QueryWrapper<CrmOps> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<CrmOps> list = iCrmOpsService.list(w);
        return tabledatas(list);
    }

    @PostMapping
    public AxiosResult add(@RequestBody CrmOps obj){
        boolean isok = iCrmOpsService.save(obj);
        return suc(isok);
    }

    @PutMapping
    public AxiosResult updateById(@RequestBody CrmOps obj){
        boolean isok = iCrmOpsService.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids){
        String[] strs = ids.split(",");
        boolean isok = iCrmOpsService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }
}
