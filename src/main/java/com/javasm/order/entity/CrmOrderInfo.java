package com.javasm.order.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@TableName("crm_order_info")
public class CrmOrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 订单编号
     */
    @TableField(value = "order_no",whereStrategy = FieldStrategy.NOT_EMPTY)
    private String orderNo;

    /**
     * 订单动作
     */
    @TableField(value = "order_action",whereStrategy = FieldStrategy.NOT_EMPTY)
    private String orderAction;

    /**
     * 业务类型
     */
    @TableField(value = "business_type",whereStrategy = FieldStrategy.NOT_EMPTY)
    private String businessType;

    /**
     * 订单类型
     */
    @TableField(value = "order_type",whereStrategy = FieldStrategy.NOT_EMPTY)

    private String orderType;

    /**
     * 支付方式
     */
    @TableField(value = "pay_type",whereStrategy = FieldStrategy.NOT_EMPTY)
    private String payType;

    /**
     * 订单状态
     */
    @TableField(value = "order_status",whereStrategy = FieldStrategy.NOT_EMPTY)
    private String orderStatus;

    /**
     * 配送方式
     */
    @TableField(value = "send_type")
    private String sendType;

    /**
     * 出库单编号
     */
    @TableField(value = "document_no")
    private String documentNo;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    private String remarks;

    @TableField(value = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String createTime;

    @TableField(value = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String updateTime;

    @TableField(value = "create_by")
    private String createBy;

    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 收货人姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 账户
     */
    @TableField(value = "account")
    private String account;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 手机
     */
    @TableField(value = "smart_phone")
    private String smartPhone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 邮政编码
     */
    @TableField(value = "mail_code")
    private String mailCode;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 配送地区
     */
    @TableField(value = "send_area")
    private String sendArea;

    /**
     * 配送费
     */
    @TableField(value = "send_price")
    private String sendPrice;

    /**
     * 自提时间
     */
    @TableField(value = "get_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String getTime;

    @TableField(value = "temp1")
    private String temp1;

    /**
     * 下单日期
     */
    @TableField(value = "order_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String orderTime;

    @TableField(exist = false)
    private List<CrmOrderDetail> details;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public String getOrderAction() {
        return orderAction;
    }

    public void setOrderAction(String orderAction) {
        this.orderAction = orderAction;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmartPhone() {
        return smartPhone;
    }

    public void setSmartPhone(String smartPhone) {
        this.smartPhone = smartPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMailCode() {
        return mailCode;
    }

    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSendArea() {
        return sendArea;
    }

    public void setSendArea(String sendArea) {
        this.sendArea = sendArea;
    }

    public String getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(String sendPrice) {
        this.sendPrice = sendPrice;
    }

    public String getGetTime() {
        return getTime;
    }

    public void setGetTime(String getTime) {
        this.getTime = getTime;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public List<CrmOrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<CrmOrderDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "CrmOrderInfo{" +
                "id='" + id + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", orderAction='" + orderAction + '\'' +
                ", businessType='" + businessType + '\'' +
                ", orderType='" + orderType + '\'' +
                ", payType='" + payType + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", sendType='" + sendType + '\'' +
                ", documentNo='" + documentNo + '\'' +
                ", remarks='" + remarks + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", phone='" + phone + '\'' +
                ", smartPhone='" + smartPhone + '\'' +
                ", email='" + email + '\'' +
                ", mailCode='" + mailCode + '\'' +
                ", address='" + address + '\'' +
                ", sendArea='" + sendArea + '\'' +
                ", sendPrice='" + sendPrice + '\'' +
                ", getTime=" + getTime +
                ", temp1='" + temp1 + '\'' +
                ", orderTime=" + orderTime +
                ", details=" + details +
                '}';
    }
}
