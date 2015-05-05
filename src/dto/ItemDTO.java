package dto;

public class ItemDTO
{
	ProductoDTO producto;
	Integer cantidad;
	
	public ItemDTO(ProductoDTO producto, Integer cantidad)
	{
		this.producto=producto;
		this.cantidad=cantidad;
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
