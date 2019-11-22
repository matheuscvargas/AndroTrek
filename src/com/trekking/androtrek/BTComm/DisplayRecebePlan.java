package com.trekking.androtrek.BTComm;

import com.trekking.androtrek.R;
import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class DisplayRecebePlan extends Activity {

	private ListView list;
	private ArrayAdapter<String> adapter;
	private Context context;
	private static BluetoothManager mngr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_recebe_plan);
		context = this;
		list = (ListView) findViewById(R.id.devicesList);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		list.setAdapter(adapter);
		list.setOnItemClickListener(itemListener);
		mngr = new BluetoothManager(this, adapter);
		mngr.checkDevices();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_recebe_plan, menu);
		return true;
	}
	OnItemClickListener itemListener = new OnItemClickListener()
	{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long ID)
		{
			Intent next = new Intent(DisplayRecebePlan.this, DisplayConectado.class);
			next.putExtra("index", position);
			mngr.stopDiscovery();
			context.startActivity(next);

		}

	};

	public static BluetoothDevice getDevice(int index)
	{
		return mngr.getDevice(index);
	}

	public void onDestroy()
	{
		mngr.stopDiscovery();
		mngr.destroy();
		mngr = null;
		super.onDestroy();
	}
}
