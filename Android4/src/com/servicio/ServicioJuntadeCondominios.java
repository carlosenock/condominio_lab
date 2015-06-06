package com.servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.modelo.Banco;
import com.modelo.JuntadeCondominios;

public class ServicioJuntadeCondominios {

	
	public JuntadeCondominios BuscarJuntaCondominio(java.util.Vector<Object> datos){
		
		JuntadeCondominios juntas = new JuntadeCondominios();
		HttpClient httpclient = new DefaultHttpClient(); 

		int idCopropietario = (Integer)datos.elementAt(0);

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=33&idlogin="+idCopropietario);
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
						juntas.setIdLoginJuntaCondominio(Integer.parseInt((String)json.get("id_login")));
						juntas.setCodigoJuntacondominio((String) json.get("codigo"));
						juntas.setFechainicio(convertirFecha((String)json.get("fecha_inicio")));
						juntas.setFechaculminacion(convertirFecha((String)json.get("fecha_culminacion")));
						juntas.setEstatusvigenciacargo( (String) json.get("estatus_vigencia_cargo"));
						juntas.setEstatusjuntacondominio( (String) json.get("estatus"));
						juntas.setIdCargoJuntaCondominio(Integer.parseInt((String)json.get("id_cargo")));
						juntas.setIdCondominioJuntaCondominio(Integer.parseInt((String)json.get("id_condominio")));
						juntas.setIdCondominio(Integer.parseInt((String)json.get("id_copropietario")));

						Log.v("IdLogin", (Integer.parseInt((String)json.get("id_login")))+"");
				}catch(JSONException  e){
					e.printStackTrace();
					
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return juntas;
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
