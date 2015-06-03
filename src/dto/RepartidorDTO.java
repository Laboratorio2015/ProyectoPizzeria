package dto;

public class RepartidorDTO 
{
	private Integer id;
	private Integer dni;
	private String nombre;
	private String apellido;
	private String fechaDeNacimiento;
	private String telefono;
	private String estado;
	
	public RepartidorDTO(Integer identificador, Integer dni, String nombr, String apellid, String fechaDeNac, String tel, String estado)
	{
		this.id = identificador;
		this.dni = dni;
		this.nombre = nombr;
		this.apellido = apellid;
		this.fechaDeNacimiento = fechaDeNac;
		this.telefono = tel;
		this.estado = estado;
	}
	
	public RepartidorDTO()
	{
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer num) 
	{
		this.id = num;
	}
	public Integer getDni() {
		return dni;
	}
	
	public void setDni(Integer num) 
	{
		this.dni = num;
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
	
	public String getFechaNacimiento() 
	{
		return fechaDeNacimiento;
	}
	public void setFechaNacimiento(String fecha) 
	{
		this.fechaDeNacimiento = fecha;
	}
	
	public String getTelefono() 
	{
		return telefono;
	}
	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}
	
	public String getEstado() 
	{
		return estado;
	}
	public void setEstado(String estado) 
	{
		this.estado = estado;
	}
	
	@Override
	public String toString()
	{
		return (this.id+ "  " + this.nombre + "  " +  this.apellido + "  " + this.estado);
	}
	
}
