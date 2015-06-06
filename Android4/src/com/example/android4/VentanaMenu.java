package com.example.android4;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Locale;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TableLayout;
import android.widget.TextView;

import com.modelo.JuntadeCondominios;
import com.modelo.Noticia;
import com.servicio.ServicioJuntadeCondominios;

public class VentanaMenu extends Activity implements OnClickListener{


	private ServicioJuntadeCondominios serJuntadeCondominios;
	private JuntadeCondominios juntadeCondominios;
	
	private TextView txvBienvenido;
	private ImageButton imbCartelera;
	private ImageButton imbAreas;
	private ImageButton imbProveedores;
	private ImageButton imbSugerencias;
	private ImageButton imbHistorial;
	
	private Button detallesMora;
	
	private ImageView imvCopropietario;
	private ImageButton imbNotifficacionPago;
	private TextView txvCopropietarioNombre;
	private TextView txvCondominioNombre;
	private TextView txvInmuebleNombre;
	private TextView txvDeuda;
	private RelativeLayout relPerfil;
	private LinearLayout montoDeudor;
	private SlidingDrawer sldNoticias;
	private String usuario;
	private int idInmueble;
	private int idCondominio;
	private int idLogin;
	private VentanaPrincipal principal;
	private Bitmap imagen;
	private String rol;
	private boolean junta = false;
	private String inicio;
	private float alicuota=0;
	
	private Vector<Object> datos = null;
	// Image loading result to pass to startActivityForResult method.
	private static int LOAD_IMAGE_RESULTS = 1;

	private TableLayout copropietario;
	private TableLayout juntadirectiva;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventana_menu);
		
		
		this.serJuntadeCondominios = new ServicioJuntadeCondominios();

		Bundle extras = getIntent().getExtras();
		idLogin = extras.getInt("idLogin");
		idCondominio = extras.getInt("condominio");
		junta=verificarenJunta();
		
		
		principal = VentanaPrincipal.getInstance(this);
		relPerfil = (RelativeLayout) findViewById(R.id.rltPerfil);
		montoDeudor = (LinearLayout) findViewById(R.id.LinearLayoutDeuda);
		txvBienvenido = (TextView) findViewById(R.id.txvBienvenidoMenu);
		imbCartelera = (ImageButton) findViewById(R.id.imbCartelera);
		imbAreas = (ImageButton) findViewById(R.id.imbAreas);
		imbHistorial = (ImageButton) findViewById(R.id.imbHistorialRecibos);
		imbProveedores = (ImageButton) findViewById(R.id.imbProveedores);
		imbSugerencias = (ImageButton) findViewById(R.id.imbReclamoSugerencia);
		imbNotifficacionPago = (ImageButton) findViewById(R.id.imbNotifficacionPago);
		detallesMora = (Button)findViewById(R.id.btnDetalleDeudaMenu);
		
		imvCopropietario = (ImageView) findViewById(R.id.imvCopropietario);
		txvCopropietarioNombre = (TextView) findViewById(R.id.txvCopropietarioNombre);
		txvCondominioNombre = (TextView) findViewById(R.id.txvCondominioNombre);
		txvInmuebleNombre = (TextView) findViewById(R.id.txvNombreInmueble);
		txvDeuda = (TextView) findViewById(R.id.txvDeuda);
		sldNoticias = (SlidingDrawer) findViewById(R.id.sldNoticias);
		
		copropietario= (TableLayout) findViewById(R.id.tableCopropietario);
		juntadirectiva= (TableLayout) findViewById(R.id.tableJunta);
		
		rol = principal.rol.getCodRol();

		alicuota= principal.inmueble.getAlicuota();
		
		if(rol.equalsIgnoreCase("R-3")){
			if(junta){
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						this);
		 
					// set title
					alertDialogBuilder.setTitle("Selección de Rol");
		 
					// set dialog message
					alertDialogBuilder
						.setMessage("Seleccione el rol con el cual desea ingresar")
						.setCancelable(false)
						.setPositiveButton("Cancelar",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activitydialog.cancel();
								VentanaMenu.this.finish();
								
							}
						  })
						.setNeutralButton("Junta de Concominio",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								
								inicio ="junta";
								juntadirectiva.setVisibility(TableLayout.VISIBLE);
								dialog.cancel();
							}
						})
						.setNegativeButton("Copropietario",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								
								inicio = "copropietario";
								copropietario.setVisibility(TableLayout.VISIBLE);
								dialog.cancel();
							}
						  });
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
				
			}else{
				copropietario.setVisibility(TableLayout.VISIBLE);
			}
		}/*else{
			juntadirectiva.setVisibility(TableLayout.VISIBLE);
		}*/
		
		imbCartelera.setOnClickListener(this);
		imbAreas.setOnClickListener(this);
		imbProveedores.setOnClickListener(this);
		imbSugerencias.setOnClickListener(this);
		imbNotifficacionPago.setOnClickListener(this);

		usuario = principal.copropietario.getNombre();
		idCondominio = principal.condominio.getId();
		idInmueble = principal.inmueble.getId();

		//txvBienvenido.setText("Bienvenido " + usuario);
		txvCondominioNombre.setText(String.valueOf(principal.condominio.getNombre()));
		txvCopropietarioNombre.setText(principal.copropietario.getNombre() + " " + principal.copropietario.getApellido());
		txvInmuebleNombre.setText(principal.inmueble.getDescripcion() + " " + principal.inmueble.getId());
		
		float deudaActual = Float.valueOf(principal.deudas);
		if(deudaActual>0){
			txvDeuda.setText(String.valueOf(deudaActual));
			montoDeudor.setVisibility(LinearLayout.VISIBLE);
		}
		
		byte[] bytes;
		try {
			bytes = Base64.decode(principal.copropietario.getFoto());
			imagen = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
			imvCopropietario.setImageBitmap(imagen );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ListView lista = (ListView) findViewById(R.id.ltvNoticias);
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
				//Noticia noticiaElegida = (Noticia) padre.getItemAtPosition(posicion); 

				//CharSequence texto = "Seleccionado: " + noticiaElegida.getDescripcion();
				//Toast toast = Toast.makeText(VentanaMenu.this, texto, Toast.LENGTH_LONG);
				//toast.show();
			}
		});

		
			
		sldNoticias.setOnDrawerOpenListener(new OnDrawerOpenListener() {

			@Override
			public void onDrawerOpened() {
				// TODO Auto-generated method stub
				//tbrBotonesMenu.setVisibility(TableLayout.GONE);
			}
		});

		sldNoticias.setOnDrawerCloseListener(new OnDrawerCloseListener() {

			@Override
			public void onDrawerClosed() {
				// TODO Auto-generated method stub
				//tbrBotonesMenu.setVisibility(TableLayout.VISIBLE);
			}
		});

		imbHistorial.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v) {
        		Intent intent = new Intent( VentanaMenu.this, HistorialReciboActivity.class);
        		Bundle b = new Bundle();
        		b.putString("Criterio", "morosos");
        		b.putFloat("alicuota", alicuota);
        		intent.putExtras(b);
        		startActivity(intent);
        	}
        		
        	} );
		
		detallesMora.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v) {
        		Intent intent = new Intent( VentanaMenu.this, HistorialReciboActivity.class);
        		Bundle b = new Bundle();
        		b.putString("criterio", "historial");
        		b.putFloat("alicuota", alicuota);
        		intent.putExtras(b);
        		startActivity(intent);
        	}
        		
        	} );
		
		imvCopropietario.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				// Start new activity with the LOAD_IMAGE_RESULTS to handle back the results when image is picked from the Image Gallery.
				startActivityForResult(i, LOAD_IMAGE_RESULTS);
			}
		});
	}
	
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// Here we need to check if the activity that was triggers was the Image Gallery.
		// If it is the requestCode will match the LOAD_IMAGE_RESULTS value.
		// If the resultCode is RESULT_OK and there is some data we know that an image was picked.
		if (requestCode == LOAD_IMAGE_RESULTS && resultCode == RESULT_OK && data != null) {
			// Let's read picked image data - its URI
			Uri pickedImage = data.getData();
			// Let's read picked image path using content resolver
			String[] filePath = { MediaStore.Images.Media.DATA };
			Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
			cursor.moveToFirst();
			String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

			// Now we need to set the GUI ImageView data with data read from the picked file.
			imvCopropietario.setImageBitmap(BitmapFactory.decodeFile(imagePath));

			// At the end remember to close the cursor or you will end with the RuntimeException!
			cursor.close();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		Bundle b;

		switch (v.getId()) {
		case R.id.imbCartelera:
			//Toast.makeText(this, "Ha seleccionado la Cartelera", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaMenu.this, VentanaCartelera.class);

			b = new Bundle();
			b.putString("usuario", usuario);
			b.putInt("condominio", idCondominio);

			intent.putExtras(b);
			startActivity(intent);
			break;
		case R.id.imbAreas:
			//Toast.makeText(this, "Ha seleccionado las Areas Comunes", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaMenu.this, VentanaAreaComun.class);
			startActivity(intent);
			break;
		case R.id.imbReclamoSugerencia:
			//Toast.makeText(this, "Ha seleccionado Reclamos o Sugerencias", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaMenu.this, VentanaReclamoSugerencia.class);
			b = new Bundle();
			b.putInt("condominio", idCondominio);
			b.putInt("inmueble", idInmueble);
			intent.putExtras(b);
			startActivity(intent);
			break;
		case R.id.imbProveedores:
			//Toast.makeText(this, "Ha seleccionado Proveedores", Toast.LENGTH_SHORT).show();
			intent = new Intent( VentanaMenu.this, VentanaProveedor.class);
			b = new Bundle();
			b.putInt("condominio", idCondominio);
			intent.putExtras(b);
			startActivity(intent);
			break;
		case R.id.imbNotifficacionPago:
			intent = new Intent( VentanaMenu.this, VentanaNotificaionPago.class);
			b = new Bundle();
			b.putInt("condominio", idCondominio);
			b.putFloat("monto", principal.deudas);
    		b.putFloat("alicuota", alicuota);
			b.putInt("idrazondepagopago", 1);
			intent.putExtras(b);
			startActivity(intent);
			break;
		case R.id.imbHistorialRecibos:
			intent = new Intent( VentanaMenu.this, HistorialReciboActivity.class);
			b = new Bundle();
			b.putInt("condominio", idCondominio);
			b.putInt("inmueble", idInmueble);
			b.putString("usuario", usuario);
			intent.putExtras(b);
			startActivity(intent);
			break;
		}

	}
	
	public boolean verificarenJunta(){
		boolean valor= false;
		datos = new Vector();
		datos.add(idLogin);
		try{
			juntadeCondominios = serJuntadeCondominios.BuscarJuntaCondominio(datos);
			valor=true;
		}catch(NullPointerException  e){
			e.printStackTrace();
		}
		return valor;
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		return super.onOptionsItemSelected(item);
	}
	
}
