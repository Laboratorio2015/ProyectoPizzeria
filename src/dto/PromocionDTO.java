package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PromocionDTO implements Serializable{
	private static final long serialVersionUID = -4408335135891759010L;
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
	
	
	@Override
	public int hashCode() {
		return this.idOferta;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromocionDTO promo = (PromocionDTO) obj;
		if (idOferta == promo.getIdOferta())
			return true;
		else{
			return false;
		}
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
	public static PromocionDTO buscarProducto(List<PromocionDTO> obtenerOfertas, int idpromo)
	{
		Iterator<PromocionDTO> Iterador = obtenerOfertas.iterator();
		while(Iterador.hasNext())
		{
			PromocionDTO elemento = Iterador.next();
			if(elemento.getIdOferta().equals(idpromo))
				return elemento;
		}
		return null;
	}
	
}

