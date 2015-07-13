/**
 * 
 */
package com.xwdl.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 
 *
 */
public class SlidingMenuListActivity extends Activity {
	
	private static final String[] LIST_DATA = new String[] {
		"平移侧滑菜单"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_sliding_menu_list);
		
		ListView list = (ListView) findViewById(R.id.list);
		ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), 
				R.layout.my_simple_list_item_1, LIST_DATA);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				switch (position) {
				case 0:
					Intent it = new Intent(SlidingMenuListActivity.this, SlidingMenuActivity1.class);
					startActivity(it);
					break;

				default:
					break;
				}
			}
			
		});
	}

}
