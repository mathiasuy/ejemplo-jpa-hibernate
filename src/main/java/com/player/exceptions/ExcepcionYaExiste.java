package com.player.exceptions;

public class ExcepcionYaExiste extends ExcepcionSistema{
	
	public ExcepcionYaExiste() {
		super("Ya existe el elemento");
	}
	
	public ExcepcionYaExiste(String mensaje) {
		super(mensaje);
	}
	
}
