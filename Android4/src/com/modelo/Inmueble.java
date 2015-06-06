package com.modelo;

public class Inmueble {
	
	private int id;
	private String codigo;
	private String metrosCuadrados;
	private float alicuota;
	private String descripcion;
	private String codigoCatastral;
	private String idCopropietario;
	private String idCondominio;
	private String estatus;

	public Inmueble() {
		// TODO Auto-generated constructor stub
	}

	public Inmueble(int id, String codigo, String metrosCuadrados,
			float alicuota, String descripcion, String codigoCatastral,
			String idCopropietario, String idCondominio, String estatus) {
		this.id = id;
		this.codigo = codigo;
		this.metrosCuadrados = metrosCuadrados;
		this.alicuota = alicuota;
		this.descripcion = descripcion;
		this.codigoCatastral = codigoCatastral;
		this.idCopropietario = idCopropietario;
		this.idCondominio = idCondominio;
		this.estatus = estatus;
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

	public String getMetrosCuadrados() {
		return metrosCuadrados;
	}

	public void setMetrosCuadrados(String metrosCuadrados) {
		this.metrosCuadrados = metrosCuadrados;
	}

	public float getAlicuota() {
		return alicuota;
	}

	public void setAlicuota(float alicuota) {
		this.alicuota = alicuota;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getCodigoCatastral() {
		return codigoCatastral;
	}

	public void setCodigoCatastral(String codigoCatastral) {
		this.codigoCatastral = codigoCatastral;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public String getIdCopropietario() {
		return idCopropietario;
	}

	public void setIdCopropietario(String idCopropietario) {
		this.idCopropietario = idCopropietario;
	}

	public String getIdCondominio() {
		return idCondominio;
	}

	public void setIdCondominio(String idCondominio) {
		this.idCondominio = idCondominio;
	}
	
}
