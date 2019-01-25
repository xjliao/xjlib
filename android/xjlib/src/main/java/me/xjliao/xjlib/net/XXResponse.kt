/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XResponse.java
 * ClassName: XResponse
 * LastModified: 12/14/17 11:26 AM
 */

package me.xjliao.xjlib.net

import com.fasterxml.jackson.annotation.JsonProperty

open class XXResponse {

    var code: Int = 0

    @JsonProperty(value = "msg")
    var msg: String? = null

    @JsonProperty(value="msgText")
    var msgText: String? = null

    constructor() {

    }


    constructor(msg: String, msgText : String) {
        this.msg = msg
        this.msgText = msgText
    }

    constructor(code: Int,  msgText : String) {
        this.code = code
        this.msgText = msgText
    }
}