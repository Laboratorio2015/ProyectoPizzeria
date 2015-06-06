package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dto.ItemDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;

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
	
	public ArrayList<Integer> iditems(PedidoDTO pedido)
	{
		ArrayList<Integer> listaId=new ArrayList<Integer>();
		Iterator<ItemDTO> Iterador = pedido.getProductos().iterator();
		while(Iterador.hasNext())
		{
			ItemDTO elemento = Iterador.next();
			listaId.add(elemento.getIditem());
		}
		return listaId;
	}
	
	public ItemDTO buscarItem(Integer iditem)
	{
		List<ItemDTO> productos=this.obtenerItems();
		Iterator<ItemDTO> Iterador = productos.iterator();
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
}
