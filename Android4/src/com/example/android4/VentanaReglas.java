package com.example.android4;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Locale;

import com.modelo.Proveedor;
import com.modelo.Regla;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class VentanaReglas extends Activity {

	private VentanaPrincipal principal;
	private Context cont;
	private TextView txvNombreArea;
	private ListView ltvReglas;
	private String nombreArea;
	private int idArea;
	private ArrayList<Regla> reglasArea;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventana_reglas);
		
		principal = VentanaPrincipal.getInstance(this);
		cont = this;
		
		Bundle bundle = this.getIntent().getExtras();
		nombreArea = bundle.getString("area");
		idArea = bundle.getInt("idArea");
		reglasArea = this.buscarReglas(idArea);
		
		txvNombreArea = (TextView) findViewById(R.id.txvAreaReglas);
		txvNombreArea.setText("Reglas del area: " + nombreArea);
		ltvReglas = (ListView) findViewById(R.id.ltvReglasAreas);
		ltvReglas.setClickable(true);
		ltvReglas.setAdapter(new ListaAdapter(this,R.layout.regla,reglasArea) {
			
			@Override
			public void onEntrada(Object entrada, View view) {
				// TODO Auto-generated method stub
				TextView txvReglaNombre = (TextView) view.findViewById(R.id.txvReglaNombre); 
				txvReglaNombre.setText("Regla " + ((Regla) entrada).getId()); 

	            TextView txvDescripcion = (TextView) view.findViewById(R.id.txvReglaDescripcion);
	            txvDescripcion.setText("Descripción: " + ((Regla) entrada).getDescripcion());
	            
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public ArrayList<Regla> buscarReglas(int area){
		ArrayList<Regla> reglas = new ArrayList<Regla>();
		
		for(int b=0; b < principal.reglas.size(); b++){
			if(principal.reglas.get(b).getIdArea() == area){
				reglas.add(principal.reglas.get(b));
			}
		}
		
		return reglas;
	}

}
