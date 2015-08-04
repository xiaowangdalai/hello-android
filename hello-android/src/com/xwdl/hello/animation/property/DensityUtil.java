package com.xwdl.hello.animation.property;

import android.content.Context;
import android.util.DisplayMetrics;

public class DensityUtil {

	// densityDpi
	private float dmDensityDpi = 0.0f;
	private DisplayMetrics dm;
	private float scale = 0.0f;

	public DensityUtil(Context context) {
		dm = new DisplayMetrics();

		dm = context.getApplicationContext().getResources().getDisplayMetrics();
		dmDensityDpi = dm.densityDpi;
		scale = getDmDensityDpi() / 160;

	}

	/**
	 * 
	 * @return
	 */
	public int getScreenWidth() {
		return dm.widthPixels;
	}

	/**
	 * 
	 * @return
	 */
	public int getScreenHeight() {
		return dm.heightPixels;
	}

	public float getDmDensityDpi() {
		return dmDensityDpi;
	}

	//
	// public void setDmDensityDpi(float dmDensityDpi) {
	// DensityUtil.dmDensityDpi = dmDensityDpi;
	// }
	//

	public int dip2px(float dipValue) {

		return (int) (dipValue * scale + 0.5f);

	}

	public int px2dip(float pxValue) {
		return (int) (pxValue / scale + 0.5f);
	}

	@Override
	public String toString() {
		return " dmDensityDpi:" + dmDensityDpi;
	}
}
