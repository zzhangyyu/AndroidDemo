package com.cslc.demo.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.cslc.demo.R;
import com.cslc.demo.util.ToastUtil;

public class TextViewActivity extends BaseActivity {

	private TextView iconTv;
	private TextView linkTv;
	private TextView spannableTv;
	private TextView diffClickTv;

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_textview;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		iconTv = (TextView) findViewById(R.id.icon_tv);
		linkTv = (TextView) findViewById(R.id.link_tv);
		spannableTv = (TextView) findViewById(R.id.spannable_tv);
		diffClickTv = (TextView) findViewById(R.id.diff_click_tv);
		initIconTv();
		linkTv.setMovementMethod(LinkMovementMethod.getInstance());
		initSpannableTv();
		initDiffClickTv();

	}

	/**
	 * 带图片的Textview 
	 * 1、Drawable[] drawable = txtZQD.getCompoundDrawables( );获得四个不同方向上的图片资源,数组元素依次是:左上右下的图片 
	 * 2、drawable[1].setBounds(100, 0, 200, 200);接着获得资源后,可以调用setBounds设置左上右下坐标点,比如这里设置了代表的是: 长是:从离文字最左边开始100dp处到200dp处宽是:从文字上方0dp处往上延伸200dp! 
	 * 3、txtZQD.setCompoundDrawables(drawable[0],drawable[1], drawable[2],drawable[3]);为TextView重新设置drawable数组!没有图片可以用null代替哦!
	 * PS：另外，从上面看出我们也可以直接在Java代码中调用setCompoundDrawables为 TextView设置图片！
	 */
	private void initIconTv() {
		Drawable[] drawable = iconTv.getCompoundDrawables();
		drawable[1].setBounds(0, 0, 50, 100);
		iconTv.setCompoundDrawables(drawable[0], drawable[1], drawable[2], drawable[3]);
	}

	/**
	 * 多个点击事件的Textview
	 */
	private void initDiffClickTv() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			sb.append("好友" + i + "，");
		}
		String likeUsers = sb.substring(0, sb.lastIndexOf("，")).toString();
		diffClickTv.setMovementMethod(LinkMovementMethod.getInstance());
		diffClickTv.setText(addClickPart(likeUsers), TextView.BufferType.SPANNABLE);
	}

	/**
	 * 同一个Textview设置不同样式
	 */
	private void initSpannableTv() {
		SpannableString span = new SpannableString("红色加大打电话斜体删除线绿色下划线图片:.");
		// 1.设置背景色,setSpan时需要指定的flag,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE(前后都不包括)
		span.setSpan(new ForegroundColorSpan(Color.RED), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		span.setSpan(new AbsoluteSizeSpan(25, true), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// 2.用超链接标记文本
		span.setSpan(new URLSpan("tel:4155551212"), 4, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// 3.用样式标记文本（斜体）
		span.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 7, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// 4.用删除线标记文本
		span.setSpan(new StrikethroughSpan(), 9, 12, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// 5.用下划线标记文本
		span.setSpan(new UnderlineSpan(), 12, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// 6.用颜色标记
		span.setSpan(new ForegroundColorSpan(Color.GREEN), 12, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		// 7.//获取Drawable资源
		Drawable d = getResources().getDrawable(R.drawable.ic_launcher);
		d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
		// 8.创建ImageSpan,然后用ImageSpan来替换文本
		ImageSpan imgspan = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
		span.setSpan(imgspan, 20, 21, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
		spannableTv.setText(span);
		spannableTv.setMovementMethod(LinkMovementMethod.getInstance());
	}

	// 定义一个点击每个部分文字的处理方法
	private SpannableStringBuilder addClickPart(String str) {
		// 创建一个SpannableStringBuilder对象，连接多个字符串
		SpannableString spanStr = new SpannableString("");
		SpannableStringBuilder ssb = new SpannableStringBuilder(spanStr);
		ssb.append(str);
		String[] likeUsers = str.split("，");
		if (likeUsers.length > 0) {
			for (int i = 0; i < likeUsers.length; i++) {
				final String name = likeUsers[i];
				final int start = str.indexOf(name) + spanStr.length();
				ssb.setSpan(new ClickableSpan() {
					@Override
					public void onClick(View widget) {
						ToastUtil.showToast(mActivity, name);
					}

					@Override
					public void updateDrawState(TextPaint ds) {
						super.updateDrawState(ds);
						// 删除下划线，设置字体颜色为蓝色
						ds.setColor(Color.BLUE);
						ds.setUnderlineText(false);
					}
				}, start, start + name.length(), 0);
			}
		}
		return ssb.append("等" + likeUsers.length + "个人觉得很赞");
	}

}
