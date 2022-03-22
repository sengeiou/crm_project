package com.javasm.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@TableName("crm_log")
public class CrmLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField(value = "ops_time")
    private LocalDateTime opsTime;

    @TableField(value = "user_id")
    private String userId;

    @TableField(value = "access_moudle")
    private String accessMoudle;

    @TableField(value = "ops_context")
    private String opsContext;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "login_user")
    private String loginUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "front_time")
    private LocalDateTime frontTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "after_time")
    private LocalDateTime afterTime;

    public static void main(String[] args) {
        System.out.println(new Date());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public LocalDateTime getOpsTime() {
        return opsTime;
    }

    public void setOpsTime(LocalDateTime opsTime) {
        this.opsTime = opsTime;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAccessMoudle() {
        return accessMoudle;
    }

    public void setAccessMoudle(String accessMoudle) {
        this.accessMoudle = accessMoudle;
    }
    public String getOpsContext() {
        return opsContext;
    }

    public void setOpsContext(String opsContext) {
        this.opsContext = opsContext;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String temp) {
        this.loginUser = temp;
    }
    public LocalDateTime getFrontTime() {
        return frontTime;
    }

    public void setFrontTime(LocalDateTime temp1) {
        this.frontTime = temp1;
    }
    public LocalDateTime getAfterTime() {
        return afterTime;
    }

    public void setAfterTime(LocalDateTime temp2) {
        this.afterTime = temp2;
    }

    @Override
    public String toString() {
        return "CrmLog{" +
            "id=" + id +
            ", opsTime=" + opsTime +
            ", userId=" + userId +
            ", accessMoudle=" + accessMoudle +
            ", opsContext=" + opsContext +
            ", createTime=" + createTime +
            ", loginUser=" + loginUser +
            ", frontTime=" + frontTime +
            ", afterTime=" + afterTime +
        "}";
    }
}
