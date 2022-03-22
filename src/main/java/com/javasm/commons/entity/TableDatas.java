package com.javasm.commons.entity;

import com.github.pagehelper.Page;

import java.util.List;

public class TableDatas {
    private List list;
    private Integer pageNum;
    private Integer pageSize;
    private Integer total;

    public TableDatas(List list, Integer pageNum, Integer pageSize, Integer total) {
        this.list = list;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }

    public TableDatas(List list) {

        if(list instanceof Page){
            Page p =(Page) list;
            this.list = list;
            this.pageNum = p.getPageNum();
            this.pageSize = p.getPageSize();
            this.total = (int)p.getTotal();
        }
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
