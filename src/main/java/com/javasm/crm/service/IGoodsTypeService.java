package com.javasm.crm.service;

import com.javasm.crm.entity.GoodsType;
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
public interface IGoodsTypeService extends IService<GoodsType> {

    List<GoodsType> getTreeList(GoodsType obj);

    boolean add(GoodsType obj);
}
