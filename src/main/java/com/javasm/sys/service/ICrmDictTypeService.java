package com.javasm.sys.service;

import com.javasm.sys.entity.CrmDictType;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统管理-字典类型 服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-19
 */
public interface ICrmDictTypeService extends IService<CrmDictType> {

    int deleteEixst(String id);

}
