package com.javasm.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.E;
import com.javasm.commons.exception.MvcException;
import com.javasm.sys.entity.CrmRole;
import com.javasm.sys.entity.CrmUser;
import com.javasm.sys.mapper.CrmRoleMapper;
import com.javasm.sys.service.ICrmRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javasm.sys.service.ICrmUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
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
public class CrmRoleServiceImpl extends ServiceImpl<CrmRoleMapper, CrmRole> implements ICrmRoleService {

    @Resource
     private ICrmRoleService crmRoleService ;

    @Resource
    private ICrmUserService iCrmUserService;

    @Resource
    CrmRoleMapper crmRoleMapper ;


    @Override
    public String sessionName(List<CrmRole> roles) {
        QueryWrapper w = new QueryWrapper();

        String permissionName = null;


        for (CrmRole role : roles) {
            String permissionIds = role.getPermissionIds();
            String[] split = permissionIds.split(",");



        }

        return null;
    }

    @Override
    public String queryRole(String roleId) {
        String permissionIds = crmRoleMapper.queryRole(roleId);


        return permissionIds;
    }

    @Override
    public Integer deleteRole(String id) {
        QueryWrapper<CrmUser> w = new QueryWrapper<>();
        w.eq("role_id",id);
        List<CrmUser> list = iCrmUserService.list(w);

        if (list.size()==0){
            String[] strs = id.split(",");
            boolean isok = removeByIds(Arrays.asList(strs));
            return 1;
        }
         return 0;
    }
}
