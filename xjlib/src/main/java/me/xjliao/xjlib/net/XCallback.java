/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XCallback.java
 * ClassName: XCallback
 * LastModified: 10/11/17 11:36 AM
 */

package me.xjliao.xjlib.net;

import android.util.Log;

import java.io.IOException;

import me.xjliao.xjlib.BaseApp;
import me.xjliao.xjlib.BuildConfig;
import me.xjliao.xjlib.xutil.XToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xjliao on 2016/7/30.
 */
public abstract class XCallback<T> implements Callback<T> {

    private static final String LOG_TAG = XCallback.class.getSimpleName();

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onResponse(response.body());
        } else {
            if (BuildConfig.LOG_DEBUG) {
                try {
                    Log.e(LOG_TAG, response.code() + ":" + response.raw().request().url() + " - "
                            + response.errorBody().string());
                } catch (IOException e) {
                    Log.e(LOG_TAG, response.code() + ":" + response.raw().request().url());
                    e.printStackTrace();
                }
            }

            onFailure("请求失败");
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (BuildConfig.LOG_DEBUG) {
            Log.e(LOG_TAG, t.toString());
        }

        onFailure("请求失败");
    }
    public abstract void onResponse(T response);

    public void onFailure(String msg) {
        XToast.showShortMsg(BaseApp.getAppContext(), msg);
    }
}
