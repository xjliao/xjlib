package me.xjliao.xjlib.ext

import android.widget.Toast
import me.xjliao.xjlib.base.BaseFragment

fun BaseFragment.showToast(text: String) {
    Toast.makeText(activity, text, Toast.LENGTH_LONG).show()
}