package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import dto.PedidoDTO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

import main.Main;
import modelo.Pedidos;

public class registrarCobroDePedido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private pedidosPendientes _padre;
	private registrarCobroDePedido _this;
	private PedidoDTO pedid;
	private JTextField tfCliente;
	private JTextField tfRepartidor;
	private JTextField tfMontoPedido;

	public registrarCobroDePedido(pedidosPendientes padre, PedidoDTO pedido) 
	{
		setModal(true);
		_padre=padre;
		_this=this;
		this.pedid=pedido;
		setMinimumSize(new Dimension(500, 280));
		setBounds(500, 300, 511, 307);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		tfCliente = new JTextField();
		tfCliente.setEditable(false);
		tfCliente.setBounds(123, 113, 260, 20);
		contentPanel.add(tfCliente);
		tfCliente.setColumns(10);
		
		tfRepartidor = new JTextField();
		tfRepartidor.setEditable(false);
		tfRepartidor.setColumns(10);
		tfRepartidor.setBounds(123, 144, 260, 20);
		contentPanel.add(tfRepartidor);
		
		tfMontoPedido = new JTextField();
		tfMontoPedido.setEditable(false);
		tfMontoPedido.setColumns(10);
		tfMontoPedido.setBounds(162, 170, 221, 20);
		tfCliente.setText(pedido.getCliente().getNombre()+"   "+pedido.getCliente().getApellido());
		tfMontoPedido.setText(pedido.getTotal().toString());
		
		contentPanel.add(tfMontoPedido);
		{
			JLabel label = new JLabel("");
			label.setBounds(0, 0, 495, 280);
			contentPanel.add(label);
			label.setIcon(new ImageIcon(registrarCobroDePedido.class.getResource("/prototipos/registrarCobro de pedido.png")));
		}
		{
			JButton btnRegistrar = new JButton("OK");
			btnRegistrar.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					//_padre.getModel().setValueAt("cobrado", _padre.getNumFilaSeleccionada(), 2);
					//PedidoDTO pedid=PedidoDTO.buscarPedido(Integer.parseInt((String)_padre.getModel().getValueAt(_padre.getNumFilaSeleccionada(), 0)), Main.listaPedidos.pedidos);
					Pedidos.buscarPedido(Main.listaPedidos, pedid).set_estado("cobrado");
					_padre.vaciarTabla();
					_padre.llenarTabla();
					dispose();
				}
			});
			btnRegistrar.setOpaque(false);
			btnRegistrar.setBounds(123, 222, 112, 28);
			contentPanel.add(btnRegistrar);
			btnRegistrar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnRegistrar);
		}
		{
			JButton btnCancelar = new JButton("Cancel");
			btnCancelar.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					dispose();
				}
			});
			btnCancelar.setOpaque(false);
			btnCancelar.setBounds(271, 222, 112, 28);
			contentPanel.add(btnCancelar);
			btnCancelar.setActionCommand("Cancel");
		}
	}
}
