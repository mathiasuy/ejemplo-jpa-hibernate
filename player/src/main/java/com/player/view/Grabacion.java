package com.player.view;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.Sound;
import com.player.datatypes.DtGrabable;

public class Grabacion {

	int id;
	ExecutorService sounds;
	ExecutorService timer;
	Cronometro jPanel;
	DtGrabable grabable;
	
	public Grabacion(DtGrabable grabable) {
		this.id = grabable.getId();
		this.jPanel = new Cronometro();
		this.jPanel.iniciarCronometro(grabable);
		this.grabable = grabable;
		sounds = Executors.newFixedThreadPool(1);
		timer = Executors.newFixedThreadPool(1);
		sounds.execute(new Sound(grabable));
		timer.execute(this.jPanel);
	}

	public int getId() {
		return id;
	}

	public Cronometro getjPanel() {
		return jPanel;
	}

	public DtGrabable getGrabable() {
		return grabable;
	}
	
	public void pararGrabacion() {
		this.jPanel.pararCronometro();
		sounds.shutdownNow();
//		timer.shutdownNow();
	}
	
	
	
	
	
}
