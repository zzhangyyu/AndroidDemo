package com.cslc.demo.util;

import android.content.Context;

/**
 * Density工具类
 * 
 * @author zhangyu
 *
 */
public class DensityUtil {
	/**
	 * dip转换为px
	 *
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static int dip2Px(Context context, float dipValue) {
		final float scale = getDensity(context);
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * px转dip
	 *
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = getDensity(context);
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * @param context
	 * @return
	 */
	public static float getDensity(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}
}
