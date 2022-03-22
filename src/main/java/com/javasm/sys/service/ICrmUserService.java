package com.javasm.sys.service;

import com.javasm.sys.entity.CrmUser;
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
public interface ICrmUserService extends IService<CrmUser> {

    CrmUser queryByPhone(String phone);


    List<CrmUser> getAllCheckerByUserPost(CrmUser crmUser);






}
