/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: XDialogFragment.java
 * ClassName: XDialogFragment
 * LastModified: 10/19/17 8:50 AM
 */

package me.xjliao.xjlib.widget;

import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import me.xjliao.xjlib.R;
import me.xjliao.xjlib.R2;
import me.xjliao.xjlib.base.BaseDialogFragment;

/**
 * Created by xjliao on 11/16/15.
 */
public class XDialogFragment extends BaseDialogFragment {

    @BindView(R2.id.msg_tv)
    TextView mMsgTV;

    @BindView(R2.id.ok_tv)
    TextView mOkTV;

    @BindView(R2.id.cancel_tv)
    TextView cancelTV;

    @BindView(R2.id.cancel_v)
    View cancelV;

    private String mMsg;

    private XDialogListener mListener;

    private boolean isNegative;

    public static XDialogFragment newInstance(String msg, boolean isNegative,
                                              XDialogListener listener) {
        XDialogFragment fragment = new XDialogFragment();
        fragment.setCancelable(false);
        fragment.mMsg = msg;
        fragment.isNegative = isNegative;
        fragment.mListener = listener;
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_dialog_x;
    }

    @Override
    protected void initView() {
        this.mMsgTV.setText(this.mMsg);

        if (!isNegative) {
            cancelV.setVisibility(View.GONE);
        }
    }

    @OnClick({R2.id.ok_tv, R2.id.cancel_tv})
    public void onClickAction(View view) {
        if (R.id.ok_tv == view.getId()) {
            mListener.onDialogPositiveClick(XDialogFragment.this);
            this.dismissAllowingStateLoss();
        }

        if (R.id.cancel_tv == view.getId()) {
            mListener.setNegativeClick(XDialogFragment.this);
            this.dismissAllowingStateLoss();
        }
    }

    public interface XDialogListener {

        void onDialogPositiveClick(DialogFragment dialog);

        void setNegativeClick(DialogFragment dialog);
    }
}
