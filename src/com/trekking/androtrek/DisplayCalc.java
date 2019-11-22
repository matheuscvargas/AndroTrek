package com.trekking.androtrek;

import java.util.List;

import com.trekking.androtrek.BTComm.DisplayEnviarPlan;
import com.trekking.androtrek.BTComm.DisplayRecebePlan;
import com.trekking.androtrek.BTComm.MyDeviceData;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayCalc extends Activity {

	int Referencia, HorasLargada, MinutosLargada, SegundosLargada;
	double Trecho, Distancia, DistanciaTrecho, DistanciaProva, Velocidade, horas, minutos, segundos, Tempo, Neutro, minutosAux, segundosAux, horasExibir, minutosExibir, segundosExibir, Passos, TamPasso;
	String blankDisplay, txtReferencias;
	TextView lstReferencias;
//	Context ctx = getApplicationContext();
//	DBHandler db = new DBHandler(ctx);
	private BluetoothAdapter bltAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_calc);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
//		Context ctx = getApplicationContext();
//		final DBHandler db = new DBHandler(ctx);
		final Button BtnEnvia, BtnRecebe;
		BtnEnvia = (Button) findViewById(R.calc.btnEnviar);
		BtnRecebe = (Button) findViewById(R.calc.btnReceber);
		
		bltAdapter = BluetoothAdapter.getDefaultAdapter();
		if (!bltAdapter.isEnabled())
		{
			// check if the bluetooth is enabled
			Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			this.startActivity(enableBluetooth);
		}
		MyDeviceData.adress = bltAdapter.getAddress();
		MyDeviceData.name = bltAdapter.getName();
		
		ImprimeReferencias();
		
		BtnEnvia.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intDist = new Intent(DisplayCalc.this, DisplayEnviarPlan.class);
				startActivity(intDist);

			}
		});
		
		BtnRecebe.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intDist = new Intent(DisplayCalc.this, DisplayRecebePlan.class);
				startActivity(intDist);

			}
		});
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_calc, menu);
		return true;
	}
	
//	public String formatNumber(long NumToFormat, int res){
//		String NumFormatado = String.valueOf(NumToFormat);
//		for (int aux = 0; aux < res; aux++){
//			if (NumToFormat < Math.pow(10, aux) && !((NumToFormat == 0) && (aux == 0))){
//				NumFormatado = "0" + NumFormatado;
//			}
//		}
//		return NumFormatado;
//	}
	
	
	public void ImprimeReferencias(){
		double TrechoAux;
		int auxVelocOmite, auxDistOmite, auxTempOmite;
		Context ctx = getApplicationContext();
		final DBHandler db = new DBHandler(ctx);
		
		SegundosLargada = 0;
		MinutosLargada = 0;
		HorasLargada = 0;
		
		lstReferencias = (TextView) findViewById(R.calc.lstReferencias);

		txtReferencias = "";
		String AuxTxtReferencias = "";
		lstReferencias.setText(blankDisplay);
    	DistanciaTrecho = 0;
    	TrechoAux = 0;
    	
//		//L� banco de dados
//		DBHandler db = new DBHandler(this);
		
		cConfig config = db.getregister(1);
		SegundosLargada = config.getHoraLargada();
		TamPasso = config.getPasso();
		
		TamPasso = TamPasso / 100;
		
		while (SegundosLargada >= 60){
			SegundosLargada = SegundosLargada - 60;
			MinutosLargada = MinutosLargada + 1;
		}
		while (MinutosLargada >= 60){
			MinutosLargada = MinutosLargada - 60;
			HorasLargada = HorasLargada + 1;
		}

		txtReferencias = "Largada: " + Utils.formatNumber(Math.round(HorasLargada), 2) + ":" + Utils.formatNumber(Math.round(MinutosLargada), 2) + ":" + Utils.formatNumber(Math.round(SegundosLargada), 2) + "\n";
		lstReferencias.setText(txtReferencias);
		
        List<cReferencia> referencia = db.getAllReferencias(); 
        for (cReferencia cn : referencia) {
        	Trecho = cn.getTrecho();
        	Velocidade = cn.getVelocidade();
        	Distancia = cn.getDistancia();
        	DistanciaProva = cn.getDistanciaProva();
        	Neutro = cn.getNeutro();
        	Tempo = cn.getTempo();
        	Referencia = cn.getID();
        	auxVelocOmite = cn.getVelocOmite();
        	auxDistOmite = cn.getDistOmite();
        	auxTempOmite = cn.getTempOmite();
        	
        	segundos = 0;
        	segundosAux = 0;
        	minutos = 0;
        	minutosAux = 0;
        	horas = 0;
        	
        	segundos = Tempo;
        	segundosAux = Neutro;
        	while (segundos >= 60) {
        		segundos = segundos - 60;
        		minutos = minutos + 1;
        	}
        	while (minutos >= 60) {
        		minutos = minutos - 60;
        		horas = horas + 1;
        	}
        	while (segundosAux >= 60) {
        		segundosAux = segundosAux - 60;
        		minutosAux = minutosAux + 1;
        	}

			segundosExibir = SegundosLargada + segundos;
			minutosExibir = MinutosLargada + minutos;
			horasExibir = HorasLargada + horas;
			while (segundosExibir >= 60){
				segundosExibir = segundosExibir - 60;
				minutosExibir = minutosExibir + 1;
			}
			while (minutosExibir >= 60){
				minutosExibir = minutosExibir - 60;
				horasExibir = horasExibir + 1;
			}
			if (TrechoAux == Trecho){
        		DistanciaTrecho = DistanciaTrecho + Distancia;
        	}
			else {
				TrechoAux = Trecho;
				DistanciaTrecho = Distancia;
			}
        	
			Passos = (Distancia / TamPasso) * 100;
			Passos = Math.round(Passos);
			Passos = Passos / 100;
			
        	if (Velocidade == 0){
        		AuxTxtReferencias = "T" + Utils.formatNumber(Math.round(Trecho), 3) + " ";
        		if (auxTempOmite == 1){
        			AuxTxtReferencias = AuxTxtReferencias + "*";
        		}
        		AuxTxtReferencias =  AuxTxtReferencias + "N" + Utils.formatNumber(Math.round(minutosAux), 2) + ":" + Utils.formatNumber(Math.round(segundosAux), 2) + " T" + Utils.formatNumber(Math.round(horasExibir), 2) + ":" + Utils.formatNumber(Math.round(minutosExibir), 2) + ":" + Utils.formatNumber(Math.round(segundosExibir), 2) + "\n";
        		txtReferencias = txtReferencias + AuxTxtReferencias;
				lstReferencias.setText(txtReferencias);

        	}
        	else {
        		AuxTxtReferencias = "T" + Utils.formatNumber(Math.round(Trecho), 3) + " ";
        		if (auxVelocOmite == 1){
        			AuxTxtReferencias = AuxTxtReferencias + "*";
        		}
        		AuxTxtReferencias = AuxTxtReferencias + "V" + Utils.formatNumber(Math.round(Velocidade), 3) + " ";
        		if (auxDistOmite == 1){
        			AuxTxtReferencias = AuxTxtReferencias + "*";
        		}
        		AuxTxtReferencias = AuxTxtReferencias + "D" + Utils.formatNumber(Math.round(Distancia), 4) + " T" + Utils.formatNumber(Math.round(horasExibir), 2) + ":" + Utils.formatNumber(Math.round(minutosExibir), 2) + ":" + Utils.formatNumber(Math.round(segundosExibir), 2)  + " P" + Passos + "\n";
        		txtReferencias = txtReferencias + AuxTxtReferencias;
				lstReferencias.setText(txtReferencias);
        	}
        }
               
	}

}
