package com.javasm.order.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.javasm.commons.dictEnum.DictEnum;
import com.javasm.commons.entity.AxiosResult;
import com.javasm.commons.entity.TableDatas;
import com.javasm.commons.utils.LoginUser;
import com.javasm.crm.entity.Goods;
import com.javasm.crm.service.IGoodsService;
import com.javasm.order.entity.CrmAppeal;
import com.javasm.order.entity.CrmOrderDetail;
import com.javasm.order.entity.CrmOrderInfo;
import com.javasm.order.mapper.CrmAppealMapper;
import com.javasm.order.service.ICrmAppealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javasm.order.service.ICrmOrderDetailService;
import com.javasm.order.service.ICrmOrderInfoService;
import com.javasm.sys.entity.CrmUser;
import com.javasm.sys.entity.ProcessRecords;
import com.javasm.sys.service.ICrmUserService;
import com.javasm.sys.service.IProcessRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@Service
public class CrmAppealServiceImpl extends ServiceImpl<CrmAppealMapper, CrmAppeal> implements ICrmAppealService {
    @Resource
    private ICrmOrderInfoService orderInfoService;
    @Resource
    private IGoodsService goodsService;
    @Resource
    private ICrmOrderDetailService orderDetailService;
    //流程记录
    @Resource
    private IProcessRecordsService processRecordsService;
    @Resource
    private ICrmUserService userService;
    @Resource
    private CrmAppealMapper appealMapper;

    @Override
    public CrmAppeal getdetailInfoById(String id) {
        CrmAppeal appeal = getById(id);
        QueryWrapper<CrmOrderInfo> w = new QueryWrapper<>();
        w.eq("order_no", appeal.getOrderNo());
        CrmOrderInfo one = orderInfoService.getOne(w);
        CrmOrderInfo crmOrderInfo = orderInfoService.getDetailInfoById(one.getId());
        appeal.setOrderInfo(crmOrderInfo);
        List<CrmOrderDetail> list = new ArrayList<>();
        List<Goods> goodsList = new ArrayList<>();
        for (CrmOrderDetail detail : crmOrderInfo.getDetails()) {
            if (detail.getId().equals(appeal.getDetailId())) {
                list.add(detail);
                appeal.setOldGoods(list);
            }
        }
        if (appeal.getGoodsChangeType().equals("2")) {
            QueryWrapper<Goods> nw = new QueryWrapper<>();
            nw.eq("goods_number", appeal.getProdCode());
            Goods goods = goodsService.getOne(nw);
            goodsList.add(goods);
            appeal.setNewGoods(goodsList);
        }
        return appeal;
    }

    @Transactional
    @Override
    public boolean saveDraft(CrmAppeal obj) {
        obj.setCheckStatus("3");
        obj.setRecordNo(IdUtil.getSnowflakeNextIdStr());
        CrmUser loginUser = LoginUser.getLoginUser();
        if (StringUtils.isEmpty(loginUser.getId())){
            loginUser.setId("1");
        }
        if (StringUtils.isEmpty(loginUser.getLoginUser())){
            loginUser.setLoginUser("admin");
        }
        obj.setRecordPersonId(loginUser.getId());
        obj.setRecordPerson(loginUser.getLoginUser());
        boolean save = save(obj);
        boolean update = updateDetailById(obj.getDetailId(), "1");
        return save && update;
    }

    @Transactional
    @Override
    public boolean removeAndUpdateDetail(String id) {
        CrmAppeal appeal = getById(id);
//        if ("1".equals(appeal.getCheckStatus())) {
//            return false;
//        }
        boolean update = true;
        if ("3".equals(appeal.getCheckStatus())) {
            update = updateDetailById(appeal.getDetailId(), "0");
        }
        boolean b = removeById(id);
        return b && update;
    }

    @Transactional
    @Override
    /**
     * 1审核通过 2审核未通过 3草稿 0未审核
     */
    public boolean updateAppealById(CrmAppeal obj) {
        if ("3".equals(obj.getCheckStatus())) {
            if ("1".equals(obj.getGoodsChangeType())) {
                obj.setProdCode(null);
            }
            boolean b = updateDetailById(obj.getDetailId(), "1");
            boolean updateAppeal = updateById(obj);
            return b && updateAppeal;
        }else if ("1".equals(obj.getCheckStatus())||"2".equals(obj.getCheckStatus())){
            boolean b = updateDetailById(obj.getDetailId(), "2");
            boolean updateAppeal = updateById(obj);
            return b && updateAppeal;
        }
        return false;

    }

    @Transactional
    @Override
    public boolean submitAppealCheck(CrmAppeal obj) {
        CrmUser loginUser = LoginUser.getLoginUser();
        if (StringUtils.isEmpty(loginUser.getId())){
            loginUser.setId("1");
        }
        obj.setRecordPersonId(loginUser.getId());
        if (StringUtils.isEmpty(loginUser.getLoginUser())){
            loginUser.setLoginUser("admin");
        }
        obj.setRecordPerson(loginUser.getLoginUser());
        if (StringUtils.isEmpty(obj.getRecordNo())) {
            obj.setRecordNo(IdUtil.getSnowflakeNextIdStr());
        }
        obj.setCheckStatus("0");
        boolean b = saveOrUpdate(obj);
        String id = processRecordsService.submitProess(DictEnum.APPEAL_CHECK.getDictName(), obj.getId(), loginUser.getId());
        if (StringUtils.hasLength(id) && b) {
            return true;
        }
        return false;
    }

    @Override
    public List<ProcessRecords> queryCheckResult(String appealId) {

        List<ProcessRecords> records = processRecordsService.queryDoesTask(DictEnum.APPEAL_CHECK.getDictName(), appealId);
        for (ProcessRecords record : records) {
            if (StringUtils.hasLength(record.getApprovalUesrId())) {
                CrmUser approval = userService.getById(record.getApprovalUesrId());
                record.setApprovalUesrId(approval.getLoginUser());
            }
        }
        return records;
    }

    //审核信息查询
    @Override
    public AxiosResult queryCheckList(CrmAppeal obj, Integer pageNum, Integer pageSize) {
        CrmUser loginUser = LoginUser.getLoginUser();
        if (StringUtils.isEmpty(loginUser.getId())){
            loginUser.setId("1");
        }
        String approvalId = loginUser.getId();
        List<ProcessRecords> records = processRecordsService.queryTodoTasks( approvalId,DictEnum.APPEAL_CHECK.getDictName());
        Set<String> appealIds = new HashSet<>();
        for (ProcessRecords record : records) {
            appealIds.add(record.getApplicationId());//获得当前登录人所有审核的申请表id
        }
        if (appealIds.isEmpty()) {
            return AxiosResult.suc();
        }

        PageHelper.startPage(Optional.ofNullable(pageNum).orElse(1), Optional.ofNullable(pageSize).orElse(3));

            QueryWrapper<CrmAppeal> wrapper=new QueryWrapper<>();
            if (StringUtils.hasLength(obj.getCheckStatus())){
                wrapper.eq("check_status",obj.getCheckStatus());
            }
            if (StringUtils.hasLength(obj.getRecordPerson())){
                wrapper.eq("record_person",obj.getRecordPerson());
            }
            if (StringUtils.hasLength(obj.getOrderNo())){
                wrapper.eq("order_no",obj.getOrderNo());
            }
            wrapper.in("id",appealIds);
        List<CrmAppeal> appeals = appealMapper.selectList(wrapper);

        if (appeals.size()==0){
            return AxiosResult.suc(new TableDatas(new ArrayList()));
        }

        for (CrmAppeal appeal : appeals) {
            List<ProcessRecords> recordsList = new ArrayList<>();
            for (ProcessRecords record : records) {
                if (appeal.getId().equals(record.getApplicationId())) {
                    recordsList.add(record);
                }
            }
            appeal.setRecords(recordsList);
        }
        return AxiosResult.suc(new TableDatas(appeals));

    }

    @Override
    public boolean updateCheckResult(CrmAppeal obj,String approvalResult) {
        CrmUser loginUser = LoginUser.getLoginUser();
        if (StringUtils.isEmpty(loginUser.getId())){
            loginUser.setId("1");
        }
        if (StringUtils.isEmpty(loginUser.getLoginUser())){
            loginUser.setLoginUser("admin");
        }
        String userId = loginUser.getId();
        obj.setCheckUserName(loginUser.getLoginUser());
        obj.setCheckUserId(userId);

        List<ProcessRecords> records = obj.getRecords();
        records.sort(Comparator.comparing(ProcessRecords::getNodeTaskNum));
        boolean b=false;
        for (ProcessRecords record : records) {
            if (StringUtils.isEmpty(record.getApprovalResult())){
                record.setApprovalSuggestion(obj.getCheckSuggestion());
                Integer integer = processRecordsService.doApprove(record.getId(), approvalResult, obj.getCheckSuggestion(), userId);
                if (integer==3){
                    obj.setCheckStatus("1");
                    b = updateAppealById(obj);
                }else if (integer==1){
                    obj.setCheckStatus("2");

                    b = updateAppealById(obj);
                }
                break;
            }
        }
        return b;
    }


    public boolean updateDetailById(String id, String temp2) {
        UpdateWrapper<CrmOrderDetail> detailWrapper = new UpdateWrapper<>();
        detailWrapper.eq("id", id);
        detailWrapper.set("temp2", temp2);
        boolean update = orderDetailService.update(detailWrapper);
        return update;
    }


}
