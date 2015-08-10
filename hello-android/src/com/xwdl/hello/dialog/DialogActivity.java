package com.xwdl.hello.dialog;

import com.xwdl.hello.R;
import com.xwdl.hello.fragment.dialog.DialogListFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class DialogActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_dialog);
		
		if (savedInstanceState == null) {
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.add(R.id.content, DialogListFragment.newInstance());
			ft.commit();
		}
	}
}
