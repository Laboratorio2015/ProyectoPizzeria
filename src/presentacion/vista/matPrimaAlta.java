package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import presentacion.controlador.Controlador;

import dto.CategoriaDTO;
import dto.MateriaPrimaDTO;
import dto.ProveedorDTO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class matPrimaAlta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTable tablaMateriasPrimas;
	private DefaultTableModel modeloMatPrima;
	private JButton btnGuardar;
	private JButton btnAgregarMatPrima;
	private JButton buttonEditarMP;
	private JButton buttonBorrarMatPrima;
	private JComboBox<String> comboBoxCategoria;
	private Controlador controlador;
	
	@SuppressWarnings("serial")
	public matPrimaAlta(Controlador controlador) {
		this.controlador = controlador;
		
		setBounds(100, 100, 734, 588);
		getContentPane().setLayout(null);
		contentPanel.setBounds(597, 0, 1, 482);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JButton okButton = new JButton("OK");
			okButton.setBounds(378, 448, 47, 23);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(430, 448, 65, 23);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			tfNombre = new JTextField();
			tfNombre.setBounds(125, 244, 222, 22);
			getContentPane().add(tfNombre);
			tfNombre.setColumns(10);
		}
		{
		comboBoxCategoria= new JComboBox();
		comboBoxCategoria.setBounds(125, 285, 222, 22);
		getContentPane().add(comboBoxCategoria);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(385, 167, 243, 259);
		getContentPane().add(scrollPane);
		
		tablaMateriasPrimas = new JTable();
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
		tablaMateriasPrimas.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent arg0) 
			{
				//evento al hacer click en la tabla.
			}
		});
		
		scrollPane.setViewportView(tablaMateriasPrimas);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(matPrimaAlta.class.getResource("/prototipos/Carga de Materia Prima.png")));
			label.setBounds(0, 0, 719, 550);
			getContentPane().add(label);
		}
		
		btnAgregarMatPrima= new JButton("");
		btnAgregarMatPrima.setOpaque(false);
		btnAgregarMatPrima.setBounds(92, 352, 215, 30);
		getContentPane().add(btnAgregarMatPrima);
		
		btnGuardar= new JButton("");
		btnGuardar.setOpaque(false);
		btnGuardar.setBounds(392, 481, 109, 36);
		getContentPane().add(btnGuardar);
		
		JButton btnCancelar = new JButton("");
		btnCancelar.setOpaque(false);
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});
		btnCancelar.setBounds(548, 481, 109, 36);
		getContentPane().add(btnCancelar);
		
		buttonEditarMP = new JButton("");
		buttonEditarMP.setOpaque(false);
		buttonEditarMP.setBounds(651, 254, 33, 30);
		getContentPane().add(buttonEditarMP);
		
		buttonBorrarMatPrima = new JButton("");
		buttonBorrarMatPrima.setOpaque(false);
		buttonBorrarMatPrima.setBounds(651, 316, 41, 43);
		getContentPane().add(buttonBorrarMatPrima);
	}
	
	public void cargarOpcCategorias(MateriaPrimaDTO matPrimaSelecc){
		//Carga el combobox con categorias q no sean iguales a la de la materia prima seleccionada.
		comboBoxCategoria.removeAllItems();
		String catMPSelecc = matPrimaSelecc.getCategoria().getDenominacion();
		Iterator<CategoriaDTO> iterador = this.controlador.getCategoria().obtenerCategorias().iterator();
		while (iterador.hasNext()){
			CategoriaDTO elemento = iterador.next();
			if (elemento.getDenominacion().compareTo(catMPSelecc)!=0){
				comboBoxCategoria.addItem(elemento.getDenominacion());
			}
		}
	}

	public void cargarOpcCategorias(){
		//Carga el combobox con categorias q no sean iguales a la de la materia prima seleccionada.
		comboBoxCategoria.removeAllItems();
		Iterator<CategoriaDTO> iterador = this.controlador.getCategoria().obtenerCategorias().iterator();
		while (iterador.hasNext()){
			CategoriaDTO elemento = iterador.next();
			comboBoxCategoria.addItem(elemento.getDenominacion());
		}
	}

	@SuppressWarnings("serial")
	public void resetearTablaMatPrimas(){
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
	
	public void limpiarCampo(){
		tfNombre.setText("");
	}
	public JTextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public void setBtnGuardar(JButton btnGuardar) {
		this.btnGuardar = btnGuardar;
	}

	public JButton getBtnAgregarMatPrima() {
		return btnAgregarMatPrima;
	}

	public void setBtnAgregarMatPrima(JButton btnAgregarMatPrima) {
		this.btnAgregarMatPrima = btnAgregarMatPrima;
	}

	public JComboBox<String> getComboBoxCategoria() {
		return comboBoxCategoria;
	}


	public JButton getButtonEditarMP() {
		return buttonEditarMP;
	}

	public void setButtonEditarMP(JButton buttonEditarMP) {
		this.buttonEditarMP = buttonEditarMP;
	}

	public JButton getButtonBorrarCat() {
		return buttonBorrarMatPrima;
	}

	public void setButtonBorrarCat(JButton buttonBorrarCat) {
		this.buttonBorrarMatPrima = buttonBorrarCat;
	}

	public void setComboBoxCategoria(JComboBox<String> comboBoxCategoria) {
		this.comboBoxCategoria = comboBoxCategoria;
	}

	public JTable getTablaMateriasPrimas() {
		return tablaMateriasPrimas;
	}

	public void setTablaMateriasPrimas(JTable tablaMateriasPrimas) {
		this.tablaMateriasPrimas = tablaMateriasPrimas;
	}

	public void cargarMateriasPrimas() {
		resetearTablaMatPrimas();
		resetearTablaMatPrimas();
		Iterator<MateriaPrimaDTO> iterador = this.controlador.getMateriasPrimas().obtenerMatPrimas().iterator();
		while (iterador.hasNext()){
			MateriaPrimaDTO elemento = iterador.next();
			if (!elemento.getFueeliminado())
				this.modeloMatPrima.addRow(new Object[] {elemento.getNombre().replace("_", " ").trim(), elemento.getCategoria().getDenominacion()});
		}
		tablaMateriasPrimas.setModel(modeloMatPrima);
		limpiarCampo();
	}

	public JButton getButtonBorrarMatPrima() {
		return buttonBorrarMatPrima;
	}

	public void setButtonBorrarMatPrima(JButton buttonBorrarMatPrima) {
		this.buttonBorrarMatPrima = buttonBorrarMatPrima;
	}
}
