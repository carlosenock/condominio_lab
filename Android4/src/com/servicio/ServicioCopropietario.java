package com.servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.modelo.Copropietario;

public class ServicioCopropietario {

	public ServicioCopropietario() {
		// TODO Auto-generated constructor stub
	}
	
	public Copropietario BuscarCopropietario(java.util.Vector<Object> datos)
	{
		Copropietario copropietarioEncontrado = new Copropietario();
		HttpClient httpclient = new DefaultHttpClient();
		String idCopropietario = (String) datos.elementAt(0);

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=3&idcopropietario="+idCopropietario);
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

					copropietarioEncontrado.setCedula( (String) json.get("id"));
					copropietarioEncontrado.setNombre( (String) json.get("nombre"));
					copropietarioEncontrado.setApellido( (String) json.get("apellido"));
					copropietarioEncontrado.setCorreo( (String) json.get("correo"));
					copropietarioEncontrado.setTelefono( (String) json.get("telefono"));
					copropietarioEncontrado.setFechaCreacion(convertirFecha((String) json.get("fecha_creacion")));
					copropietarioEncontrado.setDireccion( (String) json.get("direccion"));
					copropietarioEncontrado.setFechaNacimiento( convertirFecha((String) json.get("fecha_nacimiento")));
					copropietarioEncontrado.setFoto((String) json.get("foto"));
					copropietarioEncontrado.setTwitter( (String) json.get("twitter"));
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return copropietarioEncontrado;
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
