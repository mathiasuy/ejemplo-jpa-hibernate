package com.player.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.player.model.FactoryModel;
import controller.Reproductor;
import controller.Sound;
import com.player.datatypes.DtEmisora;
import com.player.datatypes.DtGrabable;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import model.Grabable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;

public class Grabador extends JPanel{
	Reproductor repro = new Reproductor();	
	Factory fac = new Factory();
	
	JScrollPane scrollPane_1 = new JScrollPane();
	JLayeredPane layeredPane = new JLayeredPane();
	JButton btnPlay = new JButton("Play");
	JButton btnStop = new JButton("Stop Record");
	JButton btnRecord = new JButton("Record");
	JLabel lblMensaje = new JLabel("");
	ExecutorService exe = Executors.newFixedThreadPool(1);
	
	
	HashMap<Integer, Grabacion> grabaciones = new HashMap<>();
	
	static int alto = 13;
	
	/**
	 * Create the frame.
	 */
	public Grabador() {
		fac.getIGrabables().precarga();
		ListaEmisoras listaGrabables = new ListaEmisoras(fac);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		fac.getIGrabables().precarga();
		carga();
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 459);
//		contentPane = new JPanel();
		setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
		setLayout(null);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					repro.loadURL(((DtEmisora)listaGrabables.getSeleccion()).getUrl());
//					repro.play();
					exe.execute(repro);
				} catch (BasicPlayerException | MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		btnPlay.setBounds(273, 24, 97, 25);
		add(btnPlay);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (grabaciones.containsKey(listaGrabables.getIdSeleccion())){
					int key = listaGrabables.getIdSeleccion();
					grabaciones.get(key).pararGrabacion();
					grabaciones.remove(key);
				}
			}
		});
		
		btnStop.setBounds(134, 24, 114, 25);
		add(btnStop);
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!grabaciones.containsKey(listaGrabables.getIdSeleccion())){
					DtGrabable grab = listaGrabables.getSeleccion();
					Grabacion grabando = new Grabacion(grab);
					
					Cronometro jPanel = grabando.getjPanel();
					for (int i = 0; i < layeredPane.getComponents().length; i++){
						int altoY = ((Cronometro)layeredPane.getComponent(i)).getBounds().y;
						((Cronometro)layeredPane.getComponent(i)).setBounds(0, altoY+ 80, 199, 60);
					};
					jPanel.setBounds(0, 13, 199, 60);
					alto += 80;
					layeredPane.add(jPanel);
					jPanel.setVisible(true);	
					
					grabaciones.put(grabando.getId(), grabando);		
				}
			}
		});
		
		
		btnRecord.setBounds(25, 24, 97, 25);
		add(btnRecord);
		

		lblMensaje.setBounds(12, 224, 175, 16);
		add(lblMensaje);
		
		
		scrollPane_1.setBounds(273, 85, 201, 286);
		add(scrollPane_1);
		listaGrabables.setVisible(true);
		listaGrabables.setBounds(25, 85, 223, 286);
		add(listaGrabables);
		
		scrollPane_1.setViewportView(layeredPane);
		
//		textField = new JTextField();
		textField.setBounds(25, 401, 322, 22);
		add(textField);
		textField.setColumns(10);
		btnAbrirCarpeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				FileDialog dialogoArchivo;
//				dialogoArchivo = new FileDialog(f,"Lista de Archivos desde Frame", FileDialog.SAVE);
//				dialogoArchivo.show();				
			}
		});
		btnAbrirCarpeta.setBounds(359, 400, 115, 25);
		
		add(btnAbrirCarpeta);
		btnStopPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					repro.stop();
				} catch (BasicPlayerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStopPlay.setBounds(377, 24, 97, 25);
		
		add(btnStopPlay);
	}	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField = new JTextField();
	private final JButton btnAbrirCarpeta = new JButton("Abrir carpeta");
	private final JButton btnStopPlay = new JButton("Stop");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grabador frame = new Grabador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void carga() {
		File file = new File("");
//		Path path = FileSystems.getDefault().getPath(".");
		textField.setText(file.getAbsolutePath() + "\\records\\");
	}
}
