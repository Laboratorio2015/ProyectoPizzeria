package dto;

public class ItemMateriaPrimaDTO {

	private Integer idItemMatPrima;
	private MateriaPrimaDTO itemMatPrima;
	private Integer cantidad;
	private Boolean fueeliminado;

	public ItemMateriaPrimaDTO(MateriaPrimaDTO itemMatPrima, Integer cantidad){
		this.itemMatPrima = itemMatPrima;
		this.cantidad = cantidad;
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
	
}
