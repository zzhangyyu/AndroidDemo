package com.cslc.demo.util;

import java.util.Hashtable;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

import com.cslc.demo.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码工具类
 *
 * @author zhangyu
 */
public class QrcodeUtil {
    public static String TAG = "QrcodeUtil";

    /**
     * 生成二维码
     *
     * @param content
     * @return
     * @throws WriterException
     */
    public static Bitmap createQrcode(Activity mActivity, String content) {
        try {
            int qWidth = DensityUtil.dip2Px(mActivity, 300);
            int qHeight = qWidth;
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 容错级别
            hints.put(EncodeHintType.MARGIN, 4);// 默认4
            BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qWidth, qHeight);
            int width = matrix.getWidth();
            int height = matrix.getHeight();
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (matrix.get(x, y)) {
                        pixels[y * width + x] = 0xff000000;
                    }
                }
            }
            Bitmap qrcodeBm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            qrcodeBm.setPixels(pixels, 0, width, 0, 0, width, height);
            Bitmap logoBm = ((BitmapDrawable) mActivity.getResources().getDrawable(R.drawable.app_logo)).getBitmap();
            return addLogo(qrcodeBm, logoBm);
        } catch (WriterException e) {

        }
        return null;
    }

    /**
     * 添加logo
     *
     * @param src
     * @param logo
     * @return
     */
    private static Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }
        if (logo == null) {
            return src;
        }
        // 获取图片的宽高
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();
        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }
        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }
        // logo大小为二维码整体大小的1/5
        float scaleFactor = srcWidth * 1.0f / 8 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);
            canvas.save(Canvas.ALL_SAVE_FLAG);
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
        }
        return bitmap;
    }
}
