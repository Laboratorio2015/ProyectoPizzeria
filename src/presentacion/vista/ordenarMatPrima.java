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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ordenarMatPrima extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private VentanaPrincipal _padre;
	private ordenarMatPrima _matPrima;
	private JTextField txFieldBuscarProveedor;
	private JTextField textFieldBusquedaMatPrima;
	private JTextField textFieldCantMatPrima;
	private JTextField textField_1;
	private JTextField txtFieldCategProveed;
	private JTextField textFieldTelProveed;
	private JTextField textFieldDireccProveed;
	private JTable tablaListaMatPrima;
	private JButton buttonGuardarOrden;
	private JButton buttonSeleccProveed;
	private JButton buttonAgregarMatPrima;
	private JButton buttonQuitarMatPrimaSeleccionada;
	private DefaultTableModel modeloMatPrima;


	@SuppressWarnings("serial")
	public ordenarMatPrima(VentanaPrincipal padre, final Controlador control) {
		setModal(true);
		padre=_padre;
		_matPrima=this;
		setMinimumSize(new Dimension(700, 600));
		setBounds(300, 50, 700, 641);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txFieldBuscarProveedor = new JTextField();
		txFieldBuscarProveedor.setBackground(Color.LIGHT_GRAY);
		txFieldBuscarProveedor.setBounds(120, 135, 253, 25);
		contentPanel.add(txFieldBuscarProveedor);
		txFieldBuscarProveedor.setColumns(10);
		
		textFieldBusquedaMatPrima = new JTextField();
		textFieldBusquedaMatPrima.setBackground(Color.LIGHT_GRAY);
		textFieldBusquedaMatPrima.setBounds(120, 278, 299, 25);
		contentPanel.add(textFieldBusquedaMatPrima);
		textFieldBusquedaMatPrima.setColumns(10);
		
		textFieldCantMatPrima = new JTextField();
		textFieldCantMatPrima.setBounds(506, 278, 46, 25);
		contentPanel.add(textFieldCantMatPrima);
		textFieldCantMatPrima.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(506, 283, 46, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(109, 392, 526, 126);
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
		label.setOpaque(true);
		label.setBounds(0, 0, 684, 600);
		contentPanel.add(label);
		label.setIcon(new ImageIcon(ordenarMatPrima.class.getResource("/prototipos/orden de materia prima.png")));
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
		buttonSeleccProveed.setBounds(595, 136, 33, 38);
		contentPanel.add(buttonSeleccProveed);
		
		buttonAgregarMatPrima = new JButton("");
		buttonAgregarMatPrima.setBounds(595, 274, 33, 38);
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
	}
}
