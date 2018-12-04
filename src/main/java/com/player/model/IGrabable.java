package com.player.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.player.datatypes.DtEvento;
import com.player.datatypes.DtGrabable;
import com.player.datatypes.DtGrabados;
import com.player.exceptions.ExcepcionNoEncontrado;

public interface IGrabable {

	public abstract Long altaEmisora(String nombre, String descripcion, String nombreArchivo, String banda, String frecuencia,
			String url, String pais, String localidad, String tipo);

	public abstract Long modificarEmisora(Long id, String nombre, String descripcion, String nombreArchivo, String banda,
			String frecuencia, String url, String pais, String localidad, String tipo) throws ExcepcionNoEncontrado;

	public abstract boolean bajaEmisora(Long id) throws ExcepcionNoEncontrado;

	public abstract DtGrabable getGrabable(Long id) throws ExcepcionNoEncontrado;

	public abstract List<DtGrabable> listarGrabables();

	public abstract List<DtEvento> listarEventosGrabables(Long id_grabable) throws ExcepcionNoEncontrado;

	public abstract void agregarEventoAEmisora(Long id_emisora, String nombre, String nombreArchivo, LocalTime horaInicio,
			LocalTime horaFin, DayOfWeek day_of_week, Date fecha_inicio, Date fecha_fin) throws ExcepcionNoEncontrado;

	public abstract void quitarEventoAEmisora(Long id_emisora, int id) throws ExcepcionNoEncontrado;

	public abstract ArrayList<DtGrabados> listRecords();

	public abstract void precarga();

	
	
}
