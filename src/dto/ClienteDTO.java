package dto;

public class ClienteDTO 
{
	private Integer dni;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;
	
	public ClienteDTO(Integer documento, String nombr, String apellid, String direccio, String telefon)
	{
		dni=documento;
		nombre=nombr;
		apellido=apellid;
		direccion=direccio;
		telefono=telefon;
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

	
}
