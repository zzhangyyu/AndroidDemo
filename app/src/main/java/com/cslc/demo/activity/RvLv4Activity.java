package com.cslc.demo.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.cslc.demo.R;
import com.cslc.demo.adapter.RvSimpleAdapter;
import com.cslc.demo.adapter.RvWaterfallAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyu on 2016/10/19.
 */

public class RvLv4Activity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        RvWaterfallAdapter mAdapter = new RvWaterfallAdapter(mActivity, mDatas);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_rv_lv1;
    }


    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }
}

