/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XToast.java
 * ClassName: XToast
 * LastModified: 11/30/17 4:39 PM
 */

package me.xjliao.xjlib.xutil;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Some utility methods related with the Toast class.
 *
 * @author xjliao
 */
public class XToast {

    private XToast() {
        //Empty
    }

    public static void showShortMsg(Context context, String msg) {
        Toast toast = getToast(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showLongMsg(Context context, String msg) {
        Toast toast = getToast(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private static Toast getToast(Context context, String msg, int length) {
        return Toast.makeText(context, msg, length);
    }

}
