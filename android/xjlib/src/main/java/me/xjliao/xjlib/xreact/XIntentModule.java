package me.xjliao.xjlib.xreact;

import android.app.Activity;
import android.content.Intent;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;

public class XIntentModule extends ReactContextBaseJavaModule {

	public XIntentModule(ReactApplicationContext reactContext) {
		super(reactContext);
	}

	@Override
	public String getName() {
		return "XIntentModule";
	}

	@ReactMethod
	public void startActivityFromJS(String activityFullPath, ReadableMap params){
		try{
			Activity currentActivity = getCurrentActivity();
			if(null!=currentActivity){
				Class toActivity = Class.forName(activityFullPath);
				Intent intent = new Intent(currentActivity,toActivity);
				intentPutExtra(intent,  params);
				currentActivity.startActivity(intent);
			}
		}catch(Exception e){
			throw new JSApplicationIllegalArgumentException(
					"不能打开Activity : " + activityFullPath + ":" +e.getMessage());
		}
	}

	public void intentPutExtra(Intent intent, ReadableMap params) {
		System.out.println(params);
		ReadableMapKeySetIterator iterator = params.keySetIterator();

		while (iterator.hasNextKey()) {
			String key = iterator.nextKey();
			ReadableType type = params.getType(key);

			switch (type) {
				case Null:
					intent.putExtra(key, "");
					break;
				case Boolean:
					intent.putExtra(key, params.getBoolean(key));
					break;
				case Number:
					intent.putExtra(key, params.getDouble(key));
					break;
				case String:
					intent.putExtra(key, params.getString(key));
					break;
			}
		}
	}

	@Override
	public boolean canOverrideExistingModule() {
		return true;
	}
}
