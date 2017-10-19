/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: app
 * FileName: LoginActivity.java
 * ClassName: LoginActivity
 * LastModified: 10/18/17 5:34 PM
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
        startActivityV(null, MainActivity.class);
    }
}

