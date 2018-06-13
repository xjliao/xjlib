package me.xjliao.xjlib.ext

import android.widget.Toast
import me.xjliao.xjlib.base.BaseActivity

fun BaseActivity.showToast(text: String?) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}