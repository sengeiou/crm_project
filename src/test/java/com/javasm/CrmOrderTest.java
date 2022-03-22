package com.javasm;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.javasm.commons.utils.DocxUtils;
import com.javasm.crm.entity.Goods;
import com.javasm.order.entity.CrmAppeal;
import com.javasm.order.entity.CrmOrderDetail;
import com.javasm.order.entity.CrmOrderInfo;
import com.javasm.order.service.ICrmAppealService;
import com.javasm.order.service.ICrmOrderInfoService;
import com.javasm.sys.entity.CrmDictItem;
import com.javasm.sys.entity.ProcessRecords;
import com.javasm.sys.service.ICrmDictItemService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author:
 * @className:CrmOrderTest
 * @description:
 * @date:2022/2/19 15:56
 * @version:0.1
 * @since:1.8
 */
@SpringBootTest
public class CrmOrderTest {
    @Resource
    private ICrmOrderInfoService iCo;
    @Resource
    private ICrmDictItemService dictItemService;
    @Resource
    private ICrmAppealService appealService;

    @Test
    void testGet(){
        List<String> orderNo = iCo.getAllOrderNo();
        System.out.println(orderNo);
    }
    @Test
    void testGet1(){
        CrmOrderInfo info = iCo.getDetailInfoById("1494945691426902017");
        System.out.println(info);
    }

    @Test
    void testWord() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        Goods goods = new Goods();
        goods.setGoodsName("HS111");
        goods.setGoodsNumber("114514");
        goods.setGoodsMarketPrice(new BigDecimal("666.5"));
        CrmOrderDetail detail = new CrmOrderDetail();
        detail.setGoods(goods);
        detail.setRemarks("caster");
        detail.setProdCode(IdUtil.getSnowflakeNextIdStr());
        detail.setProdNum("5");
        List<CrmOrderDetail> list=new ArrayList<>();
        list.add(detail);
        map.put("details",list);
        map.put("name","张三");
        map.put("outTime",DateUtil.now());
        map.put("documentNo",IdUtil.getSnowflakeNextIdStr());
        map.put("maker","admin");
        OutputStream writer = new FileOutputStream("D:/出库单.docx");
        DocxUtils.createDocx(map,"出库单.xml",writer);
    }

    @Test
    void testDetail(){
        Goods goods = new Goods();

        goods.setGoodsMarketPrice(new BigDecimal("666.5"));
        CrmOrderDetail detail = new CrmOrderDetail();
        detail.setGoods(goods);
        detail.setRemarks("caster");
        detail.setProdCode(IdUtil.getSnowflakeNextIdStr());
        detail.setProdNum("5");
        Double total=0D;

        total+= Double.valueOf(detail.getTemp1());
        System.out.println(detail.getTemp1());
        System.out.println(total.toString());
    }

    @Test
    void testRedis(){
        List<CrmDictItem> items = dictItemService.queryByCodeAndName("commons_check_status", "审核状态");
        for (CrmDictItem item : items) {
            System.out.println(item);
        }
    }

    @Test
    void testTime(){
        CrmAppeal appeal=new CrmAppeal();
        //appeal.setFirstRespTime("2022-02-24T09:16:39");
        appealService.saveDraft(appeal);

    }

    @Test
    void testSave(){
        CrmAppeal obj=new CrmAppeal();
        obj.setRecordNo("11233");
        obj.setId("1497113931091595266");
        boolean save = appealService.saveOrUpdate(obj);
        System.out.println(obj.getId());

    }


    @Test
    void testSet(){
        Set<String> s=new HashSet<>();
        s.add("1");
        s.add("2");
        s.add("1");
        s.add("3");
        System.out.println(s);
    }
}
