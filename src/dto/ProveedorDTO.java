package dto;

public class ProveedorDTO 
{
	private Integer id;
	private String nombre;
	private String telefono;
	private String direccion;
	
	public ProveedorDTO(Integer identificador, String nombr, String telefon, String direccio)
	{
		this.id = identificador;
		this.nombre = nombr;
		this.telefono=telefon;
		this.direccion=direccio;
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
	
	@Override
	public String toString()
	{
		return (this.id+ "  " + this.nombre + "  " +  this.direccion + "  " + this.telefono);
	}
}
