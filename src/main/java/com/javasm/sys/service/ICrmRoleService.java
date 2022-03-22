package com.javasm.sys.service;

import com.javasm.sys.entity.CrmRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.javasm.sys.entity.CrmUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface ICrmRoleService extends IService<CrmRole> {


    String sessionName(List<CrmRole> roles);

    String queryRole(String roleId);

     Integer deleteRole(String id);


}
