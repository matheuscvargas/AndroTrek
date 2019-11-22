package com.trekking.androtrek;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;

public class Principal extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_principal, menu);
		return true;
	}
	
	public void abreConfig(View view) {
    	Intent intent = new Intent(this, DisplayConfig.class);
    	startActivity(intent);
    }
	
	public void abreLancPlan(View view) {
    	Intent intent = new Intent(this, DisplayLancPlan.class);
    	startActivity(intent);
    }

	public void abreCalc(View view) {
    	Intent intent = new Intent(this, DisplayCalc.class);
    	startActivity(intent);
    }

	public void abreEnduro(View view) {
    	Intent intent = new Intent(this, DisplayEnduro.class);
    	startActivity(intent);
    }
	public void abreAlteraPlan(View view) {
    	Intent intent = new Intent(this, DisplayAlteraPlan.class);
    	startActivity(intent);
    }

}
