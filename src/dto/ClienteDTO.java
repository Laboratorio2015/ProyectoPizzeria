package dto;

import java.util.Iterator;
import java.util.List;

public class ClienteDTO 
{
	private Integer idcliente;
	private Integer dni;
	private String nombre;
	private String apellido;
	private String calle;
	private String numeracion;
	private String entrecalle1;
	private String entrecalle2;
	private String codPostal;
	private String email;
	private String telefono;
	private String comentario;
	private Boolean fueeliminado;
	
	public ClienteDTO(Integer idcliente,Integer documento, String nombr, String apellid, String direccio, String numeracion, String telefono,String entrecalle1, String entrecalle2, String codPostal, String email,String comentario, Boolean fueeliminado)
	{
		this.idcliente=idcliente;
		this.dni=documento;
		this.nombre=nombr;
		this.apellido=apellid;
		this.calle=direccio;
		this.telefono=telefono;
		this.numeracion=numeracion;
		this.entrecalle1=entrecalle1;
		this.entrecalle2=entrecalle2;
		this.comentario=comentario;
		this.codPostal=codPostal;
		this.email=email;
		this.fueeliminado=fueeliminado;
		
		this.fueeliminado=fueeliminado;
	}
	
	public ClienteDTO()
	{
		
	}
	
	public String getPiso() {
		return entrecalle1;
	}

	public void setPiso(String piso) {
		this.entrecalle1 = piso;
	}

	public String getDepto() {
		return entrecalle2;
	}

	public void setDepto(String depto) {
		this.entrecalle2 = depto;
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
		return calle;
	}
	public void setDireccion(String direccion) 
	{
		this.calle = direccion;
	}
	public String getTelefono() 
	{
		return telefono;
	}
	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}
		
	public Integer getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(Integer idcliente) {
		this.idcliente = idcliente;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNumeracion() {
		return numeracion;
	}

	public void setNumeracion(String numeracion) {
		this.numeracion = numeracion;
	}

	public String getEntrecalle1() {
		return entrecalle1;
	}

	public void setEntrecalle1(String entrecalle1) {
		this.entrecalle1 = entrecalle1;
	}

	public String getEntrecalle2() {
		return entrecalle2;
	}

	public void setEntrecalle2(String entrecalle2) {
		this.entrecalle2 = entrecalle2;
	}

	public String getCodPostal() {
		return codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
		return (this.nombre+"  "+ this.apellido+"  "+  this.calle+"  "+ this.numeracion);
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
