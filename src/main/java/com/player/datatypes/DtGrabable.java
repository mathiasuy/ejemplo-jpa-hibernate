package com.player.datatypes;

import java.util.Date;
import java.util.HashMap;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.player.model.Direccion;

public class DtGrabable {

	private Long id; 
	private String nombre;
	private String descripcion;
	private String nombreArchivo;
	private Date fechaAgregado;
	private String tipo;
	private String localidad;
	private String pais;
	
	
	public DtGrabable(Long id, String nombre, String descripcion, String nombreArchivo,String tipo, String pais, String localidad) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.fechaAgregado = new Date();
		this.nombreArchivo = nombreArchivo;
		this.pais = pais;
		this.localidad = localidad;
	}

	public String getLocalidad() {
		return this.localidad;
	}
	
	public String getNombre() {
		return nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public String getNombreArchivo() {
		return nombreArchivo;
	}


	public Date getFechaAgregado() {
		return fechaAgregado;
	}


	public Long getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return this.nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public String getPais() {
		return pais;
	}
}
