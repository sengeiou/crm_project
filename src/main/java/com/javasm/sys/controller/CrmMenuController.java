package com.javasm.sys.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.javasm.commons.annotation.Logs;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import com.javasm.sys.entity.CrmMenu;
import com.javasm.sys.service.ICrmMenuService;
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
@RequestMapping("/sys/crm-menu")
public class CrmMenuController extends BaseController {
    @Autowired
    private ICrmMenuService iCrmMenuService;

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        CrmMenu u = iCrmMenuService.getById(id);
        return suc(u);
    }

    @GetMapping("select/{id}")
    public AxiosResult getList(@PathVariable String id){
        CrmMenu crmMenu = new CrmMenu();
        QueryWrapper w = new QueryWrapper(crmMenu);
        w.ne("parent_id","0");
        List<CrmMenu> list = iCrmMenuService.list(w);
        return suc(list);
    }






}
