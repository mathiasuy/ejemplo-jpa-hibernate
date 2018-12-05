package com.player.view;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.player.datatypes.DtEmisora;
import com.player.datatypes.DtGrabable;
import com.player.exceptions.ExcepcionNoSoportado;
import com.player.model.FactoryModel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabPlayer extends JPanel {
	Player player = new Player();
	FactoryModel fac = new FactoryModel();
	ListaEmisoras lstEmisoras = new ListaEmisoras();

	ListaGrabados listaGrabados = new ListaGrabados();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabPlayer frame = new TabPlayer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TabPlayer() {
		setBounds(100, 100, 684, 531);
		setLayout(null);
		
		listaGrabados.setBounds(24, 169, 362, 326);
		listaGrabados.setVisible(true);
		add(listaGrabados);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carga();
			}
		});
		btnActualizar.setBounds(24, 131, 97, 25);
		add(btnActualizar);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listaGrabados.getSeleccion() != null) {
					player.setFileToPlay(listaGrabados.getSeleccion().getNombre());
					player.play();
				}
			}
		});
		btnPlay.setBounds(317, 131, 69, 25);
		add(btnPlay);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				player.stop();
			}
		});
		btnStop.setBounds(234, 131, 71, 25);
		add(btnStop);
		
		
		player.setBounds(24, 13, 625, 105);
		add(player);
		
		
		lstEmisoras.setBounds(398, 169, 251, 326);
		add(lstEmisoras);
		
		JButton btnPlayURL = new JButton("Play");
		btnPlayURL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						if (lstEmisoras.getSeleccion() != null) {
							DtGrabable grabable = ((DtGrabable)lstEmisoras.getSeleccion());
							String cartel = grabable.getNombre();
							cartel += "\nDescripcion: " + grabable.getDescripcion();
							cartel += "\nAgregada el día: " + grabable.getFechaAgregado();
							cartel += "\n" + grabable.getPais() + " - " + grabable.getLocalidad();
							
							player.setCartel(cartel);
							
							if (grabable instanceof DtEmisora) {
								DtEmisora emisora = (DtEmisora)grabable;
								if (!emisora.getTipo().equals("mp3"))
										throw new ExcepcionNoSoportado();
							}
							player.setURLToPlay(((DtEmisora)lstEmisoras.getSeleccion()).getUrl());
							player.play();
						}				
					} catch (ExcepcionNoSoportado e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		btnPlayURL.setBounds(580, 131, 69, 25);
		add(btnPlayURL);
		
		JButton btnStopURL = new JButton("Stop");
		btnStopURL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.stop();
			}
		});
		btnStopURL.setBounds(497, 131, 71, 25);
		add(btnStopURL);
		
		
		

	}

	public void carga() {
		listaGrabados.update();
		lstEmisoras.carga();
	}
}
