package com.cslc.demo.view;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;

/**
 * 两行文字CheckBox
 * 
 * @author zhangyu
 *
 */
public class MulTextCheckBox extends CheckBox {
	private OnMulTextCheckedChangeListener listener;
	private Mode mode = Mode.VERTICAL;
	private int titleColor = 0xFF3C3D3D;
	private String title;
	private String subTitle;

	public MulTextCheckBox(Context context) {
		super(context);
		initView();
	}

	public MulTextCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	private void initView() {
		this.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					setChecked();
				} else {
					setUnCheck();
				}
				if (listener != null) {
					listener.onCheckedChanged(buttonView, isChecked);
				}
			}
		});
	}

	/**
	 * 设置标题
	 */
	public void setTitle(String title) {
		this.title = title;
		if (isChecked()) {
			setChecked();
		} else {
			setUnCheck();
		}
	}

	/**
	 * 设置副标题
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
		if (title == null) {
			return;
		}
		if (isChecked()) {
			setChecked();
		} else {
			setUnCheck();
		}
	}

	/**
	 * 设置标题和副标题
	 * 
	 * @param title
	 * @param subTitle
	 */
	public void setTitleAndSubTitle(String title, String subTitle) {
		setTitle(title);
		setSubTitle(subTitle);
	}

	/**
	 * 分隔符
	 * 
	 * @return
	 */
	private String getSepartor() {
		String separtor = "\n";
		if (mode == Mode.HORIZONTAL) {
			separtor = " ";
		}
		return separtor;
	}

	/**
	 * 获取字符串
	 * 
	 * @return
	 */
	private String getString() {
		String separtor = getSepartor();
		if (title == null) {
			return "";
		}
		if (subTitle == null) {
			return title + separtor;
		}
		return title + separtor + subTitle;
	}

	/**
	 * 选中
	 */
	private void setChecked() {
		String str = getString();
		int index = str.indexOf(getSepartor());
		SpannableString checkStr = new SpannableString(str);
		checkStr.setSpan(new ForegroundColorSpan(0xFFFFFFFF), 0, index, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		checkStr.setSpan(new AbsoluteSizeSpan(14, true), 0, index, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		checkStr.setSpan(new ForegroundColorSpan(0xFFFFFFFF), index + 1, str.length(),
				Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		checkStr.setSpan(new AbsoluteSizeSpan(12, true), index + 1, str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		setText(checkStr);
	}

	/**
	 * 未选中
	 */
	private void setUnCheck() {
		String str = getString();
		int index = str.indexOf(getSepartor());
		SpannableString unCheckStr = new SpannableString(str);
		unCheckStr.setSpan(new ForegroundColorSpan(titleColor), 0, index, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		unCheckStr.setSpan(new AbsoluteSizeSpan(14, true), 0, index, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		unCheckStr.setSpan(new ForegroundColorSpan(0xFF929393), index + 1, str.length(),
				Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		unCheckStr.setSpan(new AbsoluteSizeSpan(12, true), index + 1, str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
		setText(unCheckStr);
	}

	/**
	 * 设置是否选中不触发监听器
	 * 
	 * @param isChecked
	 */
	public void setCheckWithoutListener(boolean isChecked) {
		OnMulTextCheckedChangeListener listener = this.listener;
		setOnMulTextCheckedChangeListener(null);
		setChecked(isChecked);
		setOnMulTextCheckedChangeListener(listener);
	}

	/**
	 * 
	 * @param listener
	 */
	public void setOnMulTextCheckedChangeListener(OnMulTextCheckedChangeListener listener) {
		this.listener = listener;
	}

	/**
	 * 
	 * @param mode
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
	}

	/**
	 * 
	 * @param titleColor
	 */
	public void setTitleColor(int titleColor) {
		this.titleColor = titleColor;
	}

	public enum Mode {
		VERTICAL, HORIZONTAL
	}

	public static interface OnMulTextCheckedChangeListener {
		/**
		 * 状态改变
		 * 
		 * @param buttonView
		 * @param isChecked
		 */
		void onCheckedChanged(CompoundButton buttonView, boolean isChecked);
	}
}
