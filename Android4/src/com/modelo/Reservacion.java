package com.modelo;

import java.util.Date;

public class Reservacion {
	
	private int id;
	private String codigo;
	private Date fechaReservacion;
	private String listaInvitados;
	private float monto;
	private int idInmueble;
	private int idArea;
	private String estatus;

	public Reservacion() {
		// TODO Auto-generated constructor stub
	}

	public Reservacion(int id, String codigo, Date fechaReservacion,
			String listaInvitados, float monto, int idInmueble, int idArea,
			String estatus) {
		this.id = id;
		this.codigo = codigo;
		this.fechaReservacion = fechaReservacion;
		this.listaInvitados = listaInvitados;
		this.monto = monto;
		this.idInmueble = idInmueble;
		this.idArea = idArea;
		this.estatus = estatus;
	}

	public int getIdArea() {
		return idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public int getIdInmueble() {
		return idInmueble;
	}

	public void setIdInmueble(int idInmueble) {
		this.idInmueble = idInmueble;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
}
