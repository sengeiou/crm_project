package com.javasm.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.sys.entity.CrmTask;
import com.javasm.sys.mapper.CrmTaskMapper;
import com.javasm.sys.service.ICrmTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class CrmTaskServiceImpl extends ServiceImpl<CrmTaskMapper, CrmTask> implements ICrmTaskService {

    @Override
    public List<CrmTask> getRunningTasks() {

        QueryWrapper w = new QueryWrapper();
        w.eq("task_status","true");
        return list(w);
    }

    @Override
    public void updateTaskStatus(String id, String status) {

            CrmTask t = new CrmTask();
            t.setId(id);
            t.setTaskStatus(status);
            updateById(t);

    }
}
