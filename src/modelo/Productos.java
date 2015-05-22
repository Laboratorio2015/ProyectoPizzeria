package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.itextpdf.text.pdf.CompareTool;

import persistencia.dao.ProductoDAO;
import dto.ProductoDTO;


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
}
