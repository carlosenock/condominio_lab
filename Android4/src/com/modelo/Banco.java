package com.modelo;

public class Banco {
	
	private int idBanco = 0;
	private String codCondominio= null;
	private String nombreBanco= null;
	private String rifBanco = null;
	private String estatusBanco = null;
	
	
	public Banco(int idBanco, String codCondominio, String nombreBanco,
			String rifBanco, String estatusBanco) {
		super();
		this.idBanco = idBanco;
		this.codCondominio = codCondominio;
		this.nombreBanco = nombreBanco;
		this.rifBanco = rifBanco;
		this.estatusBanco = estatusBanco;
	}


	public Banco() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getIdBanco() {
		return idBanco;
	}


	public void setIdBanco(int idBanco) {
		this.idBanco = idBanco;
	}


	public String getCodCondominio() {
		return codCondominio;
	}


	public void setCodCondominio(String codCondominio) {
		this.codCondominio = codCondominio;
	}


	public String getNombreBanco() {
		return nombreBanco;
	}


	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}


	public String getRifBanco() {
		return rifBanco;
	}


	public void setRifBanco(String rifBanco) {
		this.rifBanco = rifBanco;
	}


	public String getEstatusBanco() {
		return estatusBanco;
	}


	public void setEstatusBanco(String estatusBanco) {
		this.estatusBanco = estatusBanco;
	}
	
	

}
