package dto;

import java.util.ArrayList;

public class PromocionDTO {

	private Integer idOferta;
	private String nombre;
	private ArrayList<ItemDTO> productosOfertados;
	private Integer precio;
	private Boolean fueeliminado;

	public PromocionDTO (Integer nroOferta,String nombre, Integer precio,  ArrayList<ItemDTO> productosOfertados,Boolean fueeliminado)
	{
		this.idOferta = nroOferta;
		this.nombre=nombre;
		this.productosOfertados = productosOfertados;
		this.precio = precio;
		this.fueeliminado=fueeliminado;
	}
	public PromocionDTO()
	{
		
	}
	
	public Integer getIdOferta() {
		return idOferta;
	}

	public void setIdOferta(Integer nroOferta) {
		this.idOferta = nroOferta;
	}

	public ArrayList<ItemDTO> getProductosOfertados() {
		return productosOfertados;
	}

	public void setProductosOfertados(ArrayList<ItemDTO> productosOfertados) {
		this.productosOfertados = productosOfertados;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Boolean getFueeliminado() {
		return fueeliminado;
	}
	public void setFueeliminado(Boolean fueeliminado) {
		this.fueeliminado = fueeliminado;
	}
	
}

