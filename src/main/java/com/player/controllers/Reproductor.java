package com.player.controllers;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

public class Reproductor  implements BasicPlayerListener, Runnable{
	private static final String DIRECTORIO = (new File("")).getAbsolutePath() + "\\records\\";	
	private BasicPlayer basicPlayer;
	private Double bytesLength;
	private JTextArea txtPanel = null;
	private JSlider slider = null;
	private Object dir = null;
	
	public Reproductor() {
		basicPlayer = new BasicPlayer();
		basicPlayer.addBasicPlayerListener(this);
	}
	
	public double getBytesLenght() {
		return this.bytesLength;
	}
	
	public void play() throws BasicPlayerException {
		basicPlayer.play();
	}
	
	public void load(JTextArea txtPanel2, JSlider slider) throws BasicPlayerException {
		this.txtPanel = txtPanel2;
		this.slider = slider;
	}
	
	public void loadFile(String ruta) throws BasicPlayerException {
		dir = new File(DIRECTORIO + ruta);
	}
	
	public void loadURL(String url) throws BasicPlayerException, MalformedURLException {
		dir = new URL(url);
	}

	@Override
	public void opened(Object arg0, Map arg1) {
		 if (arg1.containsKey("audio.length.bytes")) {
			 bytesLength = Double.parseDouble(arg1.get("audio.length.bytes").toString());
		 }else {
			 bytesLength = new Double(100);
		 }
	}

	@Override
	public void progress(int bytesread, long microseconds, byte[] pcmdata,  Map properties) {
		if (bytesLength != null) {
			 float progressUpdate = (float) (bytesread * 1.0f / bytesLength * 1.0f);
			 int progressNow = (int) (bytesLength * progressUpdate);
			 // Descomentando la siguiente línea se mosrtaría el progreso
			 if (slider != null)
				 slider.setValue(progressNow);
//		  	System.out.println(" -&gt; " + progressNow);
		}
	}

	@Override
	public void setController(BasicController controller) {
		display("setController : "+controller);
	}

	@Override
	public void stateUpdated(BasicPlayerEvent arg0) {
		display("stateUpdated : "+arg0.toString());
	}	
	
	public void display(String msg)
	{
		if (System.out != null) System.out.println(msg);
	}

	public void stop() throws BasicPlayerException {
		basicPlayer.stop();
		slider = null;
		txtPanel = null;
	}	
	
	public void irA(float porcentaje) throws BasicPlayerException {
		if (porcentaje >= 0 && porcentaje <= 1) {
			this.buscar((long)(bytesLength*porcentaje));
		}
}	
	
	private void buscar(long b) {
		try {
			basicPlayer.seek(b);
		} catch (BasicPlayerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			if (dir instanceof URL) {
				URL ddd = (URL)dir;
				basicPlayer.open(ddd);
			}else {
				File ddd = (File)dir;
				basicPlayer.open(ddd);
			}
			this.play();
			if (slider != null)
				slider.setMaximum(bytesLength.intValue());					
		} catch (BasicPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
