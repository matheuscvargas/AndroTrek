package com.trekking.androtrek;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.*;
import android.widget.*;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.content.*;
import android.content.pm.ActivityInfo;

public class DisplayAlteraPlan extends Activity {
	
//	Context ctx = getApplicationContext();
	private static String[] FROM = new String[] { "_id", "trecho", "velocidade", "distancia", "neutro"};
	private static int[] TO = new int[] {R.id.txtView1, R.id.txtView2, R.id.txtView3, R.id.txtView4, R.id.txtView5};
//	DBHandler db = new DBHandler(this);
	Cursor cursor;
//	DBHandler db = new DBHandler(ctx);
	
	private class CustomViewBinder implements ViewBinder {

		@Override
		public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
			int neutroAux = cursor.getInt(columnIndex);
			if (columnIndex == cursor.getColumnIndex(DBHandler.REFS_NEUTRO)) {
				
				if (neutroAux == 0) {
					view.setVisibility(View.INVISIBLE);
				} 
				else {
					double segundos;
//					String txtAux;
					TextView txtNeutro = (TextView) view;

					segundos = Double.parseDouble(cursor.getString(6));
//					minutos = 0;
//					txtAux = "";
//
//					while (segundos >= 60){
//						segundos = segundos - 60;
//						minutos = minutos + 1;
//					}
//
//					txtAux = Math.round(minutos) + "," + Math.round(segundos);
//					txtNeutro.setText(txtAux);
					txtNeutro.setText(Utils.ConvertSecsToMinutes(segundos));
					
					view.setVisibility(View.VISIBLE);
					
				}
				return true;
			}
			if (neutroAux == 0) {
				view.setVisibility(View.INVISIBLE);
				return true;
			}
			// For others, we simply return false so that the default binding
			// happens.
			view.setVisibility(View.VISIBLE);
			return false;
		}
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_altera_plan);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		Context ctx = getApplicationContext();
		final DBHandler db = new DBHandler(ctx);
		
		final Context context = getApplicationContext();
		final int duration = Toast.LENGTH_SHORT;
		final String blankDisplay;
		final TextView txtTrecho, lblInsereTrecho;
		final EditText txtDist, txtVeloc, txtNeutro, txtInsereApos;
		final Button BtnAltera, BtnApagaRef, BtnInsereMetragem, BtnInsereNeutro, BtnConfirma, BtnCancela;
		final CheckBox chkTrechoNovo;
		
		BtnAltera = (Button) findViewById(R.AlteraPlan.BtnAltera);
		BtnApagaRef = (Button) findViewById(R.AlteraPlan.BtnApagaRef);
		BtnInsereMetragem = (Button) findViewById(R.AlteraPlan.BtnInsereMetragem);
		BtnInsereNeutro = (Button) findViewById(R.AlteraPlan.BtnInsereNeutro);
		BtnConfirma = (Button) findViewById(R.AlteraPlan.BtnConfirma);
		BtnCancela = (Button) findViewById(R.AlteraPlan.BtnCancela);
		
		txtTrecho = (TextView) findViewById(R.AlteraPlan.txtTrecho);
		lblInsereTrecho = (TextView) findViewById(R.AlteraPlan.lblInsereApos);
		
		txtVeloc = (EditText) findViewById(R.AlteraPlan.txtVeloc);
		txtDist = (EditText) findViewById(R.AlteraPlan.txtDist);
		txtNeutro = (EditText) findViewById(R.AlteraPlan.txtNeutro);
		txtInsereApos = (EditText) findViewById(R.AlteraPlan.txtInsere);

		chkTrechoNovo = (CheckBox) findViewById(R.AlteraPlan.CheckBox01);
		
		ListView listView = (ListView) findViewById(R.AlteraPlan.listView1);
		
		blankDisplay = txtVeloc.getText().toString();

		cursor = db.getAllCursor();
		
		txtInsereApos.setVisibility(View.INVISIBLE);
		lblInsereTrecho.setVisibility(View.INVISIBLE);
		BtnConfirma.setVisibility(View.INVISIBLE);
		BtnCancela.setVisibility(View.INVISIBLE);
		chkTrechoNovo.setVisibility(View.INVISIBLE);
		
	    SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.listview_items, cursor, FROM, TO);
	    adapter.setViewBinder(new CustomViewBinder());
	    
	    listView.setAdapter(adapter);
	    
	 // setting action listen on list item
	    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	@Override
	    	public void onItemClick(AdapterView<?> arg0, View view, final int pos, long id) {
				
				double segundos;
//				String txtAux;
				txtTrecho.setText(cursor.getString(1));
				txtVeloc.setText(cursor.getString(2));
				txtDist.setText(cursor.getString(3));

				segundos = Double.parseDouble(cursor.getString(6));
//				minutos = 0;
//				txtAux = "";
				
				cursor.moveToPosition(pos);
				
				if (cursor.getString(2).equals("0")){
					txtVeloc.setVisibility(View.INVISIBLE);
					txtDist.setVisibility(View.INVISIBLE);
					txtNeutro.setVisibility(View.VISIBLE);
				}
				else {
					txtVeloc.setVisibility(View.VISIBLE);
					txtDist.setVisibility(View.VISIBLE);
					txtNeutro.setVisibility(View.INVISIBLE);
				}

//				while (segundos >= 60){
//					segundos = segundos - 60;
//					minutos = minutos + 1;
//				}
//				
//				txtAux = Math.round(minutos) + "." + Math.round(segundos);
//				txtNeutro.setText(txtAux);
				txtNeutro.setText(Utils.ConvertSecsToMinutes(segundos));
				
	    		};
	    	});
			
		BtnAltera.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					double Trecho, Distancia, Velocidade, Neutro;

					Velocidade = 0;
					Distancia = 0;
					Neutro = 0;
					Trecho = 0;

					String aux = txtVeloc.getText().toString();
					Double auxNum;

					if (aux.equals(blankDisplay) && txtVeloc.isShown()) {
						Toast.makeText(context, "Digite antes uma velocidade", duration).show();
					}
					else {
						auxNum = Double.parseDouble(txtVeloc.getText().toString());
						if (auxNum == 0 && txtVeloc.isShown()) {
							Toast.makeText(context, "Digite antes uma velocidade", duration).show();
						}
						else {
							aux = txtDist.getText().toString();

							if (aux.equals(blankDisplay) && txtVeloc.isShown()) {
								Toast.makeText(context, "Digite uma dist�ncia", duration).show();
							}
							else {
								auxNum = Double.parseDouble(txtDist.getText().toString());

								if (auxNum == 0 && txtDist.isShown()) {
									Toast.makeText(context, "Digite uma dist�ncia", duration).show();
								}
								else {
									aux = txtNeutro.getText().toString();

									if (aux.equals(blankDisplay) && txtNeutro.isShown()) {
										Toast.makeText(context, "Digite um tempo", duration).show();
									}
									else {
										auxNum = Double.parseDouble(txtNeutro.getText().toString());

										if (auxNum == 0 && txtNeutro.isShown()) {
											Toast.makeText(context, "Digite um tempo", duration).show();
										}
										else {
											Trecho = Double.parseDouble(txtTrecho.getText().toString());
											Velocidade = Double.parseDouble(txtVeloc.getText().toString());
											Distancia = Double.parseDouble(txtDist.getText().toString());
											
											double NeutroAux = 0;
											Neutro = Double.parseDouble(txtNeutro.getText().toString());
											Neutro = Neutro * 100;
											while (Neutro >= 100) {
												Neutro = Neutro - 100;
												NeutroAux = NeutroAux + 1;
											}

											cReferencia referencia = new cReferencia();
											referencia.setID(Integer.parseInt(cursor.getString(0)));
											referencia.setTrecho(Trecho);
											referencia.setVelocidade(Velocidade);
											referencia.setDistancia(Distancia);
											referencia.setNeutro(NeutroAux * 60 + Neutro);
//											DBHandler db = new DBHandler(context);
											db.updateReferencia(referencia);

											cursor.requery();
										}
									}

								}
							}
						}
					}
					txtVeloc.setText(blankDisplay);
					txtDist.setText(blankDisplay);
					txtNeutro.setText(blankDisplay);

				}
		});
			
		BtnApagaRef.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String aux = txtVeloc.getText().toString();
				if (aux.equals(blankDisplay)) {
					Toast.makeText(context, "Selecione uma referência", duration).show();
				}
				else {
//					DBHandler db = new DBHandler(context);
					db.deleteReferenciaByID(Integer.parseInt(cursor.getString(0)));

					cursor.requery();

					txtVeloc.setText(blankDisplay);
					txtDist.setText(blankDisplay);
					txtNeutro.setText(blankDisplay);
				}
			}
		});
		
		BtnInsereMetragem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				txtInsereApos.setVisibility(View.VISIBLE);
				lblInsereTrecho.setVisibility(View.VISIBLE);
				BtnConfirma.setVisibility(View.VISIBLE);
				BtnCancela.setVisibility(View.VISIBLE);
				txtVeloc.setVisibility(View.INVISIBLE);
				txtDist.setVisibility(View.VISIBLE);
				txtNeutro.setVisibility(View.INVISIBLE);
				BtnInsereMetragem.setVisibility(View.INVISIBLE);
				BtnInsereNeutro.setVisibility(View.INVISIBLE);
				chkTrechoNovo.setVisibility(View.VISIBLE);
			}
		});
		
		BtnInsereNeutro.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				txtInsereApos.setVisibility(View.VISIBLE);
				lblInsereTrecho.setVisibility(View.VISIBLE);
				BtnConfirma.setVisibility(View.VISIBLE);
				BtnCancela.setVisibility(View.VISIBLE);
				txtVeloc.setVisibility(View.INVISIBLE);
				txtDist.setVisibility(View.INVISIBLE);
				txtNeutro.setVisibility(View.VISIBLE);
				BtnInsereMetragem.setVisibility(View.INVISIBLE);
				BtnInsereNeutro.setVisibility(View.INVISIBLE);
				chkTrechoNovo.setVisibility(View.VISIBLE);
			}
		});
		
		BtnConfirma.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				txtInsereApos.setVisibility(View.INVISIBLE);
				lblInsereTrecho.setVisibility(View.INVISIBLE);
				BtnConfirma.setVisibility(View.INVISIBLE);
				BtnCancela.setVisibility(View.INVISIBLE);
				BtnInsereMetragem.setVisibility(View.VISIBLE);
				BtnInsereNeutro.setVisibility(View.VISIBLE);
				chkTrechoNovo.setVisibility(View.INVISIBLE);

				cursor.moveToPosition(Integer.parseInt(txtInsereApos.getText().toString()) - 1);
				
				cReferencia referencia = new cReferencia();
				
				if (chkTrechoNovo.isChecked()) {
					referencia.setTrecho(Double.parseDouble(cursor.getString(1)) + 1);
				}
				else {
					referencia.setTrecho(Double.parseDouble(cursor.getString(1)));
				}
				
				if (txtNeutro.isShown()) {
					referencia.setVelocidade(0);
					referencia.setDistancia(0);
					referencia.setNeutro(Double.parseDouble(txtNeutro.getText().toString()));
				}
				else {
					if (chkTrechoNovo.isChecked()) {
						referencia.setVelocidade(Double.parseDouble(txtVeloc.getText().toString()));
					}
					else {
						referencia.setVelocidade(Double.parseDouble(cursor.getString(2)));
					}
						
						referencia.setDistancia(Double.parseDouble(txtDist.getText().toString()));
						referencia.setNeutro(0);
					
				}
				
				
//				DBHandler db = new DBHandler(context);
				db.InsereTrecho(chkTrechoNovo.isChecked(), Integer.parseInt(txtInsereApos.getText().toString()), referencia);
				
				cursor.requery();
				
				txtVeloc.setVisibility(View.VISIBLE);
				txtDist.setVisibility(View.VISIBLE);
				txtNeutro.setVisibility(View.VISIBLE);
				
				txtVeloc.setText(blankDisplay);
				txtDist.setText(blankDisplay);
				txtNeutro.setText(blankDisplay);
				txtInsereApos.setText(blankDisplay);
			}
		});
		
		BtnCancela.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				txtInsereApos.setVisibility(View.INVISIBLE);
				lblInsereTrecho.setVisibility(View.INVISIBLE);
				BtnConfirma.setVisibility(View.INVISIBLE);
				BtnCancela.setVisibility(View.INVISIBLE);
				txtVeloc.setVisibility(View.VISIBLE);
				txtDist.setVisibility(View.VISIBLE);
				txtNeutro.setVisibility(View.VISIBLE);
				BtnInsereMetragem.setVisibility(View.VISIBLE);
				BtnInsereNeutro.setVisibility(View.VISIBLE);
				chkTrechoNovo.setVisibility(View.INVISIBLE);
				
				txtVeloc.setText(blankDisplay);
				txtDist.setText(blankDisplay);
				txtNeutro.setText(blankDisplay);
				txtInsereApos.setText(blankDisplay);
			}
		});
		
		chkTrechoNovo.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (txtDist.isShown() && chkTrechoNovo.isChecked()) {
						txtVeloc.setVisibility(View.VISIBLE);
						txtVeloc.requestFocus();
					}
					else {
						txtVeloc.setVisibility(View.INVISIBLE);
						txtDist.requestFocus();
					}
					
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_display_altera_plan, menu);
		return true;
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		Context ctx = getApplicationContext();
		final DBHandler db = new DBHandler(ctx);
		
		db.atualizaBD(cursor);
	}
	
}
