package com.trekking.androtrek;

import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.content.*;
import android.content.pm.ActivityInfo;

public class DisplayLancPlan extends Activity {
	EditText Display;
	Button Btn1, Btn2, Btn3, Btn4, Btn5, Btn6, Btn7, Btn8, Btn9, Btn0, BtnDot, BtnCE, BtnVeloc, BtnDist, BtnTemp, BtnApagaTudo, BtnApagaUltima, BtnOmiteVeloc, BtnOmiteDist, BtnOmiteTemp;
	TextView txtVeloc, txtTrecho, txtDistAcumul, lstReferencias;
	CheckBox bxNeutroEhTrecho, bxMudaVeloc;
	String blankDisplay, txtReferencias;
	String AuxTxtReferencias;
	ImageView BtnOmite, BtnTeclado;
	int VelocOmitida;
	int Referencia, HorasLargada, MinutosLargada, SegundosLargada;
	double Trecho, Distancia, DistanciaTrecho, DistanciaProva, Velocidade, horas, minutos, segundos, Tempo, Neutro, minutosAux, segundosAux, horasExibir, minutosExibir, segundosExibir;
//	Context ctx = getApplicationContext();
//	DBHandler db = new DBHandler(ctx);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_lanc_plan);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		Context ctx = getApplicationContext();
		final DBHandler db = new DBHandler(ctx);
		
		final Context context = getApplicationContext();
		final int duration = Toast.LENGTH_SHORT;
		
		Velocidade = 0;
		Distancia = 0;
		DistanciaTrecho = 0;
		DistanciaProva = 0;
		Trecho = 0;
		Referencia = 0;
		horas = 0;
		minutos = 0;
		segundos = 0;	
		SegundosLargada = 0;
		MinutosLargada = 0;
		HorasLargada = 0;
		horasExibir = 0;
		minutosExibir = 0;
		segundosExibir = 0;
		VelocOmitida = 0;
		
		Display = (EditText) findViewById(R.lancplan.editText1);
		
		Btn1 = (Button) findViewById(R.lancplan.button1);
		Btn2 = (Button) findViewById(R.lancplan.button2);
		Btn3 = (Button) findViewById(R.lancplan.button3);
		Btn4 = (Button) findViewById(R.lancplan.button4);
		Btn5 = (Button) findViewById(R.lancplan.button5);
		Btn6 = (Button) findViewById(R.lancplan.button6);
		Btn7 = (Button) findViewById(R.lancplan.button7);
		Btn8 = (Button) findViewById(R.lancplan.button8);
		Btn9 = (Button) findViewById(R.lancplan.button9);
		Btn0 = (Button) findViewById(R.lancplan.button0);
		BtnCE = (Button) findViewById(R.lancplan.buttonCE);
		BtnDot = (Button) findViewById(R.lancplan.button_dot);
		BtnDist = (Button) findViewById(R.lancplan.BtnDist);
		BtnVeloc = (Button) findViewById(R.lancplan.BtnVeloc);
		BtnTemp =  (Button) findViewById(R.lancplan.BtnTemp);
		BtnApagaTudo = (Button) findViewById(R.lancplan.BtnApagaTudo);
		BtnApagaUltima = (Button) findViewById(R.lancplan.BtnApagaUltima);
		BtnOmiteDist = (Button) findViewById(R.lancplan.BtnOmiteDist);
		BtnOmiteTemp = (Button) findViewById(R.lancplan.BtnOmiteTempo);
		BtnOmiteVeloc = (Button) findViewById(R.lancplan.BtnOmiteVeloc);
		
		BtnOmite = (ImageView) findViewById(R.lancplan.imageView1);
//		BtnTeclado = (ImageView) findViewById(R.lancplan.ImageView01);
		
		txtVeloc = (TextView) findViewById(R.lancplan.txtVeloc);
		txtTrecho = (TextView) findViewById(R.lancplan.txtTrecho);
		txtDistAcumul = (TextView) findViewById(R.lancplan.txtDistAcumul);
		lstReferencias = (TextView) findViewById(R.lancplan.lstView);
		
		bxMudaVeloc = (CheckBox) findViewById(R.lancplan.CheckBox01);
		bxNeutroEhTrecho = (CheckBox) findViewById(R.lancplan.checkBox1);
		
		txtVeloc.setText("0");
		txtTrecho.setText("0");
		txtDistAcumul.setText("0");
		blankDisplay = Display.getText().toString();
		lstReferencias.setText(blankDisplay);
		BtnOmiteDist.setVisibility(View.INVISIBLE);
		BtnOmiteTemp.setVisibility(View.INVISIBLE);
		BtnOmiteVeloc.setVisibility(View.INVISIBLE);

//		DBHandler db = new DBHandler(this);
		cConfig config = db.getregister(1);
		SegundosLargada = config.getHoraLargada();
		while (SegundosLargada >= 60){
			SegundosLargada = SegundosLargada - 60;
			MinutosLargada = MinutosLargada + 1;
		}
		while (MinutosLargada >= 60){
			MinutosLargada = MinutosLargada - 60;
			HorasLargada = HorasLargada + 1;
		}
		
		Btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Display.setText(Display.getText().toString() + "1");
			}
		});
		Btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Display.setText(Display.getText().toString() + "2");
			}
		});
		Btn3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Display.setText(Display.getText().toString() + "3");
			}
		});
		Btn4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Display.setText(Display.getText().toString() + "4");
			}
		});
		Btn5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Display.setText(Display.getText().toString() + "5");
			}
		});
		Btn6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Display.setText(Display.getText().toString() + "6");
			}
		});
		Btn7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Display.setText(Display.getText().toString() + "7");
			}
		});
		Btn8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Display.setText(Display.getText().toString() + "8");
			}
		});
		Btn9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Display.setText(Display.getText().toString() + "9");
			}
		});
		Btn0.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Display.setText(Display.getText().toString() + "0");
			}
		});
		BtnDot.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Display.setText(Display.getText().toString() + ".");
			}
		});
		BtnCE.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Display.setText("");
			}
		});
		BtnVeloc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				VelocOmitida = 0;
				String aux = Display.getText().toString();
				if (aux.equals(blankDisplay)) {
					Toast.makeText(context, "Digite uma velocidade", duration).show();
				}
				else {
					if (bxMudaVeloc.isChecked()){
						Trecho = Trecho + 0.01;
						Trecho = Trecho * 100;
						Trecho = (Math.round(Trecho));
						Trecho = Trecho / 100;
						Velocidade = Double.parseDouble(Display.getText().toString());
						txtVeloc.setText(Display.getText().toString());
						txtTrecho.setText(String.valueOf(Trecho));
						Display.setText(blankDisplay);
						bxMudaVeloc.setChecked(false);
					}
					else {
						Trecho = Math.floor(Trecho) + 1;
						Distancia = 0;
						DistanciaTrecho = 0;
						txtDistAcumul.setText(String.valueOf(DistanciaTrecho));
						Velocidade = Double.parseDouble(Display.getText().toString());
						txtVeloc.setText(Display.getText().toString());
						txtTrecho.setText(String.valueOf(Trecho));
						Display.setText(blankDisplay);
					}
				}
			}
		});
		BtnDist.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String aux = Display.getText().toString();
				AuxTxtReferencias = "";
				
				if (txtVeloc.getText().equals("0.0")) {
					Toast.makeText(context, "Digite antes uma velocidade", duration).show();
				}
				else {
					if (aux.equals(blankDisplay)) {
						Toast.makeText(context, "Digite uma dist�ncia", duration).show();
					}
					else {
						Distancia = Double.parseDouble(Display.getText().toString());
						DistanciaTrecho = DistanciaTrecho + Distancia;
						DistanciaProva = DistanciaProva + Distancia;
						segundos = ((Distancia/Velocidade)) * 60 + segundos;
						
						txtDistAcumul.setText(String.valueOf(DistanciaTrecho));
						Display.setText(blankDisplay);
						
						while (segundos >= 60){
							segundos = segundos - 60;
							minutos = minutos + 1;
						}
						while (minutos >= 60){
							minutos = minutos - 60;
							horas = horas + 1;
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
						
		        		AuxTxtReferencias = "T" + Utils.formatNumber(Math.round(Trecho), 3) + " ";
		        		if (VelocOmitida == 1){
		        			AuxTxtReferencias = AuxTxtReferencias + "*";
		        		}
		        		AuxTxtReferencias = AuxTxtReferencias + "V" + Utils.formatNumber(Math.round(Velocidade), 3) + " ";
		        		AuxTxtReferencias = AuxTxtReferencias + "D" + Utils.formatNumber(Math.round(Distancia), 4) + " T" + Utils.formatNumber(Math.round(horasExibir), 2) + ":" + Utils.formatNumber(Math.round(minutosExibir), 2) + ":" + Utils.formatNumber(Math.round(segundosExibir), 2) + "\n";
		        		txtReferencias = AuxTxtReferencias + txtReferencias;
						lstReferencias.setText(txtReferencias);

//						txtReferencias = "T" + formatNumber(Math.round(Trecho), 3) + " V" + formatNumber(Math.round(Velocidade), 3) + " D" + formatNumber(Math.round(Distancia), 4) + " T" + formatNumber(Math.round(horasExibir), 2) + ":" + formatNumber(Math.round(minutosExibir), 2) + ":" + formatNumber(Math.round(segundosExibir), 2) + "\n" + txtReferencias;
//						lstReferencias.setText(txtReferencias);
						
						Referencia = Referencia + 1;
						
						cReferencia referencia = new cReferencia();
						referencia.setTrecho(Trecho);
						referencia.setVelocidade(Velocidade);
						referencia.setDistancia(Distancia);
						referencia.setDistanciaTrecho(DistanciaTrecho);
						referencia.setDistanciaProva(DistanciaProva);
						referencia.setTempo(horas * 3600 + minutos * 60 + segundos);
						referencia.setVelocOmite(VelocOmitida);
						referencia.setDistOmite(0);
//						DBHandler db = new DBHandler(context);
						db.addReferencia(referencia);
					}
				}

			}
		});
		BtnTemp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				AuxTxtReferencias = "";
				String aux = Display.getText().toString();
				if (aux.equals(blankDisplay)) {
					Toast.makeText(context, "Digite um tempo", duration).show();
				}
				else {
					if (bxNeutroEhTrecho.isChecked()){
						Trecho = Math.floor(Trecho) + 1;
						Distancia = 0;
						DistanciaTrecho = 0;
						Velocidade = 0;
						minutosAux = 0;
						
						segundosAux = Double.parseDouble(Display.getText().toString());
						segundosAux = segundosAux * 100;

						while (segundosAux >= 100) {
							segundosAux = segundosAux - 100;
							minutosAux = minutosAux + 1;
						}
						minutos = minutos + minutosAux;
						segundos = segundos + segundosAux;

						while (segundos >= 60){
							segundos = segundos - 60;
							minutos = minutos + 1;
						}
						while (minutos >= 60){
							minutos = minutos - 60;
							horas = horas + 1;
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

						txtTrecho.setText(String.valueOf(Trecho));
						txtDistAcumul.setText(String.valueOf(DistanciaTrecho));
						txtVeloc.setText(String.valueOf(Velocidade));
						txtReferencias = "T" + Utils.formatNumber(Math.round(Trecho), 3) + " N" + Utils.formatNumber(Math.round(minutosAux), 2) + ":" + Utils.formatNumber(Math.round(segundosAux), 2) + " T" + Utils.formatNumber(Math.round(horasExibir), 2) + ":" + Utils.formatNumber(Math.round(minutosExibir), 2) + ":" + Utils.formatNumber(Math.round(segundosExibir), 2) + "\n" + txtReferencias;
						lstReferencias.setText(txtReferencias);
						Display.setText(blankDisplay);
						Referencia = Referencia + 1;		
						
						cReferencia referencia = new cReferencia();
						referencia.setTrecho(Trecho);
						referencia.setNeutro(minutosAux * 60 + segundosAux);
						referencia.setDistanciaProva(DistanciaProva);
						referencia.setTempo(horas * 3600 + minutos * 60 + segundos);
//						DBHandler db = new DBHandler(context);
						db.addReferencia(referencia);

					}
					else {
						
						segundosAux = Double.parseDouble(Display.getText().toString());
						segundosAux = segundosAux * 100;

						while (segundosAux >= 100) {
							segundosAux = segundosAux - 100;
							minutosAux = minutosAux + 1;
						}
						minutos = minutos + minutosAux;
						segundos = segundos + segundosAux;

						while (segundos >= 60){
							segundos = segundos - 60;
							minutos = minutos + 1;
						}
						while (minutos >= 60){
							minutos = minutos - 60;
							horas = horas + 1;
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
						txtReferencias = "T" + Utils.formatNumber(Math.round(Trecho), 3) + " N" + Utils.formatNumber(Math.round(minutosAux), 2) + ":" + Utils.formatNumber(Math.round(segundosAux), 2) + " T" + Utils.formatNumber(Math.round(horasExibir), 2) + ":" + Utils.formatNumber(Math.round(minutosExibir), 2) + ":" + Utils.formatNumber(Math.round(segundosExibir), 2) + "\n" + txtReferencias;
						lstReferencias.setText(txtReferencias);
						Display.setText(blankDisplay);
						bxNeutroEhTrecho.setChecked(true);
						Referencia = Referencia + 1;		
						
						cReferencia referencia = new cReferencia();
						referencia.setTrecho(Trecho);
						referencia.setNeutro(minutosAux * 60 + segundosAux);
						referencia.setDistanciaProva(DistanciaProva);
						referencia.setTempo(horas * 3600 + minutos * 60 + segundos);
//						DBHandler db = new DBHandler(context);
						db.addReferencia(referencia);

					}
				
				}
			}
		});
		BtnApagaTudo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DBHandler db = new DBHandler(context);
				db.deleteTableRefs();
				
				lstReferencias.setText(blankDisplay);
				txtReferencias = "";
				Velocidade = 0;
				Distancia = 0;
				DistanciaTrecho = 0;
				DistanciaProva = 0;
				Trecho = 0;
				Referencia = 0;
				horas = 0;
				minutos = 0;
				segundos = 0;
				
				txtTrecho.setText(String.valueOf(Trecho));
				txtDistAcumul.setText(String.valueOf(DistanciaTrecho));
				txtVeloc.setText(String.valueOf(Velocidade));		
				
				ImprimeReferencias();
			}
		});
		BtnApagaUltima.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
//				DBHandler db = new DBHandler(context);
				db.deleteReferenciaByID(Referencia);
				
				ImprimeReferencias();
			}
		});
		BtnOmite.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (BtnOmiteDist.isShown()){
					BtnOmiteDist.setVisibility(View.INVISIBLE);
					BtnOmiteTemp.setVisibility(View.INVISIBLE);
					BtnOmiteVeloc.setVisibility(View.INVISIBLE);
					
				}
				else {
					BtnOmiteDist.setVisibility(View.VISIBLE);
					BtnOmiteTemp.setVisibility(View.VISIBLE);
					BtnOmiteVeloc.setVisibility(View.VISIBLE);
					
				}
			}
		});
//		BtnTeclado.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
//				if (Btn0.isShown()){
//					Btn1.setVisibility(View.INVISIBLE);
//					Btn2.setVisibility(View.INVISIBLE);
//					Btn3.setVisibility(View.INVISIBLE);
//					Btn4.setVisibility(View.INVISIBLE);
//					Btn5.setVisibility(View.INVISIBLE);
//					Btn6.setVisibility(View.INVISIBLE);
//					Btn7.setVisibility(View.INVISIBLE);
//					Btn8.setVisibility(View.INVISIBLE);
//					Btn9.setVisibility(View.INVISIBLE);
//					Btn0.setVisibility(View.INVISIBLE);
//					BtnDot.setVisibility(View.INVISIBLE);
//					BtnCE.setVisibility(View.INVISIBLE);					
//				}
//				else {
//					Btn1.setVisibility(View.VISIBLE);
//					Btn2.setVisibility(View.VISIBLE);
//					Btn3.setVisibility(View.VISIBLE);
//					Btn4.setVisibility(View.VISIBLE);
//					Btn5.setVisibility(View.VISIBLE);
//					Btn6.setVisibility(View.VISIBLE);
//					Btn7.setVisibility(View.VISIBLE);
//					Btn8.setVisibility(View.VISIBLE);
//					Btn9.setVisibility(View.VISIBLE);
//					Btn0.setVisibility(View.VISIBLE);
//					BtnDot.setVisibility(View.VISIBLE);
//					BtnCE.setVisibility(View.VISIBLE);
//				}
//			}
//		});
		BtnOmiteVeloc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				VelocOmitida = 1;
				if (bxMudaVeloc.isChecked()){
					Trecho = Trecho + 0.01;
					Trecho = Trecho * 100;
					Trecho = (Math.round(Trecho));
					Trecho = Trecho / 100;
					Velocidade = 100;
					txtVeloc.setText(String.valueOf(Velocidade));
					txtTrecho.setText(String.valueOf(Trecho));
					Display.setText(blankDisplay);
					bxMudaVeloc.setChecked(false);
				}
				else {
					Trecho = Math.floor(Trecho) + 1;
					Distancia = 0;
					DistanciaTrecho = 0;
					txtDistAcumul.setText(String.valueOf(DistanciaTrecho));
					Velocidade = 100;
					txtVeloc.setText(String.valueOf(Velocidade));
					txtTrecho.setText(String.valueOf(Trecho));
					Display.setText(blankDisplay);
				}
			}
		});
		BtnOmiteDist.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (txtVeloc.getText().equals("0.0")){
					Toast.makeText(context, "Digite antes uma velocidade", duration).show();
				}
				else {
					Distancia = 1000;
					DistanciaTrecho = DistanciaTrecho + Distancia;
					DistanciaProva = DistanciaProva + Distancia;
					segundos = ((Distancia/Velocidade)) * 60 + segundos;
					
					txtDistAcumul.setText(String.valueOf(DistanciaTrecho));
					Display.setText(blankDisplay);
					
					while (segundos >= 60){
						segundos = segundos - 60;
						minutos = minutos + 1;
					}
					while (minutos >= 60){
						minutos = minutos - 60;
						horas = horas + 1;
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
					
	        		AuxTxtReferencias = "T" + Utils.formatNumber(Math.round(Trecho), 3) + " ";
	        		if (VelocOmitida == 1){
	        			AuxTxtReferencias = AuxTxtReferencias + "*";
	        		}
	        		AuxTxtReferencias = AuxTxtReferencias + "V" + Utils.formatNumber(Math.round(Velocidade), 3) + " ";
	        		AuxTxtReferencias = AuxTxtReferencias + "*";
	        		AuxTxtReferencias = AuxTxtReferencias + "D" + Utils.formatNumber(Math.round(Distancia), 4) + " T" + Utils.formatNumber(Math.round(horasExibir), 2) + ":" + Utils.formatNumber(Math.round(minutosExibir), 2) + ":" + Utils.formatNumber(Math.round(segundosExibir), 2) + "\n";
	        		txtReferencias = AuxTxtReferencias + txtReferencias;
					lstReferencias.setText(txtReferencias);

//					txtReferencias = "T" + formatNumber(Math.round(Trecho), 3) + " V" + formatNumber(Math.round(Velocidade), 3) + " D" + formatNumber(Math.round(Distancia), 4) + " T" + formatNumber(Math.round(horasExibir), 2) + ":" + formatNumber(Math.round(minutosExibir), 2) + ":" + formatNumber(Math.round(segundosExibir), 2) + "\n" + txtReferencias;
//					lstReferencias.setText(txtReferencias);
					Referencia = Referencia + 1;
					
					cReferencia referencia = new cReferencia();
					referencia.setTrecho(Trecho);
					referencia.setVelocidade(Velocidade);
					referencia.setDistancia(Distancia);
					referencia.setDistanciaProva(DistanciaProva);
					referencia.setTempo(horas * 3600 + minutos * 60 + segundos);
					referencia.setVelocOmite(VelocOmitida);
					referencia.setDistOmite(1);
//					DBHandler db = new DBHandler(context);
					db.addReferencia(referencia);
				}
			}
		});
		BtnOmiteTemp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if (bxNeutroEhTrecho.isChecked()){
					Trecho = Math.floor(Trecho) + 1;
					Distancia = 0;
					DistanciaTrecho = 0;
					Velocidade = 0;
					minutosAux = 0;
					
					segundosAux = 1500;

					while (segundosAux >= 100) {
						segundosAux = segundosAux - 100;
						minutosAux = minutosAux + 1;
					}
					minutos = minutos + minutosAux;
					segundos = segundos + segundosAux;

					while (segundos >= 60){
						segundos = segundos - 60;
						minutos = minutos + 1;
					}
					while (minutos >= 60){
						minutos = minutos - 60;
						horas = horas + 1;
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

					txtTrecho.setText(String.valueOf(Trecho));
					txtDistAcumul.setText(String.valueOf(DistanciaTrecho));
					txtVeloc.setText(String.valueOf(Velocidade));
					txtReferencias = "T" + Utils.formatNumber(Math.round(Trecho), 3) + " *N" + Utils.formatNumber(Math.round(minutosAux), 2) + ":" + Utils.formatNumber(Math.round(segundosAux), 2) + " T" + Utils.formatNumber(Math.round(horasExibir), 2) + ":" + Utils.formatNumber(Math.round(minutosExibir), 2) + ":" + Utils.formatNumber(Math.round(segundosExibir), 2) + "\n" + txtReferencias;
					lstReferencias.setText(txtReferencias);
					Display.setText(blankDisplay);
					Referencia = Referencia + 1;		
					
					cReferencia referencia = new cReferencia();
					referencia.setTrecho(Trecho);
					referencia.setNeutro(minutosAux * 60 + segundosAux);
					referencia.setDistanciaProva(DistanciaProva);
					referencia.setTempo(horas * 3600 + minutos * 60 + segundos);
					referencia.setTempOmite(1);
//					DBHandler db = new DBHandler(context);
					db.addReferencia(referencia);

				}
				else {
					
					segundosAux = 1500;

					while (segundosAux >= 100) {
						segundosAux = segundosAux - 100;
						minutosAux = minutosAux + 1;
					}
					minutos = minutos + minutosAux;
					segundos = segundos + segundosAux;

					while (segundos >= 60){
						segundos = segundos - 60;
						minutos = minutos + 1;
					}
					while (minutos >= 60){
						minutos = minutos - 60;
						horas = horas + 1;
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
					txtReferencias = "T" + Utils.formatNumber(Math.round(Trecho), 3) + " *N" + Utils.formatNumber(Math.round(minutosAux), 2) + ":" + Utils.formatNumber(Math.round(segundosAux), 2) + " T" + Utils.formatNumber(Math.round(horasExibir), 2) + ":" + Utils.formatNumber(Math.round(minutosExibir), 2) + ":" + Utils.formatNumber(Math.round(segundosExibir), 2) + "\n" + txtReferencias;
					lstReferencias.setText(txtReferencias);
					Display.setText(blankDisplay);
					bxNeutroEhTrecho.setChecked(true);
					Referencia = Referencia + 1;		
					
					cReferencia referencia = new cReferencia();
					referencia.setTrecho(Trecho);
					referencia.setNeutro(minutosAux * 60 + segundosAux);
					referencia.setDistanciaProva(DistanciaProva);
					referencia.setTempo(horas * 3600 + minutos * 60 + segundos);
					referencia.setTempOmite(1);
//					DBHandler db = new DBHandler(context);
					db.addReferencia(referencia);
				}
			}
		});

		ImprimeReferencias();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_lanc_plan, menu);
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
		
		lstReferencias = (TextView) findViewById(R.lancplan.lstView);

		txtReferencias = "";
		AuxTxtReferencias = "";
		lstReferencias.setText(blankDisplay);
    	DistanciaTrecho = 0;
    	TrechoAux = 0;
    	
		// L� banco de dados
//		DBHandler db = new DBHandler(this);
		
		txtReferencias = "Largada: " + Utils.formatNumber(Math.round(HorasLargada), 2) + ":" + Utils.formatNumber(Math.round(MinutosLargada), 2) + ":" + Utils.formatNumber(Math.round(SegundosLargada), 2) + "\n" + txtReferencias;
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
        	
        	VelocOmitida = auxVelocOmite;
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
        	
        	if (Velocidade == 0){
        		AuxTxtReferencias = "T" + Utils.formatNumber(Math.round(Trecho), 3) + " ";
        		if (auxTempOmite == 1){
        			AuxTxtReferencias = AuxTxtReferencias + "*";
        		}
        		AuxTxtReferencias =  AuxTxtReferencias + "N" + Utils.formatNumber(Math.round(minutosAux), 2) + ":" + Utils.formatNumber(Math.round(segundosAux), 2) + " T" + Utils.formatNumber(Math.round(horasExibir), 2) + ":" + Utils.formatNumber(Math.round(minutosExibir), 2) + ":" + Utils.formatNumber(Math.round(segundosExibir), 2) + "\n";
        		txtReferencias = AuxTxtReferencias + txtReferencias;
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
        		AuxTxtReferencias = AuxTxtReferencias + "D" + Utils.formatNumber(Math.round(Distancia), 4) + " T" + Utils.formatNumber(Math.round(horasExibir), 2) + ":" + Utils.formatNumber(Math.round(minutosExibir), 2) + ":" + Utils.formatNumber(Math.round(segundosExibir), 2) + "\n";
        		txtReferencias = AuxTxtReferencias + txtReferencias;
				lstReferencias.setText(txtReferencias);
        	}
        }
        
        txtTrecho.setText(String.valueOf(Trecho));
        txtVeloc.setText(String.valueOf(Velocidade));
        txtDistAcumul.setText(String.valueOf(DistanciaTrecho));
	}
}
