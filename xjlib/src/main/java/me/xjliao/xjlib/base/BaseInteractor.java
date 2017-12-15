/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: BaseInteractor.java
 * ClassName: BaseInteractor
 * LastModified: 11/30/17 4:39 PM
 */

package me.xjliao.xjlib.base;

public abstract class BaseInteractor {

    public BaseInteractor() {
        setupComponent();
    }

    public abstract void setupComponent();
}
