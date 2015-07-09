package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;

import presentacion.controlador.Controlador;

import com.mxrck.autocompleter.TextAutoCompleter;

import dto.ProductoDTO;
import dto.PromocionDTO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class promocionAlta extends JDialog{

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
	private  String[] nombreColumnas = {"Producto","Cantidad"};
	private TextAutoCompleter AutoCompletar;
	private Controlador control;

	public promocionAlta(final Controlador control)
	{
		setBounds(100, 100, 764, 587);
		this.control=control;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		{
			comboBox= new JComboBox();
			/*comboBox.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e)
				{
					  String seleccionado=(String)comboBox.getSelectedItem();
			            //completar autocomplete dependiendo del combox que aya seleccionado  
					  switch (seleccionado) 
			            {
						case "Empanada":AutoCompletar.addItems(buscarProductos("empanada"));break;
						case "Pizza":AutoCompletar.addItems(buscarProductos("pizza"));break;
						case "Otros":AutoCompletar.addItems(buscarProductos("otros"));break;
			            }
				}
			});*/
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
			tfAgregarProducto.addKeyListener(new KeyAdapter()
			{
				@Override
				public void keyTyped(KeyEvent evt) 
				{
					validarTexto(evt,tfAgregarProducto);
				}
				@Override
				public void keyReleased(KeyEvent e) 
				{
					ProductoDTO producto=control.getProducto().buscarProductoPorNombre(tfAgregarProducto.getText());
					if(tfAgregarProducto.getText().length()>4)
						tfPrecioUnidad.setText(Integer.toString(producto.getPrecio()));
				}
			});
			tfAgregarProducto.setColumns(10);
			tfAgregarProducto.setBounds(46, 323, 258, 25);
			contentPanel.add(tfAgregarProducto);
		}
		{
			tfPrecio = new JTextField();
			tfPrecio.setHorizontalAlignment(SwingConstants.CENTER);
			tfPrecio.setFont(new Font("Calibri", Font.BOLD, 24));
			tfPrecio.setColumns(10);
			tfPrecio.setBounds(641, 351, 73, 47);
			contentPanel.add(tfPrecio);
		}
		{
			tfPrecioReal = new JTextField();
			tfPrecioReal.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfPrecioReal.setBackground(new Color(102, 102, 102));
			tfPrecioReal.setEditable(false);
			tfPrecioReal.setColumns(10);
			tfPrecioReal.setBounds(653, 275, 53, 25);
			contentPanel.add(tfPrecioReal);
		}
		{
			tfPrecioUnidad = new JTextField();
			tfPrecioUnidad.setEditable(false);
			tfPrecioUnidad.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfPrecioUnidad.setBackground(new Color(102, 102, 102));
			tfPrecioUnidad.setColumns(10);
			tfPrecioUnidad.setBounds(65, 373, 38, 25);
			contentPanel.add(tfPrecioUnidad);
		}
		{
			tfSubtotal = new JTextField();
			tfSubtotal.setEditable(false);
			tfSubtotal.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfSubtotal.setBackground(new Color(102, 102, 102));
			tfSubtotal.setColumns(10);
			tfSubtotal.setBounds(143, 373, 53, 25);
			contentPanel.add(tfSubtotal);
		}
		{
			tfCantidad = new JTextField();
			tfCantidad.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0)
				{
					ProductoDTO producto=control.getProducto().buscarProductoPorNombre(tfAgregarProducto.getText());
					if(tfAgregarProducto.getText().compareTo("")!=0 && tfCantidad.getText().compareTo("")!=0 && producto!=null)
					{
						Integer costo=producto.getPrecio();
						Integer cantidad=Integer.parseInt(tfCantidad.getText().toString());
						Integer precio=costo*cantidad;
						tfSubtotal.setText(precio.toString());
					}
					if(tfCantidad.getText().compareTo("")==0)
						tfSubtotal.setText("");
				}
			});
		/*	tfCantidad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)
				{
					int cantidad=Integer.parseInt(tfCantidad.getText());
					int precio=Integer.parseInt(tfPrecioUnidad.getText().toString());
					tfSubtotal.setText(Integer.toString(precio*cantidad));
				}
			});*/
			tfCantidad.setColumns(10);
			tfCantidad.setBounds(329, 323, 53, 25);
			contentPanel.add(tfCantidad);
		}
		//autocompletar
				AutoCompletar= new TextAutoCompleter(tfAgregarProducto);
				AutoCompletar.setCaseSensitive(false); //No sensible a mayúsculas
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(424, 144, 197, 262);
			contentPanel.add(scrollPane);
			{
				model = new DefaultTableModel(null,nombreColumnas);
				table = new JTable(model)
				{
				    @Override
					public boolean isCellEditable(int rowIndex, int colIndex)
				    {
				        return false; //desabilita la edicion de las celdas
				    }
				};
			}
			scrollPane.setViewportView(table);
			table.setForeground(new Color(255,255,255));
			table.setBackground(new Color(66,66,66));
			scrollPane.getViewport().setBackground(new Color(66,66,66));
			//encabezado
			JTableHeader headerPedidos = table.getTableHeader();
			headerPedidos.setFont(new Font("Tahoma", Font.BOLD, 13));
			headerPedidos.setForeground(new Color(66,66,66));
			headerPedidos.setBackground(new Color(0).GRAY);
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
			btnCrearPromocion.setBounds(356, 475, 151, 35);
			contentPanel.add(btnCrearPromocion);
		}
		{
			JButton btnCancelar = new JButton("Cancel");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
					dispose();
				}
			});
			btnCancelar.setOpaque(false);
			btnCancelar.setActionCommand("Cancel");
			btnCancelar.setBounds(555, 475, 151, 35);
			contentPanel.add(btnCancelar);
		}
		{
			btnAgregarProducto= new JButton("New button");
			btnAgregarProducto.setOpaque(false);
			btnAgregarProducto.setBounds(75, 423, 38, 35);
			contentPanel.add(btnAgregarProducto);
		}
		{
			btnQuitarProducto= new JButton("New button");
			btnQuitarProducto.setOpaque(false);
			btnQuitarProducto.setBounds(217, 423, 38, 35);
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

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void setNombreColumnas(String[] nombreColumnas) {
		this.nombreColumnas = nombreColumnas;
	}

	public TextAutoCompleter getAutoCompletar() {
		return AutoCompletar;
	}

	public void setAutoCompletar(TextAutoCompleter autoCompletar) {
		AutoCompletar = autoCompletar;
	}

	public Controlador getControl() {
		return control;
	}

	public void setControl(Controlador control) {
		this.control = control;
	}

	private ArrayList<Object> buscarProductos(String filtro)
	{
		ArrayList<Object> result=new ArrayList<Object>();
		Iterator<ProductoDTO> Iterador=control.getProducto().obtenerProducto().iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			if(elemento.getTipo().compareTo(filtro)==0)
				result.add(elemento.getNombre());	
		}
		return result;
	}
	
	public void validarTexto(KeyEvent evt, JTextField a)
	{
		char car = evt.getKeyChar();
		if(a.getText().length()>=20) evt.consume();
		if(!(car<'0' || car>'9')) evt.consume();
	}
}
