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

import me.xjliao.xjlib.xutil.GlobalUtil;
import me.xjliao.xjlib.xutil.XToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class XXCallback<T extends XXResponse> implements Callback<T> {

    public static final String LOG_TAG = XXCallback.class.getSimpleName();

    public static final String SUCCESS= "0:0001";

    public static final String  FAILURE = "EORROR";

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            XXResponse xxResponse = response.body();
            if (!SUCCESS.equals(xxResponse.getMsg())) {
                onSuccess(xxResponse.getMsg(),  xxResponse.getMsgText(), xxResponse);
            } else {
                onFailure(xxResponse.getMsg(), xxResponse.getMsgText());
            }
        } else {
            try {
                Log.e(LOG_TAG, response.code() + ":" + response.raw().request().url() + " - "
                        + response.errorBody().string());
            } catch (IOException e) {
                Log.e(LOG_TAG, response.code() + ":" + response.raw().request().url());
                e.printStackTrace();
            }

            XXResponse failureResponse = new XXResponse(FAILURE, "请求失败");
            onFailure(failureResponse.getMsg(),  failureResponse.getMsgText());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        XXResponse failureResponse = new XXResponse(FAILURE, "请求失败;");
        Log.e(LOG_TAG, t.toString());
        onFailure(failureResponse.getMsg(), failureResponse.getMsgText());
        XToast.showShortMsg(GlobalUtil.CONTEXT, failureResponse.getMsgText());
    }

    public abstract void onSuccess(String msg,  String msgText, Object data);

    public abstract  void onFailure(String msg,  String msgText);

}
