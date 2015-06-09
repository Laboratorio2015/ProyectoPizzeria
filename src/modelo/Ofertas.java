package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dao.OfertaDAO;
import dto.ItemDTO;
import dto.OfertaDTO;
import dto.PedidoDTO;


public class Ofertas {
	private OfertaDAO oferta;
	
	public Ofertas()
	{
		oferta=new OfertaDAO();
	}
	
	public Boolean agregarOferta(OfertaDTO agregarOferta)
	{
		return this.oferta.insert(agregarOferta);
		
	}
	
	public void quitarItem(OfertaDTO quitarOferta)
	{
		this.oferta.delete(quitarOferta);
	}
	
	public List<OfertaDTO> obtenerOfertas()
	{
		return this.oferta.readAll();
	}
	
	/*public List<OfertaDTO> obtenerOfertaPedido(Integer idpedido)
	{
		return this.oferta.obtenerListaItems(idpedido);
	}*/
	
	public String idofertas(PedidoDTO pedido)
	{
		String listaId="";
		Iterator<OfertaDTO> Iterador = pedido.getOfertas().iterator();
		while(Iterador.hasNext())
		{
			OfertaDTO elemento = Iterador.next();
			listaId=listaId +elemento.getIdOferta()+" ";
		}
		return listaId;
	}
	
	public OfertaDTO buscarOferta(Integer idoferta)
	{
		List<OfertaDTO> productos=this.obtenerOfertas();
		Iterator<OfertaDTO> Iterador = productos.iterator();
		while(Iterador.hasNext())
		{
			OfertaDTO elemento = Iterador.next();
			if(elemento.getIdOferta().equals(idoferta))
				return elemento;
		}
		return null;
	}
	
	public OfertaDTO buscarOfertaPorNombre(String nombre)
	{
	List<OfertaDTO> productos=this.obtenerOfertas();
	Iterator<OfertaDTO> Iterador = productos.iterator();
	while(Iterador.hasNext())
	{
		OfertaDTO elemento = Iterador.next();
		if(elemento.getNombre().compareTo(nombre)==0)
			return elemento;
	}
	return null;
	}

	public int ultimaOferta() 
	{
		Integer ultimo=0;
		List<OfertaDTO> ofertas=this.oferta.readAll();
		Iterator<OfertaDTO> Iterador = ofertas.iterator();
		while(Iterador.hasNext())
		{
			OfertaDTO elemento = Iterador.next();
			if(elemento.getIdOferta()>ultimo)
				ultimo=elemento.getIdOferta();
		}
		return ultimo;
	}

	public String iditemsOferta(PedidoDTO pedido)
	{
		String listaId="";
		if(pedido.getOfertas()!=null)
		{
			Iterator<OfertaDTO> Iterador = pedido.getOfertas().iterator();
			while(Iterador.hasNext())
			{
				OfertaDTO elemento = Iterador.next();
				listaId=listaId +elemento.getIdOferta()+" ";
			}
		}
		return listaId;
	}

	public ArrayList<OfertaDTO> pasarDeStringAArray(String listado) 
	{
		ArrayList<OfertaDTO> result=new ArrayList<OfertaDTO>();
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
				  OfertaDTO item=this.buscarOferta(elemento);
				  result.add(item);
				  a="";
			  }
			  else if(listado.charAt(i)==' ' && listado.charAt(i+1)==' ')
				  break;
		}
		return result;
	}
}
