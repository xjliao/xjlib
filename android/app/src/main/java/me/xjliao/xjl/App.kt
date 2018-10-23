package me.xjliao.xjl

import android.app.Application
import android.content.Context
import android.os.SystemClock
import android.support.multidex.MultiDex
import java.util.concurrent.TimeUnit

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3))
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}