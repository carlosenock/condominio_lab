package com.example.android4;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.example.android4.VentanaCheckout;
import com.example.android4.R;
import com.servicio.ServicioBancos;
import com.servicio.ServicioFormaPago;
import com.servicio.ServicioPagos;
import com.modelo.Banco;
import com.modelo.FormaPago;
import com.modelo.Inmueble;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class VentanaNotificaionPago extends Activity {


	private ArrayList<FormaPago> formasPago = new ArrayList<FormaPago>();
	private ServicioFormaPago serFormas = new ServicioFormaPago();
	
	private ArrayList<Banco> listaBancos = new ArrayList<Banco>();
	private ServicioBancos serBancos = new ServicioBancos();
	
	private EditText monto = null;
	private EditText numeroDocumento = null;
	private EditText cedula = null;
	private int codFormapago =0;
	
	private float montoDeuda = 0;
	private float alicuota = 0;
	private int idrazondepago=0;
	private int idCondominio=0;
	
	private int idBanco=0;

	private Spinner spinnerForma = null;
	private Spinner spinnerBanco = null;
	private TextView deuda = null;
	private TextView fase= null;
	
	private Button buttonNotificar = null;
	private Button buttonCancelar = null;
	private Button buttonDetalle = null;
	
	private LinearLayout panelMonto = null;
	private RadioButton total = null;
	private RadioButton parte = null;

	private RelativeLayout notificacion = null;
	private RelativeLayout credito = null;
	private LinearLayout datosNotificacion = null;

	
	private List<String> list = new ArrayList<String>();
	private String seleccionado="0";
	

	private List<String> list2 = new ArrayList<String>();

	private String seleccionado2 = "0";


	
	private List<String> listaformas = new ArrayList<String>();
	private List<String> listaSpinBancos = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventana_notificaion_pago);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			montoDeuda = extras.getFloat("monto");
			idrazondepago = extras.getInt("idrazondepagopago");
			idCondominio = extras.getInt("condominio");
			alicuota=extras.getFloat("alicuota");

			Log.v("idCondominio",String.valueOf(idCondominio));
		}
		
		
		cargarFormaPago();
		cargarBancos();

		total = (RadioButton) findViewById(R.id.radioBtnTotal);
		parte = (RadioButton) findViewById(R.id.radioBtnParte);
		panelMonto = (LinearLayout)findViewById(R.id.panelMonto);
		buttonNotificar = (Button) findViewById(R.id.btnNotificar);
        buttonCancelar = (Button) findViewById(R.id.btnCancelar);
        buttonDetalle = (Button) findViewById(R.id.btnDetalles);
		numeroDocumento = (EditText)findViewById(R.id.eddocumento);
		cedula = (EditText)findViewById(R.id.edcedula);
		monto = (EditText)findViewById(R.id.edMonto);
		deuda = (TextView)findViewById(R.id.teDeuda);
		fase = (TextView)findViewById(R.id.textFase);
		deuda.setText(String.valueOf(montoDeuda));
		notificacion = (RelativeLayout)findViewById(R.id.notificacion);
		credito = (RelativeLayout)findViewById(R.id.credito);
		datosNotificacion = (LinearLayout)findViewById(R.id.datosNotificacion);
		
		
		buttonCancelar.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v) {
        		String fase_num = String.valueOf(fase.getText());
				
				if(Integer.parseInt(fase_num)==1){
					notificacion.setVisibility(RelativeLayout.VISIBLE);
					datosNotificacion.setVisibility(LinearLayout.GONE);
					credito.setVisibility(RelativeLayout.GONE);
					seleccionado2="0";
					fase.setText("0");
					buttonNotificar.setText("Siguiente");
				}
				if(Integer.parseInt(fase_num)==0){
        			//Hacemos un click al boton Cancelar
        			numeroDocumento.setText("");
        			monto.setText("");
    				finish();
				}
        	}
        });
		
		parte.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v) {
        			//Hacemos un click al radiobuton parte
        			//panelMonto.setVisibility(1);
        	}
        });
		
		total.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v) {
        			//Hacemos un click al radiobuton parte
        			//panelMonto.setVisibility(0);
        	}
        });
		
		buttonDetalle.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v) {
        		Intent intent = new Intent( VentanaNotificaionPago.this, HistorialReciboActivity.class);
        		Bundle b = new Bundle();
        		b.putString("criterio", "morosos");
        		b.putFloat("alicuota", alicuota);
        		intent.putExtras(b);
        		startActivity(intent);
        	}
        		
        	} );
		
		buttonNotificar.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v){
				
				if(montoDeuda==0){

					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getBaseContext());
			 
						// set title
						alertDialogBuilder.setTitle("¡Atencion!");
			 
						// set dialog message
						alertDialogBuilder
							.setMessage("No posee deuda pendiente")
							.setCancelable(false)
							.setNegativeButton("Ok",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int id) {
									VentanaNotificaionPago.this.finish();
									
								}
							  });
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
		 
						// show it
						alertDialog.show();
					
				}else{
				String fase_num = String.valueOf(fase.getText());
				
				if(Integer.parseInt(fase_num)==0 ){
					if(seleccionado2.equalsIgnoreCase("Tarjeta de Credito")){
						notificacion.setVisibility(RelativeLayout.GONE);
						credito.setVisibility(RelativeLayout.VISIBLE);
						fase.setText("1");
						buttonNotificar.setText("   Enviar   ");
					}else{
						notificacion.setVisibility(RelativeLayout.GONE);
						datosNotificacion.setVisibility(LinearLayout.VISIBLE);
						fase.setText("1");
						buttonNotificar.setText("   Enviar   ");
						}
					}	
				if(Integer.parseInt(fase_num)==1){
				Intent i= new Intent(VentanaNotificaionPago.this,VentanaCheckout.class);
				Bundle bund = new Bundle();
				if(total.isChecked()==true){
					bund.putFloat("montopagar",montoDeuda);
				}else if(parte.isChecked()==true){
					bund.putFloat("montopagar", Float.parseFloat((String.valueOf(monto.getText()))));
				}
				for(int indice=0;indice<listaformas.size();indice++){
					if(String.valueOf(listaformas.get(indice)).equalsIgnoreCase(seleccionado))
					//codFormapago = Integer.parseInt(listaformas.get(indice-1));
					codFormapago = Integer.parseInt((listaformas.get(indice-1)));
				}
				for(int ind=0;ind<listaSpinBancos.size();ind++){
					if(String.valueOf(listaSpinBancos.get(ind)).equalsIgnoreCase(seleccionado2))
					//codFormapago = Integer.parseInt(listaformas.get(indice-1));
					idBanco = Integer.parseInt((listaSpinBancos.get(ind-1)));
				}
				
				bund.putFloat("montodeuda", montoDeuda);
				bund.putString("documento", (String.valueOf(numeroDocumento.getText())));
				bund.putInt("idFormapago", codFormapago); 
				bund.putString("formaPago", seleccionado);
				bund.putInt("condominio", idCondominio);
				bund.putInt("idrazondepagopago", idrazondepago);
				bund.putString("ci",(String.valueOf(cedula.getText())));
				//Log.v("ci ", (String.valueOf(cedula.getText())));
				bund.putInt("idBanco", idBanco);
				//Log.v("idBanco ",""+ idBanco);
				i.putExtras(bund);
				startActivity(i);
				finish();
				}
			}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ventana_notificaion_pago, menu);
		return true;
		
	}
	
	// add items into spinner dynamically
	
	public void destruir(){
		finish();
	}
	  
	  public void cargarFormaPago(){


		  formasPago = serFormas.BuscarFormasPago();
	           
		  for(int i=0;i<formasPago.size();i++){
			  
			  listaformas.add(String.valueOf(formasPago.get(i).getId()));
			  //Log.v("id "+i, String.valueOf(formasPago.get(i).getId()));
			  listaformas.add(formasPago.get(i).getDescripcion());
			  //Log.v("descip "+i, formasPago.get(i).getDescripcion());
			  
			  list.add(formasPago.get(i).getDescripcion());
			  
		  }
		  
		  spinnerForma = (Spinner) findViewById(R.id.spinnerFormaPago);
		
		  ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				  android.R.layout.simple_spinner_item, list);
		  dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  spinnerForma.setAdapter(dataAdapter);
		  spinnerForma.setOnItemSelectedListener(new OnItemSelectedListener() {

	            @Override
	            public void onItemSelected(AdapterView<?> arg0, View arg1,
	                    int arg2, long arg3) {
	                seleccionado=spinnerForma.getSelectedItem().toString();

	            }

	            @Override
	            public void onNothingSelected(AdapterView<?> arg0) {
	                // TODO Auto-generated method stub

	            }
	        });
	  	}
	  
	  
	  public void cargarBancos(){
		  listaBancos = serBancos.BuscarBancos(idCondominio);
          
		  for(int i=0;i<listaBancos.size();i++){
			  
			  listaSpinBancos.add(String.valueOf(listaBancos.get(i).getIdBanco()));
			  //Log.v("id "+i, String.valueOf(formasPago.get(i).getId()));
			  //listaSpinBancos.add(listaBancos.get(i).getRifBanco());
			  listaSpinBancos.add(listaBancos.get(i).getNombreBanco());
			  //Log.v("BANCO ", listaBancos.get(i).getNombreBanco() + " Condominio"+ idCondominio);

			  list2.add(listaBancos.get(i).getNombreBanco());
			  
		  }
		  spinnerBanco = (Spinner) findViewById(R.id.spinnerBancoPago);
		
		  ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				  android.R.layout.simple_spinner_item, list2);
		  dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  spinnerBanco.setAdapter(dataAdapter);
		  spinnerBanco.setOnItemSelectedListener(new OnItemSelectedListener() {

	            @Override
	            public void onItemSelected(AdapterView<?> arg0, View arg1,
	                    int arg2, long arg3) {
	                seleccionado2=spinnerBanco.getSelectedItem().toString();

	            }

	            @Override
	            public void onNothingSelected(AdapterView<?> arg0) {
	                // TODO Auto-generated method stub

	            }
	        });
		  
	  }

}
