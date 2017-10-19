/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: app
 * FileName: MainActivity.java
 * ClassName: MainActivity
 * LastModified: 10/18/17 5:34 PM
 */

package me.xjliao.xjl;

import android.view.View;

import me.xjliao.xjlib.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    public void setupComponent() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initAdapters() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void destroyView() {

    }

    public void back(View view) {
        finish();

    }
}
