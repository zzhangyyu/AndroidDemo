package com.cslc.demo.activity;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.cslc.demo.R;

/**
 * 在按钮上显示倒计时
 * 
 * @author zhangyu
 *
 */
public class TimeCountActivity extends BaseActivity implements OnClickListener {

	private Button timeCountBtn;
	private TimeCount timeCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		timeCountBtn = (Button) findViewById(R.id.time_count_btn);
		timeCountBtn.setOnClickListener(this);
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_time_count;
	}

	@Override
	public void onClick(View v) {
		timeCountBtn.setEnabled(false);
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				timeCount = new TimeCount(6000, 1000);
				timeCount.start();// 开始计时
			}
		});

	}

	class TimeCount extends CountDownTimer {
		public TimeCount(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() {
			timeCountBtn.setText("倒计时开始");
			timeCountBtn.setEnabled(true);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			timeCountBtn.setText(millisUntilFinished / 1000 + "秒");
		}
	}

}
