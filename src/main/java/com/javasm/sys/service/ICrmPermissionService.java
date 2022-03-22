package com.javasm.sys.service;

import com.javasm.sys.entity.CrmPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-22
 */
public interface ICrmPermissionService extends IService<CrmPermission> {


    List<CrmPermission> getTree(List<CrmPermission> permissions,String parentId);


    Integer deletePermission(String id);

}
