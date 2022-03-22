package com.javasm.crm.controller;


import com.javasm.commons.annotation.Logs;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.javasm.crm.entity.Goods;
import com.javasm.crm.service.IGoodsService;
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
@RequestMapping("/crm/goods")
public class GoodsController extends BaseController {
    @Autowired
    private IGoodsService iGoodsService;

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        Goods u = iGoodsService.getById(id);
        return suc(u);
    }
//    @GetMapping("{id}")
//    public AxiosResult getById1(@PathVariable String id){
//        Map u = iGoodsService.getGoodsAndProdById(id);
//        return suc(u);
//    }
    @GetMapping
    @Logs(model = "商品管理",ops="查询操作")
    public AxiosResult getList(Goods obj){
        startPage();
        QueryWrapper<Goods> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<Goods> list = iGoodsService.list(w);
        return tabledatas(list);
    }

    @PostMapping
    @Logs(model = "商品管理",ops="添加操作")
    public AxiosResult add(@RequestBody Goods obj){

        boolean isok = iGoodsService.add(obj);
        return suc(isok);
    }

    @PutMapping
    @Logs(model = "商品管理",ops="修改操作")
    public AxiosResult updateById(@RequestBody Goods obj){
        boolean isok = iGoodsService.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    @Logs(model = "商品管理",ops="删除操作")
    public AxiosResult delByIds(@PathVariable String ids){
        String[] strs = ids.split(",");
        boolean isok = iGoodsService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }

    //获得所有商品，通过供应商商品
    @GetMapping("/allGoods")
    public AxiosResult getAllGoodsBySupplierId(Goods obj){
        startPage();
        QueryWrapper<Goods> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<Goods> list = iGoodsService.list(w);
        return suc(list);
    }

    //获得所有二类商品
    @GetMapping("/allTwoGoods")
    public AxiosResult getAllTwoGoods(Goods obj){
        startPage();
        obj.setGoodsOneOrTwo("2");
        QueryWrapper<Goods> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<Goods> list = iGoodsService.list(w);
        return suc(list);
    }
}
