package com.xwdl.hello.fragment.anim;

import com.xwdl.hello.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LayoutAnimFragment extends Fragment {

	public static LayoutAnimFragment newInstance() {
		return new LayoutAnimFragment();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_layout_anim, container, false);
		return v;
	}
}
