package com.servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.modelo.Proveedor;

public class ServicioProveedor {
	
	public ServicioProveedor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Proveedor BuscarProveedor(java.util.Vector<Object> datos)
	{
		Proveedor proveedorEncontrado = new Proveedor();
		HttpClient httpclient = new DefaultHttpClient();
		String idProveedor = (String) datos.elementAt(0);

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=6&idproveedor="+idProveedor);
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

					proveedorEncontrado.setId( Integer.parseInt((String) json.get("id")));
					proveedorEncontrado.setRif( (String) json.get("rif"));
					proveedorEncontrado.setRazonSocial( (String) json.get("razon_social"));
					proveedorEncontrado.setCorreo( (String) json.get("correo"));
					proveedorEncontrado.setTelefono( (String) json.get("telefono"));
					proveedorEncontrado.setEstatus( (String)json.get("estatus"));
					proveedorEncontrado.setIdCiudad( Integer.parseInt((String) json.get("id_ciudad")));
					proveedorEncontrado.setNombreContacto( (String) json.get("nombre_proveedor"));
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return proveedorEncontrado;
	}
	
	public ArrayList<Proveedor> BuscarProveedores(java.util.Vector<Object> datos)
	{
		ArrayList<Proveedor> proveedores = new ArrayList<Proveedor>();
		HttpClient httpclient = new DefaultHttpClient();
		int idCondominio = (Integer) datos.elementAt(0);

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=7&idcondominio="+idCondominio);
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

					JSONArray proveedoresCondominio = json.getJSONArray("proveedores");
					for(int i=0; i < proveedoresCondominio.length(); i++){
						Proveedor pro = new Proveedor();
						pro.setId( Integer.parseInt((String) proveedoresCondominio.getJSONObject(i).get("id")));
						pro.setRif( (String) proveedoresCondominio.getJSONObject(i).get("rif"));
						pro.setRazonSocial( (String) proveedoresCondominio.getJSONObject(i).get("razon_social"));
						pro.setCorreo( (String) proveedoresCondominio.getJSONObject(i).get("correo"));
						pro.setTelefono( (String) proveedoresCondominio.getJSONObject(i).get("telefono"));
						pro.setDireccion( (String) proveedoresCondominio.getJSONObject(i).get("direccion"));
						pro.setEstatus( (String)proveedoresCondominio.getJSONObject(i).get("estatus"));
						pro.setIdCiudad( Integer.parseInt((String) proveedoresCondominio.getJSONObject(i).get("id_ciudad")));
						pro.setNombreContacto( (String) proveedoresCondominio.getJSONObject(i).get("nombre_proveedor"));
						
						proveedores.add(pro);
					}
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return proveedores;
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
