package com.example.android4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.modelo.ReclamoSugerencia;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class VentanaConsultarReclamo extends Activity {

	private VentanaPrincipal principal;
	private Context cont;
	private ListView lstReclamos;
	private String usuario;
	private int idCondominio;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventana_consultar_reclamo);
		
		principal = VentanaPrincipal.getInstance(this);
		Bundle bundle = this.getIntent().getExtras();
		usuario = bundle.getString("usuario");
		idCondominio = bundle.getInt("condominio");
		lstReclamos = (ListView) findViewById(R.id.ltvReclamoJunta);
		cont = this;
		
		lstReclamos.setAdapter(new ListaAdapter(this,R.layout.reclamo,principal.reclamos) {
			
			@Override
			public void onEntrada(Object entrada, View view) {
				// TODO Auto-generated method stub
				DateFormat fechaFormato = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("es_ES"));
				
				TextView txvAsunto = (TextView) view.findViewById(R.id.txvAsuntoReclamoJunta); 
				txvAsunto.setText(((ReclamoSugerencia) entrada).getRazon()); 

	            TextView txvFecha = (TextView) view.findViewById(R.id.txvFechaReclamoJunta); 
	            txvFecha.setText(String.valueOf(fechaFormato.format(((ReclamoSugerencia) entrada).getFecha())));
	            
	            TextView txvDescripcion = (TextView) view.findViewById(R.id.txvDescripcionReclamo);
	            txvDescripcion.setText(((ReclamoSugerencia) entrada).getDescripcion().substring(0, 38) + "...");
	            
	            TextView txvInmueble = (TextView) view.findViewById(R.id.txvInmuebleReclamo);
	            txvInmueble.setText(((ReclamoSugerencia) entrada).getIdInmueble());
	            
			}
		});
		lstReclamos.setOnItemClickListener(new OnItemClickListener() {

        	@Override
        	public void onItemClick(AdapterView<?> padre, View vista, int posicion,
        			long id) {
        		// TODO Auto-generated method stub
        		ReclamoSugerencia reclamoElegido = (ReclamoSugerencia) padre.getItemAtPosition(posicion); 
        		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        		final Dialog dialog = new Dialog(cont);
    			dialog.setContentView(R.layout.detalle_reclamo);
    			dialog.setTitle("Detalle del Reclamo");
     
    			// set the custom dialog components - text, image and button
    			TextView txvAsuntoDetalle = (TextView) dialog.findViewById(R.id.txvAsuntoDetalle);
    			TextView txvFechaDetalle = (TextView) dialog.findViewById(R.id.txvFechaDetalle);
    			TextView txvDescripcionDetalle = (TextView) dialog.findViewById(R.id.txvDescripcionDetalle);
    			TextView txvInmuebleDetalle = (TextView) dialog.findViewById(R.id.txvInmuebleDetalle);
    			TextView txvNumero = (TextView) dialog.findViewById(R.id.txvReclamoNumero);
    			
    			txvAsuntoDetalle.setText("Asunto: " + reclamoElegido.getRazon());
    			txvFechaDetalle.setText("Fecha: " + format.format(reclamoElegido.getFecha()));
    			txvDescripcionDetalle.setText("Descripción: " + reclamoElegido.getDescripcion());
    			txvNumero.setText("Reclamo Nº: " + reclamoElegido.getId());
    			txvInmuebleDetalle.setText("Inmueble: " + reclamoElegido.getIdInmueble());
     
    			Button dialogButton = (Button) dialog.findViewById(R.id.btnAceptarDetalle);
    			// if button is clicked, close the custom dialog
    			dialogButton.setOnClickListener(new OnClickListener() {
    				@Override
    				public void onClick(View v) {
    					dialog.dismiss();
    				}
    			});
    			dialog.show();
        	}
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
