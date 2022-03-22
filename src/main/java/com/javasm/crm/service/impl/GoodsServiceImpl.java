package com.javasm.crm.service.impl;

import cn.hutool.core.util.IdUtil;

import com.javasm.commons.utils.CreateIdUtils;
import com.javasm.crm.entity.Goods;
import com.javasm.crm.mapper.GoodsMapper;
import com.javasm.crm.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Resource
    private GoodsMapper mapper;
    @Override
    public List<Goods> getGoodsList(Goods obj) {
//        QueryWrapper<Goods> w = new QueryWrapper<>(obj);
//        w.orderByDesc("update_time");
        List<Goods> list = mapper.getGoodsList(obj);
        return list;
    }

    @Override
    public boolean add(Goods obj) {
        String id = mapper.getMaxId();
        obj.setId(CreateIdUtils.createId2(id));
        obj.setGoodsNumber("GO"+IdUtil.objectId().toUpperCase());
        obj.setBlank2("销售部");
        boolean isok = save(obj);
        return isok;
    }

    @Override
    public Map getGoodsAndProdById(String id) {
        Map u = mapper.getGoodsAndProdById(id);
        return u;
    }
}
