package com.javasm.sup.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.javasm.crm.entity.Goods;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@TableName("crm_goods_gift")
public class CrmGoodsGift implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField(value = "gift_name")
    private String giftName;

    @TableField(value = "gift_type")
    private String giftType;

    @TableField(value = "gift_classify")
    private String giftClassify;

    @TableField(value = "gift_price")
    private String giftPrice;

    @TableField(value = "gift_cost")
    private String giftCost;

    @TableField(value = "gift_department")
    private String giftDepartment;

    @TableField(value = "gift_min_inventory")
    private String giftMinInventory;

    @TableField(value = "gift_color")
    private String giftColor;

    @TableField(value = "gift_brand")
    private String giftBrand;

    @TableField(value = "gift_inventory")
    private String giftInventory;

    @TableField(value = "gift_status")
    private String giftStatus;

    @TableField(value = "goods_id")
    private String goodsId;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @TableField(value = "create_by")
    private String createBy;

    @TableField(value = "update_by")
    private String updateBy;

    @TableField(value = "temp1")
    private String temp1;

    @TableField(value = "temp2")
    private String temp2;

    @TableField(value = "temp3")
    private String temp3;

    @TableField(exist = false)
    private Goods goods;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType;
    }
    public String getGiftClassify() {
        return giftClassify;
    }

    public void setGiftClassify(String giftClassify) {
        this.giftClassify = giftClassify;
    }
    public String getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(String giftPrice) {
        this.giftPrice = giftPrice;
    }
    public String getGiftCost() {
        return giftCost;
    }

    public void setGiftCost(String giftCost) {
        this.giftCost = giftCost;
    }
    public String getGiftDepartment() {
        return giftDepartment;
    }

    public void setGiftDepartment(String giftDepartment) {
        this.giftDepartment = giftDepartment;
    }
    public String getGiftMinInventory() {
        return giftMinInventory;
    }

    public void setGiftMinInventory(String giftMinInventory) {
        this.giftMinInventory = giftMinInventory;
    }
    public String getGiftColor() {
        return giftColor;
    }

    public void setGiftColor(String giftColor) {
        this.giftColor = giftColor;
    }
    public String getGiftBrand() {
        return giftBrand;
    }

    public void setGiftBrand(String giftBrand) {
        this.giftBrand = giftBrand;
    }
    public String getGiftInventory() {
        return giftInventory;
    }

    public void setGiftInventory(String giftInventory) {
        this.giftInventory = giftInventory;
    }
    public String getGiftStatus() {
        return giftStatus;
    }

    public void setGiftStatus(String giftStatus) {
        this.giftStatus = giftStatus;
    }
    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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
        return "CrmGoodsGift{" +
            "id=" + id +
            ", giftType=" + giftType +
            ", giftClassify=" + giftClassify +
            ", giftPrice=" + giftPrice +
            ", giftCost=" + giftCost +
            ", giftDepartment=" + giftDepartment +
            ", giftMinInventory=" + giftMinInventory +
            ", giftColor=" + giftColor +
            ", giftBrand=" + giftBrand +
            ", giftInventory=" + giftInventory +
            ", giftStatus=" + giftStatus +
            ", goodsId=" + goodsId +
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
