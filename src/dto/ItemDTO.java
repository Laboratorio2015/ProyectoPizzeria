package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemDTO implements Serializable{
	private static final long serialVersionUID = -3440905735644933632L;
	private Integer iditem;
	private ProductoDTO producto;
	private Integer cantidad;
	private String comentario;
	private Boolean fueeliminado;

	public ItemDTO(Integer iditem,ProductoDTO producto, Integer cantidad, String comentario, Boolean fueeliminado)
	{
		this.iditem=iditem;
		this.producto=producto;
		this.cantidad=cantidad;
		this.comentario=comentario;
		this.fueeliminado=fueeliminado;
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
	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	public Integer getIditem() {
		return iditem;
	}

	public void setIditem(Integer iditem) {
		this.iditem = iditem;
	}

	public Boolean getFueeliminado() {
		return fueeliminado;
	}

	public void setFueeliminado(Boolean fueeliminado) {
		this.fueeliminado = fueeliminado;
	}
	
	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
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
	public static ItemDTO buscarItem(ArrayList<ItemDTO> listaItem,ItemDTO itemBuscar)
	{
		Iterator<ItemDTO> Iterador = listaItem.iterator();
		while(Iterador.hasNext())
		{
			ItemDTO elemento = Iterador.next();
			if(elemento.getProducto()==itemBuscar.getProducto() && elemento.getCantidad()== itemBuscar.getCantidad())
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((producto == null) ? 0 : producto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemDTO other = (ItemDTO) obj;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}
	
//	@Override
//	public int hashCode()
//	{
//	    return this.producto.getNombre().trim().hashCode();
//	}
//
//	@Override
//	public boolean equals(Object o)
//	{
//	    ItemDTO itemAcomparar = (ItemDTO) o;
//		return this.producto.getNombre().trim().hashCode() == itemAcomparar.getProducto().getNombre().trim().hashCode();
//	}
}
