package me.xjliao.xjl.view;

import com.microsoft.codepush.react.CodePush;

import me.xjliao.xjl.BuildConfig;
import me.xjliao.xjlib.react.XReactActivity;

public class XXReactActivity extends XReactActivity {

	@Override
	public void initReactInstanceManagerBuilder() {
			getReactInstanceManagerBuilder()
					.addPackage(new CodePush(BuildConfig.CODEPUSH_KEY, getApplicationContext(),  true))
					. setJSBundleFile(CodePush.getJSBundleFile());
	}
}
