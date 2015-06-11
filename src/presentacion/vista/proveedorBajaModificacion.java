package presentacion.vista;

import java.awt.BorderLayout;
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

import dto.ProveedorDTO;

import presentacion.controlador.Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class proveedorBajaModificacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tableProveedor;
	private JTextField tfDenominacion;
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
		setBounds(100, 100, 766, 895);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 121, 237, 310);
		contentPanel.add(scrollPane);
		this.control=control;
		model = new DefaultTableModel(null,nombreColumnasProveedor);
		model1=new DefaultTableModel(null, nombreColumnasMatPrimas);
		
		tableProveedor = new JTable(model){
		@Override
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
		tfDenominacion.setBounds(352, 150, 174, 22);
		contentPanel.add(tfDenominacion);
		tfDenominacion.setColumns(10);
		
		tfTelefono = new JTextField();
		tfTelefono.setColumns(10);
		tfTelefono.setBounds(352, 207, 174, 22);
		contentPanel.add(tfTelefono);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		tfEmail.setBounds(352, 320, 290, 22);
		contentPanel.add(tfEmail);
		
		tfDireccion = new JTextField();
		tfDireccion.setColumns(10);
		tfDireccion.setBounds(352, 261, 290, 22);
		contentPanel.add(tfDireccion);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(proveedorBajaModificacion.class.getResource("/prototipos/baja-modif de Proveedor.png")));
		label.setBounds(0, 0, 960, 749);
		contentPanel.add(label);
		{
			JButton btnFinalizar = new JButton("OK");
			btnFinalizar.addActionListener(new ActionListener() {
				@Override
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
				@Override
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
