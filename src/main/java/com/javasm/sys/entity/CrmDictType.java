package com.javasm.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;




@TableName("crm_dict_type")
public class CrmDictType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典主键
     */
    @TableId(value = "dict_type_id", type = IdType.ASSIGN_ID)
    private String dictTypeId;

    /**
     * 字典编码
     */
    @TableField(value = "dict_type_code")
    private String dictTypeCode;

    /**
     * 字典类型名称
     */
    @TableField(value = "dict_type_name")
    private String dictTypeName;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 字典状态 0正常 1停用
     */
    @TableField(value = "dict_type_status")
    private Integer dictTypeStatus;

    /**
     * 备注
     */
    @TableField(value = "dict_type_describe")
    private String dictTypeDescribe;

    @TableField(value = "update_time")
    private String updateTime;

    @TableField(value = "dict_type_exist")
    private String dictTypeExist;

    public String getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(String dictTypeId) {
        this.dictTypeId = dictTypeId;
    }
    public String getDictTypeCode() {
        return dictTypeCode;
    }

    public void setDictTypeCode(String dictTypeCode) {
        this.dictTypeCode = dictTypeCode;
    }
    public String getDictTypeName() {
        return dictTypeName;
    }

    public void setDictTypeName(String dictTypeName) {
        this.dictTypeName = dictTypeName;
    }
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public Integer getDictTypeStatus() {
        return dictTypeStatus;
    }

    public void setDictTypeStatus(Integer dictTypeStatus) {
        this.dictTypeStatus = dictTypeStatus;
    }
    public String getDictTypeDescribe() {
        return dictTypeDescribe;
    }

    public void setDictTypeDescribe(String describe) {
        this.dictTypeDescribe = describe;
    }
    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String dictTypeTemp) {
        this.updateTime = dictTypeTemp;
    }
    public String getDictTypeExist() {
        return dictTypeExist;
    }

    public void setDictTypeExist(String dictTypeTemp1) {
        this.dictTypeExist = dictTypeTemp1;
    }

    @Override
    public String toString() {
        return "CrmDictType{" +
            "dictTypeId=" + dictTypeId +
            ", dictTypeCode=" + dictTypeCode +
            ", dictTypeName=" + dictTypeName +
            ", createBy=" + createBy +
            ", createTime=" + createTime +
            ", dictTypeStatus=" + dictTypeStatus +
            ", dictTypeDescribe=" + dictTypeDescribe +
            ", updateTime=" + updateTime +
            ", dictTypeExist=" + dictTypeExist +
        "}";
    }
}
