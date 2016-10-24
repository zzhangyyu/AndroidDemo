package com.cslc.demo.activity;


import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.cslc.demo.R;

/**
 * 跳转本地assets目录下html文件
 * 
 * @author zhangyu
 *
 */
public class WebViewActivity extends BaseActivity {
	private WebView myWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myWebView = (WebView) findViewById(R.id.webview);
		WebSettings settings = myWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		myWebView.setWebViewClient(new WebViewClient());
		myWebView.loadUrl("file:///android_asset/html/register.html");
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_local_file;
	}

}
