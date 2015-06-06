package com.modelo;

import java.util.Date;

public class ReclamoSugerencia {
	
	private int id;
	private String codigo;
	private String razon;
	private Date fecha;
	private String descripcion;
	private int idInmueble;
	private String estatus;
	private String codigoInmueble;

	public ReclamoSugerencia() {
		// TODO Auto-generated constructor stub
	}

	public ReclamoSugerencia(int id, String codigo, String razon, Date fecha,
			String descripcion, int idInmueble, String estatus,
			String codigoInmueble) {
		this.id = id;
		this.codigo = codigo;
		this.razon = razon;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.idInmueble = idInmueble;
		this.estatus = estatus;
		this.codigoInmueble = codigoInmueble;
	}
	
	public ReclamoSugerencia(String codigo, String razon, Date fecha,
			String descripcion, int idInmueble, String estatus,
			String codigoInmueble) {
		this.codigo = codigo;
		this.razon = razon;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.idInmueble = idInmueble;
		this.estatus = estatus;
		this.codigoInmueble = codigoInmueble;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoInmueble() {
		return codigoInmueble;
	}

	public void setCodigoInmueble(String codigoInmueble) {
		this.codigoInmueble = codigoInmueble;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
