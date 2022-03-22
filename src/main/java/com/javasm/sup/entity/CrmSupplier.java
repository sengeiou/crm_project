package com.javasm.sup.entity;

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
 * @since 2022-02-18
 */
@TableName("crm_supplier")
public class CrmSupplier implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 供应商名称
     */
    @TableField(value = "s_name")
    private String sName;

    /**
     * 联系人
     */
    @TableField(value = "s_contact")
    private String sContact;

    /**
     * 联系电话
     */
    @TableField(value = "s_phone")
    private String sPhone;

    /**
     * 练习地址
     */
    @TableField(value = "s_address")
    private String sAddress;

    /**
     * 开户账号
     */
    @TableField(value = "s_account_number")
    private String sAccountNumber;

    /**
     * 开户银行
     */
    @TableField(value = "s_account_bank")
    private String sAccountBank;

    /**
     * 邮箱
     */
    @TableField(value = "s_email")
    private String sEmail;

    /**
     * 创建时间（默认为now()）
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 更改时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 更改人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 备用字段1
     */
    @TableField(value = "temp1")
    private String temp1;

    /**
     * 备用字段2
     */
    @TableField(value = "temp2")
    private String temp2;

    /**
     * 备用字段3
     */
    @TableField(value = "temp3")
    private String temp3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }
    public String getsContact() {
        return sContact;
    }

    public void setsContact(String sContact) {
        this.sContact = sContact;
    }
    public String getsPhone() {
        return sPhone;
    }

    public void setsPhone(String sPhone) {
        this.sPhone = sPhone;
    }
    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }
    public String getsAccountNumber() {
        return sAccountNumber;
    }

    public void setsAccountNumber(String sAccountNumber) {
        this.sAccountNumber = sAccountNumber;
    }
    public String getsAccountBank() {
        return sAccountBank;
    }

    public void setsAccountBank(String sAccountBank) {
        this.sAccountBank = sAccountBank;
    }
    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
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
    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
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

    @Override
    public String toString() {
        return "CrmSupplier{" +
            "id=" + id +
            ", sName=" + sName +
            ", sContact=" + sContact +
            ", sPhone=" + sPhone +
            ", sAddress=" + sAddress +
            ", sAccountNumber=" + sAccountNumber +
            ", sAccountBank=" + sAccountBank +
            ", sEmail=" + sEmail +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", createBy=" + createBy +
            ", updateBy=" + updateBy +
            ", temp1=" + temp1 +
            ", temp2=" + temp2 +
            ", temp3=" + temp3 +
        "}";
    }
}
