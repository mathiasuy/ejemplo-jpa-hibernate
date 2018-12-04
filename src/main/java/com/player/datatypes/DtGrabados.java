package com.player.datatypes;

import java.util.Date;

public class DtGrabados {
	private String nombre;
	private Date fechaGrabado;
	
	public DtGrabados(String nombre, Date date) {
		this.nombre = nombre;
		this.fechaGrabado = date;
	}
	
	public Date getFechaGrabado() {
		return this.fechaGrabado;
	}
	
	public  String getNombre() {
		return this.nombre;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}
}
