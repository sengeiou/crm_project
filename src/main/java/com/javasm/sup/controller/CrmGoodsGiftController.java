package com.javasm.sup.controller;


import com.javasm.commons.dictEnum.DictEnum;
import com.javasm.sys.entity.CrmDictItem;
import com.javasm.sys.service.ICrmDictItemService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.javasm.sup.entity.CrmGoodsGift;
import com.javasm.sup.service.ICrmGoodsGiftService;
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
@RequestMapping("/sup/crm-goods-gift")
public class CrmGoodsGiftController extends BaseController {
    @Autowired
    private ICrmGoodsGiftService iCrmGoodsGiftService;

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        CrmGoodsGift u = iCrmGoodsGiftService.getById(id);
        return suc(u);
    }

    @GetMapping
    public AxiosResult getList(CrmGoodsGift obj){
        startPage();
        QueryWrapper<CrmGoodsGift> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<CrmGoodsGift> list = iCrmGoodsGiftService.list(w);
        return tabledatas(list);
    }

    //根据条件查询
    @PostMapping("query")
    public AxiosResult getGiftByParams(@RequestBody Map map) throws InvocationTargetException, IllegalAccessException {
        CrmGoodsGift goodsGift = new CrmGoodsGift();

        BeanUtils.populate(goodsGift,map);
        Integer pageSize = (Integer) map.get("pageSize");
        Integer pageNum = (Integer) map.get("pageNum");
        List<CrmGoodsGift> crmGoodsGifts = iCrmGoodsGiftService.getGiftByParams(goodsGift,pageNum,pageSize);
        return tabledatas(crmGoodsGifts);
    }


    @PostMapping
    public AxiosResult add(@RequestBody CrmGoodsGift obj){
        boolean isok = iCrmGoodsGiftService.save(obj);
        return suc(isok);
    }

    @PutMapping
    public AxiosResult updateById(@RequestBody CrmGoodsGift obj){
        boolean isok = iCrmGoodsGiftService.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids){
        String[] strs = ids.split(",");
        boolean isok = iCrmGoodsGiftService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }

}
