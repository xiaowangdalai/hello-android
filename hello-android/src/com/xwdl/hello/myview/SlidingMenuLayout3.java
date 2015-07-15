/*
 * Copyright (c) FantasyValley. All Rights Reserved.
 *
 * SlidingMenuLayout3.java
 */
package com.xwdl.hello.myview;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.util.AttributeSet;

/**
 * @author Walden
 * @since 1.6
 */
public class SlidingMenuLayout3 extends SlidingMenuLayout {

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public SlidingMenuLayout3(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public SlidingMenuLayout3(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * @param context
	 */
	public SlidingMenuLayout3(Context context) {
		super(context);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		
		if (changed) {
			ViewHelper.setTranslationX(mMenu, mMenuWidth);
		}
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		
//		float xTranslation = 1.0f * l / mMenuWidth;
		
		float scale = l * 1.0f / mMenuWidth; // 1 - 0
		
		float rightScale = 0.7f + 0.3f * scale; // 1 - 0.7
		float leftScale = 1.0f - scale * 0.3f; // 0.7 - 1
		float leftAlpha = 0.6f + 0.4f * (1 - scale);
		
		ViewHelper.setTranslationX(mMenu, mMenuWidth * scale * 0.7f);
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		ViewHelper.setAlpha(mMenu, leftAlpha);
		
		ViewHelper.setPivotX(mContent, 0);
		ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);
	}

}
