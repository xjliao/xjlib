/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XPagination.java
 * ClassName: XPagination
 * LastModified: 11/30/17 4:39 PM
 */

package me.xjliao.xjlib.net;

public class XPagination {

    private int currentPageIndex;

    private int endRecordIndex;

    private int pageSize;

    private int startRecordIndex;

    private int totalItemCount;

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
