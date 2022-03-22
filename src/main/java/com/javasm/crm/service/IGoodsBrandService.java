package com.javasm.crm.service;

import com.javasm.crm.entity.GoodsBrand;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface IGoodsBrandService extends IService<GoodsBrand> {

    boolean add(GoodsBrand obj);
}
