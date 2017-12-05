/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XResponse.java
 * ClassName: XResponse
 * LastModified: 11/29/17 9:33 AM
 */

package me.xjliao.xjlib.net;

import com.fasterxml.jackson.annotation.JsonProperty;

public class XResponse<T> {

    private int code;

    @JsonProperty(value = "message")
    private String msg;

    private T obj;

    public XResponse() {

    }

    public XResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public XResponse(int code, String msg, T obj) {
        this(code, msg);
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}