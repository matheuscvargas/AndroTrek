package com.trekking.androtrek.BTComm;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.util.Log;
import com.trekking.androtrek.*;
import java.util.*;

public class ConnectionManager extends AsyncTask<Void, String, Void>
{
	private final BluetoothSocket mSocket;
	private final InputStream mInput;
	private final OutputStream mOutput;
	private final UILink mLink;
	private List<String> refs;

	public ConnectionManager(BluetoothSocket socket, UILink updater)
	{
		mSocket = socket;
		mLink = updater;
		InputStream tmpInput = null;
		OutputStream tmpOutput = null;
		refs = new ArrayList<String>();
		
		try
		{
			tmpInput = mSocket.getInputStream();
			tmpOutput = mSocket.getOutputStream();
		}
		catch (Exception er)
		{
			Log.d("BLT","Couldn't obtain the streams from socket!");
		}
		mInput = tmpInput;
		mOutput = tmpOutput;
	}

	public Void doInBackground(Void... params)
	{
		
		while (true)
		{
			try
			{
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mInput, "UTF-8"));
//				refs.add(bufferedReader.readLine());
				this.publishProgress("ConnMngr: " + bufferedReader.readLine() + " " + refs.size());
				Thread.sleep(80);
			}
			catch (Exception er)
			{
				Log.d("BLT",er.getMessage());
				break;
			}
		}
		return null;
	}

	protected void onProgressUpdate(String... strings)
	{
		if (mLink != null) 
			mLink.useData(strings);
	}

	public void write(String data)
	{
		try
		{
			mOutput.write(data.getBytes());
			mOutput.flush();
		}
		catch (Exception er)
		{

		}
	}

	public void stop()
	{
		try
		{
			mInput.close();
			mOutput.close();
			mSocket.close();
		}
		catch (Exception e)
		{
		}
	}
}
