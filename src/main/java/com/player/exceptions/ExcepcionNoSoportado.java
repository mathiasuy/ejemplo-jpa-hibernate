package com.player.exceptions;

public class ExcepcionNoSoportado extends ExcepcionSistema {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionNoSoportado() {
		super("No soportado");
	}
	
	public ExcepcionNoSoportado(String mensaje) {
		super(mensaje);
	}
		
}
