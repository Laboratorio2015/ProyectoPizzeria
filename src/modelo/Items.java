package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dto.ItemDTO;
import dto.ItemPromocionDTO;
import dto.PromocionDTO;
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
	
	public ItemDTO obtenerItemBuscado(Integer iditem)
	{
		return this.item.buscarItem(iditem);
	}
	public List<ItemDTO> obtenerItemsActuales()
	{
		return this.item.readActual();
	}
	public List<ItemDTO> obtenerItemsPedido(Integer idpedido)
	{
		return this.item.obtenerListaItems(idpedido);
	}
	
	//obtiene los items de un pedido y los agrupa en un string para subirlo a la DB
	public String iditemsPed(PedidoDTO pedido)
	{
		String listaId="";
		Iterator<ItemDTO> Iterador;
		if(pedido.getProductos()!=null)
		{
			Iterador= pedido.getProductos().iterator();
		
		while(Iterador.hasNext())
		{
			ItemDTO elemento = Iterador.next();
			listaId=listaId +elemento.getIditem()+" ";
		}
		}
		return listaId;
	}
	
	//obtiene los items de una oferta y los agrupa en un string para subirlo a la DB
	public String iditemsOfe(PromocionDTO oferta)
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
	
/*	public ItemDTO buscarItem(Integer iditem)
	{
		List<ItemDTO> items=this.obtenerItemsActuales();
		Iterator<ItemDTO> Iterador = items.iterator();
		while(Iterador.hasNext())
		{
			ItemDTO elemento = Iterador.next();
			if(elemento.getIditem().equals(iditem))
				return elemento;
		}
		return null;
	}
*/
	public ItemDTO buscarItem(Integer iditem)
	{
		ItemDTO aux=this.obtenerItemBuscado(iditem);
		return aux;
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
		if (listado!=null){
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
		}
		return result;
	}
}
