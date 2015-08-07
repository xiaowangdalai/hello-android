package com.xwdl.hello.datastructure;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.xwdl.hello.R;
import com.xwdl.hello.fragment.anim.data.StructureListFragment;

public class DataStructureListActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_data_structrure_list);
		
		if (savedInstanceState == null) {
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.add(R.id.content, StructureListFragment.newInstance());
			ft.commit();
		}
	}
}
