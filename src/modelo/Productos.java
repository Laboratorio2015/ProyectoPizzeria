package modelo;

import java.util.ArrayList;
import java.util.List;
import dto.ProductoDTO;


public class Productos 
{
	private ArrayList<ProductoDTO> producto;
	
	public Productos()
	{
		producto=new ArrayList<>();
	
	}
	
	public void agregarProducto(ProductoDTO productoNuevo)
	{
		producto.add(productoNuevo);
	}
	
	public void quitarProducto(ProductoDTO productoQuitar)
	{
		producto.remove(productoQuitar);
	}
	
	public List<ProductoDTO> obtenerProducto()
	{
		return this.producto;
	}
	
	public ArrayList<ProductoDTO> seleccionarProductos()
	{
		ArrayList<ProductoDTO> aux= new ArrayList<>();
		
		
		return aux;
	}
}
