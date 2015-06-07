package dto;

public class RepartidorDTO 
{
	private Integer idRepartidor;
	private Integer dni;
	private String nombre;
	private String apellido;
	private String fechaDeNacimiento;
	private String vehiculo;
	private String patente;
	private String telefono;
	private String estado;
	private String comentario;
	private Boolean fueeliminado;
	
	public RepartidorDTO(Integer identificador, Integer dni, String nombr, String apellid, String fechaDeNac,String vehiculo,String patente, String tel, String estado,String comentario,Boolean fueeliminado)
	{
		this.idRepartidor = identificador;
		this.dni = dni;
		this.nombre = nombr;
		this.apellido = apellid;
		this.fechaDeNacimiento = fechaDeNac;
		this.telefono = tel;
		this.estado = estado;
		this.vehiculo=vehiculo;
		this.patente=patente;
		this.comentario=comentario;
		this.fueeliminado=fueeliminado;
	}
	
	public RepartidorDTO()
	{
		
	}
	
	public Integer getId() {
		return idRepartidor;
	}
	public void setId(Integer num) 
	{
		this.idRepartidor = num;
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
	
	public Integer getIdRepartidor() {
		return idRepartidor;
	}

	public void setIdRepartidor(Integer idRepartidor) {
		this.idRepartidor = idRepartidor;
	}

	public String getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(String fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Boolean getFueeliminado() {
		return fueeliminado;
	}

	public void setFueeliminado(Boolean fueeliminado) {
		this.fueeliminado = fueeliminado;
	}

	@Override
	public String toString()
	{
		return (this.idRepartidor+ "  " + this.nombre + "  " +  this.apellido + "  " + this.estado);
	}
	
}
