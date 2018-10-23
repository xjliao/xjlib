/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: AppComponent.java
 * ClassName: AppComponent
 * LastModified: 11/30/17 4:39 PM
 */

package me.xjliao.xjlib.di;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by xjl on 2016/1/20.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

}
