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

class XResponse<T> {

    var code: Int = 0

    @JsonProperty(value = "message")
    var msg: String? = null

    var obj: T? = null

    constructor() {

    }

    constructor(code: Int, msg: String) {
        this.code = code
        this.msg = msg
    }

    constructor(code: Int, msg: String, obj: T) : this(code, msg) {
        this.obj = obj
    }

    companion object {
        val DEFAULT = "DEFAULT"
    }
}