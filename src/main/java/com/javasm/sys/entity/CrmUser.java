package com.javasm.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;


@TableName("crm_user")
public class CrmUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 登录名
     */
    @TableField(value = "login_user")
    private String loginUser;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 从字典表获取
     */
    @TableField(value = "common_sex")
    private String commonSex;

    /**
     * 从字典表获取
     */
    @TableField(value = "common_status")
    private String commonStatus;

    /**
     * 手机号
     */
    @TableField(value = "user_phone")
    private String userPhone;

    /**
     * 用户邮箱
     */
    @TableField(value = "user_email")
    private String userEmail;

    /**
     * 部门id
     */
    @TableField(value = "department_id")
    private String departmentId;

    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private String roleId;

    /**
     * 用户职务（从字典表获取
     */
    @TableField(value = "common_post")
    private String commonPost;

    /**
     * 备注
     */
    @TableField(value = "user_desc")
    private String userDesc;

    /**
     * 备用字段
     */
    @TableField(value = "user_pass")
    private String userPass;

    @TableField(value = "temp2")
    private String temp2;

    @TableField(value = "temp3")
    private String temp3;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(value = "cteate_by")
    private String cteateBy;

    /**
     * 修改人
     */
    @TableField(value = "update_by")
    private String updateBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getCommonSex() {
        return commonSex;
    }

    public void setCommonSex(String userSex) {
        this.commonSex = userSex;
    }
    public String getCommonStatus() {
        return commonStatus;
    }

    public void setCommonStatus(String userStatus) {
        this.commonStatus = userStatus;
    }
    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    public String getCommonPost() {
        return commonPost;
    }

    public void setCommonPost(String userPost) {
        this.commonPost = userPost;
    }
    public String getUserDesc() {
        return userDesc;
    }

    public void setUserDesc(String userDesc) {
        this.userDesc = userDesc;
    }
    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userId) {
        this.userPass = userId;
    }
    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }
    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3;
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
    public String getCteateBy() {
        return cteateBy;
    }

    public void setCteateBy(String cteateBy) {
        this.cteateBy = cteateBy;
    }
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "CrmUser{" +
            "id=" + id +
            ", loginUser=" + loginUser +
            ", userName=" + userName +
            ", commonSex=" + commonSex +
            ", commonStatus=" + commonStatus +
            ", userPhone=" + userPhone +
            ", userEmail=" + userEmail +
            ", departmentId=" + departmentId +
            ", roleId=" + roleId +
            ", commonPost=" + commonPost +
            ", userDesc=" + userDesc +
            ", userPass=" + userPass +
            ", temp2=" + temp2 +
            ", temp3=" + temp3 +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", cteateBy=" + cteateBy +
            ", updateBy=" + updateBy +
        "}";
    }
}
