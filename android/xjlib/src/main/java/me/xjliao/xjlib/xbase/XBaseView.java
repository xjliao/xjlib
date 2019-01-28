/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: BaseView.java
 * ClassName: BaseView
 * LastModified: 12/14/17 11:29 AM
 */

package me.xjliao.xjlib.xbase;

import me.xjliao.xjlib.base.BaseView;

/**
 * Created by xjl on 2016/1/14.
 */
public interface XBaseView<T> extends BaseView {

	void success(int code, String msg, String tag, T t);

	void failure(int code, String msg, String tag);

}
