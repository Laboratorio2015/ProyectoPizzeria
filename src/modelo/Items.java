package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dto.ItemDTO;
import dto.OfertaDTO;
import dto.PedidoDTO;
import dao.ItemDAO;

public class Items 
{
	private ItemDAO item;
	
	public Items()
	{
		item=new ItemDAO();
	}
	
	public Boolean agregarItem(ItemDTO agregarItem)
	{
		return this.item.insert(agregarItem);
		
	}
	
	public void quitarItem(ItemDTO quitarItem)
	{
		this.item.delete(quitarItem);
	}
	
	public List<ItemDTO> obtenerItems()
	{
		return this.item.readAll();
	}
	
	public List<ItemDTO> obtenerItemsPedido(Integer idpedido)
	{
		return this.item.obtenerListaItems(idpedido);
	}
	
	//obtiene los items de un pedido y los agrupa en un string para subirlo a la DB
	public String iditemsPed(PedidoDTO pedido)
	{
		String listaId="";
		Iterator<ItemDTO> Iterador = pedido.getProductos().iterator();
		while(Iterador.hasNext())
		{
			ItemDTO elemento = Iterador.next();
			listaId=listaId +elemento.getIditem()+" ";
		}
		return listaId;
	}
	
	//obtiene los items de una oferta y los agrupa en un string para subirlo a la DB
	public String iditemsOfe(OfertaDTO oferta)
	{
		String listaId="";
		Iterator<ItemDTO> Iterador = oferta.getProductosOfertados().iterator();
		while(Iterador.hasNext())
		{
			ItemDTO elemento = Iterador.next();
			listaId=listaId +elemento.getIditem()+" ";
		}
		return listaId;
	}
	
	public ItemDTO buscarItem(Integer iditem)
	{
		List<ItemDTO> items=this.obtenerItems();
		Iterator<ItemDTO> Iterador = items.iterator();
		while(Iterador.hasNext())
		{
			ItemDTO elemento = Iterador.next();
			if(elemento.getIditem().equals(iditem))
				return elemento;
		}
		return null;
	}

	public int ultimoItem() 
	{
		Integer ultimo=0;
		List<ItemDTO> items=this.item.readAll();
		Iterator<ItemDTO> Iterador = items.iterator();
		while(Iterador.hasNext())
		{
			ItemDTO elemento = Iterador.next();
			if(elemento.getIditem()>ultimo)
				ultimo=elemento.getIditem();
		}
		return ultimo;
	}

	public ArrayList<ItemDTO> pasarDeStringAArray(String listado) 
	{
		ArrayList<ItemDTO> result=new ArrayList<ItemDTO>();
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
				  ItemDTO item=this.buscarItem(elemento);
				  result.add(item);
				  a="";
			  }
			  else if(listado.charAt(i)==' ' && listado.charAt(i+1)==' ')
				  break;
		}
		return result;
	}
}
