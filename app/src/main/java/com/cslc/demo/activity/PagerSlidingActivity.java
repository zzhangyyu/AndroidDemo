package com.cslc.demo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.cslc.demo.R;
import com.cslc.demo.fragment.FirstPageFragment;
import com.cslc.demo.view.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;


/**
 * 滚动导航效果
 *
 * @author weixiang.qin
 */
public class PagerSlidingActivity extends BaseActivity {
	private PagerSlidingTabStrip tabStrip;
	private ViewPager viewPager;
	private MyPagerAdapter adapter;
	private List<String> title = new ArrayList<String>();

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_effect_pagersliding;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tab_strip);
		viewPager = (ViewPager) findViewById(R.id.view_pager);
		title.add("推荐");
		title.add("附近");
		title.add("最新");
		title.add("半成品");
		title.add("外卖");
		title.add("定位");
		title.add("厨师");
		title.add("麻辣香锅");
		adapter = new MyPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);
		tabStrip.setViewPager(viewPager);
	}

	public class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return title.get(position);
		}

		@Override
		public int getCount() {
			return title.size();
		}

		@Override
		public Fragment getItem(int position) {
			FirstPageFragment fragment = new FirstPageFragment();
			Bundle bundle = new Bundle();
			bundle.putString("param", title.get(position));
			fragment.setArguments(bundle);
			return fragment;
		}

	}
}
