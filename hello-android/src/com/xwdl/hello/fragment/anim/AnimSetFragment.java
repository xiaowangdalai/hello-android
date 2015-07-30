package com.xwdl.hello.fragment.anim;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import com.xwdl.hello.R;

public class AnimSetFragment extends Fragment {
	
	public static AnimSetFragment newInstance() {
		return new AnimSetFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_anim_set, container, false);
		
		final View ball = v.findViewById(R.id.ball);
		
		Button btn1 = (Button) v.findViewById(R.id.btn1);
		Button btn2 = (Button) v.findViewById(R.id.btn2);
		
		btn1.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				ObjectAnimator anim1 = ObjectAnimator.ofFloat(ball, "scaleX", 1.0f, 2.0f);
				ObjectAnimator anim2 = ObjectAnimator.ofFloat(ball, "scaleY", 1.0f, 2.0f);
				
				AnimatorSet set = new AnimatorSet();
				set.setDuration(2000);
				set.setInterpolator(new LinearInterpolator());
				set.playTogether(anim1, anim2);
				set.start();
			}
		});
		
		btn2.setOnClickListener(new View.OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				float x = ball.getX();
				
				ObjectAnimator anim1 = ObjectAnimator.ofFloat(ball, "scaleX", 1.0f, 2.0f);
				ObjectAnimator anim2 = ObjectAnimator.ofFloat(ball, "scaleY", 1.0f, 2.0f);
				ObjectAnimator anim3 = ObjectAnimator.ofFloat(ball, "x", x, 0f);
				ObjectAnimator anim4 = ObjectAnimator.ofFloat(ball, "x", 0f, x);
				
				AnimatorSet set = new AnimatorSet();
				set.play(anim1).with(anim2);
				set.play(anim2).with(anim3);
				set.play(anim4).after(anim3);
				set.setDuration(2000);
				set.start();
			}
		});
		
		return v;
	}
}
