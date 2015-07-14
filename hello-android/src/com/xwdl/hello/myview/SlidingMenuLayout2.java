package com.xwdl.hello.myview;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.util.AttributeSet;

public class SlidingMenuLayout2 extends SlidingMenuLayout {

	public SlidingMenuLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public SlidingMenuLayout2(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SlidingMenuLayout2(Context context) {
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
		
		ViewHelper.setTranslationX(mMenu, l);
	}

}
