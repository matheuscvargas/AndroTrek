package com.trekking.androtrek;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayConfig extends Activity {
//	Declaracao de variaveis
	Integer varPasso, varAjusteHora, horaLargada, minutosLargada, segundosLargada;
//	Integer segundosAjustados;
	EditText Passo, AjusteHora;
	Button bMais, bMenos, bMaisHoras, bMenosHoras, bMaisMinutos, bMenosMinutos, bMaisSegundos, bMenosSegundos;
	TextView HoraAjustada, txtHoraLargada, txtMinutoLargada, txtSegundoLargada;
	private Timer timer1 = new Timer();
	private TimerTask disparo;
	private final Handler handler = new Handler();
	
	
//	Funcoes publicas
//	public String formatNumber(long NumToFormat, int res){
//		String NumFormatado = String.valueOf(NumToFormat);
//		for (int aux = 0; aux < res; aux++){
//			if (NumToFormat < Math.pow(10, aux) && !((NumToFormat == 0) && (aux == 0))){
//				NumFormatado = "0" + NumFormatado;
//			}
//		}
//		return NumFormatado;
//	}
//		Funcao para exibir a hora atual corrigida
	public String ExibeHoraAjustada(int HorasASomar){
		final Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.SECOND, HorasASomar);

		if (calendar.get(Calendar.AM_PM) == 1){
			return Utils.formatNumber(calendar.get(Calendar.HOUR) + 12, 2) + ":" + Utils.formatNumber(calendar.get(Calendar.MINUTE), 2) + ":" + Utils.formatNumber(calendar.get(Calendar.SECOND), 2);
		}
		else {
			return Utils.formatNumber(calendar.get(Calendar.HOUR), 2) + ":" + Utils.formatNumber(calendar.get(Calendar.MINUTE), 2) + ":" + Utils.formatNumber(calendar.get(Calendar.SECOND), 2);
		}	}
//		Funcao recorrente
	private void ativaTimer(){
	    disparo = new TimerTask() {
	        public void run() {
	                handler.post(new Runnable() {
	                        public void run() {
	                            // execucao
	                        	HoraAjustada.setText(ExibeHoraAjustada(varAjusteHora));
	                        }
	               });
	        }};
	        timer1.schedule(disparo, 300, 300);
	}
	
//	Abertura da tela
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_config);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		varAjusteHora = 0;
		varPasso = 0;
		horaLargada = 0;
		minutosLargada = 0;
		segundosLargada = 0;

		//L� banco de dados
//		DBHandler db = new DBHandler(this);
		Context ctx = getApplicationContext();
		final DBHandler db = new DBHandler(ctx);
		
		cConfig config = db.getregister(1);
		varAjusteHora = config.getAjusteHora();
		varPasso = config.getPasso();
		segundosLargada = config.getHoraLargada();
		
        while (segundosLargada >= 60){
        	segundosLargada = segundosLargada -60;
        	minutosLargada = minutosLargada + 1;
        }
        while (minutosLargada >= 60){
        	minutosLargada = minutosLargada - 60;
        	horaLargada = horaLargada + 1;
        }
		ativaTimer();
		
		Passo = (EditText) findViewById(R.config.txtPasso);
		AjusteHora = (EditText) findViewById(R.config.txtAjuste);
		
		bMais = (Button) findViewById(R.config.btnMais);
		bMenos = (Button) findViewById(R.config.btnMenos);
		bMaisHoras = (Button) findViewById(R.config.btnMaisHoras);
		bMenosHoras = (Button) findViewById(R.config.btnMenosHoras);
		bMaisMinutos = (Button) findViewById(R.config.btnMaisMinutos);
		bMenosMinutos = (Button) findViewById(R.config.btnMenosMinutos);
		bMaisSegundos = (Button) findViewById(R.config.btnMaisSegundos);
		bMenosSegundos = (Button) findViewById(R.config.btnMenosSegundos);
		
		HoraAjustada = (TextView) findViewById(R.config.txtHorAjust);
		txtHoraLargada = (TextView) findViewById(R.config.txtHoras);
		txtMinutoLargada = (TextView) findViewById(R.config.txtMinutos);
		txtSegundoLargada = (TextView) findViewById(R.config.txtSegundos);

		Passo.setText(String.valueOf(varPasso));
		AjusteHora.setText(String.valueOf(varAjusteHora));
		txtHoraLargada.setText(String.valueOf(horaLargada));
		txtMinutoLargada.setText(String.valueOf(minutosLargada));
		txtSegundoLargada.setText(String.valueOf(segundosLargada));
		
		bMais.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				varAjusteHora = Integer.parseInt(AjusteHora.getText().toString()) + 1;
				AjusteHora.setText(String.valueOf(varAjusteHora));
				HoraAjustada.setText(ExibeHoraAjustada(varAjusteHora));
			}
		});
		
		bMenos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				varAjusteHora = Integer.parseInt(AjusteHora.getText().toString()) - 1;
				AjusteHora.setText(String.valueOf(varAjusteHora));
				HoraAjustada.setText(ExibeHoraAjustada(varAjusteHora));
			}
		});
		
		bMaisHoras.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				horaLargada = Integer.parseInt(txtHoraLargada.getText().toString()) + 1;
				
				if (horaLargada >= 24){
					horaLargada = 0;
				}
				
				txtHoraLargada.setText(String.valueOf(horaLargada));
			}
		});

		bMenosHoras.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				horaLargada = Integer.parseInt(txtHoraLargada.getText().toString()) - 1;
				
				if (horaLargada < 0){
					horaLargada = 23;
				}

				txtHoraLargada.setText(String.valueOf(horaLargada));
			}
		});

		bMaisMinutos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				minutosLargada = Integer.parseInt(txtMinutoLargada.getText().toString()) + 1;
				
				if (minutosLargada >= 60){
					minutosLargada = 0;
				}

				txtMinutoLargada.setText(String.valueOf(minutosLargada));
			}
		});

		bMenosMinutos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				minutosLargada = Integer.parseInt(txtMinutoLargada.getText().toString()) - 1;
				
				if (minutosLargada < 0){
					minutosLargada = 59;
				}

				txtMinutoLargada.setText(String.valueOf(minutosLargada));
			}
		});

		bMaisSegundos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				segundosLargada = Integer.parseInt(txtSegundoLargada.getText().toString()) + 1;
				
				if (segundosLargada >= 60){
					segundosLargada = 0;
				}

				txtSegundoLargada.setText(String.valueOf(segundosLargada));
			}
		});

		bMenosSegundos.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				segundosLargada = Integer.parseInt(txtSegundoLargada.getText().toString()) - 1;
				
				if (segundosLargada < 0){
					segundosLargada = 59;
				}

				txtSegundoLargada.setText(String.valueOf(segundosLargada));
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_config, menu);
		return true;
	}

	protected void onStop() {
	    super.onStop();
		AjusteHora = (EditText) findViewById(R.config.txtAjuste);
		Passo = (EditText) findViewById(R.config.txtPasso);
		txtHoraLargada = (TextView) findViewById(R.config.txtHoras);
		txtMinutoLargada = (TextView) findViewById(R.config.txtMinutos);
		txtSegundoLargada = (TextView) findViewById(R.config.txtSegundos);
		
		varAjusteHora = Integer.parseInt(AjusteHora.getText().toString());
		horaLargada = Integer.parseInt(txtHoraLargada.getText().toString());
		minutosLargada = Integer.parseInt(txtMinutoLargada.getText().toString());
		segundosLargada = Integer.parseInt(txtSegundoLargada.getText().toString());
		
		cConfig registro = new cConfig();
		registro.setID(1);
		registro.setPasso(Integer.parseInt(Passo.getText().toString()));
		registro.setAjusteHora(varAjusteHora);
		registro.setHoraLargada(horaLargada * 3600 + minutosLargada * 60 + segundosLargada);
		
	    DBHandler db = new DBHandler(this);
		db.updateRegister(registro);

	    timer1.cancel();
	}

}
