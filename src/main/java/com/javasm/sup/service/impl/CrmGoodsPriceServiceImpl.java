package com.javasm.sup.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.PeekingIterator;
import com.javasm.commons.utils.LoginUser;
import com.javasm.sup.entity.CrmGoodsPrice;
import com.javasm.sup.mapper.CrmGoodsPriceMapper;
import com.javasm.sup.service.ICrmGoodsPriceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javasm.sys.entity.CrmUser;
import com.javasm.sys.service.ICrmUserService;
import org.apache.catalina.User;
import org.springframework.data.repository.core.CrudMethods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-23
 */
@Service
public class CrmGoodsPriceServiceImpl extends ServiceImpl<CrmGoodsPriceMapper, CrmGoodsPrice> implements ICrmGoodsPriceService {

    @Resource
    private ICrmUserService iCrmUserService;

    @Override
    public CrmUser getChecker(CrmUser crmUser) {
        List<CrmUser> checkerList = iCrmUserService.getAllCheckerByUserPost(crmUser);
        return checkerList.get(0);
    }
}
