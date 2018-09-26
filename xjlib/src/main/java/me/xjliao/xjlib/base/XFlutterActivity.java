package me.xjliao.xjlib.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import io.flutter.facade.Flutter;
import me.xjliao.xjlib.R;
import me.xjliao.xjlib.common.Constants;

public class XFlutterActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.AppThemeNoActionBar);
		String route = getIntent().getStringExtra(Constants.ROUTE);
		View flutterView = Flutter.createView(this, getLifecycle(), route);
		FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		addContentView(flutterView, layout);
	}
}
