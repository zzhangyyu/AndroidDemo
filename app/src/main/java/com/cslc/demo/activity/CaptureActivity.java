package com.cslc.demo.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.cslc.demo.R;
import com.cslc.demo.third.zxing.camera.CameraManager;
import com.cslc.demo.third.zxing.camera.PreviewFrameShotListener;
import com.cslc.demo.third.zxing.camera.Size;
import com.cslc.demo.third.zxing.decode.DecodeListener;
import com.cslc.demo.third.zxing.decode.DecodeThread;
import com.cslc.demo.third.zxing.decode.LuminanceSource;
import com.cslc.demo.third.zxing.decode.PlanarYUVLuminanceSource;
import com.cslc.demo.third.zxing.decode.RGBLuminanceSource;
import com.cslc.demo.third.zxing.view.CaptureView;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;

/**
 * Created by zhangyu on 2016/10/14.
 */

public class CaptureActivity extends BaseActivity
        implements SurfaceHolder.Callback, PreviewFrameShotListener, DecodeListener {
    private static final long VIBRATE_DURATION = 200;
    private SurfaceView previewSv;
    private CaptureView captureView;
    private CameraManager mCameraManager;
    private DecodeThread mDecodeThread;
    private Rect previewFrameRect = null;
    private boolean isDecoding = false;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_capture;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        previewSv = (SurfaceView) findViewById(R.id.sv_preview);
        captureView = (CaptureView) findViewById(R.id.cv_capture);
        previewSv.getHolder().addCallback(this);
        mCameraManager = new CameraManager(this);
        mCameraManager.setPreviewFrameShotListener(this);
    }

    @Override
    public void foundPossibleResultPoint(ResultPoint point) {
        captureView.addPossibleResultPoint(point);
    }

    @Override
    public void onDecodeSuccess(Result result, LuminanceSource source, Bitmap bitmap) {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(VIBRATE_DURATION);
        isDecoding = false;
        String resultString = result.getText();
        scanCode(resultString);
        finish();
    }

    private void scanCode(String resultString) {
        Intent resultIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("result", resultString);
        resultIntent.putExtras(bundle);
        setResult(RESULT_OK,resultIntent);
        //        requestPreview();
    }

    /**
     *
     */
    private void requestPreview() {
        isDecoding = false;
        mCameraManager.startPreview();
        mCameraManager.requestPreviewFrameShot();
    }

    @Override
    public void onDecodeFailed(LuminanceSource source) {
        if (source instanceof RGBLuminanceSource) {
        }
        isDecoding = false;
        mCameraManager.requestPreviewFrameShot();
    }

    @Override
    public void onPreviewFrame(byte[] data, Size frameSize) {
        if (mDecodeThread != null) {
            mDecodeThread.cancel();
        }
        if (previewFrameRect == null) {
            previewFrameRect = mCameraManager.getPreviewFrameRect(captureView.getFrameRect());
        }
        PlanarYUVLuminanceSource luminanceSource = new PlanarYUVLuminanceSource(data, frameSize, previewFrameRect);
        mDecodeThread = new DecodeThread(luminanceSource, CaptureActivity.this);
        isDecoding = true;
        mDecodeThread.execute();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mCameraManager.initCamera(holder);
        if (!mCameraManager.isCameraAvailable()) {
            finish();
            return;
        }
        mCameraManager.startPreview();
        if (!isDecoding) {
            mCameraManager.requestPreviewFrameShot();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCameraManager.stopPreview();
        if (mDecodeThread != null) {
            mDecodeThread.cancel();
        }
        mCameraManager.release();
    }
}

