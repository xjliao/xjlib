/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: AppModule.java
 * ClassName: AppModule
 * LastModified: 10/19/17 8:50 AM
 */

package me.xjliao.xjlib.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.xjliao.xjlib.BaseApp;
import me.xjliao.xjlib.xutil.XSharedPreferences;

/**
 * Created by xjl on 2016/1/20.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    public XSharedPreferences provideSharedPreferences() {
        return new XSharedPreferences(BaseApp.getAppContext());
    }

}
