package com.javasm.sys.service.impl;

import com.javasm.sys.entity.CrmDictType;
import com.javasm.sys.mapper.CrmDictTypeMapper;
import com.javasm.sys.service.ICrmDictTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 系统管理-字典类型 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-19
 */
@Service
public class CrmDictTypeServiceImpl extends ServiceImpl<CrmDictTypeMapper, CrmDictType> implements ICrmDictTypeService {


    @Resource
    private CrmDictTypeMapper crmDictTypeMapper;
    @Override
    public int deleteEixst(String id) {
        int i = crmDictTypeMapper.delentExist(id);
        return i;
    }
}
