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

public class productoBajaModificacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTextField tfPrecio;
	private JTextField tfTipo;
	private static Controlador control;
	private DefaultTableModel model;
	private JTable tablaProductos;

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
		Iterator<ProductoDTO> Iterador = control.getListaProductos().iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			model.addRow(new String[] {elemento.getNombre(),elemento.getTipo(),elemento.getPrecio().toString()});			
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
	}
}
