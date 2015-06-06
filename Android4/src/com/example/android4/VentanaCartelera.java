package com.example.android4;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.text.DateFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android4.R;
import com.modelo.Noticia;

public class VentanaCartelera extends Activity {
	
	private VentanaPrincipal principal;
	private String usuario;
	private String idInmueble;
	private int idCondominio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventana_cartelera);
		
		Bundle bundle = this.getIntent().getExtras();
		usuario = bundle.getString("usuario");
		idCondominio = bundle.getInt("condominio");
		idInmueble = bundle.getString("inmueble");
		
		ListView lista = (ListView) findViewById(R.id.ltvNotificaciones);
        lista.setAdapter(new ListaAdapter(this,R.layout.noticia,principal.noticias) {
			
			@Override
			public void onEntrada(Object entrada, View view) {
				// TODO Auto-generated method stub
				DateFormat fechaFormato = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("es_ES"));
				
				TextView txvTitulo = (TextView) view.findViewById(R.id.txvTipo); 
				txvTitulo.setText(((Noticia) entrada).getNombreTipo()); 

	            TextView txvFecha = (TextView) view.findViewById(R.id.txvFecha); 
	            txvFecha.setText(String.valueOf(fechaFormato.format(((Noticia) entrada).getFecha())));
	            
	            TextView txvDescripcion = (TextView) view.findViewById(R.id.txvDescripcionNotica);
	            txvDescripcion.setText(((Noticia) entrada).getDescripcion()); 
			}
		});
        lista.setOnItemClickListener(new OnItemClickListener() {

        	@Override
        	public void onItemClick(AdapterView<?> padre, View vista, int posicion,
        			long id) {
        		// TODO Auto-generated method stub
        		Noticia noticiaElegida = (Noticia) padre.getItemAtPosition(posicion); 

        		CharSequence texto = "Seleccionado: " + noticiaElegida.getDescripcion();
        		Toast toast = Toast.makeText(VentanaCartelera.this, texto, Toast.LENGTH_LONG);
        		toast.show();
        	}
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Bundle b;
		Intent intent;
		
		switch (item.getItemId()) {
		case R.id.menInicio:
			Toast.makeText(this, "Ha seleccionado Inicio", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaCartelera.this, VentanaMenu.class);
			startActivity(intent);
			break;
		case R.id.menCartelera:
			Toast.makeText(this, "Ha seleccionado la Cartelera", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaCartelera.this, VentanaCartelera.class);
			startActivity(intent);
			break;
		case R.id.menAreas:
			Toast.makeText(this, "Ha seleccionado las Areas Comunes", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaCartelera.this, VentanaAreaComun.class);
			startActivity(intent);
			break;
		case R.id.menRecibos:
			Toast.makeText(this, "Ha seleccionado Recibos", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menReclamo:
			Toast.makeText(this, "Ha seleccionado Reclamos", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaCartelera.this, VentanaReclamoSugerencia.class);
			b = new Bundle();
			b.putString("inmueble", idInmueble);
			b.putInt("condominio", idCondominio);
			startActivity(intent);
			break;
		case R.id.menReservacion:
			Toast.makeText(this, "Ha seleccionado Reservación", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaCartelera.this, VentanaReservacion.class);
			b = new Bundle();
			b.putInt("condominio", idCondominio);
			startActivity(intent);
			break;
		case R.id.menProveedor:
			Toast.makeText(this, "Ha seleccionado Proveedores", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaCartelera.this, VentanaProveedor.class);
			b = new Bundle();
			b.putInt("condominio", idCondominio);
			intent.putExtras(b);
			startActivity(intent);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
