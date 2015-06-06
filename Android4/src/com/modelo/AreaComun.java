package com.modelo;

public class AreaComun {
	
	private int id;
	private String codigo;
	private String descripcion;
	private float monto;
	private int capacidad;
	private String nombre;
	private int idCondominio;
	private String estatus;

	public AreaComun() {
		// TODO Auto-generated constructor stub
	}

	public AreaComun(int id, String codigo, String descripcion, float monto,
			int capacidad, String nombre, int idCondominio, String estatus) {
		this.id = id;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.monto = monto;
		this.capacidad = capacidad;
		this.nombre = nombre;
		this.idCondominio = idCondominio;
		this.estatus = estatus;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public int getIdCondominio() {
		return idCondominio;
	}

	public void setIdCondominio(int idCondominio) {
		this.idCondominio = idCondominio;
	}

}
