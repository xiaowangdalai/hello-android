package com.xwdl.hello.fragment.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;

import com.xwdl.hello.R;

public class DialogFragment1 extends DialogFragment {
	
	public static final DialogFragment1 newInstance() {
		return new DialogFragment1();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		View v = inflater.inflate(R.layout.fragment_dialog_1, container, false);
		
		View ok = v.findViewById(R.id.id_sure_edit_name);
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
		
		return v;
	}
}
