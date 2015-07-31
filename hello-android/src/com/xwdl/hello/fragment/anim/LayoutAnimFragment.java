package com.xwdl.hello.fragment.anim;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridLayout;

import com.xwdl.hello.R;

/**
 * LayoutTransition transition = new LayoutTransition();
 * transition.setAnimator(LayoutTransition.CHANGE_APPEARING,
 * transition.getAnimator(LayoutTransition.CHANGE_APPEARING));
 * transition.setAnimator(LayoutTransition.APPEARING, null);
 * transition.setAnimator(LayoutTransition.DISAPPEARING, null);
 * transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, null);
 * mGridLayout.setLayoutTransition(transition);
 * 
 * 
 * 过渡的类型一共有五种： 1) LayoutTransition.APPEARING 当一个View在ViewGroup中出现时，对此View设置的动画
 * 
 * 2)LayoutTransition.CHANGE_APPEARING
 * 当一个View在ViewGroup中出现时，对此View对其他View位置造成影响， 对其他View设置的动画
 * 
 * 3)LayoutTransition.DISAPPEARING 当一个View在ViewGroup中消失时，对此View设置的动画
 * 
 * 4) LayoutTransition.CHANGE_DISAPPEARING
 * 当一个View在ViewGroup中消失时，对此View对其他View位置造成影响，对其他View设置的动画
 * 
 * 5) LayoutTransition.CHANGE 不是由于View出现或消失造成对其他View位置造成影响，然后对其他View设置的动画。 api
 * 16，not enabled by default; it can be enabled via enableTransitionType(int).
 * 
 * 注意动画到底设置在谁身上，此View还是其他View。
 * 
 * @date 2015年7月31日
 */
public class LayoutAnimFragment extends Fragment implements OnCheckedChangeListener {

	private Context mContext;
	private GridLayout mGridLayout;
	private int mVal;
	private LayoutTransition mTransition; // api 11

	private CheckBox mAppear, mChangeAppear, mDisAppear, mChangeDisAppear;

	public static LayoutAnimFragment newInstance() {
		return new LayoutAnimFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mContext = getActivity().getApplicationContext();
	}

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment_layout_anim, container, false);
		Button addBtn = (Button) v.findViewById(R.id.add_btn);

		mAppear = (CheckBox) v.findViewById(R.id.id_appear);
		mChangeAppear = (CheckBox) v.findViewById(R.id.id_change_appear);
		mDisAppear = (CheckBox) v.findViewById(R.id.id_disappear);
		mChangeDisAppear = (CheckBox) v.findViewById(R.id.id_change_disappear);

		mAppear.setOnCheckedChangeListener(this);
		mChangeAppear.setOnCheckedChangeListener(this);
		mDisAppear.setOnCheckedChangeListener(this);
		mChangeDisAppear.setOnCheckedChangeListener(this);

		// 创建一个GridLayout
		mGridLayout = new GridLayout(mContext); // api 14
		// 设置每列5个按钮
		mGridLayout.setColumnCount(5);
		// 添加到布局中
		v.addView(mGridLayout);
		// 默认动画全部开启
		mTransition = new LayoutTransition();
		mGridLayout.setLayoutTransition(mTransition);

		addBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final Button button = new Button(mContext);
				button.setText((++mVal) + "");
				button.setTextAppearance(mContext, R.style.TextTheme);

				mGridLayout.addView(button, Math.min(1, mGridLayout.getChildCount()));

				button.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						mGridLayout.removeView(button);
					}
				});

			}
		});

		return v;
	}

	@SuppressLint("NewApi")
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		mTransition = new LayoutTransition();

		// mTransition.setAnimator(LayoutTransition.APPEARING,
		// (mAppear.isChecked() ?
		// mTransition.getAnimator(LayoutTransition.APPEARING) : null));
		ObjectAnimator anim = new ObjectAnimator();
		anim.setFloatValues(0.0f, 1.0f);
		anim.setPropertyName("scaleX");
		mTransition.setAnimator(LayoutTransition.APPEARING,
				(mAppear.isChecked() ? anim : null));

		mTransition.setAnimator(
				LayoutTransition.CHANGE_APPEARING,
				(mChangeAppear.isChecked() ? mTransition
						.getAnimator(LayoutTransition.CHANGE_APPEARING) : null));

		mTransition.setAnimator(LayoutTransition.DISAPPEARING,
				(mDisAppear.isChecked() ? mTransition.getAnimator(LayoutTransition.DISAPPEARING)
						: null));

		mTransition.setAnimator(
				LayoutTransition.CHANGE_DISAPPEARING,
				(mChangeDisAppear.isChecked() ? mTransition
						.getAnimator(LayoutTransition.CHANGE_DISAPPEARING) : null));

		mGridLayout.setLayoutTransition(mTransition);

	}
}
