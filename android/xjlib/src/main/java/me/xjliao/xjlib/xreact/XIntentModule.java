package me.xjliao.xjlib.xreact;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class XIntentModule extends ReactContextBaseJavaModule {

	public XIntentModule(ReactApplicationContext reactContext) {
		super(reactContext);
	}

	@Override
	public String getName() {
		return  XReactActivity.ARG_COMPONENT_NAME;
	}

	@ReactMethod
	public void startActivityFromJS(String activityFullPath, String params){
		try{
			Activity currentActivity = getCurrentActivity();
			if(null!=currentActivity){
				Class toActivity = Class.forName(activityFullPath);
				Intent intent = new Intent(currentActivity,toActivity);
				intent.putExtra("params", params);
				currentActivity.startActivity(intent);
			}
		}catch(Exception e){
			throw new JSApplicationIllegalArgumentException(
					"不能打开Activity : "+e.getMessage());
		}
	}

}
