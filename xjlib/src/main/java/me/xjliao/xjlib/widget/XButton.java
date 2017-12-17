/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XButton.java
 * ClassName: XButton
 * LastModified: 12/15/17 4:55 PM
 */

package me.xjliao.xjlib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import me.xjliao.xjlib.R;

/**
 * Created by xjliao on 12/5/17.
 */

public class XButton extends AppCompatButton {

    private boolean preActionAble;

    private String preActionText;

    private String originText;

    public XButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XButton, 0, 0);
        try {
            preActionAble = ta.getBoolean(R.styleable.XButton_preActionAble, false);
            preActionText = ta.getString(R.styleable.XButton_preActionText);
            originText = getText().toString();
        } finally {
            ta.recycle();
        }
    }

    public boolean isPreActionAble() {
        return preActionAble;
    }

    public void setPreActionAble(boolean preActionAble) {
        this.preActionAble = preActionAble;
        this.setEnabled(!this.preActionAble);

        if (this.preActionAble) {
            if (preActionText.isEmpty()) {
                preActionText = getContext().getString(R.string.preActionText);
            }
            setText(preActionText);
        } else {
            setText(originText);
        }
        invalidate();
        requestLayout();
    }

    public String getPreActionText() {
        return preActionText;
    }

    public void setPreActionText(String preActionText) {
        this.preActionText = preActionText;
        if (this.preActionAble) {
            setText(this.preActionText);
        } else {
            setText(originText);
        }
        invalidate();
        requestLayout();
    }

    public String getOriginText() {
        return originText;
    }

    public void setOriginText(String originText) {
        this.originText = originText;
    }
}
