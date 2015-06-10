package presentacion.vista;

import java.awt.BorderLayout;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class pedidosPendientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private VentanaPrincipal _padre;
	private pedidosPendientes _pedPendiente;
	private PedidoDTO pedido=new PedidoDTO();
	private JTable table;
	private DefaultTableModel model;
	private  String[] nombreColumnas = {"Pedido","Valor","Estado","Delivery","Itinerario"};
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
		scrollPane.setBounds(24, 113, 295, 377);
		contentPanel.add(scrollPane);

		model = new DefaultTableModel(null,nombreColumnas);
		table = new JTable(model)
		{
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
					String repartidor=table.getValueAt(numFilaSeleccionada, 4).toString();
					if(pedido.getLlevaDelivery() && repartidor.compareTo(" ")==0)
					{
						lAsignarRepartidor.setVisible(true);
						lCobroADelivery.setVisible(false);
						lCobroACliente.setVisible(false);
					}

					else if(pedido.getLlevaDelivery() && table.getValueAt(numFilaSeleccionada, 4).toString().compareTo("")!=0)
					{
						lAsignarRepartidor.setVisible(false);
						lCobroADelivery.setVisible(true);
						lCobroACliente.setVisible(false);
					}
					else
					{
						lAsignarRepartidor.setVisible(false);
						lCobroADelivery.setVisible(false);
						lCobroACliente.setVisible(true);
						
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
		lMarcarComoPreparado.setIcon(new ImageIcon(pedidosPendientes.class.getResource("/Botones/btnMarcarPreparadoHab.png")));
		lMarcarComoPreparado.setBounds(329, 124, 345, 48);
		contentPanel.add(lMarcarComoPreparado);
		
		lAsignarRepartidor= new JLabel("");
		lAsignarRepartidor.setIcon(new ImageIcon(pedidosPendientes.class.getResource("/Botones/btnAsignarRepartidor.png")));
		lAsignarRepartidor.setBounds(329, 184, 345, 48);
		contentPanel.add(lAsignarRepartidor);
		
		lCobroACliente= new JLabel("");
		lCobroACliente.setIcon(new ImageIcon(pedidosPendientes.class.getResource("/Botones/btnRecibirCobro.png")));
		lCobroACliente.setBounds(318, 250, 356, 48);
		contentPanel.add(lCobroACliente);
		
		lRechazarPedido= new JLabel("");
		lRechazarPedido.setIcon(new ImageIcon(pedidosPendientes.class.getResource("/Botones/btnRechazarPedido.png")));
		lRechazarPedido.setBounds(296, 319, 366, 48);
		contentPanel.add(lRechazarPedido);
		
		lModificarPedido= new JLabel("");
		lModificarPedido.setIcon(new ImageIcon(pedidosPendientes.class.getResource("/Botones/btnModificarPedidos.png")));
		lModificarPedido.setBounds(335, 380, 345, 48);
		contentPanel.add(lModificarPedido);
		
		lCobroADelivery= new JLabel("");
		lCobroADelivery.setIcon(new ImageIcon(pedidosPendientes.class.getResource("/Botones/cobroDelivery.png")));
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
						//Quita el pedido del MONITOR antes de modificarlo en la siguiente ventana.
						control.getMonitorCocina().quitarPedido(pedidoCambia);/////////////////////
						//////////////////////////////////////////////////////////////////////////
						ordenDePedido pedidoCambiar=new ordenDePedido(_padre,pedidoCambia,control);
						pedidoCambiar.llenarTabla(pedidoCambia);
						pedidoCambiar.getTfTotal().setText(pedidoCambia.getTotal().toString());	
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
						PedidoDTO aux= control.getPedido().buscarPedidoId(Integer.parseInt(table.getValueAt(numFilaSeleccionada, 0).toString()));
						aux.set_estado("cobrado");
						control.getPedido().quitarPedido(aux);
						control.getPedido().agregarPedido(aux);
						llenarTabla();
					}				
					llenarTabla();
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
						//Quita el pedido del MONITOR ////////////////////////////////////////////
						control.getMonitorCocina().quitarPedido(pedidoCambia);/////////////////////////////
						//////////////////////////////////////////////////////////////////////////
						model.setValueAt("rechazado", numFilaSeleccionada, 2);
						pedidoCambia.set_estado("rechazado");
						control.getPedido().quitarPedido(pedidoCambia);
						control.getPedido().agregarPedido(pedidoCambia);
						llenarTabla();						
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
					if(model.getValueAt(numFilaSeleccionada, 2).toString().compareTo("preparado")==0)
					{
						PedidoDTO cobrado=control.getPedido().buscarPedidoId(Integer.parseInt((String)model.getValueAt(numFilaSeleccionada, 0)));
						registrarCobroDePedido cobroPedido= new registrarCobroDePedido(_pedPendiente,cobrado,control);
						cobroPedido.setVisible(true);
						model.setValueAt("cobrado", numFilaSeleccionada, 2);
						cobrado.set_estado("cobrado");
						control.getPedido().quitarPedido(cobrado);
						control.getPedido().agregarPedido(cobrado);
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
			/*btnAsignarRepartidor= new JButton("New button");
			btnAsignarRepartidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
				{
					int[] filasSeleccionadas=table.getSelectedRows();
					System.out.println(filasSeleccionadas);
					System.out.println("entro");
					//seleccionarRepartidor selecRepartidor=new seleccionarRepartidor(_pedPendiente,control, numFilaSeleccionada);
					//selecRepartidor.setVisible(true);
				}
			});
			btnAsignarRepartidor.setOpaque(false);
			btnAsignarRepartidor.setBounds(339, 188, 326, 40);
			contentPanel.add(btnAsignarRepartidor);*/
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
						//////////QUITA EL PEDIDO DEL MONITOR//////////
						control.getMonitorCocina().quitarPedido(pedido);
						///////////////////////////////////////////////
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
	}
	
	public void llenarTabla() 
	{
		model.setRowCount(0); //Para vaciar la tabla
		model.setColumnCount(0);

		Iterator<PedidoDTO> Iterador = control.getPedido().obtenerPedidos().iterator();
		while(Iterador.hasNext())
		{
			PedidoDTO elemento = Iterador.next();
			if(elemento.getEstado().compareTo("solicitado")==0)
			{
			model.addRow(new String[] {elemento.getIdpedido().toString(),elemento.getTotal().toString(),elemento.get_estado(),Delivery(elemento)," "});
			}
		}
		table.setModel(model);		
	}
	

	private String Delivery(PedidoDTO aux)
	{
		if(aux.getLlevaDelivery())
			return "true";
		else
			return  "-";
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

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}


	
}
