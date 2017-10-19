/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: AppComponent.java
 * ClassName: AppComponent
 * LastModified: 10/11/17 2:36 PM
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
