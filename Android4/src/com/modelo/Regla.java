package com.modelo;

public class Regla {
	
	private int id;
	private String descripcion;
	private int idArea;

	public Regla() {
		// TODO Auto-generated constructor stub
	}

	public Regla(int id, String descripcion, int idArea) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.idArea = idArea;
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

	public int getIdArea() {
		return idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}
	
}
