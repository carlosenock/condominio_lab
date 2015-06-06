package com.modelo;

public class Condominio {
	
	private int id;
	private String rif;
	private String nombre;
	private String direccion;
	private int tipo;
	private int idCiudad;
	private String estatus;
	private float interesMora;
	private String tiempoMora;

	public Condominio() {
		// TODO Auto-generated constructor stub
	}

	public Condominio(int id, String rif, String nombre, String direccion,
			int tipo, int idCiudad, String estatus,
			float interesMora, String tiempoMora) {
		super();
		this.id = id;
		this.rif = rif;
		this.nombre = nombre;
		this.direccion = direccion;
		this.tipo = tipo;
		this.idCiudad = idCiudad;
		this.estatus = estatus;
		this.interesMora = interesMora;
		this.tiempoMora = tiempoMora;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRif() {
		return rif;
	}

	public void setRif(String rif) {
		this.rif = rif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public int getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}

	public float getInteresMora() {
		return interesMora;
	}

	public void setInteresMora(float interesMora) {
		this.interesMora = interesMora;
	}

	public String getTiempoMora() {
		return tiempoMora;
	}

	public void setTiempoMora(String tiempoMora) {
		this.tiempoMora = tiempoMora;
	}
	
}
