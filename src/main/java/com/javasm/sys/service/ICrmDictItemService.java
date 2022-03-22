package com.javasm.sys.service;

import com.javasm.sys.entity.CrmDictItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统管理-后台字典管理 服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-19
 */
public interface ICrmDictItemService extends IService<CrmDictItem> {

    List<CrmDictItem> findByDcitTypeId(String dictId);

    List<CrmDictItem> queryByCodeAndName(String code,String name);

  int deleteExist(String id);

    List<Map<String, Object>> getDictItem(String dictType);
}
