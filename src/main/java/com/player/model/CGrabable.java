package com.player.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.player.datatypes.DtEmisora;
import com.player.datatypes.DtEvento;
import com.player.datatypes.DtGrabable;
import com.player.exceptions.ExcepcionNoEncontrado;

public class CGrabable implements IGrabable{

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
	
	@Override
	public Long altaEmisora(String nombre, String descripcion, String nombreArchivo, String banda, String frecuencia,
			String url,String  pais,String localidad, String tipo) {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		Emisora emi = new Emisora(nombre, descripcion, nombreArchivo, banda, frecuencia, url, pais, localidad, tipo);
		manager.persist(emi);
		manager.getTransaction().commit();
		manager.close();
//		emf.close();
		return emi.getId();
	}
	
	@Override
	public Long modificarEmisora(Long id, String nombre, String descripcion, String nombreArchivo, String banda, String frecuencia,
			String url,String  pais,String localidad, String tipo) throws ExcepcionNoEncontrado {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		Emisora emi = manager.find(Emisora.class, id);
		if (emi == null) {
			manager.close();			
			throw new ExcepcionNoEncontrado();
		}
		emi.setNombre(nombre);
		emi.setDescripcion(descripcion);
		emi.setNombreArchivo(nombreArchivo);
		emi.setBanda(banda);
		emi.setFrecuencia(frecuencia);
		emi.setUrl(url);
		emi.setPais(pais);
		emi.setLocalidad(localidad);
		emi.setTipo(tipo);
		manager.persist(emi);
		manager.getTransaction().commit();
		manager.close();
		return emi.getId();
	}

	@Override
	public boolean bajaEmisora(Long id) throws ExcepcionNoEncontrado {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		Emisora emi = manager.find(Emisora.class, id);
		if (emi == null) {
			manager.close();			
			throw new ExcepcionNoEncontrado();
		}
		manager.remove(emi);
		manager.getTransaction().commit();
		manager.close();
		return true;
	}

	@Override
	public DtGrabable getGrabable(Long id) throws ExcepcionNoEncontrado {
		EntityManager manager = emf.createEntityManager();
		Emisora emi = manager.find(Emisora.class, id);
		if (emi == null) {
			manager.close();			
			throw new ExcepcionNoEncontrado();
		}
		manager.close();
		return emi.getDt();
	}

	@Override
	public List<DtGrabable> listarGrabables(){
		EntityManager manager = emf.createEntityManager();
		@SuppressWarnings("unchecked")
		List<Emisora> lista = (List<Emisora>)manager.createQuery("FROM Emisora").getResultList();
		ArrayList<DtGrabable> grabables = new ArrayList<>();
		lista.forEach(gra->{
			grabables.add(gra.getDt());
		});
		manager.close();
		return grabables;
	}

	@Override
	public List<DtEvento> listarEventosGrabables(Long id_grabable) throws ExcepcionNoEncontrado{
		EntityManager manager = emf.createEntityManager();
		Grabable grabables = manager.find(Grabable.class, id_grabable);
		if (grabables == null) {
			manager.close();			
			throw new ExcepcionNoEncontrado();
		}
		ArrayList<DtEvento> lista = grabables.listarDtEventos();
		manager.close();
		return lista;
	}

	@Override
	public void agregarEventoAEmisora(Long id_emisora, String nombre, String nombreArchivo,  LocalTime horaInicio, LocalTime horaFin, DayOfWeek day_of_week,
			Date fecha_inicio, Date fecha_fin) throws ExcepcionNoEncontrado {
			EntityManager manager = emf.createEntityManager();
			manager.getTransaction().begin();
			Emisora emi = manager.find(Emisora.class, id_emisora);
			if (emi == null) {
				manager.close();			
				throw new ExcepcionNoEncontrado();
			}
			emi.agregarEvento(nombre, nombreArchivo, horaInicio, horaFin, day_of_week, fecha_inicio, fecha_fin);
			manager.getTransaction().commit();
			manager.close();
		}

	@Override
	public void quitarEventoAEmisora(Long id_emisora, int id) throws ExcepcionNoEncontrado {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		Emisora emisora = manager.find(Emisora.class, id_emisora);
		if (emisora == null) {
			manager.close();			
			throw new ExcepcionNoEncontrado();
		}
//		System.out.println("A borrar: " + emisora.getEvento(id));
		emisora.quitarEvento(id);
		manager.getTransaction().commit();
		manager.close();
	}
	
	public void eventosDeHoy() {
		
	}
}
