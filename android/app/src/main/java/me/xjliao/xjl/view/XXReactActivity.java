package me.xjliao.xjl.view;

import android.os.Bundle;

import com.microsoft.codepush.react.CodePush;

import me.xjliao.xjl.BuildConfig;
import me.xjliao.xjlib.react.XReactActivity;

public class XXReactActivity extends XReactActivity {

	@Override
	public void setArgLaunchOptions(Bundle bundle) {

	}

	@Override
	public void initReactInstanceManagerBuilder() {
			getReactInstanceManagerBuilder()
					.addPackage(new CodePush(BuildConfig.CODEPUSH_KEY,  getApplicationContext(),  BuildConfig.DEBUG))
					.setUseDeveloperSupport(BuildConfig.DEBUG)
					. setJSBundleFile(CodePush.getJSBundleFile(XReactActivity.DEFAULT_BUNDLE_ASSET_NAME));
	}
}
