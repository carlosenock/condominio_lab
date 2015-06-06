package com.modelo;

import java.util.Date;

public class Copropietario extends Persona {

	private String foto;
	
	public Copropietario() {
		// TODO Auto-generated constructor stub
	}
	
	public Copropietario(String foto) {
		super();
		this.foto = foto;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
}
