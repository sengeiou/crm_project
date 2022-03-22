package com.javasm.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2022-02-22
 */
@TableName("process_records")
public class ProcessRecords implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField(value = "process_name")
    private String processName;


    @TableField(value = "application_user_id")
    private String applicationUserId;

    public String getApplicationUserId() {
        return applicationUserId;
    }

    public void setApplicationUserId(String applicationUserId) {
        this.applicationUserId = applicationUserId;
    }

    @TableField(value = "application_id")
    private String applicationId;

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @TableField(value = "approval_uesr_id")
    private String approvalUesrId;

    @TableField(value = "approval_status")
    private String approvalStatus;

    @TableField(value = "approval_result")
    private String approvalResult;

    @TableField(value = "approval_suggestion")
    private String approvalSuggestion;

    @TableField(value = "node_task_num")
    private Integer nodeTaskNum;

    @TableField(value = "node_task_name")
    private String nodeTaskName;

    @TableField(value = "approval_time")
    private LocalDateTime approvalTime;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @TableField(value = "create_by")
    private String createBy;

    @TableField(value = "update_by")
    private String updateBy;

    @TableField(value = "blank1")
    private String blank1;

    @TableField(value = "blank2")
    private String blank2;

    @TableField(value = "blank3")
    private String blank3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
    public String getApprovalUesrId() {
        return approvalUesrId;
    }

    public void setApprovalUesrId(String approvalUesrId) {
        this.approvalUesrId = approvalUesrId;
    }
    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
    public String getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(String approvalResult) {
        this.approvalResult = approvalResult;
    }
    public String getApprovalSuggestion() {
        return approvalSuggestion;
    }

    public void setApprovalSuggestion(String approvalSuggestion) {
        this.approvalSuggestion = approvalSuggestion;
    }
    public Integer getNodeTaskNum() {
        return nodeTaskNum;
    }

    public void setNodeTaskNum(Integer nodeTaskNum) {
        this.nodeTaskNum = nodeTaskNum;
    }
    public String getNodeTaskName() {
        return nodeTaskName;
    }

    public void setNodeTaskName(String nodeTaskName) {
        this.nodeTaskName = nodeTaskName;
    }
    public LocalDateTime getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(LocalDateTime approvalTime) {
        this.approvalTime = approvalTime;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getBlank1() {
        return blank1;
    }

    public void setBlank1(String blank1) {
        this.blank1 = blank1;
    }

    public String getBlank2() {
        return blank2;
    }

    public void setBlank2(String blank2) {
        this.blank2 = blank2;
    }
    public String getBlank3() {
        return blank3;
    }

    public void setBlank3(String blank3) {
        this.blank3 = blank3;
    }


}
