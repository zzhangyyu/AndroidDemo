package com.cslc.demo.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cslc.demo.R;
import com.cslc.demo.util.ActivityUtil;
import com.cslc.demo.util.ToastUtil;

public class MainActivity extends BaseActivity implements OnItemClickListener {

    private long exitTime = 0;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    private ListView listView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listView = (ListView) findViewById(R.id.list_view);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
        initDemoList();
    }

    private void initDemoList() {
        List<Demo> demoDatas = new ArrayList<Demo>();
        demoDatas.add(new Demo("QrCode", "使用ZXing生成带Logo图案的二维码"));
        demoDatas.add(new Demo("NetWork", "判断手机当前的网络状态"));
        demoDatas.add(new Demo("CountDown", "在按钮上显示倒计时"));
        demoDatas.add(new Demo("WebView", "跳转本地assets目录下html文件"));
        demoDatas.add(new Demo("CustomWidget", "为Button、checkbox等定义统一的格式"));
        demoDatas.add(new Demo("MultiCheckbox", "自定义一个两行的checkbox"));
        demoDatas.add(new Demo("PopupWindow", "弹出框的使用"));
        demoDatas.add(new Demo("TextView", "TextView的详细使用方法"));
        demoDatas.add(new Demo("BottomTab", "使用Fragment实现底部导航栏功能"));
        demoDatas.add(new Demo("SlideBottomTab", "基于ViewPager和Fragment实现可以滑动的底部导航栏"));
        demoDatas.add(new Demo("Notification", "推送和删除消息"));
        demoDatas.add(new Demo("ImageLoad", "Listview异步加载图片"));
        demoDatas.add(new Demo("SlideTopTab", "顶部导航栏"));
        demoDatas.add(new Demo("RecyclerView", "RecyclerView使用练习"));
        demoDatas.add(new Demo("Dialog", "Dialog使用练习"));
        myAdapter.setDemoDatas(demoDatas);
    }

    public class MyAdapter extends BaseAdapter {
        public List<Demo> demoDatas;

        public MyAdapter() {
            demoDatas = new ArrayList<Demo>();
        }

        public void setDemoDatas(List<Demo> demoDatas) {
            this.demoDatas = demoDatas;
            this.notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return demoDatas.size();
        }

        @Override
        public Demo getItem(int position) {
            return demoDatas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Demo demo = getItem(position);
            viewHolder.bindData(demo);
            return viewHolder.view;
        }

    }

    class ViewHolder {
        public View view;
        public ImageView demoImg;
        public TextView demoTitle;
        public TextView demoDescription;

        public ViewHolder() {
            view = View.inflate(MainActivity.this, R.layout.activity_main_item, null);
            demoImg = (ImageView) view.findViewById(R.id.demo_img);
            demoTitle = (TextView) view.findViewById(R.id.title);
            demoDescription = (TextView) view.findViewById(R.id.description);
            view.setTag(this);
        }

        public void bindData(Demo demo) {
            demoTitle.setText(demo.demoTitle);
            demoDescription.setText(demo.demoDescription);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String demoTitle = myAdapter.getItem(position).demoTitle;
        if ("QrCode".equals(demoTitle)) {
            ActivityUtil.startActivity(mActivity, QrCodeActivity.class);
        }
        if ("NetWork".equals(demoTitle)) {
            ActivityUtil.startActivity(mActivity, NetStatusActivity.class);
        }
        if ("CountDown".equals(demoTitle)) {
            ActivityUtil.startActivity(mActivity, TimeCountActivity.class);
        }
        if ("WebView".equals(demoTitle)) {
            ActivityUtil.startActivity(mActivity, WebViewActivity.class);
        }
        if ("CustomWidget".equals(demoTitle)) {
            ActivityUtil.startActivity(mActivity, UniformWidgetActivity.class);
        }
        if ("MultiCheckbox".equals(demoTitle)) {
            ActivityUtil.startActivity(mActivity, MulTextCheckBoxActivity.class);
        }
        if ("PopupWindow".equals(demoTitle)) {
            ActivityUtil.startActivity(mActivity, PopWindowActivity.class);
        }
        if ("TextView".equals(demoTitle)) {
            ActivityUtil.startActivity(mActivity, TextViewActivity.class);
        }
        if ("BottomTab".equals(demoTitle)) {
            ActivityUtil.startActivity(mActivity, PageIndicatorActivity.class);
        }
        if ("SlideBottomTab".equals(demoTitle)) {
            ActivityUtil.startActivity(mActivity, SlideMenuActivity.class);
        }
        if ("Notification".equals(demoTitle)) {
            ActivityUtil.startActivity(mActivity, NotificationActivity.class);
        }
        if ("ImageLoad".equals(demoTitle)) {
            ActivityUtil.startActivity(mActivity, LoadImagActivity.class);
        }
        if ("SlideTopTab".equals(demoTitle)) {
            ActivityUtil.startActivity(mActivity, PagerSlidingActivity.class);
        }
        if ("RecyclerView".equals(demoTitle)) {
            ActivityUtil.startActivity(mActivity, RecyclerViewActivity.class);
        }
        if ("Dialog".equals(demoTitle)) {
            ActivityUtil.startActivity(mActivity, DialogActivity.class);
        }
    }

    public class Demo {
        public String demoTitle;
        public String demoDescription;

        public Demo(String demoTitle, String demoDescription) {
            this.demoTitle = demoTitle;
            this.demoDescription = demoDescription;
        }

    }

    /**
     * 退出APP
     */
    private void exitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.showToast(mActivity, "再按一次退出");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitApp();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
