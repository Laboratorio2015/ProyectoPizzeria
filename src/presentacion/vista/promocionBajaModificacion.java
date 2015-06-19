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
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

import presentacion.controlador.Controlador;
import dto.ProductoDTO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class promocionBajaModificacion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private  String[] nombreColumnas = {"Nombre","Cantidad"};
	private JTextField tfNombre;
	private JTextField tfPrecioFinal;
	private JTextField tfPrecioReal;
	private JTextField tfBuscarProducto;
	private JTextField tfUnidades;
	private JTextField tfSubTotal;
	private JComboBox comboBox ;
	private JComboBox cbTipoProducto;
	private JButton btnEliminarPromocion;
	private JButton btnBorrarProducto;
	private JButton btnAgregarProducto;
	private JButton btnGuardarCambios;
	private JTextField tfOcullto;
	private JTextField txtAgegar;
	private JTextField textField;
	private JButton btnAceptarProducto;
	private JLabel lbotonAgregarProducto;
	private Controlador control;


	public promocionBajaModificacion(final Controlador control) {
		setBounds(100, 100, 745, 670);
		this.control=control;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 192, 185, 265);
		contentPanel.add(scrollPane);
		
		model = new DefaultTableModel(null,nombreColumnas);
		table = new JTable(model){
		    @Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
		        return false; //desabilita la edicion de las celdas
		    }
		};
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
		tfBuscarProducto.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent evt) 
			{
				validarTexto(evt,tfBuscarProducto);
			}
		});
		tfBuscarProducto.setColumns(10);
		tfBuscarProducto.setBounds(343, 392, 212, 25);
		contentPanel.add(tfBuscarProducto);
		
		tfUnidades = new JTextField();
		tfUnidades.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e)
			{
				ProductoDTO producto=control.getProducto().buscarProductoPorNombre(tfBuscarProducto.getText());
				if(tfBuscarProducto.getText().compareTo("")!=0 && tfUnidades.getText().compareTo("")!=0 && producto!=null)
				{
					Integer costo=producto.getPrecio();
					Integer cantidad=Integer.parseInt(tfUnidades.getText().toString());
					Integer precio=costo*cantidad;
					tfSubTotal.setText(precio.toString());
				}
				if(tfUnidades.getText().compareTo("")==0)
					tfSubTotal.setText("");
					
			}
		});
		tfUnidades.setColumns(10);
		tfUnidades.setBounds(574, 392, 49, 25);
		contentPanel.add(tfUnidades);
		
		tfSubTotal = new JTextField();
		tfSubTotal.setEditable(false);
		tfSubTotal.setColumns(10);
		tfSubTotal.setBounds(653, 392, 49, 25);
		contentPanel.add(tfSubTotal);
		
		comboBox= new JComboBox();
		comboBox.setBounds(381, 112, 242, 25);
		contentPanel.add(comboBox);
		
		cbTipoProducto= new JComboBox();
		cbTipoProducto.setOpaque(false);
		cbTipoProducto.setModel(new DefaultComboBoxModel(new String[] {"(Seleccione un tipo de Producto)", "Empanada", "Pizza", "Otros"}));
		cbTipoProducto.setBounds(343, 366, 212, 20);
		contentPanel.add(cbTipoProducto);
		
		tfOcullto = new JTextField();
		tfOcullto.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tfOcullto.setBackground(new Color(102, 102, 102));
		tfOcullto.setEnabled(false);
		tfOcullto.setEditable(false);
		tfOcullto.setBounds(322, 364, 389, 69);
		contentPanel.add(tfOcullto);
		tfOcullto.setColumns(10);
		
		txtAgegar = new JTextField();
		txtAgegar.setForeground(new Color(204, 204, 204));
		txtAgegar.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		txtAgegar.setBackground(new Color(102, 102, 102));
		txtAgegar.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAgegar.setFont(new Font("Calibri", Font.BOLD, 14));
		txtAgegar.setText("Agregar");
		txtAgegar.setBounds(391, 336, 71, 20);
		contentPanel.add(txtAgegar);
		txtAgegar.setColumns(10);
		
		lbotonAgregarProducto= new JLabel("");
		lbotonAgregarProducto.setIcon(new ImageIcon(promocionBajaModificacion.class.getResource("/Botones/boton agregar Promocion.png")));
		lbotonAgregarProducto.setBounds(437, 428, 140, 69);
		contentPanel.add(lbotonAgregarProducto);
		
		btnAceptarProducto= new JButton("New button");
		btnAceptarProducto.setOpaque(false);
		btnAceptarProducto.setBounds(484, 439, 40, 30);
		contentPanel.add(btnAceptarProducto);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		textField.setBackground(new Color(102, 102, 102));
		textField.setBounds(322, 432, 389, 83);
		contentPanel.add(textField);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(promocionBajaModificacion.class.getResource("/prototipos/baja-modif de Promocion.png")));
		label.setBounds(0, 0, 730, 630);
		contentPanel.add(label);
		
		btnEliminarPromocion= new JButton("New button");
		btnEliminarPromocion.setOpaque(false);
		btnEliminarPromocion.setBounds(54, 484, 45, 43);
		contentPanel.add(btnEliminarPromocion);
		
		btnBorrarProducto= new JButton("New button");
		btnBorrarProducto.setOpaque(false);
		btnBorrarProducto.setBounds(541, 329, 29, 27);
		contentPanel.add(btnBorrarProducto);
		
		btnAgregarProducto= new JButton("New button");
		btnAgregarProducto.setOpaque(false);
		btnAgregarProducto.setBounds(347, 329, 34, 27);
		contentPanel.add(btnAgregarProducto);
		
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
	
	public JTextField getTfOcullto() {
		return tfOcullto;
	}


	public void setTfOcullto(JTextField tfOcullto) {
		this.tfOcullto = tfOcullto;
	}


	public DefaultTableModel getModel() {
		return model;
	}


	public void setModel(DefaultTableModel model) {
		this.model = model;
	}


	public String[] getNombreColumnas() {
		return nombreColumnas;
	}


	public void setNombreColumnas(String[] nombreColumnas) {
		this.nombreColumnas = nombreColumnas;
	}


	public JButton getBtnEliminarPromocion() {
		return btnEliminarPromocion;
	}


	public void setBtnEliminarPromocion(JButton btnEliminarPromocion) {
		this.btnEliminarPromocion = btnEliminarPromocion;
	}
	

	public JButton getBtnAgregarProducto() {
		return btnAgregarProducto;
	}


	public void setBtnAgregarProducto(JButton btnAgregarProducto) {
		this.btnAgregarProducto = btnAgregarProducto;
	}


	public JButton getBtnAceptarProducto() {
		return btnAceptarProducto;
	}


	public void setBtnAceptarProducto(JButton btnAceptarProducto) {
		this.btnAceptarProducto = btnAceptarProducto;
	}
	
	
	public JComboBox getCbTipoProducto() {
		return cbTipoProducto;
	}


	public void setCbTipoProducto(JComboBox cbTipoProducto) {
		this.cbTipoProducto = cbTipoProducto;
	}


	public JLabel getLbotonAgregarProducto() {
		return lbotonAgregarProducto;
	}


	public void setLbotonAgregarProducto(JLabel lbotonAgregarProducto) {
		this.lbotonAgregarProducto = lbotonAgregarProducto;
	}

	public JButton getBtnBorrarProducto() {
		return btnBorrarProducto;
	}

	public void setBtnBorrarProducto(JButton btnBorrarProducto) {
		this.btnBorrarProducto = btnBorrarProducto;
	}


	public JButton getBtnReemplazarProducto() {
		return btnAgregarProducto;
	}


	public void setBtnReemplazarProducto(JButton btnReemplazarProducto) {
		this.btnAgregarProducto = btnReemplazarProducto;
	}


	public JButton getBtnGuardarCambios() {
		return btnGuardarCambios;
	}


	public void setBtnGuardarCambios(JButton btnGuardarCambios) {
		this.btnGuardarCambios = btnGuardarCambios;
	}
	public void validarTexto(KeyEvent evt, JTextField a)
	{
		char car = evt.getKeyChar();
		if(a.getText().length()>=20) evt.consume();
		if(!(car<'0' || car>'9')) evt.consume();
	}
}
