package com.example.android4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.modelo.AreaComun;
import com.modelo.Recibo;

import android.os.Bundle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class HistorialReciboActivity extends Activity {

	private ScrollView encabezado;
	
	private TableLayout tabla;  
	private TableRow.LayoutParams layoutFila;  
	private TableRow.LayoutParams layoutPeriodo;  
	private TableRow.LayoutParams layoutMRecibo;
	private TableRow.LayoutParams layoutMAbonado;
	private TableRow.LayoutParams layoutMPendiente;
	private TableRow.LayoutParams layoutVer;
	
	private float alicuota =0;
	
	private Context contexto;
	private VentanaPrincipal principal;

	private String cabeceras[] = {"Periodo", "Recibo", "Abonado", "Pendiente","Ver"};
	private ArrayList<Recibo> recibos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historial_recibo);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		/*encabezado = (ScrollView)findViewById(R.id.scrollTablaEncabezado); 
		encabezado.setVerticalScrollBarEnabled(false); 
		encabezado.setHorizontalScrollBarEnabled(false);*/
		Bundle extras = getIntent().getExtras();
		String criterio = extras.getString("criterio");
		alicuota = extras.getFloat("alicuota");
		principal = VentanaPrincipal.getInstance(this);
		if (criterio.equalsIgnoreCase("morosos")){
			recibos = principal.recibosMora;
		}else{

			recibos = principal.recibos;
		}
		tabla = (TableLayout)findViewById(R.id.tblTablaHistorial);
		layoutFila = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);  
		layoutPeriodo = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT);  
		layoutMRecibo = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT);
		layoutMAbonado = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT);
		layoutMPendiente = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT);
		layoutVer = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.MATCH_PARENT);
		contexto = getApplicationContext();
		
		this.agregarCabecera();
		this.agregarFilasTabla();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.historial_recibo, menu);
		return true;
	}
	
	public void agregarCabecera(){  

		//Log.v("Cabereca", "Fueron declaradas las variables");

		TableRow fila = new TableRow(this);
		TextView txvPeriodoCabecera = new TextView(this);
		TextView txvReciboCabecera = new TextView(this);
		TextView txvAbonoCabecera = new TextView(this);
		TextView txvPendienteCabecera = new TextView(this);
		TextView txvVerCabecera = new TextView(this);
		
		fila.setLayoutParams(layoutFila);

		txvPeriodoCabecera.setText(cabeceras[0]); 
		txvPeriodoCabecera.setGravity(Gravity.CENTER_HORIZONTAL);  
		txvPeriodoCabecera.setTextAppearance(this,R.style.etiqueta);  
		txvPeriodoCabecera.setBackgroundResource(R.drawable.cabecera_tabla);
		txvPeriodoCabecera.setLayoutParams(layoutPeriodo);
		
		fila.addView(txvPeriodoCabecera);

		txvReciboCabecera.setText(cabeceras[1]);  
		txvReciboCabecera.setGravity(Gravity.CENTER_HORIZONTAL);  
		txvReciboCabecera.setTextAppearance(this,R.style.etiqueta);  
		txvReciboCabecera.setBackgroundResource(R.drawable.cabecera_tabla);
		txvReciboCabecera.setLayoutParams(layoutMRecibo);
		fila.addView(txvReciboCabecera);

		txvAbonoCabecera.setText(cabeceras[2]);  
		txvAbonoCabecera.setGravity(Gravity.CENTER_HORIZONTAL);  
		txvAbonoCabecera.setTextAppearance(this,R.style.etiqueta);  
		txvAbonoCabecera.setBackgroundResource(R.drawable.cabecera_tabla);
		txvAbonoCabecera.setLayoutParams(layoutMAbonado);
		fila.addView(txvAbonoCabecera);
		
		txvPendienteCabecera.setText(cabeceras[3]);  
		txvPendienteCabecera.setGravity(Gravity.CENTER_HORIZONTAL);  
		txvPendienteCabecera.setTextAppearance(this,R.style.etiqueta);  
		txvPendienteCabecera.setBackgroundResource(R.drawable.cabecera_tabla);
		txvPendienteCabecera.setLayoutParams(layoutMPendiente);
		fila.addView(txvPendienteCabecera);
		
		txvVerCabecera.setText(cabeceras[4]);  
		txvVerCabecera.setGravity(Gravity.CENTER_HORIZONTAL);  
		txvVerCabecera.setTextAppearance(this,R.style.etiqueta);  
		txvVerCabecera.setBackgroundResource(R.drawable.cabecera_tabla);
		txvVerCabecera.setLayoutParams(layoutVer);
		fila.addView(txvVerCabecera);

		tabla.addView(fila);

	}
	
	public String invertirFecha(Date fecha){
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);
		return  fechaCalendar.get(Calendar.YEAR) + "-"+ (fechaCalendar.get(Calendar.MONTH)+1) + "-"+fechaCalendar.get(Calendar.DAY_OF_MONTH);
	}
	
	public void agregarFilasTabla(){  

		for(int i = 0;i<recibos.size();i++){  

			TextView txvPeriodo = new TextView(this);
			TextView txvRecibo = new TextView(this);
			TextView txvAbono = new TextView(this);
			TextView txvPendiente = new TextView(this);
			ImageButton btnVer = new ImageButton(this);
			TableRow fila = new TableRow(this);
			fila = new TableRow(this);
			fila.setLayoutParams(layoutFila);  

			final int idRecibo = recibos.get(i).getIdrecibocobro();
			final Date nombre = recibos.get(i).getFecharecibocobro();
			
			txvPeriodo.setText(invertirFecha(recibos.get(i).getFecharecibocobro()));  
			txvPeriodo.setGravity(Gravity.CENTER_HORIZONTAL);  
			txvPeriodo.setTextAppearance(this,R.style.etiqueta);  
			txvPeriodo.setBackgroundResource(R.drawable.celda_tabla);  
			txvPeriodo.setLayoutParams(layoutPeriodo);
			fila.addView(txvPeriodo);

			txvRecibo.setText(String.valueOf(recibos.get(i).getMontorecibocobro()));  
			txvRecibo.setGravity(Gravity.CENTER_HORIZONTAL);  
			txvRecibo.setTextAppearance(this,R.style.etiqueta);  
			txvRecibo.setBackgroundResource(R.drawable.celda_tabla);  
			txvRecibo.setLayoutParams(layoutPeriodo);
			fila.addView(txvRecibo);
			
			txvAbono.setText(String.valueOf(recibos.get(i).getAbonorecibocobro()));  
			txvAbono.setGravity(Gravity.CENTER_HORIZONTAL);  
			txvAbono.setTextAppearance(this,R.style.etiqueta);  
			txvAbono.setBackgroundResource(R.drawable.celda_tabla);  
			txvAbono.setLayoutParams(layoutMRecibo);
			fila.addView(txvAbono);
			
			txvPendiente.setText(String.valueOf(recibos.get(i).getCuotapendienterecibo()));  
			txvPendiente.setGravity(Gravity.CENTER_HORIZONTAL);  
			txvPendiente.setTextAppearance(this,R.style.etiqueta);  
			txvPendiente.setBackgroundResource(R.drawable.celda_tabla);  
			txvPendiente.setLayoutParams(layoutMRecibo);
			fila.addView(txvPendiente);

			btnVer.setImageResource(R.drawable.ic_action_search_n);
			btnVer.setBackgroundResource(R.drawable.celda_tabla);  
			btnVer.setLayoutParams(layoutVer);
			btnVer.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent( HistorialReciboActivity.this, DetallereciboActivity.class);
					Bundle b = new Bundle();

					b.putInt("id", idRecibo);
					b.putFloat("alicuota", alicuota);
					b.putString("nombre", String.valueOf(nombre));

					intent.putExtras(b);
					startActivity(intent);

				}
			});
			fila.addView(btnVer);

			tabla.addView(fila);
		}  

	}  
	@SuppressLint("NewApi")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
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
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}


}
