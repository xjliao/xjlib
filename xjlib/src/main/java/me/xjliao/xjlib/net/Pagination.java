/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: Pagination.java
 * ClassName: Pagination
 * LastModified: 10/11/17 11:36 AM
 */

package me.xjliao.xjlib.net;

import com.google.gson.annotations.SerializedName;

public class Pagination {

    @SerializedName("CurrentPageIndex")
    private int currentPageIndex;

    @SerializedName("EndRecordIndex")
    private int endRecordIndex;

    @SerializedName("PageSize")
    private int pageSize;

    @SerializedName("StartRecordIndex")
    private int startRecordIndex;

    @SerializedName("TotalItemCount")
    private int totalItemCount;

    @SerializedName("TotalPageCount")
    private int totalPageCount;

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(int currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }

    public int getEndRecordIndex() {
        return endRecordIndex;
    }

    public void setEndRecordIndex(int endRecordIndex) {
        this.endRecordIndex = endRecordIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRecordIndex() {
        return startRecordIndex;
    }

    public void setStartRecordIndex(int startRecordIndex) {
        this.startRecordIndex = startRecordIndex;
    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }
}
