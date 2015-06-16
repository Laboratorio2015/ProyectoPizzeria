package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;
import java.util.Iterator;

import javax.swing.table.DefaultTableModel;

import presentacion.controlador.Controlador;

import dto.CategoriaDTO;
import dto.MateriaPrimaDTO;

import javax.swing.DefaultComboBoxModel;

public class matPrimaBajaModificacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDenominacion;
	private JComboBox<String> comboBoxCategoriasFiltro;
	private JComboBox<String> comboBoxCategorias;
	private JButton btnGuardarUnCambio;
	private JButton btnGuardar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private DefaultTableModel modeloMatPrima;
	private Controlador controlador;
	private JTable tablaMateriasPrimas;

	@SuppressWarnings("serial")
	public matPrimaBajaModificacion(Controlador controlador) {
		this.controlador = controlador;
		setBounds(100, 100, 745, 653);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			comboBoxCategoriasFiltro = new JComboBox<String>();
			comboBoxCategoriasFiltro.setModel(new DefaultComboBoxModel<String>(new String[] {"Ver todas"}));
			comboBoxCategoriasFiltro.setBounds(31, 187, 239, 22);
			contentPanel.add(comboBoxCategoriasFiltro);
		}
		{
			comboBoxCategorias = new JComboBox<String>();
			comboBoxCategorias.setBounds(466, 279, 174, 22);
			contentPanel.add(comboBoxCategorias);
		}
		{
			tfDenominacion = new JTextField();
			tfDenominacion.setBounds(466, 227, 174, 22);
			contentPanel.add(tfDenominacion);
			tfDenominacion.setColumns(10);
		}
		{
			{
				modeloMatPrima = new DefaultTableModel(
						new Object[][] {
						},
						new String[] {
							"Materia Prima", "Categoria"
						}
					) {
						Class[] columnTypes = new Class[] {
							String.class, String.class
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
			}
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 227, 239, 274);
		contentPanel.add(scrollPane);
		
		tablaMateriasPrimas = new JTable();
		scrollPane.setViewportView(tablaMateriasPrimas);
		tablaMateriasPrimas.setModel(modeloMatPrima);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(matPrimaBajaModificacion.class.getResource("/prototipos/baja-modif de Materia Prima.png")));
		label.setBounds(0, 0, 735, 615);
		contentPanel.add(label);
		{
			btnGuardarUnCambio= new JButton("Cancel");
			btnGuardarUnCambio.setOpaque(false);
			btnGuardarUnCambio.setBounds(360, 361, 296, 40);
			contentPanel.add(btnGuardarUnCambio);
			btnGuardarUnCambio.setActionCommand("Cancel");
		}
		{
			btnGuardar= new JButton("OK");
			btnGuardar.setOpaque(false);
			btnGuardar.setBounds(341, 536, 154, 40);
			contentPanel.add(btnGuardar);
			btnGuardar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnGuardar);
		}
		{
			JButton btnCancelar = new JButton("OK");
			btnCancelar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					dispose();
				}
			});
			btnCancelar.setOpaque(false);
			btnCancelar.setActionCommand("OK");
			btnCancelar.setBounds(536, 536, 154, 40);
			contentPanel.add(btnCancelar);
		}
		{
			btnEditar= new JButton("Cancel");
			btnEditar.setOpaque(false);
			btnEditar.setActionCommand("Cancel");
			btnEditar.setBounds(67, 524, 33, 34);
			contentPanel.add(btnEditar);
		}
		{
			btnEliminar= new JButton("Cancel");
			btnEliminar.setOpaque(false);
			btnEliminar.setActionCommand("Cancel");
			btnEliminar.setBounds(173, 524, 38, 34);
			contentPanel.add(btnEliminar);
		}
	}

	public void cargarCategoriasFiltro(){
		comboBoxCategorias.removeAllItems();
		Iterator<CategoriaDTO> iterador = this.controlador.getCategoria().obtenerCategorias().iterator();
		while (iterador.hasNext()){
			CategoriaDTO elemento = iterador.next();
			comboBoxCategoriasFiltro.addItem(elemento.getDenominacion());
		}
	}
	
	public void filtrarTabla(String nomCategoria){
		
		resetearModeloMp();
		if (nomCategoria.compareTo("Ver todas")!=0){
			Iterator<MateriaPrimaDTO> iterador = this.controlador.getMateriasPrimas().obtenerMatPrimas().iterator();
			while (iterador.hasNext()){
				MateriaPrimaDTO elemento = iterador.next();
				if (!elemento.getFueeliminado() && elemento.getCategoria().getDenominacion().trim().compareTo(nomCategoria)==0){
					getModeloMatPrima().addRow(new Object[] {elemento.getNombre().replace("_", " ").trim(), elemento.getCategoria().getDenominacion().trim()});
				}
			}
		}
		else{
			resetearModeloMp();
			Iterator<MateriaPrimaDTO> iterador = this.controlador.getMateriasPrimas().obtenerMatPrimas().iterator();
			while (iterador.hasNext()){
				MateriaPrimaDTO elemento = iterador.next();
				if (!elemento.getFueeliminado())
					this.modeloMatPrima.addRow(new Object[] {elemento.getNombre().replace("_", " ").trim(), elemento.getCategoria().getDenominacion().trim()});
			}
		}
		tablaMateriasPrimas.setModel(modeloMatPrima);
	}

	public void cargarCategorias(String catMPSelecc){
		//Carga el combobox con categorias q no sean iguales a la de la materia prima seleccionada.
		comboBoxCategorias.removeAllItems();
		Iterator<CategoriaDTO> iterador = this.controlador.getCategoria().obtenerCategorias().iterator();
		while (iterador.hasNext()){
			CategoriaDTO elemento = iterador.next();{
				comboBoxCategorias.addItem(elemento.getDenominacion().trim());
			}
		}
		comboBoxCategorias.setSelectedItem(catMPSelecc);
	}
	
	@SuppressWarnings("serial")
	private void resetearModeloMp() {
		modeloMatPrima = new DefaultTableModel(
		new Object[][] {
		},
		new String[] {
			"Materia Prima", "Categoria"
		}
	) {
		Class[] columnTypes = new Class[] {
			String.class, String.class
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
	tablaMateriasPrimas.setModel(modeloMatPrima);
}
		

	public JTextField getTfDenominacion() {
		return tfDenominacion;
	}

	public void setTfDenominacion(JTextField tfDenominacion) {
		this.tfDenominacion = tfDenominacion;
	}

	public JTable getTable() {
		return tablaMateriasPrimas;
	}

	public void setTable(JTable table) {
		this.tablaMateriasPrimas = table;
	}


	public JComboBox getComboBoxCategoriasTabla() {
		return comboBoxCategoriasFiltro;
	}

	public void setComboBoxCategoriasTabla(JComboBox comboBoxCategoriasTabla) {
		this.comboBoxCategoriasFiltro = comboBoxCategoriasTabla;
	}

	public JComboBox getComboBoxCategoriaUnObjeto() {
		return comboBoxCategorias;
	}

	public void setComboBoxCategoriaUnObjeto(JComboBox comboBoxCategoriaUnObjeto) {
		this.comboBoxCategorias = comboBoxCategoriaUnObjeto;
	}

	public JButton getBtnGuardarUnCambio() {
		return btnGuardarUnCambio;
	}

	public void setBtnGuardarUnCambio(JButton btnGuardarUnCambio) {
		this.btnGuardarUnCambio = btnGuardarUnCambio;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public void setBtnEditar(JButton btnEditar) {
		this.btnEditar = btnEditar;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public JTable getTableMatPrimas() {
		return tablaMateriasPrimas;
	}

	public void setTableMatPrimas(JTable tableMatPrimas) {
		this.tablaMateriasPrimas = tableMatPrimas;
	}

	public JComboBox getComboBoxCategoriasFiltro() {
		return comboBoxCategoriasFiltro;
	}

	public void setComboBoxCategoriasFiltro(JComboBox comboBoxCategoriasFiltro) {
		this.comboBoxCategoriasFiltro = comboBoxCategoriasFiltro;
	}

	public JComboBox<String> getComboBoxCategorias() {
		return comboBoxCategorias;
	}

	public void setComboBoxCategorias(JComboBox<String> comboBoxCategorias) {
		this.comboBoxCategorias = comboBoxCategorias;
	}

	public DefaultTableModel getModeloMatPrima() {
		return modeloMatPrima;
	}

	public void setModeloMatPrima(DefaultTableModel modeloMatPrima) {
		this.modeloMatPrima = modeloMatPrima;
	}

	public void cargarMateriasPrimasFiltradas(String string) {
		
		
	}
}
