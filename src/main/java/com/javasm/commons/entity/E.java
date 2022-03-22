package com.javasm.commons.entity;

public enum E {
    SUC(200,"suc"),
    ERROR(500,"error"),

    VALIDATE_ERROR(50001,"验证码错误"),
    UNAME_ERROR(50002,"用户名不存在"),
    BY_ZERO(50003,"除数为0"),
    NO_LOGIN(50004,"用户未登录"),
    DATA_RELATED(50005,"数据被关联" ),
    PHONE_ERROR(50006,"手机号不存在" ),
    USER_OPERTION(50007,"该用户不具备该权限"),
    GOODS_INVENTORY_ERROR(60000,"商品库存不足"),
    ROLE_USERING(10001,"该角色正被使用无法删除"),
    DEPARTMENT_USERING(10002,"该部门正被使用无法删除"),
    PERMISSION_CHILD(10003,"该权限下有子类,无法删除"),
    USERNAME_OE_PASS(10004,"用户名或密码错误");

    private Integer code;
    private String msg;

    E(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
