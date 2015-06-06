package com.example.android4;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class VentanaAgregarNoticia extends Activity implements OnClickListener{
	
	private VentanaPrincipal principal;
	private ArrayAdapter<String> adapter;
	private ArrayList<String> tiposNoticia;
	private EditText edtTitulo;
	private EditText edtDescripcion;
	private Spinner spnTipo;
	private Button btnAgregar;
	private Button btnCancelar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventana_agregar_noticia);
		
		principal = VentanaPrincipal.getInstance(this);
		tiposNoticia = new ArrayList<String>();
		this.cargarTiposNoticia();
		
		edtTitulo = (EditText) findViewById(R.id.edtTituloNoticaAgregar);
		edtDescripcion = (EditText) findViewById(R.id.edtDescripcionNoticiaAgregar);
		btnAgregar = (Button) findViewById(R.id.btnAgregarNoticia);
		btnCancelar = (Button) findViewById(R.id.btnCancelarNoticia);
		spnTipo = (Spinner) findViewById(R.id.spnTipoNoticiaAgregar);
		btnAgregar.setOnClickListener(this);
		btnCancelar.setOnClickListener(this);
		
	    adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tiposNoticia);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    spnTipo.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void cargarTiposNoticia(){
		
		for(int t=0; t < 3; t++){
			tiposNoticia.add("Noticia tipo " + t);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btnAgregarNoticia:
			intent = new Intent( VentanaAgregarNoticia.this, VentanaPrincipal.class);
			Toast.makeText(this, "Noticia Agregada", Toast.LENGTH_LONG).show();
			startActivity(intent);
			break;
		case R.id.btnCancelarReservar:
			intent = new Intent( VentanaAgregarNoticia.this, VentanaPrincipal.class);
			startActivity(intent);
			break;
		}
		
	}
	
}
