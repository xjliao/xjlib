/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XContent.java
 * ClassName: XContent
 * LastModified: 10/11/17 2:36 PM
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