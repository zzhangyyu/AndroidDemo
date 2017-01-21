package com.cslc.demo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.cslc.demo.R;
import com.cslc.demo.dialog.CheckboxDialogFragment;
import com.cslc.demo.dialog.InputDialogFragment;
import com.cslc.demo.dialog.NormalDialogFragment;

/**
 * Created by Admin on 2016/10/31.
 */

public class DialogActivity extends BaseActivity implements View.OnClickListener {

    private Button dialog1;
    private Button dialog2;
    private Button dialog3;
    private Button dialog4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog1 = (Button) findViewById(R.id.dial_form1_btn);
        dialog2 = (Button) findViewById(R.id.dial_form2_btn);
        dialog3 = (Button) findViewById(R.id.dial_form3_btn);
        dialog4 = (Button) findViewById(R.id.dial_form4_btn);
        dialog1.setOnClickListener(this);
        dialog2.setOnClickListener(this);
        dialog3.setOnClickListener(this);
        dialog4.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_dialog;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == dialog1.getId()) {
            NormalDialogFragment normalDialogFragment = NormalDialogFragment.getInstance((FragmentActivity) mActivity, "Title", "Content");
            normalDialogFragment.setCancelable(true);
            normalDialogFragment.show();
        }

        if (v.getId() == dialog2.getId()) {
            InputDialogFragment inputDialogFragment = InputDialogFragment.getInstance((FragmentActivity) mActivity, "Title");
            inputDialogFragment.setCancelable(true);
            inputDialogFragment.show();
        }
        if (v.getId() == dialog3.getId()) {
            CheckboxDialogFragment checkboxDialogFragment=CheckboxDialogFragment.getInstance((FragmentActivity) mActivity,"Title");
            checkboxDialogFragment.setCancelable(true);
            checkboxDialogFragment.show();
        }

    }
}
