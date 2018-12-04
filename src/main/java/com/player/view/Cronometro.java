package com.player.view;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.player.datatypes.DtGrabable;

import java.awt.Component;
import java.awt.Dimension;

public class Cronometro extends JPanel implements Runnable{
	String nombre;
	JLabel lblNombre = new JLabel("");
	JLabel lblTimer = new JLabel("");
	boolean cronometroActivo = false;
	Thread hilo;
	long tiempo = 0; 
	
	/**
	 * Create the panel.
	 */
	public Cronometro() {
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{385, 0};
		gridBagLayout.rowHeights = new int[]{1, 36, 40, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		setPreferredSize(new Dimension( 2000,2000));
		
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 0);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		add(lblNombre, gbc_lblNombre);
		
		GridBagConstraints gbc_lblTimer = new GridBagConstraints();
		gbc_lblTimer.fill = GridBagConstraints.VERTICAL;
		gbc_lblTimer.gridx = 0;
		gbc_lblTimer.gridy = 2;
		add(lblTimer, gbc_lblTimer);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblNombre, lblTimer}));

	}

    public void iniciarCronometro(DtGrabable grabable) {
        cronometroActivo = true;
        this.nombre = grabable.getNombre();
        hilo = new Thread( this );
        hilo.start();
    }	
	
    public void pararCronometro(){
        cronometroActivo = false;
        lblNombre.setText("Grabacion terminada " + nombre);
    }    
    
	@Override
	public void run() {
		String mostrar = "";
		int segundos = 0;
		int minutos = 0;
		int horas = 0;
		lblNombre.setText("Grabando " + nombre);
        while( cronometroActivo )
        {
            try {
				Thread.sleep( 1000 );
				tiempo++;
				segundos++;
				if (segundos >= 60) {
					segundos = 0;
					minutos++;
					if (minutos >= 60) {
						minutos = 0;
						horas++;
					}
				}
				mostrar = (horas<10?"0" + horas:horas) + ":" + (minutos<10?"0" + minutos:minutos) + ":" + (segundos<10?"0" + segundos:segundos);				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
            lblTimer.setText(mostrar + " t");
        }
	}

}
