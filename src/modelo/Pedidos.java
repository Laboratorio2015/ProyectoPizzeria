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
	
	public List<ItemDTO> obtenerTodosItems() 
	{
		return this.pedido.readItem();
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
	
	public PedidoDTO buscarPedidoId(Integer idpedido)
	{
		List<PedidoDTO> pedidos=this.obtenerPedidos();
		Iterator<PedidoDTO> Iterador = pedidos.iterator();
		while(Iterador.hasNext())
		{
			PedidoDTO elemento = Iterador.next();
			if(elemento.getIdpedido().equals(idpedido))
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
	public ArrayList<PedidoDTO> pasarDeStringAArray(String listado)
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
				  PedidoDTO item=this.buscarPedidoId(elemento);
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

	

	
}
