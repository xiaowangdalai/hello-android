package com.xwdl.hello.fragment;

import com.xwdl.hello.R;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.BoringLayout.Metrics;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

public class ValueAnimFragment extends Fragment {
	
	private Context mContext;
	private int mScreenHeight;

	public static ValueAnimFragment newInstance() {
		return new ValueAnimFragment();
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = getActivity().getApplicationContext();
		WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		DisplayMetrics metrics = new DisplayMetrics();
		display.getMetrics(metrics);
		mScreenHeight = metrics.heightPixels;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_value_anim, container, false);
		
		final View ball = v.findViewById(R.id.id_ball);
		
		Button btn1 = (Button) v.findViewById(R.id.btn1);
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(final View v) {
				ValueAnimator anim = ValueAnimator.ofFloat(0, mScreenHeight - ball.getHeight());
				anim.setTarget(ball);
				anim.setDuration(1000).start();
				anim.addUpdateListener(new AnimatorUpdateListener() {
					
					@SuppressLint("NewApi")
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						ball.setTranslationY((Float) animation.getAnimatedValue());
					}
				});
			}
		});
		
		Button btn2 = (Button) v.findViewById(R.id.btn2);
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				ValueAnimator anim = ValueAnimator.ofObject(new TypeEvaluator<PointF>() {

					// fraction = t / duration
					@Override
					public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
						PointF point = new PointF();
						point.x = 120 * fraction * 3;
						point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
						return point;
					}
					
				}, new PointF(0, 0));
				
				anim.setDuration(3000);
				anim.setInterpolator(new LinearInterpolator());
				anim.addUpdateListener(new AnimatorUpdateListener() {
					
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						PointF point = (PointF) animation.getAnimatedValue();
						ball.setX(point.x);
						ball.setY(point.y);
					}
				});
				
				anim.start();
			}
		});
		
		return v;
	}
	
}
