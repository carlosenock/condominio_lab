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

import com.modelo.Noticia;

public class ServicioNoticia {

	public ServicioNoticia() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Noticia> BuscarNoticiasCondominio(java.util.Vector<Object> datos)
	{
		ArrayList<Noticia> noticias = new ArrayList<Noticia>();
		HttpClient httpclient = new DefaultHttpClient();
		int idCondominio = (Integer) datos.elementAt(0);

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=10&idcondominio="+idCondominio);
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

					JSONArray serNoticia = json.getJSONArray("noticias");
					for(int i=0; i < serNoticia.length(); i++){
						Noticia noticia = new Noticia();
						noticia.setId( Integer.parseInt((String) serNoticia.getJSONObject(i).get("id")));
						noticia.setCodigo( (String) serNoticia.getJSONObject(i).get("codigo"));
						noticia.setFecha( convertirFecha((String) serNoticia.getJSONObject(i).get("fecha")));
						noticia.setDescripcion(( (String) serNoticia.getJSONObject(i).get("descripcion")));
						noticia.setArchivo(( (String) serNoticia.getJSONObject(i).get("archivo")));
						noticia.setTipoNoticia( Integer.parseInt((String) serNoticia.getJSONObject(i).get("tipo_noticia")));
						noticia.setIdLogin(Integer.parseInt((String) serNoticia.getJSONObject(i).get("login")));
						noticia.setCedulaAutor( (String) serNoticia.getJSONObject(i).get("cedula"));
						noticia.setNombreTipo( (String) serNoticia.getJSONObject(i).get("tipo_nombre"));
						
						noticias.add(noticia);
					}
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return noticias;
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
