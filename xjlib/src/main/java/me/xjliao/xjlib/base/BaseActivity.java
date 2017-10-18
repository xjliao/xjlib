/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: BaseActivity.java
 * ClassName: BaseActivity
 * LastModified: 10/11/17 2:30 PM
 */

package me.xjliao.xjlib.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.ButterKnife;
import me.xjliao.xjlib.common.Constants;
import me.xjliao.xjlib.R;

public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar toolbar;

    protected ActionBar actionBar;

    protected TextView titleTV;

    protected Intent intent;

    protected String title;

    protected FragmentManager fm;

    protected FragmentTransaction fragmentTransaction;

    protected Fragment currentFragment;

    protected Boolean isDisplayHomeAsUpEnabled = true;

    protected boolean v;

    protected boolean h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        setupComponent();

        ButterKnife.bind(this);
        init();

        // SCREEN_ORIENTATION_PORTRAIT
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public abstract void setupComponent();

    /**
     * Every fragment has to inflate a layout in the onCreateView method. We have added this method to
     * avoid duplicate all the inflate code in every fragment. You only have to return the layout to
     * inflate in this method when extends BaseFragment.
     */
    protected abstract int getLayout();

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
     * Init some datas
     */
    protected abstract void initData();

    /**
     * Init
     */
    protected void init() {
        initReferFragment();
        initIntent();
        initData();
        initTitle();
        initToolBar();
        initViews();
        initAdapters();
        initListeners();
    }

    protected void initReferFragment() {
        fm = getSupportFragmentManager();
    }

    protected void initIntent() {
        intent = getIntent();
    }

    protected void initTitle() {
        if (title == null) {
            title = intent.getStringExtra(Constants.TITLE);
        }
    }

    public void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar == null) {
            return;
        }

//        titleTV = (TextView) toolbar.findViewById(R.id.title_tv);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        if (actionBar != null) {
            setXTitle(title);
        }
    }

    public void startActivityH(Intent intent, Class cls) {
        h = true;

        if (intent == null) {
            intent = new Intent();
        }

        if (cls != null) {
            intent.setClass(this, cls);
        }

        startActivity(intent);
    }

    public void startActivityForResultH(Intent intent, Class cls, int requestCode) {
        h = true;

        if (intent == null) {
            intent = new Intent();
        }

        if (cls != null) {
            intent.setClass(this, cls);
        }

        startActivityForResult(intent, requestCode);
    }

    public void startActivityV(Intent intent, Class cls) {
        v = true;

        if (intent == null) {
            intent = new Intent();
        }

        if (cls != null) {
            intent.setClass(this, cls);
        }

        startActivity(intent);
    }

    public void startActivityForResultV(Intent intent, Class cls, int requestCode) {
        v = true;

        if (intent == null) {
            intent = new Intent();
        }

        if (cls != null) {
            intent.setClass(this, cls);
        }

        startActivityForResult(intent, requestCode);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

    public FragmentManager getFm() {
        return fm;
    }

    public void setFm(FragmentManager fm) {
        this.fm = fm;
    }

    public FragmentTransaction getFragmentTransaction() {
        return fragmentTransaction;
    }

    public void setFragmentTransaction(FragmentTransaction fragmentTransaction) {
        this.fragmentTransaction = fragmentTransaction;
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(Fragment currentFragment) {
        this.currentFragment = currentFragment;
    }

    public String getXTitle() {
        return this.title;
    }

    public void setXTitle(String title) {
        if (titleTV != null) {
            titleTV.setText(title);
        } else {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(isDisplayHomeAsUpEnabled);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (v) {
            overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
        }

        if (h) {
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (v) {
            overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        }

        if (h) {
            overridePendingTransition(R.anim.slede_in_right, R.anim.slide_out_right);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyView();
    }

    public void destroyView() {

    }
}