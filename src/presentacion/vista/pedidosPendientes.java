package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class pedidosPendientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private VentanaPrincipal _padre;
	private pedidosPendientes _pedPendiente;
	private PedidoDTO pedido=new PedidoDTO();
	private JTable table;
	private DefaultTableModel model;
	private JButton btnModificarPedido;
	private JButton btnRegistrarCobroManual;
	private JButton btnRechazarPedido;
	private JButton btnRegistrarCobroPedido;
	private JButton btnAsignarRepartidor;
	private JButton btnMarcarComoPreparado;
	private JLabel lMarcarComoPreparado ;
	private JLabel lAsignarRepartidor;
	private JLabel lCobroACliente;
	private JLabel lRechazarPedido;
	private JLabel lModificarPedido;
	private JLabel lCobroADelivery;
	private int numFilaSeleccionada;
	private Controlador control;

	public pedidosPendientes(VentanaPrincipal padre, final Controlador control) 
	{
		setModal(true);
		padre=_padre;
		this.control=control;
		_pedPendiente=this;
		setMinimumSize(new Dimension(700, 600));
		setBounds(300, 70, 700, 620);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 124, 253, 362);
		contentPanel.add(scrollPane);

		
		table = new JTable(){
		 public boolean isCellEditable(int rowIndex, int colIndex) {
	        return false;
	    }};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				numFilaSeleccionada=table.getSelectedRow();
				PedidoDTO pedido=control.getPedido().buscarPedidoId(Integer.parseInt((String)model.getValueAt(numFilaSeleccionada, 0)));
				if (pedido.get_estado().compareTo("solicitado")==0)
				{
					lMarcarComoPreparado.setVisible(true);
					lRechazarPedido.setVisible(true);
					lModificarPedido.setVisible(true);
					lAsignarRepartidor.setVisible(false);
					lCobroACliente.setVisible(false);
					lCobroADelivery.setVisible(false);
					
				}
				else if (pedido.get_estado().compareTo("preparado")==0)
				{
					lMarcarComoPreparado.setVisible(false);
					lRechazarPedido.setVisible(true);
					lModificarPedido.setVisible(false);
					lCobroACliente.setVisible(true);
					if(pedido.getLlevaDelivery())
					{
						lAsignarRepartidor.setVisible(true);
						lCobroADelivery.setVisible(true);	
						
					}
					else
					{
						lAsignarRepartidor.setVisible(false);
						lCobroADelivery.setVisible(true);
						
					}
				}
				else if(pedido.get_estado().compareTo("rechazado")==0)
				{
					lMarcarComoPreparado.setVisible(false);
					lRechazarPedido.setVisible(false);
					lModificarPedido.setVisible(false);
					lAsignarRepartidor.setVisible(false);
					lCobroACliente.setVisible(false);
					lCobroADelivery.setVisible(false);
				}
			}
		});
		scrollPane.setRowHeaderView(table);
		scrollPane.setViewportView(table);
		
		lMarcarComoPreparado= new JLabel("");
		lMarcarComoPreparado.setIcon(new ImageIcon(pedidosPendientes.class.getResource("/prototipos/Botones/btnMarcarPreparadoHab.png")));
		lMarcarComoPreparado.setBounds(329, 124, 345, 48);
		contentPanel.add(lMarcarComoPreparado);
		
		lAsignarRepartidor= new JLabel("");
		lAsignarRepartidor.setIcon(new ImageIcon(pedidosPendientes.class.getResource("/prototipos/Botones/btnAsignarRepartidor.png")));
		lAsignarRepartidor.setBounds(329, 184, 345, 48);
		contentPanel.add(lAsignarRepartidor);
		
		lCobroACliente= new JLabel("");
		lCobroACliente.setIcon(new ImageIcon(pedidosPendientes.class.getResource("/prototipos/Botones/btnRecibirCobro.png")));
		lCobroACliente.setBounds(318, 250, 356, 48);
		contentPanel.add(lCobroACliente);
		
		lRechazarPedido= new JLabel("");
		lRechazarPedido.setIcon(new ImageIcon(pedidosPendientes.class.getResource("/prototipos/Botones/btnRechazarPedido.png")));
		lRechazarPedido.setBounds(296, 319, 366, 48);
		contentPanel.add(lRechazarPedido);
		
		lModificarPedido= new JLabel("");
		lModificarPedido.setIcon(new ImageIcon(pedidosPendientes.class.getResource("/prototipos/Botones/btnModificarPedidos.png")));
		lModificarPedido.setBounds(335, 380, 345, 48);
		contentPanel.add(lModificarPedido);
		
		lCobroADelivery= new JLabel("");
		lCobroADelivery.setIcon(new ImageIcon(pedidosPendientes.class.getResource("/prototipos/Botones/cobroDelivery.png")));
		lCobroADelivery.setBounds(335, 442, 345, 48);
		contentPanel.add(lCobroADelivery);
		
		lMarcarComoPreparado.setVisible(false);
		lRechazarPedido.setVisible(false);
		lModificarPedido.setVisible(false);
		lAsignarRepartidor.setVisible(false);
		lCobroACliente.setVisible(false);
		lCobroADelivery.setVisible(false);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(pedidosPendientes.class.getResource("/prototipos/pedidos Pendientes.png")));
			label.setBounds(0, 0, 684, 581);
			contentPanel.add(label);
		}
		{
			JButton btnCancelar = new JButton("Cancel");
			btnCancelar.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					vaciarTabla();
					dispose();
				}
			});
			btnCancelar.setOpaque(false);
			btnCancelar.setBounds(255, 530, 152, 34);
			contentPanel.add(btnCancelar);
			btnCancelar.setActionCommand("Cancel");
		}
		{
			btnModificarPedido= new JButton("New button");
			btnModificarPedido.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					numFilaSeleccionada=table.getSelectedRow();
					if(model.getValueAt(numFilaSeleccionada, 2).toString().compareTo("solicitado")==0 && model.getValueAt(numFilaSeleccionada, 2).toString().compareTo("rechazado")!=0)
					{
						numFilaSeleccionada=table.getSelectedRow();
						PedidoDTO pedidoCambia=control.getPedido().buscarPedidoId(Integer.parseInt((String)model.getValueAt(numFilaSeleccionada, 0)));
						ordenDePedido pedidoCambiar=new ordenDePedido(_padre,pedidoCambia,control);
						pedidoCambiar.llenarTabla(pedidoCambia);
						pedidoCambiar.getTfTotal().setText(pedidoCambia.getTotal().toString());
						control.getMonitorCocina().quitarPedido(pedidoCambia);
						pedidoCambiar.setVisible(true);
						dispose();
					}
					else if(model.getValueAt(numFilaSeleccionada, 2).toString()=="rechazado")
						JOptionPane.showMessageDialog(null, "Error, el pedido se encuentra Rechazado");
					else 
					{
						JOptionPane.showMessageDialog(null, "Error, solo se pude modificar un pedido en estado Solicitado");
					}
				}
			});
			btnModificarPedido.setOpaque(false);
			btnModificarPedido.setBounds(345, 384, 318, 40);
			contentPanel.add(btnModificarPedido);
		}
		{
			btnRegistrarCobroManual= new JButton("New button");
			btnRegistrarCobroManual.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					if(model.getValueAt(numFilaSeleccionada, 2).toString()!="rechazado")
					{
					registrarCobroManualmente cobroManual=new registrarCobroManualmente(_pedPendiente);
					cobroManual.setVisible(true);
					}
				}
			});
			btnRegistrarCobroManual.setOpaque(false);
			btnRegistrarCobroManual.setBounds(335, 448, 328, 40);
			contentPanel.add(btnRegistrarCobroManual);
		}
		{
			btnRechazarPedido = new JButton("New button");
			btnRechazarPedido.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent arg0)
				{
						numFilaSeleccionada=table.getSelectedRow();
						PedidoDTO pedidoCambia=control.getPedido().buscarPedidoId(Integer.parseInt((String)model.getValueAt(numFilaSeleccionada, 0)));
						control.getMonitorCocina().quitarPedido(pedidoCambia);
						model.setValueAt("rechazado", numFilaSeleccionada, 2);
						pedidoCambia.set_estado("rechazado");
				}
			});
			
			btnRechazarPedido.setOpaque(false);
			btnRechazarPedido.setBounds(335, 324, 328, 40);
			contentPanel.add(btnRechazarPedido);
		}
		{
			btnRegistrarCobroPedido= new JButton("New button");
			btnRegistrarCobroPedido.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					numFilaSeleccionada=table.getSelectedRow();
					if(model.getValueAt(numFilaSeleccionada, 2).toString()=="preparado"&& model.getValueAt(numFilaSeleccionada, 2).toString()!="rechazado")
					{
						registrarCobroDePedido cobroPedido= new registrarCobroDePedido(_pedPendiente,control.getPedido().buscarPedidoId(Integer.parseInt((String)model.getValueAt(numFilaSeleccionada, 0))),control);
						cobroPedido.setVisible(true);
					}
					else if(model.getValueAt(numFilaSeleccionada, 2).toString()=="solicitado")
					{
						JOptionPane.showMessageDialog(null, "Error, el estado del pedido debe ser Preparado");
					}
					else if(model.getValueAt(numFilaSeleccionada, 2).toString()=="rechazado")
						JOptionPane.showMessageDialog(null, "Error, el pedido se encuentra Rechazado");
				}
			});
			btnRegistrarCobroPedido.setOpaque(false);
			btnRegistrarCobroPedido.setBounds(335, 254, 328, 40);
			contentPanel.add(btnRegistrarCobroPedido);
		}
		{
			btnAsignarRepartidor= new JButton("New button");
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
			btnAsignarRepartidor.setBounds(339, 188, 326, 40);
			contentPanel.add(btnAsignarRepartidor);
		}
		{
			btnMarcarComoPreparado= new JButton("New button");
			btnMarcarComoPreparado.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent arg0)
				{
					numFilaSeleccionada=table.getSelectedRow();
					if(model.getValueAt(numFilaSeleccionada, 2).toString().compareTo("solicitado")==0)
					{
						model.setValueAt("preparado", numFilaSeleccionada, 2);
						pedido=control.getPedido().buscarPedidoId(Integer.parseInt((String)model.getValueAt(numFilaSeleccionada, 0)));
						control.getPedido().quitarPedido(pedido);
						pedido.set_estado("preparado");
						control.getPedido().agregarPedido(pedido);
						control.getMonitorCocina().quitarPedido(pedido);
					}
					else if(model.getValueAt(numFilaSeleccionada, 2).toString().compareTo("rechazado")==0)
						JOptionPane.showMessageDialog(null, "Error, el pedido se encuentra Rechazado");
					else
						System.out.println("no funca");
				}
			});
			btnMarcarComoPreparado.setOpaque(false);
			btnMarcarComoPreparado.setBounds(335, 129, 328, 40);
			contentPanel.add(btnMarcarComoPreparado);
		}
		
		model= new DefaultTableModel();
		model.addColumn("Pedido");
		model.addColumn("Valor");
		model.addColumn("Estado");
	}
	
	

	public void llenarTabla() 
	{
		Iterator<PedidoDTO> Iterador = control.getPedido().obtenerPedidos().iterator();
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
