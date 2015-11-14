package modelo;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.PedidoDAO;
import dto.HojaItinerarioDTO;
import dto.ItemDTO;
import dto.ItemPromocionDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import dto.PromocionDTO;

public class Pedidos 
{
	public PedidoDAO pedido;
	
	
	public Pedidos()
	{
		this.pedido=new PedidoDAO();
	}
	
	public void agregarPedido(PedidoDTO pedidoAgregar)
	{
		this.pedido.insert(pedidoAgregar);
	}
	
	public void quitarPedido(PedidoDTO pedidoQuitar)
	{
		this.pedido.delete(pedidoQuitar);
	}
	public List<PedidoDTO> obtenerPedidos()
	{
		return this.pedido.readAll();
	}
	public List<PedidoDTO> obtenerPedidosDeFecha(String fecha)
	{
		return this.pedido.pedidosDadoUnaFecha(fecha);
	}
	public List<ItemDTO> obtenerTodosItems() 
	{
		return this.pedido.readItem();
	}
	
	public void actualizarPedido(PedidoDTO pedido, String estado)
	{
		this.pedido.actualizarEstadoPedido(pedido, estado);
	}
	
	public List<ItemPromocionDTO> obtenerTodosPromos() 
	{
		return this.pedido.readPromo();
	}
	
	public Integer ultimoPedido()
	{
		Integer ultimo=0;
		List<PedidoDTO> pedidos=this.pedido.readAll();
		Iterator<PedidoDTO> Iterador = pedidos.iterator();
		while(Iterador.hasNext())
		{
			PedidoDTO elemento = Iterador.next();
			if(elemento.getIdpedido()>ultimo)
				ultimo=elemento.getIdpedido();
		}
		return ultimo;
	}
	
	public Integer ultimoPedidoFecha(String fecha) 
	{
		Integer ultimo=0;
		List<PedidoDTO> pedidos=this.pedido.pedidosDadoFecha(fecha);
		Iterator<PedidoDTO> Iterador = pedidos.iterator();
		while(Iterador.hasNext())
		{
			PedidoDTO elemento = Iterador.next();
			if(elemento.getNumPedido()>ultimo)
				ultimo=elemento.getNumPedido();
		}
		return ultimo;
	}
	
	public PedidoDTO buscarPedidoId(Integer idpedido, String fecha)
	{
		List<PedidoDTO> pedidos=this.obtenerPedidosDeFecha(fecha);
		Iterator<PedidoDTO> Iterador = pedidos.iterator();
		while(Iterador.hasNext())
		{
			PedidoDTO elemento = Iterador.next();
			if(elemento.getIdpedido().equals(idpedido))
				return elemento;
		}
		return null;	
	}
	
	public PedidoDTO buscarPedidoNumeroFecha(Integer numPedido, String fecha)
	{
		List<PedidoDTO> pedidos=this.obtenerPedidosDeFecha(fecha);
		Iterator<PedidoDTO> Iterador = pedidos.iterator();
		while(Iterador.hasNext())
		{
			PedidoDTO elemento = Iterador.next();
			if(elemento.getNumPedido().equals(numPedido))
				return elemento;
		}
		return null;
	}

	public String iditemsPedido(HojaItinerarioDTO itinerario)
	{
		String listaId="";
		Iterator<PedidoDTO> Iterador = itinerario.getPedidos().iterator();
		while(Iterador.hasNext())
		{
			PedidoDTO elemento = Iterador.next();
			listaId=listaId +elemento.getIdpedido()+" ";
		}
		return listaId;
	}

	//crea el array de pedidos a partir del string 
	public ArrayList<PedidoDTO> pasarDeStringAArray(String listado, String fecha)
	{
		ArrayList<PedidoDTO> result=new ArrayList<PedidoDTO>();
		String a="";


		for (int i=0; i<listado.length(); i++)
		{

			if (listado.charAt(i) != ' '&& listado.charAt(i+1)!=' ')
			{
				a=a+listado.charAt(i)+"";

			}
			if(listado.charAt(i) != ' '&& listado.charAt(i+1)==' ')
			{
				a=a+listado.charAt(i);
				int elemento=Integer.parseInt(a);
				PedidoDTO item=this.buscarPedidoId(elemento,fecha);
				result.add(item);
				a="";
			}
			else if(listado.charAt(i)==' ' && listado.charAt(i+1)==' ')
				break;
		}
		return result;
	}

	public ArrayList<PedidoDTO> reporteDiario(String dia, String mes, String año) throws SQLException {
		return pedido.getPedidosVendidosPorFecha(dia,mes, año);
		
	}

	public List<PedidoDTO> obtenerPedidosPendientes(){
		return this.pedido.pedidosPendientes();
	}

	public  ArrayList<PedidoDTO> obtenerPedidosHoy(String fecha) {
		return this.pedido.pedidosHoy(fecha);
	}

	public ArrayList<PedidoDTO> obtenerPedidoDeFecha(String fecha) {
		return this.pedido.pedidoPendientesFecha(fecha);
	}	
}
