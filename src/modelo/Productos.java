package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dao.ProductoDAO;
import dto.ProductoDTO;
import dto.RepartidorDTO;


public class Productos 
{
	private ProductoDAO producto;
	
	public Productos()
	{
		producto=new ProductoDAO();
	
	}
	
	public void agregarProducto(ProductoDTO productoNuevo)
	{
		this.producto.insert(productoNuevo);
	}
	
	public void quitarProducto(ProductoDTO productoQuitar)
	{
		producto.delete(productoQuitar);
	}
	
	public List<ProductoDTO> obtenerProducto()
	{
		return this.producto.readAll();
	}
	
	public List<ProductoDTO> obtenerTodoProducto()
	{
		return this.producto.readAlli();
	}
	//actualiza un producto/////////////////////////////
	public void actualizarProducto(ProductoDTO producto)
	{
		this.producto.actualizarProducto(producto);
	}
	//////////////////////////////////////////////////////////
	public ProductoDTO buscarProductoPorNombre(String nombre)
	{
		List<ProductoDTO> productos=this.obtenerProducto();
		Iterator<ProductoDTO> Iterador = productos.iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			if(elemento.getNombre().compareTo(nombre)==0)
				return elemento;
		}
		return null;
	}
	
	public ArrayList<Object> buscaNombresProductos(String tipo)
	{
		ArrayList<Object> nombres= new ArrayList<>();
		List<ProductoDTO> productos=this.obtenerProducto();
		Iterator<ProductoDTO> Iterador = productos.iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			if(elemento.getTipo().equals(tipo))
				nombres.add(elemento.getNombre());
		}
		return nombres;
	}
	public ProductoDTO buscarProductoPorId(int idProducto)
	{
		List<ProductoDTO> productos=this.obtenerProducto();
		Iterator<ProductoDTO> Iterador = productos.iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			if(elemento.getIdproducto().equals(idProducto))
				return elemento;
		}
		return null;
	}

	public int ultimoProducto()
	{
		Integer ultimo=0;
		List<ProductoDTO> productos=this.producto.readAlli();
		Iterator<ProductoDTO> Iterador = productos.iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			if(elemento.getIdproducto()>ultimo)
				ultimo=elemento.getIdproducto();
		}
		return ultimo;
	}


}
