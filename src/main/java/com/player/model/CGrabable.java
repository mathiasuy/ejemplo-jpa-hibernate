package com.player.model;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import com.player.datatypes.DtEmisora;
import com.player.datatypes.DtEvento;
import com.player.datatypes.DtGrabable;
import com.player.datatypes.DtGrabados;
import com.player.exceptions.ExcepcionNoEncontrado;

public class CGrabable implements IGrabable{

	private static final String DIRECTORIO = (new File("")).getAbsolutePath() + "\\records\\";	
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");
	
	@Override
	public Long altaEmisora(String nombre, String descripcion, String nombreArchivo, String banda, String frecuencia,
			String url,String  pais,String localidad, String tipoDeArchivo) {
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		Emisora emi = new Emisora(nombre, descripcion, nombreArchivo, banda, frecuencia, url, pais, localidad, tipoDeArchivo);
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
	
	@Override
	public ArrayList<DtGrabados> listRecords() {
		File dir = new File(DIRECTORIO);
		String[] ficheros = dir.list();
		ArrayList<DtGrabados> grabados = new ArrayList<>();
		
		if (ficheros != null)
			  for (int x=0;x<ficheros.length;x++)
			    grabados.add(new DtGrabados(ficheros[x], new Date()));
		
		return grabados;
	}	
	
	@Override
	public void precarga() {
//		JOptionPane.showMessageDialog(null,  "B1");
		altaEmisora("DelSol", "Emisora de radio", "DelSol", "99.5", "FM", "https://radio3.dl.uy:9952/?type=http&nocache=2780", "Uruguay","Montevideo","mp3");
//		JOptionPane.showMessageDialog(null,  "B2");
		altaEmisora("Oceano", "Emisora de radio", "Oceano", "93.9", "FM", "http://radio3.oceanofm.com:8010/listen.mp3", "Uruguay","Montevideo","aac");
		altaEmisora("Azul", "Emisora de radio", "Azul", "101.9", "FM", "http://195.154.182.222:3320/stream", "Uruguay","Montevideo","aac");
		altaEmisora("Sport890", "Emisora de radio", "Deportes", "890", "AM", "http://d1a7butsko8nkd.cloudfront.net/sport.mp3", "Uruguay","Montevideo","mp3");

//		gr = new Emisora(4,"Espectador", "Emisora de radio", "Deportes", "810", "AM", "http://streaming.espectador.com/envivoaac", "Uruguay","Montevideo","aac");
//		MGrabables.getInstance().add(gr);
	}	
	
	public void eventosDeHoy() {
		
	}
}
