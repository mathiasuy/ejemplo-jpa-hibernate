package com.player.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JTextPane;

import controller.Reproductor;
import javazoom.jlgui.basicplayer.BasicPlayerException;

import javax.swing.JSlider;

public class Player extends JPanel {
	
	JTextPane txtPanel = new JTextPane();
	JSlider sldProgress = new JSlider();
	Reproductor repro = new Reproductor();
	String ruta = null;
	ExecutorService exe = Executors.newFixedThreadPool(1);
	
	public void setFileToPlay(String ruta) {
		try {
			this.ruta = ruta;
			repro.loadFile(ruta);
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void play() {
		if (ruta != null)
			try {
				txtPanel.setText(ruta);
				repro.load(txtPanel, sldProgress);
				exe.execute(repro);
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void stop() {
		if (ruta != null)
			try {
				repro.stop();
			} catch (BasicPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	/**
	 * Create the panel.
	 */
	public Player() {
		setLayout(new BorderLayout(0, 0));
		
		add(txtPanel, BorderLayout.CENTER);
		
		
		add(sldProgress, BorderLayout.SOUTH);

		this.sldProgress.addMouseMotionListener(new MouseMotionAdapter(){
			@Override
			public void mouseDragged(MouseEvent e) {
				synchronized (sldProgress) {
					if(sldProgress.isEnabled()) {
						int anchoReal = sldProgress.getWidth()  -sldProgress.getHeight()*2;
						int xReal = e.getX() - sldProgress.getHeight();

						float porcentaje;
						if(xReal <= anchoReal) {
							porcentaje = ((float)xReal)/anchoReal;
						}else {
							porcentaje = ((float)sldProgress.getValue())/1000;
						}
						try {
							repro.irA(porcentaje);
						} catch (BasicPlayerException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});		
		
		if (ruta != null) {
			txtPanel.setText(ruta);
//			sldProgress.setSize(repro.getBytesLenght());
		}
		
	}

	
	
}
