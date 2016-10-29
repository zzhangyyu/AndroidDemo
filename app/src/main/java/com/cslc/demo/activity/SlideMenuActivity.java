package com.cslc.demo.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cslc.demo.R;
import com.cslc.demo.fragment.FirstPageFragment;
import com.cslc.demo.fragment.SecondPageFragment;
import com.cslc.demo.fragment.ThirdPageFragment;

/**
 * 可滑动的导航菜单
 *
 * @author zhangyu
 */
public class SlideMenuActivity extends BaseActivity
        implements ViewPager.OnPageChangeListener, RadioGroup.OnCheckedChangeListener {

    private RadioGroup tabRg;
    private RadioButton firstRb, secondRb, thirdRb;
    private ViewPager vpager;

    private MyFragmentPagerAdapter mAdapter;
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_slide_menu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firstRb = (RadioButton) findViewById(R.id.first_rb);
        secondRb = (RadioButton) findViewById(R.id.second_rb);
        thirdRb = (RadioButton) findViewById(R.id.third_rb);
        vpager = (ViewPager) findViewById(R.id.vpager);
        tabRg = (RadioGroup) findViewById(R.id.tab_rg);
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.setOnPageChangeListener(this);
        tabRg.setOnCheckedChangeListener(this);
        firstRb.setChecked(true);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.first_rb:
                vpager.setCurrentItem(0);
                break;
            case R.id.second_rb:
                vpager.setCurrentItem(1);
                break;
            case R.id.third_rb:
                vpager.setCurrentItem(2);
                break;
        }
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        private final int PAGER_COUNT = 3;
        private FirstPageFragment myFragment1 = null;
        private SecondPageFragment myFragment2 = null;
        private ThirdPageFragment myFragment3 = null;

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
            myFragment1 = new FirstPageFragment();
            myFragment2 = new SecondPageFragment();
            myFragment3 = new ThirdPageFragment();
        }

        @Override
        public int getCount() {
            return PAGER_COUNT;
        }

        @Override
        public Object instantiateItem(ViewGroup vg, int position) {
            return super.instantiateItem(vg, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            System.out.println("position Destory" + position);
            super.destroyItem(container, position, object);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = myFragment1;
                    break;
                case 1:
                    fragment = myFragment2;
                    break;
                case 2:
                    fragment = myFragment3;
                    break;
            }
            return fragment;
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case 0:
                    firstRb.setChecked(true);
                    break;
                case 1:
                    secondRb.setChecked(true);
                    break;
                case 2:
                    thirdRb.setChecked(true);
                    break;
            }
        }

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {

    }

}
