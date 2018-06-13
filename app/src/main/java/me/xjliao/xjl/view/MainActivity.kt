/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: app
 * FileName: MainActivity.java
 * ClassName: MainActivity
 * LastModified: 11/30/17 4:39 PM
 */

package me.xjliao.xjl.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import me.xjliao.xjl.R
import me.xjliao.xjlib.auth.*
import me.xjliao.xjlib.base.BaseActivity
import me.xjliao.xjlib.common.Constants
import me.xjliao.xjlib.ext.showToast

class MainActivity : BaseActivity() {

    private lateinit var authSharedPreferences: SharedPreferences

    override fun setupComponent() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authSharedPreferences = getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE)
        val useFingerprintPreference = authSharedPreferences
                .getBoolean(getString(R.string.use_fingerprint_to_authenticate_key), false)
        if (useFingerprintPreference) {
            var intent = Intent(this, AuthenticationActivity::class.java)
            intent.putExtra(AUTH_USERNAME, "xjliao")
            intent.putExtra(AUTH_AVATAR, "BAIDU.COM")
            startActivityForResult(intent, AUTH_REQUEST_CODE)
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initViews() {

    }

    override fun initListeners() {
        auth_settings_button.setOnClickListener{
            val intent = Intent()
            intent.putExtra(AUTH_USERNAME, "xjliao")
            intent.putExtra(AUTH_AVATAR, "BAIDU.COM")
            startActivityH(intent, AuthSettingsActivity::class.java)
        }
    }

    override fun initAdapters() {

    }

    override fun initData() {

    }

    override fun destroyView() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (AUTH_REQUEST_CODE == requestCode) {
            if (AUTH_SIGN_IN_OTHER_ACCOUNT_RESULT_CODE == resultCode) {
                showToast(requestCode.toString() + ":" + resultCode)
            } else if (AUTH_SIGN_IN_WITH_PASSWORD == resultCode) {
                showToast("登录密码"+ data!!.getStringExtra(Constants.PASSWORD))
            }
        }
    }


    fun back(view: View) {
        finish()
    }
}
