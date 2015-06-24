package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductoDTO implements Serializable
{
	private static final long serialVersionUID = 1606181834602238613L;
	private Integer idproducto;
	private String nombre;
	private Integer precio;
	private String tipo;
	private Boolean fueeliminado;
	
	public ProductoDTO( Integer idproducto,String nombre, Integer precio, String tipo, Boolean fueeliminado)
	{
		this.nombre=nombre;
		this.idproducto=idproducto;
		this.precio=precio;
		this.tipo=tipo;
		this.fueeliminado=fueeliminado;
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
	
	public Boolean getFueeliminado() {
		return fueeliminado;
	}
	public void setFueeliminado(Boolean fueeliminado) {
		this.fueeliminado = fueeliminado;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		ProductoDTO other = (ProductoDTO) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	// sobreescribir metodo equals
//    @Override
//	public boolean equals (Object obj) {
//        if (obj instanceof ProductoDTO) {
//        ProductoDTO tmpProducto = (ProductoDTO) obj;
//            if (this.nombre.equals(tmpProducto.nombre))
//             return true;
//            else
//             return false;
//        } else 
//     return false;
//    }
    
}
