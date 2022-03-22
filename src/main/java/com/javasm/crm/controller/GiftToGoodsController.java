package com.javasm.crm.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.javasm.commons.dictEnum.DictEnum;
import com.javasm.commons.entity.E;
import com.javasm.commons.utils.LoginUser;
import com.javasm.crm.service.IGoodsToGiftService;
import com.javasm.sup.entity.CrmGoodsGift;
import com.javasm.sup.service.ICrmGoodsGiftService;
import com.javasm.sys.entity.CrmUser;
import com.javasm.sys.entity.ProcessRecords;
import com.javasm.sys.mapper.ProcessRecordsMapper;
import com.javasm.sys.service.ICrmUserService;
import com.javasm.sys.service.IProcessRecordsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.ResponseEntity;
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

import com.javasm.crm.entity.GiftToGoods;
import com.javasm.crm.service.IGiftToGoodsService;
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
@RequestMapping("/crm/gift-to-goods")
@Api(tags = "赠品转商品接口", description = "赠品转商品接口")
public class GiftToGoodsController extends BaseController {
    @Autowired
    private IGiftToGoodsService iGiftToGoodsService;

    @Resource
    private ICrmGoodsGiftService goodsGiftService;

    @Resource
    private IProcessRecordsService processRecordsService;

    @Resource
    private ICrmUserService userService;

    @Resource
    private IGoodsToGiftService goodsToGiftService;

    @GetMapping("{id}")
    @ApiOperation(value = "根据主键id查询")
    public AxiosResult getById(@PathVariable String id) {
        GiftToGoods u = iGiftToGoodsService.getById(id);
        return suc(u);
    }

    @GetMapping
    @ApiOperation(value = "根据对象查询")
    public AxiosResult getList(GiftToGoods obj) {
        startPage();
        QueryWrapper<GiftToGoods> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<GiftToGoods> list = iGiftToGoodsService.list(w);
        return tabledatas(list);
    }

    @PostMapping("query")
    @ApiOperation(value = "根据赠品的字段,审核状态查询 ")
    public ResponseEntity getListByParams(@RequestBody Map map) throws InvocationTargetException, IllegalAccessException {
        String loginUserId = LoginUser.getLoginUser().getId();
        GiftToGoods giftToGoods = new GiftToGoods();
        CrmGoodsGift goodsGift = new CrmGoodsGift();
        BeanUtils.populate(giftToGoods, map);
        BeanUtils.populate(goodsGift, map);
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");
        AxiosResult axiosResult = iGiftToGoodsService.getListByParams(loginUserId,giftToGoods, goodsGift, pageNum, pageSize);
        return ResponseEntity.ok().body(axiosResult);
    }

    @GetMapping("queryGoodsGiftByGiftToGoodsId/{id}")
    @ApiOperation(value = "根据赠品转商品id获取赠品信息")
    public ResponseEntity queryGoodsGiftByGiftToGoodsId(@PathVariable String id) {
        AxiosResult axiosResult = iGiftToGoodsService.queryGoodsGiftByGiftToGoodsId(id);
        return ResponseEntity.ok().body(axiosResult);
    }

    @GetMapping("queryDoesTask/{giftToGoodsId}")
    @ApiOperation(value = "查询审核记录")
    public AxiosResult queryDoesTask(@PathVariable String giftToGoodsId){
        List<ProcessRecords> list = processRecordsService.queryDoesTask(DictEnum.GIFT_TO_GOODS_CHECK_NAME.getDictName(), giftToGoodsId);
        for (ProcessRecords processRecords : list) {
            String approvalUesrId = processRecords.getApprovalUesrId();
            if (StringUtils.hasLength(approvalUesrId)){
                CrmUser byId = userService.getById(approvalUesrId);
                processRecords.setApprovalUesrId(byId.getUserName());
            }
        }
        return AxiosResult.suc(list);
    }

    @PostMapping("queryToDoTask")
    @ApiOperation(value = "根据条件查询某人赠品转商品待审核,审核未通过,审核通过记录")
    public AxiosResult queryToDoTask(@RequestBody Map map) throws InvocationTargetException, IllegalAccessException {
        String loginUserId = LoginUser.getLoginUser().getId();
        //解析map
        CrmGoodsGift goodsGift = new CrmGoodsGift();
        BeanUtils.populate(goodsGift,map);
        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer)map.get("pageSize");
        String approvalResult = (String) map.get("approvalResult");
        ProcessRecords processRecords = new ProcessRecords();
        processRecords.setApprovalResult(approvalResult);
        processRecords.setProcessName(DictEnum.GIFT_TO_GOODS_CHECK_NAME.getDictName());
        AxiosResult axiosResult = goodsToGiftService.queryToDoTaskByParams(goodsGift, pageNum, pageSize, processRecords, loginUserId);
        return axiosResult;

    }


    @PostMapping("updateGiftToGoodsNumber")
    @ApiOperation(value = "根据id修改赠品转商品数量")
    public AxiosResult updateGiftToGoodsNumber(@RequestBody GiftToGoods giftToGoods) {
        String id = giftToGoods.getId();
        GiftToGoods byId = iGiftToGoodsService.getById(id);
        String giftId = byId.getGiftId();
        CrmGoodsGift byId1 = goodsGiftService.getById(giftId);
        if (Integer.valueOf(giftToGoods.getGiftToGoodsNumber()) > Integer.valueOf(byId1.getGiftInventory())) {
            return AxiosResult.error(E.ERROR);
        }
        boolean b = iGiftToGoodsService.updateById(giftToGoods);
        if (!b) {
            return AxiosResult.error(E.ERROR);
        }
        return AxiosResult.suc();
    }

    @PostMapping("doApprove")
    @ApiOperation(value = "审核人执行审核")
    public AxiosResult doApprove(@RequestBody ProcessRecords processRecords){
        String userId = LoginUser.getLoginUser().getId();
        processRecords.setProcessName(DictEnum.GIFT_TO_GOODS_CHECK_NAME.getDictName());
        AxiosResult axiosResult = goodsToGiftService.doApprove(userId, processRecords);
        return axiosResult;
    }

    @PostMapping
    @ApiOperation(value = "添加到申请表中")
    public ResponseEntity add(@RequestBody GiftToGoods giftToGoods) {
        if (!StringUtils.hasLength(giftToGoods.getGiftToGoodsNumber()) || !StringUtils.hasLength(giftToGoods.getGiftId())) {
            return ResponseEntity.ok().body(AxiosResult.error(E.ERROR));
        }
        //判断转的数量是否大于赠品库存
        CrmGoodsGift byId = goodsGiftService.getById(giftToGoods.getGiftId());
        if (Integer.valueOf(byId.getGiftInventory()) < Integer.valueOf(giftToGoods.getGiftToGoodsNumber())) {
            return ResponseEntity.ok().body(AxiosResult.error(E.ERROR));
        }
        //存
        boolean isok = iGiftToGoodsService.save(giftToGoods);
        //新增申请表记录的id
        String id = giftToGoods.getId();
        if (!isok) {
            return ResponseEntity.ok().body(AxiosResult.error(E.ERROR));
        }
        return ResponseEntity.ok().body(AxiosResult.suc(id));
    }

    @Transactional
    @GetMapping("submitProcess/{giftToGoodsId}")
    @ApiOperation(value = "提交赠品转商品申请表")
    public AxiosResult submitProcess(@PathVariable String giftToGoodsId) {
        CrmUser loginUser = LoginUser.getLoginUser();
        String applyUserId = loginUser.getId();
        String id = processRecordsService.submitProess(DictEnum.GIFT_TO_GOODS_CHECK_NAME.getDictName(), giftToGoodsId, applyUserId);
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",giftToGoodsId);
        updateWrapper.set("check_status",0);
        boolean update = iGiftToGoodsService.update(updateWrapper);
        if (StringUtils.hasLength(id)&&update){
            return AxiosResult.suc(id);
        }
        return AxiosResult.error(E.ERROR);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public AxiosResult updateById(@RequestBody GiftToGoods obj) {
        boolean isok = iGiftToGoodsService.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    @ApiOperation(value = "批量删除")
    public AxiosResult delByIds(@PathVariable String ids) {
        String[] strs = ids.split(",");
        boolean isok = iGiftToGoodsService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }
}
