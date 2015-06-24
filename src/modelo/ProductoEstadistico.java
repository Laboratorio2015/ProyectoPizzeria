package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import dto.ProductoDTO;

public class ProductoEstadistico 
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
}
