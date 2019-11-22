package com.trekking.androtrek.BTComm;

import com.trekking.androtrek.R;
import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class DisplayConectado extends Activity {

	private int deviceIndex;
	private BluetoothDevice deviceToConnect;
	private AsyncClientComponent client;
	private EditText chatText;
	private EditText inputText;
	
	private UILink updater = new UILink()
	{
		@Override
		public void useData(String... args)
		{
			chatText.append(args[0] + "\n");
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_conectado);
		
		chatText = (EditText) findViewById(R.id.clientEditText);
//		inputText = (EditText) findViewById(R.id.clientInput);
		Bundle extras = this.getIntent().getExtras();
		deviceIndex = extras.getInt("index");
		deviceToConnect = DisplayRecebePlan.getDevice(deviceIndex);
		client = new AsyncClientComponent(deviceToConnect, updater, getApplicationContext());
		client.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_conectado, menu);
		return true;
	}
	
	public void onDestroy()
	{
		client.closeSockets();
		client.cancel(true);
		super.onDestroy();
	}

	public void SendClick(View view)
	{
		try
		{
			String text = inputText.getText().toString();
			chatText.append(MyDeviceData.name + ": " + text + "\n");
			client.write(MyDeviceData.name + ": " + text + "\n");
			inputText.setText("");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
