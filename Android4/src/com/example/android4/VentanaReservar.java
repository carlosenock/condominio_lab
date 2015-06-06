package com.example.android4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.modelo.AreaComun;
import com.modelo.Horario;
import com.servicio.ServicioAreaComun;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class VentanaReservar extends Activity implements OnClickListener{

	private VentanaPrincipal principal;
	private ListView lstDias;
	private TextView txvNombreArea;
	private TextView txvFechaInicio;
	private TextView txvFechaFinal;
	private TextView txvMonto;
	private Button btnReservar;
	private Button btnCancelar;
	private ArrayList<Horario> listaDias;
	private ArrayList<ArrayList<String>> bloquesHijos;
	private ArrayList<String> diasPadre;
	private DateFormat format;
	private String nombre;
	private Date fechaInicio;
	private Date fechaFin;
	private int idArea;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventana_reservar);

		principal = VentanaPrincipal.getInstance(this);
		format = new SimpleDateFormat("dd/MM/yyyy");
		Bundle bundle = this.getIntent().getExtras();

		nombre = bundle.getString("area");
		idArea = bundle.getInt("idArea");
		fechaInicio = convertirFecha(bundle.getString("inicio"));
		fechaFin = convertirFecha(bundle.getString("fin"));
		
		btnReservar = (Button) findViewById(R.id.btnReservar);
		btnCancelar = (Button) findViewById(R.id.btnCancelarReservar);
		txvNombreArea = (TextView) findViewById(R.id.txvAreaReservar);
		txvFechaInicio = (TextView) findViewById(R.id.txvFechaInicioReservar);
		txvFechaFinal = (TextView) findViewById(R.id.txvFechaFinalReservar);
		txvMonto = (TextView) findViewById(R.id.txvMontoReservar);
		lstDias = (ListView) findViewById(R.id.lsvDiasReservar);

		txvNombreArea.setText("Area a Reservar: " + nombre);
		txvFechaInicio.setText("Fecha de Inicio:" + format.format(fechaInicio));
		txvFechaFinal.setText("Fecha de Culminación:" + format.format(fechaFin));
		btnReservar.setOnClickListener(this);
		btnCancelar.setOnClickListener(this);

		ArrayList<String> padre = cargarPadre(idArea);
		ArrayAdapter<String> diasReservados = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, padre);
		
		lstDias.setAdapter(diasReservados);
		txvMonto.setText("Monto Total:" + calcularMontoReservacion(idArea));

	}

	public ArrayList<String> cargarPadre(int idArea){
		Calendar fecha = Calendar.getInstance();
		Calendar fechaFinal = Calendar.getInstance();
		fecha.setTime(fechaInicio);
		Log.v("Fecha Inicio", fecha.getTime().toString());
		fechaFinal.setTime(fechaFin);
		Log.v("Fecha Final", fechaFinal.getTime().toString());
		listaDias = new ArrayList<Horario>();
		diasPadre = new ArrayList<String>();
		Log.v("Fecha ", fecha.getTime().toString());

		while(!fecha.after(fechaFinal)){
			Log.v("Fecha ", fecha.getTime().toString());

			diasPadre.add(format.format(fecha.getTime()));
			fecha.add(Calendar.DATE, 1);
		}
		return diasPadre;
	}
	
	public float calcularMontoReservacion(int idArea){
		ServicioAreaComun serArea = new ServicioAreaComun();
		AreaComun area = serArea.BuscarAreaComun(idArea);
		
		return area.getMonto() * diasPadre.size();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ventana_reservar, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btnReservar:
			intent = new Intent( VentanaReservar.this, VentanaPrincipal.class);
			Toast.makeText(this, "Reservación Procesada", Toast.LENGTH_LONG).show();
			startActivity(intent);
			break;
		case R.id.btnCancelarReservar:
			intent = new Intent( VentanaReservar.this, VentanaPrincipal.class);
			startActivity(intent);
			break;
		}

	}
	
	public Date convertirFecha(String fecha){
		Calendar calendario = Calendar.getInstance();
		Calendar calendarioActual = Calendar.getInstance();
		Log.v("FECHA A CONVERTIR", fecha);
		String fechaFraccionada[] = fecha.split(" / ");

		calendario.add(Calendar.DAY_OF_MONTH, Integer.valueOf(fechaFraccionada[0]) - calendarioActual.get(Calendar.DAY_OF_MONTH));
		calendario.add(Calendar.MONTH, Integer.valueOf(fechaFraccionada[1])-2);
		calendario.add(Calendar.YEAR, Integer.valueOf(fechaFraccionada[2]) - calendarioActual.get(Calendar.YEAR));
		return calendario.getTime();
	}

}
