package com.javasm.crm.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.math.BigDecimal;
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
@TableName("crm_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 产品id
     */
    @TableField(value = "product_id")
    private String productId;

    /**
     * 商品编号
     */
    @TableField(value = "goods_number")
    private String goodsNumber;

    /**
     * 商品名称
     */
    @TableField(value = "goods_name",whereStrategy=FieldStrategy.NOT_EMPTY,condition=SqlCondition.LIKE)
    private String goodsName;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 商品类别
     */
    @TableField(value = "goods_classify",whereStrategy=FieldStrategy.NOT_EMPTY,condition=SqlCondition.LIKE)
    private String goodsClassify;

    /**
     * 采购价
     */
    @TableField(value = "goods_purchase_price")
    private BigDecimal goodsPurchasePrice;

    /**
     * 市场价
     */
    @TableField(value = "goods_market_price")
    private BigDecimal goodsMarketPrice;

    /**
     * 商城价
     */
    @TableField(value = "goods_mall_price")
    private BigDecimal goodsMallPrice;

    /**
     * 最低库存量
     */
    @TableField(value = "min_inventory")
    private String minInventory;

    /**
     * 是否可以采购
     */
    @TableField(value = "goods_purchase_status")
    private String goodsPurchaseStatus;

    /**
     * 一类二类
     */
    @TableField(value = "goods_one_or_two")
    private String goodsOneOrTwo;

    @TableField(value = "goods_color",whereStrategy=FieldStrategy.NOT_EMPTY,condition=SqlCondition.LIKE)
    private String goodsColor;

    @TableField(value = "brand_id",whereStrategy=FieldStrategy.NOT_EMPTY,condition=SqlCondition.LIKE)
    private String brandId;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    /**
     * 型号
     */
    @TableField(value = "goods_type",whereStrategy=FieldStrategy.NOT_EMPTY,condition=SqlCondition.LIKE)
    private String goodsType;

    /**
     * 供应商id
     */
    @TableField(value = "supplier_id")
    private String supplierId;

    /**
     * 商品库存
     */
    @TableField(value = "goods_inventory")
    private String goodsInventory;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @TableField(value = "create_by")
    private String createBy;

    @TableField(value = "update_by")
    private String updateBy;

    @TableField(value = "goods_id")
    private String goodsId;

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
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }
    public String getGoodsClassify() {
        return goodsClassify;
    }

    public void setGoodsClassify(String goodsClassify) {
        this.goodsClassify = goodsClassify;
    }
    public BigDecimal getGoodsPurchasePrice() {
        return goodsPurchasePrice;
    }

    public void setGoodsPurchasePrice(BigDecimal goodsPurchasePrice) {
        this.goodsPurchasePrice = goodsPurchasePrice;
    }
    public BigDecimal getGoodsMarketPrice() {
        return goodsMarketPrice;
    }

    public void setGoodsMarketPrice(BigDecimal goodsMarketPrice) {
        this.goodsMarketPrice = goodsMarketPrice;
    }
    public BigDecimal getGoodsMallPrice() {
        return goodsMallPrice;
    }

    public void setGoodsMallPrice(BigDecimal goodsMallPrice) {
        this.goodsMallPrice = goodsMallPrice;
    }
    public String getMinInventory() {
        return minInventory;
    }

    public void setMinInventory(String minInventory) {
        this.minInventory = minInventory;
    }
    public String getGoodsPurchaseStatus() {
        return goodsPurchaseStatus;
    }

    public void setGoodsPurchaseStatus(String goodsPurchaseStatus) {
        this.goodsPurchaseStatus = goodsPurchaseStatus;
    }
    public String getGoodsOneOrTwo() {
        return goodsOneOrTwo;
    }

    public void setGoodsOneOrTwo(String goodsOneOrTwo) {
        this.goodsOneOrTwo = goodsOneOrTwo;
    }
    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor;
    }
    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }
    public String getGoodsInventory() {
        return goodsInventory;
    }

    public void setGoodsInventory(String goodsInventory) {
        this.goodsInventory = goodsInventory;
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

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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
        return "Goods{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", goodsNumber='" + goodsNumber + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsClassify='" + goodsClassify + '\'' +
                ", goodsPurchasePrice=" + goodsPurchasePrice +
                ", goodsMarketPrice=" + goodsMarketPrice +
                ", goodsMallPrice=" + goodsMallPrice +
                ", minInventory='" + minInventory + '\'' +
                ", goodsPurchaseStatus='" + goodsPurchaseStatus + '\'' +
                ", goodsOneOrTwo='" + goodsOneOrTwo + '\'' +
                ", goodsColor='" + goodsColor + '\'' +
                ", brand_Id='" + brandId + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", goodsInventory='" + goodsInventory + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", blank1='" + goodsId + '\'' +
                ", blank2='" + blank2 + '\'' +
                ", blank3='" + blank3 + '\'' +
                '}';
    }
}
