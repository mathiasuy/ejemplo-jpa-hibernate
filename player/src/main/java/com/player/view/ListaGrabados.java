package com.player.view;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JScrollPane;

import com.player.model.FactoryModel;
import com.player.datatypes.DtGrabados;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class ListaGrabados extends JPanel {

	FactoryModel fac = new FactoryModel();
	JList<DtGrabados> list = new JList<DtGrabados>();
	
	/**
	 * Create the panel.
	 */
	public ListaGrabados() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(list);

		update();
	}

	void update() {
		DefaultListModel<DtGrabados> dflm = new DefaultListModel<DtGrabados>();
		
		ArrayList<DtGrabados> grabados = fac.getIGrabables().getListRecords();
		grabados.forEach(element->{
			dflm.addElement(element);
		});
		
		list.setModel(dflm);
	}
	
	public DtGrabados getSeleccion() {
		return this.list.getSelectedValue();
	}

}
