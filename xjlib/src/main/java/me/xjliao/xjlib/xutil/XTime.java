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

    public static final double requestMinSeconds = 0.3;

    public static final double requestMaxSeconds = 0.5;

    public static long deployRequestSeconds() {
        return new Double((requestMinSeconds + (Math.random() * (requestMaxSeconds - requestMinSeconds + 0.1))) * 1000).longValue();
    }

    public static long deploySeconds(double minSeconds, double maxSeconds) {
        return new Double((minSeconds + (Math.random() * (maxSeconds - minSeconds + 0.1))) * 1000).longValue();
    }

}
