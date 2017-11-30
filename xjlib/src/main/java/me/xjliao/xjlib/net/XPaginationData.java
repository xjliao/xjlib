/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XPaginationData.java
 * ClassName: XPaginationData
 * LastModified: 11/29/17 9:44 AM
 */

package me.xjliao.xjlib.net;

public class XPaginationData<T> {

    private T data;

    private XPagination pagination;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public XPagination getPagination() {
        return pagination;
    }

    public void setPagination(XPagination pagination) {
        this.pagination = pagination;
    }
}