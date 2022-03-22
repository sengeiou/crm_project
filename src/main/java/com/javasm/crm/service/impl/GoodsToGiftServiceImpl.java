package com.javasm.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.javasm.commons.dictEnum.DictEnum;
import com.javasm.commons.entity.AxiosResult;
import com.javasm.commons.entity.E;
import com.javasm.commons.entity.TableDatas;
import com.javasm.crm.entity.GiftToGoods;
import com.javasm.crm.entity.Goods;
import com.javasm.crm.entity.GoodsToGift;
import com.javasm.crm.mapper.GiftToGoodsMapper;
import com.javasm.crm.mapper.GoodsToGiftMapper;
import com.javasm.crm.service.IGiftToGoodsService;
import com.javasm.crm.service.IGoodsService;
import com.javasm.crm.service.IGoodsToGiftService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javasm.sup.entity.CrmGoodsGift;
import com.javasm.sup.service.ICrmGoodsGiftService;
import com.javasm.sys.entity.ProcessRecords;
import com.javasm.sys.mapper.ProcessRecordsMapper;
import com.javasm.sys.service.IProcessRecordsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class GoodsToGiftServiceImpl extends ServiceImpl<GoodsToGiftMapper, GoodsToGift> implements IGoodsToGiftService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private IGoodsService goodsService;

    @Resource
    private ICrmGoodsGiftService goodsGiftService;

    @Resource
    private GoodsToGiftMapper goodsToGiftMapper;

    @Resource
    private IGoodsToGiftService goodsToGiftService;

    @Resource
    private ProcessRecordsMapper processRecordsMapper;

    @Resource
    private IProcessRecordsService processRecordsService;

    @Resource
    private GiftToGoodsMapper giftToGoodsMapper;

    @Resource
    private IGiftToGoodsService giftToGoodsService;

    @Transactional
    @Override
    public AxiosResult queryGoodsToGiftByParams(String loginUserId, CrmGoodsGift goodsGift, GoodsToGift goodsToGift, Integer pageSize, Integer pageNum) {
        List<GoodsToGift> goodsToGiftList = new ArrayList<>();
        //在审核记录表查询登录人申请的记录的申请表id
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("application_id");
        queryWrapper.eq("process_name", DictEnum.GOODS_TO_GIFT_CHECK_NAME.getDictName());
        queryWrapper.eq("application_user_id", loginUserId);
        //查询所有审核记录中申请人是该登录人的信息
        List<ProcessRecords> list = processRecordsMapper.selectList(queryWrapper);
        //查询查到的审核记录的申请单id存入list1
        ArrayList<String> list1 = new ArrayList<>();
        for (ProcessRecords processRecords : list) {
            list1.add(processRecords.getApplicationId());
        }

        List<String> list3 = new ArrayList<>();
        //根据赠品状态查询
        QueryWrapper queryWrapper1 = new QueryWrapper();
        //如果商品状态不为空 拼接商品状态条件
        if (StringUtils.hasLength(goodsGift.getGiftStatus())) {
            queryWrapper1.eq("gift_status", goodsGift.getGiftStatus());
        }
        //如果商品名称不为空 拼接商品名称条件
        if (StringUtils.hasLength(goodsGift.getGiftName())) {
            queryWrapper1.like("gift_name", goodsGift.getGiftName());
        }
        //如果商品类型不为空 拼接商品类型条件
        if (StringUtils.hasLength(goodsGift.getGiftClassify())) {
            queryWrapper1.eq("gift_classify", goodsGift.getGiftClassify());
        }
        //查询符合以上条件的商品存入list2
        List<CrmGoodsGift> list2 = goodsGiftService.list(queryWrapper1);
        if (list2.size() == 0) {
            log.info("没有此商品:{}", list2);
            return new AxiosResult(E.ERROR, new TableDatas(list2, pageNum, pageSize, 0));
        }
        if (list2.size() != 0) {
            //获取商品id存入list3中
            for (CrmGoodsGift goodsGift1 : list2) {
                String id = goodsGift1.getId();
                list3.add(id);
            }
        }

        QueryWrapper<GoodsToGift> queryWrapper2 = new QueryWrapper<GoodsToGift>();
        //按修改时间排序
        queryWrapper2.orderByDesc("update_time");
        //如果商品id集合不是0 拼接 赠品id 符合查询到的id集合
        if (list3.size() != 0) {
            queryWrapper2.in("gift_id", list3);
        }
        //查询条件是草稿
        if (StringUtils.hasLength(goodsToGift.getCheckStatus()) && "3".equals(goodsToGift.getCheckStatus())) {
            queryWrapper2.eq("check_status", goodsToGift.getCheckStatus());
        } else {
            //如查询审核状态是空或者是null 如果查询审核状态不是草稿  拼接或者条件  审核条件为草稿
            if (!StringUtils.hasLength(goodsToGift.getCheckStatus())) {
                if (list1.size()==0){
                    list1.add("1");
                }
                queryWrapper2.and(wrapper -> wrapper.in("id", list1).or().eq("check_status", 3));
            } else if (!"3".equals(goodsToGift.getCheckStatus())) {
                queryWrapper2.in("id", list1);
                queryWrapper2.eq("check_status", goodsToGift.getCheckStatus());
            }
        }

        //启动分页
        PageHelper.startPage(Optional.ofNullable(pageNum).orElse(1), Optional.ofNullable(pageSize).orElse(3));
        //查询所有符合条件的赠品转商品集合
        goodsToGiftList = goodsToGiftMapper.selectList(queryWrapper2);
        for (GoodsToGift toGift : goodsToGiftList) {
            String getgiftId = toGift.getgiftId();
            CrmGoodsGift byId2 = goodsGiftService.getById(getgiftId);
            toGift.setGoodsGift(byId2);
            String goodsId1 = byId2.getGoodsId();
            Goods byId3 = goodsService.getById(goodsId1);
            toGift.setGoods(byId3);
        }
        log.info("最终结果:{}", goodsToGiftList);
        return new AxiosResult(E.SUC, new TableDatas(goodsToGiftList));
    }

    @Transactional
    @Override
    public AxiosResult updateGoodsToGiftNumberById(String goodsToGiftId, String goodsToGiftInventory) {
        GoodsToGift goodsToGift = goodsToGiftMapper.selectById(goodsToGiftId);
        goodsToGift.setGoodsToGiftNumber(goodsToGiftInventory);

        String getgiftId = goodsToGift.getgiftId();
        CrmGoodsGift byId = goodsGiftService.getById(getgiftId);
        String goodsId = byId.getGoodsId();
        Goods byId1 = goodsService.getById(goodsId);
        if (Integer.valueOf(goodsToGiftInventory) > Integer.valueOf(byId1.getGoodsInventory())) {
            return AxiosResult.error(E.ERROR);
        }
        int i = goodsToGiftMapper.updateById(goodsToGift);
        if (i == 0) {
            return AxiosResult.error(E.ERROR);
        }
        return AxiosResult.suc();
    }

    @Override
    public AxiosResult deleteById(String id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        queryWrapper.ne("check_status", "1");
        int delete = goodsToGiftMapper.delete(queryWrapper);
        if (delete == 0) {
            return AxiosResult.error(E.ERROR);
        }
        return AxiosResult.suc();
    }

    @Override
    public AxiosResult getGiftById(String id) {
        GoodsToGift goodsToGift = goodsToGiftMapper.selectById(id);
        String s = goodsToGift.getgiftId();
        CrmGoodsGift byId = goodsGiftService.getById(s);
        goodsToGift.setGoodsGift(byId);
        String goodsId = goodsToGift.getGoodsId();
        Goods byId1 = goodsService.getById(goodsId);
        goodsToGift.setGoods(byId1);
        return AxiosResult.suc(goodsToGift);
    }

    @Override
    public AxiosResult queryToDoTaskByParams(CrmGoodsGift goodsGift, Integer pageNum, Integer pageSize, ProcessRecords processRecords, String loginUserId) {
        //根据条件查询对应的赠品
        QueryWrapper queryWrapper1 = new QueryWrapper();
        if (StringUtils.hasLength(goodsGift.getGiftName())) {
            queryWrapper1.like("gift_name", goodsGift.getGiftName());
        }
        if (StringUtils.hasLength(goodsGift.getGiftClassify())) {
            queryWrapper1.eq("gift_classify", goodsGift.getGiftClassify());
        }
        //查询到的赠品
        List<CrmGoodsGift> list1 = goodsGiftService.list(queryWrapper1);
        //存放赠品的的id
        List<String> idList = new ArrayList<>();

        if (list1.size() != 0) {
            for (CrmGoodsGift goodsGift1 : list1) {
                String id = goodsGift1.getId();
                idList.add(id);
            }
        } else {
            return AxiosResult.suc(new TableDatas(new ArrayList(), pageNum, pageSize, 0));
        }


        //查询记录
        String processName = processRecords.getProcessName();
        String approvalResult = processRecords.getApprovalResult();
        List<ProcessRecords> processRecords1 = processRecordsService.queryTodoTasks(loginUserId, processName, approvalResult);
        if (processRecords1.size() == 0) {
            return AxiosResult.suc(new TableDatas(processRecords1, pageNum, pageSize, 0));
        }
        //申请表id集合
        List<String> list = new ArrayList<>();
        for (ProcessRecords processRecord : processRecords1) {
            String applicationId = processRecord.getApplicationId();
            list.add(applicationId);
        }

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.in("gift_id", idList);
        queryWrapper.in("id", list);
        queryWrapper.orderByDesc("update_time");
        PageHelper.startPage(Optional.ofNullable(pageNum).orElse(1), Optional.ofNullable(pageSize).orElse(3));
        if (DictEnum.GOODS_TO_GIFT_CHECK_NAME.getDictName().equals(processName)) {
            List<GoodsToGift> list2 = goodsToGiftMapper.selectList(queryWrapper);
            for (GoodsToGift goodsToGift : list2) {
                //查询赠品存入申请对应的赠品对象
                String s = goodsToGift.getgiftId();
                CrmGoodsGift byId = goodsGiftService.getById(s);
                goodsToGift.setGoodsGift(byId);
                //查询流程记录信息存入申请表对应的流程记录对象
                QueryWrapper queryWrapper2 = new QueryWrapper();
                queryWrapper2.eq("application_id", goodsToGift.getId());
                queryWrapper2.eq("process_name", DictEnum.GOODS_TO_GIFT_CHECK_NAME.getDictName());
                ProcessRecords one = processRecordsService.getOne(queryWrapper2);
                goodsToGift.setProcessRecords(one);
            }
            return AxiosResult.suc(new TableDatas(list2));
        } else if (DictEnum.GIFT_TO_GOODS_CHECK_NAME.getDictName().equals(processName)) {
            List<GiftToGoods> list2 = giftToGoodsMapper.selectList(queryWrapper);
            for (GiftToGoods giftToGoods : list2) {
                //查询赠品存入申请对应的赠品对象
                String s = giftToGoods.getGiftId();
                CrmGoodsGift byId = goodsGiftService.getById(s);
                giftToGoods.setGoodsGift(byId);
                //查询流程记录信息存入申请表对应的流程记录对象
                QueryWrapper queryWrapper2 = new QueryWrapper();
                queryWrapper2.eq("application_id", giftToGoods.getId());
                queryWrapper2.eq("process_name", DictEnum.GIFT_TO_GOODS_CHECK_NAME.getDictName());
                ProcessRecords one = processRecordsService.getOne(queryWrapper2);
                giftToGoods.setProcessRecords(one);
            }
            return AxiosResult.suc(new TableDatas(list2));
        }

        return AxiosResult.error(E.ERROR);
    }

    @Transactional
    @Override
    public AxiosResult doApprove(String loginUserId, ProcessRecords processRecords) {
        String id = processRecords.getId();
        String approvalSuggestion = processRecords.getApprovalSuggestion();
        String approvalResult = processRecords.getApprovalResult();
        Integer integer = processRecordsService.doApprove(id, approvalResult, approvalSuggestion, loginUserId);
        if (integer == 0) {
            return AxiosResult.error(E.ERROR);
        }
        //流程记录 result 1 审核不通过 2 审核通过
        //申请表  0 未审核 1审核通过 2审核未通过 3 草稿
        if (integer.equals(1)) {  //未通过
            //修改申请表中审核状态为申请不通过
            ProcessRecords byId = processRecordsService.getById(id);
            String applicationId = byId.getApplicationId();
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("id", applicationId);
            updateWrapper.set("check_status", 2);
            boolean update = true;
            if (DictEnum.GOODS_TO_GIFT_CHECK_NAME.getDictName().equals(processRecords.getProcessName())) {
                update = goodsToGiftService.update(updateWrapper);
            } else if (DictEnum.GIFT_TO_GOODS_CHECK_NAME.getDictName().equals(processRecords.getProcessName())) {
                update = giftToGoodsService.update(updateWrapper);
            }
            if (!update) {
                return AxiosResult.error(E.ERROR);
            }
        } else if (integer.equals(3)) {  //通过 根据商品转赠品数量或者赠品转商品数量 修改商品表和赠品表的库存
            //修改申请表中的审核状态为审核通过
            ProcessRecords byId = processRecordsService.getById(id);
            String applicationId = byId.getApplicationId();
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("id", applicationId);
            updateWrapper.set("check_status", 1);

            if (DictEnum.GOODS_TO_GIFT_CHECK_NAME.getDictName().equals(processRecords.getProcessName())) {
                boolean update = goodsToGiftService.update(updateWrapper);
                //修改赠品表库存 商品表库存
                GoodsToGift goodsToGift = goodsToGiftService.getById(applicationId);
                //商品转赠品数量
                String goodsToGiftNumber = goodsToGift.getGoodsToGiftNumber();
                //修改赠品表库存
                String giftId = goodsToGift.getgiftId();
                CrmGoodsGift byId1 = goodsGiftService.getById(giftId);
                byId1.setGiftInventory(String.valueOf(Integer.valueOf(byId1.getGiftInventory()) + Integer.valueOf(goodsToGiftNumber)));
                byId1.setGiftStatus("1");
                boolean b = goodsGiftService.updateById(byId1);
                //修改商品表库存
                String goodsId = byId1.getGoodsId();
                Goods byId2 = goodsService.getById(goodsId);
                String goodsInventory = byId2.getGoodsInventory();
                if (Integer.valueOf(goodsInventory) < Integer.valueOf(goodsToGiftNumber)) {
                    return AxiosResult.error(E.GOODS_INVENTORY_ERROR);
                }
                byId2.setGoodsInventory(String.valueOf(Integer.valueOf(byId2.getGoodsInventory()) - Integer.valueOf(goodsToGiftNumber)));
                boolean b1 = goodsService.updateById(byId2);
            } else if (DictEnum.GIFT_TO_GOODS_CHECK_NAME.getDictName().equals(processRecords.getProcessName())) {
                boolean update = giftToGoodsService.update(updateWrapper);
                //修改赠品表库存 商品表库存
                GiftToGoods giftToGoods = giftToGoodsService.getById(applicationId);
                //赠品转商品数量
                String giftToGoodsNumber = giftToGoods.getGiftToGoodsNumber();
                //修改赠品表库存
                String giftId = giftToGoods.getGiftId();
                CrmGoodsGift byId1 = goodsGiftService.getById(giftId);
                String giftInventory = byId1.getGiftInventory();
                if (Integer.valueOf(giftToGoodsNumber)>Integer.valueOf(giftInventory)){
                    return AxiosResult.error(E.ERROR);
                }
                byId1.setGiftInventory(String.valueOf(Integer.valueOf(byId1.getGiftInventory()) - Integer.valueOf(giftToGoodsNumber)));
                if ((Integer.valueOf(byId1.getGiftInventory()) - Integer.valueOf(giftToGoodsNumber))==0){
                    byId1.setGiftStatus("0");
                }else {
                    byId1.setGiftStatus("1");
                }
                boolean b = goodsGiftService.updateById(byId1);
                //修改商品表库存
                String goodsId = byId1.getGoodsId();
                Goods byId2 = goodsService.getById(goodsId);
                byId2.setGoodsInventory(String.valueOf(Integer.valueOf(byId2.getGoodsInventory()) + Integer.valueOf(giftToGoodsNumber)));
                boolean b1 = goodsService.updateById(byId2);
                if (!(update && b && b1)){
                    return AxiosResult.error(E.ERROR);
                }
            }
        }
        return AxiosResult.suc();
    }

    @Override
    public boolean save(GoodsToGift goodsToGift) {
        String goodsId = goodsToGift.getGoodsId();
        Goods byId = goodsService.getById(goodsId);
        Integer goodsToGiftNumber = 1;
        Integer goodsInventory = 0;
        if (StringUtils.hasLength(goodsToGift.getGoodsToGiftNumber())) {
            goodsToGiftNumber = Integer.parseInt(goodsToGift.getGoodsToGiftNumber());
        }
        if (StringUtils.hasLength(byId.getGoodsInventory())) {
            goodsInventory = Integer.parseInt(byId.getGoodsInventory());
        }
        if (goodsToGiftNumber > goodsInventory) {
            return false;
        }
        int insert = goodsToGiftMapper.insert(goodsToGift);
        if (insert != 1) {
            return false;
        }
        return true;
    }
}
