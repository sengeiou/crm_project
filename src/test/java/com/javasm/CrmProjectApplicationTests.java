package com.javasm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.util.StringUtil;
import com.javasm.crm.entity.Goods;
import com.javasm.crm.entity.GoodsToGift;
import com.javasm.crm.entity.Product;
import com.javasm.crm.service.IGoodsService;
import com.javasm.crm.service.IGoodsToGiftService;
import com.javasm.sup.entity.CrmGoodsGift;
import com.javasm.sup.service.ICrmGoodsGiftService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CrmProjectApplicationTests {

    @Resource
    private IGoodsToGiftService goodsToGiftService;

    @Resource
    private IGoodsService goodsService;

    @Resource
    private ICrmGoodsGiftService goodsGiftService;


    @Test
    void contextLoads() {
        List<GoodsToGift> goodsToGiftList = new ArrayList<>();

        Goods goods = new Goods();
       /* goods.setGoodsName("测试商品1");
        goods.setGoodsType("P50");*/
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.hasLength(goods.getGoodsName())){
            queryWrapper.eq("goods_name",goods.getGoodsName());
        }
        if (StringUtils.hasLength(goods.getGoodsType())){
            queryWrapper.eq("goods_type",goods.getGoodsType());
        }
        List<Goods> list = goodsService.list(queryWrapper);
        if (list.size()==0){
            System.out.println("没有此商品");
            return;
        }
        System.out.println(list);
        List<String> list1 = new ArrayList<>();
        for (Goods goods1 : list) {
            String id = goods1.getId();
            list1.add(id);
        }

        QueryWrapper queryWrapper1 = new QueryWrapper();
        CrmGoodsGift goodsGift = new CrmGoodsGift();
        /*goodsGift.setGiftStatus("有效");*/
        if (StringUtils.hasLength(goodsGift.getGiftStatus())){
            queryWrapper1.eq("gift_status",goodsGift.getGiftStatus());
        }
        List<CrmGoodsGift> list2 = goodsGiftService.list(queryWrapper1);
        System.out.println(list2);
        if (list2.size()==0){
            System.out.println("没有此赠品");
            return;
        }
        List<String> list3 = new ArrayList<>();
        for (CrmGoodsGift goodsGift1 : list2) {
            String id = goodsGift1.getId();
            list3.add(id);
        }


        QueryWrapper queryWrapper2 = new QueryWrapper();
        GoodsToGift goodsToGift = new GoodsToGift();
        if (list1.size()!=0){
            queryWrapper2.in("goods_id",list1);
        }
        if (list3.size()!=0){
            queryWrapper2.in("gift_id",list3);
        }
        if (StringUtils.hasLength(goodsToGift.getCheckStatus())){
            queryWrapper2.eq("check_status",goodsToGift.getCheckStatus());
        }
        goodsToGiftList = goodsToGiftService.list(queryWrapper2);
        System.out.println("最终结果："+goodsToGiftList);

    }

}
