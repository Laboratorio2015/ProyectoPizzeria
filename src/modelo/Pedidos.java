package modelo;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dto.ClienteDTO;
import dto.PedidoDTO;


public class Pedidos 
{
	public ArrayList<PedidoDTO> pedidos;
	
	
	public Pedidos()
	{
		this.pedidos=new ArrayList<>();
		//Clientes cl=new Clientes();
		//Productos p=new Productos();
		//PedidoDTO p1=new PedidoDTO(1, "sin aceitunas", 2015-06-06, 20:15, "solicitado", 230, 1, 1, cl.seleccionarCliente(1), Productos)
	}
	/*
	public void agregarPedido(PedidoDTO nuevoPedido, ArrayList<Productos> producto)
	{
		pedidos.insert(nuevoPedido);
		productos.agragar;
	}
	
	public void quitarPedido(PedidoDTO pedidoQuitar)
	{
		pedidos.delete(pedidoQuitar);
	}

	public List<PedidoDTO> obtenerPedido()
	{
		return this.pedidos.readAll();
	}*/
	public static PedidoDTO buscarPedido(Pedidos listaPedidos, PedidoDTO pedido)
	{
		Iterator<PedidoDTO> Iterador = listaPedidos.pedidos.iterator();
		while(Iterador.hasNext())
		{
			PedidoDTO elemento = Iterador.next();
			if(elemento.getIdpedido()==pedido.getIdpedido())
				return elemento;
		}
		return null;
	}
	
}
