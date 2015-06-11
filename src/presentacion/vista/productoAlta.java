package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class productoAlta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfDenominacion;
	private JTextField tfPrecio;
	private JTable tableProductos;
	private DefaultTableModel model;
	private JButton btnAgregarProducto;
	private JComboBox cbTipo ;
	private  String[] nombreColumnasProducto = {"Nombre","Precio","Tipo"};

	public productoAlta() {
		setBounds(100, 100, 664, 536);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		model = new DefaultTableModel(null,nombreColumnasProducto);
		
		cbTipo= new JComboBox();
		cbTipo.setModel(new DefaultComboBoxModel(new String[] {"(Seleccione un tipo de Producto)", "empanada", "pizza", "otros"}));
		cbTipo.setBounds(175, 200, 180, 22);
		contentPanel.add(cbTipo);
		
		tfDenominacion = new JTextField();
		tfDenominacion.setBounds(175, 164, 180, 22);
		contentPanel.add(tfDenominacion);
		tfDenominacion.setColumns(10);
		
		tfPrecio = new JTextField();
		tfPrecio.setColumns(10);
		tfPrecio.setBounds(175, 237, 91, 22);
		contentPanel.add(tfPrecio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(384, 153, 238, 246);
		contentPanel.add(scrollPane);
		
		tableProductos = new JTable(model);
		scrollPane.setViewportView(tableProductos);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(productoAlta.class.getResource("/prototipos/Alta de Producto.png")));
			label.setBounds(0, 0, 730, 500);
			contentPanel.add(label);
		}
		{
			btnAgregarProducto= new JButton("OK");
			btnAgregarProducto.setOpaque(false);
			btnAgregarProducto.setBounds(129, 346, 38, 40);
			contentPanel.add(btnAgregarProducto);
			btnAgregarProducto.setActionCommand("OK");
			getRootPane().setDefaultButton(btnAgregarProducto);
		}
		{
			JButton btnFinalizar = new JButton("Cancel");
			btnFinalizar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnFinalizar.setOpaque(false);
			btnFinalizar.setBounds(239, 434, 150, 34);
			contentPanel.add(btnFinalizar);
			btnFinalizar.setActionCommand("Cancel");
		}
		
	}

	public JTextField getTfDenominacion() {
		return tfDenominacion;
	}

	public void setTfDenominacion(JTextField tfDenominacion) {
		this.tfDenominacion = tfDenominacion;
	}

	public JTextField getTfPrecio() {
		return tfPrecio;
	}

	public void setTfPrecio(JTextField tfPrecio) {
		this.tfPrecio = tfPrecio;
	}

	public JTable getTableProductos() {
		return tableProductos;
	}

	public void setTableProductos(JTable tableProductos) {
		this.tableProductos = tableProductos;
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

	public JButton getBtnAgregarProducto() {
		return btnAgregarProducto;
	}

	public void setBtnAgregarProducto(JButton btnAgregarProducto) {
		this.btnAgregarProducto = btnAgregarProducto;
	}

	public JComboBox getCbTipo() {
		return cbTipo;
	}

	public void setCbTipo(JComboBox cbTipo) {
		this.cbTipo = cbTipo;
	}
	
}
