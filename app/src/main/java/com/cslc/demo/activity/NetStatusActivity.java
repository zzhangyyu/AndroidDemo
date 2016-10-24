package com.cslc.demo.activity;


import android.os.Bundle;
import android.widget.TextView;

import com.cslc.demo.R;
import com.cslc.demo.util.NetworkUtil;

/**
 * 判断手机当前的网络状态
 * 
 * @author zhangyu
 *
 */
public class NetStatusActivity extends BaseActivity {
	private TextView netWorkTv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		netWorkTv = (TextView) findViewById(R.id.network_tv);
		if (NetworkUtil.isAvailable(mActivity)) {
			netWorkTv.setText("可以上网");
		}
		if (NetworkUtil.isNetworkWifi(mActivity)) {
			netWorkTv.setText("可以上网，且为Wifi环境");
		}
		if (!NetworkUtil.isAvailable(mActivity)) {
			netWorkTv.setText("无法上网");
		}
		if (!NetworkUtil.isNetworkWifi(mActivity)) {
			netWorkTv.setText("在非Wifi环境下上网");
		}
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_network;
	}

}
