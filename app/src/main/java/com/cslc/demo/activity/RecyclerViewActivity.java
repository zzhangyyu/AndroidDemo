package com.cslc.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cslc.demo.R;
import com.cslc.demo.util.ActivityUtil;

/**
 * Created by zhangyu on 2016/10/19.
 */

public class RecyclerViewActivity extends BaseActivity implements View.OnClickListener {

    private Button rvLv1Btn;
    private Button rvLv2Btn;
    private Button rvLv3Btn;
    private Button rvLv4Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rvLv1Btn = (Button) findViewById(R.id.rv_lv1_btn);
        rvLv2Btn = (Button) findViewById(R.id.rv_lv2_btn);
        rvLv3Btn = (Button) findViewById(R.id.rv_lv3_btn);
        rvLv4Btn = (Button) findViewById(R.id.rv_lv4_btn);
        rvLv1Btn.setOnClickListener(this);
        rvLv2Btn.setOnClickListener(this);
        rvLv3Btn.setOnClickListener(this);
        rvLv4Btn.setOnClickListener(this);
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_recyclerview;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == rvLv1Btn.getId()) {
            ActivityUtil.startActivity(mActivity, RvLv1Activity.class);
        }

        if (v.getId() == rvLv2Btn.getId()) {
            ActivityUtil.startActivity(mActivity, RvLv2Activity.class);
        }
        if (v.getId() == rvLv3Btn.getId()) {
            ActivityUtil.startActivity(mActivity, RvLv3Activity.class);
        }

        if (v.getId() == rvLv4Btn.getId()) {
            ActivityUtil.startActivity(mActivity, RvLv4Activity.class);
        }
    }
}
