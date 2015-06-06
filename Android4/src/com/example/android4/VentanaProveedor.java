package com.example.android4;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;

import com.modelo.Proveedor;
import com.modelo.ReclamoSugerencia;
import com.modelo.Servicio;
import com.servicio.ServicioProveedor;
import com.servicio.ServicioServicio;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class VentanaProveedor extends Activity {
	
	private ArrayList<String> proveedorPadre;
	private ArrayList<ArrayList<String>> serviciosHijos;
	private VentanaPrincipal principal;
	private ArrayList<Proveedor> proveedorCondominio;
	private ArrayList<Servicio> serviciosProveedor;
	private Context cont;
	private ExpandableListView lstProveedores;
	private ListView ltvProveedoresDetalle;
	private ListAdapter adapterProveedor;
	private int idCondominio;
	private TabHost tab;
	private ServicioProveedor serProveedor;
	private ServicioServicio serServicios;
	private Vector<Object> datos = null;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventana_proveedor);
		
		principal = VentanaPrincipal.getInstance(this);
		cont = this;
		idCondominio = principal.condominio.getId();
		serProveedor = new ServicioProveedor();
		serServicios = new ServicioServicio();
		lstProveedores = (ExpandableListView) findViewById(R.id.elvProveedores);
		ltvProveedoresDetalle = (ListView) findViewById(R.id.ltvProveedores);
		
		ArrayList<String> padre = cargarPadre(idCondominio);
		ArrayList<ArrayList<String>> hijos = cargarHijos();
		adapterProveedor = new ListAdapter(this, padre, hijos);
		adapterProveedor.setInflater((LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE), this);
		lstProveedores.setClickable(true);
		lstProveedores.setAdapter(adapterProveedor);
		
		ltvProveedoresDetalle.setAdapter(new ListaAdapter(this,R.layout.proveedor,proveedorCondominio) {
			
			@Override
			public void onEntrada(Object entrada, View view) {
				// TODO Auto-generated method stub
				DateFormat fechaFormato = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("es_ES"));
				
				TextView txvProveedor = (TextView) view.findViewById(R.id.txvProveedorDetalle); 
				txvProveedor.setText("Proveedor: " + ((Proveedor) entrada).getRazonSocial()); 

	            TextView txvRif = (TextView) view.findViewById(R.id.txvRifProveedorDetalle); 
	            txvRif.setText("RIF: " + ((Proveedor) entrada).getRif());
	            
	            TextView txvDireccion = (TextView) view.findViewById(R.id.txvDireccionProveedorDetalle);
	            txvDireccion.setText("Dirección: " + ((Proveedor) entrada).getDireccion());
	            
	            TextView txvTelefono = (TextView) view.findViewById(R.id.txvTelefonoProveedorDetalle);
	            txvTelefono.setText("Teléfono: " + ((Proveedor) entrada).getTelefono());
	            
	            TextView txvCorreo = (TextView) view.findViewById(R.id.txvCorreoProveedorDetalle);
	            txvCorreo.setText("Correo: " + ((Proveedor) entrada).getCorreo());
	            
			}
		});
		
		tab = (TabHost) findViewById(R.id.tabhost);
		tab.setup();
		
		TabSpec spec = tab.newTabSpec("TAG1");
		spec.setIndicator("Datos");
		spec.setContent(R.id.tabProveedorDatos);
		tab.addTab(spec);
		
		spec = tab.newTabSpec("TAG2");
		spec.setIndicator("Servicios");
		spec.setContent(R.id.tabProveedorServicios);
		tab.addTab(spec);
	}

	public ArrayList<ArrayList<String>> cargarHijos(){
		serviciosHijos = new ArrayList<ArrayList<String>>();

		for(int f=0; f < proveedorCondominio.size(); f++){
			ArrayList<String> hijo = new ArrayList<String>();
			
			this.cargarServiciosProveedor(proveedorCondominio.get(f).getId());
			for(int j=0; j < serviciosProveedor.size(); j++){
				
				hijo.add(serviciosProveedor.get(j).getCodigo() + "          " + serviciosProveedor.get(j).getDescripcion());
				
			}
			serviciosHijos.add(hijo);
		}

		return serviciosHijos;

	}
	
	public void cargarServiciosProveedor(int idProv){
		serviciosProveedor = new ArrayList<Servicio>();
		Log.v("Id Prov: ", String.valueOf(idProv));

		datos = new Vector();
		datos.clear();
		datos.add(idProv);
		serviciosProveedor = serServicios.BuscarServicios(datos);
		
	}
	
	public ArrayList<String> cargarPadre(int condominio){
		datos = new Vector();
		datos.clear();
		datos.add(condominio);
		proveedorCondominio = serProveedor.BuscarProveedores(datos);

		proveedorPadre = new ArrayList<String>();
		
		for(int o=0; o < proveedorCondominio.size(); o++){
			proveedorPadre.add(proveedorCondominio.get(o).getRazonSocial());
		}
		return proveedorPadre;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Intent intent;
		
		switch (item.getItemId()) {
		case R.id.menInicio:
			Toast.makeText(this, "Ha seleccionado Inicio", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaProveedor.this, VentanaMenu.class);
			startActivity(intent);
			break;
		case R.id.menCartelera:
			Toast.makeText(this, "Ha seleccionado la Cartelera", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaProveedor.this, VentanaCartelera.class);
			startActivity(intent);
			break;
		case R.id.menAreas:
			Toast.makeText(this, "Ha seleccionado las Areas Comunes", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaProveedor.this, VentanaAreaComun.class);
			startActivity(intent);
			break;
		case R.id.menRecibos:
			Toast.makeText(this, "Ha seleccionado Recibos", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menReclamo:
			Toast.makeText(this, "Ha seleccionado Reclamos", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaProveedor.this, VentanaReclamoSugerencia.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	
	}
	
	



}
