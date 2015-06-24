package dto;

import java.util.ArrayList;

public class ItemPromocionDTO
{
	private Integer iditempromo;
	private PromocionDTO promocion;
	private Integer cantidad;
	private String comentario;
	private Boolean fueeliminado;

	public ItemPromocionDTO(Integer iditem,PromocionDTO promocion, Integer cantidad, String comentario, Boolean fueeliminado)
	{
		this.iditempromo=iditem;
		this.promocion=promocion;
		this.cantidad=cantidad;
		this.comentario=comentario;
		this.fueeliminado=fueeliminado;
	}

	public ItemPromocionDTO() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIditemPromo() {
		return iditempromo;
	}

	public void setIditemPromo(Integer iditem) {
		this.iditempromo = iditem;
	}

	public PromocionDTO getPromocion() {
		return promocion;
	}

	public void setPromocion(PromocionDTO promocion) {
		this.promocion = promocion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
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

	public ArrayList<ItemDTO> getProductosOfertados() {
		return promocion.getProductosOfertados();
	}

}
