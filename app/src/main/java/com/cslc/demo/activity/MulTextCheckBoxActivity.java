package com.cslc.demo.activity;

import android.os.Bundle;

import com.cslc.demo.R;
import com.cslc.demo.view.MulTextCheckBox;

/**
 * 自定义一个两行的checkbox
 * 
 * @author zhangyu
 *
 */
public class MulTextCheckBoxActivity extends BaseActivity {
	private MulTextCheckBox mMulTextCb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMulTextCb = (MulTextCheckBox) findViewById(R.id.mul_text_cb);
		mMulTextCb.setTitle("主标题");
		mMulTextCb.setSubTitle("副标题");
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_mul_text_checkbox;
	}

}
