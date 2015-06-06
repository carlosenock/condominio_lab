package com.modelo;

import java.util.Date;

public class Persona {
	
	private String cedula;
	private String nombre;
	private String apellido;
	private String correo;
	private String telefono;
	private Date fechaCreacion;
	private String estatus;
	private String direccion;
	private Date fechaNacimiento;
	private String twitter;

	public Persona() {
		// TODO Auto-generated constructor stub
	}

	public Persona(String cedula, String nombre, String apellido,
			String correo, String telefono, Date fechaCreacion, String estatus,
			String direccion, Date fechaNacimiento, String twitter) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.telefono = telefono;
		this.fechaCreacion = fechaCreacion;
		this.estatus = estatus;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.twitter = twitter;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	
}
