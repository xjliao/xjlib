package me.xjliao.xjlib.xreact;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public  abstract class XPackage implements ReactPackage {

	private List<NativeModule> nativeModules = new ArrayList<>();

	@Override
	public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
		nativeModules.add(new XIntentModule(reactContext));
		addModules(nativeModules,  reactContext);
		return nativeModules;
	}

	@Override
	public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
		return Collections.emptyList();
	}

	public abstract  void addModules(List<NativeModule> nativeModules, ReactApplicationContext reactContext);

}
