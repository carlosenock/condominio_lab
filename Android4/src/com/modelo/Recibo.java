package com.modelo;

import java.util.Date;

public class Recibo {

	private int idrecibocobro;
    private String codigorecibocobro;
    private Date fecharecibocobro;
    private float cuotapendienterecibo;
    private Date fechavencimientorecibo;
    private float abonorecibocobro;
    private float montorecibocobro;
    private String estatuscancelacionrecibo;
    private String estatusrecibocobro;
    private int idinmueblerecibocobro;
    
	

	public Recibo(int idrecibocobro, String codigorecibocobro,
			Date fecharecibocobro, float cuotapendienterecibo,
			Date fechavencimientorecibo, float abonorecibocobro,
			float montorecibocobro, String estatuscancelacionrecibo,
			String estatusrecibocobro, int idinmueblerecibocobro) {
		super();
		this.idrecibocobro = idrecibocobro;
		this.codigorecibocobro = codigorecibocobro;
		this.fecharecibocobro = fecharecibocobro;
		this.cuotapendienterecibo = cuotapendienterecibo;
		this.fechavencimientorecibo = fechavencimientorecibo;
		this.abonorecibocobro = abonorecibocobro;
		this.montorecibocobro = montorecibocobro;
		this.estatuscancelacionrecibo = estatuscancelacionrecibo;
		this.estatusrecibocobro = estatusrecibocobro;
		this.idinmueblerecibocobro = idinmueblerecibocobro;
	}

	public Recibo() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public int getIdrecibocobro() {
		return idrecibocobro;
	}

	public void setIdrecibocobro(int idrecibocobro) {
		this.idrecibocobro = idrecibocobro;
	}

	public String getCodigorecibocobro() {
		return codigorecibocobro;
	}

	public void setCodigorecibocobro(String codigorecibocobro) {
		this.codigorecibocobro = codigorecibocobro;
	}

	public Date getFecharecibocobro() {
		return fecharecibocobro;
	}

	public void setFecharecibocobro(Date fecharecibocobro) {
		this.fecharecibocobro = fecharecibocobro;
	}

	public float getCuotapendienterecibo() {
		return cuotapendienterecibo;
	}

	public void setCuotapendienterecibo(float cuotapendienterecibo) {
		this.cuotapendienterecibo = cuotapendienterecibo;
	}

	public Date getFechavencimientorecibo() {
		return fechavencimientorecibo;
	}

	public void setFechavencimientorecibo(Date fechavencimientorecibo) {
		this.fechavencimientorecibo = fechavencimientorecibo;
	}

	public float getAbonorecibocobro() {
		return abonorecibocobro;
	}

	public void setAbonorecibocobro(float abonorecibocobro) {
		this.abonorecibocobro = abonorecibocobro;
	}

	public float getMontorecibocobro() {
		return montorecibocobro;
	}

	public void setMontorecibocobro(float montorecibocobro) {
		this.montorecibocobro = montorecibocobro;
	}

	public String getEstatuscancelacionrecibo() {
		return estatuscancelacionrecibo;
	}

	public void setEstatuscancelacionrecibo(String estatuscancelacionrecibo) {
		this.estatuscancelacionrecibo = estatuscancelacionrecibo;
	}

	public String getEstatusrecibocobro() {
		return estatusrecibocobro;
	}

	public void setEstatusrecibocobro(String estatusrecibocobro) {
		this.estatusrecibocobro = estatusrecibocobro;
	}

	public int getIdinmueblerecibocobro() {
		return idinmueblerecibocobro;
	}

	public void setIdinmueblerecibocobro(int idinmueblerecibocobro) {
		this.idinmueblerecibocobro = idinmueblerecibocobro;
	}
    
    
}
