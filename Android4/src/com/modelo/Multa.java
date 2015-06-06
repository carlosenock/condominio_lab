package com.modelo;

import java.util.Date;

public class Multa {
	
	private int id;
	private String codigo;
	private String descripcion;
	private float monto;
	private Date fecha;
	private int tipoMulta;
	private int idInmueble;
	private int idRecibo;

	public Multa() {
		// TODO Auto-generated constructor stub
	}
	
	public Multa(int id, String codigo, String descripcion, float monto,
			Date fecha, int tipoMulta, int idInmueble, int idRecibo) {
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.monto = monto;
		this.fecha = fecha;
		this.tipoMulta = tipoMulta;
		this.idInmueble = idInmueble;
		this.idRecibo = idRecibo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getTipoMulta() {
		return tipoMulta;
	}

	public void setTipoMulta(int tipoMulta) {
		this.tipoMulta = tipoMulta;
	}

	public int getIdInmueble() {
		return idInmueble;
	}

	public void setIdInmueble(int idInmueble) {
		this.idInmueble = idInmueble;
	}

	public int getIdRecibo() {
		return idRecibo;
	}

	public void setIdRecibo(int idRecibo) {
		this.idRecibo = idRecibo;
	}
	
}
