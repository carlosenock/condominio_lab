package com.servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ServicioPagos {

	public boolean grabarPago(java.util.Vector<Object> datos){

		HttpClient httpclient = new DefaultHttpClient();
	 	
	 	String codigo = (String) datos.elementAt(0);
	 	String descripcion = (String) datos.elementAt(1);
	 	String numDoc = (String) datos.elementAt(2);
	 	float montopago = (Float) datos.elementAt(3);
	 	int validacion = (Integer)(datos.elementAt(4));
	 	String fechapago = (String) datos.elementAt(5);
	 	String estatus = (String) datos.elementAt(6);
	 	int idformapago = (Integer) datos.elementAt(7);
	 	int idrazondepago = (Integer) datos.elementAt(8);
	 	//int idreservacion = (Integer) datos.elementAt(9);
	 	//int idrecibocobro = (Integer) datos.elementAt(10);
	 	int idcondominio = (Integer) datos.elementAt(11);
	 	String cedula = (String)datos.elementAt(12);
	 	int idBanco = (Integer)datos.elementAt(13);
		String idreservacion=null;
		String idrecibocobro=null;
	 	boolean exito= false;
		String urlInsertar = null;
		
	 	
	 	try {
			urlInsertar = URIUtil.encodeQuery("http://condominioucla.webcindario.com/despachador.php?servicio=30&codigoP="+codigo+
					"&observacionesP="+descripcion+"&referenciaP="+numDoc+"&montoP="+montopago+"&validacionP="+validacion+
					"&fechaP="+fechapago+"&estatusP="+estatus+"&formaP="+idformapago+"&idrazondepago="+idrazondepago+
					"&idreservacion="+idreservacion+"&idrecibocobroP="+idrecibocobro+"&idcondominio="+idcondominio+"&cidepositante="+cedula+"&idbanco="+idBanco);
		} catch (URIException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 	
	 	// Preparar un objeto request via method Get
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
        		Log.v("JSON PAGO", resultado);
        		// Un objeto JSONObject se crea
        		try{
        			JSONObject json = new JSONObject(resultado);

        			datos.clear();
        			datos.add(json.get("exito"));
        			Log.v("final del pago", (String) datos.get(0));
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
	
	public int contarPagoxCondominio(java.util.Vector<Object> datos){
		int valor=0;
		
		HttpClient httpclient = new DefaultHttpClient();
		int idCondominio = (Integer)(datos.elementAt(0));
	 	
		String urlInsertar = null;

	 	
	 	try {
			urlInsertar = URIUtil.encodeQuery("http://condominioucla.webcindario.com/despachador.php?servicio=23&idcondominio="+idCondominio);
		} catch (URIException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 	
	 	// Preparar un objeto request via method Get
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

        			datos.clear();
        			datos.add(json.get("cantidad"));
        			
        		}catch(JSONException  e){
        			e.printStackTrace();
        		}

        		// Se cierra la entrada del stream y se libera la conexion
        		instream.close();
        	}
     	   }catch(Exception e){
     		   e.printStackTrace();
     	   }
     	   String parse= (String)datos.elementAt(0);
           valor=Integer.parseInt(parse);
     	   return valor;
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
