/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XContent.java
 * ClassName: XContent
 * LastModified: 10/11/17 2:36 PM
 */

package me.xjliao.xjlib.net;

import com.google.gson.annotations.SerializedName;

public class XContent<T> {

    @SerializedName("Data")
    private T data;

    @SerializedName("Paging")
    private Pagination pagination;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}