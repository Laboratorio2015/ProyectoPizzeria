package presentacion.vista;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import presentacion.controlador.Controlador;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

import com.mxrck.autocompleter.TextAutoCompleter;

import dto.ItemMateriaPrimaDTO;
import dto.OrdenPedidoMatPrimaDTO;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.ListSelectionModel;

public class gestionarOrdenesMatPrima extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final Controlador controlador;
	@SuppressWarnings("unused")
	private VentanaPrincipal ventanaPrincipal;
	private JButton btnAgregarOrdenMatPrima = new JButton("New button");
	private JButton btnCargarorden;
	private JButton btnBorrarorden;
	private JButton btnPagarorden;
	private JButton btnEnviarmailorden;
	private JButton btnImprimirOrden;
	private JButton btnFinalizar; 
	private JButton btnBuscar; 
	private JLabel lblOcultarOpcEliminar;
	private JLabel lblocultarOpcPago;

	private JTextField textFieldBuscadorNombre;
	private JTable tableOrdenesMatPrimas;
	private DefaultTableModel modeloOrdenesMatPrimas;
	private DefaultTableModel modeloItemsSolicitdos;
	private JComboBox<String> comboBoxFiltroOrdenes;
	private TextAutoCompleter textAutoAcompleter;
	private JTable tableItemsSolicitados;
	private JLabel lblOcultarEnviarOrden;
	
	private OrdenPedidoMatPrimaDTO ordenSeleccionada; 
	
	@SuppressWarnings("serial")
	public gestionarOrdenesMatPrima (VentanaPrincipal ventanaPrincipal, Controlador controlador){
		this.ventanaPrincipal = ventanaPrincipal;
		this.controlador = controlador;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 919, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBoxFiltroOrdenes = new JComboBox<String>();
		comboBoxFiltroOrdenes.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBoxFiltroOrdenes.setModel(new DefaultComboBoxModel<String>(new String[] {"Todas las ordenes", "Ordenes guardadas", 
				"Ordenes enviadas pendientes de pago", "Ordenes pagadas", "Ordenes rechazadas"}));
		comboBoxFiltroOrdenes.setBounds(48, 129, 253, 27);
		contentPane.add(comboBoxFiltroOrdenes);
		
		textFieldBuscadorNombre = new JTextField();
		textFieldBuscadorNombre.setBounds(48, 199, 253, 27);
		contentPane.add(textFieldBuscadorNombre);
		textFieldBuscadorNombre.setColumns(10);
		textAutoAcompleter = new TextAutoCompleter( textFieldBuscadorNombre );
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(48, 263, 259, 321);
		contentPane.add(scrollPane);

		tableOrdenesMatPrimas = new JTable();
		tableOrdenesMatPrimas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modeloOrdenesMatPrimas = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"New column", "New column", "New column"
				}
				) {
			Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		tableOrdenesMatPrimas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableOrdenesMatPrimas.setTableHeader(null);
		scrollPane.setViewportView(tableOrdenesMatPrimas);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(422, 178, 247, 342);
		contentPane.add(scrollPane_1);
		
		tableItemsSolicitados = new JTable();	
		modeloItemsSolicitdos = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		tableItemsSolicitados.setTableHeader(null);
		tableItemsSolicitados.setModel(modeloItemsSolicitdos);

		tableItemsSolicitados.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            // do some actions here, for example
	            // print first column value from selected row
	            System.out.println(tableItemsSolicitados.getValueAt(tableItemsSolicitados.getSelectedRow(), 0).toString());
	        }
	    });
		
		
		scrollPane_1.setViewportView(tableItemsSolicitados);
		
		lblocultarOpcPago = new JLabel("");
		lblocultarOpcPago.setOpaque(true);
		lblocultarOpcPago.setBackground(new Color(204, 204, 0));
		lblocultarOpcPago.setBounds(691, 349, 203, 43);
		contentPane.add(lblocultarOpcPago);
		
		lblOcultarEnviarOrden = new JLabel("");
		lblOcultarEnviarOrden.setOpaque(true);
		lblOcultarEnviarOrden.setBackground(new Color(204, 204, 0));
		lblOcultarEnviarOrden.setBounds(690, 403, 162, 52);
		contentPane.add(lblOcultarEnviarOrden);
		
		lblOcultarOpcEliminar = new JLabel("");
		lblOcultarOpcEliminar.setOpaque(true);
		lblOcultarOpcEliminar.setBackground(new Color(204, 204, 0));
		lblOcultarOpcEliminar.setBounds(680, 289, 172, 49);
		contentPane.add(lblOcultarOpcEliminar);
		
		JLabel lblFondo = new JLabel("fondo");
		lblFondo.setIcon(new ImageIcon(gestionarOrdenesMatPrima.class.getResource("/prototipos/gestor Ordenes de Pedido.png")));
		lblFondo.setBounds(10, 11, 893, 601);
		contentPane.add(lblFondo);
		
		btnBuscar = new JButton("buscar");
		btnBuscar.setOpaque(false);
		btnBuscar.setIcon(null);
		btnBuscar.setBackground(new Color(255, 255, 0));
		btnBuscar.setBounds(305, 201, 82, 23);
		contentPane.add(btnBuscar);
		
		btnCargarorden = new JButton("cargarOrden");
		btnCargarorden.setOpaque(false);
		btnCargarorden.setBounds(314, 359, 89, 80);
		contentPane.add(btnCargarorden);
		
		btnBorrarorden = new JButton("borrarOrden");
		btnBorrarorden.setOpaque(false);
		btnBorrarorden.setBounds(695, 277, 157, 37);
		contentPane.add(btnBorrarorden);
		
		btnPagarorden = new JButton("pagarOrden");
		btnPagarorden.setOpaque(false);
		btnPagarorden.setBounds(694, 333, 186, 37);
		contentPane.add(btnPagarorden);
		
		btnEnviarmailorden = new JButton("enviarMailOrden");
		btnEnviarmailorden.setOpaque(false);
		btnEnviarmailorden.setBounds(693, 388, 159, 37);
		contentPane.add(btnEnviarmailorden);
		
		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnFinalizar.setOpaque(false);
		btnFinalizar.setBounds(713, 551, 151, 35);
		contentPane.add(btnFinalizar);
		
		btnImprimirOrden = new JButton("imprimir orden");
		btnImprimirOrden.setOpaque(false);
		btnImprimirOrden.setBounds(691, 223, 161, 43);
		contentPane.add(btnImprimirOrden);
		
	}

	public void ocultarOpcPagoBorrarEnviar(Boolean ocultarPago,Boolean ocultarEliminar, boolean ocultarEnviar){
		this.lblocultarOpcPago.setVisible(ocultarPago);
		this.lblOcultarOpcEliminar.setVisible(ocultarEliminar);
		this.lblOcultarEnviarOrden.setVisible(ocultarEnviar);
		
		
		
//		this.lblocultarOpcPago.setOpaque(ocultarPago);
//		this.lblOcultarOpcEliminar.setOpaque(ocultarEliminar);
		
		
		this.btnBorrarorden.setEnabled(!ocultarEliminar);
		this.btnPagarorden.setEnabled(!ocultarPago);
		this.btnEnviarmailorden.setEnabled(!ocultarEnviar);
	}
	
	public void agregarFilaOrden(OrdenPedidoMatPrimaDTO ordenAagregar){
		modeloOrdenesMatPrimas.addRow(new Object[] {ordenAagregar.getIdCompra(), ordenAagregar.getFecha(),ordenAagregar.getProveedor().getNombre()});
	}
	
	@SuppressWarnings("serial")
	public void resetearModeloOrdenesPedido(){
		modeloOrdenesMatPrimas = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"New column", "New column", "New column"
				}
				) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
					Integer.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		tableOrdenesMatPrimas.setModel(modeloOrdenesMatPrimas);
	}
	
	@SuppressWarnings("serial")
	public void resetearItemsOrdenesMatPrima(){
		modeloItemsSolicitdos = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"New column", "New column"
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
		tableItemsSolicitados.setModel(modeloItemsSolicitdos);
	}
	public JButton getBtnAgregarOrdenMatPrima() {
		return btnAgregarOrdenMatPrima;
	}

	public void setBtnAgregarOrdenMatPrima(JButton btnAgregarOrdenMatPrima) {
		this.btnAgregarOrdenMatPrima = btnAgregarOrdenMatPrima;
	}

	public JButton getBtnCargarorden() {
		return btnCargarorden;
	}

	public void setBtnCargarorden(JButton btnCargarorden) {
		this.btnCargarorden = btnCargarorden;
	}

	public JButton getBtnBorrarorden() {
		return btnBorrarorden;
	}

	public void setBtnBorrarorden(JButton btnBorrarorden) {
		this.btnBorrarorden = btnBorrarorden;
	}

	public JButton getBtnPagarorden() {
		return btnPagarorden;
	}

	public void setBtnPagarorden(JButton btnPagarorden) {
		this.btnPagarorden = btnPagarorden;
	}

	public JButton getBtnEnviarmailorden() {
		return btnEnviarmailorden;
	}

	public void setBtnEnviarmailorden(JButton btnEnviarmailorden) {
		this.btnEnviarmailorden = btnEnviarmailorden;
	}

	public JButton getBtnFinalizar() {
		return btnFinalizar;
	}

	public void setBtnFinalizar(JButton btnFinalizar) {
		this.btnFinalizar = btnFinalizar;
	}

	public JTextField getTextFieldBuscadorNombre() {
		return textFieldBuscadorNombre;
	}

	public void setTextFieldBuscadorNombre(JTextField textFieldBuscadorNombre) {
		this.textFieldBuscadorNombre = textFieldBuscadorNombre;
	}

	public JTable gettableOrdenesMatPrimas() {
		return tableOrdenesMatPrimas;
	}

	public void settableOrdenesMatPrimas(JTable tableOrdenesMatPrimas) {
		this.tableOrdenesMatPrimas = tableOrdenesMatPrimas;
	}

	public JTable gettableItemsSolicitados() {
		return tableItemsSolicitados;
	}

	public void settableItemsSolicitados(JTable tableItemsSolicitados) {
		this.tableItemsSolicitados = tableItemsSolicitados;
	}

	public DefaultTableModel getModeloOrdenesMatPrimas() {
		return modeloOrdenesMatPrimas;
	}

	public void setmodeloOrdenesMatPrimas(DefaultTableModel modeloOrdenesMatPrimas) {
		this.modeloOrdenesMatPrimas = modeloOrdenesMatPrimas;
	}

	public DefaultTableModel getmodeloItemsSolicitdos() {
		return modeloItemsSolicitdos;
	}

	public void setmodeloItemsSolicitdos(DefaultTableModel modeloItemsSolicitdos) {
		this.modeloItemsSolicitdos = modeloItemsSolicitdos;
	}

	public JComboBox<String> getComboBoxFiltroOrdenes() {
		return comboBoxFiltroOrdenes;
	}

	public void setcomboBoxFiltroOrdenes(JComboBox<String> comboBoxFiltroOrdenes) {
		this.comboBoxFiltroOrdenes = comboBoxFiltroOrdenes;
	}

	public TextAutoCompleter getTextAutoAcompleter() {
		return textAutoAcompleter;
	}

	public void setTextAutoAcompleter(TextAutoCompleter textAutoAcompleter) {
		this.textAutoAcompleter = textAutoAcompleter;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public void agregarFilaItem(ItemMateriaPrimaDTO elementoItem) {
		String nombreMatPrima = elementoItem.getItemMatPrima().getNombre().trim();
		nombreMatPrima = nombreMatPrima.replace("_", " ");
		modeloItemsSolicitdos.addRow(new Object [] {nombreMatPrima,elementoItem.getCantidad()});
	}

	public OrdenPedidoMatPrimaDTO getOrdenSeleccionada() {
		return ordenSeleccionada;
	}

	public void setOrdenSeleccionada(OrdenPedidoMatPrimaDTO ordenSeleccionada) {
		this.ordenSeleccionada = ordenSeleccionada;
	}

	public JButton getBtnImprimirOrden() {
		return btnImprimirOrden;
	}

	public void setBtnImprimirOrden(JButton btnImprimirOrden) {
		this.btnImprimirOrden = btnImprimirOrden;
	}
}