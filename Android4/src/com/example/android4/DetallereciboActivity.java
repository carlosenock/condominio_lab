package com.example.android4;

import com.servicio.ServicioDetalleRecibo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.ListView;

public class DetallereciboActivity extends Activity {

	private Context cont;
	private ListView ltvProveedoresDetalle;
	private int idCondominio;
	private ServicioDetalleRecibo serDetalle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detallerecibo);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detallerecibo, menu);
		return true;
	}

}
