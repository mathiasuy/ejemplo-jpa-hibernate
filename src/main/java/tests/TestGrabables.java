package tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.player.datatypes.DtEmisora;
import com.player.datatypes.DtEvento;
import com.player.datatypes.DtGrabable;
import com.player.exceptions.ExcepcionNoEncontrado;
import com.player.model.CGrabable;
import com.player.model.Emisora;
import com.player.model.Evento;
import com.player.model.FactoryModel;
import com.player.model.Grabable;
import com.player.model.IGrabable;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public abstract class TestGrabables {

	private static FactoryModel fac = new FactoryModel();
	private static IGrabable cgr = fac.getCGrabable();
	private static EntityManager manager;
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");;
	
	public static void main(String[] args) {
		try {

			//		emf = Persistence.createEntityManagerFactory("Persistencia");
		
		
			cgr.altaEmisora("DelSol", "Emisora de radio", "DelSol", "99.5", "FM", "https://radio3.dl.uy:9952/?type=http&nocache=2780", "Uruguay","Montevideo","mp3");
			cgr.altaEmisora("Oceano", "Emisora de radio", "Oceano", "93.9", "FM", "http://radio3.oceanofm.com:8010/listen.mp3", "Uruguay","Montevideo","aac");
			cgr.altaEmisora("Azul", "Emisora de radio", "Azul", "101.9", "FM", "http://195.154.182.222:3320/stream", "Uruguay","Montevideo","aac");
			cgr.altaEmisora("Sport890", "Emisora de radio", "Deportes", "890", "AM", "http://d1a7butsko8nkd.cloudfront.net/sport.mp3", "Uruguay","Montevideo","mp3");

			cgr.modificarEmisora(new Long(4),"Sport8900", "Emisora de radio", "Deportes", "890", "AM", "http://d1a7butsko8nkd.cloudfront.net/sport.mp3", "Uruguay","Montevideo","mp3");
		
			cgr.bajaEmisora(new Long(3));
			cgr.agregarEventoAEmisora(new Long(1), "La mesa de los galanes", "LaMesa", LocalTime.now(), LocalTime.now(), DayOfWeek.FRIDAY, new Date(), new Date());
			cgr.quitarEventoAEmisora(new Long(1), 0);
	//		manager = emf.createEntityManager();
	//		Emisora emi = manager.find(Emisora.class, new Long(1));
	//		System.out.println("2)En la base de datos hay " + emi.getEventos().size() + " eventos de "+ emi.getNombre() +".");
			
	//		System.out.println(cgr.getEmisora(new Long(3)));
			imprimirTodo();
		} catch (ExcepcionNoEncontrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void imprimirTodo() throws ExcepcionNoEncontrado {
//		manager = emf.createEntityManager();
		List<DtGrabable> lista = cgr.listarGrabables();
		for (int i = 0; i < lista.size(); i++) {
			DtGrabable emi = lista.get(i);
			System.out.println(emi);
			List<DtEvento> eventos = cgr.listarEventosGrabables(emi.getId());
			System.out.println("En la base de datos hay " + eventos.size() + " eventos de "+ emi.getNombre() +".");
			for (int j = 0; j < eventos.size(); j++) {
				System.out.println(eventos.get(j));
			}
		}
		System.out.println("En la base de datos hay " + lista.size() + " emisoras.");
	}
	

}
