/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XCallback.java
 * ClassName: XCallback
 * LastModified: 12/4/17 2:21 PM
 */

package me.xjliao.xjlib.net;

import android.util.Log;

import java.io.IOException;

import me.xjliao.xjlib.BaseApp;
import me.xjliao.xjlib.xutil.XToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class XCallback<T> implements Callback<XResponse<T>> {

    private static final String LOG_TAG = XCallback.class.getSimpleName();

    @Override
    public void onResponse(Call<XResponse<T>> call, Response<XResponse<T>> response) {
        if (response.isSuccessful()) {
            XResponse<T> xResponse = response.body();
            if (xResponse.getCode() == XResponseStatus.SUCCESS.getCode()) {
                onSuccess(xResponse.getCode(), xResponse.getMsg(), xResponse.getObj());
            } else {
                onFailure(xResponse.getCode(), xResponse.getMsg());
            }
        } else {
            try {
                Log.e(LOG_TAG, response.code() + ":" + response.raw().request().url() + " - "
                        + response.errorBody().string());
            } catch (IOException e) {
                Log.e(LOG_TAG, response.code() + ":" + response.raw().request().url());
                e.printStackTrace();
            }

            XResponse failureResponse = new XResponse(response.code(), "请求失败");
            onFailure(failureResponse.getCode(), failureResponse.getMsg());
        }

    }

    @Override
    public void onFailure(Call<XResponse<T>> call, Throwable t) {
        XResponse failureResponse = new XResponse(10000, "请求失败");
        Log.e(LOG_TAG, t.toString());
        onFailure(failureResponse.getCode(), failureResponse.getMsg());
        XToast.showShortMsg(BaseApp.getAppContext(), failureResponse.getMsg());
    }

    public abstract void onSuccess(int code, String msg, T obj);

    public abstract void onFailure(int code, String msg);

}
