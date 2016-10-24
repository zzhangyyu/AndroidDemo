package com.cslc.demo.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cslc.demo.R;
import com.cslc.demo.dialog.QrcodeDialogFragment;
import com.cslc.demo.util.ActivityUtil;
import com.cslc.demo.util.StringUtil;
import com.cslc.demo.util.ToastUtil;

/**
 * 使用ZXing生成带Logo图案的二维码
 *
 * @author zhangyu
 */
public class QrCodeActivity extends BaseActivity implements View.OnClickListener {

    private Button generateQrBtn;
    private Button scanQrBtn;
    private EditText qrcodeEt;
    private TextView qrcodeContTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generateQrBtn = (Button) findViewById(R.id.generate_qr_btn);
        scanQrBtn = (Button) findViewById(R.id.scan_qr_btn);
        qrcodeEt = (EditText) findViewById(R.id.qrcode_et);
        qrcodeContTv = (TextView) findViewById(R.id.qrcode_content);
        generateQrBtn.setOnClickListener(this);
        scanQrBtn.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_qrcode;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == generateQrBtn.getId()) {
            String temp = qrcodeEt.getText().toString();
            if (StringUtil.isEmpty(temp)) {
                ToastUtil.showToast(mActivity, "请输入内容");
            } else {
                QrcodeDialogFragment qrDialog = QrcodeDialogFragment.getInstance((FragmentActivity) mActivity, "title", "content", "desc");
                qrDialog.setCancelable(true);
                qrDialog.show();
            }
        } else if (v.getId() == scanQrBtn.getId()) {
//            ActivityUtil.startActivity(mActivity, CaptureActivity.class);
            Intent intent = new Intent(mActivity, CaptureActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            qrcodeContTv.setText(scanResult);
        }
    }
}
