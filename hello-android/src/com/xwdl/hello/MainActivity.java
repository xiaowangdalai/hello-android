package com.xwdl.hello;

import com.xwdl.hello.animation.property.PropertyAnimListActivity;
import com.xwdl.hello.datastructure.DataStructureListActivity;
import com.xwdl.hello.dialog.DialogActivity;
import com.xwdl.hello.lbs.LbsActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private static final String[] LIST_DATA = new String[] {
		"侧滑菜单",
		"属性动画",
		"地图",
		"数据结构",
		"对话框"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
	}

	private void initView() {
		ListView list = (ListView) findViewById(R.id.hello_list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), 
				R.layout.my_simple_list_item_1, LIST_DATA);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				switch (position) {
				case 0:
					openSlidingMenuListPage();
					break;
					
				case 1:
					startActivity(new Intent(MainActivity.this, PropertyAnimListActivity.class));
					break;
					
				case 2:
					startActivity(new Intent(MainActivity.this, LbsActivity.class));
					break;
					
				case 3:
					startActivity(new Intent(MainActivity.this, DataStructureListActivity.class));
					break;
					
				case 4:
					startActivity(new Intent(MainActivity.this, DialogActivity.class));
					break;

				default:
					break;
				}
			}
		});
	}
	
	private void openSlidingMenuListPage() {
		Intent it = new Intent(this, SlidingMenuListActivity.class);
		startActivity(it);
	}

}
