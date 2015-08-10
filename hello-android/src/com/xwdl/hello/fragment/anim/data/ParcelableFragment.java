package com.xwdl.hello.fragment.anim.data;

import com.xwdl.hello.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ParcelableFragment extends Fragment {
	
	private static final String NAME = "小往大来";
	private static final String DESCRIPTION = "潜龙勿用";

	public static final ParcelableFragment newInstance(MyParcelable myParcelable) {
		ParcelableFragment pf = new ParcelableFragment();
		
		Bundle bundle = new Bundle();
		bundle.putParcelable("aa", myParcelable);
		pf.setArguments(bundle);
		
		return pf;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View content = inflater.inflate(R.layout.fragment_parcelable, container, false);
		
		TextView src = (TextView) content.findViewById(R.id.src);
		Button btn = (Button) content.findViewById(R.id.btn);
		final TextView target = (TextView) content.findViewById(R.id.target);
		
		src.setText("name: " + NAME + "\ndescription: " + DESCRIPTION);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Bundle bd = getArguments();
				MyParcelable mp = bd.getParcelable("aa");
				if (mp != null) {
					target.setText("" + mp.getData());
				}
			}
		});
		
		return content;
	}
}
