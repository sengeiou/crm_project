package com.javasm.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.E;
import com.javasm.commons.exception.MvcException;
import com.javasm.crm.entity.GoodsType;
import com.javasm.sys.entity.CrmPermission;
import com.javasm.sys.mapper.CrmPermissionMapper;
import com.javasm.sys.service.ICrmPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-22
 */
@Service
public class CrmPermissionServiceImpl extends ServiceImpl<CrmPermissionMapper, CrmPermission> implements ICrmPermissionService {

    @Override
    public List<CrmPermission> getTree(List<CrmPermission> permissions, String parentId) {

            List<CrmPermission> list = new ArrayList<>();
        for (CrmPermission permission : permissions) {
            String t = permission.getRoleId();
            if(t!=null&&t.equals(parentId)){
                String id = permission.getId();
                List<CrmPermission> childs = getTree(permissions, id);
                if(childs!=null && childs.size()>0){
                    permission.setChild(childs);
                }
                list.add(permission);
            }
        }
            return list;
    }

    @Override
    public Integer deletePermission(String id) {
        CrmPermission pid = getById(id);
        QueryWrapper<CrmPermission> wrapper = new QueryWrapper();
        wrapper.eq("role_id",id);

        List list = list(wrapper);
        if (list.size() ==0){
            pid.setCanOps("0");
            boolean b = updateById(pid);
            return 1;
        }
       return 0;
    }
}
