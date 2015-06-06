package com.modelo;

import java.util.Date;

import android.media.Image;

public class Noticia {
	
	private int id;
	private String codigo;
	private Date fecha;
	private String descripcion;
	private int tipoNoticia;
	private String archivo;
	private String estatus;
	private int idCondominio;
	private int idLogin;
	private String cedulaAutor;
	private String nombreTipo;

	public Noticia() {
		// TODO Auto-generated constructor stub
	}

	public Noticia(int id, String codigo, Date fecha, String descripcion,
			int tipoNoticia, String archivo, String estatus, int idCondominio,
			int idLogin, String cedulaAutor, String nombreTipo) {
		this.id = id;
		this.codigo = codigo;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.tipoNoticia = tipoNoticia;
		this.archivo = archivo;
		this.estatus = estatus;
		this.idCondominio = idCondominio;
		this.idLogin = idLogin;
		this.cedulaAutor = cedulaAutor;
		this.nombreTipo = nombreTipo;
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

	public int getTipoNoticia() {
		return tipoNoticia;
	}

	public void setTipoNoticia(int tipoNoticia) {
		this.tipoNoticia = tipoNoticia;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
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

	public int getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(int idLogin) {
		this.idLogin = idLogin;
	}

	public String getCedulaAutor() {
		return cedulaAutor;
	}

	public void setCedulaAutor(String cedulaAutor) {
		this.cedulaAutor = cedulaAutor;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}
	
}
