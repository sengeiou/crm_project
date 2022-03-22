package com.javasm.sys.mapper;

import com.javasm.sys.entity.ProcessRecords;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2022-02-22
 */
public interface ProcessRecordsMapper extends BaseMapper<ProcessRecords> {
    String getMaxId();

}
