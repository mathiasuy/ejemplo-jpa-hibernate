package com.player.exceptions;

public class ExcepcionSistema extends Exception{

	public ExcepcionSistema() {
		super("Ha ocurrido un error");
	}
	
	public ExcepcionSistema(String mensaje) {
		super("ERROR: " + mensaje);
	}
	
}
