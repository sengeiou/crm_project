package com.javasm.crm.mapper;

import com.javasm.crm.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    List<Goods> getGoodsList(Goods obj);

    String getMaxId();

    Map getGoodsAndProdById(String id);
}
