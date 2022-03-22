package com.javasm.sys.service;

import com.javasm.sys.entity.CrmDepartment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface ICrmDepartmentService extends IService<CrmDepartment> {

    Integer deleteDepartment(String id);

}
