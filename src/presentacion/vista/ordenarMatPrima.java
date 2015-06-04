package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.ImageIcon;

import presentacion.controlador.Controlador;
import dao.*;
import dto.*;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

import modelo.MatPrimas;

// poner funcionalidad al boton seleccionar proveedor, actualmente, se cargaria automaticamente al seleccionar el prov desde el combo box.
public class ordenarMatPrima extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private VentanaPrincipal _padre;

	private JTextField txFieldBuscarProveedor;
	private JTextField textFieldBusquedaMatPrima;
	private JTextField textFieldCantMatPrima;
	private JTextField txtFieldCategProveed;
	private JTextField textFieldTelProveed;
	private JTextField textFieldDireccProveed;
	private JTable tablaListaMatPrima;
	private JButton buttonGuardarOrden;
	private JButton buttonSeleccProveed;
	private JButton buttonAgregarMatPrima;
	private JButton buttonQuitarMatPrimaSeleccionada;
	private JComboBox<String> comboBoxProveedores;
	private JComboBox<String> comboBoxCategorias;
	private ProveedorDTO provSeleccionado;
	private DefaultTableModel modeloMatPrima;
	
	private ProveedorDAO conexionProveedor;
	private ArrayList<ProveedorDTO> listadoProveedores;
	private ArrayList<MateriaPrimaDTO> materiasPrimasFiltradas;

	private ordenarMatPrima _matPrima;
	private MateriaPrimaDAO conexionMatPrima;
	private ArrayList<MateriaPrimaDTO> listadoMatPrimas;


	@SuppressWarnings("serial")
	public ordenarMatPrima(VentanaPrincipal padre, final Controlador control) {
		setModal(true);
		padre=_padre;
		_matPrima=this;
		materiasPrimasFiltradas = new ArrayList<MateriaPrimaDTO>();
		setMinimumSize(new Dimension(700, 600));
		setBounds(300, 50, 700, 641);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		comboBoxProveedores = new JComboBox<String>();
		comboBoxProveedores.setBounds(120, 135, 253, 25);
		comboBoxProveedores.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				try {
					provSeleccionado = getProveedorSeleccionado();
					cargarDatosProveedor(provSeleccionado);
					cargarCategorias();
					cargarMatPrimaXcategoria();
				} catch (Exception e) {
					System.out.println("El proveedor recibido es nulo o no se puede encontrar.");
					e.printStackTrace();
				}
			}
		});
		contentPanel.add(comboBoxProveedores);
		
		comboBoxCategorias = new JComboBox();
		comboBoxCategorias.setBounds(100, 272, 300, 23);
		contentPanel.add(comboBoxCategorias);
		
		txFieldBuscarProveedor = new JTextField();
		txFieldBuscarProveedor.setBackground(Color.LIGHT_GRAY);
		txFieldBuscarProveedor.setBounds(120, 135, 253, 25);
		contentPanel.add(txFieldBuscarProveedor);
		txFieldBuscarProveedor.setColumns(10);
		
		textFieldBusquedaMatPrima = new JTextField();
		textFieldBusquedaMatPrima.setBackground(Color.LIGHT_GRAY);
		textFieldBusquedaMatPrima.setBounds(100, 319, 311, 25);
		contentPanel.add(textFieldBusquedaMatPrima);
		textFieldBusquedaMatPrima.setColumns(10);
		
		textFieldCantMatPrima = new JTextField();
		textFieldCantMatPrima.setBounds(427, 319, 55, 25);
		contentPanel.add(textFieldCantMatPrima);
		textFieldCantMatPrima.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(120, 405, 526, 114);
		contentPanel.add(scrollPane);
		
		tablaListaMatPrima = new JTable();
		modeloMatPrima = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Materia Prima", "Cantidad"
				}
			) {
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, Integer.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
		};
		tablaListaMatPrima.setModel(modeloMatPrima);
		scrollPane.setViewportView(tablaListaMatPrima);
		
		JLabel label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/**Tomar el nombre cargado en el txt field de mat prima, buscar el objeto MateriaPrima con ese nombre y guardarlo.
				 * Creo un nuevo objeto que se llama itemMatPrima(MatPrima, Cantidad). Y lo uso para almacenar el item guardado con la cantidad
				 *ingresada por el usuario.
				 * */
				
			}
		});
		label.setOpaque(true);
		label.setBounds(0, 0, 684, 600);
		contentPanel.add(label);
		label.setIcon(new ImageIcon("C:\\Users\\Yanina\\Documents\\Elab de constr de Soft\\tp pizzeria\\Gui\\Recursos\\Editados\\Ventanas\\orden de materia prima.png"));
		{
			JButton okButton = new JButton("OK");
			okButton.setOpaque(false);
			okButton.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					/**se debe crear en la base de datos una tabla con nombre
					OrdenDePedido, con los campos idOrden,idMatPrima,idProveed,fecha,hora.
					Y dentro de esta tabla guardar el registro de orden de pedido creada.
					Luego de guardarla, tengo q tomar toda esta informacion, para elaborar un pdf,
					y enviarlo adjunto al mail indicado por el proveedor seleccionado.
					*/
					//dispose();
				}
			});
			okButton.setBounds(155, 540, 116, 38);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setOpaque(false);
			cancelButton.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					dispose();
				}
			});
			cancelButton.setBounds(427, 540, 125, 38);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		
		buttonGuardarOrden = new JButton("Guardar");
		buttonGuardarOrden.setOpaque(false);
		buttonGuardarOrden.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				/**se debe crear en la base de datos una tabla con nombre
				OrdenDePedido, con los campos idOrden,idMatPrima,idProveed,fecha,hora.
				Y dentro de esta tabla guardar el registro de orden de pedido creada.
				*/
				//dispose();
			}
		});
		buttonGuardarOrden.setActionCommand("OK");
		buttonGuardarOrden.setBounds(291, 540, 116, 38);
		contentPanel.add(buttonGuardarOrden);
		
		buttonSeleccProveed = new JButton("");
		buttonSeleccProveed.setBounds(624, 135, 33, 38);
		contentPanel.add(buttonSeleccProveed);
		
		buttonAgregarMatPrima = new JButton("");
		buttonAgregarMatPrima.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ItemMateriaPrimaDTO itemSolicitado = new ItemMateriaPrimaDTO(getMatPrimaSeleccionada(), Integer.getInteger(textFieldCantMatPrima.getText()));
				modeloMatPrima.addColumn(new Object[] {itemSolicitado.getItemMatPrima().getNombre(), itemSolicitado.getCantidad()});
			}
		});
		buttonAgregarMatPrima.setBounds(555, 295, 33, 38);
		contentPanel.add(buttonAgregarMatPrima);
		
		buttonQuitarMatPrimaSeleccionada = new JButton("");
		buttonQuitarMatPrimaSeleccionada.setBounds(50, 438, 39, 38);
		contentPanel.add(buttonQuitarMatPrimaSeleccionada);
		
		txtFieldCategProveed = new JTextField();
		txtFieldCategProveed.setBounds(463, 112, 107, 20);
		contentPanel.add(txtFieldCategProveed);
		txtFieldCategProveed.setColumns(10);
		
		textFieldTelProveed = new JTextField();
		textFieldTelProveed.setColumns(10);
		textFieldTelProveed.setBounds(463, 137, 107, 20);
		contentPanel.add(textFieldTelProveed);
		
		textFieldDireccProveed = new JTextField();
		textFieldDireccProveed.setColumns(10);
		textFieldDireccProveed.setBounds(463, 156, 107, 20);
		contentPanel.add(textFieldDireccProveed);
		
		listadoProveedores = (ArrayList<ProveedorDTO>) conexionProveedor.readAll(); //ArrayList Con todos los proveedores.
		listadoMatPrimas = (ArrayList<MateriaPrimaDTO>) conexionMatPrima.readAll();
		cargarProveedores();
	}
	
	protected MateriaPrimaDTO getMatPrimaSeleccionada() {
		String nomMatPrimaSeleccionada = textFieldBusquedaMatPrima.getText();
		for (int  i= 0; i < materiasPrimasFiltradas.size();i++){
			if ( nomMatPrimaSeleccionada.compareTo(materiasPrimasFiltradas.get(i).getNombre())==0 ){
				return materiasPrimasFiltradas.get(i);
			}
		}
		return null;
	}

	protected void cargarMatPrimaXcategoria() {
		/** Tomo cada elemento q compone el comboBox categorias, con nada nombre de categoria pregunto por cada item*/
		materiasPrimasFiltradas = new ArrayList<MateriaPrimaDTO>();
		for (int i = 0; i < listadoMatPrimas.size();i++){
			if ( contieneCategoria(listadoMatPrimas.get(i).getCategoria())){
				materiasPrimasFiltradas.add(listadoMatPrimas.get(i));
			}
		}
	}

	private boolean contieneCategoria(String nomCategoria) {
		for (int  i= 0; i < comboBoxCategorias.getComponentCount();i++){
			if (comboBoxCategorias.getItemAt(i).compareTo(nomCategoria)== 0){
				return true;
			}
		}
		return false;
	}

	protected void cargarCategorias() {
		//Cuando el prov tenga un arraylist de Categorias, activo esta funcion>
		/**
		 for (int i = 0; i < proSeleccionado.getCategorias().size();i++){
		  		comboBoxCategorias.addItem(provSeleccionado.get(i));
		 *  */	
		comboBoxCategorias.addItem(provSeleccionado.getCategoria());
	}

	protected void cargarDatosProveedor(ProveedorDTO provSeleccionado) {
		textFieldDireccProveed.setText(provSeleccionado.getDireccion());
		textFieldTelProveed.setText(provSeleccionado.getTelefono());
		//FALTA CARGAR CATEGORIAS ASOCIADAS
	}

	public void cargarProveedores(){
		for (int i=0; i< listadoProveedores.size();i++){
			comboBoxProveedores.addItem(listadoProveedores.get(i).getNombre());
		}
	}
	
	public ProveedorDTO getProveedorSeleccionado() throws Exception{
		String nomProveedor = (String) comboBoxProveedores.getSelectedItem();
		return getProveedor(nomProveedor);
	}
	
	public ProveedorDTO getProveedor(String nomProveedor){
		for (int i= 0; i < listadoProveedores.size();i++){
			if (nomProveedor.compareTo(listadoProveedores.get(i).getNombre()) == 0){
				return listadoProveedores.get(i);
			}
		}
		return null;
	}
}
