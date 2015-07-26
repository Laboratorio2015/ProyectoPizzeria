package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import presentacion.controlador.Controlador;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import dto.PedidoDTO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;


public class pedidosPendientes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private VentanaPrincipal _padre;
	private pedidosPendientes _pedPendiente;
	private PedidoDTO pedido=new PedidoDTO();
	private JTable table;
	private DefaultTableModel model;
	private  String[] nombreColumnas = {"Pedido","Valor","Estado","Delivery","Itinerario"};
	private JButton btnModificarPedido;
	private JButton btnRegistrarCobroDelivery;
	private JButton btnRechazarPedido;
	private JButton btnRegistrarCobroCliente;
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
		scrollPane.setBounds(23, 113, 297, 379);
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
				PedidoDTO pedido=control.getPedido().buscarPedidoNumeroFecha(Integer.parseInt((String)model.getValueAt(numFilaSeleccionada, 0)),fechaActual());
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
					if(pedido.getLlevaDelivery() )//&& repartidor.compareTo(" ")==0)
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
				else if(pedido.get_estado().compareTo("endelivery")==0)
				{
					lMarcarComoPreparado.setVisible(false);
					lRechazarPedido.setVisible(true);
					lModificarPedido.setVisible(false);
					lAsignarRepartidor.setVisible(false);
					lCobroACliente.setVisible(false);
					lCobroADelivery.setVisible(true);
				}
				else if(pedido.get_estado().compareTo("cobrado")==0)
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
		table.setForeground(new Color(255, 255, 255));
		table.setBackground(new Color(153, 153, 0));
		scrollPane.getViewport().setBackground(new Color(153, 153, 0));
		//encabezado
		JTableHeader headerPedidos = table.getTableHeader();
		headerPedidos.setFont(new Font("Tahoma", Font.BOLD, 10));
		headerPedidos.setForeground(new Color(153, 153, 0));
		headerPedidos.setBackground(new Color(0).GRAY);
		
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
						PedidoDTO pedidoCambia=control.getPedido().buscarPedidoNumeroFecha(Integer.parseInt((String)model.getValueAt(numFilaSeleccionada, 0)), fechaActual());
						
						//Quita el pedido del MONITOR antes de modificarlo en la siguiente ventana.
						pedidoCambia.setEstado("modificado");
						try {
							control.enviarPedidoMonitor(pedidoCambia);
						} catch (IOException e) {
							System.out.println("Error para enviar pedido a monitor (servidor)");
							e.printStackTrace();
						}
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
			btnRegistrarCobroDelivery= new JButton("New button");
			btnRegistrarCobroDelivery.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					if(model.getValueAt(numFilaSeleccionada, 2).toString()!="rechazado")
					{
						registrarCobroManualmente cobroManual=new registrarCobroManualmente(_pedPendiente,control);
						cobroManual.setVisible(true);
					}				
					llenarTabla();
					borrarBotones();
				}
			});
			btnRegistrarCobroDelivery.setOpaque(false);
			btnRegistrarCobroDelivery.setBounds(335, 448, 328, 40);
			contentPanel.add(btnRegistrarCobroDelivery);
		}
		{
			btnRechazarPedido = new JButton("New button");
			btnRechazarPedido.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent arg0)
				{
						numFilaSeleccionada=table.getSelectedRow();
						PedidoDTO pedidoCambia=control.getPedido().buscarPedidoNumeroFecha(Integer.parseInt((String)model.getValueAt(numFilaSeleccionada, 0)), fechaActual());
						model.setValueAt("rechazado", numFilaSeleccionada, 2);
						control.getPedido().actualizarPedido(pedidoCambia, "rechazado");
						//Quita el pedido del MONITOR ////////////////////////////////////////////
						try {					
							pedidoCambia.setEstado("rechazado");
							control.enviarPedidoMonitor(pedidoCambia);
						} catch (IOException e)
						{
							System.out.println("Fallo conexion con monitor (servidor)");
							e.printStackTrace();
						}
						llenarTabla();
						borrarBotones();
				}
			});
			
			btnRechazarPedido.setOpaque(false);
			btnRechazarPedido.setBounds(335, 324, 328, 40);
			contentPanel.add(btnRechazarPedido);
		}
		{
			btnRegistrarCobroCliente= new JButton("New button");
			btnRegistrarCobroCliente.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					numFilaSeleccionada=table.getSelectedRow();
					if(model.getValueAt(numFilaSeleccionada, 2).toString().compareTo("preparado")==0)
					{
						PedidoDTO cobrado=control.getPedido().buscarPedidoNumeroFecha(Integer.parseInt((String)model.getValueAt(numFilaSeleccionada, 0)),fechaActual());
						registrarCobroDePedido cobroPedido= new registrarCobroDePedido(_pedPendiente,cobrado,control,numFilaSeleccionada);
						cobroPedido.setVisible(true);
					}
					else if(model.getValueAt(numFilaSeleccionada, 2).toString()=="solicitado")
					{
						JOptionPane.showMessageDialog(null, "Error, el estado del pedido debe ser Preparado");
					}
					else if(model.getValueAt(numFilaSeleccionada, 2).toString()=="rechazado")
						JOptionPane.showMessageDialog(null, "Error, el pedido se encuentra Rechazado");
					llenarTabla();
					borrarBotones();
				}
			});
			btnRegistrarCobroCliente.setOpaque(false);
			btnRegistrarCobroCliente.setBounds(335, 254, 328, 40);
			contentPanel.add(btnRegistrarCobroCliente);
		}
		{
			btnAsignarRepartidor= new JButton("New button");
			btnAsignarRepartidor.addMouseListener(new MouseAdapter() 
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					int[] numeros=table.getSelectedRows();
					int[] pedidos=new int[numeros.length];
					ArrayList<PedidoDTO> nuevo=new ArrayList<PedidoDTO>();
					for(int i=0;i<numeros.length;i++)
					{
						nuevo.add(control.getPedido().buscarPedidoNumeroFecha(Integer.parseInt(model.getValueAt(numeros[i], 0).toString()), fechaActual()));
					}
					seleccionarRepartidor selecRepartidor=new seleccionarRepartidor(_pedPendiente,control,numeros,nuevo);
					selecRepartidor.setVisible(true);
					llenarTabla();
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
						table.setModel(model);
						pedido=control.getPedido().buscarPedidoNumeroFecha(Integer.parseInt((String)model.getValueAt(numFilaSeleccionada, 0)), fechaActual());
						pedido.setEstado("preparado");
						control.getPedido().actualizarPedido(pedido, "preparado");
						//////////QUITA EL PEDIDO DEL MONITOR//////////
						try {
							control.enviarPedidoMonitor(pedido);
						} catch (IOException e) {
							System.out.println("Fallo conexion con monitor (servidor)");
							e.printStackTrace();
						}
						///////////////////////////////////////////////
						
					}
					else if(model.getValueAt(numFilaSeleccionada, 2).toString().compareTo("rechazado")==0)
						JOptionPane.showMessageDialog(null, "Error, el pedido se encuentra Rechazado");
					borrarBotones();
				}
			});
			btnMarcarComoPreparado.setOpaque(false);
			btnMarcarComoPreparado.setBounds(335, 129, 328, 40);
			contentPanel.add(btnMarcarComoPreparado);
		}	
	}
	
	public void llenarTabla() 
	{
		Calendar c1 = GregorianCalendar.getInstance();
		String fecha=(c1.getTime().getDate()+"-"+(c1.getTime().getMonth()+1)+"-"+(c1.getTime().getYear()+1900));
		model.setRowCount(0); //Para vaciar la tabla
		model.setColumnCount(0);
		model.setColumnIdentifiers(nombreColumnas);
		Iterator<PedidoDTO> Iterador = control.getPedido().obtenerPedidosDeFecha(fecha).iterator();
		while(Iterador.hasNext())
		{
			PedidoDTO elemento = Iterador.next();
			if(elemento.getEstado().compareTo("rechazado")!=0 && elemento.getEstado().compareTo("cobrado")!=0)
			{
				if(elemento.getEstado().compareTo("endelivery")==0)
				{
					int itinerario=control.getItinerario().buscarItinerarioPorPedido(elemento.getIdpedido(), fechaActual());
					model.addRow(new String[] {elemento.getNumPedido().toString(),elemento.getTotal().toString(),elemento.get_estado(),Delivery(elemento),itinerario+""});
				}
				else
					model.addRow(new String[] {elemento.getNumPedido().toString(),elemento.getTotal().toString(),elemento.get_estado(),Delivery(elemento),""});
			}
		}
		table.setModel(model);		
	}
	private void borrarBotones() 
	{
		lMarcarComoPreparado.setVisible(false);
		lRechazarPedido.setVisible(false);
		lModificarPedido.setVisible(false);
		lAsignarRepartidor.setVisible(false);
		lCobroACliente.setVisible(false);
		lCobroADelivery.setVisible(false);
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
	
	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void setNombreColumnas(String[] nombreColumnas) {
		this.nombreColumnas = nombreColumnas;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public String fechaActual()
	{
		Calendar c1 = GregorianCalendar.getInstance();
		String fecha=(c1.getTime().getDate()+"-"+(c1.getTime().getMonth()+1)+"-"+(c1.getTime().getYear()+1900));
		return fecha;
	}

	
}
