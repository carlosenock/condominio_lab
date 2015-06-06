package com.servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.modelo.Multa;

public class ServicioDetalleRecibo {

	public ServicioDetalleRecibo() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Multa> BuscarMultas(java.util.Vector<Object> datos)
	{
		ArrayList<Multa> multas = new ArrayList<Multa>();
		HttpClient httpclient = new DefaultHttpClient();
		int idInmueble = (Integer) datos.elementAt(0);
		String fecha = (String) datos.elementAt(1);

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=13&idinmueble="+idInmueble+"&fecharecibo="+fecha);
		HttpResponse response;
		try {
			// Ejecutar el request
			response = httpclient.execute(httpget);

			// Obtener la entidad del response
			HttpEntity entity = response.getEntity();

			// Si el response no esta encerrado como una entity, no hay necesidad de preocuparse, liberar la conexion
			if (entity != null) {
				// el JSON Response es leido
				InputStream instream = entity.getContent();
				String resultado = convertStreamToString(instream);

				// Un objeto JSONObject se crea
				try{
					JSONObject json = new JSONObject(resultado);

					JSONArray multasInmueble = json.getJSONArray("multas");
					for(int i=0; i < multasInmueble.length(); i++){
						Multa multa = new Multa();
						multa.setId( Integer.parseInt((String) multasInmueble.getJSONObject(i).get("id")));
						multa.setDescripcion( (String) multasInmueble.getJSONObject(i).get("codigo"));
						multa.setDescripcion( (String) multasInmueble.getJSONObject(i).get("descripcion"));
						multa.setMonto( (Float) multasInmueble.getJSONObject(i).get("monto"));
						multa.setFecha( convertirFecha((String) multasInmueble.getJSONObject(i).get("fecha")));
						multa.setTipoMulta( Integer.parseInt((String) multasInmueble.getJSONObject(i).get("id_tipo")));
						multa.setIdInmueble( Integer.parseInt((String)multasInmueble.getJSONObject(i).get("id_inmueble")));
						multa.setIdRecibo( Integer.parseInt((String) multasInmueble.getJSONObject(i).get("id_recibo")));
						
						multas.add(multa);
					}
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return multas;
	}
	
	public ArrayList<Multa> BuscarMultasxRecibo(java.util.Vector<Object> datos)
	{
		ArrayList<Multa> multas = new ArrayList<Multa>();
		HttpClient httpclient = new DefaultHttpClient();
		String idInmueble = (String) datos.elementAt(0);
		String idRecibo = (String) datos.elementAt(1);

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=37&idinmueble="+idInmueble+"&fecharecibo="+idRecibo);
		HttpResponse response;
		try {
			// Ejecutar el request
			response = httpclient.execute(httpget);

			// Obtener la entidad del response
			HttpEntity entity = response.getEntity();

			// Si el response no esta encerrado como una entity, no hay necesidad de preocuparse, liberar la conexion
			if (entity != null) {
				// el JSON Response es leido
				InputStream instream = entity.getContent();
				String resultado = convertStreamToString(instream);

				// Un objeto JSONObject se crea
				try{
					JSONObject json = new JSONObject(resultado);

					JSONArray multasInmueble = json.getJSONArray("multas");
					for(int i=0; i < multasInmueble.length(); i++){
						Multa multa = new Multa();
						multa.setId( Integer.parseInt((String) multasInmueble.getJSONObject(i).get("id")));
						multa.setDescripcion( (String) multasInmueble.getJSONObject(i).get("codigo"));
						multa.setDescripcion( (String) multasInmueble.getJSONObject(i).get("descripcion"));
						multa.setMonto( (Float) multasInmueble.getJSONObject(i).get("monto"));
						multa.setFecha( convertirFecha((String) multasInmueble.getJSONObject(i).get("fecha")));
						multa.setTipoMulta( Integer.parseInt((String) multasInmueble.getJSONObject(i).get("id_tipo")));
						multa.setIdInmueble( Integer.parseInt((String)multasInmueble.getJSONObject(i).get("id_inmueble")));
						multa.setIdRecibo( Integer.parseInt((String) multasInmueble.getJSONObject(i).get("id_recibo")));
						
						multas.add(multa);
					}
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return multas;
	}
	
	public HashMap<String, ArrayList<String>> BuscarEgresosComunes(java.util.Vector<Object> datos)
	{
		HashMap<String, ArrayList<String>> egresosComunes = new HashMap<String, ArrayList<String>>();
		HttpClient httpclient = new DefaultHttpClient();
		int idInmueble = (Integer) datos.elementAt(0);
		String fecha = (String) datos.elementAt(1);

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=11&idinmueble="+idInmueble+"&fecharecibo="+fecha);
		HttpResponse response;
		try {
			// Ejecutar el request
			response = httpclient.execute(httpget);

			// Obtener la entidad del response
			HttpEntity entity = response.getEntity();

			// Si el response no esta encerrado como una entity, no hay necesidad de preocuparse, liberar la conexion
			if (entity != null) {
				// el JSON Response es leido
				InputStream instream = entity.getContent();
				String resultado = convertStreamToString(instream);

				// Un objeto JSONObject se crea
				try{
					JSONObject json = new JSONObject(resultado);

					JSONArray egresosInmueble = json.getJSONArray("egresos");
					for(int i=0; i < egresosInmueble.length(); i++){
						ArrayList<String> dato = new ArrayList<String>();
						dato.add((String) egresosInmueble.getJSONObject(i).get("descripcion"));
						dato.add((String) egresosInmueble.getJSONObject(i).get("monto"));
						
						egresosComunes.put("Egreso"+i, dato);
					}
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return egresosComunes;
	}
	
	public HashMap<String, ArrayList<String>> BuscarEgresosNoComunes(java.util.Vector<Object> datos)
	{
		HashMap<String, ArrayList<String>> egresosNoComunes = new HashMap<String, ArrayList<String>>();
		HttpClient httpclient = new DefaultHttpClient();
		int idInmueble = (Integer) datos.elementAt(0);
		String fecha = (String) datos.elementAt(1);

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=12&idinmueble="+idInmueble+"&fecharecibo="+fecha);
		HttpResponse response;
		try {
			// Ejecutar el request
			response = httpclient.execute(httpget);

			// Obtener la entidad del response
			HttpEntity entity = response.getEntity();

			// Si el response no esta encerrado como una entity, no hay necesidad de preocuparse, liberar la conexion
			if (entity != null) {
				// el JSON Response es leido
				InputStream instream = entity.getContent();
				String resultado = convertStreamToString(instream);

				// Un objeto JSONObject se crea
				try{
					JSONObject json = new JSONObject(resultado);

					JSONArray egresosNoComunesInmueble = json.getJSONArray("egresos_no_comunes");
					for(int i=0; i < egresosNoComunesInmueble.length(); i++){
						ArrayList<String> dato = new ArrayList<String>();
						dato.add((String) egresosNoComunesInmueble.getJSONObject(i).get("descripcion"));
						dato.add((String) egresosNoComunesInmueble.getJSONObject(i).get("monto"));
						
						egresosNoComunes.put("EgresoNoComun"+i, dato);
					}
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return egresosNoComunes;
	}
	
	public Date convertirFecha(String fecha){
		Calendar calendario = Calendar.getInstance();
		Calendar calendarioActual = Calendar.getInstance();
		String fechaFraccionada[] = fecha.split("-");
		
		calendario.add(Calendar.YEAR, Integer.valueOf(fechaFraccionada[0]) - calendarioActual.get(Calendar.YEAR));
		calendario.add(Calendar.MONTH, Integer.valueOf(fechaFraccionada[1])-2);
		calendario.add(Calendar.DAY_OF_MONTH, Integer.valueOf(fechaFraccionada[2]) - calendarioActual.get(Calendar.DAY_OF_MONTH));
		
		return calendario.getTime();
	}
	
	private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
 
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
     	   e.getMessage();  
        } finally {
            try {
                is.close();
            } catch (IOException e) {
         	   e.getMessage();  
            }
        }
        return sb.toString();
    }    

}
