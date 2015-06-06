package dto;

import java.util.ArrayList;

public class OrdenPedidoMatPrimaDTO {
	

	private Integer idCompra;
	private ProveedorDTO proveedor;
	private ArrayList<ItemMateriaPrimaDTO> listadoCompra;
	
	
	public OrdenPedidoMatPrimaDTO(Integer idCompra, ProveedorDTO proveedor,
			ArrayList<ItemMateriaPrimaDTO> listadoCompra) {
		super();
		this.idCompra = idCompra;
		this.proveedor = proveedor;
		this.listadoCompra = listadoCompra;
	}
	
	public Integer getIdCompra() {
		return idCompra;
	}


	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}


	public ProveedorDTO getProveedor() {
		return proveedor;
	}


	public void setProveedor(ProveedorDTO proveedor) {
		this.proveedor = proveedor;
	}


	public ArrayList<ItemMateriaPrimaDTO> getListadoCompra() {
		return listadoCompra;
	}


	public void setListadoCompra(ArrayList<ItemMateriaPrimaDTO> listadoCompra) {
		this.listadoCompra = listadoCompra;
	}

	
}
