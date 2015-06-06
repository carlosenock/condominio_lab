package com.servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.modelo.Inmueble;

public class ServicioInmueble {

	public ServicioInmueble() {
		// TODO Auto-generated constructor stub
	}
	
	public Inmueble BuscarInmueble(java.util.Vector<Object> datos)
	{
		Inmueble inmuebleEncontrado = new Inmueble();
		HttpClient httpclient = new DefaultHttpClient();
		String idLogin = (String) datos.elementAt(0);

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=5&idlogin="+idLogin);
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

					inmuebleEncontrado.setId(Integer.parseInt((String) json.get("id")));
					inmuebleEncontrado.setCodigo( (String) json.get("codigo"));
					inmuebleEncontrado.setMetrosCuadrados( (String) json.get("metros"));
					inmuebleEncontrado.setAlicuota(Float.parseFloat((String) json.get("alicuota")));
					inmuebleEncontrado.setDescripcion((String) json.get("descripcion"));
					inmuebleEncontrado.setCodigoCatastral((String) json.get("codigo_catastral"));
					inmuebleEncontrado.setIdCopropietario((String) json.get("id_copropietario"));
					inmuebleEncontrado.setIdCondominio((String) json.get("id_condominio"));
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return inmuebleEncontrado;
	}
	
	public Inmueble BuscarInmueblePorId(int idInmueble)
	{
		Inmueble inmuebleEncontrado = new Inmueble();
		HttpClient httpclient = new DefaultHttpClient();

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=18&idinmueble="+idInmueble);
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

					inmuebleEncontrado.setId(Integer.parseInt((String) json.get("id")));
					inmuebleEncontrado.setCodigo( (String) json.get("codigo"));
					inmuebleEncontrado.setMetrosCuadrados( (String) json.get("metros"));
					inmuebleEncontrado.setAlicuota(Float.parseFloat((String) json.get("alicuota")));
					inmuebleEncontrado.setDescripcion((String) json.get("descripcion"));
					inmuebleEncontrado.setCodigoCatastral((String) json.get("codigo_catastral"));
					inmuebleEncontrado.setIdCopropietario((String) json.get("id_copropietario"));
					inmuebleEncontrado.setIdCondominio((String) json.get("id_condominio"));
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return inmuebleEncontrado;
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
