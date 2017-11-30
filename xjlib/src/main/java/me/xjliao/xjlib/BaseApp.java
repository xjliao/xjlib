/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: BaseApp.java
 * ClassName: BaseApp
 * LastModified: 10/19/17 8:50 AM
 */

package me.xjliao.xjlib;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;
import com.squareup.picasso.Picasso;

/**
 * @author xjliao
 */
public class BaseApp extends Application {

    public static Context applicationContext;

    private static BaseApp instance;

    private static Bus EVENT_BUS;

    public static Context getAppContext() {
        return applicationContext;
    }

    public static Bus getEventBus() {
        return EVENT_BUS;
    }

    public static BaseApp getInstance() {
        return instance;
    }

    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        instance = this;
        EVENT_BUS = new Bus(ThreadEnforcer.ANY);
        BaseApp.getEventBus().register(this);

        if (BuildConfig.DEBUG) {
            Picasso.with(this).setIndicatorsEnabled(true);
        }
    }

    public static void exit() {
        System.exit(0);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}