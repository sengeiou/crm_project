package com.javasm.crm.mapper;

import com.javasm.crm.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface ProductMapper extends BaseMapper<Product> {

     String getMaxId();


}
