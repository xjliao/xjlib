/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: OnResponseListener.java
 * ClassName: OnResponseListener
 * LastModified: 12/14/17 11:22 AM
 */

package me.xjliao.xjlib.listener;

import me.xjliao.xjlib.net.XResponse;

/**
 * Created by xjl on 2016/1/14.
 */
public interface OnResponseListener<T> {

    void onSuccess(String tag, T t);

    void onFailure(String tag, XResponse failureResponse);

}
