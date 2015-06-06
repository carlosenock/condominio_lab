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

import com.modelo.Horario;

public class ServicioHorarioArea {

	public ServicioHorarioArea() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Horario> BuscarHorarioArea(java.util.Vector<Object> datos)
	{
		ArrayList<Horario> horarios = new ArrayList<Horario>();
		HttpClient httpclient = new DefaultHttpClient();
		int idArea = (Integer) datos.elementAt(0);

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=16&idareacomun="+idArea);
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

					JSONArray horariosArea = json.getJSONArray("horarios");
					for(int i=0; i < horariosArea.length(); i++){
						Horario horario = new Horario();
						horario.setId( Integer.parseInt((String) horariosArea.getJSONObject(i).get("id")));
						horario.setCodigo( (String) horariosArea.getJSONObject(i).get("codigo"));
						horario.setDia( (String) horariosArea.getJSONObject(i).get("dia"));
						horario.setHoraInicio( (String) horariosArea.getJSONObject(i).get("hora_inicio"));
						horario.setHoraFinal( (String) horariosArea.getJSONObject(i).get("hora_fin"));
						horario.setEstatus((String) horariosArea.getJSONObject(i).get("estatus"));
						
						horarios.add(horario);
					}
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return horarios;
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
