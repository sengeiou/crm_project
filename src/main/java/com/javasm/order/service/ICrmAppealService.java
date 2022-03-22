package com.javasm.order.service;

import com.javasm.commons.entity.AxiosResult;
import com.javasm.order.entity.CrmAppeal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javasm.sys.entity.ProcessRecords;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface ICrmAppealService extends IService<CrmAppeal> {

    CrmAppeal getdetailInfoById(String id);

    boolean saveDraft(CrmAppeal obj);


    boolean removeAndUpdateDetail(String id);

    boolean updateAppealById(CrmAppeal obj);

    boolean submitAppealCheck(CrmAppeal obj);

    List<ProcessRecords> queryCheckResult(String appealId);

    AxiosResult queryCheckList(CrmAppeal obj, Integer pageNum, Integer pageSize);

    boolean updateCheckResult(CrmAppeal obj,String approvalResult);
}
