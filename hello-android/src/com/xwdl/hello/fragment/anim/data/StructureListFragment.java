package com.xwdl.hello.fragment.anim.data;

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

public class StructureListFragment extends Fragment {
	
	private Context mContext;
	
	public static final String[] DATA = new String[] {
		"Parcelable"
	};

	public static final StructureListFragment newInstance() {
		return new StructureListFragment();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = getActivity().getApplicationContext();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View content = inflater.inflate(R.layout.fragment_data_structure_list, container, false);
		
		ListView list = (ListView) content.findViewById(R.id.list);
		ListAdapter adapter = new ArrayAdapter<String>(mContext, R.layout.my_simple_list_item_1, DATA);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				FragmentManager fm = getFragmentManager();
				
				switch (position) {
				case 0:
					FragmentTransaction ft = fm.beginTransaction();
					ft.replace(R.id.content, ParcelableFragment.newInstace());
					ft.addToBackStack(null);
					ft.commit();
					break;

				default:
					break;
				}
			}
			
		});
		
		return content;
	}
}
