package com.javasm.order.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.order.entity.CrmOrderDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface ICrmOrderDetailService extends IService<CrmOrderDetail> {


    List<CrmOrderDetail> getDetailAll(CrmOrderDetail obj);


    boolean updateDetailById(String id);
}
