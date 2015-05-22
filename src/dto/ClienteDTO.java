package dto;

import java.util.Iterator;
import java.util.List;

public class ClienteDTO 
{
	private Integer dni;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;
	private String piso;
	private String depto;
	
	public ClienteDTO(Integer documento, String nombr, String apellid, String direccio, String telefon, String piso, String depto)
	{
		dni=documento;
		nombre=nombr;
		apellido=apellid;
		direccion=direccio;
		telefono=telefon;
		this.piso=piso;
		this.depto=depto;
	}
	
	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) 
	{
		this.dni = dni;
	}
	public String getNombre() 
	{
		return nombre;
	}
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	public String getApellido() 
	{
		return apellido;
	}
	public void setApellido(String apellido) 
	{
		this.apellido = apellido;
	}
	public String getDireccion() 
	{
		return direccion;
	}
	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}
	public String getTelefono() 
	{
		return telefono;
	}
	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}
	
	@Override
	public String toString()
	{
		return (this.nombre+"  "+ this.apellido+"  "+  this.direccion+"  "+ this.telefono);
	}

	public static ClienteDTO buscarCliente(List<ClienteDTO> listaClientes, Integer dni)
	{
		Iterator<ClienteDTO> Iterador = listaClientes.iterator();
		while(Iterador.hasNext())
		{
			ClienteDTO elemento = Iterador.next();
			if(elemento.getDni().equals(dni))
				return elemento;
		}
		return null;
	}

}
