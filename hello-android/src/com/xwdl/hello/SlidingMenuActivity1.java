package com.xwdl.hello;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.xwdl.hello.myview.SlidingMenuLayout;

public class SlidingMenuActivity1 extends Activity {
	
	private SlidingMenuLayout sml;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_sliding_menu_1);
		
		sml = (SlidingMenuLayout) findViewById(R.id.sliding_menu_layout);
	}
	
	public void swapMenu(View view) {
		sml.swapMenu();
	}
}
