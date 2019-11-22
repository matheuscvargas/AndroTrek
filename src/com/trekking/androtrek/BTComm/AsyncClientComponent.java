package com.trekking.androtrek.BTComm;

import java.io.IOException;
import java.util.UUID;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.util.Log;
import android.content.*;

public class AsyncClientComponent extends AsyncTask<Void, String, Void>
{
	private BluetoothSocket mDataSocket;
	private final BluetoothDevice mDevice;
	private final UILink mUpdater;
	private ConnectionManager mManager;
	
	private Context c;
	
	public AsyncClientComponent(BluetoothDevice device, UILink UIUpdater, Context c)
	{
		this.c = c;
		BluetoothSocket tmp = null;
		mUpdater = UIUpdater;
		mDevice = device;
		try
		{
			tmp = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
		}
		catch (IOException er)
		{
		}
		mDataSocket = tmp;
	}

	protected Void doInBackground(Void... params)
	{
		try
		{
			mDataSocket.connect();
		}
		catch (Exception connectEr)
		{
			try
			{
				Log.d("BLT", connectEr.getMessage());
				this.publishProgress("Connection to " + mDataSocket.getRemoteDevice().getName() + " has failed!", "system");
				mDataSocket.close();
				return null;
			}
			catch (IOException closeEr)
			{
				Log.d("BLT", closeEr.getMessage());
				this.publishProgress("Connection to " + mDataSocket.getRemoteDevice().getName() + " has failed!", "system");
				return null;
			}
		}
		mManager = new ConnectionManager(mDataSocket, mUpdater);
		mManager.execute();
		this.publishProgress("Connection established to " + mDataSocket.getRemoteDevice().getName(), "system");
		
		return null;
	}

	public void onProgressUpdate(String... strings)
	{
//		cReferencia refs = new cReferencia();
//		int idAux = 0;
		
		if (mUpdater != null) {
			if (strings[1] == "system"){
				mUpdater.useData("Sys: " + strings[0]);
			}
			else {
//				StringTokenizer ref = new StringTokenizer(strings[0], ":");
//				idAux = Integer.parseInt(ref.nextToken());
//				refs.setID(idAux);
//				refs.setTrecho(Integer.parseInt(ref.nextToken()));
//				refs.setVelocidade(Integer.parseInt(ref.nextToken()));
//				refs.setDistancia(Integer.parseInt(ref.nextToken()));
//				refs.setDistanciaTrecho(Integer.parseInt(ref.nextToken()));
//				refs.setDistanciaProva(Integer.parseInt(ref.nextToken()));
//				refs.setNeutro(Integer.parseInt(ref.nextToken()));
//				refs.setTempo(Integer.parseInt(ref.nextToken()));
//				refs.setDistOmite(Integer.parseInt(ref.nextToken()));
//				refs.setTempOmite(Integer.parseInt(ref.nextToken()));
//				refs.setVelocOmite(Integer.parseInt(ref.nextToken()));
//				
//				if (idAux == 1) {
//					db.deleteTableRefs();
//					db.addReferencia(refs);
//					mUpdater.useData("Passou no delete");
////					mUpdater.useData(strings[0]);
//				}
//				else {
//					db.addReferencia(refs);
//					mUpdater.useData("NAO passou no delete");
////					mUpdater.useData(strings[0]);
//				}
//			
////				text = String.valueOf(cn.getID()) + ":" + String.valueOf(cn.getTrecho()) + ":"
////					+ String.valueOf(cn.getVelocidade() + ":" + String.valueOf(cn.getDistancia())
////									 + ":" + String.valueOf(cn.getDistanciaTrecho()) + ":" + String.valueOf(cn.getDistanciaProva())
////									 + ":" + String.valueOf(cn.getNeutro()) + ":" + String.valueOf(cn.getTempo())
////									 + ":" + String.valueOf(cn.getDistOmite()) + ":" + String.valueOf(cn.getTempOmite())
////									 + ":" + String.valueOf(cn.getVelocOmite()))
//				
				mUpdater.useData("AsyncCli " + strings[0]);
			}
//			mUpdater.useData(strings[0], strings[1]);
		}
//		mUpdater.useData(strings[0]);
	}

	public void closeSockets()
	{
		try
		{
			mManager.stop();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void write(String data)
	{
		mManager.write(data);
	}
}
