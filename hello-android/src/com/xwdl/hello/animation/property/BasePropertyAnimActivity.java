package com.xwdl.hello.animation.property;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.xwdl.hello.R;
import com.xwdl.hello.fragment.BasePropertyAnimListFrament;

/**
 * 属性动画（Property Animation）：Android 3.0（API 11）以上提供的动画。
 * Duration: 动画持续时间，默认300ms
 * Time Interpretation：时间差，类似LinearInterpolator、AccelerateDecelerateInterpolator
 * 定义动画的变化率
 * Repeat Count and Behavior： 重复次数，重复模式（从头开始还是反向）
 * Animator Set： 动画集合，定义一组动画，一起执行或者顺序执行
 * Frame Refresh Delay： 多久刷新一帧，默认10ms
 * 
 * 相关的类：
 * ObjectAnimatior
 * ValueAnimation
 * AnimatorSet: 一组动画，线性，一起，每个动画的先后执行等
 * TypeEvaluator： 类型估值，主要用于设置动画操作属性的值
 * TimeInterpretation
 * 
 * 属性动画就是，动画的执行类来设置动画操作的对象的属性、持续时间、开始和结束的属性值，时间
 * 差值等，然后系统会根据设置的参数动态的变化对象的属性。
 *
 * @date 2015年7月24日
 */
public class BasePropertyAnimActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_base_property_animation);
		
		if (savedInstanceState == null) {
			FragmentManager fm = getSupportFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			ft.add(R.id.content, BasePropertyAnimListFrament.newInstance());
			ft.commit();
		}
	}
	
	public void loadObjectAnimPage() {
		
	}
	
}
