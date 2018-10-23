package me.xjliao.xjlib.react;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;

import me.xjliao.xjlib.R;
import me.xjliao.xjlib.xutil.XString;

public abstract  class XReactActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {

	protected ReactRootView reactRootView;

	protected ReactInstanceManager reactInstanceManager;

	protected  ReactInstanceManagerBuilder reactInstanceManagerBuilder;

	protected String bundleAssetName = "index.bundle";

	protected String jsMainModulePath = "index";

	protected Boolean useDeveloperSupport = true;

	private final int OVERLAY_PERMISSION_REQ_CODE = 0x10;

	public static final String ARG_COMPONENT_NAME = "arg_component_name";

	public static final String ARG_COMPONENT_TITLE = "arg_component_title";

	public static final String ARG_LAUNCH_OPTIONS = "arg_launch_options";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.XReactTheme);
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		String moduleName = intent.getStringExtra(ARG_COMPONENT_NAME);
		String moduleTitle = intent.getStringExtra(ARG_COMPONENT_TITLE);
		if (XString.isNullOrEmpty(moduleTitle)) {
			moduleTitle = getResources().getString(R.string.app_name);
		}

		getSupportActionBar().setTitle(moduleTitle);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		reactRootView = new ReactRootView(this);
		reactInstanceManagerBuilder = ReactInstanceManager.builder()
		.setApplication(getApplication())
		.setBundleAssetName(getBundleAssetName())
		.setJSMainModulePath(getJsMainModulePath())
		.addPackage(new MainReactPackage())
		.setUseDeveloperSupport(getUseDeveloperSupport())
		.setInitialLifecycleState(LifecycleState.RESUMED);

		// Init react instance manager builder()
		initReactInstanceManagerBuilder();

		this.setReactInstanceManager(reactInstanceManagerBuilder.build());
		Bundle argLaunchOptions = intent.getBundleExtra(ARG_LAUNCH_OPTIONS);
		reactRootView.startReactApplication(getReactInstanceManager(), moduleName, argLaunchOptions);

		setContentView(reactRootView);
	}

	public abstract  void initReactInstanceManagerBuilder();

	@Override
	public void invokeDefaultOnBackPressed() {
		super.onBackPressed();
	}

	@Override
	public void onBackPressed() {
		if (reactInstanceManager != null) {
			reactInstanceManager.onBackPressed();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();

		if (reactInstanceManager != null) {
			reactInstanceManager.onHostPause(this);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (reactInstanceManager != null) {
			reactInstanceManager.onHostResume(this, this);
		}
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

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_MENU && reactInstanceManager != null) {
			reactInstanceManager.showDevOptionsDialog();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
				if (!Settings.canDrawOverlays(this)) {
					// SYSTEM_ALERT_WINDOW permission not granted
				}
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		if (reactInstanceManager != null) {
			reactInstanceManager.onHostDestroy(this);
		}
		if (reactRootView != null) {
			reactRootView.unmountReactApplication();
		}
	}

	public ReactRootView getReactRootView() {
		return reactRootView;
	}

	public void setReactRootView(ReactRootView reactRootView) {
		this.reactRootView = reactRootView;
	}

	public ReactInstanceManager getReactInstanceManager() {
		return reactInstanceManager;
	}

	public void setReactInstanceManager(ReactInstanceManager reactInstanceManager) {
		this.reactInstanceManager = reactInstanceManager;
	}

	public String getBundleAssetName() {
		return bundleAssetName;
	}

	public void setBundleAssetName(String bundleAssetName) {
		this.bundleAssetName = bundleAssetName;
	}

	public String getJsMainModulePath() {
		return jsMainModulePath;
	}

	public void setJsMainModulePath(String jsMainModulePath) {
		this.jsMainModulePath = jsMainModulePath;
	}

	public Boolean getUseDeveloperSupport() {
		return useDeveloperSupport;
	}

	public void setUseDeveloperSupport(Boolean useDeveloperSupport) {
		this.useDeveloperSupport = useDeveloperSupport;
	}

	public ReactInstanceManagerBuilder getReactInstanceManagerBuilder() {
		return reactInstanceManagerBuilder;
	}

	public void setReactInstanceManagerBuilder(ReactInstanceManagerBuilder reactInstanceManagerBuilder) {
		this.reactInstanceManagerBuilder = reactInstanceManagerBuilder;
	}
}

