package com.modelo;

public class Servicio {
	
	private int id;
	private String codigo;
	private String descripcion;
	private String estatus;
	
	public Servicio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Servicio(int id, String codigo, String descripcion, String estatus) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.estatus = estatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
