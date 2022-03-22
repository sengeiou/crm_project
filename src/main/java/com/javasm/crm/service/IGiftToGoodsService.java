package com.javasm.crm.service;

import com.javasm.commons.entity.AxiosResult;
import com.javasm.crm.entity.GiftToGoods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javasm.sup.entity.CrmGoodsGift;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface IGiftToGoodsService extends IService<GiftToGoods> {

    AxiosResult getListByParams(String loginUserId,GiftToGoods giftToGoods, CrmGoodsGift goodsGift, Integer pageNum, Integer pageSize);

    AxiosResult queryGoodsGiftByGiftToGoodsId(String id);
}
