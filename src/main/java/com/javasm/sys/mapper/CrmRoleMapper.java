package com.javasm.sys.mapper;

import com.javasm.sys.entity.CrmRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface CrmRoleMapper extends BaseMapper<CrmRole> {

    String queryRole(String roleId);

}
