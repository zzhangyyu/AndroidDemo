package com.cslc.demo.activity;


import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cslc.demo.R;

public class NotificationActivity extends BaseActivity implements View.OnClickListener {

	private NotificationManager mNManager;
	private Notification notify1;
	private Bitmap LargeBitmap = null;
	private static final int NOTIFYID_1 = 1;

	private Button showNotificationBtn;
	private Button delNotificationBtn;

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_notification;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LargeBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.app_logo);
		mNManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		showNotificationBtn = (Button) findViewById(R.id.btn_show_notification);
		delNotificationBtn = (Button) findViewById(R.id.btn_close_notification);
		showNotificationBtn.setOnClickListener(this);
		delNotificationBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_show_notification:
			// 定义一个PendingIntent点击Notification后启动一个Activity
			Intent it = new Intent(mActivity, MulTextCheckBoxActivity.class);
			PendingIntent pit = PendingIntent.getActivity(mActivity, 0, it, 0);

			// 设置图片,通知标题,发送时间,提示方式等属性
			Notification.Builder mBuilder = new Notification.Builder(this);
			mBuilder.setContentTitle("标题") // 标题
					.setContentText("内容~") // 内容
					.setSubText("--内容下面的一小段文字") // 内容下面的一小段文字
					.setTicker("收到信息后状态栏显示的文字信息~") // 收到信息后状态栏显示的文字信息
					.setWhen(System.currentTimeMillis()) // 设置通知时间
					.setSmallIcon(R.drawable.slide) // 设置小图标
					.setLargeIcon(LargeBitmap) // 设置大图标
					.setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE) // 设置默认的三色灯与振动器
					.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.biaobiao)) // 设置自定义的提示音
					.setAutoCancel(true) // 设置点击后取消Notification
					.setContentIntent(pit); // 设置PendingIntent
			notify1 = mBuilder.build();
			mNManager.notify(NOTIFYID_1, notify1);
			break;

		case R.id.btn_close_notification:
			// 除了可以根据ID来取消Notification外,还可以调用cancelAll();关闭该应用产生的所有通知
			mNManager.cancel(NOTIFYID_1); // 取消Notification
			break;

		}
	}

}
