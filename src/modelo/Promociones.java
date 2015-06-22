package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dao.PromocionDAO;
import dto.ItemDTO;
import dto.ProductoDTO;
import dto.PromocionDTO;
import dto.PedidoDTO;
import dto.RepartidorDTO;


public class Promociones {
	private PromocionDAO oferta;
	
	public Promociones()
	{
		oferta=new PromocionDAO();
	}
	
	public Boolean agregarOferta(PromocionDTO agregarOferta)
	{
		return this.oferta.insert(agregarOferta);
		
	}
	
	public void quitarPromocion(PromocionDTO quitarOferta)
	{
		this.oferta.delete(quitarOferta);
	}
	
	public List<PromocionDTO> obtenerOfertas()
	{
		return this.oferta.readAll();
	}
	
	//actualiza un repartidor/////////////////////////////
	public void actualizarPromocion(PromocionDTO promo)
	{
		this.oferta.actualizarPromocion(promo);
	}
	//////////////////////////////////////////////////////////
	
/*	public String idofertas(PedidoDTO pedido)
	{
		String listaId="";
		Iterator<PromocionDTO> Iterador = pedido.getOfertas().iterator();
		while(Iterador.hasNext())
		{
			PromocionDTO elemento = Iterador.next();
			listaId=listaId +elemento.getIdOferta()+" ";
		}
		return listaId;
	}
	*/
	public PromocionDTO buscarOferta(Integer idoferta)
	{
		List<PromocionDTO> productos=this.obtenerOfertas();
		Iterator<PromocionDTO> Iterador = productos.iterator();
		while(Iterador.hasNext())
		{
			PromocionDTO elemento = Iterador.next();
			if(elemento.getIdOferta().equals(idoferta))
				return elemento;
		}
		return null;
	}
	
	public PromocionDTO buscarOfertaPorNombre(String nombre)
	{
	List<PromocionDTO> productos=this.obtenerOfertas();
	Iterator<PromocionDTO> Iterador = productos.iterator();
	while(Iterador.hasNext())
	{
		PromocionDTO elemento = Iterador.next();
		if(elemento.getNombre().compareTo(nombre)==0)
			return elemento;
	}
	return null;
	}

	public int ultimaOferta() 
	{
		Integer ultimo=0;
		List<PromocionDTO> ofertas=this.oferta.readAll();
		Iterator<PromocionDTO> Iterador = ofertas.iterator();
		while(Iterador.hasNext())
		{
			PromocionDTO elemento = Iterador.next();
			if(elemento.getIdOferta()>ultimo)
				ultimo=elemento.getIdOferta();
		}
		return ultimo;
	}
/*
	public String iditemsOferta(PedidoDTO pedido)
	{
		String listaId="";
		if(pedido.getOfertas()!=null)
		{
			Iterator<PromocionDTO> Iterador = pedido.getOfertas().iterator();
			while(Iterador.hasNext())
			{
				PromocionDTO elemento = Iterador.next();
				listaId=listaId +elemento.getIdOferta()+" ";
			}
		}
		return listaId;
	}
*/
	public ArrayList<PromocionDTO> pasarDeStringAArray(String listado) 
	{
		ArrayList<PromocionDTO> result=new ArrayList<PromocionDTO>();
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
				  PromocionDTO item=this.buscarOferta(elemento);
				  result.add(item);
				  a="";
			  }
			  else if(listado.charAt(i)==' ' && listado.charAt(i+1)==' ')
				  break;
		}
		return result;
	}

	public Integer obtenerUltimoid()
	{
		Integer ultimo=0;
		List<PromocionDTO> ofertas=this.oferta.readAlli();
		Iterator<PromocionDTO> Iterador = ofertas.iterator();
		while(Iterador.hasNext())
		{
			PromocionDTO elemento = Iterador.next();
			if(elemento.getIdOferta()>ultimo)
				ultimo=elemento.getIdOferta();
		}
		return ultimo;
	}

	public ArrayList<Object> buscaNombresOfertas()
	{
		ArrayList<Object> nombres= new ArrayList<>();
		Iterator<PromocionDTO> Iterador = this.obtenerOfertas().iterator();
		while(Iterador.hasNext())
		{
			PromocionDTO elemento = Iterador.next();
			nombres.add(elemento.getNombre());
		}
		return nombres;
	}
}
