package com.javasm.sys.mapper;

import com.javasm.sys.entity.CrmOps;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-02-21
 */
public interface CrmOpsMapper extends BaseMapper<CrmOps> {



    String getId(String name);
}
