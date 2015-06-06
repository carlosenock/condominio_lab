package com.modelo;

import java.util.Date;

public class Egresos {

	private String codigoegreso;
	private String descripcionegreso;
	private Date fechaegreso;
	private float montoegreso;
	private String estatusegreso;
	private int iddetalles;
	private int idcondominio;
	private int idformapagopago;
	
	public Egresos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Egresos(String codigoegreso, String descripcionegreso,
			Date fechaegreso, float montoegreso, String estatusegreso,
			int iddetalles, int idcondominio, int idformapagopago) {
		super();
		this.codigoegreso = codigoegreso;
		this.descripcionegreso = descripcionegreso;
		this.fechaegreso = fechaegreso;
		this.montoegreso = montoegreso;
		this.estatusegreso = estatusegreso;
		this.iddetalles = iddetalles;
		this.idcondominio = idcondominio;
		this.idformapagopago = idformapagopago;
	}

	public String getCodigoegreso() {
		return codigoegreso;
	}

	public void setCodigoegreso(String codigoegreso) {
		this.codigoegreso = codigoegreso;
	}

	public String getDescripcionegreso() {
		return descripcionegreso;
	}

	public void setDescripcionegreso(String descripcionegreso) {
		this.descripcionegreso = descripcionegreso;
	}

	public Date getFechaegreso() {
		return fechaegreso;
	}

	public void setFechaegreso(Date fechaegreso) {
		this.fechaegreso = fechaegreso;
	}

	public float getMontoegreso() {
		return montoegreso;
	}

	public void setMontoegreso(float montoegreso) {
		this.montoegreso = montoegreso;
	}

	public String getEstatusegreso() {
		return estatusegreso;
	}

	public void setEstatusegreso(String estatusegreso) {
		this.estatusegreso = estatusegreso;
	}

	public int getIddetalles() {
		return iddetalles;
	}

	public void setIddetalles(int iddetalles) {
		this.iddetalles = iddetalles;
	}

	public int getIdcondominio() {
		return idcondominio;
	}

	public void setIdcondominio(int idcondominio) {
		this.idcondominio = idcondominio;
	}

	public int getIdformapagopago() {
		return idformapagopago;
	}

	public void setIdformapagopago(int idformapagopago) {
		this.idformapagopago = idformapagopago;
	}
	
	
}
