package com.player.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.player.datatypes.DtEvento;

@Entity
public class Evento {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_EVENTO")
	private int id_evento;

	@Column(name="NOMBRE")
	private String nombre;

	@Column(name="NOMBREARCHIVO")
	private String nombreArchivo;

	@Column(name="HORAFIN")
	private LocalTime horaFin;

	@Column(name="HORAINICIO")
	private LocalTime horaInicio;

	@Column(name="DAYOFWEEK")
	private DayOfWeek day_of_week;

	
	@Column(name="FECHAINICIO")
	private Date fecha_inicio;
	
	
	@Column(name="FECHAFIN")
	private Date fecha_fin;
	
	//optional es para indicar si puede ser nulleable
	 //Aca en el ManyToOne el Lazy no era por defecto
	//Ahora le decimos a JPA que los id_grabables los guardaremos en una nueva columna ID_GRABABLE donde hara el join
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "ID_GRABABLE")
	private Grabable grabable;
	
	public Evento() {
		super();
	}


	public Evento(int id_evento, String nombre, String nombreArchivo,  LocalTime horaInicio, LocalTime horaFin, DayOfWeek day_of_week,
		Date fecha_inicio, Date fecha_fin, Grabable grabable) {	
	super();	
	this.id_evento = id_evento;
	
	this.nombre = nombre;	
	this.nombreArchivo = nombreArchivo;	
	this.horaFin = horaFin;	
	this.horaInicio = horaInicio;	
	this.day_of_week = day_of_week;	
	this.fecha_inicio = fecha_inicio;	
	this.fecha_fin = fecha_fin;	
	this.grabable = grabable;	
}
	

	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getNombreArchivo() {
		return nombreArchivo;
	}



	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}



	public LocalTime getHoraFin() {
		return horaFin;
	}



	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}



	public LocalTime getHoraInicio() {
		return horaInicio;
	}



	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}



	public DayOfWeek getDay_of_week() {
		return day_of_week;
	}



	public void setDay_of_week(DayOfWeek day_of_week) {
		this.day_of_week = day_of_week;
	}



	public Date getFecha_inicio() {
		return fecha_inicio;
	}



	public void setFecha_inicio(Date fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}



	public Date getFecha_fin() {
		return fecha_fin;
	}



	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}



	public int getId_evento() {
		return id_evento;
	}

	public void setGrabable(Grabable grabable) {
		this.grabable = grabable;
	}

	public Grabable getGrabable() {
		return this.grabable;
	}

	@Override
	public String toString() {
		return "Evento [id_evento=" + id_evento + ", nombre=" + nombre + ", nombreArchivo=" + nombreArchivo
				+ ", horaFin=" + horaFin + ", horaInicio=" + horaInicio + ", day_of_week=" + day_of_week
				+ ", fecha_inicio=" + fecha_inicio + ", fecha_fin=" + fecha_fin + "]";
	}

	public DtEvento getDt() {
		return new DtEvento(id_evento, getNombre(), getNombreArchivo(), getHoraFin(), getHoraInicio(), getDay_of_week(), getFecha_inicio(), getFecha_fin(), grabable.getDt());
	}
	
}
