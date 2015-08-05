package com.xwdl.hello.lbs;

import com.xwdl.hello.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class LbsListActivity extends Activity {
	
	private static final String[] DATA = new String[] {
		"百度地图SDK"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_lbs_list);
		
		ListView list = (ListView) findViewById(R.id.list);
		ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.my_simple_list_item_1, 
				DATA);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
				switch (index) {
				case 0:
					startActivity(new Intent(LbsListActivity.this, BaiduSdkActivity.class));
					break;

				default:
					break;
				}
			}
			
		});
	}
}
