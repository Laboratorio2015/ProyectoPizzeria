package modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import dto.ProductoDTO;

public class ProductoEstadistico implements Comparable<ProductoEstadistico>, Comparator<ProductoEstadistico>
{
	private ProductoDTO producto;
	private Integer cantidad;

	public ProductoEstadistico()
	{
		producto=new ProductoDTO();
		cantidad=0;
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) 
	{
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public static ProductoEstadistico buscarProductoEst(ArrayList<ProductoEstadistico> listaProductos, ProductoEstadistico producto)
	{
		Iterator<ProductoEstadistico> lista=listaProductos.iterator();
		while (lista.hasNext()) 
		{
			ProductoEstadistico elemento= lista.next();
			if(elemento.producto.equals(producto.producto))
				return elemento;	
		}
		return null;
	}
	@Override
    public int compareTo(ProductoEstadistico o)
	{
       if(this.cantidad<o.cantidad)
    	   return -1;
       if(this.cantidad>o.cantidad)
    	   return 1;
       return 0;
   }
	@Override
	public int compare(ProductoEstadistico p1, ProductoEstadistico p2) {
		Integer numero=new Integer(p1.getCantidad()).compareTo(new Integer(p2.getCantidad()));
		return numero;
	}

}
