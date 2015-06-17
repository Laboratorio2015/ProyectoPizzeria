package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class promocionBajaModificacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField tfNombre;
	private JTextField tfPrecioFinal;
	private JTextField tfPrecioReal;
	private JTextField tfBuscarProducto;
	private JTextField tfUnidades;
	private JTextField tfSubTotal;
	private JComboBox comboBox ;
	private JButton btnEliminarPromocion;
	private JButton btnGuardarEdicionPromocion;
	private JButton btnBorrarProducto;
	private JButton btnReemplazarProducto;
	private JButton btnGuardarCambios;


	public promocionBajaModificacion() {
		setBounds(100, 100, 745, 670);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 192, 185, 265);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(457, 198, 177, 23);
		contentPanel.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfPrecioFinal = new JTextField();
		tfPrecioFinal.setColumns(10);
		tfPrecioFinal.setBounds(351, 257, 96, 23);
		contentPanel.add(tfPrecioFinal);
		
		tfPrecioReal = new JTextField();
		tfPrecioReal.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 0, 0)));
		tfPrecioReal.setBackground(new Color(102, 102, 102));
		tfPrecioReal.setEditable(false);
		tfPrecioReal.setColumns(10);
		tfPrecioReal.setBounds(499, 257, 71, 23);
		contentPanel.add(tfPrecioReal);
		
		tfBuscarProducto = new JTextField();
		tfBuscarProducto.setColumns(10);
		tfBuscarProducto.setBounds(343, 392, 212, 25);
		contentPanel.add(tfBuscarProducto);
		
		tfUnidades = new JTextField();
		tfUnidades.setColumns(10);
		tfUnidades.setBounds(574, 392, 49, 25);
		contentPanel.add(tfUnidades);
		
		tfSubTotal = new JTextField();
		tfSubTotal.setColumns(10);
		tfSubTotal.setBounds(653, 392, 49, 25);
		contentPanel.add(tfSubTotal);
		
		comboBox= new JComboBox();
		comboBox.setBounds(381, 112, 242, 25);
		contentPanel.add(comboBox);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(promocionBajaModificacion.class.getResource("/prototipos/baja-modif de Promocion.png")));
		label.setBounds(0, 0, 730, 630);
		contentPanel.add(label);
		
		btnEliminarPromocion= new JButton("New button");
		btnEliminarPromocion.setOpaque(false);
		btnEliminarPromocion.setBounds(54, 484, 45, 43);
		contentPanel.add(btnEliminarPromocion);
		
		btnGuardarEdicionPromocion= new JButton("New button");
		btnGuardarEdicionPromocion.setOpaque(false);
		btnGuardarEdicionPromocion.setBounds(347, 450, 50, 51);
		contentPanel.add(btnGuardarEdicionPromocion);
		
		btnBorrarProducto= new JButton("New button");
		btnBorrarProducto.setOpaque(false);
		btnBorrarProducto.setBounds(541, 329, 29, 27);
		contentPanel.add(btnBorrarProducto);
		
		btnReemplazarProducto= new JButton("New button");
		btnReemplazarProducto.setOpaque(false);
		btnReemplazarProducto.setBounds(347, 329, 34, 27);
		contentPanel.add(btnReemplazarProducto);
		
		btnGuardarCambios= new JButton("New button");
		btnGuardarCambios.setOpaque(false);
		btnGuardarCambios.setBounds(192, 569, 155, 35);
		contentPanel.add(btnGuardarCambios);
		
		JButton btnCancelar = new JButton("New button");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
			}
		});
		btnCancelar.setOpaque(false);
		btnCancelar.setBounds(390, 569, 155, 35);
		contentPanel.add(btnCancelar);
	}


	public JTable getTable() {
		return table;
	}


	public void setTable(JTable table) {
		this.table = table;
	}


	public JTextField getTfNombre() {
		return tfNombre;
	}


	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}


	public JTextField getTfPrecioFinal() {
		return tfPrecioFinal;
	}


	public void setTfPrecioFinal(JTextField tfPrecioFinal) {
		this.tfPrecioFinal = tfPrecioFinal;
	}


	public JTextField getTfPrecioReal() {
		return tfPrecioReal;
	}


	public void setTfPrecioReal(JTextField tfPrecioReal) {
		this.tfPrecioReal = tfPrecioReal;
	}


	public JTextField getTfBuscarProducto() {
		return tfBuscarProducto;
	}


	public void setTfBuscarProducto(JTextField tfBuscarProducto) {
		this.tfBuscarProducto = tfBuscarProducto;
	}


	public JTextField getTfUnidades() {
		return tfUnidades;
	}


	public void setTfUnidades(JTextField tfUnidades) {
		this.tfUnidades = tfUnidades;
	}


	public JTextField getTfSubTotal() {
		return tfSubTotal;
	}


	public void setTfSubTotal(JTextField tfSubTotal) {
		this.tfSubTotal = tfSubTotal;
	}


	public JComboBox getComboBox() {
		return comboBox;
	}


	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}


	public JButton getBtnEliminarPromocion() {
		return btnEliminarPromocion;
	}


	public void setBtnEliminarPromocion(JButton btnEliminarPromocion) {
		this.btnEliminarPromocion = btnEliminarPromocion;
	}


	public JButton getBtnGuardarEdicionPromocion() {
		return btnGuardarEdicionPromocion;
	}


	public void setBtnGuardarEdicionPromocion(JButton btnGuardarEdicionPromocion) {
		this.btnGuardarEdicionPromocion = btnGuardarEdicionPromocion;
	}


	public JButton getBtnBorrarProducto() {
		return btnBorrarProducto;
	}


	public void setBtnBorrarProducto(JButton btnBorrarProducto) {
		this.btnBorrarProducto = btnBorrarProducto;
	}


	public JButton getBtnReemplazarProducto() {
		return btnReemplazarProducto;
	}


	public void setBtnReemplazarProducto(JButton btnReemplazarProducto) {
		this.btnReemplazarProducto = btnReemplazarProducto;
	}


	public JButton getBtnGuardarCambios() {
		return btnGuardarCambios;
	}


	public void setBtnGuardarCambios(JButton btnGuardarCambios) {
		this.btnGuardarCambios = btnGuardarCambios;
	}
	
}
