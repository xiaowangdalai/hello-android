package com.xwdl.hello.fragment.lbs;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.xwdl.hello.R;

public class BaiduLbsListFragment extends Fragment {

	public static final String AK = "IXnH5sQsE7CWTfhGNoOqR7Xb";

	private Context mContext;

	private static final String[] DATA = new String[] { "定位", "地图", "周边" };
	
	public static final BaiduLbsListFragment newInstance() {
		return new BaiduLbsListFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mContext = getActivity().getApplicationContext();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_baidu_lbs_list, container, false);

		ListView list = (ListView) v.findViewById(R.id.list);
		ListAdapter adapter = new ArrayAdapter<String>(mContext, R.layout.my_simple_list_item_1,
				DATA);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
				FragmentManager fm = getFragmentManager();
				FragmentTransaction ft = null;

				switch (index) {
				case 0:
					ft = fm.beginTransaction();
					ft.replace(R.id.content, BaiduLocationFragment.newInstance());
					ft.addToBackStack(null);
					ft.commit();
					break;

				default:
					break;
				}
			}

		});

		return v;
	}
}
