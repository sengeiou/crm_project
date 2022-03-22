package com.javasm.crm.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.javasm.sup.entity.CrmGoodsGift;
import com.javasm.sys.entity.ProcessRecords;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;
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
@TableName("crm_goods_to_gift")
@ApiModel(value = "商品转赠品实体类",description = "商品转赠品实体类")
public class GoodsToGift implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键id")
    private String id;

    @TableField(value = "goods_id")
    @ApiModelProperty(value = "商品id")
    private String goodsId;

    @TableField(value = "goods_to_gift_number")
    private String goodsToGiftNumber;

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

    @TableField(value = "gift_id")
    private String giftId;

    @TableField(value = "temp2")
    private String temp2;

    @TableField(value = "temp3")
    private String temp3;

    @TableField(exist = false)
    private Goods goods;

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

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
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
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    public String getGoodsToGiftNumber() {
        return goodsToGiftNumber;
    }

    public void setGoodsToGiftNumber(String goodsToGiftNumber) {
        this.goodsToGiftNumber = goodsToGiftNumber;
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
    public String getgiftId() {
        return giftId;
    }

    public void setgiftId(String giftId) {
        this.giftId = giftId;
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
        return "GoodsToGift{" +
            "id=" + id +
            ", goodsId=" + goodsId +
            ", goodsToGiftNumber=" + goodsToGiftNumber +
            ", checkUserId=" + checkUserId +
            ", checkTime=" + checkTime +
            ", checkStatus=" + checkStatus +
            ", checkIdea=" + checkIdea +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", createBy=" + createBy +
            ", updateBy=" + updateBy +
            ", giftId=" + giftId +
            ", temp2=" + temp2 +
            ", temp3=" + temp3 +
        "}";
    }
}
