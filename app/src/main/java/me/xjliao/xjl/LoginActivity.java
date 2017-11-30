/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: app
 * FileName: LoginActivity.java
 * ClassName: LoginActivity
 * LastModified: 11/30/17 4:07 PM
 */

package me.xjliao.xjl;

import android.view.View;

import butterknife.OnClick;
import me.xjliao.xjlib.base.BaseActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {


    @Override
    public void setupComponent() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
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

    @OnClick(R.id.email_sign_in_button)
    public void signin(View view) {
        startActivityX(MainActivity.class);
    }

    @OnClick(R.id.t1)
    public void t1(View view) {
        startActivityV(null, MainActivity.class);
    }

    @OnClick(R.id.t2)
    public void t2(View view) {
        startActivityH(null, MainActivity.class);
    }
}

