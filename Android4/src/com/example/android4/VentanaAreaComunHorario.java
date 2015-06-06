package com.example.android4;

import java.util.ArrayList;
import java.util.Vector;

import com.modelo.Horario;
import com.servicio.ServicioHorarioArea;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

public class VentanaAreaComunHorario extends Activity {

	private ArrayList<String> diasPadre;
	private ArrayList<ArrayList<String>> bloquesHijos;
	private VentanaPrincipal principal;
	private ArrayList<Horario> listaDias;
	private Vector<Object> datos;
	private int idArea;
	private String nombreArea;
	private TextView txvNombArea;
	private ExpandableListView lstBloqueHoras;
	private ListAdapter adapterHorario;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventana_area_comun_horario);

		principal = VentanaPrincipal.getInstance(this);		
		Bundle bundle = this.getIntent().getExtras();

		idArea = bundle.getInt("id");
		nombreArea = bundle.getString("nombre");
		datos = new Vector<Object>();

		txvNombArea = (TextView) findViewById(R.id.txvNombreArea);
		lstBloqueHoras = (ExpandableListView) findViewById(R.id.exlvAreaHorario);

		txvNombArea.setText("Area: " + nombreArea);

		ArrayList<String> padre = cargarPadre(idArea);
		ArrayList<ArrayList<String>> hijos = cargarHijos();
		adapterHorario = new ListAdapter(this, padre, hijos);
		adapterHorario.setInflater((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
		lstBloqueHoras.setClickable(true);

		lstBloqueHoras.setAdapter(adapterHorario);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ventana_area_comun_horario, menu);
		return true;
	}

	public ArrayList<ArrayList<String>> cargarHijos(){
		bloquesHijos = new ArrayList<ArrayList<String>>();

		Log.v("Cargando hijos", "Se van a cargar los hijos");
		for(int f=0; f < listaDias.size(); f++){
			ArrayList<String> hijo = new ArrayList<String>();
			Log.v("HIJO", "Cargando padre: " + listaDias.get(f).getDia());

			hijo.add("Apertura: " + listaDias.get(f).getHoraInicio() + " - Cierre: " + listaDias.get(f).getHoraFinal());
			Log.v("Hijos", "HI: " + listaDias.get(f).getHoraFinal());

			bloquesHijos.add(hijo);
		}

		return bloquesHijos;

	}

	public ArrayList<String> cargarPadre(int idArea){
		ServicioHorarioArea serHorarios = new ServicioHorarioArea();
		datos.add(idArea);
		listaDias = serHorarios.BuscarHorarioArea(datos);
		diasPadre = new ArrayList<String>();

		for(int o=0; o < listaDias.size(); o++){
			diasPadre.add(listaDias.get(o).getDia());
			Log.i("Cargando Dia: ", diasPadre.get(o));
		}
		return diasPadre;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Intent intent;

		switch (item.getItemId()) {
		case R.id.menInicio:
			Toast.makeText(this, "Ha seleccionado Inicio", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaAreaComunHorario.this, VentanaMenu.class);
			startActivity(intent);
			break;
		case R.id.menCartelera:
			Toast.makeText(this, "Ha seleccionado la Cartelera", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaAreaComunHorario.this, VentanaCartelera.class);
			startActivity(intent);
			break;
		case R.id.menAreas:
			Toast.makeText(this, "Ha seleccionado las Areas Comunes", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaAreaComunHorario.this, VentanaAreaComun.class);
			startActivity(intent);
			break;
		case R.id.menRecibos:
			Toast.makeText(this, "Ha seleccionado Recibos", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menReclamo:
			Toast.makeText(this, "Ha seleccionado Reclamos", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaAreaComunHorario.this, VentanaReclamoSugerencia.class);
			startActivity(intent);
			break;
		case R.id.menProveedor:
			Toast.makeText(this, "Ha seleccionado Proveedores", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaAreaComunHorario.this, VentanaProveedor.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);

	}



}
