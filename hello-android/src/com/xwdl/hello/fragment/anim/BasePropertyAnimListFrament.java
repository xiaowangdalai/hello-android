package com.xwdl.hello.fragment.anim;

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

/**
 * Fragment使用注意：
 * 1、不要重写构造函数
 * 2、传参数的话用setArgument
 * 3、replace相当于move+add
 * 4、activity oncreate中添加要注意重复添加的问题
 * 5、尽量不要再oncreate， ui操作，onpostresume，
 * 
 *
 * @date 2015年7月28日
 */
public class BasePropertyAnimListFrament extends Fragment {
	
	private static final String[] LIST_DATA = new String[] {
		"ObjectAnimator",
		"ValueAnimator",
		"AnimatorSet"
	};
	
	private Context mContext;
	
	public static BasePropertyAnimListFrament newInstance() {
		return new BasePropertyAnimListFrament();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mContext = getActivity().getApplicationContext();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.frament_base_property_anim_list, container, false);
		
		ListView listview = (ListView) v.findViewById(R.id.list);
		
		ListAdapter adapter = new ArrayAdapter<String>(mContext, R.layout.my_simple_list_item_1, 
				LIST_DATA);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				FragmentManager fm = getFragmentManager();
				
				switch (position) {
				case 0:
					FragmentTransaction ftObjectAnim = fm.beginTransaction();
					ObjectAnimFragment oaf = ObjectAnimFragment.newInstance();
					ftObjectAnim.replace(R.id.content, oaf);
//					ftObjectAnim.hide(BasePropertyAnimListFrament.this);
//					ftObjectAnim.add(R.id.content, oaf);
					ftObjectAnim.addToBackStack(null);
					ftObjectAnim.commit();
					break;
					
				case 1:
					FragmentTransaction ftValueAnim = fm.beginTransaction();
					ValueAnimFragment vaf = ValueAnimFragment.newInstance();
					ftValueAnim.replace(R.id.content, vaf);
					ftValueAnim.addToBackStack(null);
					ftValueAnim.commit();
					break;
					
				case 2:
					FragmentTransaction ftAnimSet = fm.beginTransaction();
					AnimSetFragment asf = AnimSetFragment.newInstance();
					ftAnimSet.replace(R.id.content, asf);
					ftAnimSet.addToBackStack(null);
					ftAnimSet.commit();
					break;

				default:
					break;
				}
			}
			
		});
		
		return v;
	}
}
