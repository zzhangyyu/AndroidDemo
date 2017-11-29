package com.cslc.demo.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.cslc.demo.R;
import com.cslc.demo.fragment.FirstPageFragment;
import com.cslc.demo.fragment.SecondPageFragment;
import com.cslc.demo.fragment.ThirdPageFragment;

/**
 * 使用Fragment实现底部导航栏功能
 */
public class PageIndicatorActivity2 extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;
    private Fragment currentFragment;
    private FirstPageFragment firstPageFragment = new FirstPageFragment();
    private SecondPageFragment secondPageFragment = new SecondPageFragment();
    private ThirdPageFragment thirdPageFragment = new ThirdPageFragment();

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_page_indicator2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.v_home_navigation);//底部导航栏view
        bottomNavigationView.setOnNavigationItemSelectedListener(this);//底部导航栏监听事件
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.first_tab) {
            switchFragment(firstPageFragment);
            return true;
        } else if (item.getItemId() == R.id.second_rb) {
            switchFragment(secondPageFragment);
            return true;
        } else if (item.getItemId() == R.id.third_tab) {
            switchFragment(thirdPageFragment);
            return true;
        }
        return false;
    }

    /**
     * 替换fragment
     *
     * @param fragment
     */
    private void switchFragment(Fragment fragment) {
        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        if (currentFragment != null) {
            transaction.hide(currentFragment);
        }
        boolean isAdd = fragment.isAdded();
        if (!isAdd) {
            transaction.add(R.id.fragment_view, fragment);
        } else {
            transaction.show(fragment);
        }
        transaction.commit();
        currentFragment = fragment;
    }
}
