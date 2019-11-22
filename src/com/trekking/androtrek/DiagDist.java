package com.trekking.androtrek;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DiagDist extends Activity {
	double varDado;
	int auxTipo;
	EditText txtDado;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diag_dist);
		
		Context ctx = getApplicationContext();
		final DBHandler db = new DBHandler(ctx);
		
		Button bMais, bMais10, bMenos, bMenos10, bOk, bCancel;
		int auxRef;
		
		txtDado = (EditText) findViewById(R.DiagDist.txtDados);
		bMais = (Button) findViewById(R.DiagDist.bMais);
		bMais10 = (Button) findViewById(R.DiagDist.bMais10);
		bMenos = (Button) findViewById(R.DiagDist.bMenos);
		bMenos10 = (Button) findViewById(R.DiagDist.bMenos10);
		bOk = (Button) findViewById(R.DiagDist.bOK);
		bCancel = (Button) findViewById(R.DiagDist.btnCancela);
		
		Intent intDist = getIntent();
		
		auxRef = Integer.parseInt((String) intDist.getSerializableExtra("Ref"));

		final cReferencia referencia = db.getReferenciaByID(auxRef);

		auxTipo = Integer.parseInt((String) intDist.getSerializableExtra("Tipo"));
		varDado = Double.parseDouble((String) intDist.getSerializableExtra("Dado"));
		
		switch (auxTipo) {
		case 0:
			txtDado.setText(String.valueOf(varDado));		
			break;
		case 1:
			txtDado.setText(String.valueOf(varDado));		
			break;
		case 2:
			txtDado.setText(String.valueOf(Utils.ConvertSecsToMinutes(varDado)));
			break;
		}
		
		bMais.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				switch (auxTipo) {
				case 0:
					varDado = varDado + 1;
					txtDado.setText(String.valueOf(varDado));
					break;
				case 1:
					varDado = varDado + 1;
					txtDado.setText(String.valueOf(varDado));
					break;
				case 2:
					varDado = varDado + 1;
					txtDado.setText(String.valueOf(Utils.ConvertSecsToMinutes(varDado)));
					break;
				}

			}
		});
		bMais10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				switch (auxTipo) {
				case 0:
					varDado = varDado + 10;
					txtDado.setText(String.valueOf(varDado));
					break;
				case 1:
					varDado = varDado + 10;
					txtDado.setText(String.valueOf(varDado));
					break;
				case 2:
					varDado = varDado + 10;
					txtDado.setText(String.valueOf(Utils.ConvertSecsToMinutes(varDado)));
					break;
				}
			}
		});
		bMenos.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				switch (auxTipo) {
				case 0:
					varDado = varDado - 1;
					txtDado.setText(String.valueOf(varDado));
					break;
				case 1:
					varDado = varDado - 1;
					txtDado.setText(String.valueOf(varDado));
					break;
				case 2:
					varDado = varDado - 1;
					txtDado.setText(String.valueOf(Utils.ConvertSecsToMinutes(varDado)));
					break;
				}
			}
		});
		bMenos10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				switch (auxTipo) {
				case 0:
					varDado = varDado - 10;
					txtDado.setText(String.valueOf(varDado));
					break;
				case 1:
					varDado = varDado - 10;
					txtDado.setText(String.valueOf(varDado));
					break;
				case 2:
					varDado = varDado - 10;
					txtDado.setText(String.valueOf(Utils.ConvertSecsToMinutes(varDado)));
					break;
				}
			}
		});
		bOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				switch(auxTipo) {
				case 0:
					referencia.setVelocidade(varDado);
					referencia.setVelocOmite(0);
					break;
				case 1:
					referencia.setDistancia(varDado);
					referencia.setDistOmite(0);
					break;
				case 2:
					referencia.setNeutro(varDado);
					referencia.setTempOmite(0);
					break;
				}

				db.updateReferencia(referencia);
				
				Cursor cursor = db.getAllCursor();
//				
				db.atualizaBD(cursor);
				finish();

			}
		});
		bCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_diag_dist, menu);
		return true;
	}

}
