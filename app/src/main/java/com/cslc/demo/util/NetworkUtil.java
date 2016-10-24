package com.cslc.demo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络工具类
 * 
 * @author zhangyu
 *
 */
public class NetworkUtil {
	/**
	 * 手机是否联网 需要android.permission.ACCESS_NETWORK_STATE权限
	 *
	 * @param context
	 * @return
	 */
	public static boolean isAvailable(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null && info.isAvailable()) {
			return true;
		}
		return false;
	}

	/**
	 * 是否是使用WIFI网络
	 *
	 * @param context
	 * @return
	 */
	public static boolean isNetworkWifi(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info != null && info.isAvailable()) {
			if (info.getType() == ConnectivityManager.TYPE_WIFI) {
				return true;
			}
		}
		return false;
	}
}
