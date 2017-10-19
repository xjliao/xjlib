/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: BaseView.java
 * ClassName: BaseView
 * LastModified: 10/11/17 2:36 PM
 */

package me.xjliao.xjlib.base;

/**
 * Created by xjl on 2016/1/14.
 */
public interface BaseView<T> {

    void success(T t);

    void failure(String msg);

}
