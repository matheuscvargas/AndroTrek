package com.trekking.androtrek;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.*;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.*;

public class DisplayEnduro extends Activity {
	private Timer timer1 = new Timer();
	private TimerTask disparo;
	private final Handler handler = new Handler();
	Integer varPasso, varAjusteHora, horaLargada, minutosLargada, segundosLargada, HoraAjustada, segundosLargadaAux, Ref;
	Button btnSetVeloc, btnSetDist, btnSetTempo;
	TextView txtHoraAjustada, txtRefsAnteriores, txtRefsPosteriores, txtRefAtual, txtRefAtualTempo, txtDist, txtDistAcumul, txtPassos, txtTempo, lblSetar;
	List<cReferencia> referencia;
	Calendar calendar;
	Cursor cursor;
	
//	Context ctx = getApplicationContext();
//	DBHandler db = new DBHandler(ctx);
//	final Context context = this;
	
//	public String formatNumber(long NumToFormat, int res){
//		String NumFormatado = String.valueOf(NumToFormat);
//		for (int aux = 0; aux < res; aux++){
//			if (NumToFormat < Math.pow(10, aux) && !((NumToFormat == 0) && (aux == 0))){
//				NumFormatado = "0" + NumFormatado;
//			}
//		}
//		return NumFormatado;
//	}
//	public String ConvertSecsToHours(double NumToFormat){
//		String NumFormatado;
//		double hr, min, sec;
//		hr = 0;
//		min = 0;
//		sec = NumToFormat;
//		while (sec >= 60){
//			sec = sec - 60;
//			min = min + 1;
//		}
//		while (min >= 60){
//			min = min - 60;
//			hr = hr + 1;
//		}
//		NumFormatado = Utils.formatNumber((long) hr, 2) + ":" + Utils.formatNumber((long) min, 2) + ":" + Utils.formatNumber((long) sec, 2);
//		return NumFormatado;
//	}
//	public String ConvertSecsToMinutes(double NumToFormat){
//		String NumFormatado;
//		double min, sec;
//		min = 0;
//		sec = NumToFormat;
//		while (sec >= 60){
//			sec = sec - 60;
//			min = min + 1;
//		}
//		NumFormatado = Utils.formatNumber((long) min, 2) + ":" + Utils.formatNumber((long) sec, 2);
//		return NumFormatado;
//	}
	public String ExibeHoraAjustada(int HorasASomar){
		calendar = new GregorianCalendar();
		calendar.add(Calendar.SECOND, HorasASomar);

		if (calendar.get(Calendar.AM_PM) == 1){
			return Utils.formatNumber(calendar.get(Calendar.HOUR) + 12, 2) + ":" + Utils.formatNumber(calendar.get(Calendar.MINUTE), 2) + ":" + Utils.formatNumber(calendar.get(Calendar.SECOND), 2);
		}
		else {
			return Utils.formatNumber(calendar.get(Calendar.HOUR), 2) + ":" + Utils.formatNumber(calendar.get(Calendar.MINUTE), 2) + ":" + Utils.formatNumber(calendar.get(Calendar.SECOND), 2);
		}
	}
//	Funcao recorrente
	private void ativaTimer(){
	    disparo = new TimerTask() {
	        public void run() {
	                handler.post(new Runnable() {
	                        public void run() {
	                            // execucao
								txtHoraAjustada.setText(ExibeHoraAjustada(varAjusteHora));
								
								if (HoraAjustada > segundosLargadaAux){
									HoraAjustada = calendar.get(Calendar.HOUR) * 3600 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND);
									if (calendar.get(Calendar.AM_PM) == 1){
										HoraAjustada = HoraAjustada + 12 * 3600;
									}
									if (Ref < referencia.size()){
										ImprimeRefAtual();
										if ((referencia.get(Ref).getTempo() + segundosLargadaAux) < HoraAjustada){
											Ref = Ref + 1;
											ImprimeRefAnterior();
											ImprimeRefPosterior();
										}
										else {
											txtTempo.setText(Utils.ConvertSecsToMinutes(- HoraAjustada + referencia.get(Ref).getTempo() + segundosLargadaAux));
											double metragem = 0;
											if (Ref > 0){
												metragem = HoraAjustada - segundosLargadaAux - referencia.get(Ref- 1).getTempo();
												metragem = metragem / 60 * referencia.get(Ref).getVelocidade();
												txtDistAcumul.setText(Utils.formatNumber((long) (referencia.get(Ref - 1).getDistanciaTrecho() + metragem), 4));
											}
											else {
												metragem = HoraAjustada - segundosLargadaAux;
												metragem = metragem / 60 * referencia.get(Ref).getVelocidade();
												txtDistAcumul.setText(Utils.formatNumber((long) metragem, 4));
											}
											txtDist.setText(Utils.formatNumber((long) metragem, 4));
							
											txtPassos.setText(Utils.formatNumber((long) (metragem / varPasso * 100), 4));
										}
									}
									else {
										txtRefAtual.setText("FIM DE PROVA");
										txtRefsPosteriores.setText("");
										txtRefAtualTempo.setText("");
										txtDist.setText("");
										txtDistAcumul.setText("");
										txtPassos.setText("");
										txtTempo.setText("");
									}
								}
								else {
									txtRefAtual.setText("Aguardando largada");
									HoraAjustada = calendar.get(Calendar.HOUR) * 3600 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND);
									if (calendar.get(Calendar.AM_PM) == 1){
										HoraAjustada = HoraAjustada + 12 * 3600;
									}
								}
	                        }
	               });
	        }};
	        timer1.schedule(disparo, 300, 300);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_enduro);
//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	protected void onStart() {

		super.onStart();
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);	
		
		Context ctx = getApplicationContext();
		final DBHandler db = new DBHandler(ctx);
		
		varAjusteHora = 0;
		varPasso = 0;
		horaLargada = 0;
		minutosLargada = 0;
		segundosLargada = 0;
		
		txtDist = (TextView) findViewById(R.enduro.txtDist);
		txtTempo = (TextView) findViewById(R.enduro.txtTempoFim);
		lblSetar = (TextView) findViewById(R.enduro.lblSetar);
		txtPassos = (TextView) findViewById(R.enduro.txtPassos);
		txtRefAtual = (TextView) findViewById(R.enduro.RefAtual);
		txtDistAcumul = (TextView) findViewById(R.enduro.txtDistAcumul);
		txtHoraAjustada = (TextView) findViewById(R.enduro.HoraAtual);
		txtRefAtualTempo = (TextView) findViewById(R.enduro.RefAtualTempo);
		txtRefsAnteriores = (TextView) findViewById(R.enduro.RefsAnteriores);
		txtRefsPosteriores = (TextView) findViewById(R.enduro.RefsPosteriores);
		
		btnSetDist = (Button) findViewById(R.enduro.btnSetDist);
		btnSetTempo = (Button) findViewById(R.enduro.btnSetTemp);
		btnSetVeloc = (Button) findViewById(R.enduro.btnSetVeloc);
		
		calendar = new GregorianCalendar();
		HoraAjustada = calendar.get(Calendar.HOUR) * 3600 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND);
		if (calendar.get(Calendar.AM_PM) == 1){
			HoraAjustada = HoraAjustada + 12 * 3600;
		}
		
		//L� banco de dados
//		DBHandler db = new DBHandler(this);
		
		cConfig config = db.getregister(1);
		varAjusteHora = config.getAjusteHora();
		varPasso = config.getPasso();
		segundosLargada = config.getHoraLargada();
		
		segundosLargadaAux = segundosLargada;
		
        while (segundosLargada >= 60){
        	segundosLargada = segundosLargada -60;
        	minutosLargada = minutosLargada + 1;
        }
        while (minutosLargada >= 60){
        	minutosLargada = minutosLargada - 60;
        	horaLargada = horaLargada + 1;
        }
		
		referencia = db.getAllReferencias();
		HoraAjustada = HoraAjustada + varAjusteHora;
		
		Ref = 0;
		while ((Ref < referencia.size()) && ((referencia.get(Ref).getTempo() + segundosLargadaAux) < HoraAjustada)) {
			Ref = Ref + 1;
		}
		
		ativaTimer();
		
		if (Ref >= referencia.size()){
			txtRefAtual.setText("FIM DE PROVA");
			txtRefsPosteriores.setText("");
			txtRefAtualTempo.setText("");
			txtDist.setText("");
			txtDistAcumul.setText("");
			txtPassos.setText("");
			txtTempo.setText("");
		}
		else {
			ImprimeRefAtual();
			ImprimeRefAnterior();
			ImprimeRefPosterior();
		}
		btnSetVeloc.setVisibility(View.INVISIBLE);
		btnSetDist.setVisibility(View.INVISIBLE);
		btnSetTempo.setVisibility(View.INVISIBLE);
		lblSetar.setVisibility(View.INVISIBLE);
		
		btnSetVeloc.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intDist = new Intent(DisplayEnduro.this, DiagDist.class);

					intDist.putExtra("Tipo", "0");	//0- Velocidade, 1- Dist�ncia, 2- Neutro
					intDist.putExtra("Dado", "100");
					intDist.putExtra("Ref", String.valueOf(Ref+1));

					startActivity(intDist);
				}
			});
			
		btnSetDist.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intDist = new Intent(DisplayEnduro.this, DiagDist.class);
					if (txtDist.getText().equals("")) {
						intDist.putExtra("Tipo", "1");	//0- Velocidade, 1- Dist�ncia, 2- Neutro
						intDist.putExtra("Dados", "0");
						intDist.putExtra("Ref", String.valueOf(Ref+1));
					}
					else {
						intDist.putExtra("Tipo", "1");	//0- Velocidade, 1- Dist�ncia, 2- Neutro
						intDist.putExtra("Dado", txtDist.getText().toString());
						intDist.putExtra("Ref", String.valueOf(Ref+1));
					}
					
					startActivity(intDist);
				}
			});
		
		btnSetTempo.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intDist = new Intent(DisplayEnduro.this, DiagDist.class);

					double auxTempoRestante;
					auxTempoRestante = 900 + HoraAjustada - referencia.get(Ref).getTempo() - segundosLargadaAux;
					
					intDist.putExtra("Tipo", "2");	//0- Velocidade, 1- Dist�ncia, 2- Neutro
					intDist.putExtra("Dado", String.valueOf(auxTempoRestante));
					intDist.putExtra("Ref", String.valueOf(Ref+1));
					
					startActivity(intDist);
				}
			});
	}
	
	public void ImprimeRefAnterior(){
		ScrollView scroll = (ScrollView) findViewById(R.id.scrollView1);
		String auxTextRefAtual = "";
		
		for (int Cont = 0; Cont < Ref; Cont++){
			if (referencia.get(Cont).getVelocidade() == 0){
				auxTextRefAtual = auxTextRefAtual + "\n" + "T" + Utils.formatNumber((long) referencia.get(Cont).getTrecho(), 3) + " ";
				if (referencia.get(Cont).getTempOmite() == 1){
					auxTextRefAtual = auxTextRefAtual + "*";
				}
				auxTextRefAtual = auxTextRefAtual + "N" + Utils.ConvertSecsToMinutes(referencia.get(Cont).getNeutro()) + " ";
				auxTextRefAtual = auxTextRefAtual + "T" + Utils.ConvertSecsToHours(segundosLargadaAux + referencia.get(Cont).getTempo());
			}
			else {
				auxTextRefAtual = auxTextRefAtual + "\n" + "T" + Utils.formatNumber((long) referencia.get(Cont).getTrecho(), 3) + " ";
				if (referencia.get(Cont).getVelocOmite() == 1){
					auxTextRefAtual = auxTextRefAtual + "*";
				}
				auxTextRefAtual = auxTextRefAtual + "V" + Utils.formatNumber((long) referencia.get(Cont).getVelocidade(), 3) + " ";
				if (referencia.get(Cont).getDistOmite() == 1){
					auxTextRefAtual = auxTextRefAtual + "*";
				}
				auxTextRefAtual = auxTextRefAtual + "D" + Utils.formatNumber((long) referencia.get(Cont).getDistancia(), 4) + " ";
				auxTextRefAtual = auxTextRefAtual + "T" + Utils.ConvertSecsToHours(segundosLargadaAux + referencia.get(Cont).getTempo());
			}
			txtRefsAnteriores.setText(auxTextRefAtual);
		}
//		scroll.fullScroll(ScrollView.FOCUS_DOWN);
		scroll.scrollTo(0, txtRefsAnteriores.getHeight() + 20);
	}
	public void ImprimeRefPosterior(){
		String auxTextRefAtual = "";

		for (int Cont = Ref + 1; Cont < referencia.size(); Cont++){
			if (referencia.get(Cont).getVelocidade() == 0){
				auxTextRefAtual = auxTextRefAtual + "T" + Utils.formatNumber((long) referencia.get(Cont).getTrecho(), 3) + " ";
				if (referencia.get(Cont).getTempOmite() == 1){
					auxTextRefAtual = auxTextRefAtual + "*";
				}
				auxTextRefAtual = auxTextRefAtual + "N" + Utils.ConvertSecsToMinutes(referencia.get(Cont).getNeutro()) + " ";
				auxTextRefAtual = auxTextRefAtual + "T" + Utils.ConvertSecsToHours(segundosLargadaAux + referencia.get(Cont).getTempo()) + "\n";
			}
			else {
				auxTextRefAtual = auxTextRefAtual + "T" + Utils.formatNumber((long) referencia.get(Cont).getTrecho(), 3) + " ";
				if (referencia.get(Cont).getVelocOmite() == 1){
					auxTextRefAtual = auxTextRefAtual + "*";
				}
				auxTextRefAtual = auxTextRefAtual + "V" + Utils.formatNumber((long) referencia.get(Cont).getVelocidade(), 3) + " ";
				if (referencia.get(Cont).getDistOmite() == 1){
					auxTextRefAtual = auxTextRefAtual + "*";
				}
				auxTextRefAtual = auxTextRefAtual + "D" + Utils.formatNumber((long) referencia.get(Cont).getDistancia(), 4) + " ";
				auxTextRefAtual = auxTextRefAtual + "T" + Utils.ConvertSecsToHours(segundosLargadaAux + referencia.get(Cont).getTempo()) + "\n";
			}
			txtRefsPosteriores.setText(auxTextRefAtual);
		}
	}
	public void ImprimeRefAtual(){
		double AuxDistPassos = 0;
		String auxTextRefAtual = "";
		
		AuxDistPassos = referencia.get(Ref).getDistancia() / varPasso * 10000;
		AuxDistPassos = Math.round(AuxDistPassos);
		AuxDistPassos = AuxDistPassos / 100;
		
		if (referencia.get(Ref).getVelocidade() == 0) {
			txtDist.setText("");
			txtDistAcumul.setText("");
			txtPassos.setText("");
			
			txtRefAtualTempo.setText("Fim do trecho: " + Utils.ConvertSecsToHours(referencia.get(Ref).getTempo() + segundosLargadaAux));
			auxTextRefAtual = "T" + Utils.formatNumber((long) referencia.get(Ref).getTrecho(), 3) + " ";
			if (referencia.get(Ref).getTempOmite() == 1){
				auxTextRefAtual = auxTextRefAtual + "*";
				lblSetar.setVisibility(View.VISIBLE);
				btnSetTempo.setVisibility(View.VISIBLE);
			}
			else {
				lblSetar.setVisibility(View.INVISIBLE);
				btnSetTempo.setVisibility(View.INVISIBLE);			
			}
			auxTextRefAtual = auxTextRefAtual + "N" + Utils.ConvertSecsToMinutes(referencia.get(Ref).getNeutro());
			txtRefAtual.setText(auxTextRefAtual);
		}
		else {
			txtDist.setText(String.valueOf(referencia.get(Ref).getDistancia()));
			txtDistAcumul.setText(String.valueOf(referencia.get(Ref).getDistanciaProva()));
			txtPassos.setText(String.valueOf(AuxDistPassos));
			
			txtRefAtualTempo.setText("Fim do trecho: " + Utils.ConvertSecsToHours(referencia.get(Ref).getTempo() + segundosLargadaAux));
			auxTextRefAtual = "T" + Utils.formatNumber((long) referencia.get(Ref).getTrecho(), 3) + " ";
			if (referencia.get(Ref).getVelocOmite() == 1){
				auxTextRefAtual = auxTextRefAtual + "*";
				lblSetar.setVisibility(View.VISIBLE);
				btnSetVeloc.setVisibility(View.VISIBLE);
			}
			else {
				lblSetar.setVisibility(View.INVISIBLE);
				btnSetVeloc.setVisibility(View.INVISIBLE);			
			}
			auxTextRefAtual = auxTextRefAtual + "V" + Utils.formatNumber((long) referencia.get(Ref).getVelocidade(), 3) + " ";
			if (referencia.get(Ref).getDistOmite() == 1){
				auxTextRefAtual = auxTextRefAtual + "*";
				lblSetar.setVisibility(View.VISIBLE);
				btnSetDist.setVisibility(View.VISIBLE);
			}
			else {
				lblSetar.setVisibility(View.INVISIBLE);
				btnSetDist.setVisibility(View.INVISIBLE);			
			}
			auxTextRefAtual = auxTextRefAtual + "D" + Utils.formatNumber((long) referencia.get(Ref).getDistancia(), 4) + " P" + AuxDistPassos;
			txtRefAtual.setText(auxTextRefAtual);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_display_enduro, menu);
		return true;
	}
	
	protected void onResume() {
		super.onResume();
    
		Context ctx = getApplicationContext();
		final DBHandler db = new DBHandler(ctx);
	
		Ref = 0;
		referencia = db.getAllReferencias();
		while ((Ref < referencia.size()-1) && ((referencia.get(Ref).getTempo() + segundosLargadaAux) < HoraAjustada)) {
			Ref = Ref + 1;
		}
		
		ImprimeRefAtual();
		ImprimeRefAnterior();
		ImprimeRefPosterior();
	}

}
