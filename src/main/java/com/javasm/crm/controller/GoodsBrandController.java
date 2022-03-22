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
import com.javasm.crm.entity.GoodsBrand;
import com.javasm.crm.service.IGoodsBrandService;
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
@RequestMapping("/crm/goods-brand")
@Api(tags = "商品品牌接口",description = "商品品牌接口")
public class GoodsBrandController extends BaseController {
    @Autowired
    private IGoodsBrandService iGoodsBrandService;

    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取")
    public AxiosResult getById(@PathVariable String id){
        GoodsBrand u = iGoodsBrandService.getById(id);
        return suc(u);
    }

    @Logs(model = "品牌管理",ops="查询操作")
    @GetMapping
    @ApiOperation(value = "获取全部")
    public AxiosResult getList(GoodsBrand obj){
        startPage();
        QueryWrapper<GoodsBrand> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<GoodsBrand> list = iGoodsBrandService.list(w);
        return tabledatas(list);
    }

    @PostMapping
    @Logs(model = "品牌管理",ops="添加操作")
    @ApiOperation(value = "基础新增")
    public AxiosResult add(@RequestBody GoodsBrand obj){
        boolean isok = iGoodsBrandService.add(obj);
        return suc(isok);
    }

    @PutMapping
    @Logs(model = "品牌管理",ops="修改操作")
    @ApiOperation(value = "基础修改")
    public AxiosResult updateById(@RequestBody GoodsBrand obj){
        boolean isok = iGoodsBrandService.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    @ApiOperation(value = "批量删除")
    @Logs(model = "品牌管理",ops="删除操作")
    public AxiosResult delByIds(@PathVariable String ids){
        String[] strs = ids.split(",");
        boolean isok = iGoodsBrandService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }
}
