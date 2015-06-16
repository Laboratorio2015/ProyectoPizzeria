package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dto.CategoriaDTO;
import javax.swing.table.DefaultTableModel;

public class proveedorAlta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDenominacion;
	private JTextField tfTelefono;
	private JTextField tfEmail;
	private JTextField tfDireccion;
	private JButton btnCancelar;
	private JButton btnRegistrar;
	private JButton btnaddCategoria;
	private JTextField tfComentario;
	private JTable tableCategorias;
	private DefaultTableModel modeloCategorias;
	private JTextField tfNombreContacto;
	private JComboBox<String> comboBoxCategorias;
	private JButton btnQuitarcateg;


	@SuppressWarnings("serial")
	public proveedorAlta() {
		setBounds(100, 100, 695, 620);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			tfDenominacion = new JTextField();
			tfDenominacion.setBounds(177, 170, 289, 22);
			contentPanel.add(tfDenominacion);
			tfDenominacion.setColumns(10);
		}
		{
			tfTelefono = new JTextField();
			tfTelefono.setColumns(10);
			tfTelefono.setBounds(177, 282, 289, 22);
			contentPanel.add(tfTelefono);
		}
		{
			tfEmail = new JTextField();
			tfEmail.setColumns(10);
			tfEmail.setBounds(176, 356, 291, 22);
			contentPanel.add(tfEmail);
		}
		
		tfDireccion = new JTextField();
		tfDireccion.setBounds(177, 318, 289, 22);
		contentPanel.add(tfDireccion);
		tfDireccion.setColumns(10);
		
		comboBoxCategorias= new JComboBox();
		comboBoxCategorias.setModel(new DefaultComboBoxModel(new String[] {"(Agregar Categorias)"}));
		comboBoxCategorias.setBounds(176, 208, 249, 22);
		contentPanel.add(comboBoxCategorias);
		
		tfComentario = new JTextField();
		tfComentario.setBounds(175, 393, 292, 54);
		contentPanel.add(tfComentario);
		tfComentario.setColumns(10);
		
		tfNombreContacto = new JTextField();
		tfNombreContacto.setColumns(10);
		tfNombreContacto.setBounds(176, 245, 289, 22);
		contentPanel.add(tfNombreContacto);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(496, 194, 174, 200);
		contentPanel.add(scrollPane);
		
		tableCategorias = new JTable();
		modeloCategorias = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Categor\u00EDa", "idCategoria"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};

		tableCategorias.setModel(modeloCategorias);
		tableCategorias.getColumnModel().getColumn(1).setMinWidth(0);
		tableCategorias.getColumnModel().getColumn(1).setWidth(0);
		tableCategorias.getColumnModel().getColumn(1).setMaxWidth(0);
		
		scrollPane.setViewportView(tableCategorias);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(proveedorAlta.class.getResource("/prototipos/alta de Proveedor.png")));
			label.setBounds(0, 0, 679, 580);
			contentPanel.add(label);
		}
		{
			btnRegistrar= new JButton("OK");
			btnRegistrar.setOpaque(false);
			btnRegistrar.setBounds(201, 499, 165, 35);
			contentPanel.add(btnRegistrar);
			btnRegistrar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnRegistrar);
		}
		{
			btnCancelar = new JButton("cancelar");
			btnCancelar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0)
				{
					dispose();
				}
			});
			
			btnQuitarcateg = new JButton("QuitarCateg");
			btnQuitarcateg.setOpaque(false);
			btnQuitarcateg.setBounds(504, 405, 150, 30);
			contentPanel.add(btnQuitarcateg);
			btnCancelar.setOpaque(false);
			btnCancelar.setActionCommand("OK");
			btnCancelar.setBounds(430, 499, 108, 35);
			contentPanel.add(btnCancelar);
		}
		
		btnaddCategoria = new JButton("addCategoria");
		btnaddCategoria.setOpaque(false);
		btnaddCategoria.setBounds(435, 208, 31, 23);
		contentPanel.add(btnaddCategoria);
	}


	public JTextField getTfDenominacion() {
		return tfDenominacion;
	}


	public void setTfDenominacion(JTextField tfDenominacion) {
		this.tfDenominacion = tfDenominacion;
	}
	
	public JTextField getTfComentario() {
		return tfComentario;
	}

	public void setTfComentario(JTextField tfComentario) {
		this.tfComentario = tfComentario;
	}

	public JTextField getTfNombreContacto() {
		return tfNombreContacto;
	}

	public void setTfNombreContacto(JTextField tfNombreContacto) {
		this.tfNombreContacto = tfNombreContacto;
	}


	public JTextField getTfTelefono() {
		return tfTelefono;
	}


	public void setTfTelefono(JTextField tfTelefono) {
		this.tfTelefono = tfTelefono;
	}


	public JTextField getTfEmail() {
		return tfEmail;
	}


	public void setTfEmail(JTextField tfEmail) {
		this.tfEmail = tfEmail;
	}


	public JTextField getTfDireccion() {
		return tfDireccion;
	}


	public void setTfDireccion(JTextField tfDireccion) {
		this.tfDireccion = tfDireccion;
	}


	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}


	public void setBtnRegistrar(JButton btnRegistrar) {
		this.btnRegistrar = btnRegistrar;
	}


	public void cargarCategorias(List<CategoriaDTO> obtenerCategorias) {
		Iterator<CategoriaDTO> iterador = obtenerCategorias.iterator();
		while (iterador.hasNext()){
			CategoriaDTO elemento = iterador.next();
			if (!elemento.getFueEliminado())
				this.comboBoxCategorias.addItem(elemento.getDenominacion());
		}
	}


	public JButton getBtnQuitarcateg() {
		return btnQuitarcateg;
	}


	public void setBtnQuitarcateg(JButton btnQuitarcateg) {
		this.btnQuitarcateg = btnQuitarcateg;
	}


	public JTable getTablaCategorias() {
		return tableCategorias;
	}


	public JButton getBtnaddCategoria() {
		return btnaddCategoria;
	}


	public void setBtnaddCategoria(JButton btnaddCategoria) {
		this.btnaddCategoria = btnaddCategoria;
	}


	public JComboBox<String> getComboBoxCategorias() {
		return comboBoxCategorias;
	}


	public void setComboBoxCategorias(JComboBox<String> comboBoxCategorias) {
		this.comboBoxCategorias = comboBoxCategorias;
	}


	public void agregarCategoria(CategoriaDTO categoria) {
		modeloCategorias.addRow(new Object[] {categoria.getDenominacion(),categoria.getIdCategoria()});
		tableCategorias.setModel(modeloCategorias);
	}


	public DefaultTableModel getModeloCategorias() {
		return modeloCategorias;
	}


	public void setModeloCategorias(DefaultTableModel modeloCategorias) {
		this.modeloCategorias = modeloCategorias;
	}
}
