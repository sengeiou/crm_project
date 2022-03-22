package com.javasm.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.javasm.sup.entity.CrmGoodsGift;
import com.javasm.sys.entity.ProcessRecords;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@TableName("crm_gift_to_goods")
@ApiModel(value = "赠品转商品实体类",description = "赠品转商品实体类")
public class GiftToGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField(value = "gift_id")
    private String giftId;

    @TableField(value = "gift_to_goods_number")
    private String giftToGoodsNumber;

    @TableField(value = "check_user_id")
    private String checkUserId;

    @TableField(value = "check_time")
    private LocalDateTime checkTime;

    @TableField(value = "check_status")
    private String checkStatus;

    @TableField(value = "check_idea")
    private String checkIdea;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @TableField(value = "create_by")
    private String createBy;

    @TableField(value = "update_by")
    private String updateBy;

    @TableField(value = "application_id")
    private String applicationId;

    @TableField(value = "apply_time")
    private LocalDateTime applyTime;

    @TableField(value = "temp3")
    private String temp3;

    @TableField(exist = false)
    private CrmGoodsGift goodsGift;

    @TableField(exist = false)
    private ProcessRecords processRecords;

    public ProcessRecords getProcessRecords() {
        return processRecords;
    }

    public void setProcessRecords(ProcessRecords processRecords) {
        this.processRecords = processRecords;
    }

    public LocalDateTime getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(LocalDateTime applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public CrmGoodsGift getGoodsGift() {
        return goodsGift;
    }

    public void setGoodsGift(CrmGoodsGift goodsGift) {
        this.goodsGift = goodsGift;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }
    public String getGiftToGoodsNumber() {
        return giftToGoodsNumber;
    }

    public void setGiftToGoodsNumber(String giftToGoodsNumber) {
        this.giftToGoodsNumber = giftToGoodsNumber;
    }
    public String getCheckUserId() {
        return checkUserId;
    }

    public void setCheckUserId(String checkUserId) {
        this.checkUserId = checkUserId;
    }
    public LocalDateTime getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(LocalDateTime checkTime) {
        this.checkTime = checkTime;
    }
    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }
    public String getCheckIdea() {
        return checkIdea;
    }

    public void setCheckIdea(String checkIdea) {
        this.checkIdea = checkIdea;
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

    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }

    @Override
    public String toString() {
        return "GiftToGoods{" +
                "id='" + id + '\'' +
                ", giftId='" + giftId + '\'' +
                ", giftToGoodsNumber='" + giftToGoodsNumber + '\'' +
                ", checkUserId='" + checkUserId + '\'' +
                ", checkTime=" + checkTime +
                ", checkStatus='" + checkStatus + '\'' +
                ", checkIdea='" + checkIdea + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", applicationId='" + applicationId + '\'' +
                ", applyTime=" + applyTime +
                ", temp3='" + temp3 + '\'' +
                ", goodsGift=" + goodsGift +
                '}';
    }
}
