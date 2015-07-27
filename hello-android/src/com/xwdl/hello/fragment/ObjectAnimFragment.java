package com.xwdl.hello.fragment;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xwdl.hello.R;

public class ObjectAnimFragment extends Fragment {
	
	private Context mContext;

	public static ObjectAnimFragment newInstance() {
		return new ObjectAnimFragment();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = getActivity().getApplicationContext();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_object_anim, container, false);
		final ImageView image1 = (ImageView) v.findViewById(R.id.image1);
		image1.setOnClickListener(new View.OnClickListener() {
			
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@Override
			public void onClick(View v) {
//				ObjectAnimator.ofFloat(image1, "rotationX", 0.0f, 360.0f)
//					.setDuration(500).start();
				Animator anim = AnimatorInflater.loadAnimator(mContext, R.anim.object_anim_rotation_y);
				anim.setTarget(v);
				anim.start();
			}
		});
		
		final ImageView image2 = (ImageView) v.findViewById(R.id.image2);
		image2.setOnClickListener(new View.OnClickListener() {
			
			@TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@Override
			public void onClick(View v) {
				ObjectAnimator anim = ObjectAnimator.ofFloat(image2, "lj", 1.0f, 0.0f).setDuration(500);
				anim.start();
				anim.addUpdateListener(new AnimatorUpdateListener() {
					
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						float value = (Float) animation.getAnimatedValue();
						image2.setAlpha(value);
						image2.setScaleX(value);
						image2.setScaleY(value);
					}
				});
			}
		});
		
		return v;
	}
}
