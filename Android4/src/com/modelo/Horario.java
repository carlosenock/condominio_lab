package com.modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.text.format.Time;

public class Horario {
	
	private int id;
	private String codigo;
	private String dia;
	private String horaInicio;
	private String horaFinal;
	private String estatus;
	private int idArea;

	public Horario() {
		// TODO Auto-generated constructor stub
	}

	public Horario(int id, String codigo, String dia, String horaInicio,
			String horaFinal, String estatus, int idArea) {
		this.id = id;
		this.codigo = codigo;
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
		this.estatus = estatus;
		this.idArea = idArea;
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

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String string) {
		this.horaInicio = string;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public int getIdArea() {
		return idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}

}
