package com.xwdl.hello.fragment.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.xwdl.hello.R;

public class DialogListFragment extends Fragment {
	
	private static final String[] DATA = new String[] {
		"Dialog Fragment",
		"Pop Window"
	};
	
	private Context mContext;
	
	public static final DialogListFragment newInstance() {
		return new DialogListFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = getActivity().getApplicationContext();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_dialog_list, container, false);
		
		ListView list = (ListView) v.findViewById(R.id.list);
		ListAdapter adapter = new ArrayAdapter<String>(mContext, R.layout.my_simple_list_item_1, 
				DATA);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
				switch (position) {
				case 0:
					DialogFragment1 df = DialogFragment1.newInstance();
					df.show(getFragmentManager(), "dialog");
					break;
					
				case 1:
					LinearLayout contentView = new LinearLayout(mContext);
					contentView.setBackgroundColor(0xff000000);
					
					Button aText = new Button(getActivity());
					aText.setText("弹出菜单");
					aText.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							Toast.makeText(mContext, "aaa", Toast.LENGTH_SHORT).show();
						}
					});
					contentView.addView(aText);
					
					PopupWindow window = new PopupWindow(contentView, LayoutParams.WRAP_CONTENT, 
							LayoutParams.WRAP_CONTENT, true);
//					window.setTouchable(true);
					window.setOutsideTouchable(true);
					window.setBackgroundDrawable(getResources().getDrawable(R.drawable.popupwindow_bg));
					window.showAsDropDown(view);
					break;

				default:
					break;
				}
			}
		});
		
		return v;
	}
}
