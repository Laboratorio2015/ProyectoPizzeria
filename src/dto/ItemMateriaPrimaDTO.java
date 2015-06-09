package dto;

public class ItemMateriaPrimaDTO {

	private Integer idItemMatPrima;
	private MateriaPrimaDTO itemMatPrima;
	private Integer cantidad;
	private Boolean fueEliminado;

	public ItemMateriaPrimaDTO(Integer iditemMatPrima,MateriaPrimaDTO itemMatPrima, Integer cantidad, Boolean fueeliminado){
		this.itemMatPrima = itemMatPrima;
		this.cantidad = cantidad;
		this.idItemMatPrima=iditemMatPrima;
		this.fueEliminado=fueeliminado;
	}
	public MateriaPrimaDTO getItemMatPrima() {
		return itemMatPrima;
	}
	public void setItemMatPrima(MateriaPrimaDTO itemMatPrima) {
		this.itemMatPrima = itemMatPrima;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getIdItemMatPrima() {
		return idItemMatPrima;
	}
	public void setIdItemMatPrima(Integer idItemMatPrima) {
		this.idItemMatPrima = idItemMatPrima;
	}
	public Boolean getFueEliminado() {
		return fueEliminado;
	}
	public void setFueEliminado(Boolean fueEliminado) {
		this.fueEliminado = fueEliminado;
	}
	
}
