package com.modelo;

import java.util.Date;

public class JuntadeCondominios {

	private String codigoJuntacondominio;
	private Date fechainicio;
	private Date fechaculminacion;
	private String estatusvigenciacargo;
	private String estatusjuntacondominio;
	private int idLoginJuntaCondominio;
	private int idCargoJuntaCondominio;
	private int idCondominioJuntaCondominio;
	private int IdCondominio;
	
	
	public JuntadeCondominios(String codigoJuntacondominio, Date fechainicio,
			Date fechaculminacion, String estatusvigenciacargo,
			String estatusjuntacondominio, int idLoginJuntaCondominio,
			int idCargoJuntaCondominio, int idCondominioJuntaCondominio,
			int idCondominio) {
		super();
		this.codigoJuntacondominio = codigoJuntacondominio;
		this.fechainicio = fechainicio;
		this.fechaculminacion = fechaculminacion;
		this.estatusvigenciacargo = estatusvigenciacargo;
		this.estatusjuntacondominio = estatusjuntacondominio;
		this.idLoginJuntaCondominio = idLoginJuntaCondominio;
		this.idCargoJuntaCondominio = idCargoJuntaCondominio;
		this.idCondominioJuntaCondominio = idCondominioJuntaCondominio;
		IdCondominio = idCondominio;
	}


	public JuntadeCondominios() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getCodigoJuntacondominio() {
		return codigoJuntacondominio;
	}


	public void setCodigoJuntacondominio(String codigoJuntacondominio) {
		this.codigoJuntacondominio = codigoJuntacondominio;
	}


	public Date getFechainicio() {
		return fechainicio;
	}


	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}


	public Date getFechaculminacion() {
		return fechaculminacion;
	}


	public void setFechaculminacion(Date fechaculminacion) {
		this.fechaculminacion = fechaculminacion;
	}


	public String getEstatusvigenciacargo() {
		return estatusvigenciacargo;
	}


	public void setEstatusvigenciacargo(String estatusvigenciacargo) {
		this.estatusvigenciacargo = estatusvigenciacargo;
	}


	public String getEstatusjuntacondominio() {
		return estatusjuntacondominio;
	}


	public void setEstatusjuntacondominio(String estatusjuntacondominio) {
		this.estatusjuntacondominio = estatusjuntacondominio;
	}


	public int getIdLoginJuntaCondominio() {
		return idLoginJuntaCondominio;
	}


	public void setIdLoginJuntaCondominio(int idLoginJuntaCondominio) {
		this.idLoginJuntaCondominio = idLoginJuntaCondominio;
	}


	public int getIdCargoJuntaCondominio() {
		return idCargoJuntaCondominio;
	}


	public void setIdCargoJuntaCondominio(int idCargoJuntaCondominio) {
		this.idCargoJuntaCondominio = idCargoJuntaCondominio;
	}


	public int getIdCondominioJuntaCondominio() {
		return idCondominioJuntaCondominio;
	}


	public void setIdCondominioJuntaCondominio(int idCondominioJuntaCondominio) {
		this.idCondominioJuntaCondominio = idCondominioJuntaCondominio;
	}


	public int getIdCondominio() {
		return IdCondominio;
	}


	public void setIdCondominio(int idCondominio) {
		IdCondominio = idCondominio;
	}
	
	
	
}
