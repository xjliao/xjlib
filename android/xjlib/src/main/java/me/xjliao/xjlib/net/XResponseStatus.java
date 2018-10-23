/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XResponseStatus.java
 * ClassName: XResponseStatus
 * LastModified: 11/28/17 2:43 PM
 */

package me.xjliao.xjlib.net;

public enum XResponseStatus {
    FAILURE(0, "失败"),
    SUCCESS(1, "成功"),
    UNKNOWN(2, "未知");

    private int code;
    private String msg;

    XResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
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

    @Override
    public String toString() {
        return "XResponseStatus{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}