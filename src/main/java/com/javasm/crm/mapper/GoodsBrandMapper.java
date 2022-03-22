package com.javasm.crm.mapper;

import com.javasm.crm.entity.GoodsBrand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface GoodsBrandMapper extends BaseMapper<GoodsBrand> {

    String getMaxId();
}
