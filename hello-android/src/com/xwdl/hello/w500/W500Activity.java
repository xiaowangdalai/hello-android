/*
 * Copyright (c) FantasyValley. All Rights Reserved.
 *
 * W500Activity.java
 */
package com.xwdl.hello.w500;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.xwdl.hello.R;

/**
 * @author Walden
 * @since 1.6
 */
public class W500Activity extends Activity {
	
	private static final String TAG = "W500Activity";
	
	private static final float TEXT_SIZE = 48.f;
	
	private static final int[] RED_BUMNER = new int[] {
		1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33
	};
	
	private static final int[] RED_BLUE = new int[] {
		1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16
	};
	
	private ViewGroup mVgRandomCode;
	private SurfaceView mSvRandomCode;
	private View mVStart;
	private TextView mTxtResult;
	
	private boolean mCanSurfaceUpdate;	
	private boolean mOnShow;
	
	private Paint mTxtPaint = new Paint();
	
	private float mRealTextSize = TEXT_SIZE;
	
	private Timer mTimer = null;
	private MyTimerTask mTimerTask = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_w500);
		
//		getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.window_bg));
		
		mVStart = findViewById(R.id.btn_start);
		mSvRandomCode = (SurfaceView) findViewById(R.id.sv_random_code);
		mVgRandomCode = (ViewGroup) findViewById(R.id.fl_random_code);
		mTxtResult = (TextView) findViewById(R.id.txt_result);
		
		// init the text paint
		WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		DisplayMetrics metric = new DisplayMetrics();
		display.getMetrics(metric);
		mRealTextSize = TEXT_SIZE * metric.density;
		
		mTxtPaint.setColor(Color.BLACK);
		mTxtPaint.setTextSize(mRealTextSize);
		mTxtPaint.setTextAlign(Align.CENTER);
		mTxtPaint.setAntiAlias(true);
		
//		mSvRandomCode.setZOrderOnTop(false);
		SurfaceHolder sh = mSvRandomCode.getHolder();
		if (sh != null) {
			sh.setFormat(PixelFormat.TRANSLUCENT);
			sh.addCallback(new SurfaceHolder.Callback() {
				
				@Override
				public void surfaceDestroyed(SurfaceHolder holder) {
					mCanSurfaceUpdate = false;
				}
				
				@Override
				public void surfaceCreated(SurfaceHolder holder) {
					mCanSurfaceUpdate = true;
					
					drawNumber("01");
				}

				@Override
				public void surfaceChanged(SurfaceHolder holder, int format, int width,
						int height) {
				}
			});
		}
		
		mVStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				v.setEnabled(false);
				
				startTimerTask();
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						Random random1 = new Random();
						
						ArrayList<Integer> red = new ArrayList<Integer>();
						ArrayList<Integer> result = new ArrayList<Integer>();
						
						for (int i = 1; i < 34; i++) {
							red.add(i);
						}
						
						for (int i = 0; i < 6; i++) {
							int a = random1.nextInt(red.size());
							
							result.add(red.remove(a));
						}
						
						Random random2 = new Random();
						int blue = random2.nextInt(16) + 1;
						result.add(blue);
						
						String str = "";
						for (int i = 0; i < result.size(); i++) {
							str += result.get(i) + " ";
						}
						
						final String text = str;
						mTxtResult.post(new Runnable() {
							
							@Override
							public void run() {
								mTxtResult.setText(text);
							}
						});
					}
				}).start();
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		mOnShow = true;
		mTimer = new Timer();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		mOnShow = false;
		mTimer.cancel();
		mTimer = null;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	private void startTimerTask() {
		if (mTimer != null) {
			mTimerTask = new MyTimerTask(this);
			mTimer.schedule(mTimerTask, 10, 100);
		}
	}
	
	private void drawNumber(String text) {
		if (TextUtils.isEmpty(text)) {
			Log.e(TAG, "Can not draw a empty text!");
			return;
		}
		
		if (mOnShow && mCanSurfaceUpdate) {
			SurfaceHolder holder = mSvRandomCode.getHolder();
			Canvas canvas = holder.lockCanvas();
			if (canvas != null) {
//				canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
				canvas.drawColor(Color.WHITE);
				
				float x = mSvRandomCode.getWidth() / 2;
//				float y = (mSvRandomCode.getHeight() + mRealTextSize) / 2;
				FontMetricsInt fontMetrics = mTxtPaint.getFontMetricsInt();
//				int baseline = targetRect.top + (targetRect.bottom - targetRect.top - 
//						fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
				float y = mSvRandomCode.getHeight() / 2 + 
						(fontMetrics.bottom - fontMetrics.ascent) / 2 - fontMetrics.descent;
				canvas.drawText(text, x, y, mTxtPaint);
			
				holder.unlockCanvasAndPost(canvas);
			} else {
				Log.e(TAG, "Canvas is null!");
			}
		} else {
			Log.d(TAG, "Timer fragment is not on show or has not been created, "
					+ "ignore update surface view.");
		}
	}
	
	private boolean isRed;
	
	static class MyTimerTask extends TimerTask {
		private boolean mStop = false;
		private WeakReference<W500Activity> mMyActivityRef = null;
		
		public MyTimerTask(W500Activity act) {
			mMyActivityRef = new WeakReference<W500Activity>(act);
		}
		
		public long startTime;
		private int mCurNumber = 1;

		@Override
		public void run() {
			W500Activity tf = mMyActivityRef.get();
			if (tf == null) {
				Log.e(TAG, "Fragment has been gc!");
				return;
			}
			
			String text = String.valueOf(mCurNumber++);
			if (text.length() < 2) {
				text = "0" + text;
			}
			tf.drawNumber(text);
			
			if (tf.isRed) {
				if (mCurNumber > 33) {
					mCurNumber = 1;
				}
			} else {
				if (mCurNumber > 16) {
					mCurNumber = 1;
				}
			}
			
			if (mStop) {
				
				long now = System.currentTimeMillis();
				if (now -  startTime > 5000) {
					cancel();
					Log.d(TAG, "Timer Task canceled...");
				}
			}
		}
				
		public void stop() {
			mStop = true;
		}
	}
}
