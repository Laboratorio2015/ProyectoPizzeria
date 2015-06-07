package dto;

import java.util.ArrayList;

public class OrdenPedidoMatPrimaDTO
{
	private Integer idCompra;
	private ProveedorDTO proveedor;
	private ArrayList<ItemMateriaPrimaDTO> listadoCompra;
	private String estado;
	private Boolean fueeliminado;
	
	
	public OrdenPedidoMatPrimaDTO(Integer idCompra, ProveedorDTO proveedor,
			ArrayList<ItemMateriaPrimaDTO> listadoCompra, String estado, Boolean fueeliminado) {
		super();
		this.idCompra = idCompra;
		this.proveedor = proveedor;
		this.listadoCompra = listadoCompra;
		this.estado=estado;
		this.fueeliminado=fueeliminado;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Boolean getFueeliminado() {
		return fueeliminado;
	}

	public void setFueeliminado(Boolean fueeliminado) {
		this.fueeliminado = fueeliminado;
	}
	

	
}
