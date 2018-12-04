package com.player.datatypes;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.player.model.Grabable;

public class DtEvento {
	
	private int id_evento;
	private String nombre;
	private String nombreArchivo;
	private LocalTime horaFin;
	private LocalTime horaInicio;
	private DayOfWeek day_of_week;
	private Date fecha_inicio;
	private Date fecha_fin;
	private DtGrabable grabable;
	
	public DtEvento(int id_evento, String nombre, String nombreArchivo, LocalTime horaFin, LocalTime horaInicio,
			DayOfWeek day_of_week, Date fecha_inicio, Date fecha_fin, DtGrabable dtGrabable) {
		super();
		this.id_evento = id_evento;
		this.nombre = nombre;
		this.nombreArchivo = nombreArchivo;
		this.horaFin = horaFin;
		this.horaInicio = horaInicio;
		this.day_of_week = day_of_week;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.grabable = dtGrabable;
	}

	public int getId_evento() {
		return id_evento;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public DayOfWeek getDay_of_week() {
		return day_of_week;
	}

	public Date getFecha_inicio() {
		return fecha_inicio;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public DtGrabable getGrabable() {
		return grabable;
	}

	@Override
	public String toString() {
		return this.nombre ;
	}
	
	
	
}
