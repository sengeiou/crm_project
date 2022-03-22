package com.javasm.order.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.javasm.commons.utils.DocxUtils;
import com.javasm.crm.entity.Goods;
import com.javasm.crm.service.IGoodsService;
import com.javasm.order.entity.CrmOrderDetail;
import com.javasm.order.entity.CrmOrderInfo;
import com.javasm.order.mapper.CrmOrderInfoMapper;
import com.javasm.order.service.ICrmOrderDetailService;
import com.javasm.order.service.ICrmOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@Service
public class CrmOrderInfoServiceImpl extends ServiceImpl<CrmOrderInfoMapper, CrmOrderInfo> implements ICrmOrderInfoService {
    @Resource
    private ICrmOrderDetailService iCrmOrderDetailService;
    @Resource
    private IGoodsService iGoodsService;

    @Override
    public List<String> getAllOrderNo() {
        QueryWrapper w = new QueryWrapper();
        w.select("order_no");
        List<String> allOrderNo = listObjs(w);
        return allOrderNo;
    }

    @Override
    public CrmOrderInfo getDetailInfoById(String id) {
        CrmOrderInfo info = this.getById(id);
        QueryWrapper<CrmOrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",info.getOrderNo());
        List<CrmOrderDetail> details = iCrmOrderDetailService.list(wrapper);
        for (CrmOrderDetail detail : details) {
            QueryWrapper<Goods> w=new QueryWrapper<>();
            w.eq("goods_number",detail.getGoodsNumber());
            Goods goods = iGoodsService.getOne(w);
            detail.setGoods(goods);
        }
        info.setDetails(details);
        return info;
    }

    @Override
    @Transactional
    public CrmOrderInfo ExportDocxById(String id) {
        CrmOrderInfo info = this.getById(id);
        QueryWrapper<CrmOrderDetail> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",info.getOrderNo());
        List<CrmOrderDetail> details = iCrmOrderDetailService.list(wrapper);
        for (CrmOrderDetail detail : details) {
            QueryWrapper<Goods> w=new QueryWrapper<>();
            w.eq("goods_number",detail.getGoodsNumber());
            Goods goods = iGoodsService.getOne(w);
            detail.setGoods(goods);
            String prodCode = IdUtil.getSnowflakeNextIdStr();
            detail.setProdCode(prodCode);
            UpdateWrapper<CrmOrderDetail> uw=new UpdateWrapper<>();
            uw.eq("id",detail.getId());
            iCrmOrderDetailService.update(detail,uw);
        }
        info.setDetails(details);
        return info;
    }

}


