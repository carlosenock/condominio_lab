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

public class ServicioDeudaCopropietario {
	
	public float TotalDeudaxInmueble(java.util.Vector<Object> datos)
    {
 	   boolean exito = false;
 	   HttpClient httpclient = new DefaultHttpClient();
 	   int idinmueble = (Integer) datos.elementAt(0);
 	   
        // Preparar un objeto request via method Get
 	   try{
        HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=24&idinmueble="+idinmueble);
        HttpResponse response;
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

    			datos.clear();
    			datos.add(json.get("total_deuda"));
    			
    		}catch(JSONException  e){
    			e.printStackTrace();
    		}

    		// Se cierra la entrada del stream y se libera la conexion
    		instream.close();

    	}
 	   }catch(Exception e){
 		   e.printStackTrace();
 	   }
 	   String valor= (String)datos.elementAt(0);
       float floatvalor=Float.parseFloat(valor);
 	   return floatvalor;
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
