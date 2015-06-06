package com.example.android4;

import java.util.Calendar;

import org.apache.commons.httpclient.URIException;

import com.modelo.Inmueble;
import com.modelo.ReclamoSugerencia;
import com.servicio.ServicioInmueble;
import com.servicio.ServicioReclamoSugerecia;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VentanaReclamoSugerencia extends Activity implements OnClickListener {
	
	private VentanaPrincipal principal;
	private ServicioReclamoSugerecia serReclamo;
	private ServicioInmueble serInmueble;
	private int idInmueble;
	private int idCondominio;
	private TextView txvInmueble;
	private EditText edtAsunto;
	private EditText edtDescripcion;
	private Button btnEnviar;
	private Button btnLimpiar;
	private Inmueble inmueble;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventana_reclamo);
		
		serInmueble = new ServicioInmueble();
		serReclamo = new ServicioReclamoSugerecia();
		Bundle b = this.getIntent().getExtras();
		idInmueble = b.getInt("inmueble");
		idCondominio = b.getInt("condominio");
		inmueble = serInmueble.BuscarInmueblePorId(idInmueble);
		
		principal = VentanaPrincipal.getInstance(this);
		txvInmueble = (TextView) findViewById(R.id.txvInmbuebleReclamos);
		edtAsunto = (EditText) findViewById(R.id.edtAsunto);
		edtDescripcion = (EditText) findViewById(R.id.edtDescripcionReclamo);
		btnEnviar = (Button) findViewById(R.id.btnEnviarReclamos);
		btnLimpiar = (Button) findViewById(R.id.btnCancelarReclamos);
		
		txvInmueble.setText(inmueble.getDescripcion() +" "+ inmueble.getCodigo());
		btnEnviar.setOnClickListener(this);
		btnLimpiar.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public String generarCodigo(int idCondominio){
		return "RC-" + (serReclamo.ContarReclamosxCondominio(idCondominio)+1);
	}
		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Intent intent;
		
		switch (item.getItemId()) {
		case R.id.menInicio:
			Toast.makeText(this, "Ha seleccionado Inicio", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaReclamoSugerencia.this, VentanaMenu.class);
			startActivity(intent);
			break;
		case R.id.menCartelera:
			Toast.makeText(this, "Ha seleccionado la Cartelera", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaReclamoSugerencia.this, VentanaCartelera.class);
			startActivity(intent);
			break;
		case R.id.menAreas:
			Toast.makeText(this, "Ha seleccionado las Areas Comunes", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaReclamoSugerencia.this, VentanaAreaComun.class);
			startActivity(intent);
			break;
		case R.id.menRecibos:
			Toast.makeText(this, "Ha seleccionado Recibos", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menReclamo:
			Toast.makeText(this, "Ha seleccionado Reclamos", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaReclamoSugerencia.this, VentanaReclamoSugerencia.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnEnviarReclamos:
			Calendar fecha = Calendar.getInstance();
			String razon = edtAsunto.getText().toString();
			String descripcion = edtDescripcion.getText().toString();
			String codigo = this.generarCodigo(idCondominio);
			int idInmueble = inmueble.getId();
			String codigoInmueble = inmueble.getCodigo();
			
			ReclamoSugerencia reclamo = new ReclamoSugerencia(codigo, razon, fecha.getTime(), descripcion, idInmueble, "A", codigoInmueble);
			try {
				serReclamo.InsertarReclamo(reclamo);
			} catch (URIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Toast.makeText(this, "El Reclamo ha sido enviado", Toast.LENGTH_SHORT).show();
			VentanaReclamoSugerencia.this.finish();
			break;
		}
	}

}
