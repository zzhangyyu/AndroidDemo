package com.cslc.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cslc.demo.R;
import com.cslc.demo.util.ToastUtil;

import java.util.List;

/**
 * Created by Admin on 2016/10/31.
 */

public class RvSimpleAdapter extends RecyclerView.Adapter<RvSimpleAdapter.MyViewHolder> {
    protected Context mContext;
    protected List<String> mDatas;
    private LayoutInflater mLayoutInflater;

    public RvSimpleAdapter(Context context, List<String> mDatas) {
        this.mContext = context;
        this.mDatas = mDatas;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_recyclerview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int position) {
        myViewHolder.tv.setText(mDatas.get(position));
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = myViewHolder.getPosition();
                String str = myViewHolder.tv.getText().toString();
                ToastUtil.showToast(mContext, "you click " + str + " at " + pos);
            }
        });

        myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = myViewHolder.getPosition();
                String str = myViewHolder.tv.getText().toString();
                ToastUtil.showToast(mContext, "you long click " + str + " at " + pos);
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.rv_item);
        }
    }
}
