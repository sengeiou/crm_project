package com.javasm.order.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.javasm.commons.annotation.Logs;
import com.javasm.commons.entity.E;
import com.javasm.commons.utils.LoginUser;
import com.javasm.crm.entity.Goods;
import com.javasm.crm.service.IGoodsService;
import com.javasm.sys.entity.CrmUser;
import com.javasm.sys.entity.ProcessRecords;
import com.javasm.sys.service.IProcessRecordsService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.javasm.order.entity.CrmAppeal;
import com.javasm.order.service.ICrmAppealService;
import org.springframework.web.bind.annotation.RestController;
import com.javasm.commons.base.BaseController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@RestController
@RequestMapping("/order/crm-appeal")
public class CrmAppealController extends BaseController {
    @Resource
    private ICrmAppealService iCrmAppealService;
    @Resource
    private IGoodsService iGoodsService;


    @GetMapping("{id}")
    @Logs(model = "退换货管理",ops = "查看详情")
    public AxiosResult getById(@PathVariable String id) {
        CrmAppeal u = iCrmAppealService.getdetailInfoById(id);
        return suc(u);
    }


    @GetMapping("listGoods")
    @Logs(model = "退换货管理",ops = "查看更换商品")
    @ApiOperation(value = "查询更换商品类型对应的商品")
    public AxiosResult getList(Goods obj) {
        startPage();
        QueryWrapper<Goods> w = new QueryWrapper<>(obj);
        w.eq("goods_type", obj.getGoodsType());
        w.orderByDesc("update_time");
        List<Goods> list = iGoodsService.list(w);
        return tabledatas(list);
    }

    @GetMapping
    public AxiosResult getList(CrmAppeal obj) {
        startPage();
        QueryWrapper<CrmAppeal> w = new QueryWrapper<>(obj);
        w.orderByDesc("create_time");
        //w.eq("record_person",LoginUser.getLoginUserName());
        List<CrmAppeal> list = iCrmAppealService.list(w);
        return tabledatas(list);
    }

    @PostMapping
    @Logs(model = "退换货管理",ops = "新增草稿")
    public AxiosResult add(@RequestBody CrmAppeal obj) {
        boolean isok = iCrmAppealService.saveDraft(obj);
        if (isok) {
            return suc();
        } else {
            return AxiosResult.error(E.ERROR);
        }
    }

    @PutMapping
    @Logs(model = "退换货管理",ops = "修改草稿")
    public AxiosResult updateById(@RequestBody CrmAppeal obj) {
        boolean isok = iCrmAppealService.updateAppealById(obj);
        return suc(isok);
    }

    @DeleteMapping("{id}")
    @Logs(model = "退换货管理",ops = "删除草稿")
    public AxiosResult delByIds(@PathVariable String id) {

        boolean isok = iCrmAppealService.removeAndUpdateDetail(id);
        if (isok) {
            return suc();
        } else {
            return AxiosResult.error(E.ERROR);
        }

    }


    @PostMapping("submitProcess")
    @Logs(model = "退换货管理",ops = "提交售后申请")
    @ApiOperation(value = "提交售后退换货申请表")
    public AxiosResult submitProcess(@RequestBody CrmAppeal obj) {
        boolean b = iCrmAppealService.submitAppealCheck(obj);
        if (b) {
            return suc();
        }
        return AxiosResult.error(E.ERROR);
    }

    @GetMapping("queryCheck/{appealId}")
    @ApiOperation(value = "查询审核记录")
    @Logs(model = "退换货管理",ops = "审核跟踪")
    public AxiosResult queryDoesTask(@PathVariable String appealId) {
        List<ProcessRecords> list = iCrmAppealService.queryCheckResult(appealId);
        CrmAppeal appeal = iCrmAppealService.getdetailInfoById(appealId);
        appeal.setRecords(list);
        return AxiosResult.suc(appeal);
    }

    @PostMapping("queryCheckList")
    @ApiOperation(value = "查询用户审核信息")
    public AxiosResult queryCheckList(@RequestBody Map map) throws InvocationTargetException, IllegalAccessException {
        CrmAppeal obj=new CrmAppeal();
        BeanUtils.populate(obj,map);
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer)map.get("pageSize");
        AxiosResult result = iCrmAppealService.queryCheckList(obj, pageNum, pageSize);
        return result;
    }


    @PostMapping("doApproval")
    @ApiOperation(value = "审核成功提交")
    @Logs(model = "退换货审核",ops = "审核通过")
    public AxiosResult updateCheck(@RequestBody CrmAppeal obj) {
        if ("0".equals(obj.getCheckStatus())){
            boolean isok = iCrmAppealService.updateCheckResult(obj,"2");
            return suc();
        }else {
            return AxiosResult.error(E.ERROR);
        }
    }

    @PostMapping("doRejectApproval")
    @ApiOperation(value = "审核不通过提交")
    @Logs(model = "退换货审核",ops = "审核不通过")
    public AxiosResult rejectCheck(@RequestBody CrmAppeal obj) {
        if ("0".equals(obj.getCheckStatus())){
            boolean isok = iCrmAppealService.updateCheckResult(obj,"1");
            return suc();
        }else {
            return AxiosResult.error(E.ERROR);
        }

    }


}
