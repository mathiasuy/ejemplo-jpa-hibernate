package com.player.exceptions;

public class ExcepcionNoPermitido extends ExcepcionSistema{

	public ExcepcionNoPermitido() {
		super("No permitido");
	}
	
	public ExcepcionNoPermitido(String mensaje) {
		super(mensaje);
	}
		
}
