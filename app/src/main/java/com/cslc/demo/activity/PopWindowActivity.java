package com.cslc.demo.activity;


import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.cslc.demo.R;
import com.cslc.demo.util.ToastUtil;

public class PopWindowActivity extends BaseActivity implements OnClickListener {
	private View popTopView;
	private PopupWindow window;
	private ImageView arrowIv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		popTopView = findViewById(R.id.pop_top_view);
		arrowIv = (ImageView) findViewById(R.id.arrow_iv);
		popTopView.setOnClickListener(this);
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_pop_window;
	}

	public PopupWindow showPopView(final Context context, final ImageView arrowIv, View popTopView,
			OnClickListener listener) {
		View view = View.inflate(context, R.layout.pop_item, null);
		PopupWindow window = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
		View contentView = window.getContentView();
		contentView.findViewById(R.id.btn_1).setOnClickListener(listener);
		contentView.findViewById(R.id.btn_2).setOnClickListener(listener);
		contentView.findViewById(R.id.btn_3).setOnClickListener(listener);
		contentView.findViewById(R.id.btn_11).setOnClickListener(listener);
		contentView.findViewById(R.id.btn_12).setOnClickListener(listener);
		contentView.findViewById(R.id.btn_13).setOnClickListener(listener);
		window.setBackgroundDrawable(new BitmapDrawable());
		window.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
				arrowIv.startAnimation(AnimationUtils.loadAnimation(context, R.anim.arrow_down));
			}
		});
		arrowIv.startAnimation(AnimationUtils.loadAnimation(context, R.anim.arrow_up));
		window.showAsDropDown(popTopView);
		return window;

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == popTopView.getId()) {
			if (window == null) {
				window = showPopView(mActivity, arrowIv, popTopView, this);
			} else {
				window.showAsDropDown(popTopView);
				arrowIv.startAnimation(AnimationUtils.loadAnimation(mActivity, R.anim.arrow_up));
			}
		} else if (v.getId() == R.id.btn_1) {
			ToastUtil.showLongToast(mActivity, "你点击了按钮1");
			window.dismiss();
		} else if (v.getId() == R.id.btn_2) {
			ToastUtil.showLongToast(mActivity, "你点击了按钮2");
			window.dismiss();
		} else if (v.getId() == R.id.btn_3) {
			ToastUtil.showLongToast(mActivity, "你点击了按钮3");
			window.dismiss();
		} else if (v.getId() == R.id.btn_11) {
			ToastUtil.showLongToast(mActivity, "你点击了按钮4");
			window.dismiss();
		} else if (v.getId() == R.id.btn_12) {
			ToastUtil.showLongToast(mActivity, "你点击了按钮5");
			window.dismiss();
		} else if (v.getId() == R.id.btn_13) {
			ToastUtil.showLongToast(mActivity, "你点击了按钮6");
			window.dismiss();
		}
	}

}
