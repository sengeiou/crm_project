package com.javasm.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2022-02-21
 */
@TableName("crm_menu")
public class CrmMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @TableField(value = "access_moudle")
    private String accessMoudle;


    @TableField(value = "parent_id")
    private String parentId;

    @TableField(value = "create_time")
    private LocalDateTime createTime;

    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    @TableField(value = "menu_glyphicons")
    private String menuGlyphicons;

    @TableField(value = "menu_url")
    private String menuUrl;

    @TableField(value = "temp2")
    private String temp2;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getAccessMoudle() {
        return accessMoudle;
    }

    public void setAccessMoudle(String menuName) {
        this.accessMoudle = menuName;
    }
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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
    public String getMenuGlyphicons() {
        return menuGlyphicons;
    }

    public void setMenuGlyphicons(String temp) {
        this.menuGlyphicons = temp;
    }
    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String temp1) {
        this.menuUrl = temp1;
    }
    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    @Override
    public String toString() {
        return "CrmMenu{" +
            "id=" + id +
            ", accessMoudle=" + accessMoudle +
            ", parentId=" + parentId +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", menuGlyphicons=" + menuGlyphicons +
            ", menuUrl=" + menuUrl +
            ", temp2=" + temp2 +
        "}";
    }
}
