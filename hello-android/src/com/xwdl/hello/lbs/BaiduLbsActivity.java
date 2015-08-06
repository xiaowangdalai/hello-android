package com.xwdl.hello.lbs;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.xwdl.hello.R;
import com.xwdl.hello.fragment.lbs.BaiduLbsListFragment;

public class BaiduLbsActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_baidu_lbs);
		
		if (savedInstanceState == null) {
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(R.id.content, BaiduLbsListFragment.newInstance());
			ft.commit();
		}
	}
}
