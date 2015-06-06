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

import android.util.Log;

import com.modelo.FormaPago;


public class ServicioFormaPago {

	public ServicioFormaPago() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ArrayList<FormaPago> BuscarFormasPago()
	{
		ArrayList<FormaPago> formasPago = new ArrayList<FormaPago>();
		HttpClient httpclient = new DefaultHttpClient(); 

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=9");
		
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

					JSONArray formaPago = json.getJSONArray("formas_pago");
					for(int i=0; i < formaPago.length(); i++){
						FormaPago forma = new FormaPago();
						forma.setId( Integer.parseInt((String) formaPago.getJSONObject(i).get("id")));
						forma.setCodigo( (String) formaPago.getJSONObject(i).get("codigo"));
						forma.setDescripcion( (String) formaPago.getJSONObject(i).get("descripcion"));
						
						
						formasPago.add(forma);
					}
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return formasPago;
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

