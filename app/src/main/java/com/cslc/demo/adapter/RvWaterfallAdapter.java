package com.cslc.demo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2016/10/31.
 */

public class RvWaterfallAdapter extends RvSimpleAdapter {
    List<Integer> mHeights;

    public RvWaterfallAdapter(Context context, List<String> mDatas) {
        super(context, mDatas);
        mHeights = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            int height = (int) (100 + Math.random() * 300);
            mHeights.add(height);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        ViewGroup.LayoutParams mLayoutParams = myViewHolder.itemView.getLayoutParams();     //这里的子布局只能使用相对布局才有效
        mLayoutParams.height = mHeights.get(position);
        myViewHolder.itemView.setLayoutParams(mLayoutParams);
        super.onBindViewHolder(myViewHolder, position);
    }


}
