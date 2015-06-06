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

import android.util.Log;

import com.modelo.Condominio;

public class ServicioCondominio {

	public ServicioCondominio() {
		// TODO Auto-generated constructor stub
	}
	
	public Condominio BuscarCondominio(java.util.Vector<Object> datos)
	{
		Condominio condominioEncontrado = new Condominio();
		HttpClient httpclient = new DefaultHttpClient();
		String idcondominio = (String) datos.elementAt(0);

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=4&idcondominio="+idcondominio);
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

					condominioEncontrado.setId(Integer.parseInt((String) json.get("id")));
					condominioEncontrado.setRif((String) json.get("rif"));
					condominioEncontrado.setNombre((String) json.get("nombre"));
					condominioEncontrado.setDireccion((String) json.get("direccion"));
					condominioEncontrado.setTipo(Integer.parseInt((String) json.get("tipo")));
					condominioEncontrado.setIdCiudad(Integer.parseInt((String) json.get("id_ciudad")));
					condominioEncontrado.setInteresMora(Float.parseFloat((String) json.get("interes_mora")));
					condominioEncontrado.setTiempoMora((String) json.get("tiempo_mora"));
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return condominioEncontrado;
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
