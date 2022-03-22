package com.javasm.crm.service.impl;

import com.javasm.commons.utils.CreateIdUtils;
import com.javasm.crm.entity.GoodsBrand;
import com.javasm.crm.mapper.GoodsBrandMapper;
import com.javasm.crm.service.IGoodsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@Service
public class GoodsBrandServiceImpl extends ServiceImpl<GoodsBrandMapper, GoodsBrand> implements IGoodsBrandService {

    @Resource
    private GoodsBrandMapper mapper;
    @Override
    public boolean add(GoodsBrand obj) {
        String id = mapper.getMaxId();
        obj.setId(CreateIdUtils.createId2(id));
        boolean isok = save(obj);
        return isok;
    }
}
