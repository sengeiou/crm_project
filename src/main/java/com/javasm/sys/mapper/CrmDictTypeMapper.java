package com.javasm.sys.mapper;

import com.javasm.sys.entity.CrmDictType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 系统管理-字典类型 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-02-19
 */
public interface CrmDictTypeMapper extends BaseMapper<CrmDictType> {
     int delentExist(String id);
}
