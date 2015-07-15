/*
 * Copyright (c) FantasyValley. All Rights Reserved.
 *
 * SlidingMenuActivity3.java
 */
package com.xwdl.hello;

import com.xwdl.hello.myview.SlidingMenuLayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * @author Walden
 * @since 1.6
 */
public class SlidingMenuActivity3 extends Activity {
	private SlidingMenuLayout sml;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_sliding_menu_3);
		sml = (SlidingMenuLayout) findViewById(R.id.sliding_menu_layout);
	}
	
	public void swapMenu(View view) {
		sml.swapMenu();
	}
}
