/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JvmName("Constants")

package me.xjliao.xjlib.auth

@JvmField val DEFAULT_KEY_NAME = "default_key"

/**
 * Enumeration to indicate which authentication method the user is trying to authenticate with.
 */
enum class Stage { FINGERPRINT, NEW_FINGERPRINT_ENROLLED, PASSWORD }

val AUTH_USERNAME = "AUTH_USERNAME"

val AUTH_AVATAR = "AUTH_AVATAR"

val AUTH_PREFERENCES = "AUTH_PREFERENCES"

val AUTH_REQUEST_CODE = 50000

val AUTH_SIGN_IN_OTHER_ACCOUNT_RESULT_CODE = 50001

val AUTH_SIGN_IN_WITH_PASSWORD = 50002
