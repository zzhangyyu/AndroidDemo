package com.cslc.demo;


import android.app.Application;
import android.content.Context;

/**
 * Application
 * 
 * @author qinweixiang
 *
 */
public class App extends Application {
	public static Context applicationContext;

	@Override
	public void onCreate() {
		super.onCreate();
		applicationContext = this;
		initLibrary();
	}

	/**
	 * 初始化库
	 */
	private void initLibrary() {

	}

}
