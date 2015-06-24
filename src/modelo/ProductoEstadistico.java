package modelo;

import dto.ProductoDTO;

public class ProductoEstadistico 
{
	private ProductoDTO producto;
	private Integer cantidad;

	public ProductoEstadistico()
	{
		producto=new ProductoDTO();
		cantidad=0;
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
}
