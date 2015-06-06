package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import presentacion.controlador.Controlador;

import dto.PedidoDTO;
import dto.ProductoDTO;

import javax.swing.JTable;

import presentacion.controlador.Controlador;

import dto.ProductoDTO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class productoBajaModificacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTextField tfPrecio;
	private JTextField tfTipo;
	private static Controlador control;
	private DefaultTableModel model;
	private JTable tablaProductos;
	private JComboBox cbTipo;
	private DefaultTableModel model;
	private  String[] nombreColumnasProducto = {"Nombre","Precio","Tipo"};
	private JButton btnQuitar;
	private JButton btnGuardar;
	private Controlador control;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			productoBajaModificacion dialog = new productoBajaModificacion(control);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public productoBajaModificacion(final Controlador control) {
	public productoBajaModificacion(final Controlador control) {
		setBounds(100, 100, 746, 586);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 145, 178, 345);
		contentPanel.add(scrollPane);
		
		tablaProductos = new JTable();
		scrollPane.setColumnHeaderView(tablaProductos);
		model = new DefaultTableModel(null,nombreColumnasProducto);
		{
			tfNombre = new JTextField();
			tfNombre.setBounds(491, 159, 173, 22);
			contentPanel.add(tfNombre);
			tfNombre.setColumns(10);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(299, 477, 151, 38);
			contentPanel.add(cancelButton);
			cancelButton.setActionCommand("Cancel");
		}
		{
			tfPrecio = new JTextField();
			tfPrecio.setColumns(10);
			tfPrecio.setBounds(490, 230, 91, 23);
			contentPanel.add(tfPrecio);
		}
		
		cbTipo= new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"(Seleccione un tipo de Producto)", "empanada", "pizza", "otros"}));
		cbTipo.setBounds(491, 216, 180, 23);
		contentPanel.add(cbTipo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 146, 186, 355);
		contentPanel.add(scrollPane);
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ProductoDTO auxi=control.getProducto().buscarProductoPorNombre(model.getValueAt(table.getSelectedRow(), 0).toString());
				agregarDatos(auxi);
			}
		});

		scrollPane.setViewportView(table);
		{
			tfTipo = new JTextField();
			tfTipo.setColumns(10);
			tfTipo.setBounds(490, 195, 173, 22);
			contentPanel.add(tfTipo);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(productoBajaModificacion.class.getResource("/prototipos/baja-modif de Producto.png")));
			label.setBounds(0, 0, 730, 550);
			contentPanel.add(label);
		}
		{
			btnQuitar= new JButton("OK");
			btnQuitar.setOpaque(false);
			btnQuitar.setBounds(243, 284, 52, 52);
			contentPanel.add(btnQuitar);
			btnQuitar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnQuitar);
			JButton borrarButton = new JButton("OK");
			borrarButton.setBounds(248, 291, 41, 38);
			contentPanel.add(borrarButton);
			borrarButton.setActionCommand("OK");
			getRootPane().setDefaultButton(borrarButton);
		}
		
		JButton guardarbutton = new JButton("OK");
		guardarbutton.setActionCommand("OK");
		guardarbutton.setBounds(369, 306, 45, 47);
		contentPanel.add(guardarbutton);
		
		model =crearModelo();
	}
	
	public void llenarTabla() 
		{
			JButton btnFinalizar = new JButton("Cancel");
			btnFinalizar.setOpaque(false);
			btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
		Iterator<ProductoDTO> Iterador = control.getListaProductos().iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			model.addRow(new String[] {elemento.getNombre(),elemento.getTipo(),elemento.getPrecio().toString()});			
				}
			});
			btnFinalizar.setBounds(440, 459, 151, 38);
			contentPanel.add(btnFinalizar);
			btnFinalizar.setActionCommand("Cancel");
		}
		tablaProductos.setModel(model);		
	}
		
	
	
	public DefaultTableModel crearModelo()
	{ 
		 DefaultTableModel modelo= new DefaultTableModel();
			modelo.addColumn("Nombre");
			modelo.addColumn("Tipo");
			modelo.addColumn("Precio");
		return modelo;
		btnGuardar= new JButton("New button");
		btnGuardar.setOpaque(false);
		btnGuardar.setBounds(418, 326, 48, 52);
		contentPanel.add(btnGuardar);
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public JTextField getTfPrecio() {
		return tfPrecio;
	}

	public void setTfPrecio(JTextField tfPrecio) {
		this.tfPrecio = tfPrecio;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public String[] getNombreColumnasProducto() {
		return nombreColumnasProducto;
	}

	public void setNombreColumnasProducto(String[] nombreColumnasProducto) {
		this.nombreColumnasProducto = nombreColumnasProducto;
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
	
	private void agregarDatos(ProductoDTO aux)
	{
		tfNombre.setText(aux.getNombre());
		tfPrecio.setText(aux.getPrecio().toString());
	}

	public JComboBox getCbTipo() {
		return cbTipo;
	}

	public void setCbTipo(JComboBox cbTipo) {
		this.cbTipo = cbTipo;
	}
	
}