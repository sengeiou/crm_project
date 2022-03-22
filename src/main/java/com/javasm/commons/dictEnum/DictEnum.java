package com.javasm.commons.dictEnum;

public enum DictEnum {
    STATUS("common_status","赠品状态"),
    SEX("common_sex","性别"),
    POST("common_post","职务"),
    CHECK_STATUS("commons_check_status","审核状态"),
    ORDER_TYPE("order_type","订单类型"),
    PAY_TYPE("pay_type","支付方式"),
    BUSINESS_TYPE("business_type","业务类型"),
    ORDER_STATUS("order_status","订单状态"),
    ORDER_ACTION("order_action","订单动作"),
    GOODS_COLOR("goods_color","商品颜色"),
    PROD_CHANGE_TYPE("prod_change_type","换货名称"),
    APPROVAL_RESULT("approval_result","审批结果"),
    GOODS_TO_GIFT_CHECK_NAME("goods_to_gift","商品转赠品审批"),
    GIFT_TO_GOODS_CHECK_NAME("gift_to_goods","赠品转商品审批"),
    APPEAL_CHECK("crm-appeal","售后退换货审批")
    ;
    private String dictCode;
    private String dictName;

    DictEnum(String dictCode, String dictName) {
        this.dictCode = dictCode;
        this.dictName = dictName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
}
