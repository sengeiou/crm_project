package com.javasm.crm.service;

import com.javasm.crm.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */

public interface IGoodsService extends IService<Goods> {

    List<Goods> getGoodsList(Goods obj);

    boolean add(Goods obj);

    Map getGoodsAndProdById(String id);
}
