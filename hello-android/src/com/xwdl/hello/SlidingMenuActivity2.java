/*
 * Copyright (c) FantasyValley. All Rights Reserved.
 *
 * SlidingMenuActivity2.java
 */
package com.xwdl.hello;

import android.os.Bundle;
import android.view.View;

import com.xwdl.hello.myview.SlidingMenuLayout;

/**
 * @author Walden
 * @since 1.6
 */
public class SlidingMenuActivity2 extends SlidingMenuActivity1 {
	
	private SlidingMenuLayout sml;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_sliding_menu_2);
		
		sml = (SlidingMenuLayout) findViewById(R.id.sliding_menu_layout);
	}
	
	public void swapMenu(View view) {
		sml.swapMenu();
	}
}
