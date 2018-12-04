package com.player.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.player.datatypes.DtEmisora;
import com.player.datatypes.DtGrabable;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="ID_GRABABLE")
public class Emisora extends Grabable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name= "BANDA")
	private String banda;
	
	@Column(name= "FRECUENCIA")
	private String frecuencia;
	
	@Column(name= "URL")
	private String url;
	
	public Emisora(String nombre, String descripcion, String nombreArchivo, String banda, String frecuencia,
			String url,String  pais,String localidad, String tipo) {
		super(nombre, descripcion, nombreArchivo,pais,localidad,tipo);
		this.banda = banda;
		this.frecuencia = frecuencia;
		this.url = url;
	}

	public Emisora() {super();}
	

	
	public String getBanda() {
		return banda;
	}

	public void setBanda(String banda) {
		this.banda = banda;
	}

	public String getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(String frecuencia) {
		this.frecuencia = frecuencia;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public DtGrabable getDt() {
		return new DtEmisora(super.getId(), super.getNombre(), super.getDescripcion(), super.getNombreArchivo(), this.banda, this.frecuencia, this.url,super.getTipo());
	}

	@Override
	public String toString() {
		return super.toString() + " Emisora [banda=" + banda + ", frecuencia=" + frecuencia + ", url=" + url + "]";
	}

}
