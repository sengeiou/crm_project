package com.javasm.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.E;
import com.javasm.commons.exception.MvcException;
import com.javasm.sys.entity.CrmDepartment;
import com.javasm.sys.entity.CrmRole;
import com.javasm.sys.entity.CrmUser;
import com.javasm.sys.mapper.CrmDepartmentMapper;
import com.javasm.sys.service.ICrmDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javasm.sys.service.ICrmRoleService;
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
public class CrmDepartmentServiceImpl extends ServiceImpl<CrmDepartmentMapper, CrmDepartment> implements ICrmDepartmentService {


    @Resource
    private ICrmUserService iCrmUserService;


    @Resource
    private ICrmRoleService iCrmRoleService;

    @Override
    public Integer deleteDepartment(String id) {

        QueryWrapper<CrmUser> w = new QueryWrapper<>();
        w.eq("department_id",id);
        List<CrmUser> list = iCrmUserService.list(w);

        QueryWrapper<CrmRole> wrapper = new QueryWrapper<>();
        wrapper.eq("department_id",id);
        List<CrmRole> list1 = iCrmRoleService.list(wrapper);

        if (list.size()==0&&list1.size()==0){
            String[] strs = id.split(",");
            boolean isok = removeByIds(Arrays.asList(strs));
            return 1;
        }
        return 0;
    }
}
