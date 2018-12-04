package com.player.exceptions;

public class ExcepcionNoEncontrado extends ExcepcionSistema{

	public ExcepcionNoEncontrado() {
		super("No se ha encontrado el elemento");
	}
	
	public ExcepcionNoEncontrado(String mensaje) {
		super(mensaje);
	}
	
}
