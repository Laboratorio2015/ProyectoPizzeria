package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dao.ItemMateriaPrimaDAO;
import dto.ItemMateriaPrimaDTO;
import dto.OrdenPedidoMatPrimaDTO;

public class ItemMateriasPrimas
{
private ItemMateriaPrimaDAO itemMatPrima;
	
	public ItemMateriasPrimas() 
	{
		itemMatPrima=new ItemMateriaPrimaDAO();
	}
	
	public Boolean agregarItemMatPrima(ItemMateriaPrimaDTO agregarItem)
	{
		return this.itemMatPrima.insert(agregarItem);
		
	}
	
	public void quitarItemMatPrima(ItemMateriaPrimaDTO quitarItem)
	{
		this.itemMatPrima.delete(quitarItem);
	}
	
	public List<ItemMateriaPrimaDTO> obtenerItemMatPrima()
	{
		return this.itemMatPrima.readAll();
	}

	
	public ItemMateriaPrimaDTO buscarItemMatPrima(int iditemmatprima) 
	{
		List<ItemMateriaPrimaDTO> categorias=this.obtenerItemMatPrima();
		Iterator<ItemMateriaPrimaDTO> Iterador = categorias.iterator();
		while(Iterador.hasNext())
		{
			ItemMateriaPrimaDTO elemento = Iterador.next();
			if(elemento.getIdItemMatPrima().equals(iditemmatprima))
				return elemento;
		}
		return null;
	}

	
	public String iditemMatPrima(OrdenPedidoMatPrimaDTO item)
	{
		String listaId="";
		Iterator<ItemMateriaPrimaDTO> Iterador = item.getListadoCompra().iterator();
		while(Iterador.hasNext())
		{
			ItemMateriaPrimaDTO elemento = Iterador.next();
			listaId=listaId +elemento.getIdItemMatPrima()+" ";
		}
		return listaId;
	}
	
	public ArrayList<ItemMateriaPrimaDTO> pasarDeStringAArray(String listado)
	{
		ArrayList<ItemMateriaPrimaDTO> result=new ArrayList<ItemMateriaPrimaDTO>();
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
				  ItemMateriaPrimaDTO item=this.buscarItemMatPrima(elemento);
				  result.add(item);
				  a="";
			  }
			  else if(listado.charAt(i)==' ' && listado.charAt(i+1)==' ')
				  break;
		}
		return result;
	}

	

}
