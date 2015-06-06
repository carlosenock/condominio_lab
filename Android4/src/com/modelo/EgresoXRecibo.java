package com.modelo;

public class EgresoXRecibo {

	private float montoegresoxrecibo;
	private String estatusegresoxrecibo;
	private int idrecibocobro;
	private int idegreso;
	private int idegresonocomunxinmueble;
	
	
	public EgresoXRecibo(float montoegresoxrecibo, String estatusegresoxrecibo,
			int idrecibocobro, int idegreso, int idegresonocomunxinmueble) {
		super();
		this.montoegresoxrecibo = montoegresoxrecibo;
		this.estatusegresoxrecibo = estatusegresoxrecibo;
		this.idrecibocobro = idrecibocobro;
		this.idegreso = idegreso;
		this.idegresonocomunxinmueble = idegresonocomunxinmueble;
	}


	public EgresoXRecibo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public float getMontoegresoxrecibo() {
		return montoegresoxrecibo;
	}


	public void setMontoegresoxrecibo(float montoegresoxrecibo) {
		this.montoegresoxrecibo = montoegresoxrecibo;
	}


	public String getEstatusegresoxrecibo() {
		return estatusegresoxrecibo;
	}


	public void setEstatusegresoxrecibo(String estatusegresoxrecibo) {
		this.estatusegresoxrecibo = estatusegresoxrecibo;
	}


	public int getIdrecibocobro() {
		return idrecibocobro;
	}


	public void setIdrecibocobro(int idrecibocobro) {
		this.idrecibocobro = idrecibocobro;
	}


	public int getIdegreso() {
		return idegreso;
	}


	public void setIdegreso(int idegreso) {
		this.idegreso = idegreso;
	}


	public int getIdegresonocomunxinmueble() {
		return idegresonocomunxinmueble;
	}


	public void setIdegresonocomunxinmueble(int idegresonocomunxinmueble) {
		this.idegresonocomunxinmueble = idegresonocomunxinmueble;
	}
	
	
}
