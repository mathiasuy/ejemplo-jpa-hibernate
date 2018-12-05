package com.player.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.player.model.FactoryModel;
import com.player.model.IGrabable;

import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Inicio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					JOptionPane.showMessageDialog(null,  "A1");
					FactoryModel fac = new FactoryModel();
//					JOptionPane.showMessageDialog(null,  "A2");
					IGrabable IGR = fac.getIGrabables();
					IGR.precarga();
//					JOptionPane.showMessageDialog(null,  "A3");
					Inicio frame = new Inicio();
//					JOptionPane.showMessageDialog(null,  "A4");
					frame.setVisible(true);
//					JOptionPane.showMessageDialog(null,  "A5");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,  e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 736, 622);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.setBounds(12, 13, 686, 543);
		contentPane.add(tabbedPane);
		
		TabPlayer player = new TabPlayer();
		Grabador grabador = new Grabador();
		TabABM abm = new TabABM();
		tabbedPane.addTab("Reproducir", null, player, null);
		player.setLayout(null);
		tabbedPane.addTab("Grabador", null, grabador, null);
		tabbedPane.addTab("Emisoras", null, abm , null);
	}

}
