package dto;

import java.util.ArrayList;
import java.util.Iterator;

public class ProductoDTO 
{
	private String nombre;
	private Integer precio;
	
	public ProductoDTO(String nombre, Integer precio)
	{
		this.nombre=nombre;
		this.precio=precio;
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public Integer getPrecio() 
	{
		return precio;
	}
	public void setPrecio(Integer precio)
	{
		this.precio = precio;
	}
	
	public static Integer buscarPrecio (ArrayList<ProductoDTO> productos, String nombre)
	{
		Iterator<ProductoDTO> Iterador = productos.iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			if(elemento.getNombre().equals(nombre))
				return elemento.getPrecio();
		}
		return 1;
	}
	
	public static ArrayList<Object> mostrarNombres(ArrayList<ProductoDTO> productos)
	{
		ArrayList<Object> Nombres=new ArrayList<>();
		Iterator<ProductoDTO> Iterador = productos.iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			Nombres.add(elemento.getNombre());
		}
		return Nombres;
	}

	// sobreescribir metodo equals
    public boolean equals (Object obj) {
        if (obj instanceof ProductoDTO) {
        ProductoDTO tmpProducto = (ProductoDTO) obj;
            if (this.nombre.equals(tmpProducto.nombre) && this.precio.equals(tmpProducto.precio))
             return true;
            else
             return false;
        } else 
     return false;
    }
}
