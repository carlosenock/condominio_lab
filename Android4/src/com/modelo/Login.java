package com.modelo;

import java.util.Date;

public class Login {
	
	private int id;
	private String usuario;
	private String clave;
	private Date fechaRegistro;
	private int idRol;
	private String estatus;

	public Login() {
		// TODO Auto-generated constructor stub
	}

	public Login(int id, String usuario, String clave, Date fechaRegistro,
			int idRol, String estatus) {
		this.id = id;
		this.usuario = usuario;
		this.clave = clave;
		this.fechaRegistro = fechaRegistro;
		this.idRol = idRol;
		this.estatus = estatus;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

}
