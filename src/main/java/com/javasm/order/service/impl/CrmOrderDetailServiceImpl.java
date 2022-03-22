package com.javasm.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.javasm.crm.entity.Goods;
import com.javasm.crm.service.IGoodsService;
import com.javasm.order.entity.CrmOrderDetail;
import com.javasm.order.mapper.CrmOrderDetailMapper;
import com.javasm.order.service.ICrmOrderDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class CrmOrderDetailServiceImpl extends ServiceImpl<CrmOrderDetailMapper, CrmOrderDetail> implements ICrmOrderDetailService {
    @Resource
    private IGoodsService goodsService;

    @Override
    public List<CrmOrderDetail> getDetailAll(CrmOrderDetail obj) {
        QueryWrapper<CrmOrderDetail> w = new QueryWrapper<>(obj);
        w.eq("order_no", obj.getOrderNo());
        w.eq("temp2","0");
        w.orderByDesc("create_time");
        List<CrmOrderDetail> details = list(w);
        for (CrmOrderDetail detail : details) {
            QueryWrapper<Goods> qw=new QueryWrapper<>();
            qw.eq("goods_number",detail.getGoodsNumber());
            Goods goods = goodsService.getOne(qw);
            detail.setGoods(goods);
        }
        return details;
    }

    /**
     * 修改售后时通过此方法确定商品售后退换货状态
     * @param id
     * @return
     */
    @Override
    public boolean updateDetailById(String id) {
        CrmOrderDetail detail = getById(id);
        if ("1".equals(detail.getTemp2())){
            UpdateWrapper<CrmOrderDetail> wrapper=new UpdateWrapper<>();
            wrapper.eq("id",id);
            wrapper.set("temp2","0");
            boolean update = update(wrapper);
            return update;
        }else if ("2".equals(detail.getTemp2())){
            return true;
        }else {
            return true;
        }
    }
}
