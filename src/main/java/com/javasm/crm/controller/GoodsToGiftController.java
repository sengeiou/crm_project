package com.javasm.crm.controller;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.javasm.commons.dictEnum.DictEnum;
import com.javasm.commons.entity.E;
import com.javasm.commons.entity.TableDatas;
import com.javasm.commons.utils.LoginUser;
import com.javasm.commons.utils.ServletUtil;
import com.javasm.crm.entity.Goods;
import com.javasm.crm.service.IGoodsService;
import com.javasm.sup.entity.CrmGoodsGift;
import com.javasm.sup.service.ICrmGoodsGiftService;
import com.javasm.sys.entity.CrmUser;
import com.javasm.sys.entity.ProcessRecords;
import com.javasm.sys.service.ICrmUserService;
import com.javasm.sys.service.IProcessRecordsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import com.javasm.crm.entity.GoodsToGift;
import com.javasm.crm.service.IGoodsToGiftService;
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
@RequestMapping("/crm/goods-to-gift")
@Api(tags = "商品转赠品接口", description = "商品转赠品接口")
public class GoodsToGiftController extends BaseController {
    @Resource
    private IGoodsToGiftService iGoodsToGiftService;

    @Resource
    private ICrmUserService userService;

    @Resource
    private ICrmGoodsGiftService goodsGiftService;

    @Resource
    private IGoodsService goodsService;

    //流程记录
    @Resource
    private IProcessRecordsService processRecordsService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @GetMapping("queryDoesTask/{goodsToGiftId}")
    @ApiOperation(value = "查询审核记录")
    public AxiosResult queryDoesTask(@PathVariable String goodsToGiftId){
        List<ProcessRecords> list = processRecordsService.queryDoesTask(DictEnum.GOODS_TO_GIFT_CHECK_NAME.getDictName(), goodsToGiftId);
            for (ProcessRecords processRecords : list) {
                String approvalUesrId = processRecords.getApprovalUesrId();
                if (StringUtils.hasLength(approvalUesrId)){
                    CrmUser byId = userService.getById(approvalUesrId);
                    processRecords.setApprovalUesrId(byId.getUserName());
                }
            }
        return AxiosResult.suc(list);
    }

    @PostMapping("doApprove")
    @ApiOperation(value = "审核人执行审核")
    public AxiosResult doApprove(@RequestBody ProcessRecords processRecords){
        String userId = LoginUser.getLoginUser().getId();
        processRecords.setProcessName(DictEnum.GOODS_TO_GIFT_CHECK_NAME.getDictName());
        AxiosResult axiosResult = iGoodsToGiftService.doApprove(userId, processRecords);
        return axiosResult;
    }


    @PostMapping("queryToDoTask")
    @ApiOperation(value = "根据条件查询某人商品转赠品待审核,审核未通过,审核通过记录")
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
        processRecords.setProcessName(DictEnum.GOODS_TO_GIFT_CHECK_NAME.getDictName());
        AxiosResult axiosResult = iGoodsToGiftService.queryToDoTaskByParams(goodsGift, pageNum, pageSize, processRecords, loginUserId);
        return axiosResult;
    }


    @Transactional
    @GetMapping("submitProcess/{goodsToGiftId}")
    @ApiOperation(value = "提交商品转赠品申请表")
    public AxiosResult submitProcess(@PathVariable String goodsToGiftId){
        //获取当前登录人 也就是申请人
        CrmUser loginUser = LoginUser.getLoginUser();
        String applyUserId = loginUser.getId();
        String id = processRecordsService.submitProess(DictEnum.GOODS_TO_GIFT_CHECK_NAME.getDictName(), goodsToGiftId, applyUserId);
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("id",goodsToGiftId);
        updateWrapper.set("check_status",0);
        boolean update = iGoodsToGiftService.update(updateWrapper);
        if (StringUtils.hasLength(id)&&update){
            return AxiosResult.suc(id);
        }
        return AxiosResult.error(E.ERROR);
    }




    @GetMapping("{id}")
    @ApiOperation(value = "根据id查询")
    public AxiosResult getById(@PathVariable String id) {
        GoodsToGift u = iGoodsToGiftService.getById(id);
        return suc(u);
    }

    @GetMapping("queryGiftByGoodsToGiftId/{id}")
    @ApiOperation(value = "根据商品转赠品id获取赠品信息")
    public ResponseEntity getGiftById(@PathVariable String id) {
        AxiosResult axiosResult = iGoodsToGiftService.getGiftById(id);
        return ResponseEntity.ok().body(axiosResult);
    }


    @PostMapping("query")
    @ApiOperation(value = "根据条件查询并分页")
    public ResponseEntity getListByQueryParms(@RequestBody Map map) throws InvocationTargetException, IllegalAccessException {
        //登录人
        String loginUserId = LoginUser.getLoginUser().getId();
        GoodsToGift goodsToGift = new GoodsToGift();
        CrmGoodsGift goodsGift = new CrmGoodsGift();

        BeanUtils.populate(goodsGift, map);
        BeanUtils.populate(goodsToGift, map);

        Integer pageNum = (Integer) map.get("pageNum");
        Integer pageSize = (Integer) map.get("pageSize");

        AxiosResult axiosResult = iGoodsToGiftService.queryGoodsToGiftByParams(loginUserId,goodsGift, goodsToGift, pageSize, pageNum);
        return ResponseEntity.ok().body(axiosResult);
    }

    @PostMapping("updateGoodsToGiftNumber")
    @ApiOperation(value = "根据商品转赠品id修改商品转赠品的数量")
    public ResponseEntity updateGoodsToGiftNumber(@RequestBody GoodsToGift goodsToGift) {
        String id = goodsToGift.getId();
        String goodsToGiftNumber = goodsToGift.getGoodsToGiftNumber();
        AxiosResult axiosResult = iGoodsToGiftService.updateGoodsToGiftNumberById(id, goodsToGiftNumber);
        return ResponseEntity.ok().body(axiosResult);
    }


    @GetMapping
    @ApiOperation(value = "根据商品转赠品对象获取")
    public AxiosResult getList(GoodsToGift obj) {
        startPage();
        QueryWrapper<GoodsToGift> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<GoodsToGift> list = iGoodsToGiftService.list(w);
        return tabledatas(list);
    }

    @PostMapping
    @ApiOperation(value = "新增")
    public ResponseEntity add(@RequestBody GoodsToGift goodsToGift) {
        if (!StringUtils.hasLength(goodsToGift.getGoodsToGiftNumber()) || !StringUtils.hasLength(goodsToGift.getgiftId()) ||
        !StringUtils.hasLength(goodsToGift.getGoodsId())){
            return ResponseEntity.ok().body(AxiosResult.error(E.ERROR));
        }
        //判断转的数量是否大于商品库存
        CrmGoodsGift byId = goodsGiftService.getById(goodsToGift.getgiftId());
        Goods byId1 = goodsService.getById(byId.getGoodsId());
        if (Integer.valueOf(byId1.getGoodsInventory())<Integer.valueOf(goodsToGift.getGoodsToGiftNumber())){
            return ResponseEntity.ok().body(AxiosResult.error(E.ERROR));
        }
        //存
        boolean isok = iGoodsToGiftService.save(goodsToGift);
        //新增申请表记录的id
        String id = goodsToGift.getId();
        if (!isok){
            return ResponseEntity.ok().body(AxiosResult.error(E.ERROR));
        }
        return ResponseEntity.ok().body(AxiosResult.suc(id));
    }

    @PutMapping
    @ApiOperation(value = "根据id修改")
    public AxiosResult updateById(@RequestBody GoodsToGift obj) {
        boolean isok = iGoodsToGiftService.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    @ApiOperation(value = "根据ids批量删除")
    public AxiosResult delByIds(@PathVariable String ids) {
        String[] strs = ids.split(",");
        boolean isok = iGoodsToGiftService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }


    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "根据id删除")
    public ResponseEntity delById(@PathVariable String id) {
        AxiosResult axiosResult = iGoodsToGiftService.deleteById(id);
        return ResponseEntity.ok().body(axiosResult);
    }
}
