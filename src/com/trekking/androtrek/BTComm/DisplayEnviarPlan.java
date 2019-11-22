package com.trekking.androtrek.BTComm;

import java.util.List;
import com.trekking.androtrek.DBHandler;
import com.trekking.androtrek.R;
import com.trekking.androtrek.cReferencia;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class DisplayEnviarPlan extends Activity {
	
	private AsyncServerComponent server;
	private EditText chatText;
	
	private UILink asdf = new UILink()
	{
		@Override
		public void useData(String... args)
		{
			Log.d("BLT", "de aci " + args[0]);
			chatText.append(args[0] + "\n");
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_enviar_plan);
		
		chatText = (EditText) findViewById(R.id.serverEditText);
		server = new AsyncServerComponent(this, asdf);
		server.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_enviar_plan, menu);
		return true;
	}
	
	@Override
	public void onDestroy()
	{
		server.closeSockets();
		server.cancel(true);
		super.onDestroy();
	}

	public void SendClick(View view)
	{
		Context ctx = getApplicationContext();
		final DBHandler db = new DBHandler(ctx);
		int cont = 0;
		
		List<cReferencia> referencia = db.getAllReferencias(); 
		for (cReferencia cn : referencia) {
			String text;
			
			
			text = String.valueOf(cn.getID()) + ":" + String.valueOf(cn.getTrecho()) + ":"
							+ String.valueOf(cn.getVelocidade() + ":" + String.valueOf(cn.getDistancia())
							 + ":" + String.valueOf(cn.getDistanciaTrecho()) + ":" + String.valueOf(cn.getDistanciaProva())
							 + ":" + String.valueOf(cn.getNeutro()) + ":" + String.valueOf(cn.getTempo())
							 + ":" + String.valueOf(cn.getDistOmite()) + ":" + String.valueOf(cn.getTempOmite())
							 + ":" + String.valueOf(cn.getVelocOmite()));
			
			try
			{
//				chatText.append(text + "\n");
				server.write(text + "\n");
				text="";
				Thread.sleep(250);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			cont = cont +1;
			
		}
		chatText.append("Foram enviadas " + cont + "/" + referencia.size() + " refer�ncias\n");
	}
}
