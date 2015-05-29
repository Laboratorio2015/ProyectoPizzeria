package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

import dto.MateriaPrimaDTO;
import dto.ProductoDTO;
import dto.ProveedorDTO;

import presentacion.controlador.Controlador;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class proveedorBajaModificacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableProveedor;
	private JTable tableMateriaPrima;
	private JTextField tfDenominacion;
	private JTextField tfCategoria;
	private JTextField tfTelefono;
	private JTextField tfEmail;
	private JTextField tfDireccion;
	private DefaultTableModel model;
	private DefaultTableModel model1;
	private Controlador control;
	private  String[] nombreColumnasProveedor = {"Nombre"};
	private String[] nombreColumnasMatPrimas = {"Nombre"};

	public proveedorBajaModificacion(final Controlador control) {
		setBounds(100, 100, 965, 638);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 173, 203, 343);
		contentPanel.add(scrollPane);
		this.control=control;
		model = new DefaultTableModel(null,nombreColumnasProveedor);
		model1=new DefaultTableModel(null, nombreColumnasMatPrimas);
		
		tableProveedor = new JTable(model){
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
				llenarTablaMatPrimas(auxi);
			}
		});
	    		
		scrollPane.setViewportView(tableProveedor);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(663, 173, 260, 309);
		contentPanel.add(scrollPane_1);
		
		tableMateriaPrima = new JTable(model1)
		{public boolean isCellEditable(int rowIndex, int colIndex) {
	        return false; //desabilita la edicion de las celdas
	    }};
		scrollPane_1.setViewportView(tableMateriaPrima);
		
		tfDenominacion = new JTextField();
		tfDenominacion.setBounds(461, 205, 174, 22);
		contentPanel.add(tfDenominacion);
		tfDenominacion.setColumns(10);
		
		tfCategoria = new JTextField();
		tfCategoria.setColumns(10);
		tfCategoria.setBounds(460, 240, 174, 22);
		contentPanel.add(tfCategoria);
		
		tfTelefono = new JTextField();
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(460, 276, 174, 22);
		contentPanel.add(tfTelefono);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(460, 315, 174, 22);
		contentPanel.add(tfEmail);
		
		tfDireccion = new JTextField();
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(461, 353, 174, 20);
		contentPanel.add(tfDireccion);
		
		JLabel label_1 = new JLabel("Direccion");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(349, 356, 93, 14);
		contentPanel.add(label_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(proveedorBajaModificacion.class.getResource("/prototipos/baja-modif de Proveedor.png")));
		label.setBounds(10, 11, 950, 600);
		contentPanel.add(label);
		{
			JButton okButton = new JButton("OK");
			okButton.setOpaque(false);
			okButton.setBounds(466, 547, 151, 36);
			contentPanel.add(okButton);
			okButton.setActionCommand("OK");
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancel");
			cancelButton.setOpaque(false);
			cancelButton.setBounds(662, 547, 151, 35);
			contentPanel.add(cancelButton);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setOpaque(false);
		btnNewButton.setBounds(244, 264, 53, 49);
		contentPanel.add(btnNewButton);
	}

	public JTable getTableProveedor() {
		return tableProveedor;
	}

	public void setTableProveedor(JTable tableProveedor) {
		this.tableProveedor = tableProveedor;
	}

	public JTable getTableMateriaPrima() {
		return tableMateriaPrima;
	}

	public void setTableMateriaPrima(JTable tableMateriaPrima) {
		this.tableMateriaPrima = tableMateriaPrima;
	}

	public JTextField getTfDenominacion() {
		return tfDenominacion;
	}

	public void setTfDenominacion(JTextField tfDenominacion) {
		this.tfDenominacion = tfDenominacion;
	}

	public JTextField getTfCategoria() {
		return tfCategoria;
	}

	public void setTfCategoria(JTextField tfCategoria) {
		this.tfCategoria = tfCategoria;
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
		
		tfCategoria.setText(aux.getCategoria());
		tfDenominacion.setText(aux.getNombre());
		tfDireccion.setText(aux.getDireccion());
		tfEmail.setText(aux.getEmail());
		tfTelefono.setText(aux.getTelefono());
	}
	private void llenarTablaMatPrimas(ProveedorDTO aux) 
	{
		System.out.println(aux.getMateriasProvistas().toString());
		model1.setRowCount(0); //Para vaciar la tabla
		model1.setColumnCount(0);
		model1.setColumnIdentifiers(nombreColumnasMatPrimas);
		
	 	Iterator<MateriaPrimaDTO> Iterador = aux.getMateriasProvistas().iterator();
		while(Iterador.hasNext())
		{
			MateriaPrimaDTO elemento = Iterador.next();
			Object[] fila = {elemento.getNombre()};
			model1.addRow(fila);	
		}
		
		tableMateriaPrima=new JTable(model1);
	}
}
