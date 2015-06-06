package com.example.android4;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;

import com.example.android4.R;
import com.modelo.AreaComun;
import com.modelo.Banco;
import com.modelo.Condominio;
import com.modelo.Copropietario;
import com.modelo.FormaPago;
import com.modelo.Horario;
import com.modelo.Inmueble;
import com.modelo.Login;
import com.modelo.Noticia;
import com.modelo.Recibo;
import com.modelo.ReclamoSugerencia;
import com.modelo.Regla;
import com.modelo.Rol;
import com.servicio.ServicioAreaComun;
import com.servicio.ServicioBancos;
import com.servicio.ServicioCondominio;
import com.servicio.ServicioCopropietario;
import com.servicio.ServicioDeudaCopropietario;
import com.servicio.ServicioFormaPago;
import com.servicio.ServicioHorarioArea;
import com.servicio.ServicioInmueble;
import com.servicio.ServicioJuntadeCondominios;
import com.servicio.ServicioLogin;
import com.servicio.ServicioNoticia;
import com.servicio.ServicioRecibos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VentanaPrincipal extends Activity implements OnClickListener{

	public static VentanaPrincipal instance = null;
	private EditText edtUsuario;
	private EditText edtClave;
	private Button btnLogin;
	private Context c;
	private ServicioLogin serLogin;
	private ServicioCopropietario serCopropietario;
	private ServicioCondominio serCondominio;
	private ServicioInmueble serInmueble;
	private ServicioNoticia serNoticia;
	private ServicioFormaPago serFormaPago;
	private ServicioAreaComun serAreaComun;
	private ServicioHorarioArea serHorario;
	private ServicioDeudaCopropietario serDeuda;
	private ServicioBancos serBancos;
	private ServicioRecibos serRecibos;
	
	public static Condominio condominio;
	public static Login usuario;
	public static Rol rol;
	public static Copropietario copropietario;
	public static Inmueble inmueble;
	
	public static ArrayList<Horario> horarios;
	public static ArrayList<Inmueble> inmuebles;
	public static ArrayList<AreaComun> areasComunes;
	public static ArrayList<ReclamoSugerencia> reclamos;
	public static ArrayList<Noticia> noticias;
	public static ArrayList<Regla> reglas;
	public static ArrayList<Banco> bancos; 
	public static ArrayList<Recibo> recibos;
	public static ArrayList<Recibo> recibosMora;
	
	public static float deudas;
	private Vector<Object> datos = null;
	

	

	public VentanaPrincipal() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public VentanaPrincipal(Context c) {
		super();
		this.c = c;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		this.serLogin = new ServicioLogin();
		this.serCopropietario = new ServicioCopropietario();
		this.serInmueble = new ServicioInmueble();
		this.serCondominio = new ServicioCondominio();
		this.serNoticia = new ServicioNoticia();
		this.serFormaPago = new ServicioFormaPago();
		this.serAreaComun = new ServicioAreaComun();
		this.serHorario = new ServicioHorarioArea();
		this.serDeuda = new ServicioDeudaCopropietario();
		this.serBancos = new ServicioBancos();
		this.serRecibos = new ServicioRecibos();
		
		this.horarios = new ArrayList<Horario>();
		this.condominio = new Condominio();
		this.inmuebles = new ArrayList<Inmueble>();
		this.reclamos = new ArrayList<ReclamoSugerencia>();
		this.noticias = new ArrayList<Noticia>();
		this.reglas = new ArrayList<Regla>();
		this.areasComunes = new ArrayList<AreaComun>();
		this.bancos = new ArrayList<Banco>();
		this.recibos = new ArrayList<Recibo>();
		this.recibosMora = new ArrayList<Recibo>();
		
		//this.deudas = new ArrayList<Float>();
		edtUsuario = (EditText) findViewById(R.id.edtUsuario);
		edtClave = (EditText) findViewById(R.id.edtClave);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		
		btnLogin.setOnClickListener(this);
		
		
	}
	
	@Override
	public void onClick(View v) {
		Intent intent;
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnLogin:

			datos = new Vector();
			datos.add((String)edtUsuario.getText().toString());
			datos.add((String)edtClave.getText().toString());
			boolean exito = serLogin.ValidarUsuario(datos); 
			if(exito){
				
				datos.clear();
				datos.add((String)edtUsuario.getText().toString());
				datos.add((String)edtClave.getText().toString());
				usuario = serLogin.BuscarUsuario(datos);
				
				datos.clear();
				datos.add(usuario.getIdRol());
				rol = serLogin.ValidarRol(datos);
				
				datos.clear();
				datos.add(String.valueOf(usuario.getId()));
				inmueble = serInmueble.BuscarInmueble(datos);
				
				datos.clear();
				datos.add(inmueble.getIdCopropietario());
				copropietario = serCopropietario.BuscarCopropietario(datos);
				
				datos.clear();
				datos.add(inmueble.getIdCondominio());
				condominio = serCondominio.BuscarCondominio(datos);
				
				
				datos.clear();
				datos.add(condominio.getId());
				noticias = serNoticia.BuscarNoticiasCondominio(datos);
				
				datos.clear();
				datos.add(inmueble.getId());
				deudas = serDeuda.TotalDeudaxInmueble(datos);
				
				datos.clear();
				datos.add(condominio.getId());
				areasComunes = serAreaComun.BuscarAreasComunes(datos);
				//Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show();
				
				datos.clear();
				datos.add(inmueble.getIdCondominio());
				recibos= serRecibos.BuscarRecibosInmuelbes(datos);
				
				datos.clear();
				datos.add(inmueble.getIdCondominio());
				recibosMora= serRecibos.BuscarRecibosMorososxInmueble(datos);
				
				Bundle bund = new Bundle();
				bund.putString("Rol", rol.getCodRol());
				bund.putInt("idLogin", usuario.getId());
				intent = new Intent(VentanaPrincipal.this, VentanaMenu.class);
				this.limpiar();
				intent.putExtras(bund);
				startActivity(intent);
				
				
			}else{
				this.limpiar();
				Toast.makeText(this, "Usuario o clave invalidos", Toast.LENGTH_LONG).show();
			}
			
			break;
		case R.id.btnRegistrarLogin:
			intent = new Intent(VentanaPrincipal.this, VentanaRegistrarNuevoUsuario.class);
			startActivity(intent);
			break;
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static VentanaPrincipal getInstance(Context c){
		if(instance == null){
			instance = new VentanaPrincipal(c);}
		
		return instance;
	}

	public void limpiar(){
		edtUsuario.setText("");
		edtClave.setText("");
	}

	public static Condominio getCondominio() {
		return condominio;
	}

	public static void setCondominio(Condominio condominio) {
		VentanaPrincipal.condominio = condominio;
	}
	
}
