package com.modelo;

public class Rol {
	
	private int idRol;
	private String codRol;
	private String nomRol;
	private String estatus;
	
	public Rol() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rol(int idRol, String codRol, String nomRol, String estatus) {
		super();
		this.idRol = idRol;
		this.codRol = codRol;
		this.nomRol = nomRol;
		this.estatus = estatus;
	}
	public int getIdRol() {
		return idRol;
	}
	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}
	public String getCodRol() {
		return codRol;
	}
	public void setCodRol(String codRol) {
		this.codRol = codRol;
	}
	public String getNomRol() {
		return nomRol;
	}
	public void setNomRol(String nomRol) {
		this.nomRol = nomRol;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

}