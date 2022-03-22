package com.javasm.sup.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.javasm.commons.entity.AxiosResult;
import com.javasm.crm.entity.Goods;
import com.javasm.crm.mapper.GoodsMapper;
import com.javasm.sup.entity.CrmGoodsGift;
import com.javasm.sup.mapper.CrmGoodsGiftMapper;
import com.javasm.sup.service.ICrmGoodsGiftService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@Service
public class CrmGoodsGiftServiceImpl extends ServiceImpl<CrmGoodsGiftMapper, CrmGoodsGift> implements ICrmGoodsGiftService {

    @Resource
    private CrmGoodsGiftMapper goodsGiftMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public List<CrmGoodsGift> getGiftByParams(CrmGoodsGift goodsGift,Integer pageNum,Integer pageSize) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.hasLength(goodsGift.getId())) {
            queryWrapper.eq("id", goodsGift.getId());
        }
        if (StringUtils.hasLength(goodsGift.getGiftName())) {
            queryWrapper.like("gift_name", goodsGift.getGiftName());
        }
        if (StringUtils.hasLength(goodsGift.getGiftType())) {
            queryWrapper.like("gift_type", goodsGift.getGiftType());
        }
        if (StringUtils.hasLength(goodsGift.getGiftColor())) {
            queryWrapper.eq("gift_color", goodsGift.getGiftColor());
        }
        if (StringUtils.hasLength(goodsGift.getGiftBrand())) {
            queryWrapper.eq("gift_brand", goodsGift.getGiftBrand());
        }
        if (StringUtils.hasLength(goodsGift.getGiftClassify())) {
            queryWrapper.eq("gift_classify", goodsGift.getGiftClassify());
        }
        queryWrapper.orderByDesc("update_time");
        PageHelper.startPage(Optional.ofNullable(pageNum).orElse(1), Optional.ofNullable(pageSize).orElse(5));
        List<CrmGoodsGift> list = goodsGiftMapper.selectList(queryWrapper);
        for (CrmGoodsGift crmGoodsGift : list) {
            String goodsId = crmGoodsGift.getGoodsId();
            Goods goods = goodsMapper.selectById(goodsId);
            crmGoodsGift.setGoods(goods);
        }
        return list;
    }
}
