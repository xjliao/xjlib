/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: BaseFragment.java
 * ClassName: BaseFragment
 * LastModified: 10/18/17 4:06 PM
 */

package me.xjliao.xjlib.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.xjliao.xjlib.R;
import me.xjliao.xjlib.common.Constants;

public abstract class BaseFragment extends Fragment {

    protected FragmentManager mFm;

    protected ActionBar mActionBar;

    // TODO: Rename and change types of parameters
    protected String mTitle;

    protected int mIndex;

    protected View view;

    private Unbinder unbinder;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFm = getActivity().getSupportFragmentManager();
        mActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        if (getArguments() != null) {
            mTitle = getArguments().getString(Constants.TITLE);
            mIndex = getArguments().getInt(Constants.INDEX, -1);
        }

        setHasOptionsMenu(true);

        setupComponent();
    }

    public abstract void setupComponent();

    public int getShowIndex() {
        return getArguments().getInt(Constants.INDEX, -1);
    }

    public String getTitle() {
        return getArguments().getString(Constants.TITLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getFragmentLayout(), container, false);

        return view;
    }

    /**
     * Every fragment has to inflate a layout in the onCreateView method. We have added this method to
     * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
     * inflate in this method when extends BaseFragment.
     */
    protected abstract int getFragmentLayout();

    /**
     * Init some datas
     */
    protected abstract void initData();

    protected abstract void initViews();

    /**
     * Init some listeners
     */
    protected abstract void initListeners();

    /**
     * Init some adapters
     */
    protected abstract void initAdapters();

    /**
     * Init
     */
    protected void init() {
        initData();
        initViews();
        initAdapters();
        initListeners();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
        init();
    }

    /**
     * Replace every field annotated with ButterKnife annotations like @InjectView with the proper
     * value.
     *
     * @param view to extract each widget injected in the fragment.
     */
    protected void injectViews(final View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    protected FragmentManager getFm() {
        return this.mFm;
    }

    protected <T extends View> T getViewById(View view, int id) {
        return (T) view.findViewById(id);
    }

    protected <T extends View> T getViewById(int id) {
        return (T) view.findViewById(id);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        destroyView();
    }

    public abstract void destroyView();

    public void startActivityX(Intent intent) {
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public void startActivityForResultX(Intent intent, int requestCode) {
        getActivity().startActivityForResult(intent, requestCode);
        getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

}
