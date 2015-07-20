package com.xwdl.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class PropertyAnimationListActivity extends Activity {
	
	private static final String[] LIST_DATA = new String[] {
		"墨迹天气3.0"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_property_animation_list);
		
		ListView list = (ListView) findViewById(R.id.list);
		ListAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), 
				R.layout.my_simple_list_item_1, LIST_DATA);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
				switch (position) {
				case 0:
					startActivity(new Intent(PropertyAnimationListActivity.this,
							Moji3Activity.class));
					break;

				default:
					break;
				}
			}
			
		});
	}
}
