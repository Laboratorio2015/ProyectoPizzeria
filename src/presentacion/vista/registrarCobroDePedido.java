package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import dto.PedidoDTO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

import presentacion.controlador.Controlador;

import main.Main;
import modelo.Pedidos;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class registrarCobroDePedido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private pedidosPendientes _padre;
	private registrarCobroDePedido _this;
	private PedidoDTO pedid;
	private JTextField tfCliente;
	private JTextField tfMontoPedido;

	public registrarCobroDePedido(pedidosPendientes padre, PedidoDTO pedido, Controlador control) 
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
		tfCliente.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tfCliente.setBackground(new Color(204, 204, 0));
		tfCliente.setEditable(false);
		tfCliente.setBounds(103, 113, 280, 20);
		contentPanel.add(tfCliente);
		tfCliente.setColumns(10);
		
		tfMontoPedido = new JTextField();
		tfMontoPedido.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		tfMontoPedido.setBackground(new Color(204, 204, 0));
		tfMontoPedido.setEditable(false);
		tfMontoPedido.setColumns(10);
		tfMontoPedido.setBounds(162, 154, 221, 20);
		
		//seteados los datos
		tfCliente.setText(pedido.getCliente().getApellido()+" "+pedido.getCliente().getNombre());
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
					
					pedid.set_estado("cobrado");
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
