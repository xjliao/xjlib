/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: BaseItem.java
 * ClassName: BaseItem
 * LastModified: 10/11/17 2:31 PM
 */

package me.xjliao.xjlib.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xjliao on 2016/10/11.
 */
public class BaseItem {

    @SerializedName("fid")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
