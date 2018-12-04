package com.player.view;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabPlayer extends JPanel {
	Player player = new Player();
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
		setBounds(100, 100, 781, 493);
		setLayout(null);
		
		ListaGrabados listaGrabados = new ListaGrabados();
		listaGrabados.setBounds(31, 48, 261, 339);
		listaGrabados.setVisible(true);
		add(listaGrabados);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaGrabados.update();
			}
		});
		btnActualizar.setBounds(31, 399, 97, 25);
		add(btnActualizar);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listaGrabados.getSeleccion() != null)
				player.setFileToPlay(listaGrabados.getSeleccion().getNombre());
				player.play();
			}
		});
		btnPlay.setBounds(140, 399, 69, 25);
		add(btnPlay);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				player.stop();
			}
		});
		btnStop.setBounds(221, 400, 71, 25);
		add(btnStop);
		
		
		player.setBounds(349, 48, 364, 70);
		add(player);
		
		
		

	}
}
