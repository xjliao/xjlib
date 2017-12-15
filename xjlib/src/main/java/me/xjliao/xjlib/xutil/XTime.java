/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XTime.java
 * ClassName: XTime
 * LastModified: 12/15/17 4:35 PM
 */

package me.xjliao.xjlib.xutil;

/**
 * Created by xjliao on 12/15/17.
 */

public class XTime {

    public static long dploy(long min, long max) {
        return min + (long) (Math.random() * max - min + 1L);
    }

}
