package com.javasm.crm.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@TableName("crm_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 档案编号
     */

    @TableField(value = "prod_number",whereStrategy=FieldStrategy.NOT_EMPTY,condition=SqlCondition.LIKE)
    private String prodNumber;

    /**
     * 基础库型号
     */
    @TableField(value = "prod_type",whereStrategy=FieldStrategy.NOT_EMPTY,condition=SqlCondition.LIKE)
    private String prodType;

    /**
     * 基础库分类
     */
    @TableField(value = "prod_classify",whereStrategy=FieldStrategy.NOT_EMPTY,condition=SqlCondition.LIKE)
    private String prodClassify;

    /**
     * 基础库品牌
     */
    @TableField(value = "brand_id",whereStrategy=FieldStrategy.NOT_EMPTY,condition=SqlCondition.LIKE)
    private String brandId;

    /**
     * 基础库颜色
     */
    @TableField(value = "prod_color",whereStrategy=FieldStrategy.NOT_EMPTY,condition=SqlCondition.LIKE)
    private String prodColor;

    /**
     * 采购模式
     */
    @TableField(value = "prod_purchasing_pattern")
    private String prodPurchasingPattern;

    /**
     * 基础库铺货
     */
    @TableField(value = "prod_distribution ")
    private String prodDistribution ;

    /**
     * 基础库备机
     */
    @TableField(value = "prod_standby")
    private String prodStandby;

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
    public String getProdNumber() {
        return prodNumber;
    }

    public void setProdNumber(String prodNumber) {
        this.prodNumber = prodNumber;
    }
    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }
    public String getProdClassify() {
        return prodClassify;
    }

    public void setProdClassify(String prodClassify) {
        this.prodClassify = prodClassify;
    }
    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
    public String getProdColor() {
        return prodColor;
    }

    public void setProdColor(String prodColor) {
        this.prodColor = prodColor;
    }
    public String getProdPurchasingPattern() {
        return prodPurchasingPattern;
    }

    public void setProdPurchasingPattern(String prodPurchasingPattern) {
        this.prodPurchasingPattern = prodPurchasingPattern;
    }

    public String getProdDistribution() {
        return prodDistribution;
    }

    public void setProdDistribution(String prodDistribution) {
        this.prodDistribution = prodDistribution;
    }

    public String getProdStandby() {
        return prodStandby;
    }

    public void setProdStandby(String prodStandby) {
        this.prodStandby = prodStandby;
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

    @Override
    public String toString() {
        return "Product{" +
            "id=" + id +
            ", prodNumber=" + prodNumber +
            ", prodType=" + prodType +
            ", prodClassify=" + prodClassify +
            ", brandId=" + brandId +
            ", prodColor=" + prodColor +
            ", prodPurchasingPattern=" + prodPurchasingPattern +
            ", prodDiseribution=" + prodDistribution +
            ", prodStandby=" + prodStandby +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", createBy=" + createBy +
            ", updateBy=" + updateBy +
            ", blank1=" + blank1 +
            ", blank2=" + blank2 +
            ", blank3=" + blank3 +
        "}";
    }
}
