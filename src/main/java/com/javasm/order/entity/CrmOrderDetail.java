package com.javasm.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.javasm.crm.entity.Goods;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@TableName("crm_order_detail")
public class CrmOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 订单编号,外键，与order_info表相关
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 商品串码，出库生成，唯一
     */
    @TableField(value = "prod_code")
    private String prodCode;

    /**
     * 购买数量
     */
    @TableField(value = "prod_num")
    private String prodNum;

    /**
     * 商品编号
     */
    @TableField(value = "goods_number")
    private String goodsNumber;

    /**
     * 下单日期
     */
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

    @TableField(value = "remarks")
    private String remarks;
    /**
     * 订单商品金额
     */
    @TableField(value = "temp1")
    private String temp1;
    /**
     * 是否退换货，0 未退换  1  已退换
     */
    @TableField(value = "temp2")
    private String temp2;

    @TableField(exist = false)
    private Goods goods;

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

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }
    public String getProdNum() {
        return prodNum;
    }

    public void setProdNum(String prodNum) {
        this.prodNum = prodNum;
    }
    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
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
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getTemp1() {
        if (this.goods!=null){
            if (this.prodNum!=null && this.goods.getGoodsMarketPrice()!=null){
                BigDecimal num = new BigDecimal(this.prodNum);
                this.temp1 = String.valueOf(this.goods.getGoodsMarketPrice().multiply(num));
            }
        }
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

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "CrmOrderDetail{" +
                "id='" + id + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", prodCode='" + prodCode + '\'' +
                ", prodNum='" + prodNum + '\'' +
                ", goodsNumber='" + goodsNumber + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", remarks='" + remarks + '\'' +
                ", temp1='" + temp1 + '\'' +
                ", temp2='" + temp2 + '\'' +
                ", goods=" + goods +
                '}';
    }
}
