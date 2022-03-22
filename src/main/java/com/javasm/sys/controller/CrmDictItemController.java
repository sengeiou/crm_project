package com.javasm.sys.controller;


import com.javasm.commons.annotation.Logs;
import com.javasm.commons.dictEnum.DictEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.javasm.sys.entity.CrmDictItem;
import com.javasm.sys.service.ICrmDictItemService;
import org.springframework.web.bind.annotation.RestController;
import com.javasm.commons.base.BaseController;

/**
 * <p>
 * 系统管理-后台字典管理 前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-02-19
 */
@RestController
@RequestMapping("/sys/crm-dict-item")
@Api(tags = "字典项获取接口",description = "字典项获取接口")
public class CrmDictItemController extends BaseController {
    @Autowired
    private ICrmDictItemService iCrmDictItemService;

    @GetMapping("{id}")
    @ApiOperation(value = "根据id获取")
    public AxiosResult getById(@PathVariable String id){
        CrmDictItem u = iCrmDictItemService.getById(id);
        return suc(u);
    }

    @GetMapping("find/{typeId}")
    @ApiOperation(value = "根据类型id获取")
    public AxiosResult FindByTypeId(@PathVariable String typeId){
        QueryWrapper<CrmDictItem> w = new QueryWrapper();
        w.eq("type_id",typeId);
        w.ne("dict_status","0");
        w.ne("dict_exist","0");
        List<CrmDictItem> items = iCrmDictItemService.list(w);
        return suc(items);
    }

    @GetMapping("find1/{typeId}")
    @ApiOperation(value = "根据类型id获取")
    public AxiosResult FindByTypeId1(@PathVariable String typeId){
        QueryWrapper<CrmDictItem> w = new QueryWrapper();
        w.eq("type_id",typeId);
        w.ne("dict_exist","0");
        List<CrmDictItem> items = iCrmDictItemService.list(w);
        return suc(items);
    }




    @GetMapping("giftStatus")
    @ApiOperation(value = "获取赠品状态")
    public AxiosResult getGiftStatus(){
        String dictCode = DictEnum.STATUS.getDictCode();
        String dictName = DictEnum.STATUS.getDictName();
        List<CrmDictItem> crmDictItems = iCrmDictItemService.queryByCodeAndName(dictCode, dictName);
        return suc(crmDictItems);
    }

    @GetMapping("checkStatus")
    @ApiOperation(value = "获取审核状态")
    public AxiosResult getCheckStatus(){
        String dictCode = DictEnum.CHECK_STATUS.getDictCode();
        String dictName = DictEnum.CHECK_STATUS.getDictName();
        List<CrmDictItem> crmDictItems = iCrmDictItemService.queryByCodeAndName(dictCode, dictName);
        return suc(crmDictItems);
    }

    @GetMapping("approvalResult")
    @ApiOperation(value = "获取审核结果")
    public AxiosResult getApprovalResult(){
        String dictCode = DictEnum.APPROVAL_RESULT.getDictCode();
        String dictName = DictEnum.APPROVAL_RESULT.getDictName();
        List<CrmDictItem> crmDictItems = iCrmDictItemService.queryByCodeAndName(dictCode, dictName);
        return suc(crmDictItems);
    }

    @GetMapping("prodChangeType")
    @ApiOperation(value = "获取换货名称")
    public AxiosResult getProdChangeType(){
        String dictCode = DictEnum.PROD_CHANGE_TYPE.getDictCode();
        String dictName = DictEnum.PROD_CHANGE_TYPE.getDictName();
        List<CrmDictItem> crmDictItems = iCrmDictItemService.queryByCodeAndName(dictCode, dictName);
        return suc(crmDictItems);
    }

    @GetMapping("goodsColor")
    @ApiOperation(value = "获取商品颜色")
    public AxiosResult getGoodsColor(){
        String dictCode = DictEnum.GOODS_COLOR.getDictCode();
        String dictName = DictEnum.GOODS_COLOR.getDictName();
        List<CrmDictItem> crmDictItems = iCrmDictItemService.queryByCodeAndName(dictCode, dictName);
        return suc(crmDictItems);
    }


    @GetMapping("orderType")
    @ApiOperation(value = "获取订单类型")
    public AxiosResult getOrderType(){
        String dictCode = DictEnum.ORDER_TYPE.getDictCode();
        String dictName = DictEnum.ORDER_TYPE.getDictName();
        List<CrmDictItem> crmDictItems = iCrmDictItemService.queryByCodeAndName(dictCode, dictName);
        return suc(crmDictItems);
    }

    @GetMapping("orderAction")
    @ApiOperation(value = "获取订单动作")
    public AxiosResult getOrderAction(){
        String dictCode = DictEnum.ORDER_ACTION.getDictCode();
        String dictName = DictEnum.ORDER_ACTION.getDictName();
        List<CrmDictItem> crmDictItems = iCrmDictItemService.queryByCodeAndName(dictCode, dictName);
        return suc(crmDictItems);
    }

    @GetMapping("businessType")
    @ApiOperation(value = "获取业务类型")
    public AxiosResult getBusinessType(){
        String dictCode = DictEnum.BUSINESS_TYPE.getDictCode();
        String dictName = DictEnum.BUSINESS_TYPE.getDictName();
        List<CrmDictItem> crmDictItems = iCrmDictItemService.queryByCodeAndName(dictCode, dictName);
        return suc(crmDictItems);
    }

    @GetMapping("payType")
    @ApiOperation(value = "获取订单类型")
    public AxiosResult getPayType(){
        String dictCode = DictEnum.PAY_TYPE.getDictCode();
        String dictName = DictEnum.PAY_TYPE.getDictName();
        List<CrmDictItem> crmDictItems = iCrmDictItemService.queryByCodeAndName(dictCode, dictName);
        return suc(crmDictItems);
    }

    @GetMapping("orderStatus")
    @ApiOperation(value = "获取订单类型")
    public AxiosResult getOrderStatus(){
        String dictCode = DictEnum.ORDER_STATUS.getDictCode();
        String dictName = DictEnum.ORDER_STATUS.getDictName();
        List<CrmDictItem> crmDictItems = iCrmDictItemService.queryByCodeAndName(dictCode, dictName);
        return suc(crmDictItems);
    }


    @GetMapping
    @ApiOperation(value = "获取全部")
    public AxiosResult getList(CrmDictItem obj){
        startPage();
        QueryWrapper<CrmDictItem> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        w.ne("dict_exist","0");
        List<CrmDictItem> list = iCrmDictItemService.list(w);
        return tabledatas(list);
    }


    @Logs(model = "字典管理",ops="添加操作")
    @PostMapping
    @ApiOperation(value = "基础新增")
    public AxiosResult add(@RequestBody CrmDictItem obj){
        obj.setDictExist("1");
        boolean isok = iCrmDictItemService.save(obj);
        return suc(isok);
    }

    @Logs(model = "字典管理",ops="修改操作")
    @PutMapping
    @ApiOperation(value = "基础根据id修改")
    public AxiosResult updateById(@RequestBody CrmDictItem obj){
        boolean isok = iCrmDictItemService.updateById(obj);
        return suc(isok);
    }


    @Logs(model = "字典管理",ops="删除操作")
    @DeleteMapping("{ids}")
    @ApiOperation(value = "基础根据id批量删除")
    public AxiosResult delByIds(@PathVariable String ids){

        iCrmDictItemService.deleteExist(ids);

        return suc();
    }
    @GetMapping("item/{dictType}")
    public AxiosResult getDictItem(@PathVariable String dictType){
        List<Map<String, Object>> dictItem = iCrmDictItemService.getDictItem(dictType);
        return suc(dictItem);
    }
}
