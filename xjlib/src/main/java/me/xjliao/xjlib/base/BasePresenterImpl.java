/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: BasePresenterImpl.java
 * ClassName: BasePresenterImpl
 * LastModified: 10/19/17 8:50 AM
 */

package me.xjliao.xjlib.base;

/**
 * Created by xjl on 2016/1/14.
 */
public abstract class BasePresenterImpl implements BasePresenter {

    public BaseView view;

    public BasePresenterImpl() {
        setupComponent();
    }

    public BasePresenterImpl(BaseView view) {
        this();
        this.view = view;
    }

    public abstract void setupComponent();

    public void onDestroy() {
        view = null;
    }
}