package presentacion.vista;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import com.mxrck.autocompleter.TextAutoCompleter;
import dto.ClienteDTO;
import dto.PedidoDTO;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import presentacion.controlador.Controlador;
import presentacion.reportes.Ticket;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import java.util.Iterator;
import javax.swing.JCheckBox;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class seleccionDeCliente extends JDialog {


	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private ordenDePedido _padre;
	private PedidoDTO pedido;
	private ClienteDTO cliente;
	private seleccionDeCliente _this;
	private JTextField tfAgregarDNI;
	private JTextField tfNombrApellido;
	private JTextField tfDireccionTelefono;
	private JButton btnSeleccionar;
	private JButton btnEditarCliente;
	private JButton btnAgregarCliente;
	private Controlador control;
	private JCheckBox CheckBoxDelivery;
	private JTable table;
	private DefaultTableModel model;
	private  String[] nombreColumnas = {"DNI","Apellido","Nombre","Calle","Num","Telefono"};
	
	public seleccionDeCliente(final Controlador control,final PedidoDTO pedido)
	{
		this.pedido=pedido;
		_this=this;
		this.control=control;
		setMinimumSize(new Dimension(700, 600));
		setBounds(500, 100, 700, 638);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		tfAgregarDNI = new JTextField();
		tfAgregarDNI.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent arg0)
			{
				validarNumerosDNI(arg0, tfAgregarDNI);
			}
			@Override
			public void keyReleased(KeyEvent e) 
			{
				String dni=tfAgregarDNI.getText();
				if(dni!=" " && dni!="")
					cliente=control.getCliente().buscarClientePorDNI(Integer.parseInt(dni));
				if(cliente!=null)
				{
				tfNombrApellido.setText("Apellido y Nombre: "+cliente.getApellido()+""+ cliente.getNombre());
				tfDireccionTelefono.setText("Direccion: "+cliente.getDireccion()+""+cliente.getNumeracion());
				}
				if(Integer.parseInt(dni)==0)
					CheckBoxDelivery.setVisible(false);
				else
					CheckBoxDelivery.setVisible(true);
			}
		});
		tfAgregarDNI.setBounds(43, 198, 209, 23);
		contentPanel.add(tfAgregarDNI);
		tfAgregarDNI.setColumns(10);
		
		TextAutoCompleter autoCompletar=new TextAutoCompleter(tfAgregarDNI);
		autoCompletar.setCaseSensitive(false);
		autoCompletar.addItems(control.getCliente().dniClientes());
		
		
		{
			tfDireccionTelefono = new JTextField();
			tfDireccionTelefono.setFont(new Font("Calibri", Font.BOLD, 15));
			tfDireccionTelefono.setForeground(Color.DARK_GRAY);
			tfDireccionTelefono.setBackground(new Color(204, 204, 0));
			tfDireccionTelefono.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 0, 0)));
			tfDireccionTelefono.setEditable(false);
			tfDireccionTelefono.setColumns(10);
			tfDireccionTelefono.setBounds(275, 184, 277, 20);
			contentPanel.add(tfDireccionTelefono);
		}
		
		CheckBoxDelivery= new JCheckBox("");
		CheckBoxDelivery.setBackground(new Color(204, 204, 0));
		CheckBoxDelivery.setBounds(386, 280, 28, 23);
		contentPanel.add(CheckBoxDelivery);
		{
			tfNombrApellido = new JTextField();
			tfNombrApellido.setForeground(Color.DARK_GRAY);
			tfNombrApellido.setFont(new Font("Calibri", Font.BOLD, 15));
			tfNombrApellido.setBackground(new Color(204, 204, 0));
			tfNombrApellido.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 0, 0)));
			tfNombrApellido.setEditable(false);
			tfNombrApellido.setBounds(275, 157, 277, 20);
			contentPanel.add(tfNombrApellido);
			tfNombrApellido.setColumns(10);
		}
		
		JLabel lblListadoDeTodos = new JLabel("Listado de Clientes");
		lblListadoDeTodos.setForeground(Color.DARK_GRAY);
		lblListadoDeTodos.setFont(new Font("Calibri", Font.BOLD, 17));
		lblListadoDeTodos.setBounds(31, 316, 277, 23);
		contentPanel.add(lblListadoDeTodos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 366, 607, 139);
		contentPanel.add(scrollPane);
		
		model = new DefaultTableModel(null,nombreColumnas);
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				tfAgregarDNI.setText("");
				ClienteDTO auxi=control.getCliente().buscarClientePorDNI(Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString()));
				tfNombrApellido.setText("Apellido y Nombre: "+auxi.getApellido()+" "+ auxi.getNombre());
				tfDireccionTelefono.setText("Direccion: "+auxi.getDireccion()+""+auxi.getNumeracion());
				if(auxi.getDni().equals(0))
					CheckBoxDelivery.setVisible(false);
				else
					CheckBoxDelivery.setVisible(true);
			}
		});
		scrollPane.setViewportView(table);
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(153, 153, 0));
		scrollPane.getViewport().setBackground(new Color(153, 153, 0));	
		//encabezado
		JTableHeader headerPedidos = table.getTableHeader();
		headerPedidos.setFont(new Font("Tahoma", Font.BOLD, 13));
		headerPedidos.setForeground(new Color(153, 153, 0));
		headerPedidos.setBackground(new Color(0).GRAY);
		llenarTablaCliente();
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(seleccionDeCliente.class.getResource("/prototipos/seleccion de cliente.png")));
			label.setBounds(0, 0, 684, 600);
			contentPanel.add(label);
		}
		{
			btnSeleccionar= new JButton("OK");
			btnSeleccionar.addActionListener(control);
			btnSeleccionar.addMouseListener(new MouseAdapter() 
			{
				//este metodo agrega el cliente a el pedido y debe crear el ticket y la comanda.
				@Override
				public void mouseClicked(MouseEvent arg0)
				{
					try
					{
						cliente=control.getCliente().buscarClientePorDNI(Integer.parseInt(tfAgregarDNI.getText()));
						pedido.setCliente(cliente);
					}
					catch(Exception e)
					{
						cliente=control.getCliente().buscarClientePorDNI(Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString()));
						pedido.setCliente(cliente);
					}
					if(CheckBoxDelivery.isSelected())
						pedido.setLlevaDelivery(true);
					else
						pedido.setLlevaDelivery(false);
					control.getPedido().agregarPedido(pedido);
//				try {
//						control.enviarPedidoMonitor(pedido);
//					} catch (IOException e) {
//						System.out.println("Fallo conexion con monitor (servidor)");
//						e.printStackTrace();
//					}
				
					JOptionPane.showMessageDialog(null, "Se genero ticket y comanda con el número de pedido: "+seleccionDeCliente.this.pedido.getIdpedido());
					//control.getMonitorCocina().nuevoPedido(pedido);
					//new Ticket().generarTicket(seleccionDeCliente.this.pedido);
					//new Comanda().generarComanda(seleccionDeCliente.this.pedido);
					//control.getMonitorCocina().nuevoPedido(pedido);
					//////////ENVIA el NUEVO PEDIDO AL MONITOR//////
					//control.getMonitorCocina().nuevoPedido(pedido);  // no funciona, da null pointer exception
					/////////////////////////////////////////////
					new Ticket(seleccionDeCliente.this.pedido).generarTicket();
					//new Comanda(seleccionDeCliente.this.pedido).generarComanda();
					dispose();
				}
			});
			btnSeleccionar.setOpaque(false);
			btnSeleccionar.setBounds(161, 530, 168, 34);
			contentPanel.add(btnSeleccionar);
			btnSeleccionar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnSeleccionar);
		}
		{
			JButton btnCancelar = new JButton("Cancel");
			btnCancelar.setOpaque(false);
			btnCancelar.setBounds(378, 530, 161, 34);
			contentPanel.add(btnCancelar);
			btnCancelar.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					dispose();
				}
			});
			btnCancelar.setActionCommand("Cancel");
		}
		{
			btnAgregarCliente= new JButton("New button");
			btnAgregarCliente.setOpaque(false);
			btnAgregarCliente.setBounds(540, 226, 123, 95);
			contentPanel.add(btnAgregarCliente);
		}
		{
			btnEditarCliente= new JButton("New button");
			btnEditarCliente.setOpaque(false);
			btnEditarCliente.setBounds(572, 146, 78, 34);
			contentPanel.add(btnEditarCliente);
		}
		

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	///////// Se agrega el siguiente constructor para asignar al cliente 0, un cliente verdadero///////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * @wbp.parser.constructor
	 */
	public seleccionDeCliente(final Controlador control,final PedidoDTO pedido, String otro)
	{
		this.pedido=pedido;
		_this=this;
		this.control=control;
		setMinimumSize(new Dimension(700, 600));
		setBounds(500, 100, 700, 638);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		tfAgregarDNI = new JTextField();
		tfAgregarDNI.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyTyped(KeyEvent arg0)
			{
				validarNumerosDNI(arg0, tfAgregarDNI);
			}
			@Override
			public void keyReleased(KeyEvent e) 
			{
				String dni=tfAgregarDNI.getText();
				if(dni!=" " && dni!="")
					cliente=control.getCliente().buscarClientePorDNI(Integer.parseInt(dni));
				if(cliente!=null)
				{
				tfNombrApellido.setText("Apellido y Nombre: "+cliente.getApellido()+""+ cliente.getNombre());
				tfDireccionTelefono.setText("Direccion: "+cliente.getDireccion()+""+cliente.getNumeracion());
				}
				if(Integer.parseInt(dni)==0)
					CheckBoxDelivery.setVisible(false);
				else
					CheckBoxDelivery.setVisible(true);
			}
		});
		tfAgregarDNI.setBounds(43, 198, 209, 23);
		contentPanel.add(tfAgregarDNI);
		tfAgregarDNI.setColumns(10);
		
		TextAutoCompleter autoCompletar=new TextAutoCompleter(tfAgregarDNI);
		autoCompletar.setCaseSensitive(false);
		autoCompletar.addItems(control.getCliente().dniClientes());
		
		
		{
			tfDireccionTelefono = new JTextField();
			tfDireccionTelefono.setFont(new Font("Calibri", Font.BOLD, 15));
			tfDireccionTelefono.setForeground(Color.DARK_GRAY);
			tfDireccionTelefono.setBackground(new Color(204, 204, 0));
			tfDireccionTelefono.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 0, 0)));
			tfDireccionTelefono.setEditable(false);
			tfDireccionTelefono.setColumns(10);
			tfDireccionTelefono.setBounds(275, 184, 277, 20);
			contentPanel.add(tfDireccionTelefono);
		}
		
		CheckBoxDelivery= new JCheckBox("");
		CheckBoxDelivery.setBackground(new Color(204, 204, 0));
		CheckBoxDelivery.setBounds(386, 280, 28, 23);
		contentPanel.add(CheckBoxDelivery);
		{
			tfNombrApellido = new JTextField();
			tfNombrApellido.setForeground(Color.DARK_GRAY);
			tfNombrApellido.setFont(new Font("Calibri", Font.BOLD, 15));
			tfNombrApellido.setBackground(new Color(204, 204, 0));
			tfNombrApellido.setBorder(new MatteBorder(0, 0, 0, 0, new Color(0, 0, 0)));
			tfNombrApellido.setEditable(false);
			tfNombrApellido.setBounds(275, 157, 277, 20);
			contentPanel.add(tfNombrApellido);
			tfNombrApellido.setColumns(10);
		}
		
		JLabel lblListadoDeTodos = new JLabel("Listado de Clientes");
		lblListadoDeTodos.setForeground(Color.DARK_GRAY);
		lblListadoDeTodos.setFont(new Font("Calibri", Font.BOLD, 17));
		lblListadoDeTodos.setBounds(31, 316, 277, 23);
		contentPanel.add(lblListadoDeTodos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 366, 607, 139);
		contentPanel.add(scrollPane);
		
		model = new DefaultTableModel(null,nombreColumnas);
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) 
			{
				tfAgregarDNI.setText("");
				ClienteDTO auxi=control.getCliente().buscarClientePorDNI(Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString()));
				tfNombrApellido.setText("Apellido y Nombre: "+auxi.getApellido()+" "+ auxi.getNombre());
				tfDireccionTelefono.setText("Direccion: "+auxi.getDireccion()+""+auxi.getNumeracion());
				if(auxi.getDni().equals(0))
					CheckBoxDelivery.setVisible(false);
				else
					CheckBoxDelivery.setVisible(true);
			}
		});
		scrollPane.setViewportView(table);
		llenarTablaCliente();
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(seleccionDeCliente.class.getResource("/prototipos/seleccion de cliente.png")));
			label.setBounds(0, 0, 684, 600);
			contentPanel.add(label);
		}
		{
			btnSeleccionar= new JButton("OK");
			btnSeleccionar.addActionListener(control);
			btnSeleccionar.addMouseListener(new MouseAdapter() 
			{
				//este metodo agrega el cliente a el pedido y debe crear el ticket y la comanda.
				@Override
				public void mouseClicked(MouseEvent arg0)
				{
					try
					{
						cliente=control.getCliente().buscarClientePorDNI(Integer.parseInt(tfAgregarDNI.getText()));
						pedido.setCliente(cliente);
						try {
							control.enviarPedidoMonitor(pedido);
						} catch (IOException e) {
							System.out.println("Fallo conexion con monitor (servidor)");
							e.printStackTrace();
						}
					}
					catch(Exception e)
					{
						cliente=control.getCliente().buscarClientePorDNI(Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString()));
						pedido.setCliente(cliente);
						try {
							control.enviarPedidoMonitor(pedido);
						} catch (IOException e1) {
							System.out.println("Fallo la conexion con el monitor (servidor)");
							e1.printStackTrace();
						}
					}
					if(CheckBoxDelivery.isSelected())
						pedido.setLlevaDelivery(true);
					else
						pedido.setLlevaDelivery(false);
					control.getPedido().quitarPedido(pedido);
					control.getPedido().agregarPedido(pedido);
					JOptionPane.showMessageDialog(null, "Se genero ticket y comanda con el número de pedido: "+seleccionDeCliente.this.pedido.getIdpedido());
				
					//control.getMonitorCocina().nuevoPedido(pedido);
					//new Ticket().generarTicket(seleccionDeCliente.this.pedido);
					//new Comanda().generarComanda(seleccionDeCliente.this.pedido);
					//control.getMonitorCocina().nuevoPedido(pedido);
					//////////ENVIA el NUEVO PEDIDO AL MONITOR//////
					//control.getMonitorCocina().nuevoPedido(pedido);  // no funciona, da null pointer exception
					/////////////////////////////////////////////
					//new Ticket(seleccionDeCliente.this.pedido).generarTicket();
					//new Comanda(seleccionDeCliente.this.pedido).generarComanda();
					dispose();
				}
			});
			btnSeleccionar.setOpaque(false);
			btnSeleccionar.setBounds(161, 530, 168, 34);
			contentPanel.add(btnSeleccionar);
			btnSeleccionar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnSeleccionar);
		}
		{
			JButton btnCancelar = new JButton("Cancel");
			btnCancelar.setOpaque(false);
			btnCancelar.setBounds(378, 530, 161, 34);
			contentPanel.add(btnCancelar);
			btnCancelar.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					dispose();
				}
			});
			btnCancelar.setActionCommand("Cancel");
		}
		{
			btnAgregarCliente= new JButton("New button");
			btnAgregarCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)
				{
					registroDeCliente ventanaRegistrarCliente= new registroDeCliente(_this, control,"otro");
					ventanaRegistrarCliente.setVisible(true);
				}
			});
			btnAgregarCliente.setOpaque(false);
			btnAgregarCliente.setBounds(540, 226, 123, 95);
			contentPanel.add(btnAgregarCliente);
		}
		{
			btnEditarCliente= new JButton("New button");
			btnEditarCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{					
					Integer dniCliente=0;
				if(tfAgregarDNI.getText().length()>7)
				{
					clienteBajaModificacion ventanaModificacionCliente=new clienteBajaModificacion(_this, control);
					ClienteDTO aux=control.getCliente().buscarClientePorDNI(Integer.parseInt(tfAgregarDNI.getText().toString()));
					ventanaModificacionCliente.getTfDni().setText(aux.getDni().toString());
					ventanaModificacionCliente.getTfNombre().setText(aux.getNombre());
					ventanaModificacionCliente.getTfApellido().setText(aux.getApellido());
					ventanaModificacionCliente.getTfCalle().setText(aux.getCalle());
					ventanaModificacionCliente.getTfNumeracion().setText(aux.getNumeracion());
					ventanaModificacionCliente.getTfEntreCalle1().setText(aux.getEntrecalle1());
					ventanaModificacionCliente.getTfEntreCalle2().setText(aux.getEntrecalle2());
					ventanaModificacionCliente.getTfCodPostal().setText(aux.getCodPostal());
					ventanaModificacionCliente.getTfTelefono().setText(aux.getTelefono());
					ventanaModificacionCliente.getTfComentario().setText(aux.getComentario());
					ventanaModificacionCliente.getTfEmail().setText(aux.getEmail());
					ventanaModificacionCliente.setVisible(true);
				}
					
				else 
					dniCliente=Integer.parseInt(model.getValueAt(table.getSelectedRow(), 0).toString());
					if(tfAgregarDNI.getText().compareTo("")==0 && !dniCliente.equals(0))
				{
					clienteBajaModificacion ventanaModificacionCliente=new clienteBajaModificacion(_this, control);
					ClienteDTO aux=control.getCliente().buscarClientePorDNI(dniCliente);
					ventanaModificacionCliente.getTfDni().setText(aux.getDni().toString());
					ventanaModificacionCliente.getTfNombre().setText(aux.getNombre());
					ventanaModificacionCliente.getTfApellido().setText(aux.getApellido());
					ventanaModificacionCliente.getTfCalle().setText(aux.getCalle());
					ventanaModificacionCliente.getTfNumeracion().setText(aux.getNumeracion());
					ventanaModificacionCliente.getTfEntreCalle1().setText(aux.getEntrecalle1());
					ventanaModificacionCliente.getTfEntreCalle2().setText(aux.getEntrecalle2());
					ventanaModificacionCliente.getTfCodPostal().setText(aux.getCodPostal());
					ventanaModificacionCliente.getTfTelefono().setText(aux.getTelefono());
					ventanaModificacionCliente.getTfComentario().setText(aux.getComentario());
					ventanaModificacionCliente.getTfEmail().setText(aux.getEmail());
					ventanaModificacionCliente.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null, "Error el debe haber un dni registrado para poder editarlo");
				}	
			});
			btnEditarCliente.setOpaque(false);
			btnEditarCliente.setBounds(572, 146, 78, 34);
			contentPanel.add(btnEditarCliente);
		}
		

	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////METODOS//////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////
	public JPanel getContentPanel() {
		return contentPanel;
	}

	public ordenDePedido get_padre() {
		return _padre;
	}

	public JButton getBtnEditarCliente() {
		return btnEditarCliente;
	}

	public void setBtnEditarCliente(JButton btnEditarCliente) {
		this.btnEditarCliente = btnEditarCliente;
	}

	public JButton getBtnAgregarCliente() {
		return btnAgregarCliente;
	}



	public void setBtnAgregarCliente(JButton btnAgregarCliente) {
		this.btnAgregarCliente = btnAgregarCliente;
	}



	public void setTfAgregarDNI(JTextField tfAgregarDNI) {
		this.tfAgregarDNI = tfAgregarDNI;
	}



	public void setTfNombrApellido(JTextField tfNombrApellido) {
		this.tfNombrApellido = tfNombrApellido;
	}

	public void setTfDireccionTelefono(JTextField tfDireccionTelefono) {
		this.tfDireccionTelefono = tfDireccionTelefono;
	}

	public void setBtnSeleccionar(JButton btnSeleccionar) {
		this.btnSeleccionar = btnSeleccionar;
	}

	public PedidoDTO getPedido() {
		return pedido;
	}



	public ClienteDTO getCliente() {
		return cliente;
	}



	public seleccionDeCliente get_this() {
		return _this;
	}



	public JTextField getTfAgregarDNI() {
		return tfAgregarDNI;
	}



	public JTextField getTfNombrApellido() {
		return tfNombrApellido;
	}



	public JTextField getTfDireccionTelefono() {
		return tfDireccionTelefono;
	}

	public Controlador getControl() {
		return control;
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



	public void setPedido(PedidoDTO pedido) {
		this.pedido = pedido;
	}



	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}



	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	public void llenarTablaCliente()
	{
		model.setRowCount(0);
		model.setColumnCount(0);
		model.setColumnIdentifiers(nombreColumnas);
		Iterator<ClienteDTO> Iterador = control.getCliente().obtenerClientes().iterator();
		while(Iterador.hasNext())
		{
			ClienteDTO elemento = Iterador.next();
			Object[] fila = {elemento.getDni(),elemento.getApellido(),elemento.getNombre(),elemento.getCalle(), elemento.getNumeracion(),elemento.getTelefono()};
			model.addRow(fila);			
		}
	}
	
	private void validarNumerosDNI(KeyEvent evt, JTextField a)
	{
		char car = evt.getKeyChar();
		if(a.getText().length()>=8) evt.consume();
		if((car<'0' || car>'9')) evt.consume();
	}
}
