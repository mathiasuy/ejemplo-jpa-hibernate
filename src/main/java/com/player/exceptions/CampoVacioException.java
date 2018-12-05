package com.player.exceptions;

public class CampoVacioException extends ExcepcionSistema{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CampoVacioException() {
		super("Campo vacio no permitido");
	}
	
	public CampoVacioException(String mensaje) {
		super(mensaje);
	}
}
