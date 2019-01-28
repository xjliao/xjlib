package me.xjliao.xjlib.xutil;

import android.content.Context;

public class GlobalUtil {

	public static Context CONTEXT;

	public static void withContext(Context context) {
		CONTEXT = context;
	}
}
