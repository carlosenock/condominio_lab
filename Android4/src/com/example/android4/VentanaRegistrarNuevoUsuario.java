package com.example.android4;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class VentanaRegistrarNuevoUsuario extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventana_registrar_nuevo_usuario);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ventana_registrar_nuevo_usuario, menu);
		return true;
	}

}
