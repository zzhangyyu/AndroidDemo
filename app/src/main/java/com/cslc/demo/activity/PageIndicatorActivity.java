package com.cslc.demo.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;

import com.cslc.demo.R;
import com.cslc.demo.fragment.FirstPageFragment;
import com.cslc.demo.fragment.SecondPageFragment;
import com.cslc.demo.fragment.ThirdPageFragment;

public class PageIndicatorActivity extends BaseActivity implements OnCheckedChangeListener {
	private FirstPageFragment firstPageFragment = new FirstPageFragment();
	private SecondPageFragment secondPageFragment = new SecondPageFragment();
	private ThirdPageFragment thirdPageFragment = new ThirdPageFragment();
	private RadioButton firstRb, secondRb, thirdRb;
	private Fragment currentFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		firstRb = (RadioButton) findViewById(R.id.first_rb);
		secondRb = (RadioButton) findViewById(R.id.second_rb);
		thirdRb = (RadioButton) findViewById(R.id.third_rb);
		firstRb.setOnCheckedChangeListener(this);
		secondRb.setOnCheckedChangeListener(this);
		thirdRb.setOnCheckedChangeListener(this);
		firstRb.setChecked(true);
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_page_indicator;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			if (buttonView.getId() == R.id.first_rb) {
				switchFragment(firstPageFragment);
			} else if (buttonView.getId() == R.id.second_rb) {
				switchFragment(secondPageFragment);
			} else if (buttonView.getId() == R.id.third_rb) {
				switchFragment(thirdPageFragment);
			}
		}

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
