package com.xwdl.hello.myview;

import com.xwdl.hello.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;

public class SlidingMenuLayout extends HorizontalScrollView {

	private int mScreenWidth;

	/**
	 * 单位dp
	 */
	private int mMenuRightPadding = 60;

	private int mMenuWidth;

	private boolean init;

	private boolean isOpen;

	public SlidingMenuLayout(Context context) {
		this(context, null);
	}

	public SlidingMenuLayout(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mScreenWidth = outMetrics.widthPixels;

		// TypedArray ta = context.obtainStyledAttributes(attrs,
		// R.styleable.SlidingMenu);
		TypedArray ta = context.getTheme().obtainStyledAttributes(attrs,
				R.styleable.SlidingMenu, defStyleAttr, 0);
		
		mMenuRightPadding = ta.getDimensionPixelSize(
				R.styleable.SlidingMenu_manuRightPadding, (int) TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60,
								context.getResources().getDisplayMetrics()));
		ta.recycle();
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
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);

		if (changed) {
			scrollTo(mMenuWidth, 0);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (!init) {
			ViewGroup wrapper = (ViewGroup) this.getChildAt(0);
			View menu = wrapper.getChildAt(0);
			View content = wrapper.getChildAt(1);

			mMenuWidth = menu.getLayoutParams().width = mScreenWidth
					- mMenuRightPadding;
			content.getLayoutParams().width = mScreenWidth;

			init = true;
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
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
