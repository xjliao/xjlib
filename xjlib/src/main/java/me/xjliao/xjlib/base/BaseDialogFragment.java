/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: BaseDialogFragment.java
 * ClassName: BaseDialogFragment
 * LastModified: 10/19/17 8:50 AM
 */

package me.xjliao.xjlib.base;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by xjliao on 11/16/15.
 */
public abstract class BaseDialogFragment extends DialogFragment {

    protected View view;

    public BaseDialogFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(getFragmentLayout(), container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
        initView();
    }

    protected void injectViews(final View view) {
        ButterKnife.bind(this, view);
    }

    protected abstract int getFragmentLayout();

    protected abstract void initView();
}
