package com.player.model;


import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Direccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_DIRECCION")
	private Long id_direccion;

	@Column(name="PAIS")
	private String pais;

	@Column(name="LOCALIDAD")
	private String localidad;

	//Esto es por si quiero que Direccion vea a Grabable, como direccion no es duenia en la relacion se usa mapppedBy, donde se indica cual es el atributo del otro lado de la relacion que mappea a esta clase
	//Tambien agrego fetch = LAZY para que  grabable solo se cargue si se lo pido mediante el getter
	@OneToOne(mappedBy="direccion", fetch = FetchType.LAZY)
	private Grabable grabable;
	
	public Direccion() {
		this.pais = "N/D";
		this.localidad = "N/D";
	}

	public Direccion(String pais, String localidad) {
		super();
		this.pais = pais;
		this.localidad = localidad;
	}

	public Long getId() {
		return id_direccion;
	}
	
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	
	
	public Grabable getGrabable() {
		return grabable;
	}

	public void setGrabable(Grabable grabable) {
		this.grabable = grabable;
	}

	public Long getId_direccion() {
		return id_direccion;
	}

	@Override
	public String toString() {
		return "Direccion [pais=" + pais + ", localidad=" + localidad + ", grabable="+ grabable.getNombre() + "]";
	}
	
	
	
}
