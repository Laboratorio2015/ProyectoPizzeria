package dto;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProveedorDTO 
{
	private Integer idproveedor;
	private String nombre;
	private ArrayList<CategoriaDTO> categoria;
	private String nombrecontacto;
	private String telefono;
	private String direccion;
	private String email;
	private String comentario;
	private Boolean fueeliminado;
	
	
	
	public ProveedorDTO(Integer identificador, String nombre,String nombrecontact,ArrayList<CategoriaDTO> categoria ,String telefono,String direccion,String email ,String comentario, Boolean fueeliminado)
	{
		this.idproveedor = identificador;
		this.nombre = nombre;
		this.nombrecontacto=nombrecontact;
		this.categoria=categoria;
		this.telefono=telefono;
		this.email=email;
		this.direccion=direccion;
		this.comentario=comentario;
		this.fueeliminado=fueeliminado;
	}
	public ProveedorDTO()
	{
		
	}
	
	public Integer getId() {
		return idproveedor;
	}
	public void setId(Integer num) 
	{
		this.idproveedor = num;
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
	
	public ArrayList<CategoriaDTO> getCategoria() {
		return categoria;
	}

	public void setCategoria(ArrayList<CategoriaDTO> categoria) {
		this.categoria = categoria;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdproveedor() {
		return idproveedor;
	}
	public void setIdproveedor(Integer idproveedor) {
		this.idproveedor = idproveedor;
	}
	public String getNombrecontacto() {
		return nombrecontacto;
	}
	public void setNombrecontacto(String nombrecontacto) {
		this.nombrecontacto = nombrecontacto;
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
		return (this.idproveedor+ "  " + this.nombre + "  " +  this.direccion + "  " + this.telefono);
	}
	public Component getCategorias() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean isCategoria(String nomCategoria) {
		Iterator<CategoriaDTO> Iterador = categoria.iterator();
		while(Iterador.hasNext())
		{
			CategoriaDTO elementoCategoria = Iterador.next();
			if (elementoCategoria.getDenominacion().compareTo(nomCategoria)==0){
				return true;
			}
		}
		return false;
	}
}
