package com.example.android4;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import com.example.android4.R;
import com.example.android4.VentanaNotificaionPago;
import com.servicio.ServicioPagos;

import android.os.Bundle;
import android.app.Activity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VentanaCheckout extends Activity {

	private float montoDeuda = 0;
	private String documento = null;
	private float montoPago = 0;
	private int codForma = 0;
	private float resultado=0;
	private int idrazondepago=0;
	private int idCondominio=0;
	private String formaSeleccionada;
	private String codigoPago = "";
	private String fecha = "";
	private String cedula= "";
	private int idBanco =0;
	
	private EditText edMontoDeuda;
	private EditText edMontoPago;
	private EditText edFormaPago;
	private EditText edDocumento;
	
	private TextView mensajeOperacion;
	private TextView nota;
	
	private EditText edPendiente;
	
	private Button enviar;
	private Button atras;
	private Button salir;
	
	private LinearLayout formato;
	private LinearLayout mensaje;

	private Vector<Object> datos  = new Vector<Object>();
	private ServicioPagos serPago = new ServicioPagos();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventana_checkout);
		
	//se obtienen los datos
	Bundle extras= getIntent().getExtras();
	if (extras != null) {
		montoDeuda = extras.getFloat("montodeuda");
		documento = extras.getString("documento");
		montoPago = extras.getFloat("montopagar");
		codForma = extras.getInt("idFormapago");
		formaSeleccionada = extras.getString("formaPago");
		idrazondepago = extras.getInt("idrazondepagopago");
		idCondominio = extras.getInt("condominio");
		cedula = extras.getString("ci");
		idBanco= extras.getInt("idBanco");
			
		fecha = (String) DateFormat.format("yyyy-MM-dd", new Date());
		
		
		Vector<Object> dato = new Vector<Object>();
		dato.add(idCondominio);
		int numerodePagos= (serPago.contarPagoxCondominio(dato)+1);
		codigoPago = "P-"+(String.valueOf(numerodePagos)+"");//
		Log.v("codigoPago", codigoPago);
	}
	
	mensajeOperacion = (TextView)findViewById(R.id.txtMensaje);
	nota = (TextView)findViewById(R.id.txtNota);
	
	edMontoDeuda = (EditText)findViewById(R.id.edDeuda);
	edMontoPago = (EditText)findViewById(R.id.edMonto);
	edPendiente = (EditText)findViewById(R.id.edResultado);
	edDocumento = (EditText)findViewById(R.id.edDocumentoCheck);
	edFormaPago = (EditText)findViewById(R.id.edForma);
	
	edMontoDeuda = (EditText)findViewById(R.id.edDeuda);
	edMontoDeuda = (EditText)findViewById(R.id.edDeuda);
	
	
	formato = (LinearLayout)findViewById(R.id.formatoCheck);
	mensaje = (LinearLayout)findViewById(R.id.MensajeCheck);
	
	//mostramos los datos
	edMontoDeuda.setText(String.valueOf(montoDeuda));
	edMontoPago.setText(String.valueOf(montoPago));
	
	enviar = (Button)findViewById(R.id.enviarNotif);
	atras = (Button)findViewById(R.id.atrasNotif);
	salir = (Button)findViewById(R.id.btnSalir);
	
	resultado = (montoDeuda - montoPago);
	
	edPendiente.setText(String.valueOf(resultado));
	
	edDocumento.setText(documento);
	edFormaPago.setText(formaSeleccionada);
	
	String descripcion = "Pago de Recibo de Mes";
	int validacion = 0;
	String estatus = "A";
	int idReservacion = 0;
	int reciboCobro=1;
	
	
	datos.add(codigoPago);
	datos.add(descripcion);
	datos.add(edDocumento.getText().toString());
	datos.add(Float.parseFloat(edMontoPago.getText().toString()));
	datos.add(validacion);
	datos.add(fecha);
	datos.add(estatus);
	datos.add(codForma);
	datos.add(idrazondepago);
	datos.add(idReservacion);
	datos.add(reciboCobro);
	datos.add(idCondominio);
	datos.add(cedula);
	datos.add(idBanco);
	
	Log.v("codigoPago",""+codigoPago);
	Log.v("descripcion",""+descripcion);
	Log.v("edDocumento",""+edDocumento.getText().toString());
	Log.v("edMontoPago",""+Float.parseFloat(edMontoPago.getText().toString()));
	Log.v("validacion",""+validacion);
	Log.v("fecha",""+fecha);
	Log.v("estatus",""+estatus);
	Log.v("codForma",""+codForma);
	Log.v("idrazondepago",""+idrazondepago);
	Log.v("idReservacion",""+idReservacion);
	Log.v("reciboCobro",""+reciboCobro);
	Log.v("idCondominio",""+idCondominio);
	Log.v("cedula",""+cedula);
	Log.v("idBanco",""+idBanco);
	
	boolean exito = serPago.grabarPago(datos);
	
	if(exito==true){
		mensajeOperacion.setText(R.string.Mensaje_checkout_exito);
		nota.setText(R.string.nota_checkout_exito);

		formato.setVisibility(0);
		mensaje.setVisibility(1);
	}else {
		mensajeOperacion.setText(R.string.Mensaje_checkout_fallo);
		nota.setText(R.string.nota_checkout_fallo);	

		formato.setVisibility(0);
		mensaje.setVisibility(1);
	}
	/*enviar.setOnClickListener(new Button.OnClickListener() {
    	public void onClick(View v) {
    		
    		
    		String descripcion = "Pago Recibo";
    		int validacion = 0;
    		String estatus = "A";
    		int idReservacion = 0;
    		int reciboCobro=0;
    		
    		
    		datos.add(codigoPago);
			datos.add(descripcion);
			datos.add(edDocumento.getText().toString());
			datos.add(Float.parseFloat(edMontoPago.getText().toString()));
			datos.add(validacion);
			datos.add(fecha);
			datos.add(estatus);
			datos.add(codForma);
			datos.add(idrazondepago);
			datos.add(idReservacion);
			datos.add(reciboCobro);
			datos.add(idCondominio);
			boolean exito = serPago.grabarPago(datos);
			
			if(exito==true){
				mensajeOperacion.setText(R.string.Mensaje_checkout_exito);
				nota.setText(R.string.nota_checkout_exito);

				formato.setVisibility(0);
				mensaje.setVisibility(1);
			}else {
				mensajeOperacion.setText(R.string.Mensaje_checkout_fallo);
				nota.setText(R.string.nota_checkout_fallo);	

				formato.setVisibility(0);
				mensaje.setVisibility(1);
			}
    	}
    });*/
	
	atras.setOnClickListener(new Button.OnClickListener() {
    	public void onClick(View v) {
    			finish();
    	}
    });
	
	salir.setOnClickListener(new Button.OnClickListener() {
    	public void onClick(View v) {
			finish();
	}
});
	
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ventana_checkout, menu);
		return true;
	}

}
