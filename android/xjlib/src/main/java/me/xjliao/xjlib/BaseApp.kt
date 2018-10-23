/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: BaseApp.java
 * ClassName: BaseApp
 * LastModified: 11/30/17 4:39 PM
 */

package me.xjliao.xjlib

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex

import com.squareup.otto.Bus
import com.squareup.otto.ThreadEnforcer
import com.squareup.picasso.Picasso

/**
 * @author xjliao
 */
open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        instance = this
        eventBus = Bus(ThreadEnforcer.ANY)
        BaseApp.eventBus!!.register(this)

        if (BuildConfig.DEBUG) {
            Picasso.with(this).setIndicatorsEnabled(true)
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {

        var appContext: Context? = null

        var instance: BaseApp? = null
            private set

        var eventBus: Bus? = null
            private set

        fun exit() {
            System.exit(0)
        }
    }

}