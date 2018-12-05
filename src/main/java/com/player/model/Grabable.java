package com.player.model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.player.datatypes.DtEvento;
import com.player.datatypes.DtGrabable;

@Entity
@Table
@Inheritance( strategy = InheritanceType.JOINED )
public abstract class Grabable  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_GRABABLE")
	private Long id_grabable;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="NOMBREARCHIVO")
	private String nombreArchivo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAgregado;
	
	//Para crear una relacion unidireccional alcanza con poner esto de este lado, el ID_DIRECCION es el nombre de la columna en la otra tabla
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="ID_DIRECCION")
	private Direccion direccion = new Direccion();
	
	@Column(name="TIPO")
	private String tipo;
	
	//Tenemos un Grabable para varios Eventos
	//En OneToMany el fetch por defecto es LAZY
	//pongo mappedBy="Grabable" para indicar que campo JAVA (no columna) en Evento es el que guarda el Grabable (la relacion llegara desde Evento)
	@OneToMany(mappedBy = "grabable", cascade = CascadeType.ALL)//, orphanRemoval = true)//aca el fetch es por defecto, se agrega a modo ilustrativo
	private List<Evento> eventos = new ArrayList<>();
	
	
	public Grabable(String nombre, String descripcion, String nombreArchivo, String pais, String localidad, String tipo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaAgregado = new Date();
		this.nombreArchivo = nombreArchivo;
		this.direccion.setPais(pais);
		this.setLocalidad(localidad);
		this.tipo = tipo;
	}

	public Grabable() {}
	
//	public void agregarEvento(Evento ev) {
//		this.eventos.put(ev.getId(),ev);
//	}
//	
//	public void quitarEvento(int id) {
//		this.eventos.remove(id);
//	}

	public String getNombre() {
		return nombre;
	}
	
	public String getTipo() {
		return tipo;
	}


	public Evento agregarEvento(String nombre, String nombreArchivo,  LocalTime horaInicio, LocalTime horaFin, DayOfWeek day_of_week,
			Date fecha_inicio, Date fecha_fin) {
		Evento evento = new Evento(this.eventos.size(), nombre, nombreArchivo, horaInicio, horaFin, day_of_week, fecha_inicio, fecha_fin, this);
		this.eventos.add(evento);
		return evento;
	}
	
	public Evento agregarEvento(Evento evento) {
		this.eventos.add(evento);
		return evento;
	}	
	
	public Evento getEvento(int i) {
		return this.eventos.get(i);
	}
	
	public ArrayList<DtEvento> listarDtEventos() {
		ArrayList<DtEvento> eventos = new ArrayList<>();
		this.eventos.forEach(eve->{
			eventos.add(eve.getDt());
		});
		return eventos;
	}
	
	public void quitarEvento(int id) {
		this.eventos.remove(id);
	}
	
	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getNombreArchivo() {
		return nombreArchivo;
	}


	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}


	public Date getFechaAgregado() {
		return fechaAgregado;
	}


	
	
	public String getPais() {
		return direccion.getPais();
	}

	public void setPais(String pais) {
		this.direccion.setPais(pais);;
	}

	public String getLocalidad() {
		return direccion.getLocalidad();
	}

	public void setLocalidad(String localidad) {
		this.direccion.setLocalidad(localidad);
	}

	public Long getId() {
		return id_grabable;
	}
	
	
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public abstract DtGrabable getDt();

	@Override
	public String toString() {
		return "Grabable [id_grabable=" + id_grabable + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", nombreArchivo=" + nombreArchivo + ", fechaAgregado=" + fechaAgregado + direccion +", tipo=" + tipo + "]";
	}
}
