package com.javasm.sup.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.math.BigDecimal;
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
 * @since 2022-02-23
 */
@TableName("crm_goods_price")
public class CrmGoodsPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 商品id
     */
    @TableField(value = "goods_id")
    private String goodsId;

    /**
     * 商品名称
     */
    @TableField(value = "goods_name")
    private String goodsName;

    /**
     * 商品库存
     */
    @TableField(value = "goods_inventory")
    private String goodsInventory;

    /**
     * 原始市场价
     */
    @TableField(value = "goods_old_mark_price")
    private String goodsOldMarkPrice;

    /**
     * 原始商城价
     */
    @TableField(value = "goods_old_mall_price")
    private String goodsOldMallPrice;

    /**
     * 调整后市场价
     */
    @TableField(value = "goods_new_mark_price")
    private String goodsNewMarkPrice;

    /**
     * 调整后商城价
     */
    @TableField(value = "goods_new_mall_price")
    private String goodsNewMallPrice;

    /**
     * 失效时间
     */
    @TableField(value = "failure_time")
    private String failureTime;

    /**
     * 生效时间
     */
    @TableField(value = "effective_time")
    private String effectiveTime;

    /**
     * 审核状态（从字典表获取）
     */
    @TableField(value = "check_status")
    private String checkStatus;

    /**
     * 备注
     */
    @TableField(value = "price_desc")
    private String priceDesc;

    /**
     * 调价原因
     */
    @TableField(value = "price_reason")
    private String priceReason;

    /**
     * 审核时间
     */
    @TableField(value = "check_time")
    private String checkTime;

    /**
     * 审核人（从用户表获取）
     */
    @TableField(value = "checker")
    private String checker;

    /**
     * 审核意见
     */
    @TableField(value = "check_idea")
    private String checkIdea;

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
     * process_definition表id
     */
    @TableField(value = "definition_id")
    private String definitionId;

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
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    public String getGoodsInventory() {
        return goodsInventory;
    }

    public void setGoodsInventory(String goodsInventory) {
        this.goodsInventory = goodsInventory;
    }

    public String getGoodsOldMarkPrice() {
        return goodsOldMarkPrice;
    }

    public void setGoodsOldMarkPrice(String goodsOldMarkPrice) {
        this.goodsOldMarkPrice = goodsOldMarkPrice;
    }

    public String getGoodsOldMallPrice() {
        return goodsOldMallPrice;
    }

    public void setGoodsOldMallPrice(String goodsOldMallPrice) {
        this.goodsOldMallPrice = goodsOldMallPrice;
    }

    public String getGoodsNewMarkPrice() {
        return goodsNewMarkPrice;
    }

    public void setGoodsNewMarkPrice(String goodsNewMarkPrice) {
        this.goodsNewMarkPrice = goodsNewMarkPrice;
    }

    public String getGoodsNewMallPrice() {
        return goodsNewMallPrice;
    }

    public void setGoodsNewMallPrice(String goodsNewMallPrice) {
        this.goodsNewMallPrice = goodsNewMallPrice;
    }

    public String getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(String failureTime) {
        this.failureTime = failureTime;
    }

    public String getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(String effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }
    public String getPriceDesc() {
        return priceDesc;
    }

    public void setPriceDesc(String priceDesc) {
        this.priceDesc = priceDesc;
    }
    public String getPriceReason() {
        return priceReason;
    }

    public void setPriceReason(String priceReason) {
        this.priceReason = priceReason;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
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
    public String getDefinitionId() {
        return definitionId;
    }

    public void setDefinitionId(String definitionId) {
        this.definitionId = definitionId;
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
        return "CrmGoodsPrice{" +
            "id=" + id +
            ", goodsId=" + goodsId +
            ", goodsName=" + goodsName +
            ", goodsInventory=" + goodsInventory +
            ", goodsOldMarkPrice=" + goodsOldMarkPrice +
            ", goodsOldMallPrice=" + goodsOldMallPrice +
            ", goodsNewMarkPrice=" + goodsNewMarkPrice +
            ", goodsNewMallPrice=" + goodsNewMallPrice +
            ", failureTime=" + failureTime +
            ", effectiveTime=" + effectiveTime +
            ", checkStatus=" + checkStatus +
            ", priceDesc=" + priceDesc +
            ", priceReason=" + priceReason +
            ", checkTime=" + checkTime +
            ", checker=" + checker +
            ", checkIdea=" + checkIdea +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", createBy=" + createBy +
            ", updateBy=" + updateBy +
            ", definitionId=" + definitionId +
            ", temp2=" + temp2 +
            ", temp3=" + temp3 +
        "}";
    }
}
