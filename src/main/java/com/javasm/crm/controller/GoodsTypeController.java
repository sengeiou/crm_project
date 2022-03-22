package com.javasm.crm.controller;


import com.javasm.commons.annotation.Logs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import com.javasm.crm.entity.GoodsType;
import com.javasm.crm.service.IGoodsTypeService;
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
@RequestMapping("/crm/goods-type")
@Api(tags = "赠品类型/分类接口",description = "赠品类型/分类接口")
public class GoodsTypeController extends BaseController {
    @Autowired
    private IGoodsTypeService iGoodsTypeService;

    @GetMapping("{id}")
    @ApiOperation(value = "根据id查询")
    public AxiosResult getById(@PathVariable String id){
        GoodsType u = iGoodsTypeService.getById(id);
        return suc(u);
    }

    @GetMapping
    @Logs(model = "商品类型",ops="查询操作")
    @ApiOperation(value = "获取全部")
    public AxiosResult getList(GoodsType obj){
        startPage();
        QueryWrapper<GoodsType> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<GoodsType> list = iGoodsTypeService.list(w);
        return tabledatas(list);
    }

    @GetMapping("tree")
    public AxiosResult getTreeList(GoodsType obj){

        List<GoodsType> list = iGoodsTypeService.getTreeList(obj);
        return AxiosResult.suc(list);
    }

    @PostMapping
    @Logs(model = "商品类型",ops="添加操作")
    @ApiOperation(value = "基础添加")
    public AxiosResult add(@RequestBody GoodsType obj){
        boolean isok = iGoodsTypeService.add(obj);
        return suc(isok);
    }

    @PutMapping
    @Logs(model = "商品类型",ops="修改操作")
    @ApiOperation(value = "基础根据id修改")
    public AxiosResult updateById(@RequestBody GoodsType obj){
        boolean isok = iGoodsTypeService.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    @Logs(model = "商品类型",ops="删除操作")
    @ApiOperation(value = "基础根据id批量删除")
    public AxiosResult delByIds(@PathVariable String ids){
        String[] strs = ids.split(",");
        boolean isok = iGoodsTypeService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }
}
