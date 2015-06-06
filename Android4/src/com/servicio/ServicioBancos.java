package com.servicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.widget.Toast;

import com.modelo.Banco;
import com.modelo.FormaPago;

public class ServicioBancos {

	public ServicioBancos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Banco> BuscarBancos(int idCondominio){
		ArrayList<Banco> bancos = new ArrayList<Banco>();
		HttpClient httpclient = new DefaultHttpClient(); 

		// Preparar un objeto request via method Get
		HttpGet httpget = new HttpGet("http://condominioucla.webcindario.com/despachador.php?servicio=25&idcondominio="+idCondominio);
		//Log.v("CONDOMINIO BANCO", "http://condominioucla.webcindario.com/despachador.php?servicio=25&idcondominio="+idCondominio);
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

					JSONArray formaBanco = json.getJSONArray("bancos");
					for(int i=0; i < formaBanco.length(); i++){
						Banco banco = new Banco();
						banco.setIdBanco( Integer.parseInt((String) formaBanco.getJSONObject(i).get("id")));
						banco.setCodCondominio( (String) formaBanco.getJSONObject(i).get("codigo"));
						banco.setNombreBanco( (String) formaBanco.getJSONObject(i).get("nombre"));
						banco.setRifBanco( (String) formaBanco.getJSONObject(i).get("rif"));
						//Log.v("banco",(String) formaBanco.getJSONObject(i).get("nombre"));
						bancos.add(banco);
					}
				}catch(JSONException  e){
					e.printStackTrace();
				}

			}

		}
		catch (Exception e) {
			e.printStackTrace();  
		}
		return bancos;
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
