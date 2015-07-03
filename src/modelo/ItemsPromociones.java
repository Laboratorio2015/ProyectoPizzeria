package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.ItemPromocionDAO;
import dto.ItemDTO;
import dto.ItemPromocionDTO;
import dto.PedidoDTO;

public class ItemsPromociones 
{
	private ItemPromocionDAO itempromo;
	
	public ItemsPromociones()
	{
		itempromo= new ItemPromocionDAO();
	}
	
	
	public Boolean agregarItemPromo(ItemPromocionDTO agregarItem)
	{
		return this.itempromo.insert(agregarItem);
		
	}
	
	public void quitarItem(ItemPromocionDTO quitarItem)
	{
		this.itempromo.delete(quitarItem);
	}
	
	public List<ItemPromocionDTO> obtenerItems()
	{
		return this.itempromo.readAll();
	}


	public String iditemsOferta(PedidoDTO pedido)
	{
		String listaId="";
		if(pedido.getOfertas()!=null)
		{
			Iterator<ItemPromocionDTO> Iterador = pedido.getOfertas().iterator();
			while(Iterador.hasNext())
			{
				ItemPromocionDTO elemento = Iterador.next();
				listaId=listaId +elemento.getIditemPromo()+" ";
			}
		}
		return listaId;
	}

	public ArrayList<ItemPromocionDTO> pasarDeStringAArrayItPromo(String listado) 
	{
		ArrayList<ItemPromocionDTO> result=new ArrayList<ItemPromocionDTO>();
		String a="";
		if (!listado.isEmpty()){
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
					ItemPromocionDTO item=this.buscarItemPromo(elemento);
					result.add(item);
					a="";
				}
				else if(listado.charAt(i)==' ' && listado.charAt(i+1)==' ')
					break;
			}
		}
		return result;

	}

/*	public ItemPromocionDTO buscarItemPromo(int iditem) 
	{
		List<ItemPromocionDTO> items=this.obtenerItems();
		Iterator<ItemPromocionDTO> Iterador = items.iterator();
		while(Iterador.hasNext())
		{
			ItemPromocionDTO elemento = Iterador.next();
			if(elemento.getIditemPromo().equals(iditem))
				return elemento;
		}
		return null;
	}
*/
	public ItemPromocionDTO buscarItemPromo(int iditem) 
	{
		return this.itempromo.buscarPromo(iditem);
	}
	
	public int ultimaOferta() 
	{
		Integer ultimo=0;
		List<ItemPromocionDTO> items=this.itempromo.readAll();
		Iterator<ItemPromocionDTO> Iterador = items.iterator();
		while(Iterador.hasNext())
		{
			ItemPromocionDTO elemento = Iterador.next();
			if(elemento.getIditemPromo()>ultimo)
				ultimo=elemento.getIditemPromo();
		}
		return ultimo;
	}
}
