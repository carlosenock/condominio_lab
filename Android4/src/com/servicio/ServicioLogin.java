package com.servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.modelo.Login;
import com.modelo.Rol;

import android.util.Log;

public class ServicioLogin {

	public ServicioLogin() {
		// TODO Auto-generated constructor stub
	}
	
	public Login BuscarUsuario(java.util.Vector<Object> datos)
    {
 	   Login loginEncontrado = new Login();
 	   HttpClient httpclient = new DefaultHttpClient();
 	   String usuario = (String) datos.elementAt(0);
 	   String clave = (String) datos.elementAt(1);
 	   
        // Preparar un objeto request via method Get
        HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=1&usuario="+usuario+"&clave="+clave);
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

        			loginEncontrado.setUsuario((String) json.get("usuario"));
        			loginEncontrado.setClave((String) json.get("clave"));
        			loginEncontrado.setFechaRegistro(convertirFecha((String) json.get("fecha_registro")));
        			loginEncontrado.setIdRol(Integer.parseInt((String) json.get("id_rol")));
        			loginEncontrado.setId(Integer.parseInt((String) json.get("login")));
        		}catch(JSONException  e){
        			e.printStackTrace();
        		}

        	}

        }
        catch (Exception e) {
     	   e.printStackTrace();  
        }
 	   return loginEncontrado;
    }
	
	public Date convertirFecha(String fecha){
		Calendar calendario = Calendar.getInstance();
		Calendar calendarioActual = Calendar.getInstance();
		String fechaFraccionada[] = fecha.split("-");
		
		Log.v("fecha", fechaFraccionada[0]);
		calendario.add(Calendar.YEAR, Integer.valueOf(fechaFraccionada[0]) - calendarioActual.get(Calendar.YEAR));
		Log.v("fecha", fechaFraccionada[1]);
		calendario.add(Calendar.MONTH, Integer.valueOf(fechaFraccionada[1])-2);
		Log.v("fecha", fechaFraccionada[2]);
		calendario.add(Calendar.DAY_OF_MONTH, Integer.valueOf(fechaFraccionada[2]) - calendarioActual.get(Calendar.DAY_OF_MONTH));
		Log.v("ANNO CALENDARIO",""+ (calendario.get(Calendar.YEAR)));
		Log.v("MES CALENDARIO",""+ calendario.get(Calendar.MONTH));
		Log.v("DIA CALENDARIO",""+ (calendario.get(Calendar.DAY_OF_MONTH)));
		
		return calendario.getTime();
	}
	
	public boolean ValidarUsuario(java.util.Vector<Object> datos)
    {
 	   boolean exito = false;
 	   HttpClient httpclient = new DefaultHttpClient();
 	   String usuario = (String) datos.elementAt(0);
 	   String clave = (String) datos.elementAt(1);
 	   
        // Preparar un objeto request via method Get
 	   try{
        HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=1&usuario="+usuario+"&clave="+clave);
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
    			datos.add((String)json.get("exito"));
    		}catch(JSONException  e){
    			e.printStackTrace();
    		}

    		// Se cierra la entrada del stream y se libera la conexion
    		instream.close();

    		if(Integer.parseInt((String) datos.get(0)) == 1){
    			exito = true;
    		}
    	}
 	   }catch(Exception e){
 		   e.printStackTrace();
 	   }
        
 	   return exito;
    }
	
	public Rol ValidarRol(java.util.Vector<Object> datos)
    {
 	   Rol rolUsuario = new Rol();
 	   HttpClient httpclient = new DefaultHttpClient();
 	   int idrol = (Integer) datos.elementAt(0);
 	   
        // Preparar un objeto request via method Get
        HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=32&idrol="+idrol);
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

        			rolUsuario.setIdRol(Integer.parseInt((String) json.get("id_rol")));
        			rolUsuario.setCodRol((String) json.get("codigo_rol"));
        			rolUsuario.setNomRol((String) json.get("nombre_rol"));
        		}catch(JSONException  e){
        			e.printStackTrace();
        		}

        	}

        }
        catch (Exception e) {
     	   e.printStackTrace();  
        }
 	   return rolUsuario;
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