package com.javasm.sys.service.impl;

import com.javasm.sys.entity.CrmOps;
import com.javasm.sys.mapper.CrmOpsMapper;
import com.javasm.sys.service.ICrmOpsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-21
 */
@Service
public class CrmOpsServiceImpl extends ServiceImpl<CrmOpsMapper, CrmOps> implements ICrmOpsService {

    @Resource
    private ICrmOpsService opsService;



    @Resource
    private CrmOpsMapper crmOpsMapper;


    @Override
    public String getId(String name) {
        String id = crmOpsMapper.getId(name);

        return id;
    }



}
