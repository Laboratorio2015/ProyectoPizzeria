package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dto.CategoriaDTO;
import dto.ProveedorDTO;

import presentacion.controlador.Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

public class proveedorBajaModificacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableProveedor;
	private JTextField tfDenominacion;
	private JTextField tfTelefono;
	private JTextField tfEmail;
	private JTextField tfDireccion;
	private DefaultTableModel model;
	private DefaultTableModel model1;
	private JTextPane textPaneComentario;
	private JComboBox<String> comboBoxCategorias;
	private JButton btnQuitarcat;
	private Controlador control;
	private  String[] nombreColumnasProveedor = {"Nombre"};
	private String[] nombreColumnasMatPrimas = {"Nombre"};
	private JButton btnQuitar ;
	private JButton btnGuardar;
	private JTextField tfNombreContacto;
	private JTable tablaCategorias;
	private DefaultTableModel modeloCategorias;
	private JButton btnAddcategoria;

	@SuppressWarnings("serial")
	public proveedorBajaModificacion(final Controlador control) {
		setBounds(100, 100, 809, 772);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 196, 237, 310);
		contentPanel.add(scrollPane);
		this.control=control;
		model = new DefaultTableModel(null,nombreColumnasProveedor);
		model1=new DefaultTableModel(null, nombreColumnasMatPrimas);
		
		tableProveedor = new JTable(model){
		@Override
		public boolean isCellEditable(int rowIndex, int colIndex) {
	        return false; //desabilita la edicion de las celdas
	    }};
		tableProveedor.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				ProveedorDTO auxi=control.getProveedor().buscarProveedor(model.getValueAt(tableProveedor.getSelectedRow(), 0).toString());
				agregarDatos(auxi);
			}
		});
	    		
		scrollPane.setViewportView(tableProveedor);
		
		tfDenominacion = new JTextField();
		tfDenominacion.setEditable(true);
		tfDenominacion.setBounds(352, 225, 174, 22);
		contentPanel.add(tfDenominacion);
		tfDenominacion.setColumns(10);
		
		tfTelefono = new JTextField();
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(352, 282, 174, 22);
		contentPanel.add(tfTelefono);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(352, 395, 290, 22);
		contentPanel.add(tfEmail);
		
		tfDireccion = new JTextField();
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(352, 336, 290, 22);
		contentPanel.add(tfDireccion);
		
		tfNombreContacto = new JTextField();
		tfNombreContacto.setBounds(536, 225, 186, 22);
		contentPanel.add(tfNombreContacto);
		tfNombreContacto.setColumns(10);
		
		comboBoxCategorias = new JComboBox<String>();
		comboBoxCategorias.setBounds(536, 281, 186, 22);
		contentPanel.add(comboBoxCategorias);
		
		textPaneComentario = new JTextPane();
		textPaneComentario.setBounds(352, 442, 290, 49);
		contentPanel.add(textPaneComentario);
		
		btnQuitarcat = new JButton("quitarCat");
		btnQuitarcat.setOpaque(false);
		btnQuitarcat.setBounds(317, 557, 38, 23);
		contentPanel.add(btnQuitarcat);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(361, 545, 359, 62);
		contentPanel.add(scrollPane_1);
		
		tablaCategorias = new JTable();
		modeloCategorias = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Categoria", "idCategoria"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		tablaCategorias.setModel(modeloCategorias);	
		scrollPane_1.setViewportView(tablaCategorias);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(proveedorBajaModificacion.class.getResource("/prototipos/baja-modif de Proveedor.png")));
		label.setBounds(0, 0, 960, 749);
		contentPanel.add(label);
		{
			JButton btnFinalizar = new JButton("OK");
			btnFinalizar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnFinalizar.setOpaque(false);
			btnFinalizar.setBounds(271, 706, 151, 36);
			contentPanel.add(btnFinalizar);
			btnFinalizar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnFinalizar);
		}
		{
			btnQuitar= new JButton("Cancel");
			btnQuitar.setOpaque(false);
			btnQuitar.setBounds(36, 544, 53, 49);
			contentPanel.add(btnQuitar);
			btnQuitar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnQuitar.setActionCommand("Cancel");
		}
		
		btnGuardar = new JButton("guardar modificaciones");
		btnGuardar.setOpaque(false);
		btnGuardar.setBounds(447, 633, 258, 49);
		contentPanel.add(btnGuardar);
		
		btnAddcategoria = new JButton("addCategoria");
		btnAddcategoria.setBounds(725, 282, 43, 23);
		contentPanel.add(btnAddcategoria);
	}

	public JTable getTableProveedor() {
		return tableProveedor;
	}

	public void setTableProveedor(JTable tableProveedor) {
		this.tableProveedor = tableProveedor;
	}


	public JTextField getTfDenominacion() {
		return tfDenominacion;
	}

	public void setTfDenominacion(JTextField tfDenominacion) {
		this.tfDenominacion = tfDenominacion;
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

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public String[] getNombreColumnasProveedor() {
		return nombreColumnasProveedor;
	}
	

	public DefaultTableModel getModel1() {
		return model1;
	}

	public void setModel1(DefaultTableModel model1) {
		this.model1 = model1;
	}

	public String[] getNombreColumnasMatPrimas() {
		return nombreColumnasMatPrimas;
	}

	public void setNombreColumnasMatPrimas(String[] nombreColumnasMatPrimas) {
		this.nombreColumnasMatPrimas = nombreColumnasMatPrimas;
	}

	public void setNombreColumnasProveedor(String[] nombreColumnasProveedor) {
		this.nombreColumnasProveedor = nombreColumnasProveedor;
	}
	
	public void agregarDatos(ProveedorDTO aux)
	{
		tfDenominacion.setText(aux.getNombre());
		tfDireccion.setText(aux.getDireccion());
		tfEmail.setText(aux.getEmail());
		tfTelefono.setText(aux.getTelefono());
		tfNombreContacto.setText(aux.getNombrecontacto());
		textPaneComentario.setText(aux.getComentario());
		comboBoxCategorias.removeAllItems();
		resetearModelo();
		
		Iterator<CategoriaDTO> iteradorCat = control.getCategoria().obtenerCategorias().iterator();
		while (iteradorCat.hasNext()){
			CategoriaDTO elemento = iteradorCat.next();
			if (!aux.isCategoria(elemento.getDenominacion())){
				comboBoxCategorias.addItem(elemento.getDenominacion());
			}
			else{
				modeloCategorias.addRow(new Object[] {elemento.getDenominacion(), elemento.getIdCategoria()});
			}
		}
		tablaCategorias.setModel(modeloCategorias);
	}

	public JButton getBtnQuitar() {
		return btnQuitar;
	}

	public void setBtnQuitar(JButton btnQuitar) {
		this.btnQuitar = btnQuitar;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JTextPane getTextPaneComentario() {
		return textPaneComentario;
	}

	public void setTextPaneComentario(JTextPane textPaneComentario) {
		this.textPaneComentario = textPaneComentario;
	}

	public JComboBox<String> getComboBoxCategorias() {
		return comboBoxCategorias;
	}

	public void setComboBoxCategorias(JComboBox<String> comboBoxCategorias) {
		this.comboBoxCategorias = comboBoxCategorias;
	}

	public JButton getBtnQuitarcat() {
		return btnQuitarcat;
	}

	public void setBtnQuitarcat(JButton btnQuitarcat) {
		this.btnQuitarcat = btnQuitarcat;
	}

	public JTextField getTfNombreContacto() {
		return tfNombreContacto;
	}

	public void setTfNombreContacto(JTextField tfNombreContacto) {
		this.tfNombreContacto = tfNombreContacto;
	}

	public JTable getTablaCategorias() {
		return tablaCategorias;
	}

	public void setTablaCategorias(JTable tablaCategorias) {
		this.tablaCategorias = tablaCategorias;
	}

	public DefaultTableModel getModeloCategorias() {
		return modeloCategorias;
	}

	public void setModeloCategorias(DefaultTableModel modeloCategorias) {
		this.modeloCategorias = modeloCategorias;
	}

	@SuppressWarnings("serial")
	public void resetearModelo() {
		modeloCategorias = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Categoria", "idCategoria"
				}
				) {
			Class[] columnTypes = new Class[] {
					String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		tablaCategorias.setModel(modeloCategorias);
	}

	public void limpiarCampos() {
		getTfDenominacion().setText("");
		getTfDireccion().setText("");
		getTfEmail().setText("");
		getTfTelefono().setText("");
		resetearModelo();
		getTfNombreContacto().setText("");
		getTextPaneComentario().setText("");
	}

	public JButton getBtnAddcategoria() {
		return btnAddcategoria;
	}

	public void setBtnAddcategoria(JButton btnAddcategoria) {
		this.btnAddcategoria = btnAddcategoria;
	}
}
