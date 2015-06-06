package com.example.android4;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.modelo.AreaComun;

@SuppressLint("NewApi")
public class VentanaAreaComun extends Activity{

	private TableLayout tabla;  
	private TableRow.LayoutParams layoutFila;  
	private TableRow.LayoutParams layoutArea;  
	private TableRow.LayoutParams layoutHorario;
	private TableRow.LayoutParams layoutReservacion;
	private TableRow.LayoutParams layoutMonto;
	
	private Context contexto;
	private VentanaPrincipal principal;

	private String cabeceras[] = { "Nombre", "Horario", " Monto", "Reservar"};
	private ArrayList<AreaComun> areasComunes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventana_area_comun);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		principal = VentanaPrincipal.getInstance(this);
		areasComunes = principal.areasComunes;
		tabla = (TableLayout)findViewById(R.id.tblTablaHistorial);
		layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);  
		layoutArea = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT);  
		layoutHorario = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
		layoutMonto = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
		layoutReservacion = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
		contexto = getApplicationContext();
		
		this.agregarCabecera();
		this.agregarFilasTabla();

	}

	public void agregarCabecera(){  

		Log.v("Cabereca", "Fueron declaradas las variables");

		TableRow fila = new TableRow(this);
		TextView txvAreaCabecera = new TextView(this);
		TextView txvHorarioCabecera = new TextView(this);
		TextView txvMontoCabecera = new TextView(this);
		TextView txvReservacionCabecera = new TextView(this);
		fila.setLayoutParams(layoutFila);

		txvAreaCabecera.setText(cabeceras[0]);  
		txvAreaCabecera.setGravity(Gravity.CENTER_HORIZONTAL);  
		txvAreaCabecera.setTextAppearance(this,R.style.etiqueta);  
		txvAreaCabecera.setBackgroundResource(R.drawable.cabecera_tabla);
		txvAreaCabecera.setLayoutParams(layoutArea);
		fila.addView(txvAreaCabecera);

		txvHorarioCabecera.setText(cabeceras[1]);  
		txvHorarioCabecera.setGravity(Gravity.CENTER_HORIZONTAL);  
		txvHorarioCabecera.setTextAppearance(this,R.style.etiqueta);  
		txvHorarioCabecera.setBackgroundResource(R.drawable.cabecera_tabla);
		txvHorarioCabecera.setLayoutParams(layoutHorario);
		fila.addView(txvHorarioCabecera);

		txvMontoCabecera.setText(cabeceras[2]);  
		txvMontoCabecera.setGravity(Gravity.CENTER_HORIZONTAL);  
		txvMontoCabecera.setTextAppearance(this,R.style.etiqueta);  
		txvMontoCabecera.setBackgroundResource(R.drawable.cabecera_tabla);
		txvMontoCabecera.setLayoutParams(layoutMonto);
		fila.addView(txvMontoCabecera);
		
		txvReservacionCabecera.setText(cabeceras[3]);  
		txvReservacionCabecera.setGravity(Gravity.CENTER_HORIZONTAL);  
		txvReservacionCabecera.setTextAppearance(this,R.style.etiqueta);  
		txvReservacionCabecera.setBackgroundResource(R.drawable.cabecera_tabla);
		txvReservacionCabecera.setLayoutParams(layoutReservacion);
		fila.addView(txvReservacionCabecera);

		tabla.addView(fila);

	}

	public void agregarFilasTabla(){  

		for(int i = 0;i<areasComunes.size();i++){  

			TextView tvArea = new TextView(this);
			TextView tvMonto = new TextView(this);
			ImageButton btnHorario = new ImageButton(this);
			ImageButton btnReservacion = new ImageButton(this);
			TableRow fila = new TableRow(this);
			fila = new TableRow(this);
			fila.setLayoutParams(layoutFila);  

			final int idArea = areasComunes.get(i).getId();
			final String nombre = areasComunes.get(i).getDescripcion();
			
			tvArea.setText(areasComunes.get(i).getNombre());  
			tvArea.setGravity(Gravity.CENTER_HORIZONTAL);  
			tvArea.setTextAppearance(this,R.style.etiqueta);  
			tvArea.setBackgroundResource(R.drawable.celda_tabla);  
			tvArea.setLayoutParams(layoutArea);
			fila.addView(tvArea);

			btnHorario.setImageResource(R.drawable.ic_action_search);
			btnHorario.setBackgroundResource(R.drawable.celda_tabla);  
			btnHorario.setLayoutParams(layoutArea);
			btnHorario.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(contexto, "Ha seleccionado Consultar Horario ", Toast.LENGTH_LONG).show();
					Intent intent = new Intent( VentanaAreaComun.this, VentanaAreaComunHorario.class);

					Bundle b = new Bundle();

					b.putInt("id", idArea);
					b.putString("nombre", nombre);
					Log.v("AREA: ", nombre);

					intent.putExtras(b);
					startActivity(intent);

				}
			});
			fila.addView(btnHorario);
			
			tvMonto.setText(String.valueOf(areasComunes.get(i).getMonto()));  
			tvMonto.setGravity(Gravity.CENTER_HORIZONTAL);  
			tvMonto.setTextAppearance(this,R.style.etiqueta);  
			tvMonto.setBackgroundResource(R.drawable.celda_tabla);  
			tvMonto.setLayoutParams(layoutMonto);
			fila.addView(tvMonto);

			btnReservacion.setImageResource(R.drawable.ic_action_go_to_today);
			btnReservacion.setBackgroundResource(R.drawable.celda_tabla);  
			btnReservacion.setLayoutParams(layoutArea);
			btnReservacion.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(contexto, "Ha seleccionado Reservar Area ", Toast.LENGTH_LONG).show();
					Intent intent = new Intent( VentanaAreaComun.this, VentanaReservacion.class);
					Bundle b = new Bundle();

					b.putInt("id", idArea);
					b.putString("nombre", nombre);
					Log.v("AREA: ", nombre);

					intent.putExtras(b);
					startActivity(intent);

				}
			});
			fila.addView(btnReservacion);

			tabla.addView(fila);
		}  

	}  

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Intent intent;

		switch (item.getItemId()) {
		case android.R.id.home:
			Intent upIntent = NavUtils.getParentActivityIntent(this);
			if(NavUtils.shouldUpRecreateTask(this, upIntent)){
				TaskStackBuilder.create(this)
					.addNextIntentWithParentStack(upIntent)
					.startActivities();
			}else{
				NavUtils.navigateUpTo(this, upIntent);
			}
			break;
		case R.id.menInicio:
			Toast.makeText(this, "Ha seleccionado Inicio", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaAreaComun.this, VentanaMenu.class);
			startActivity(intent);
			break;
		case R.id.menCartelera:
			Toast.makeText(this, "Ha seleccionado la Cartelera", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaAreaComun.this, VentanaCartelera.class);
			startActivity(intent);
			break;
		case R.id.menAreas:
			Toast.makeText(this, "Ha seleccionado las Areas Comunes", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaAreaComun.this, VentanaAreaComun.class);
			startActivity(intent);
			break;
		case R.id.menRecibos:
			Toast.makeText(this, "Ha seleccionado Recibos", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menReclamo:
			Toast.makeText(this, "Ha seleccionado Reclamos", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaAreaComun.this, VentanaReclamoSugerencia.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
