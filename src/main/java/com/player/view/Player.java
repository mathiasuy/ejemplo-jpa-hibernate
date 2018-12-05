package com.player.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.MalformedURLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JTextPane;

import com.player.controllers.Reproductor;

import javazoom.jlgui.basicplayer.BasicPlayerException;

import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Player extends JPanel {
	private JSlider sldProgress = new JSlider();
	private Reproductor repro = new Reproductor();
	private String ruta = null;
	private ExecutorService exe = Executors.newFixedThreadPool(1);
	private String cartel = "";
	private JTextArea txtPanel = new JTextArea();
	
	public void setFileToPlay(String ruta) {
		try {
			this.ruta = ruta;
			cartel = ruta;
			repro.loadFile(ruta);
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setURLToPlay(String ruta) {
		try {
			this.ruta = ruta;
			repro.loadURL(ruta);
		} catch (BasicPlayerException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setCartel(String cartel) {
		this.cartel = cartel;
	}
	
	public void play() {
		if (ruta != null)
			try {
				txtPanel.setText(cartel);
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
				txtPanel.setText("Parado.");
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
		
		add(sldProgress, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		

		txtPanel.setLineWrap(true);
		scrollPane.setViewportView(txtPanel);

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
