/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: BaseInteractor.java
 * ClassName: BaseInteractor
 * LastModified: 10/19/17 8:50 AM
 */

package me.xjliao.xjlib.base;

public abstract class BaseInteractor {

    public BaseInteractor() {
        setupComponent();
    }

    public abstract void setupComponent();
}
