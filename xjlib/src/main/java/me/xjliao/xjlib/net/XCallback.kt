/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XCallback.java
 * ClassName: XCallback
 * LastModified: 12/4/17 2:21 PM
 */

package me.xjliao.xjlib.net

import android.util.Log
import me.xjliao.xjlib.BaseApp
import me.xjliao.xjlib.xutil.XToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

abstract class XCallback<T> : Callback<XResponse<T>> {

    override fun onResponse(call: Call<XResponse<T>>, response: Response<XResponse<T>>) {
        if (response.isSuccessful) {
            val xResponse = response.body()
            if (xResponse!!.code == XResponseStatus.SUCCESS.code) {
                onSuccess(xResponse.code, xResponse.msg, xResponse.obj)
            } else {
                onFailure(xResponse.code, xResponse.msg)
            }
        } else {
            try {
                Log.e(LOG_TAG, response.code().toString() + ":" + response.raw().request().url() + " - "
                        + response.errorBody()!!.string())
            } catch (e: IOException) {
                Log.e(LOG_TAG, response.code().toString() + ":" + response.raw().request().url())
                e.printStackTrace()
            }

            val failureResponse = XResponse<T>(response.code(), "请求失败")
            onFailure(failureResponse.code, failureResponse.msg)
        }

    }

    override fun onFailure(call: Call<XResponse<T>>, t: Throwable) {
        val failureResponse = XResponse<T>(10000, "请求失败")
        Log.e(LOG_TAG, t.toString())
        onFailure(failureResponse.code, failureResponse.msg)
        XToast.showShortMsg(BaseApp.appContext, failureResponse.msg)
    }

    abstract fun onSuccess(code: Int?, msg: String?, obj: T?)

    abstract fun onFailure(code: Int?, msg: String?)

    companion object {

        private val LOG_TAG = XCallback::class.java.simpleName
    }

}
