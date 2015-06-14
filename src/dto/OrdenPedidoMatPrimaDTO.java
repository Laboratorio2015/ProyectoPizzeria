package dto;

import java.util.ArrayList;

public class OrdenPedidoMatPrimaDTO
{
	private Integer idCompra;
	private ProveedorDTO proveedor;
	private ArrayList<ItemMateriaPrimaDTO> listadoCompra;
	private String estado;
	private String fecha;
	private Integer costo;
	private Boolean enviado;
	private Boolean fueeliminado;
	
	
	public OrdenPedidoMatPrimaDTO(Integer idCompra, ProveedorDTO proveedor,
			ArrayList<ItemMateriaPrimaDTO> listadoCompra, String estado,
			String fecha, Integer costo,Boolean enviado,Boolean fueeliminado) {
		super();
		this.idCompra = idCompra;
		this.proveedor = proveedor;
		this.listadoCompra = listadoCompra;
		this.estado=estado;
		this.fecha=fecha;
		this.costo=costo;
		this.enviado=enviado;
		this.fueeliminado=fueeliminado;
	}
	
	public OrdenPedidoMatPrimaDTO(Integer idCompra, ProveedorDTO proveedor,ArrayList<ItemMateriaPrimaDTO> listadoCompra,boolean enviado) {
		super();
		this.idCompra = idCompra;
		this.proveedor = proveedor;
		this.listadoCompra = listadoCompra;
		this.estado="guardado";
		this.fecha = "12122015";
		this.costo=0;
		this.enviado = false;
		this.fueeliminado=false;
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Integer getCosto() {
		return costo;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	}

	public Boolean getEnviado() {
		return enviado;
	}

	public void setEnviado(Boolean enviado) {
		this.enviado = enviado;
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
