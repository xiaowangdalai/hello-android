package com.xwdl.hello.fragment.dialog;

import com.xwdl.hello.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class DialogListFragment extends Fragment {
	
	private static final String[] DATA = new String[] {
		"Dialog Fragment"
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
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				switch (position) {
				case 0:
					DialogFragment1 df = DialogFragment1.newInstance();
					df.show(getFragmentManager(), "dialog");
					break;

				default:
					break;
				}
			}
		});
		
		return v;
	}
}
