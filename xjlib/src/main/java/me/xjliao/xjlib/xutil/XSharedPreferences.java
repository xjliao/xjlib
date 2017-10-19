/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XSharedPreferences.java
 * ClassName: XSharedPreferences
 * LastModified: 10/11/17 4:44 PM
 */

package me.xjliao.xjlib.xutil;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import me.xjliao.xjlib.BuildConfig;

public class XSharedPreferences {

    public static String mFileName = BuildConfig.APPLICATION_ID;

    private static volatile XSharedPreferences INSTANCE;

    private SharedPreferences mSp;

    private int mMode = Context.MODE_MULTI_PROCESS;

    // 传递过来上下文对象
    private XSharedPreferences() {

    }

    public XSharedPreferences(Context context) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(mFileName, mMode);
        }
    }

    // thread safe and performance promote
    public static XSharedPreferences getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (XSharedPreferences.class) {
                if (INSTANCE == null) {
                    INSTANCE = new XSharedPreferences(context);
                }
            }
        }
        return INSTANCE;
    }

    public static String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }

    public void save(String key, String value) {
        // 进行保存
        Editor editor = mSp.edit();
        editor.putString(key, value);
        editor.commit();

    }

    public void save(String key, int value) {
        Editor editor = mSp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void save(String key, long value) {
        Editor editor = mSp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public void save(String key, boolean value) {
        Editor editor = mSp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    // 获取某个字段
    public String getString(String key) {
        String data = mSp.getString(key, null);
        return data;
    }

    public int getInt(String key) {
        int data = mSp.getInt(key, -1);
        return data;
    }

    public long getLong(String key) {
        long data = mSp.getLong(key, -1L);
        return data;
    }

    public Boolean getBoolean(String key) {
        Boolean data = mSp.getBoolean(key, false);
        return data;
    }

    public Boolean getIsFirstUse(String key) {
        Boolean data = mSp.getBoolean(key, true);
        return data;
    }

    // 删除
    public void remove(String key) {
        Editor editor = mSp.edit();
        editor.remove(key);
        editor.commit();
    }

    public SharedPreferences getSp() {
        return mSp;
    }

    public void setSp(SharedPreferences sp) {
        mSp = sp;
    }

    public int getMode() {
        return mMode;
    }

    public void setMode(int mode) {
        mMode = mode;
    }

}
