package com.javasm.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.javasm.commons.entity.E;
import com.javasm.commons.exception.MvcException;
import com.javasm.commons.utils.CreateIdUtils;
import com.javasm.sys.controller.ProcessDefinitionController;
import com.javasm.sys.entity.CrmUser;
import com.javasm.sys.entity.ProcessDefinition;
import com.javasm.sys.entity.ProcessRecords;
import com.javasm.sys.mapper.CrmUserMapper;
import com.javasm.sys.mapper.ProcessRecordsMapper;
import com.javasm.sys.service.ICrmUserService;
import com.javasm.sys.service.IProcessDefinitionService;
import com.javasm.sys.service.IProcessRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.xml.bind.Element;
import java.lang.annotation.ElementType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-22
 */
@Service
public class ProcessRecordsServiceImpl extends ServiceImpl<ProcessRecordsMapper, ProcessRecords> implements IProcessRecordsService {

    @Resource
    private IProcessDefinitionService pds;
    @Resource
    private ProcessRecordsMapper mapper;
    @Resource
    private ICrmUserService cus;

    @Override
    public String submitProess(String processName, String applicationId, String applicationUserId) {

        //1查询审批定义
        QueryWrapper<ProcessDefinition> w = new QueryWrapper();
        w.orderByAsc("node_task_num");
        w.eq("process_name", processName);
        List<ProcessDefinition> list = pds.list(w);
        //2向审批记录里添加流程
        ProcessRecords pr = new ProcessRecords();
        String newId = CreateIdUtils.createId2(mapper.getMaxId());
        pr.setId(newId);
        pr.setProcessName(processName);
        pr.setApplicationId(applicationId);
        pr.setApplicationUserId(applicationUserId);
        pr.setApprovalStatus("1");

        ProcessDefinition pd = list.get(1);
        pr.setNodeTaskNum(2);
        pr.setNodeTaskName(pd.getNodeTaskName());
        boolean save = save(pr);
        return newId;
    }

    @Override
    public List<ProcessRecords> queryTodoTasks(String approvalUesrId, String processName) {
        //1查询部门和职务
        CrmUser user = cus.getById(approvalUesrId);
        //2查询流程定义表，获取 流程名 和 节点任务名称
        QueryWrapper<ProcessDefinition> w = new QueryWrapper();
        w.eq("dept_id", user.getDepartmentId());
        w.eq("user_post", user.getCommonPost());
        w.eq("process_name", processName);
        ProcessDefinition one = pds.getOne(w);
        if (one==null){
            List<ProcessRecords> processRecords = new ArrayList<>();
            return processRecords;
        }
        //3查询由他审批的传入的流程名的记录
        QueryWrapper<ProcessRecords> w2 = new QueryWrapper();

        w2.eq("process_name", processName);
        w2.eq("node_task_name", one.getNodeTaskName());
        List<ProcessRecords> list2 = list(w2);
        return list2;
    }

    @Override
    public List<ProcessRecords> queryTodoTasks(String approvalUesrId, String processName,String approvalResult) {
        //1查询部门和职务
        CrmUser user = cus.getById(approvalUesrId);
        //2查询流程定义表，获取 流程名 和 节点任务名称
        QueryWrapper<ProcessDefinition> w = new QueryWrapper();
        w.eq("dept_id", user.getDepartmentId());
        w.eq("user_post", user.getCommonPost());
        w.eq("process_name", processName);
        ProcessDefinition one = pds.getOne(w);
        if (one==null){
            List<ProcessRecords> processRecords = new ArrayList<>();
            return processRecords;
        }
        //3查询由他审批的传入的流程名的记录
        QueryWrapper<ProcessRecords> w2 = new QueryWrapper();
        if (StringUtils.hasLength(approvalResult)){
            w2.eq("approval_result",approvalResult);
            w2.eq("approval_status",2);
            w2.eq("approval_uesr_id",approvalUesrId);
        }else {
            w2.eq("approval_status",1);
        }
        w2.eq("process_name", processName);
        w2.eq("node_task_name", one.getNodeTaskName());
        List<ProcessRecords> list2 = list(w2);
        return list2;
    }


    @Override
    public List<ProcessRecords> queryDoesTask(String processRecordsId) {

        ProcessRecords byId = getById(processRecordsId);
        //通过 申请表id 和 流程名  ，查询该申请表下的该id的所有审核记录

        QueryWrapper w = new QueryWrapper();
        w.eq("application_id", byId.getApplicationId());
        w.eq("process_name", byId.getProcessName());
        List list = list(w);
        return list;
    }

    @Override
    public List<ProcessRecords> queryDoesTask(String processName, String applicationId) {
        QueryWrapper w = new QueryWrapper();
        w.eq("application_id", applicationId);
        w.eq("process_name", processName);
        List list = list(w);
        if (list.size() == 0) {
            ArrayList<ProcessRecords> processRecords = new ArrayList<>();
            processRecords.add(new ProcessRecords());
            return processRecords;
        }
        return list;
    }

    @Override
    public Integer doApprove(String processRecordsId, String approvalResult, String approvalSuggestion,String approvalUserId) {
        //1修改审批记录
        UpdateWrapper<ProcessRecords> w = new UpdateWrapper();
        w.eq("id", processRecordsId);
        ProcessRecords pr = new ProcessRecords();

        pr.setApprovalStatus("2");//已审批
        pr.setApprovalUesrId(approvalUserId);
        pr.setApprovalResult(approvalResult);
        pr.setApprovalSuggestion(approvalSuggestion);
        pr.setApprovalTime(LocalDateTime.now());
        boolean update = update(pr, w);
        if ("1".equals(approvalResult) && update) {
            return 1;//审核未通过
        }else if (!update){
            return 0;
        }
        //2查询是否有下一步审核
        ProcessRecords byId = getById(processRecordsId);

        QueryWrapper<ProcessDefinition> w2 = new QueryWrapper();
        w2.eq("node_task_num", byId.getNodeTaskNum() + 1);
        w2.eq("process_name", byId.getProcessName());
        ProcessDefinition one = pds.getOne(w2);
        ProcessRecords addpr = new ProcessRecords();
        if (one != null ) {
            ////3有则像向 审批记录 里添加下一步流程，返回2 正在审核
            String newId = CreateIdUtils.createId2(mapper.getMaxId());
            addpr.setId(newId);
            addpr.setProcessName(byId.getProcessName());
            addpr.setApplicationId(byId.getApplicationId());
            addpr.setApplicationUserId(byId.getApplicationUserId());
            addpr.setApprovalStatus("1");

            addpr.setNodeTaskNum(2);
            addpr.setNodeTaskName(one.getNodeTaskName());
            boolean save = save(addpr);
            if (save){
                return 2;
            }else {
                return 0;
            }
        } else {
            if (update){
                //3 审核完毕
                return 3;
            }else {
                return 0;
            }

        }

    }


}
