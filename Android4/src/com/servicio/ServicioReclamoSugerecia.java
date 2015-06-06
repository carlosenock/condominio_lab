package com.servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.modelo.ReclamoSugerencia;

public class ServicioReclamoSugerecia {
	
	public ServicioReclamoSugerecia() {
		// TODO Auto-generated constructor stub
	}
	
	public ReclamoSugerencia InsertarReclamo(ReclamoSugerencia reclamoNuevo) throws URIException
	{
		HttpClient httpclient = new DefaultHttpClient();

		// Preparar un objeto request via method Get
		String urlInsertar = URIUtil.encodeQuery("http://condominioucla.webcindario.com/despachador.php?servicio=19&codigo="+reclamoNuevo.getCodigo()+"&razon="+reclamoNuevo.getRazon()+"&fecha="+invertirFecha(reclamoNuevo.getFecha())+"&descripcion="+reclamoNuevo.getDescripcion()+"&estatus=A&idinmueble="+reclamoNuevo.getIdInmueble()+"&codigoinmueble="+reclamoNuevo.getCodigoInmueble()+"");
		HttpGet httpget = new HttpGet(urlInsertar);
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

					reclamoNuevo.setCodigo( (String) json.get("codigo"));
					reclamoNuevo.setRazon( (String) json.get("razon"));
					reclamoNuevo.setFecha( convertirFecha((String) json.get("fecha")));
					reclamoNuevo.setDescripcion( (String) json.get("descripcion"));
					reclamoNuevo.setEstatus((String) json.get("estatus"));
					reclamoNuevo.setIdInmueble( Integer.parseInt((String) json.get("id_inmueble")));
					reclamoNuevo.setCodigoInmueble( (String) json.get("codigo_inmueble"));
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return reclamoNuevo;
	}
	
	public int ContarReclamosxCondominio(int idcondominio)
	{
		int cantidadReclamos = 0;
		HttpClient httpclient = new DefaultHttpClient();

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=21&idcondominio="+idcondominio+"");
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

					cantidadReclamos = Integer.parseInt( (String) json.get("cantidad"));
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return cantidadReclamos;
	}
	
	public String invertirFecha(Date fecha){
		Calendar fechaCalendar = Calendar.getInstance();
		fechaCalendar.setTime(fecha);
		return  fechaCalendar.get(Calendar.YEAR) + "-"+ (fechaCalendar.get(Calendar.MONTH)+1) + "-"+fechaCalendar.get(Calendar.DAY_OF_MONTH);
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
        /*
         * Para convertir un InputStream a String se usa el metodo BufferedReader.readLine()
         * Iteramos hasta que el BufferedReader retone null lo cual significa
         * que no hay mas datos para leer. Cada linea sera agregada al StringBuilder
         * y sera retornado como un String.
         */
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
