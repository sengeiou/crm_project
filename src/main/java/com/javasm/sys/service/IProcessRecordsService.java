package com.javasm.sys.service;

import com.javasm.sys.entity.ProcessRecords;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 * @author admin
 * @since 2022-02-22
 */
public interface IProcessRecordsService extends IService<ProcessRecords> {
    /**
     * //1申请人提交申请表
     * @param processName 流程名
     * @param applicationId 申请单id
     * @param applicationUserId 申请人id
     * @return 新增的流程记录id
     */
    String submitProess(String processName,String applicationId,String applicationUserId);
    /**
     * //2审批人查询所有审批,根据某人的部门和职务查询String deptId,String userPost
     * @param approvalUesrId 审批人id
     * @return 所有他能审核的，审批记录
     */
    List<ProcessRecords> queryTodoTasks(String approvalUesrId, String processName);
    /**
     * //2审批人查询待审批，（所有审批）,根据某人的部门和职务查询String deptId,String userPost
     * @param approvalUesrId 审批人id
     * @return 所有他能审核的，审批记录
     */
    List<ProcessRecords> queryTodoTasks(String approvalUesrId, String processName,String approvalResult);
    /**
     * //3申请人在管理列表中，查看申请单的审批记录
     * @param processRecordsId 该申请单流程记录表id
     * @return 该申请单的审核记录id
     */
    List<ProcessRecords> queryDoesTask(String processRecordsId);


    List<ProcessRecords> queryDoesTask(String processName,String applicationId);
    /**
     *  //4审批人执行审批操作，提交审批状态，结果，意见；
     *  // 调用接口自己修改自己的审批状态,->进入下一步审核  (要开启事务)
     * @param processRecordsId 流程记录表id
     * @param approvalResult    审批结果
     * @param approvalSuggestion  审批意见
     * @return 返回结果 （1 审核未通过，2 正在审核，3 审核完毕）
     */
    Integer doApprove(String processRecordsId,String approvalResult,String approvalSuggestion,String approvalUserId);
}
