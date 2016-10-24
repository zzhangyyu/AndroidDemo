package com.cslc.demo.dialog;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.cslc.demo.R;
import com.cslc.demo.util.QrcodeUtil;
import com.cslc.demo.util.StringUtil;

/**
 * 二维码对话框
 *
 * @author qinweixiang
 */
public class QrcodeDialogFragment extends BaseDialogFragment {
    private String title;
    private String content;
    private String desc;
    private TextView titleTv;
    private ImageView qrcode1Iv;
    private TextView descTv;

    public static QrcodeDialogFragment getInstance(FragmentActivity activity, String title, String content, String desc) {
        QrcodeDialogFragment fragment = new QrcodeDialogFragment();
        fragment.mActivity = activity;
        fragment.title = title;
        fragment.content = content;
        fragment.desc = desc;
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        setDialogWH(0.8);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
        View view = inflater.inflate(R.layout.fragment_dialog_qrcode, container);
        titleTv = (TextView) view.findViewById(R.id.title_tv);
        qrcode1Iv = (ImageView) view.findViewById(R.id.qrcode1_iv);
        descTv = (TextView) view.findViewById(R.id.desc_tv);
        if (StringUtil.isEmpty(title)) {
            titleTv.setVisibility(View.GONE);
        } else {
            titleTv.setText(title);
        }

        qrcode1Iv.setImageBitmap(QrcodeUtil.createQrcode(mActivity, content));

        if (StringUtil.isEmpty(desc)) {
            descTv.setVisibility(View.GONE);
        } else {
            descTv.setText(desc);
        }
        return view;
    }

    @Override
    public void show() {
        show("qrcodeDialog");
    }

}
