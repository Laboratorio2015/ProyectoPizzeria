package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import dto.ItemMateriaPrimaDTO;
import dto.MateriaPrimaDTO;
import dto.OrdenPedidoMatPrimaDTO;
import dto.ProveedorDTO;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import presentacion.controlador.Controlador;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mxrck.autocompleter.TextAutoCompleter;

public class ordenarMatPrima extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//BOTONES
	private JButton buttonAgregarMateriaPrima;
	private JButton buttonLimpiarBuscador;
	private JButton buttonQuitarMatPrima;
	private JButton btnEnviarform;
	private JButton btnGuardarform;
	private JButton btnCancelar;
	private JButton btnVerproveedores;
	private JButton btnVermatprima;
	
	//COMBOBOX
	private JComboBox<String> comboListaProveedores;
	private JComboBox<String> comboListaCategorias;
	private JTextField textFieldBuscadorMatPrima;
	private JTextField textFieldCantMatPrima;
	private VentanaPrincipal ventanaPrincipal;
	private Controlador controlador;

	//TABLA
	private DefaultTableModel modeloItemsSolicitados;
	private JTable tablaItemsMateriaPrima;
	
	//Auxiliares
	private Boolean ProvBloqueado=false;
	private ProveedorDTO provSeleccionado;
	private ItemMateriaPrimaDTO itemSolicitado;
	private ArrayList<ProveedorDTO> listadoProveedores;
	private ArrayList<MateriaPrimaDTO> materiasPrimasFiltradas;
	private ArrayList<ItemMateriaPrimaDTO> listadoItemsOrdenados;
	private OrdenPedidoMatPrimaDTO nuevaOrden;
	private TextAutoCompleter textAutoAcompleter;
	private String categoriaSeleccionada;


	
	public ordenarMatPrima(VentanaPrincipal ventanaPrincipal, Controlador controlador) {
		this.ventanaPrincipal = ventanaPrincipal;
		this.controlador = controlador;
		materiasPrimasFiltradas = new ArrayList<MateriaPrimaDTO>();
		listadoItemsOrdenados = new ArrayList<ItemMateriaPrimaDTO>();
		categoriaSeleccionada="";
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 898, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textFieldBuscadorMatPrima = new JTextField();
		textFieldBuscadorMatPrima.setBounds(63, 389, 299, 25);
		contentPane.add(textFieldBuscadorMatPrima);
		textFieldBuscadorMatPrima.setColumns(10);
		
		textFieldCantMatPrima = new JTextField();
		textFieldCantMatPrima.setBounds(407, 389, 48, 25);
		contentPane.add(textFieldCantMatPrima);
		textFieldCantMatPrima.setColumns(10);
		
		comboListaProveedores = new JComboBox<String>();
		comboListaProveedores.setBounds(68, 143, 250, 25);
		contentPane.add(comboListaProveedores);
		
		comboListaCategorias = new JComboBox<String>();
		comboListaCategorias.setBounds(63, 328, 299, 25);
		contentPane.add(comboListaCategorias);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(601, 143, 250, 360);
		contentPane.add(scrollPane);
		
		tablaItemsMateriaPrima = new JTable();
		modeloItemsSolicitados = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Materia Prima", "Cantidad"
				}
			) {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, Integer.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};

		scrollPane.setViewportView(tablaItemsMateriaPrima);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon("C:\\Users\\Yanina\\Documents\\Elab de constr de Soft\\tp pizzeria\\Gui\\Recursos\\Editados\\Ventanas\\orden de materia prima.png"));
		label.setBounds(0, 11, 882, 600);
		contentPane.add(label);
		
		buttonAgregarMateriaPrima = new JButton("agregarMatPrima");
		buttonAgregarMateriaPrima.setOpaque(false);
		buttonAgregarMateriaPrima.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonAgregarMateriaPrima.setBorder(null);
		buttonAgregarMateriaPrima.setBounds(92, 439, 127, 37);
		contentPane.add(buttonAgregarMateriaPrima);
		
		buttonLimpiarBuscador = new JButton("Limpiar Busq");
		buttonLimpiarBuscador.setOpaque(false);
		buttonLimpiarBuscador.setBounds(251, 439, 149, 36);
		contentPane.add(buttonLimpiarBuscador);
		
		buttonQuitarMatPrima = new JButton("borrarMatPrima");
		buttonQuitarMatPrima.setOpaque(false);
		buttonQuitarMatPrima.setBounds(535, 264, 34, 37);
		contentPane.add(buttonQuitarMatPrima);
		
		btnEnviarform = new JButton("enviarForm");
		btnEnviarform.setOpaque(false);
		btnEnviarform.setBounds(242, 532, 120, 37);
		contentPane.add(btnEnviarform);
		
		btnGuardarform = new JButton("GuardarForm");
		btnGuardarform.setOpaque(false);
		btnGuardarform.setBounds(378, 532, 120, 37);
		contentPane.add(btnGuardarform);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setOpaque(false);
		btnCancelar.setBounds(520, 532, 127, 37);
		contentPane.add(btnCancelar);
		
		btnVerproveedores = new JButton("VerProveedores");
		btnVerproveedores.setOpaque(false);
		btnVerproveedores.setBounds(339, 127, 76, 37);
		contentPane.add(btnVerproveedores);
		
		btnVermatprima = new JButton("verMatPrima");
		btnVermatprima.setOpaque(false);
		btnVermatprima.setBounds(394, 310, 67, 42);
		contentPane.add(btnVermatprima);
	
		textAutoAcompleter = new TextAutoCompleter( textFieldBuscadorMatPrima );
		textAutoAcompleter.setMode(0);
		textAutoAcompleter.setCaseSensitive(false);
	}
	
//	public ProveedorDTO getProveedorSeleccionado() {
//		String nomProveedor = (String) comboListaProveedores.getSelectedItem();
//		if (obtenerProveedor(nomProveedor)!= null){
//			return obtenerProveedor(nomProveedor);
//		}
//		else{
//			//throw new Exception("El proveedor recibido en nulo.");
//			System.out.println("No se haya el proveedor");
//			return null;
//		}
//	}
//	
//	public ProveedorDTO obtenerProveedor(String nomProveedor){
//		for (int i= 0; i < listadoProveedores.size();i++){
//			if (nomProveedor.compareTo(listadoProveedores.get(i).getNombre()) == 0){
//				return listadoProveedores.get(i);
//			}
//		}
//		return null;
//	}

	
	
	
	//GETTERS AND SETTERS.
	public JButton getButtonAgregarMateriaPrima() {
		return buttonAgregarMateriaPrima;
	}

	public void setButtonAgregarMateriaPrima(JButton buttonAgregarMateriaPrima) {
		this.buttonAgregarMateriaPrima = buttonAgregarMateriaPrima;
	}

	public JButton getButtonLimpiarBuscador() {
		return buttonLimpiarBuscador;
	}

	public void setButtonLimpiarBuscador(JButton buttonLimpiarBuscador) {
		this.buttonLimpiarBuscador = buttonLimpiarBuscador;
	}

	public JButton getButtonQuitarMatPrima() {
		return buttonQuitarMatPrima;
	}

	public void setButtonQuitarMatPrima(JButton buttonQuitarMatPrima) {
		this.buttonQuitarMatPrima = buttonQuitarMatPrima;
	}

	public JButton getBtnEnviarform() {
		return btnEnviarform;
	}

	public void setBtnEnviarform(JButton btnEnviarform) {
		this.btnEnviarform = btnEnviarform;
	}

	public JButton getBtnGuardarform() {
		return btnGuardarform;
	}

	public void setBtnGuardarform(JButton btnGuardarform) {
		this.btnGuardarform = btnGuardarform;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public JButton getBtnVerproveedores() {
		return btnVerproveedores;
	}

	public void setBtnVerproveedores(JButton btnVerproveedores) {
		this.btnVerproveedores = btnVerproveedores;
	}

	public JButton getBtnVermatprima() {
		return btnVermatprima;
	}

	public void setBtnVermatprima(JButton btnVermatprima) {
		this.btnVermatprima = btnVermatprima;
	}

	public JComboBox<String> getComboListaProveedores() {
		return comboListaProveedores;
	}

	public void setComboListaProveedores(JComboBox<String> comboListaProveedores) {
		this.comboListaProveedores = comboListaProveedores;
	}

	public JComboBox<String> getComboListaCategorias() {
		return comboListaCategorias;
	}

	public void setComboListaCategorias(JComboBox<String> comboListaCategorias) {
		this.comboListaCategorias = comboListaCategorias;
	}

	public JTextField getTextFieldBuscadorMatPrima() {
		return textFieldBuscadorMatPrima;
	}

	public void setTextFieldBuscadorMatPrima(JTextField textFieldBuscadorMatPrima) {
		this.textFieldBuscadorMatPrima = textFieldBuscadorMatPrima;
	}

	public JTextField getTextFieldCantMatPrima() {
		return textFieldCantMatPrima;
	}

	public void setTextFieldCantMatPrima(JTextField textFieldCantMatPrima) {
		this.textFieldCantMatPrima = textFieldCantMatPrima;
	}

	public Boolean getProvBloqueado() {
		return ProvBloqueado;
	}

	public void setProvBloqueado(Boolean provBloqueado) {
		ProvBloqueado = provBloqueado;
	}

	public ItemMateriaPrimaDTO getItemSolicitado() {
		return itemSolicitado;
	}

	public void setItemSolicitado(ItemMateriaPrimaDTO itemSolicitado) {
		this.itemSolicitado = itemSolicitado;
	}

	public ArrayList<MateriaPrimaDTO> getMateriasPrimasFiltradas() {
		return materiasPrimasFiltradas;
	}

	public void setMateriasPrimasFiltradas(
			ArrayList<MateriaPrimaDTO> materiasPrimasFiltradas) {
		this.materiasPrimasFiltradas = materiasPrimasFiltradas;
	}

	public ArrayList<ProveedorDTO> getListadoProveedores() {
		return listadoProveedores;
	}

	public void setListadoProveedores(ArrayList<ProveedorDTO> listadoProveedores) {
		this.listadoProveedores = listadoProveedores;
	}

	public DefaultTableModel getModeloItemsSolicitados() {
		return modeloItemsSolicitados;
	}

	public void setModeloItemsSolicitados(DefaultTableModel modeloItemsSolicitados) {
		this.modeloItemsSolicitados = modeloItemsSolicitados;
	}

	public JTable getTablaItemsMateriaPrima() {
		return tablaItemsMateriaPrima;
	}

	public void setTablaItemsMateriaPrima(JTable tablaItemsMateriaPrima) {
		this.tablaItemsMateriaPrima = tablaItemsMateriaPrima;
	}

	public ArrayList<ItemMateriaPrimaDTO> getListadoItemsOrdenados() {
		return listadoItemsOrdenados;
	}

	public void setListadoItemsOrdenados(
			ArrayList<ItemMateriaPrimaDTO> listadoItemsOrdenados) {
		this.listadoItemsOrdenados = listadoItemsOrdenados;
	}

	public OrdenPedidoMatPrimaDTO getNuevaOrden() {
		return nuevaOrden;
	}

	public void setNuevaOrden(OrdenPedidoMatPrimaDTO nuevaOrden) {
		this.nuevaOrden = nuevaOrden;
	}

	public ProveedorDTO getProvSeleccionado() {
		return provSeleccionado;
	}

	public void setProvSeleccionado(ProveedorDTO provSeleccionado) {
		this.provSeleccionado = provSeleccionado;
	}

	public TextAutoCompleter getTextAutoAcompleter() {
		return textAutoAcompleter;
	}

	public void setTextAutoAcompleter(TextAutoCompleter textAutoAcompleter) {
		this.textAutoAcompleter = textAutoAcompleter;
	}

	public void setCategoriaSeleccionada(String string) {
		categoriaSeleccionada=string;
	}

	public String getCategoriaSeleccionada() {
		return categoriaSeleccionada;
	}

	public void actualizarModelo() {
		this.tablaItemsMateriaPrima.setModel(modeloItemsSolicitados);
	}
}
