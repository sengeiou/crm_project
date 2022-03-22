package com.javasm.order.entity;

import com.baomidou.mybatisplus.annotation.*;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.javasm.crm.entity.Goods;
import com.javasm.sys.entity.ProcessRecords;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@TableName("crm_appeal")
public class CrmAppeal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 订单编号
     */
    @TableField(value = "order_no",whereStrategy = FieldStrategy.NOT_EMPTY)
    private String orderNo;

    /**
     * 申诉记录名称
     */
    @TableField(value = "record_name")
    private String recordName;

    /**
     * 记录人/制单人
     */
    @TableField(value = "record_person",whereStrategy = FieldStrategy.NOT_EMPTY)
    private String recordPerson;

    /**
     * 记录时间/制单时间/
     */
    @TableField(value = "create_time",whereStrategy = FieldStrategy.NOT_EMPTY)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String createTime;

    @TableField(value = "check_user_id")
    private String checkUserId;

    @TableField(value = "record_person_id")
    private String recordPersonId;

    @TableField(value = "check_user_name",whereStrategy = FieldStrategy.NOT_EMPTY)
    private String checkUserName;

    @TableField(value = "check_time",whereStrategy = FieldStrategy.NOT_EMPTY)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String checkTime;

    /**
     * 单据状态
     */
    @TableField(value = "check_status",whereStrategy = FieldStrategy.NOT_EMPTY)
    private String checkStatus;

    /**
     * 投诉来源
     */
    @TableField(value = "complaint_source")
    private String complaintSource;

    /**
     * 投诉描述
     */
    @TableField(value = "complaint_descreption")
    private String complaintDescreption;

    /**
     * 投诉单据编号，自动生成
     */
    @TableField(value = "record_no")
    private String recordNo;

    /**
     * 退换货类型
     */
    @TableField(value = "goods_change_type")
    private String goodsChangeType;

    /**
     * 原商品信息串码
     */
    @TableField(value = "old_prod_code")
    private String oldProdCode;

    /**
     * 新商品串码
     */
    @TableField(value = "prod_code")
    private String prodCode;

    /**
     * 商品退换货名称
     */
    @TableField(value = "prod_change_type")
    private String prodChangeType;

    /**
     * 商品退换货原因
     */
    @TableField(value = "prod_change_reson")
    private String prodChangeReson;

    /**
     * 客服回复意见
     */
    @TableField(value = "first_resp")
    private String firstResp;

    /**
     * 回复时间
     */
    @TableField(value = "first_resp_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String firstRespTime;

    /**
     * 2次客服回复意见
     */
    @TableField(value = "sec_resp")
    private String secResp;

    @TableField(value = "sec_resp_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String secRespTime;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    private String remarks;

    @TableField(value = "temp1")
    private String temp1;

    @TableField(value = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String updateTime;

    @TableField(value = "create_by")
    private String createBy;

    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 审核意见
     */
    @TableField(value = "check_suggestion")
    private String checkSuggestion;
    /**
     * 退换商品的订单详情id
     */
    @TableField(value = "detail_id")
    private String detailId;
    /**
     * 选择对应的订单
     */
    @TableField(exist = false)
    private CrmOrderInfo orderInfo;
    /**
     *要退换的商品
     */
    @TableField(exist = false)
    private List<CrmOrderDetail> oldGoods;
    /**
     * 更换后获得的商品
     */
    @TableField(exist = false)
    private List<Goods> newGoods;
    @TableField(exist = false)
    private List<ProcessRecords> records;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }
    public String getRecordPerson() {
        return recordPerson;
    }

    public void setRecordPerson(String recordPerson) {
        this.recordPerson = recordPerson;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId;
    }
    public String getRecordPersonId() {
        return recordPersonId;
    }

    public void setRecordPersonId(String recordPersonId) {
        this.recordPersonId = recordPersonId;
    }
    public String getCheckUserName() {
        return checkUserName;
    }

    public void setCheckUserName(String checkUserName) {
        this.checkUserName = checkUserName;
    }
    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }
    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }
    public String getComplaintSource() {
        return complaintSource;
    }

    public void setComplaintSource(String complaintSource) {
        this.complaintSource = complaintSource;
    }
    public String getComplaintDescreption() {
        return complaintDescreption;
    }

    public void setComplaintDescreption(String complaintDescreption) {
        this.complaintDescreption = complaintDescreption;
    }
    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }
    public String getGoodsChangeType() {
        return goodsChangeType;
    }

    public void setGoodsChangeType(String goodsChangeType) {
        this.goodsChangeType = goodsChangeType;
    }
    public String getOldProdCode() {
        return oldProdCode;
    }

    public void setOldProdCode(String oldProdCode) {
        this.oldProdCode = oldProdCode;
    }
    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }
    public String getProdChangeType() {
        return prodChangeType;
    }

    public void setProdChangeType(String prodChangeType) {
        this.prodChangeType = prodChangeType;
    }
    public String getProdChangeReson() {
        return prodChangeReson;
    }

    public void setProdChangeReson(String prodChangeReson) {
        this.prodChangeReson = prodChangeReson;
    }
    public String getFirstResp() {
        return firstResp;
    }

    public void setFirstResp(String firstResp) {
        this.firstResp = firstResp;
    }
    public String getSecResp() {
        return secResp;
    }

    public String getFirstRespTime() {
        return firstRespTime;
    }

    public void setFirstRespTime(String firstRespTime) {
        this.firstRespTime = firstRespTime;
    }

    public void setSecResp(String secResp) {
        this.secResp = secResp;
    }
    public String getSecRespTime() {
        return secRespTime;
    }

    public void setSecRespTime(String secRespTime) {
        this.secRespTime = secRespTime;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
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
    public String getCheckSuggestion() {
        return checkSuggestion;
    }

    public void setCheckSuggestion(String checkSuggestion) {
        this.checkSuggestion = checkSuggestion;
    }

    public CrmOrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(CrmOrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<CrmOrderDetail> getOldGoods() {
        return oldGoods;
    }

    public void setOldGoods(List<CrmOrderDetail> oldGoods) {
        this.oldGoods = oldGoods;
    }

    public List<Goods> getNewGoods() {
        return newGoods;
    }

    public void setNewGoods(List<Goods> newGoods) {
        this.newGoods = newGoods;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public List<ProcessRecords> getRecords() {
        return records;
    }

    public void setRecords(List<ProcessRecords> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "CrmAppeal{" +
                "id='" + id + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", recordName='" + recordName + '\'' +
                ", recordPerson='" + recordPerson + '\'' +
                ", createTime=" + createTime +
                ", checkUserId='" + checkUserId + '\'' +
                ", recordPersonId='" + recordPersonId + '\'' +
                ", checkUserName='" + checkUserName + '\'' +
                ", checkTime=" + checkTime +
                ", checkStatus='" + checkStatus + '\'' +
                ", complaintSource='" + complaintSource + '\'' +
                ", complaintDescreption='" + complaintDescreption + '\'' +
                ", recordNo='" + recordNo + '\'' +
                ", goodsChangeType='" + goodsChangeType + '\'' +
                ", oldProdCode='" + oldProdCode + '\'' +
                ", prodCode='" + prodCode + '\'' +
                ", prodChangeType='" + prodChangeType + '\'' +
                ", prodChangeReson='" + prodChangeReson + '\'' +
                ", firstResp='" + firstResp + '\'' +
                ", firstRespTime=" + firstRespTime +
                ", secResp='" + secResp + '\'' +
                ", secRespTime=" + secRespTime +
                ", remarks='" + remarks + '\'' +
                ", temp1='" + temp1 + '\'' +
                ", updateTime=" + updateTime +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", checkSuggestion='" + checkSuggestion + '\'' +
                ", detailId='" + detailId + '\'' +
                ", orderInfo=" + orderInfo +
                ", oldGoods=" + oldGoods +
                ", newGoods=" + newGoods +
                '}';
    }
}
