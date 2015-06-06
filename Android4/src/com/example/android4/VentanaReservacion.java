package com.example.android4;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.modelo.AreaComun;
import com.servicio.ServicioAreaComun;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class VentanaReservacion extends Activity implements OnClickListener{

	private TextView txvNombreArea;
	private EditText edtFechaInicial;
	private EditText edtFechaFinal;
	private Button btnAceptar;
	private Button btnLimpiar;
	private String fechaInicial;
	private String fechaFinal;
	private String nombreArea;
	private int idArea;
	private int diaInicio;
	private int mesInicio;
	private int annoInicio;
	private int diaFinal;
	private int mesFinal;
	private int annoFinal;
	private Calendar calendario;
	private VentanaPrincipal principal;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ventana_reservacion);
		

		principal = VentanaPrincipal.getInstance(this);
		calendario = Calendar.getInstance();
		Bundle bundle = this.getIntent().getExtras();
		idArea = bundle.getInt("id");
		nombreArea = bundle.getString("nombre");

		txvNombreArea = (TextView) findViewById(R.id.txvAreaReservacion);
		txvNombreArea.setText("Reservar area: " + nombreArea);

		edtFechaInicial = (EditText) findViewById(R.id.edtFechaInicial);
		edtFechaFinal = (EditText) findViewById(R.id.edtFechaFinal);
		btnAceptar = (Button) findViewById(R.id.btnAceptarReservacion);
		btnLimpiar = (Button) findViewById(R.id.btnLimpiarReservacion);

		diaInicio = calendario.get(Calendar.DAY_OF_MONTH);
		diaFinal = calendario.get(Calendar.DAY_OF_MONTH);
		mesInicio = calendario.get(Calendar.MONTH);
		mesFinal = calendario.get(Calendar.MONTH);
		annoInicio = calendario.get(Calendar.YEAR);
		annoFinal = calendario.get(Calendar.YEAR);

		btnAceptar.setOnClickListener(this);
		btnLimpiar.setOnClickListener(this);
		edtFechaInicial.setOnClickListener(this);
		edtFechaFinal.setOnClickListener(this);


	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Log.v("ID Dialog", "Dialog datepikcer: " + id);
		
		switch(id){
		case 0:
			return new DatePickerDialog(this, dtpInicio, annoInicio, mesInicio, diaInicio); 
		case 1:
			return new DatePickerDialog(this, dtpFinal, annoFinal, mesFinal, diaFinal);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener dtpInicio = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {

				edtFechaInicial.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
						+ selectedYear);
				diaInicio = selectedDay;
				mesInicio = (selectedMonth + 1);
				annoInicio = selectedYear;
				
		}
	};
	
	private DatePickerDialog.OnDateSetListener dtpFinal = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {

				edtFechaFinal.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
						+ selectedYear);
				diaFinal = selectedDay;
				mesFinal = selectedMonth + 1;
				annoFinal = selectedYear;
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent;
		Bundle b = new Bundle();

		switch (v.getId()) {
		case R.id.edtFechaInicial:
			showDialog(0);
			break;
		case R.id.edtFechaFinal:
			showDialog(1);
			break;
		case R.id.btnAceptarReservacion:

			intent = new Intent( VentanaReservacion.this, VentanaReservar.class);
			b.putString("area", nombreArea);
			b.putInt("idArea", idArea);
			b.putString("inicio", edtFechaInicial.getText().toString());
			b.putString("fin", edtFechaFinal.getText().toString());
			intent.putExtras(b);
			startActivity(intent);
			break;
		case R.id.btnLimpiarReservacion:
			this.limpiar();
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		long ini = 0;
		long fin = 0;
		Calendar fecha = Calendar.getInstance();
		fecha.set(GregorianCalendar.DAY_OF_MONTH, 1);

		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {

				Bundle bundle = data.getExtras();

				fechaInicial = bundle.getString("fecha_inicial");
				ini = bundle.getLong("fec_ini");
				edtFechaInicial.setText(fechaInicial);
				fechaFinal = bundle.getString("fecha_final");
				fin = bundle.getLong("fec_fin");
				edtFechaFinal.setText(fechaFinal);

			}
		}

	}

	public void limpiar(){
		edtFechaFinal.setText("");
		edtFechaInicial.setText("");
	}

}
