package com.javasm.sys.service;

import com.javasm.sys.entity.CrmTask;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface ICrmTaskService extends IService<CrmTask> {

    List<CrmTask> getRunningTasks();

    void updateTaskStatus(String id, String status);


}
