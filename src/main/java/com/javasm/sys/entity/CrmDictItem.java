package com.javasm.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;



@TableName("crm_dict_item")
public class CrmDictItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 字典项编码
     */
    @TableId(value = "dict_id", type = IdType.ASSIGN_ID)
    private String dictId;

    /**
     * 字典标签
     */
    @TableField(value = "dict_name")
    private String dictName;

    /**
     * 字典排序
     */
    @TableField(value = "dict_sort")
    private String dictSort;

    /**
     * 字典键值
     */
    @TableField(value = "dict_value")
    private String dictValue;

    /**
     * 字典项所属类型id
     */
    @TableField(value = "dict_type")
    private String dictType;

    /**
     * 备注
     */
    @TableField(value = "dict_describe")
    private String dictDescribe;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 状态
     */
    @TableField(value = "dict_status")
    private String dictStatus;

    @TableField(value = "type_id")
    private String typeId;

    @TableField(value = "dict_exist")
    private String dictExist;

    @TableField(value = "dict_temp2")
    private String dictTemp2;

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }
    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }
    public String getDictSort() {
        return dictSort;
    }

    public void setDictSort(String dictSort) {
        this.dictSort = dictSort;
    }
    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }
    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }
    public String getDictDescribe() {
        return dictDescribe;
    }

    public void setDictDescribe(String describe) {
        this.dictDescribe = describe;
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
    public String getDictStatus() {
        return dictStatus;
    }

    public void setDictStatus(String dictStatus) {
        this.dictStatus = dictStatus;
    }
    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String dictTypeId) {
        this.typeId = dictTypeId;
    }
    public String getDictExist() {
        return dictExist;
    }

    public void setDictExist(String dictTemp1) {
        this.dictExist = dictTemp1;
    }
    public String getDictTemp2() {
        return dictTemp2;
    }

    public void setDictTemp2(String dictTemp2) {
        this.dictTemp2 = dictTemp2;
    }

    @Override
    public String toString() {
        return "CrmDictItem{" +
            "dictId=" + dictId +
            ", dictName=" + dictName +
            ", dictSort=" + dictSort +
            ", dictValue=" + dictValue +
            ", dictType=" + dictType +
            ", dictDescribe=" + dictDescribe +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", dictStatus=" + dictStatus +
            ", typeId=" + typeId +
            ", dictExist=" + dictExist +
            ", dictTemp2=" + dictTemp2 +
        "}";
    }
}
