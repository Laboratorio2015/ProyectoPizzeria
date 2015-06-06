package dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductoDTO 
{
	private Integer idproducto;
	private String nombre;
	private Integer precio;
	private String tipo;
	
	public ProductoDTO( Integer idproducto,String nombre, Integer precio, String tipo)
	{
		this.nombre=nombre;
		this.idproducto=idproducto;
		this.precio=precio;
		this.tipo=tipo;
	}
	public ProductoDTO()
	{
		
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	
		
	public Integer getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
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
	
	public static ProductoDTO buscarProducto(List<ProductoDTO> listaProductos, Integer idproduto)
	{
		Iterator<ProductoDTO> Iterador = listaProductos.iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			if(elemento.getIdproducto().equals(idproduto))
				return elemento;
		}
		return null;
	}

	// sobreescribir metodo equals
    public boolean equals (Object obj) {
        if (obj instanceof ProductoDTO) {
        ProductoDTO tmpProducto = (ProductoDTO) obj;
            if (this.nombre.equals(tmpProducto.nombre))
             return true;
            else
             return false;
        } else 
     return false;
    }
}
