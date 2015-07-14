package com.xwdl.hello.myview;

import com.xwdl.hello.R;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

/**
 * @date 2015年7月14日
 */
public class SlidingMenuLayout extends HorizontalScrollView {

	protected int mScreenWidth;

	/**
	 * 单位dp
	 */
	protected int mMenuRightPadding = 60;

	protected int mMenuWidth;

	protected boolean init;

	protected boolean isOpen;
	
	protected ViewGroup mWrapper;
	protected View mMenu;
	protected View mContent;
	
	protected WindowManager mWindowManager;

	public SlidingMenuLayout(Context context) {
		this(context, null);
	}

	public SlidingMenuLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		System.out.println("[[[ defStyleAttr:" + defStyleAttr);

		mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		getScreenWidth();
		
		// TypedArray ta = context.obtainStyledAttributes(attrs,
		// R.styleable.SlidingMenu);
		TypedArray ta = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.SlidingMenuLayout, defStyleAttr, 0);
		
		mMenuRightPadding = ta.getDimensionPixelSize(
				R.styleable.SlidingMenuLayout_manuRightPadding, (int) TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60,
								context.getResources().getDisplayMetrics()));
		ta.recycle();
	}
	
	protected void getScreenWidth() {
		DisplayMetrics outMetrics = new DisplayMetrics();
		mWindowManager.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;
	}

	public SlidingMenuLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public void openMenu() {
		if (isOpen) {
			return;
		}

		smoothScrollTo(0, 0);
		isOpen = true;
	}

	public void closeMenu() {
		if (isOpen) {
			smoothScrollTo(mMenuWidth, 0);
			isOpen = false;
		}
	}

	public void swapMenu() {
		if (isOpen) {
			closeMenu();
		} else {
			openMenu();
		}
	}

	@Override
	protected void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		
		System.out.println("[[[ configuration changed !");
		init = false;
		getScreenWidth();
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);

		if (changed) {
			scrollTo(mMenuWidth, 0);
		}
		
		System.out.println("[[[ onLayout ! changed: " + changed);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (!init) {
			measureMenu();
			init = true;
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		System.out.println("[[[ onMeasure !");
	}
	
	protected void measureMenu() {
		mWrapper = (ViewGroup) this.getChildAt(0);
		mMenu = mWrapper.getChildAt(0);
		mContent = mWrapper.getChildAt(1);
		
		mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth- mMenuRightPadding;
		mContent.getLayoutParams().width = mScreenWidth;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			if (getScrollX() <= mMenuWidth / 2) {
				this.smoothScrollTo(0, 0);
				isOpen = true;
			} else {
				this.smoothScrollTo(mMenuWidth, 0);
				isOpen = false;
			}
			return true;
		}

		return super.onTouchEvent(ev);
	}

}
