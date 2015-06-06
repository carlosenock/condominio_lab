package com.servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.modelo.AreaComun;

public class ServicioAreaComun {

	public ServicioAreaComun() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<AreaComun> BuscarAreasComunes(java.util.Vector<Object> datos)
	{
		ArrayList<AreaComun> areas = new ArrayList<AreaComun>();
		HttpClient httpclient = new DefaultHttpClient();
		int idCondominio = (Integer) datos.elementAt(0);

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=15&idcondominio="+idCondominio);
		HttpResponse response; //condominioucla.webcindario.com
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

					JSONArray areasComunes = json.getJSONArray("areas");
					for(int i=0; i < areasComunes.length(); i++){
						AreaComun area = new AreaComun();
						area.setId( Integer.parseInt((String) areasComunes.getJSONObject(i).get("id")));
						area.setCodigo( (String) areasComunes.getJSONObject(i).get("codigo"));
						area.setDescripcion( (String) areasComunes.getJSONObject(i).get("descripcion"));
						area.setMonto(Float.parseFloat((String)areasComunes.getJSONObject(i).get("monto")));
						area.setEstatus( (String) areasComunes.getJSONObject(i).get("estatus"));
						area.setCapacidad( Integer.parseInt((String) areasComunes.getJSONObject(i).get("capacidad")));
						area.setIdCondominio( Integer.parseInt((String) areasComunes.getJSONObject(i).get("id_condominio")));
						area.setNombre( (String) areasComunes.getJSONObject(i).get("nombre"));
						
						areas.add(area);
					}
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return areas;
	}
	
	public AreaComun BuscarAreaComun(int idArea)
	{
		AreaComun areaEncontrada = new AreaComun();
		HttpClient httpclient = new DefaultHttpClient();

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=17&idareacomun="+idArea);
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

					areaEncontrada.setId(Integer.parseInt((String) json.get("id")));
					areaEncontrada.setCodigo( (String) json.get("codigo"));
					areaEncontrada.setDescripcion( (String) json.get("descripcion"));
					areaEncontrada.setMonto(Float.parseFloat((String) json.get("monto")));
					areaEncontrada.setEstatus( (String) json.get("estatus"));
					areaEncontrada.setCapacidad(Integer.parseInt((String) json.get("capacidad")));
					areaEncontrada.setIdCondominio(Integer.parseInt((String) json.get("id_condominio")));
					areaEncontrada.setNombre((String) json.get("nombre"));
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return areaEncontrada;
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
