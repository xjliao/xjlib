/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: AppModule.java
 * ClassName: AppModule
 * LastModified: 12/15/17 1:47 PM
 */

package me.xjliao.xjlib.di;

import android.content.Context;

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
    public Context providerContext() {
        return BaseApp.getAppContext();
    }

    @Provides
    @Singleton
    public XSharedPreferences provideSharedPreferences() {
        return new XSharedPreferences(BaseApp.getAppContext());
    }

}
