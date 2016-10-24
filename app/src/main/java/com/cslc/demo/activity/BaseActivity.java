package com.cslc.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Activity父类
 */
public abstract class BaseActivity extends FragmentActivity {
    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        mActivity = this;
    }

    /**
     * 布局文件
     *
     * @return
     */
    protected abstract int getLayoutResource();

}
