package com.hibernate.gui;
//SILVIA
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


import com.hibernate.dao.HabitacionDAO;

import com.hibernate.dao.ServicioDAO;

import com.hibernate.model.Habitacion;

import com.hibernate.model.Servicio;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

import java.awt.Font;

public class App {
	private JFrame frame;
	private JTable habitacionTable;
	private JTable servicioTable;
	// HABITACION
	private JTextField txtIdHabitacion;
	private JTextField txtNumeroHabitacion;
	private JTextField txtPrecio;
	private JComboBox<String> comboBoxTipoHabitacion;
	// SERVICIO
	private JTextField txtIdServicio;
	private JTextField txtNombreServicio;
	// HABITACION SERVICIO
	private JTable habitacionServicioTable;
	
	// ER
	private static final String NOMBRE = "^[A-Za-z]+(?:\\s+[A-Za-z]+){0,2}$";
	private static final String MOVIL = "^[67]\\d{8}$";
	private static final String EMAIL = "^\\w+@\\w+\\.[a-z]{2,3}$";
	private static final String NUMEROHABITACION = "^\\d{3}$";

	public static boolean comprobarExpReg(String palabra, String er) {
		Pattern pat = Pattern.compile(er);
		Matcher mat = pat.matcher(palabra);
		if (mat.matches()) {
			return true;
		} else {
			return false;
		}
	}

	public void cargarTablaHabitacion() {
		HabitacionDAO hd = new HabitacionDAO();
		DefaultTableModel model = (DefaultTableModel) habitacionTable.getModel();
		model.setRowCount(0);
		try {
			List<Habitacion> habitaciones = null;
			try {
				habitaciones = hd.selectAllHabitacion();
			} catch (Exception e) {
				e.printStackTrace();
			}
			habitaciones.forEach(h -> {
				Object[] rowData = { h.getIdHabitacion(), h.getNumeroHabitacion(), h.getTipoHabitacion(),
						h.getPrecio() };
				model.addRow(rowData);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void crearHabitacion() {
		Habitacion h = new Habitacion();
		HabitacionDAO hd = new HabitacionDAO();
		String numeroHabitacion = txtNumeroHabitacion.getText().trim();
		String precioTexto = txtPrecio.getText().trim();
		double precio;
//		int calorias;

		// NUMERO HABITACION
		if (numeroHabitacion.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Complete el campo numero de habitacion", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			txtNumeroHabitacion.requestFocus();
			return;
		} else if (!comprobarExpReg(numeroHabitacion, NUMEROHABITACION)) {
			JOptionPane.showMessageDialog(null, "Formato de numero de habitacion incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
			txtNumeroHabitacion.requestFocus();
			return;
		}
		h.setNumeroHabitacion(numeroHabitacion);
		// TIPO HABITACION
		h.setTipoHabitacion((String) comboBoxTipoHabitacion.getSelectedItem());
		// PRECIO
		if (precioTexto.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Complete el campo precio", "Advertencia", JOptionPane.WARNING_MESSAGE);
			txtPrecio.requestFocus();
			return;
		}
		try {
			precio = Double.parseDouble(precioTexto);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El precio debe ser un numero valido", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			txtPrecio.requestFocus();
			return;
		}
		h.setPrecio(precio);
		
	/*     CALORIAS
	    if (caloriasTexto.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Complete el campo calorias", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        txtCalorias.requestFocus();
	        return;
	    }
	    try {
	        calorias = Integer.parseInt(caloriasTexto);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Las calorias deben ser un numero valido", "Advertencia", JOptionPane.WARNING_MESSAGE);
	        txtCalorias.requestFocus();
	        return;
	    }
	    a.setCalorias(calorias);
	*/
		// INSERCION
		try {
			hd.insertHabitacion(h);
			int idHabitacion = h.getIdHabitacion();
			h.setIdHabitacion(idHabitacion);
			JOptionPane.showMessageDialog(null, "Habitacion creada");
			cargarTablaHabitacion();
			limpiarCamposHabitacion();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al crear el habitacion: " + ex.getMessage());
		}
	}

	public void actualizarHabitacion() {
		Habitacion h = new Habitacion();
		HabitacionDAO hd = new HabitacionDAO();
		String numeroHabitacion = txtNumeroHabitacion.getText().trim();
		String precioTexto = txtPrecio.getText().trim();
		double precio;
		// NUMERO HABITACION
		if (numeroHabitacion.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Complete el campo numero de habitacion", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			txtNumeroHabitacion.requestFocus();
			return;
		} else if (!comprobarExpReg(numeroHabitacion, NUMEROHABITACION)) {
			JOptionPane.showMessageDialog(null, "Formato de numero de habitacion incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
			txtNumeroHabitacion.requestFocus();
			return;
		}
		h.setNumeroHabitacion(numeroHabitacion);
		// TIPO HABITACION
		h.setTipoHabitacion((String) comboBoxTipoHabitacion.getSelectedItem());
		// PRECIO
		if (precioTexto.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Complete el campo precio", "Advertencia", JOptionPane.WARNING_MESSAGE);
			txtPrecio.requestFocus();
			return;
		}
		try {
			precio = Double.parseDouble(precioTexto);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "El precio debe ser un numero valido", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			txtPrecio.requestFocus();
			return;
		}
		h.setPrecio(precio);
		// ACTUALIZACION
		try {
			int idHabitacion = Integer.parseInt(txtIdHabitacion.getText());
			h.setIdHabitacion(idHabitacion);
			hd.updateHabitacion(h);
			JOptionPane.showMessageDialog(null, "Habitacion actualizada");
			cargarTablaHabitacion();
			limpiarCamposHabitacion();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al actualizar la habitacion: " + ex.getMessage());
		}
	}

	public void borrarHabitacion() {
		try {
			HabitacionDAO hd = new HabitacionDAO();
			int idHabitacion = Integer.parseInt(txtIdHabitacion.getText());
			hd.deleteHabitacion(idHabitacion);
			JOptionPane.showMessageDialog(null, "Habitacion borrada");
			cargarTablaHabitacion();
			limpiarCamposHabitacion();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al borrar la habitacion: " + ex.getMessage());
		}
	}

	public void limpiarCamposHabitacion() {
		txtIdHabitacion.setText("");
		txtNumeroHabitacion.setText("");
		comboBoxTipoHabitacion.setSelectedIndex(0);
		txtPrecio.setText("");
	}

	public void cargarTablaServicio() {
		ServicioDAO sd = new ServicioDAO();
		DefaultTableModel model = (DefaultTableModel) servicioTable.getModel();
		model.setRowCount(0);
		try {
			List<Servicio> servicios = sd.selectAllServicio();
			servicios.forEach(s -> {
				Object[] rowData = { s.getIdServicio(), s.getNombreServicio() };
				model.addRow(rowData);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void crearServicio() {
		Servicio s = new Servicio();
		ServicioDAO sd = new ServicioDAO();
		String nombreServicio = txtNombreServicio.getText().trim();

		// NOMBRE SERVICIO
		if (nombreServicio.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Complete el campo nombre de servicio", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			txtNombreServicio.requestFocus();
			return;
		} else if (!comprobarExpReg(nombreServicio, NOMBRE)) {
			JOptionPane.showMessageDialog(null, "Formato de servicio incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
			txtNombreServicio.requestFocus();
			return;
		}
		s.setNombreServicio(nombreServicio);

		// INSERCION
		try {
			int idServicio = sd.insertServicioAndGetId(s);
			s.setIdServicio(idServicio);
			JOptionPane.showMessageDialog(null, "Servicio creado");

			cargarTablaServicio();
			limpiarCamposServicio();

		} catch (Exception ex) {

			JOptionPane.showMessageDialog(null, "Error al crear el servicio: " + ex.getMessage());
		}
	}

	public void actualizarServicio() {
		Servicio s = new Servicio();
		ServicioDAO sd = new ServicioDAO();

		// NOMBRE CLIENTE
		s.setNombreServicio(txtNombreServicio.getText());

		// ACTUALIZACION
		try {
			int idServicio = Integer.parseInt(txtIdServicio.getText());
			s.setIdServicio(idServicio);
			sd.updateServicio(s);
			JOptionPane.showMessageDialog(null, "Servicio actualizado");
			cargarTablaServicio();
			limpiarCamposServicio();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al actualizar el servicio: " + ex.getMessage());
		}
	}

	public void borrarServicio() {
		ServicioDAO sd = new ServicioDAO();
		try {
			int idServicio = Integer.parseInt(txtIdServicio.getText());

			sd.deleteServicio(idServicio);

			JOptionPane.showMessageDialog(null, "Servicio borrado");
			cargarTablaServicio();
			cargarTablaHabitacionServicio();
			limpiarCamposServicio();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error al borrar el servicio: " + ex.getMessage());
		}
	}

	public void limpiarCamposServicio() {
		txtIdServicio.setText("");
		txtNombreServicio.setText("");
	}

	public void cargarTablaHabitacionServicio() {

		HabitacionDAO hd = new HabitacionDAO();
		DefaultTableModel habitacionServicioModel = (DefaultTableModel) habitacionServicioTable.getModel();
		habitacionServicioModel.setRowCount(0);

		List<Habitacion> habitaciones = hd.selectAllHabitacion();

		for (Habitacion hab : habitaciones) {
			for (Servicio serv : hab.getServicios()) {
				habitacionServicioModel.addRow(new Object[] { hab.getIdHabitacion(), serv.getIdServicio() });
			}
		}
	}

	/**
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
					UIManager.put("nimbusBase", new Color(64, 224, 208));
					UIManager.put("nimbusBlueGrey", new Color(190, 190, 190));
					UIManager.put("control", new Color(220, 220, 220));
					UIManager.put("nimbusFocus", new Color(0, 0, 255));
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		HabitacionDAO hd = new HabitacionDAO();
		List<Habitacion> lh = null;

		ServicioDAO sd = new ServicioDAO();
		List<Servicio> ls = null;


		// FRAME
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// TEXTFIELD HABITACION
		txtIdHabitacion = new JTextField();
		txtIdHabitacion.setBounds(135, 150, 150, 25);
		txtIdHabitacion.setEditable(false);
		frame.getContentPane().add(txtIdHabitacion);

		txtNumeroHabitacion = new JTextField();
		txtNumeroHabitacion.setBounds(135, 180, 150, 25);
		frame.getContentPane().add(txtNumeroHabitacion);
	

		txtPrecio = new JTextField();
		txtPrecio.setBounds(135, 240, 150, 25);
		frame.getContentPane().add(txtPrecio);

		// COMBOBOX TIPO HABITACION
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
		comboBoxModel.addElement("Standard");
		comboBoxModel.addElement("Deluxe");
		comboBoxModel.addElement("Premium");
		comboBoxTipoHabitacion = new JComboBox<>(comboBoxModel);
		comboBoxTipoHabitacion.setBounds(135, 210, 150, 25);
		frame.getContentPane().add(comboBoxTipoHabitacion);

		
		// HABITACION
		// TABLA
		DefaultTableModel habitacionModel = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		habitacionModel.addColumn("ID");
		habitacionModel.addColumn("Numero");
		habitacionModel.addColumn("Tipo");
		habitacionModel.addColumn("Precio");

		lh = hd.selectAllHabitacion();

		for (int i = 0; i < lh.size(); i++) {
			Object[] row = new Object[4];
			row[0] = lh.get(i).getIdHabitacion();
			row[1] = lh.get(i).getNumeroHabitacion();
			row[2] = lh.get(i).getTipoHabitacion();
			row[3] = lh.get(i).getPrecio();
			habitacionModel.addRow(row);
		}
		habitacionTable = new JTable(habitacionModel);
		habitacionTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// Centrar los datos en las celdas de la tabla
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < habitacionTable.getColumnCount(); i++) {
			habitacionTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		JScrollPane scrollPaneHabitacion = new JScrollPane(habitacionTable);
		scrollPaneHabitacion.setSize(402, 103);
		scrollPaneHabitacion.setLocation(33, 36);
		scrollPaneHabitacion.setPreferredSize(new Dimension(500, 300));
		frame.getContentPane().add(scrollPaneHabitacion);

		habitacionTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = habitacionTable.getSelectedRow();
				TableModel model = habitacionTable.getModel();

				txtIdHabitacion.setText(model.getValueAt(index, 0).toString());
				txtNumeroHabitacion.setText(model.getValueAt(index, 1).toString());
				comboBoxTipoHabitacion.setSelectedItem(model.getValueAt(index, 2).toString());
				txtPrecio.setText(model.getValueAt(index, 3).toString());
			}
		});

		// BOTONES HABITACION
		JButton btnCrearHabitacion = new JButton("Crear Habitacion");
		btnCrearHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearHabitacion();
			}
		});
		btnCrearHabitacion.setBounds(295, 150, 140, 30);
		frame.getContentPane().add(btnCrearHabitacion);

		JButton btnActualizarHabitacion = new JButton("Actualizar Habitacion");
		btnActualizarHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarHabitacion();
			}
		});
		btnActualizarHabitacion.setBounds(295, 200, 140, 30);
		frame.getContentPane().add(btnActualizarHabitacion);

		JButton btnBorrarHabitacion = new JButton("Borrar Habitacion");
		btnBorrarHabitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarHabitacion();
			}
		});
		btnBorrarHabitacion.setBounds(295, 250, 140, 30);
		frame.getContentPane().add(btnBorrarHabitacion);

		
		txtIdServicio = new JTextField();
		txtIdServicio.setEditable(false);
		txtIdServicio.setBounds(143, 585, 96, 25);
		frame.getContentPane().add(txtIdServicio);
		txtIdServicio.setColumns(10);

		txtNombreServicio = new JTextField();
		txtNombreServicio.setBounds(143, 617, 96, 25);
		frame.getContentPane().add(txtNombreServicio);
		txtNombreServicio.setColumns(10);

		// TABLA SERVICIO
		DefaultTableModel servicioModel = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		servicioModel.addColumn("ID");
		servicioModel.addColumn("Nombre");

		ls = sd.selectAllServicio();

		for (int i = 0; i < ls.size(); i++) {
			Object[] row = new Object[2];
			row[0] = ls.get(i).getIdServicio();
			row[1] = ls.get(i).getNombreServicio();
			servicioModel.addRow(row);
		}
		servicioTable = new JTable(servicioModel);
		servicioTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// Centrar los datos en las celdas de la tabla
		DefaultTableCellRenderer centerRenderer8 = new DefaultTableCellRenderer();
		centerRenderer8.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < servicioTable.getColumnCount(); i++) {
			servicioTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer8);
		}
		JScrollPane scrollPaneServicio = new JScrollPane(servicioTable);
		scrollPaneServicio.setSize(178, 70);
		scrollPaneServicio.setLocation(135, 408);
		scrollPaneServicio.setPreferredSize(new Dimension(500, 300));
		frame.getContentPane().add(scrollPaneServicio);

		servicioTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = servicioTable.getSelectedRow();
				TableModel model = servicioTable.getModel();

				txtIdServicio.setText(model.getValueAt(index, 0).toString());
				txtNombreServicio.setText(model.getValueAt(index, 1).toString());
			}
		});

		cargarTablaServicio();

		JButton btnActualizarServicio = new JButton("Actualizar Servicio");
		btnActualizarServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarServicio();
				cargarTablaServicio();
			}
		});
		btnActualizarServicio.setBounds(250, 584, 140, 25);
		frame.getContentPane().add(btnActualizarServicio);

		JButton btnBorrarServicio = new JButton("Borrar Servicio");
		btnBorrarServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarServicio();
			}
		});
		btnBorrarServicio.setBounds(250, 616, 140, 25);
		frame.getContentPane().add(btnBorrarServicio);

		JButton btnCrearServicio = new JButton("Crear Servicio");
		btnCrearServicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearServicio();
			}
		});
		btnCrearServicio.setBounds(250, 554, 140, 25);
		frame.getContentPane().add(btnCrearServicio);

		// TABLA HABITACION-SERVICIO
		// MUCHOS A MUCHOS
		DefaultTableModel habitacionServicioModel = new DefaultTableModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		habitacionServicioModel.addColumn("IdServicio");
		habitacionServicioModel.addColumn("IdHabitacion");

		lh = hd.selectAllHabitacion();

		for (int i = 0; i < lh.size(); i++) {
			Object[] row = new Object[4];
			row[0] = lh.get(i).getIdHabitacion();
			row[1] = lh.get(i).getNumeroHabitacion();

			habitacionServicioModel.addRow(row);
		}
		habitacionServicioTable = new JTable(habitacionServicioModel);
		habitacionServicioTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// Centrar los datos en las celdas de la tabla
		DefaultTableCellRenderer centerRenderer34 = new DefaultTableCellRenderer();
		centerRenderer34.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < habitacionServicioTable.getColumnCount(); i++) {
			habitacionServicioTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer34);
		}
		JScrollPane scrollPaneHabitacionServicio = new JScrollPane(habitacionServicioTable);
		scrollPaneHabitacionServicio.setSize(178, 86);
		scrollPaneHabitacionServicio.setLocation(511, 317);
		scrollPaneHabitacionServicio.setPreferredSize(new Dimension(500, 300));
		frame.getContentPane().add(scrollPaneHabitacionServicio);

		habitacionServicioTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = habitacionServicioTable.getSelectedRow();
				TableModel model = habitacionServicioTable.getModel();

				txtIdServicio.setText(model.getValueAt(index, 0).toString());
				txtIdHabitacion.setText(model.getValueAt(index, 1).toString());

			}
		});

		cargarTablaHabitacionServicio();

		// MUCHOS A MUCHOS
		JButton btnCrearMuchos = new JButton("Crear Habitacion-Servicio");
		btnCrearMuchos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServicioDAO sd = new ServicioDAO();
				DefaultTableModel model = (DefaultTableModel) habitacionServicioTable.getModel();

				int habitacionSelectedRow = habitacionTable.getSelectedRow();
				int servicioSelectedRow = servicioTable.getSelectedRow();

				if (habitacionSelectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Seleccione una habitacion de tabla habitacion.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (servicioSelectedRow == -1) {
					JOptionPane.showMessageDialog(null, "Seleccione un servicio de tabla servicio.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (habitacionSelectedRow != -1 && servicioSelectedRow != -1) {
					int idHabitacion = (int) habitacionTable.getValueAt(habitacionSelectedRow, 0);
					int idServicio = (int) servicioTable.getValueAt(servicioSelectedRow, 0);

					Habitacion h = hd.selectHabitacionbyId(idHabitacion);
					Servicio s = sd.selectServiciobyId(idServicio);

					if (h != null && s != null) {
						if (!s.getHabitaciones().contains(h)) {

							s.getHabitaciones().add(h);
							sd.updateServicio(s);

							Object[] rowData = { h.getIdHabitacion(), s.getIdServicio() };
							model.addRow(rowData);
							JOptionPane.showMessageDialog(null, "Servicio-Habitacion creado");

							cargarTablaHabitacionServicio();
						} else {
							JOptionPane.showMessageDialog(null,
									"La habitacion seleccionada ya esta asociada al cliente.", "Advertencia",
									JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente y una habitacion",
								"Advertencia", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnCrearMuchos.setBounds(699, 314, 184, 23);
		frame.getContentPane().add(btnCrearMuchos);

		JButton btnBorrarMuchos = new JButton("Borrar Habitacion-Servicio");
		btnBorrarMuchos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServicioDAO sd = new ServicioDAO();
				DefaultTableModel model = (DefaultTableModel) habitacionServicioTable.getModel();

				int habitacionServicioSelectedRow = habitacionServicioTable.getSelectedRow();
				if (habitacionServicioSelectedRow != -1) {
					int idHabitacion = (int) habitacionServicioTable.getValueAt(habitacionServicioSelectedRow, 0);
					int idServicio = (int) habitacionServicioTable.getValueAt(habitacionServicioSelectedRow, 1);

					Habitacion h = hd.selectHabitacionbyId(idHabitacion);
					Servicio s = sd.selectServiciobyId(idServicio);
					if (h != null && s != null) {

						s.getHabitaciones().remove(h);

						sd.updateServicio(s);
						sd.deleteServicio(s.getIdServicio());

						model.removeRow(habitacionServicioSelectedRow);

						JOptionPane.showMessageDialog(null, "Servicio-Habitacion borrado");
						cargarTablaHabitacionServicio();
					} else {
						JOptionPane.showMessageDialog(null,
								"No se pudo encontrar la relacion entre Habitacion y Servicio.", "Advertencia",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Seleccione una fila para borrar.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnBorrarMuchos.setBounds(699, 380, 184, 25);
		frame.getContentPane().add(btnBorrarMuchos);

		
		// LABELS
		JLabel lblIdHabitacion = new JLabel("IdHabitacion:");
		lblIdHabitacion.setBounds(33, 150, 100, 20);
		frame.getContentPane().add(lblIdHabitacion);

		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(33, 180, 100, 20);
		frame.getContentPane().add(lblNumero);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(33, 210, 100, 20);
		frame.getContentPane().add(lblTipo);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(33, 240, 100, 20);
		frame.getContentPane().add(lblPrecio);

		JLabel lblNewLabel = new JLabel("HABITACION");
		lblNewLabel.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 18));
		lblNewLabel.setBounds(162, 11, 193, 20);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("IdServicio");
		lblNewLabel_2.setBounds(33, 585, 49, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_5 = new JLabel("Nombre Servicio");
		lblNewLabel_5.setBounds(33, 617, 107, 14);
		frame.getContentPane().add(lblNewLabel_5);

		JLabel lblServicios = new JLabel("SERVICIOS");
		lblServicios.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 18));
		lblServicios.setBounds(144, 353, 130, 20);
		frame.getContentPane().add(lblServicios);

		JLabel lblNewLabel_6 = new JLabel("HABITACION-SERVICIO");
		lblNewLabel_6.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 18));
		lblNewLabel_6.setBounds(511, 288, 267, 18);
		frame.getContentPane().add(lblNewLabel_6);

	}
}
