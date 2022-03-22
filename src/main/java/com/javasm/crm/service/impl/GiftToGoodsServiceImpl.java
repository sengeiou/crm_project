package com.javasm.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.javasm.commons.dictEnum.DictEnum;
import com.javasm.commons.entity.AxiosResult;
import com.javasm.commons.entity.E;
import com.javasm.commons.entity.TableDatas;
import com.javasm.crm.entity.GiftToGoods;
import com.javasm.crm.entity.GoodsToGift;
import com.javasm.crm.mapper.GiftToGoodsMapper;
import com.javasm.crm.mapper.GoodsToGiftMapper;
import com.javasm.crm.service.IGiftToGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javasm.sup.entity.CrmGoodsGift;
import com.javasm.sup.mapper.CrmGoodsGiftMapper;
import com.javasm.sys.entity.ProcessRecords;
import com.javasm.sys.mapper.ProcessRecordsMapper;
import com.javasm.sys.service.IProcessRecordsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@Service
public class GiftToGoodsServiceImpl extends ServiceImpl<GiftToGoodsMapper, GiftToGoods> implements IGiftToGoodsService {

    @Resource
    private CrmGoodsGiftMapper goodsGiftMapper;

    @Resource
    private GiftToGoodsMapper giftToGoodsMapper;

    @Resource
    private ProcessRecordsMapper processRecordsMapper;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public AxiosResult getListByParams(String loginUserId,GiftToGoods giftToGoods, CrmGoodsGift goodsGift, Integer pageNum, Integer pageSize) {
        log.info("这个方法的service入参:{},{},{},{}",giftToGoods,goodsGift,pageNum,pageSize);
        //在审核记录表查询登录人申请的记录的申请表id
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("application_id");
        queryWrapper.eq("process_name", DictEnum.GIFT_TO_GOODS_CHECK_NAME.getDictName());
        queryWrapper.eq("application_user_id", loginUserId);
        //查询所有审核记录中申请人是该登录人的信息
        List<ProcessRecords> list = processRecordsMapper.selectList(queryWrapper);
        //查询查到的审核记录的申请单id存入list1
        ArrayList<String> list1 = new ArrayList<>();
        for (ProcessRecords processRecords : list) {
            list1.add(processRecords.getApplicationId());
        }
        //查询符合条件的赠品
        QueryWrapper queryWrapper2 = new QueryWrapper();
        if (StringUtils.hasLength(goodsGift.getGiftName())){
            queryWrapper2.like("gift_name",goodsGift.getGiftName());
        }
        if (StringUtils.hasLength(goodsGift.getGiftClassify())){
            queryWrapper2.eq("gift_classify",goodsGift.getGiftClassify());
        }
        if (StringUtils.hasLength(goodsGift.getGiftColor())){
            queryWrapper2.eq("gift_color",goodsGift.getGiftColor());
        }
        if (StringUtils.hasLength(goodsGift.getGiftBrand())){
            queryWrapper2.eq("gift_brand",goodsGift.getGiftBrand());
        }
        List<CrmGoodsGift> list2 = goodsGiftMapper.selectList(queryWrapper2);
        if (list2.size()==0){
            return new AxiosResult(E.SUC,new TableDatas(new ArrayList(),pageNum,pageSize,0));
        }
        List<String> list3 = new ArrayList<>();
        for (CrmGoodsGift crmGoodsGift : list2) {
            list3.add(crmGoodsGift.getId());
        }

        QueryWrapper<GiftToGoods> queryWrapper1 = new QueryWrapper<GiftToGoods>();
        queryWrapper1.orderByDesc("update_time");
        queryWrapper1.in("gift_id",list3);


        if (StringUtils.hasLength(giftToGoods.getCheckStatus()) && "3".equals(giftToGoods.getCheckStatus())) {
            queryWrapper1.eq("check_status", giftToGoods.getCheckStatus());
        } else {
            //如查询审核状态是空或者是null 如果查询审核状态不是草稿  拼接或者条件  审核条件为草稿
            if (!StringUtils.hasLength(giftToGoods.getCheckStatus())) {
                if (list1.size()==0){
                    list1.add("1");
                }
                queryWrapper1.and(wrapper -> wrapper.in("id", list1).or().eq("check_status", 3));
            } else if (!"3".equals(giftToGoods.getCheckStatus())) {
                queryWrapper1.in("id", list1);
                queryWrapper1.eq("check_status", giftToGoods.getCheckStatus());
            }
        }
        PageHelper.startPage(Optional.ofNullable(pageNum).orElse(1),Optional.ofNullable(pageSize).orElse(3));
        List<GiftToGoods> list4 = giftToGoodsMapper.selectList(queryWrapper1);
        for (GiftToGoods giftToGoods1 : list4) {
            String s = giftToGoods1.getGiftId();
            CrmGoodsGift goodsGift1 = goodsGiftMapper.selectById(s);
            giftToGoods1.setGoodsGift(goodsGift1);
        }
        log.info("最终结果:{}",list4);
        return AxiosResult.suc(new TableDatas(list4));
    }

    @Override
    public AxiosResult queryGoodsGiftByGiftToGoodsId(String id) {
        GiftToGoods giftToGoods = giftToGoodsMapper.selectById(id);
        String giftId = giftToGoods.getGiftId();
        CrmGoodsGift goodsGift = goodsGiftMapper.selectById(giftId);
        giftToGoods.setGoodsGift(goodsGift);
        return AxiosResult.suc(giftToGoods);
    }
}
