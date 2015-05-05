package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.MouseEvent;

import modelo.Clientes;
import modelo.Pedidos;
import modelo.Productos;
import dto.ClienteDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import presentacion.vista.VentanaPrincipal;
import presentacion.vista.ordenDePedido;
import presentacion.vista.pedidosPendientes;
import presentacion.vista.seleccionDeCliente;

public class Controlador 
{
	private VentanaPrincipal ventana;
	private ordenDePedido ventanaPedido;
	private seleccionDeCliente ventanaCliente;
	private pedidosPendientes ventanaPedPendiente;
	
	private List<PedidoDTO> listaPedidos;
	private List<ProductoDTO> listaProductos;
	private List<ClienteDTO> listaClientes;
	private ArrayList<String> nombreProductos;
	private Productos producto;
	private Pedidos pedido;
	private Clientes cliente;
	
	public Controlador(VentanaPrincipal ventana, Pedidos pedido, Clientes cliente) 
	{
		this.ventana=ventana;
		this.pedido=pedido;
		this.cliente=cliente;
		this.listaPedidos=null;
		this.listaProductos=null;
		this.listaClientes=null;
	}
	
	public void inicializar()
	{
		//this.llenarListas();
		//this.llenarTextField();
		this.ventana.show();
		//ventana.actualizarPedidoPend();
	}
	//@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == this.ventanaCliente.getBtnSeleccionar())
			this.ventana.actualizarPedidoPend();
	}
	
	//@Override
	//public void btnPedidosPendientes.addMouseListener(new MouseAdapter() 
	//{
	//@Override
	/*public void mouseClicked(MouseEvent e) 
	{
		if(e.getSource()==this.ventana.getBtnPedidosPendientes())
			this.ventanaPedPendiente=new pedidosPendientes(ventana,this);
			//pedidosPendientes pedidoPendiente=new pedidosPendientes(_this);
			ventanaPedPendiente.setVisible(true);
	}
	//});*/
}
