package com.javasm.order.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import com.javasm.order.entity.CrmOrderDetail;
import com.javasm.order.service.ICrmOrderDetailService;
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
@RequestMapping("/order/crm-order-detail")
public class CrmOrderDetailController extends BaseController {
    @Resource
    private ICrmOrderDetailService iCrmOrderDetailService;

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        CrmOrderDetail u = iCrmOrderDetailService.getById(id);
        return suc(u);
    }

    @GetMapping("update/{id}")
    public AxiosResult updateById(@PathVariable String id){
        boolean b= iCrmOrderDetailService.updateDetailById(id);
        return suc(b);
    }



    @GetMapping
    public AxiosResult getList(CrmOrderDetail obj){
        startPage();

        List<CrmOrderDetail> list = iCrmOrderDetailService.getDetailAll(obj);
        return tabledatas(list);
    }

    @PostMapping
    public AxiosResult add(@RequestBody CrmOrderDetail obj){
        boolean isok = iCrmOrderDetailService.save(obj);
        return suc(isok);
    }

    @PutMapping
    public AxiosResult updateById(@RequestBody CrmOrderDetail obj){
        boolean isok = iCrmOrderDetailService.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids){
        String[] strs = ids.split(",");
        boolean isok = iCrmOrderDetailService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }
}
