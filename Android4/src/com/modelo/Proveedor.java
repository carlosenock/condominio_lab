package com.modelo;

public class Proveedor {
	
	private int id;
	private String rif;
	private String razonSocial;
	private String correo;
	private String direccion;
	private String telefono;
	private String estatus;
	private int idCiudad;
	private String nombreContacto;
	
	public Proveedor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Proveedor(int id, String rif, String razonSocial, String correo,
			String direccion, String telefono, String estatus, int idCiudad,
			String nombreContacto) {
		super();
		this.id = id;
		this.rif = rif;
		this.razonSocial = razonSocial;
		this.correo = correo;
		this.direccion = direccion;
		this.telefono = telefono;
		this.estatus = estatus;
		this.idCiudad = idCiudad;
		this.nombreContacto = nombreContacto;
	}


	public String getRif() {
		return rif;
	}

	public void setRif(String rif) {
		this.rif = rif;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}
	
}
