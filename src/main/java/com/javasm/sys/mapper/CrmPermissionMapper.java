package com.javasm.sys.mapper;

import com.javasm.sys.entity.CrmPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-02-22
 */
public interface CrmPermissionMapper extends BaseMapper<CrmPermission> {

    List<CrmPermission> selectPermission(String id);//根据role_id 查找

}
