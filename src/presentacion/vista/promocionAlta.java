package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

public class promocionAlta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNombre;
	private JTextField tfAgregarProducto;
	private JTextField tfPrecio;
	private JTextField tfPrecioReal;
	private JTextField tfPrecioUnidad;
	private JTextField tfSubtotal;
	private JTextField tfCantidad;
	private JComboBox comboBox;
	private JButton btnCrearPromocion;
	private JButton btnAgregarProducto;
	private JButton btnQuitarProducto ;
	private JTable table;
	private DefaultTableModel model;
	private  String[] nombreColumnas = {"Producto","Precio"};

	public promocionAlta() {
		setBounds(100, 100, 764, 587);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			comboBox= new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"(Seleccione un tipo de Producto)", "Empanada", "Pizza", "Otros"}));
			comboBox.setBounds(46, 261, 254, 25);
			contentPanel.add(comboBox);
		}
		{
			tfNombre = new JTextField();
			tfNombre.setColumns(10);
			tfNombre.setBounds(46, 167, 258, 25);
			contentPanel.add(tfNombre);
		}
		{
			tfAgregarProducto = new JTextField();
			tfAgregarProducto.setColumns(10);
			tfAgregarProducto.setBounds(46, 323, 258, 25);
			contentPanel.add(tfAgregarProducto);
		}
		{
			tfPrecio = new JTextField();
			tfPrecio.setColumns(10);
			tfPrecio.setBounds(340, 168, 53, 25);
			contentPanel.add(tfPrecio);
		}
		{
			tfPrecioReal = new JTextField();
			tfPrecioReal.setEditable(false);
			tfPrecioReal.setColumns(10);
			tfPrecioReal.setBounds(453, 168, 53, 25);
			contentPanel.add(tfPrecioReal);
		}
		{
			tfPrecioUnidad = new JTextField();
			tfPrecioUnidad.setColumns(10);
			tfPrecioUnidad.setBounds(330, 323, 38, 25);
			contentPanel.add(tfPrecioUnidad);
		}
		{
			tfSubtotal = new JTextField();
			tfSubtotal.setColumns(10);
			tfSubtotal.setBounds(458, 323, 53, 25);
			contentPanel.add(tfSubtotal);
		}
		{
			tfCantidad = new JTextField();
			tfCantidad.setColumns(10);
			tfCantidad.setBounds(377, 324, 53, 25);
			contentPanel.add(tfCantidad);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(526, 135, 197, 262);
			contentPanel.add(scrollPane);
			{
				model = new DefaultTableModel(null,nombreColumnas);
				table = new JTable(model)
				{
				    public boolean isCellEditable(int rowIndex, int colIndex)
				    {
				        return false; //desabilita la edicion de las celdas
				    }
				};
			}
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(promocionAlta.class.getResource("/prototipos/Alta de Promocion.png")));
			label.setBounds(0, 0, 750, 550);
			contentPanel.add(label);
		}
		{
			btnCrearPromocion= new JButton("OK");
			btnCrearPromocion.setOpaque(false);
			btnCrearPromocion.setActionCommand("OK");
			btnCrearPromocion.setBounds(93, 470, 151, 35);
			contentPanel.add(btnCrearPromocion);
		}
		{
			JButton btnCancelar = new JButton("Cancel");
			btnCancelar.setOpaque(false);
			btnCancelar.setActionCommand("Cancel");
			btnCancelar.setBounds(290, 470, 151, 35);
			contentPanel.add(btnCancelar);
		}
		{
			btnAgregarProducto= new JButton("New button");
			btnAgregarProducto.setOpaque(false);
			btnAgregarProducto.setBounds(194, 384, 45, 35);
			contentPanel.add(btnAgregarProducto);
		}
		{
			btnQuitarProducto= new JButton("New button");
			btnQuitarProducto.setOpaque(false);
			btnQuitarProducto.setBounds(526, 423, 53, 35);
			contentPanel.add(btnQuitarProducto);
		}
	}

	public JTextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public JTextField getTfAgregarProducto() {
		return tfAgregarProducto;
	}

	public void setTfAgregarProducto(JTextField tfAgregarProducto) {
		this.tfAgregarProducto = tfAgregarProducto;
	}

	public JTextField getTfPrecio() {
		return tfPrecio;
	}

	public void setTfPrecio(JTextField tfPrecio) {
		this.tfPrecio = tfPrecio;
	}

	public JTextField getTfPrecioReal() {
		return tfPrecioReal;
	}

	public void setTfPrecioReal(JTextField tfPrecioReal) {
		this.tfPrecioReal = tfPrecioReal;
	}

	public JTextField getTfPrecioUnidad() {
		return tfPrecioUnidad;
	}

	public void setTfPrecioUnidad(JTextField tfPrecioUnidad) {
		this.tfPrecioUnidad = tfPrecioUnidad;
	}

	public JTextField getTfSubtotal() {
		return tfSubtotal;
	}

	public void setTfSubtotal(JTextField tfSubtotal) {
		this.tfSubtotal = tfSubtotal;
	}

	public JTextField getTfCantidad() {
		return tfCantidad;
	}

	public void setTfCantidad(JTextField tfCantidad) {
		this.tfCantidad = tfCantidad;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public JButton getBtnCrearPromocion() {
		return btnCrearPromocion;
	}

	public void setBtnCrearPromocion(JButton btnCrearPromocion) {
		this.btnCrearPromocion = btnCrearPromocion;
	}

	public JButton getBtnAgregarProducto() {
		return btnAgregarProducto;
	}

	public void setBtnAgregarProducto(JButton btnAgregarProducto) {
		this.btnAgregarProducto = btnAgregarProducto;
	}

	public JButton getBtnQuitarProducto() {
		return btnQuitarProducto;
	}

	public void setBtnQuitarProducto(JButton btnQuitarProducto) {
		this.btnQuitarProducto = btnQuitarProducto;
	}
	
}
