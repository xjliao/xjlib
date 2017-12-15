/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XTime.java
 * ClassName: XTime
 * LastModified: 12/15/17 4:47 PM
 */

package me.xjliao.xjlib.xutil;

/**
 * Created by xjliao on 12/15/17.
 */

public class XTime {

    public static long deloySeconds(double minSeconds, double maxSeconds) {
        return new Double(minSeconds + (Math.random() * maxSeconds - minSeconds + 1) * 1000).longValue();
    }

}
