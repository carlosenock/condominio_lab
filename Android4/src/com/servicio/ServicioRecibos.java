package com.servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.modelo.Multa;
import com.modelo.Recibo;

public class ServicioRecibos {
	
	public ArrayList<Recibo> BuscarRecibosInmuelbes(Vector<Object> datos){
		//obtiene los recibos de un inmueble
		ArrayList<Recibo> recibos = new ArrayList<Recibo>();
		
		HttpClient httpclient = new DefaultHttpClient();
		
		String idInmueble = (String) datos.elementAt(0);

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=14&idinmueble="+idInmueble);
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

					JSONArray listaRecibo = json.getJSONArray("recibos");
					
					for(int i=0; i < listaRecibo.length(); i++){
						
						Recibo recibo = new Recibo();
						recibo.setIdrecibocobro(Integer.valueOf((String) listaRecibo.getJSONObject(i).get("id")));
						recibo.setCodigorecibocobro((String) listaRecibo.getJSONObject(i).get("codigo"));
						recibo.setFecharecibocobro( convertirFecha((String) listaRecibo.getJSONObject(i).get("fecha_recibo")));
						recibo.setCuotapendienterecibo( Float.valueOf((String) listaRecibo.getJSONObject(i).get("cuota_pendiente")));
						recibo.setFechavencimientorecibo( convertirFecha((String) listaRecibo.getJSONObject(i).get("fecha_vencimiento")));
						recibo.setAbonorecibocobro( Float.valueOf((String) listaRecibo.getJSONObject(i).get("abono")));
						recibo.setMontorecibocobro( Float.valueOf((String)listaRecibo.getJSONObject(i).get("monto")));
						recibo.setEstatuscancelacionrecibo( (String) listaRecibo.getJSONObject(i).get("estatus_cancelacion"));
						recibo.setEstatusrecibocobro( (String) listaRecibo.getJSONObject(i).get("estatus"));
						recibo.setIdinmueblerecibocobro( Integer.valueOf((String) listaRecibo.getJSONObject(i).get("id_inmueble")));
						
						recibos.add(recibo);
					}
				}catch(JSONException  e){
					e.printStackTrace();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return recibos;
	}
	
	
	public ArrayList<Recibo> BuscarRecibosMorososxInmueble(Vector<Object> datos){
ArrayList<Recibo> recibos = new ArrayList<Recibo>();
		
		HttpClient httpclient = new DefaultHttpClient();
		
		String idInmueble = (String) datos.elementAt(0);

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=40&idinmueble="+idInmueble);
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

					JSONArray listaRecibo = json.getJSONArray("recibos");
					
					for(int i=0; i < listaRecibo.length(); i++){
						
						Recibo recibo = new Recibo();
						recibo.setIdrecibocobro(Integer.valueOf((String) listaRecibo.getJSONObject(i).get("id")));
						recibo.setCodigorecibocobro((String) listaRecibo.getJSONObject(i).get("codigo"));
						recibo.setFecharecibocobro( convertirFecha((String) listaRecibo.getJSONObject(i).get("fecha_recibo")));
						recibo.setCuotapendienterecibo( Float.valueOf((String) listaRecibo.getJSONObject(i).get("cuota_pendiente")));
						recibo.setFechavencimientorecibo( convertirFecha((String) listaRecibo.getJSONObject(i).get("fecha_vencimiento")));
						recibo.setAbonorecibocobro( Float.valueOf((String) listaRecibo.getJSONObject(i).get("abono")));
						recibo.setMontorecibocobro( Float.valueOf((String)listaRecibo.getJSONObject(i).get("monto")));
						recibo.setEstatuscancelacionrecibo( (String) listaRecibo.getJSONObject(i).get("estatus_cancelacion"));
						recibo.setEstatusrecibocobro( (String) listaRecibo.getJSONObject(i).get("estatus"));
						recibo.setIdinmueblerecibocobro( Integer.valueOf((String) listaRecibo.getJSONObject(i).get("id_inmueble")));
						
						recibos.add(recibo);
					}
				}catch(JSONException  e){
					e.printStackTrace();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return recibos;
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
