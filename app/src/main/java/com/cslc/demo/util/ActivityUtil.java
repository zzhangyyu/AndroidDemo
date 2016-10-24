package com.cslc.demo.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Activity工具类
 */
@SuppressWarnings("rawtypes")
public class ActivityUtil {
	/**
	 * 跳转Activity
	 *
	 * @param context
	 * @param cls
	 */
	public static void startActivity(Context context, Class cls) {
		startActivity(context, cls, null);
	}

	/**
	 * 跳转Activity
	 *
	 * @param context
	 * @param cls
	 * @param extras
	 */
	public static void startActivity(Context context, Class cls, Bundle extras) {
		Intent intent = new Intent(context, cls);
		if (extras != null) {
			intent.putExtras(extras);
		}
		context.startActivity(intent);
	}

}
