package dto;

public class ItemMateriaPrimaDTO {

	private Integer idItemMatPrima;
	private MateriaPrimaDTO itemMatPrima;
	private Integer cantidad;
	private Boolean fueeliminado;

	public ItemMateriaPrimaDTO(Integer idItemMatPrima,MateriaPrimaDTO itemMatPrima, Integer cantidad, Boolean fueeliminado)
	{
		this.idItemMatPrima=idItemMatPrima;
		this.itemMatPrima = itemMatPrima;
		this.cantidad = cantidad;
		this.fueeliminado=fueeliminado;		
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
	public Boolean getFueeliminado() {
		return fueeliminado;
	}
	public void setFueeliminado(Boolean fueeliminado) {
		this.fueeliminado = fueeliminado;
	}
	
}
