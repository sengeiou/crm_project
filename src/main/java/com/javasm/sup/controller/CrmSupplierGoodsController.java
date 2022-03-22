package com.javasm.sup.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import com.javasm.sup.entity.CrmSupplierGoods;
import com.javasm.sup.service.ICrmSupplierGoodsService;
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
@RequestMapping("/sup/crm-supplier-goods")
public class CrmSupplierGoodsController extends BaseController {
    @Autowired
    private ICrmSupplierGoodsService iCrmSupplierGoodsService;

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id) {
        CrmSupplierGoods u = iCrmSupplierGoodsService.getById(id);
        return suc(u);
    }

    @GetMapping
    public AxiosResult getList(CrmSupplierGoods obj) {
        startPage();
        QueryWrapper<CrmSupplierGoods> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<CrmSupplierGoods> list = iCrmSupplierGoodsService.list(w);
        return tabledatas(list);
    }

    @PostMapping
    public AxiosResult add(@RequestBody CrmSupplierGoods obj) {
        boolean isok = iCrmSupplierGoodsService.save(obj);
        return suc(isok);
    }

    @PutMapping
    public AxiosResult updateById(@RequestBody CrmSupplierGoods obj) {
        boolean isok = iCrmSupplierGoodsService.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids) {
        String[] strs = ids.split(",");
        boolean isok = iCrmSupplierGoodsService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }

    @DeleteMapping("/delete")
    public AxiosResult delByGoodsId(@RequestBody CrmSupplierGoods obj) {
        QueryWrapper<CrmSupplierGoods> queryWrapper = new QueryWrapper<>(obj);
        boolean isok = iCrmSupplierGoodsService.remove(queryWrapper);
        return suc(isok);
    }


}
