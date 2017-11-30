/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XString.java
 * ClassName: XString
 * LastModified: 10/19/17 8:50 AM
 */

package me.xjliao.xjlib.xutil;

/**
 * Some utility methods related with the String class.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
public class XString {

    private static final String EMPTY_STRING = "";

    private XString() {
        //Empty
    }

    public static boolean isNullOrEmpty(final String string) {
        return string == null || EMPTY_STRING.equals(string);
    }
}
