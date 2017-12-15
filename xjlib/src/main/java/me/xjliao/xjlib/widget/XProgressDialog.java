/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XProgressDialog.java
 * ClassName: XProgressDialog
 * LastModified: 12/5/17 3:19 PM
 */

package me.xjliao.xjlib.widget;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by xjliao on 12/5/17.
 */

public class XProgressDialog extends ProgressDialog {

    public XProgressDialog(Context context) {
        super(context);
    }

    public XProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public static XProgressDialog newInstance(Context context, String msg) {
        XProgressDialog progressDialog = new XProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(msg);
        return progressDialog;
    }

    public static XProgressDialog newInstance(Context context, String msg, int style) {
        XProgressDialog progressDialog = new XProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(style);
        progressDialog.setMessage(msg);
        return progressDialog;
    }

}
