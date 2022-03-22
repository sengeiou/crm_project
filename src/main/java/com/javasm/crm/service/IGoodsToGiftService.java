package com.javasm.crm.service;

import com.github.pagehelper.PageInfo;
import com.javasm.commons.entity.AxiosResult;
import com.javasm.crm.entity.Goods;
import com.javasm.crm.entity.GoodsToGift;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javasm.sup.entity.CrmGoodsGift;
import com.javasm.sys.entity.ProcessRecords;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface IGoodsToGiftService extends IService<GoodsToGift> {

    AxiosResult queryGoodsToGiftByParams(String loginUserId,CrmGoodsGift goodsGift, GoodsToGift goodsToGift, Integer pageSize, Integer pageNum);


    AxiosResult updateGoodsToGiftNumberById(String goodsToGiftId,String goodsToGiftInventory);


    AxiosResult deleteById(String id);

    AxiosResult getGiftById(String id);

    AxiosResult queryToDoTaskByParams(CrmGoodsGift goodsGift, Integer pageNum, Integer pageSize, ProcessRecords processRecords, String loginUserId);

    AxiosResult doApprove(String loginUserId,ProcessRecords processRecords);
}
