package dto;

import java.util.ArrayList;
import java.util.Iterator;

public class ItemDTO
{
	ProductoDTO producto;
	Integer cantidad;
	
	public ItemDTO(ProductoDTO producto, Integer cantidad)
	{
		this.producto=producto;
		this.cantidad=cantidad;
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) 
	{
		this.cantidad = cantidad;
	}
	
	public ItemDTO buscarItem(ArrayList<ItemDTO> listaItem)
	{
		Iterator<ItemDTO> Iterador = listaItem.iterator();
		while(Iterador.hasNext())
		{
			ItemDTO elemento = Iterador.next();
			if(elemento.getProducto()==this.getProducto() && elemento.getCantidad()== this.getCantidad())
				return elemento;
		}
		return null;
	}
	
	public static void borrarItem(ArrayList<ItemDTO> listaItem, ItemDTO itemBorrar)
	{
		Iterator<ItemDTO> Iterador = listaItem.iterator();
		int cont=0;
		boolean salir=false;
		while(Iterador.hasNext()&&salir==false)
		{
			ItemDTO elemento = Iterador.next();
			if(!elemento.getProducto().equals(itemBorrar.getProducto()))
				cont++;
			else
				salir=true;	
		}
		listaItem.remove(cont);
		
	}
}
