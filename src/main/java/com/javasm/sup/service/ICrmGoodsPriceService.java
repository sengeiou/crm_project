package com.javasm.sup.service;

import com.javasm.sup.entity.CrmGoodsPrice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javasm.sys.entity.CrmUser;
import org.apache.catalina.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-23
 */
public interface ICrmGoodsPriceService extends IService<CrmGoodsPrice> {

    CrmUser getChecker(CrmUser crmUser);

}
