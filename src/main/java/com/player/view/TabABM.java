package com.player.view;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

import com.player.datatypes.DtEmisora;
import com.player.datatypes.DtGrabable;
import com.player.exceptions.CampoVacioException;
import com.player.exceptions.ExcepcionNoEncontrado;
import com.player.model.FactoryModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class TabABM extends JPanel {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JList<DtGrabable> lstEmisoras = new JList<DtGrabable>();
	private FactoryModel fac = new FactoryModel();	
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtNombreArchivo;
	private JTextField txtFrecuencia;
	private JTextField txtSrc;
	private JTextField txtPais;
	private JTextField txtLocalidad;
	private JComboBox<String> cmbTipoDeArchivo = new JComboBox<String>();
	/**
	 * Create the panel.
	 */
	public TabABM() {
		setLayout(null);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(387, 26, 247, 22);
		add(txtId);
		txtId.setColumns(10);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(319, 29, 56, 16);
		add(lblId);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(319, 64, 56, 16);
		add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(459, 61, 175, 22);
		add(txtNombre);
		txtNombre.setColumns(10);
		
		txtNombreArchivo = new JTextField();
		txtNombreArchivo.setColumns(10);
		txtNombreArchivo.setBounds(459, 96, 175, 22);
		add(txtNombreArchivo);
		
		JLabel lblNombreDeArchivo = new JLabel("Nombre de archivo:");
		lblNombreDeArchivo.setBounds(319, 96, 128, 16);
		add(lblNombreDeArchivo);
		
		JLabel lblFrecuencia = new JLabel("Frecuencia:");
		lblFrecuencia.setBounds(319, 296, 93, 16);
		add(lblFrecuencia);
		
		txtFrecuencia = new JTextField();
		txtFrecuencia.setColumns(10);
		txtFrecuencia.setBounds(401, 293, 87, 22);
		add(txtFrecuencia);
		
		JLabel lblBanda = new JLabel("Banda:");
		lblBanda.setBounds(500, 296, 93, 16);
		add(lblBanda);
		
		JComboBox<String> cmbBanda = new JComboBox<String>();
		cmbBanda.addItem("AM");
		cmbBanda.addItem("FM");
		cmbBanda.addItem("SW");
		cmbBanda.setBounds(573, 293, 61, 22);
		add(cmbBanda);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(319, 160, 122, 16);
		add(lblDescripcion);
		
		JTextPane txtDescripcion = new JTextPane();
		txtDescripcion.setBounds(319, 178, 315, 65);
		add(txtDescripcion);
		
		JLabel lblFechaDeAgregado = new JLabel("Fecha de agregado:");
		lblFechaDeAgregado.setBounds(319, 131, 122, 16);
		add(lblFechaDeAgregado);
		
		JDateChooser dateFechaDeAgregado = new JDateChooser();
		dateFechaDeAgregado.setBounds(459, 131, 175, 22);
		add(dateFechaDeAgregado);
		
		JComboBox<String> cmbFuente = new JComboBox<String>();
		cmbFuente.setBounds(442, 256, 192, 22);
		cmbFuente.addItem("Radio Online");
//		cmbFuente.addItem("Radio Online");
		add(cmbFuente);
		
		JLabel lblFuente = new JLabel("Fuente:");
		lblFuente.setBounds(319, 256, 122, 16);
		add(lblFuente);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 26, 247, 466);
		add(scrollPane);
		

		lstEmisoras.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!lstEmisoras.isSelectionEmpty()) {
					DtGrabable grabable = lstEmisoras.getSelectedValue();
					txtId.setText(grabable.getId() + "");
					txtNombre.setText(grabable.getNombre());
					txtDescripcion.setText(grabable.getDescripcion());
					txtNombreArchivo.setText(grabable.getNombreArchivo());
					dateFechaDeAgregado.setDate(grabable.getFechaAgregado());
					dateFechaDeAgregado.setEnabled(false);
					if (grabable instanceof DtEmisora) {
						cmbFuente.setSelectedItem("Radio Online");
						cmbBanda.setSelectedItem(((DtEmisora)grabable).getBanda());
						cmbTipoDeArchivo.setSelectedItem(grabable.getTipo());
						txtFrecuencia.setText(((DtEmisora) grabable).getFrecuencia());
						txtSrc.setText(((DtEmisora) grabable).getUrl());
						txtPais.setText(grabable.getPais());
						txtLocalidad.setText(grabable.getLocalidad());
					}
				}
			}
		});
		scrollPane.setViewportView(lstEmisoras);
		
		txtSrc = new JTextField();
		txtSrc.setColumns(10);
		txtSrc.setBounds(401, 333, 164, 22);
		add(txtSrc);
		
		JLabel lblSrc = new JLabel("SRC:");
		lblSrc.setBounds(319, 336, 93, 16);
		add(lblSrc);
		
		txtPais = new JTextField();
		txtPais.setColumns(10);
		txtPais.setBounds(401, 370, 233, 22);
		add(txtPais);
		
		JLabel lblPas = new JLabel("Pa\u00EDs:");
		lblPas.setBounds(319, 373, 70, 16);
		add(lblPas);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setColumns(10);
		txtLocalidad.setBounds(401, 405, 233, 22);
		add(txtLocalidad);
		
		JLabel lblLocalidad = new JLabel("Localidad:");
		lblLocalidad.setBounds(319, 409, 70, 16);
		add(lblLocalidad);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!lstEmisoras.isSelectionEmpty()) {
					Long id = ((DtGrabable)lstEmisoras.getSelectedValue()).getId(); 
					String nombre = txtNombre.getText().trim(); 
					String descripcion = txtDescripcion.getText().trim();
					String nombreArchivo = txtNombreArchivo.getText().trim();
					String banda = (String)cmbBanda.getSelectedItem();
					String frecuencia = txtFrecuencia.getText().trim();
					String url = txtSrc.getText().trim();
					String pais = txtPais.getText().trim();
					String localidad = txtLocalidad.getText().trim();
					String tipo = (String)cmbTipoDeArchivo.getSelectedItem();
					try {
						if (nombre.isEmpty())
							throw new CampoVacioException("El nombre no puede estar vacio");
						if (url.isEmpty())
							throw new MalformedURLException("La URL esta mal formada");
						fac.getIGrabables().modificarEmisora(id, nombre, descripcion, nombreArchivo, banda, frecuencia, url, pais, localidad, tipo);
					} catch (ExcepcionNoEncontrado | MalformedURLException | CampoVacioException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}
					carga();
				}
			}
		});
		btnModificar.setBounds(537, 467, 97, 25);
		add(btnModificar);
		
		cmbTipoDeArchivo.setBounds(573, 333, 61, 22);
		cmbTipoDeArchivo.addItem("aac");
		cmbTipoDeArchivo.addItem("wav");
		cmbTipoDeArchivo.addItem("mp3");
		cmbTipoDeArchivo.addItem("wma");
		add(cmbTipoDeArchivo);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String nombre = txtNombre.getText(); 
					String descripcion = txtDescripcion.getText();
					String nombreArchivo = txtNombreArchivo.getText();
					String banda = (String)cmbBanda.getSelectedItem();
					String frecuencia = txtFrecuencia.getText();
					String url = txtSrc.getText();
					String pais = txtPais.getText();
					String localidad = txtLocalidad.getText();
					String tipo = (String)cmbTipoDeArchivo.getSelectedItem();
					System.out.println("click");
					Long id = fac.getIGrabables().altaEmisora(nombre, descripcion, nombreArchivo, banda, frecuencia, url, pais, localidad, tipo);
					txtId.setText(id + "");
					carga();
			}
		});
		btnAgregar.setBounds(428, 467, 97, 25);
		add(btnAgregar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (lstEmisoras.getSelectedValue() != null) {
					Long id = lstEmisoras.getSelectedValue().getId();
					try {
						fac.getIGrabables().bajaEmisora(id);
					} catch (ExcepcionNoEncontrado e) {
						JOptionPane.showMessageDialog(null, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					}
					carga();
				}
			}
		});
		btnBorrar.setBounds(315, 467, 97, 25);
		add(btnBorrar);

		carga();
		
	}
	
	public void carga() {
		DefaultListModel<DtGrabable> dflm = new DefaultListModel<>();
		List<DtGrabable> grabables = fac.getIGrabables().listarGrabables();
		grabables.forEach(gra->{
			dflm.addElement(gra);
		});
		lstEmisoras.setModel(dflm);		
	}
}
