package com.javasm.crm.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.javasm.commons.utils.CreateIdUtils;
import com.javasm.commons.utils.TreeUtils;
import com.javasm.crm.entity.GoodsType;
import com.javasm.crm.mapper.GoodsTypeMapper;
import com.javasm.crm.service.IGoodsTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@Service
public class GoodsTypeServiceImpl extends ServiceImpl<GoodsTypeMapper, GoodsType> implements IGoodsTypeService {
    @Resource
    private GoodsTypeMapper mapper;

    @Override
    public List<GoodsType> getTreeList(GoodsType obj) {

        QueryWrapper<GoodsType> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<GoodsType> list = list(w);
        List<GoodsType> goodsTypeTree = TreeUtils.createGoodsTypeTree(list, "0");

        return goodsTypeTree;
    }
    @Transactional
    @Override
    public boolean add(GoodsType obj) {
        if(obj.getParentId()!=null&& !obj.getParentId().equals("0")){
            UpdateWrapper<GoodsType> w =new UpdateWrapper<>();
            w.eq("id",obj.getParentId());
            w.set("temp1","1");
            boolean update = update(w);
        }

        String id = mapper.getMaxId();
        obj.setId(CreateIdUtils.createId2(id));

        boolean save = save(obj);
        return save;
    }


}
