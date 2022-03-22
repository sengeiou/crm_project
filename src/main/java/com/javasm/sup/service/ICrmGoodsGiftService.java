package com.javasm.sup.service;

import com.javasm.commons.entity.AxiosResult;
import com.javasm.sup.entity.CrmGoodsGift;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface ICrmGoodsGiftService extends IService<CrmGoodsGift> {


    List<CrmGoodsGift> getGiftByParams(CrmGoodsGift goodsGift,Integer pageNum,Integer pageSize);

}
