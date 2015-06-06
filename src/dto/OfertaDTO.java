package dto;

import java.util.ArrayList;

public class OfertaDTO {

	Integer nroOferta;
	ArrayList<ItemDTO> productosOfertados = new ArrayList<ItemDTO>();
	Double precio;

	public OfertaDTO (Integer nroOferta, ArrayList<ItemDTO> productosOfertados, Double precio){
		this.nroOferta = nroOferta;
		this.productosOfertados = productosOfertados;
		this.precio = precio;
	}

	public Integer getNroOferta() {
		return nroOferta;
	}

	public void setNroOferta(Integer nroOferta) {
		this.nroOferta = nroOferta;
	}

	public ArrayList<ItemDTO> getProductosOfertados() {
		return productosOfertados;
	}

	public void setProductosOfertados(ArrayList<ItemDTO> productosOfertados) {
		this.productosOfertados = productosOfertados;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}


}

