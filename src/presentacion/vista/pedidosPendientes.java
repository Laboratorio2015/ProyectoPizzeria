package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import presentacion.controlador.Controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import dto.PedidoDTO;
import dto.ProductoDTO;

import main.Main;
import modelo.Pedidos;

public class pedidosPendientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private VentanaPrincipal _padre;
	private pedidosPendientes _pedPendiente;
	private Controlador control;
	private PedidoDTO pedido=new PedidoDTO();
	private JTable table;
	private DefaultTableModel model;
	private int numFilaSeleccionada;

	public pedidosPendientes(VentanaPrincipal padre) 
	{
		setModal(true);
		padre=_padre;
		_pedPendiente=this;
		setMinimumSize(new Dimension(700, 600));
		setBounds(300, 70, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 131, 244, 339);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setRowHeaderView(table);
		scrollPane.setViewportView(table);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(pedidosPendientes.class.getResource("/prototipos/pedidos Pendientes.png")));
			label.setBounds(0, 0, 684, 561);
			contentPanel.add(label);
		}
		{
			JButton btnEnviar = new JButton("OK");
			btnEnviar.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					dispose();
				}
			});
			btnEnviar.setOpaque(false);
			btnEnviar.setBounds(163, 516, 118, 34);
			contentPanel.add(btnEnviar);
			btnEnviar.setActionCommand("OK");
			getRootPane().setDefaultButton(btnEnviar);
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
			btnCancelar.setBounds(430, 516, 124, 34);
			contentPanel.add(btnCancelar);
			btnCancelar.setActionCommand("Cancel");
		}
		{
			JButton btnModificarPedido = new JButton("New button");
			btnModificarPedido.setOpaque(false);
			btnModificarPedido.setBounds(335, 376, 328, 40);
			contentPanel.add(btnModificarPedido);
		}
		{
			JButton btnRegistrarCobroManual = new JButton("New button");
			btnRegistrarCobroManual.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					registrarCobroManualmente cobroManual=new registrarCobroManualmente(_pedPendiente);
					cobroManual.setVisible(true);
				}
			});
			btnRegistrarCobroManual.setOpaque(false);
			btnRegistrarCobroManual.setBounds(335, 441, 328, 40);
			contentPanel.add(btnRegistrarCobroManual);
		}
		{
			JButton btnRechazarPedido = new JButton("New button");
			btnRechazarPedido.setOpaque(false);
			btnRechazarPedido.setBounds(335, 312, 328, 40);
			contentPanel.add(btnRechazarPedido);
		}
		{
			JButton btnRegistrarCobroPedido = new JButton("New button");
			btnRegistrarCobroPedido.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					numFilaSeleccionada=table.getSelectedRow();
					if(model.getValueAt(numFilaSeleccionada, 2).toString()=="preparado")
					{
						registrarCobroDePedido cobroPedido= new registrarCobroDePedido(_pedPendiente,PedidoDTO.buscarPedido(Integer.parseInt((String)model.getValueAt(numFilaSeleccionada, 0)), Main.listaPedidos.pedidos));
						cobroPedido.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Error, el estado del pedido debe ser Preparado");
					}
				}
			});
			btnRegistrarCobroPedido.setOpaque(false);
			btnRegistrarCobroPedido.setBounds(335, 247, 328, 40);
			contentPanel.add(btnRegistrarCobroPedido);
		}
		{
			JButton btnAsignarRepartidor = new JButton("New button");
			btnAsignarRepartidor.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					seleccionarRepartidor selecRepartidor=new seleccionarRepartidor(_pedPendiente);
					selecRepartidor.setVisible(true);
				}
			});
			btnAsignarRepartidor.setOpaque(false);
			btnAsignarRepartidor.setBounds(335, 183, 328, 40);
			contentPanel.add(btnAsignarRepartidor);
		}
		{
			JButton btnMarcarComoPreparado = new JButton("New button");
			btnMarcarComoPreparado.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent arg0)
				{
					numFilaSeleccionada=table.getSelectedRow();
					if(model.getValueAt(numFilaSeleccionada, 2).toString()=="solicitado")
						model.setValueAt("preparado", numFilaSeleccionada, 2);
					pedido=PedidoDTO.buscarPedido(Integer.parseInt((String)model.getValueAt(numFilaSeleccionada, 0)), Main.listaPedidos.pedidos);
					Pedidos.buscarPedido(Main.listaPedidos, pedido).set_estado("preparado");
					Main.monitorCocina.quitarPedido(pedido);
				}
			});
			btnMarcarComoPreparado.setOpaque(false);
			btnMarcarComoPreparado.setBounds(335, 117, 328, 40);
			contentPanel.add(btnMarcarComoPreparado);
		}
		
		model= new DefaultTableModel();
		model.addColumn("Pedido");
		model.addColumn("Valor");
		model.addColumn("Estado");
	}

	public void llenarTabla() 
	{
		Iterator<PedidoDTO> Iterador = Main.listaPedidos.pedidos.iterator();
		while(Iterador.hasNext())
		{
			PedidoDTO elemento = Iterador.next();
			model.addRow(new String[] {elemento.getIdpedido().toString(),elemento.getTotal().toString(),elemento.get_estado()});			
		}
		table.setModel(model);		
	}
	
	public void vaciarTabla()
	{
		       for (int i = 0; i < table.getRowCount(); i++) {
		           model.removeRow(i);
		           i-=1;
		       }
	}

	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public int getNumFilaSeleccionada() 
	{
		return numFilaSeleccionada;
	}
	
}
