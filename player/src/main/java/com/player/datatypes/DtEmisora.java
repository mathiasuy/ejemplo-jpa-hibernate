package com.player.datatypes;

import com.player.datatypes.DtGrabable;

public class DtEmisora extends DtGrabable{

	private String banda;
	private String frecuencia;
	private String url;
	
	public DtEmisora(Long id, String nombre, String descripcion, String nombreArchivo, String banda, String frecuencia,
			String url,String tipo) {
		super(id, nombre, descripcion, nombreArchivo,tipo);
		this.banda = banda;
		this.frecuencia = frecuencia;
		this.url = url;
	}

	public String getBanda() {
		return banda;
	}
	
	public String getFrecuencia() {
		return frecuencia;
	}
	
	public String getUrl() {
		return url;
	}
}
