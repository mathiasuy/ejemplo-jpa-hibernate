package com.player.view;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.player.model.FactoryModel;
import com.player.controllers.Reproductor;
import com.player.datatypes.DtEmisora;
import com.player.datatypes.DtGrabable;
import com.player.exceptions.ExcepcionNoSoportado;

import javazoom.jlgui.basicplayer.BasicPlayerException;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Grabador extends JPanel{
	Reproductor repro = new Reproductor();	
	FactoryModel fac = new FactoryModel();
	JButton btnPlay = new JButton("Play");
	JButton btnStop = new JButton("Stop Record");
	JButton btnRecord = new JButton("Record");
	ExecutorService exe = Executors.newFixedThreadPool(1);
	
	
	HashMap<Long, Grabacion> grabaciones = new HashMap<>();
	
	static int alto = 13;
	
	/**
	 * Create the frame.
	 */
	public Grabador() {
//		fac.getIGrabables().precarga();
		ListaEmisoras listaGrabables = new ListaEmisoras();
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
//		fac.getIGrabables().precarga();
		carga();
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 536);
//		contentPane = new JPanel();
		setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
		setLayout(null);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DtEmisora emisora = (DtEmisora)listaGrabables.getSeleccion();
					if (emisora.getTipo().equals("mp3"))
						throw new ExcepcionNoSoportado();
					repro.loadURL(emisora.getUrl());
					exe.execute(repro);
				} catch (BasicPlayerException | MalformedURLException | ExcepcionNoSoportado e) {
					JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnPlay.setBounds(405, 46, 97, 25);
		add(btnPlay);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (grabaciones.containsKey(listaGrabables.getIdSeleccion())){
					Long key = listaGrabables.getIdSeleccion();
					grabaciones.get(key).pararGrabacion();
					grabaciones.remove(key);
				}
			}
		});
		
		btnStop.setBounds(201, 46, 114, 25);
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
		
		
		btnRecord.setBounds(66, 46, 97, 25);
		add(btnRecord);
		listaGrabables.setVisible(true);
		listaGrabables.setBounds(66, 84, 247, 355);
		add(listaGrabables);
		
//		textField = new JTextField();
		textField.setBounds(66, 464, 545, 22);
		add(textField);
		textField.setColumns(10);
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
		btnStopPlay.setBounds(514, 46, 97, 25);
		
		add(btnStopPlay);
		scrollPane.setBounds(407, 84, 204, 355);
		
		add(scrollPane);
		
		scrollPane.setViewportView(layeredPane);
	}	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField = new JTextField();
	private final JButton btnStopPlay = new JButton("Stop");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JLayeredPane layeredPane = new JLayeredPane();

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
