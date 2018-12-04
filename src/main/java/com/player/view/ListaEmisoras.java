package com.player.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.player.model.FactoryModel;
import com.player.datatypes.DtGrabable;
import java.awt.BorderLayout;

public class ListaEmisoras extends JPanel {
	JList<DtGrabable> lstGrabables = new JList<DtGrabable>();
	JScrollPane scrollPane = new JScrollPane();
	/**
	 * Create the panel.
	 */
	public ListaEmisoras(FactoryModel fac) {
		setLayout(new BorderLayout(0, 0));
		scrollPane.setBounds(25, 85, 223, 286);
		add(scrollPane);
		
		scrollPane.setViewportView(lstGrabables);		
		
		List<DtGrabable> grabables = fac.getIGrabables().listarGrabables();
		DefaultListModel<DtGrabable> dflm = new DefaultListModel<>();
		grabables.forEach(grab->{
			dflm.addElement(grab);
		});
		lstGrabables.setModel(dflm);
		lstGrabables.setSelectedIndex(0);
	}
	
	public DtGrabable getSeleccion() {
		return this.lstGrabables.getSelectedValue();
	}
	
	public Long getIdSeleccion() {
		return this.lstGrabables.getSelectedValue().getId();
	}

}
