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
	private JButton btnQuitar ;
	private JButton btnGuardar;

	public proveedorBajaModificacion(final Controlador control) {
		setBounds(100, 100, 766, 638);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(69, 211, 185, 244);
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
			}
		});
	    		
		scrollPane.setViewportView(tableProveedor);
		
		tfDenominacion = new JTextField();
		tfDenominacion.setEditable(false);
		tfDenominacion.setBounds(468, 233, 174, 22);
		contentPanel.add(tfDenominacion);
		tfDenominacion.setColumns(10);
		
		tfCategoria = new JTextField();
		tfCategoria.setEditable(false);
		tfCategoria.setColumns(10);
		tfCategoria.setBounds(468, 269, 174, 22);
		contentPanel.add(tfCategoria);
		
		tfTelefono = new JTextField();
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(468, 306, 174, 22);
		contentPanel.add(tfTelefono);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(468, 343, 174, 22);
		contentPanel.add(tfEmail);
		
		tfDireccion = new JTextField();
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(468, 381, 174, 20);
		contentPanel.add(tfDireccion);
		
		JLabel label_1 = new JLabel("Direccion");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(353, 382, 93, 14);
		contentPanel.add(label_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(proveedorBajaModificacion.class.getResource("/prototipos/baja-modif de Proveedor.png")));
		label.setBounds(0, 0, 960, 599);
		contentPanel.add(label);
		{
			JButton btnFinalizar = new JButton("OK");
			btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnFinalizar.setOpaque(false);
			btnFinalizar.setBounds(442, 525, 151, 36);
			contentPanel.add(btnFinalizar);
			btnFinalizar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnFinalizar);
		}
		{
			btnQuitar= new JButton("Cancel");
			btnQuitar.setOpaque(false);
			btnQuitar.setBounds(36, 482, 53, 49);
			contentPanel.add(btnQuitar);
			btnQuitar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnQuitar.setActionCommand("Cancel");
		}
		
		btnGuardar = new JButton("New button");
		btnGuardar.setOpaque(false);
		btnGuardar.setBounds(382, 420, 64, 49);
		contentPanel.add(btnGuardar);
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
		
		tfCategoria.setText(aux.getCategoria().toString());
		tfDenominacion.setText(aux.getNombre());
		tfDireccion.setText(aux.getDireccion());
		tfEmail.setText(aux.getEmail());
		tfTelefono.setText(aux.getTelefono());
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
	
}
