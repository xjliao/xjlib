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

import android.app.DialogFragment
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.hardware.fingerprint.FingerprintManager
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import me.xjliao.xjlib.R
import me.xjliao.xjlib.common.Constants

/**
 * A dialog which uses fingerprint APIs to authenticate the user, and falls back to password
 * authentication if fingerprint is not available.
 */
class FingerprintAuthenticationDialogFragment : DialogFragment(), FingerprintUiHelper.Callback {

    private lateinit var cancelButton: Button
    private lateinit var fingerprintContainer: View
    private lateinit var secondDialogButton: Button

    private lateinit var callback: Callback
    private lateinit var cryptoObject: FingerprintManager.CryptoObject
    private lateinit var fingerprintUiHelper: FingerprintUiHelper
    private lateinit var sharedPreferences: SharedPreferences

    private var stage = Stage.FINGERPRINT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Do not create a new Fragment when the Activity is re-created such as orientation changes.
        retainInstance = true
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Material_Light_Dialog)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        dialog.setTitle(getString(R.string.sign_in))
        return inflater.inflate(R.layout.fingerprint_dialog_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cancelButton = view.findViewById(R.id.cancel_button)
        fingerprintContainer = view.findViewById(R.id.fingerprint_container)
        secondDialogButton = view.findViewById(R.id.second_dialog_button)

        cancelButton.setOnClickListener { dismiss() }
        secondDialogButton.setOnClickListener {
            usePassword()
        }

        fingerprintUiHelper = FingerprintUiHelper(
                activity.getSystemService(FingerprintManager::class.java),
                view.findViewById(R.id.fingerprint_icon),
                view.findViewById(R.id.fingerprint_status),
                this
        )
        updateStage()

        // If fingerprint authentication is not available, switch immediately to the backup
        // (password) screen.
        if (!fingerprintUiHelper.isFingerprintAuthAvailable) {
            usePassword()
        }
    }
    override fun onResume() {
        super.onResume()
        if (stage == Stage.FINGERPRINT) {
            fingerprintUiHelper.startListening(cryptoObject)
        }
    }

    override fun onPause() {
        super.onPause()
        fingerprintUiHelper.stopListening()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setCallback(callback: Callback) {
        this.callback = callback
    }

    fun setCryptoObject(cryptoObject: FingerprintManager.CryptoObject) {
        this.cryptoObject = cryptoObject
    }

    fun setStage(stage: Stage) {
        this.stage = stage
    }

    private fun usePassword() {
        // Re-create the key so that fingerprints including new ones are validated.
        callback.createKey(DEFAULT_KEY_NAME)
        stage = Stage.FINGERPRINT
        updateStage()
        // Fingerprint is not used anymore. Stop listening for it.
        fingerprintUiHelper.stopListening()
        callback.onAuthenticated(withFingerprint = false, usePassword = true)
        dismiss()
    }

    private fun updateStage() {
        when (stage) {
            Stage.FINGERPRINT -> {
                cancelButton.setText(R.string.cancel)
                secondDialogButton.setText(R.string.use_password)
                fingerprintContainer.visibility = View.VISIBLE
            }
            Stage.NEW_FINGERPRINT_ENROLLED -> {
                Toast.makeText(activity, "设备指纹有变化，需要密码重新登录。", Toast.LENGTH_LONG).show()
                usePassword()
            } // Intentional fall through
//            Stage.PASSWORD -> {
//                cancelButton.setText(R.string.cancel)
//                secondDialogButton.setText(R.string.ok)
//                fingerprintContainer.visibility = View.GONE
//                backupContent.visibility = View.VISIBLE
//                if (stage == Stage.NEW_FINGERPRINT_ENROLLED) {
//                    passwordDescriptionTextView.visibility = View.GONE
//                    fingerprintEnrolledTextView.visibility = View.VISIBLE
//                }
//            }
        }
    }

    override fun onAuthenticated() {
        // Callback from FingerprintUiHelper. Let the activity know that authentication succeeded.
        callback.onAuthenticated(withFingerprint = true, crypto = cryptoObject, usePassword = false)
        dismiss()
    }

    override fun onError() {
        usePassword()
    }

    interface Callback {
        fun onAuthenticated(withFingerprint: Boolean,
                             crypto: FingerprintManager.CryptoObject? = null,
                            usePassword: Boolean?= false)
        fun createKey(keyName: String, invalidatedByBiometricEnrollment: Boolean = true)
    }
}
