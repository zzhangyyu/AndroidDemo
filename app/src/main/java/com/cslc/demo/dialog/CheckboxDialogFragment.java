package com.cslc.demo.dialog;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.cslc.demo.R;
import com.cslc.demo.util.StringUtil;

/**
 * 普通dialog
 *
 * @author zhangyu
 */
public class CheckboxDialogFragment extends BaseDialogFragment {
    private String title;
    private boolean lineVisible = true;
    private boolean sureVisible = true;
    private boolean cancelVisible = true;
    private String sureBtnText;
    private String cancelBtnText;
    private View.OnClickListener sureListener;
    private View.OnClickListener cancelListener;
    private boolean isTitleVisible = true;
    private TextView titleTv;
    private View lineView;
    private Button cancelBtn;
    private Button sureBtn;

    public static CheckboxDialogFragment getInstance(FragmentActivity activity, String title) {
        CheckboxDialogFragment fragment = new CheckboxDialogFragment();
        fragment.mActivity = activity;
        fragment.title = title;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
        View view = inflater.inflate(R.layout.fragment_dialog_checkbox, container);
        titleTv = (TextView) view.findViewById(R.id.title_tv);
        lineView = view.findViewById(R.id.line_view);
        cancelBtn = (Button) view.findViewById(R.id.cancel_btn);
        sureBtn = (Button) view.findViewById(R.id.sure_btn);
        titleTv.setVisibility(isTitleVisible ? View.VISIBLE : View.GONE);
        titleTv.setText(title == null ? "" : title);
        lineView.setVisibility(lineVisible ? View.VISIBLE : View.GONE);
        sureBtn.setVisibility(sureVisible ? View.VISIBLE : View.GONE);
        cancelBtn.setVisibility(cancelVisible ? View.VISIBLE : View.GONE);
        if (!StringUtil.isEmpty(sureBtnText)) {
            sureBtn.setText(sureBtnText);
        }
        if (!StringUtil.isEmpty(cancelBtnText)) {
            cancelBtn.setText(cancelBtnText);
        }
        sureBtn.setOnClickListener(sureListener != null ? sureListener : this);
        cancelBtn.setOnClickListener(cancelListener != null ? cancelListener : this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setDialogWH(0.8);
    }

    @Override
    public void show() {
        show("normalDialog");
    }

    public void setTitleInVisible() {
        isTitleVisible = false;
    }

    public void setSureBtnText(int sureBtnTextId) {
        this.sureBtnText = mActivity.getString(sureBtnTextId);
    }

    public void setSureBtnText(String sureBtnText) {
        this.sureBtnText = sureBtnText;
    }

    public void setCancelBtnText(int cancelBtnTextId) {
        this.cancelBtnText = mActivity.getString(cancelBtnTextId);
    }

    public void setCancelBtnText(String cancelBtnText) {
        this.cancelBtnText = cancelBtnText;
    }

    public void setSureVisible(boolean visible) {
        if (!visible) {
            lineVisible = false;
        }
        sureVisible = visible;
    }

    public void setCancelVisible(boolean visible) {
        if (!visible) {
            lineVisible = false;
        }
        cancelVisible = visible;
    }

    public void setSureListener(View.OnClickListener sureListener) {
        this.sureListener = sureListener;
    }

    public void setCancelListener(View.OnClickListener cancelListener) {
        this.cancelListener = cancelListener;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == sureBtn.getId()) {
            this.dismiss();
        } else if (v.getId() == cancelBtn.getId()) {
            this.dismiss();
        }
    }
}
