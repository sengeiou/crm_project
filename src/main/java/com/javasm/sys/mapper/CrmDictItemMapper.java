package com.javasm.sys.mapper;

import com.javasm.sys.entity.CrmDictItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;


public interface CrmDictItemMapper extends BaseMapper<CrmDictItem> {


    List<CrmDictItem> findByTypeId(String id);


    String getMaxId();

    int delentExist(String id);


    List<Map<String, Object>> getDictItem(String dictType);
}
