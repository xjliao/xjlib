/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: BasePresenter.java
 * ClassName: BasePresenter
 * LastModified: 12/14/17 4:47 PM
 */

package me.xjliao.xjlib.xbase;

/**
 * Created by xjl on 2016/1/14.
 */
public abstract class XBasePresenter<T extends XBaseView> {

    public T view;

    public XBasePresenter() {
        setupComponent();
    }

    public XBasePresenter(T view) {
        this();
        this.view = view;
    }

    public abstract void setupComponent();

    public void onDestroy() {
        if (null != view) {
            view = null;
        }
    }
}