/*
 * Copyright (c) 2017 xjliao.me created by xjliao
 * ProjectName: xjl
 * ModuleName: xjlib
 * FileName: FragmentContainerActivity.java
 * ClassName: FragmentContainerActivity
 * LastModified: 11/30/17 4:39 PM
 */

package me.xjliao.xjlib.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public abstract class FragmentContainerActivity extends BaseActivity {

    private Fragment currentFragment;

    public void switchFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment targetFragment = fm.findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        if (targetFragment == null) {
            if (null != currentFragment) {
                fragmentTransaction.hide(currentFragment);
            }

            fragmentTransaction.add(containerViewId, fragment, tag);
            currentFragment = fragment;
        } else {
            if (targetFragment.isAdded()) {
                fragmentTransaction.hide(currentFragment).show(targetFragment);
            }

            currentFragment = targetFragment;
        }

        fragmentTransaction.commit();
    }

}