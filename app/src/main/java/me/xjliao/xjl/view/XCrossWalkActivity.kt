package me.xjliao.xjl.view

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cross_walk.*
import me.xjliao.xjl.R
import org.xwalk.core.XWalkActivity
import org.xwalk.core.XWalkResourceClient
import org.xwalk.core.XWalkUIClient
import org.xwalk.core.XWalkView



class XCrossWalkActivity : XWalkActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cross_walk)
    }

    fun initXWalkView() {
        var mXWalkView = XWalkView(this)
        layout1.addView(mXWalkView)
        mXWalkView.setUIClient(XWalkUIClient(mXWalkView))
        mXWalkView.setResourceClient(XWalkResourceClient(mXWalkView))
        mXWalkView.load("http://192.168.0.89:9000", null)
    }

    override fun onXWalkReady() {
//        Toast.makeText(this@XCrossWalkActivity, "ready", Toast.LENGTH_LONG).show()
        initXWalkView()
    }

}

