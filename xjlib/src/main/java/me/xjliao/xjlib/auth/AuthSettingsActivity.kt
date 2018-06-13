/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package me.xjliao.xjlib.auth

import android.Manifest
import android.app.KeyguardManager
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_auth_settings.*
import me.xjliao.xjlib.R
import me.xjliao.xjlib.base.BaseActivity
import me.xjliao.xjlib.ext.showToast

class AuthSettingsActivity : BaseActivity() {

    private var sharedPreferences: SharedPreferences? = null

    override fun setupComponent() {

    }

    override fun getLayout(): Int {
        return R.layout.activity_auth_settings
    }

    override fun initViews() {
        auth_checkbox.isChecked = sharedPreferences!!.getBoolean(getString(R.string.use_fingerprint_to_authenticate_key),
                false)
    }

    override fun initListeners() {
        auth_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                if (!isPassAuthCondition()) {
                    auth_checkbox.isChecked = false
                    showToast("开启指纹识别失败")
                    return@setOnCheckedChangeListener
                }
            }

            auth_settings_text_view!!.text = ""
            updateAuthSettings(isChecked)
        }
    }

    private fun isPassAuthCondition(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Get an instance of KeyguardManager and FingerprintManager//
            var keyguardManager = getSystemService(AppCompatActivity.KEYGUARD_SERVICE) as KeyguardManager
            var fingerprintManager = getSystemService(AppCompatActivity.FINGERPRINT_SERVICE) as FingerprintManager

            //Check whether the device has a fingerprint sensor//
            if (!fingerprintManager!!.isHardwareDetected) {
                // If a fingerprint sensor isn’t available, then inform the user that they’ll be unable to use your app’s fingerprint functionality//
                auth_settings_text_view!!.text = "手机未检测到指纹传感器，请确认手机是否支持指纹识别"
                return false
            }
            //Check whether the user has granted your app the USE_FINGERPRINT permission//
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                // If your app doesn't have this permission, then display the following text//
                auth_settings_text_view!!.text = "未开启指纹识别权限"
                return false
            }

            //Check that the lockscreen is secured//
            if (!keyguardManager!!.isKeyguardSecure) {
                // If the user hasn’t secured their lockscreen with a PIN password or pattern, then display the following text//
                auth_settings_text_view!!.text = getString(R.string.setup_lock_screen)
                return false
            }

            //Check that the user has registered at least one fingerprint//
            if (!fingerprintManager!!.hasEnrolledFingerprints()) {
                // If the user hasn’t configured any fingerprints, then display the following message//
                auth_settings_text_view!!.text = getString(R.string.register_fingerprint)
                return false
            }

            return true
        } else {
            auth_settings_text_view.text = getString(R.string.fingerprint_not_support)
            return false
        }
        return true
    }

    override fun initAdapters() {

    }

    override fun initData() {
        title = getString(R.string.auth_settings)
        sharedPreferences = getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE)
    }

    private fun updateAuthSettings(isChecked: Boolean) {
        var editor = getSharedPreferences(AUTH_PREFERENCES, Context.MODE_PRIVATE).edit()
        editor.putBoolean(getString(R.string.use_fingerprint_to_authenticate_key), isChecked)
        editor.apply()
        if (isChecked) {
            showToast("指纹识别已开启，下次启动生效")
        }
    }

    override fun destroyView() {

    }

}
