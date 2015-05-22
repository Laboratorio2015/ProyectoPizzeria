package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.MouseEvent;

import Monitor.Cocina.PadreMonitor;

import modelo.Clientes;
import modelo.Items;
import modelo.Pedidos;
import modelo.Productos;
import dto.ClienteDTO;
import dto.ItemDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import presentacion.vista.VentanaPrincipal;
import presentacion.vista.ordenDePedido;
import presentacion.vista.ordenarMatPrima;
import presentacion.vista.pedidosPendientes;
import presentacion.vista.registrarCobroDePedido;
import presentacion.vista.registrarCobroManualmente;
import presentacion.vista.registroDeCliente;
import presentacion.vista.seleccionDeCliente;
import presentacion.vista.seleccionarRepartidor;

public class Controlador implements ActionListener
{
	//ventanas
	private VentanaPrincipal ventana;
	private ordenDePedido ventanaPedido;
	private pedidosPendientes ventanaPedPendiente;
	private seleccionDeCliente ventanaCliente;
	private ordenarMatPrima ventanaMatPrima;
	private registrarCobroDePedido ventanaRegCobroPedido;
	private registrarCobroManualmente ventanaRegCobroManual;
	private seleccionarRepartidor ventanaSeleccionRepartidor;
	private registroDeCliente ventanaRegCliente;
	private PadreMonitor monitorCocina;
	
	//dto
	private List<PedidoDTO> listaPedidos;
	private List<ProductoDTO> listaProductos;
	private List<ClienteDTO> listaClientes;
	private List<ItemDTO> listaItems;
	
	//modelo
	private Productos producto;
	private Pedidos pedido;
	private Clientes cliente;
	private Items item;
	
	public Controlador(VentanaPrincipal ventana, Pedidos pedido, Clientes cliente,Productos producto, Items item) 
	{
		this.ventana=ventana;
		this.pedido=pedido;
		this.cliente=cliente;
		this.item=item;
		this.producto=producto;
		
		this.listaClientes=this.cliente.obtenerClientes();
		this.listaProductos=this.producto.obtenerProducto();
		this.listaItems=this.item.obtenerItems();
		this.listaPedidos=this.pedido.obtenerPedidos();
		
		this.ventana.getBtnIngresarPedido().addActionListener(this);
		this.ventana.getBtnPedidosPendientes().addActionListener(this);
		
	}
	
	public void inicializar()
	{
		this.monitorCocina=new PadreMonitor(pedido);
		this.ventana.show();
		//this.llenarListas();
		//this.llenarTextField();
		//ventana.actualizarPedidoPend();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if(e.getSource() == this.ventana.getBtnIngresarPedido())
		{
			ventanaPedido=new ordenDePedido(ventana,this);
			this.ventanaPedido.getBtnOrdenar().addActionListener(this);
			this.ventanaPedido.setVisible(true);
		}
		else if(e.getSource() == this.ventana.getBtnPedidosPendientes())
		{
			ventanaPedPendiente=new pedidosPendientes(ventana, this);
			ventanaPedPendiente.llenarTabla();
			this.ventanaPedPendiente.setVisible(true);
		}
		else if(e.getSource()==this.ventanaPedido.getBtnOrdenar())
		{
			PedidoDTO nuevoPedido=new PedidoDTO();
			nuevoPedido.setIdpedido(this.getListaPedidos().size()+1);
			nuevoPedido.set_estado("solicitado");
			nuevoPedido.setTotal(Integer.parseInt(this.ventanaPedido.getTfTotal().getText()));
			nuevoPedido.set_comanda(nuevoPedido.getIdpedido());
			nuevoPedido.set_ticket(nuevoPedido.getIdpedido());
			nuevoPedido.setProductos(generarListaItems());
			this.ventanaCliente=new seleccionDeCliente(this,nuevoPedido);
			this.ventanaCliente.setVisible(true);
			vaciarFormulario();
			this.ventanaPedido.dispose();
		}
	}

	public List<PedidoDTO> getListaPedidos() {
		return listaPedidos;
	}

	public void setListaPedidos(List<PedidoDTO> listaPedidos) {
		this.listaPedidos = listaPedidos;
	}

	public List<ProductoDTO> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<ProductoDTO> listaProductos) {
		this.listaProductos = listaProductos;
	}


