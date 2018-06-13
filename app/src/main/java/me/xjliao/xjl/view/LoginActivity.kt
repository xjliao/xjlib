/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: app
 * FileName: LoginActivity.java
 * ClassName: LoginActivity
 * LastModified: 11/30/17 4:39 PM
 */

package me.xjliao.xjl.view

import android.content.Intent
import android.view.View

import butterknife.OnClick
import me.xjliao.xjl.R
import me.xjliao.xjlib.base.BaseActivity
import me.xjliao.xjlib.base.BaseXWebActivity
import me.xjliao.xjlib.common.Constants

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity() {


    override fun setupComponent() {

    }

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun initViews() {

    }

    override fun initListeners() {

    }

    override fun initAdapters() {

    }

    override fun initData() {

    }

    override fun destroyView() {

    }

    @OnClick(R.id.email_sign_in_button)
    fun signin(view: View) {
        startActivityX(MainActivity::class.java)
    }

    @OnClick(R.id.t1)
    fun t1(view: View) {
        val intent = Intent()
        intent.putExtra(Constants.URL, "www.baidu.com")
        startActivityV(null, BaseXWebActivity::class.java)
    }

    @OnClick(R.id.t2)
    fun t2(view: View) {
        startActivityH(null, MainActivity::class.java)
    }
}

