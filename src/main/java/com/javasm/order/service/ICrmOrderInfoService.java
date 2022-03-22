package com.javasm.order.service;

import com.javasm.order.entity.CrmOrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface ICrmOrderInfoService extends IService<CrmOrderInfo> {

    List<String> getAllOrderNo();

    CrmOrderInfo getDetailInfoById(String id);

    CrmOrderInfo ExportDocxById(String id);
}
