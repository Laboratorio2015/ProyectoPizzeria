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

import main.Main;

import com.mxrck.autocompleter.TextAutoCompleter;

import dto.ClienteDTO;
import dto.PedidoDTO;

import javax.swing.border.MatteBorder;

import presentacion.controlador.Controlador;
import presentacion.reportes.Comanda;
import presentacion.reportes.ReporteTicket;
import presentacion.reportes.Ticket;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class seleccionDeCliente extends JDialog {

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
	private Comanda comanda=new Comanda();
	public seleccionDeCliente(final Controlador control,final PedidoDTO pedido)
	{
		setModal(true);
		this.pedido=pedido;
		_this=this;
		this.control=control;
		setMinimumSize(new Dimension(700, 600));
		setBounds(500, 100, 700, 611);
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
				if(tfAgregarDNI.getText().length()>7)
				{
				cliente=control.getCliente().buscarCliente(Integer.parseInt(tfAgregarDNI.getText()));
				tfNombrApellido.setText("Apellido y Nombre: "+cliente.getApellido()+""+ cliente.getNombre());
				tfDireccionTelefono.setText("Direccion: "+cliente.getDireccion()+""+cliente.getNumeracion());
				}
			}
		});
		tfAgregarDNI.setBounds(91, 165, 174, 23);
		contentPanel.add(tfAgregarDNI);
		tfAgregarDNI.setColumns(10);
		
		TextAutoCompleter autoCompletar=new TextAutoCompleter(tfAgregarDNI);
		{
			tfDireccionTelefono = new JTextField();
			tfDireccionTelefono.setBackground(new Color(204, 204, 0));
			tfDireccionTelefono.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfDireccionTelefono.setEditable(false);
			tfDireccionTelefono.setColumns(10);
			tfDireccionTelefono.setBounds(348, 166, 301, 20);
			contentPanel.add(tfDireccionTelefono);
		}
		{
			tfNombrApellido = new JTextField();
			tfNombrApellido.setBackground(new Color(204, 204, 0));
			tfNombrApellido.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
			tfNombrApellido.setEditable(false);
			tfNombrApellido.setBounds(348, 146, 301, 20);
			contentPanel.add(tfNombrApellido);
			tfNombrApellido.setColumns(10);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(seleccionDeCliente.class.getResource("/prototipos/seleccion de cliente.png")));
			label.setBounds(0, 0, 684, 572);
			contentPanel.add(label);
		}
		{
			btnSeleccionar= new JButton("OK");
			btnSeleccionar.addActionListener((ActionListener) control);
			btnSeleccionar.addMouseListener(new MouseAdapter() 
			{
				//este metodo agrega el cliente a el pedido y debe crear el ticket y la comanda.
				@Override
				public void mouseClicked(MouseEvent arg0)
				{
					seleccionDeCliente.this.pedido.setCliente(cliente);
					//control.getPedido().agregarPedido(seleccionDeCliente.this.pedido);
					JOptionPane.showMessageDialog(null, "Se genero ticket y comanda con el número de pedido: "+seleccionDeCliente.this.pedido.getIdpedido());
					control.getMonitorCocina().nuevoPedido(seleccionDeCliente.this.pedido);
					new ReporteTicket(seleccionDeCliente.this.pedido.getProductos()).mostrar();
					//new Ticket().generarTicket(seleccionDeCliente.this.pedido);
					//new Comanda().generarComanda(seleccionDeCliente.this.pedido);
					dispose();
				}
			});
			btnSeleccionar.setOpaque(false);
			btnSeleccionar.setBounds(118, 527, 168, 34);
			contentPanel.add(btnSeleccionar);
			btnSeleccionar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnSeleccionar);
		}
		{
			JButton btnCancelar = new JButton("Cancel");
			btnCancelar.setOpaque(false);
			btnCancelar.setBounds(334, 527, 161, 34);
			contentPanel.add(btnCancelar);
			btnCancelar.addActionListener(new ActionListener() 
			{
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
			btnAgregarCliente.setBounds(287, 330, 82, 85);
			contentPanel.add(btnAgregarCliente);
		}
		{
			btnEditarCliente= new JButton("New button");
			btnEditarCliente.setOpaque(false);
			btnEditarCliente.setBounds(571, 114, 78, 29);
			contentPanel.add(btnEditarCliente);
		}
		autoCompletar.setCaseSensitive(false);
		autoCompletar.addItems(control.getCliente().dniClientes());
	}

	
	
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



	public JButton getBtnSeleccionar() {
		return btnSeleccionar;
	}

	private void validarNumerosDNI(KeyEvent evt, JTextField a)
	{
		char car = evt.getKeyChar();
		if(a.getText().length()>=8) evt.consume();
		if((car<'0' || car>'9')) evt.consume();
	}
}
