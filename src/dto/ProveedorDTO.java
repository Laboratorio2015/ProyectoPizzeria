package dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProveedorDTO 
{
	private Integer id;
	private String nombre;
	private String categoria;
	private String telefono;
	private String email;
	private String direccion;
	
	
	
	public ProveedorDTO(Integer identificador, String nombre,String categoria ,String telefono,String email ,String direccion)
	{
		this.id = identificador;
		this.nombre = nombre;
		this.categoria=categoria;
		this.telefono=telefono;
		this.email=email;
		this.direccion=direccion;
	}
	public ProveedorDTO()
	{
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer num) 
	{
		this.id = num;
	}
	public String getNombre() 
	{
		return nombre;
	}
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public String getTelefono() 
	{
		return telefono;
	}
	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}
	
	public String getDireccion() 
	{
		return direccion;
	}
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	//esta funcion verifica al proveedor para no cargarlo dos veces
		public static boolean estaProveedor(List<ProveedorDTO> proveedores, Integer num)
		{
			Iterator<ProveedorDTO> Iterador = proveedores.iterator();
			while(Iterador.hasNext())
			{
				ProveedorDTO elemento = Iterador.next();
				if(elemento.getId()==num)
					return true;
			}
			return false;
		}
		
	@Override
	public String toString()
	{
		return (this.id+ "  " + this.nombre + "  " +  this.direccion + "  " + this.telefono);
	}
}
