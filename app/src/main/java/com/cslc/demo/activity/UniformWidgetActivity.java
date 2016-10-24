package com.cslc.demo.activity;


import android.os.Bundle;

import com.cslc.demo.R;

/**
 * 为Button、checkbox等定义统一的格式
 * 
 * @author zhangyu
 *
 */
public class UniformWidgetActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_uniform_widget;
	}

}
