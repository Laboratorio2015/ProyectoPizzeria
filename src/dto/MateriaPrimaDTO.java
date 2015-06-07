package dto;

public class MateriaPrimaDTO 
{
	private Integer idMatPrima;
	private String nombre;
	private CategoriaDTO categoria;
	private Integer precio;
	private Boolean fueeliminado;

	
	public MateriaPrimaDTO(Integer identificador, String nombre, CategoriaDTO categoria,Integer precio ,Boolean fueliminado)
	{
		this.idMatPrima = identificador;
		this.nombre = nombre;
		this.categoria=categoria;
		this.precio=precio;
		this.fueeliminado=fueliminado;
	}
	//constructor vacio
	public MateriaPrimaDTO()
	{
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public Integer getIdMatPrima() {
		return idMatPrima;
	}
	public void setIdMatPrima(Integer idMatPrima) {
		this.idMatPrima = idMatPrima;
	}
	public Boolean getFueeliminado() {
		return fueeliminado;
	}
	public void setFueeliminado(Boolean fueeliminado) {
		this.fueeliminado = fueeliminado;
	}
	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}
		
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	public CategoriaDTO getCategoria() {
		return categoria;
	}
	@Override
	public String toString()
	{
		return (this.idMatPrima+ "  " + this.nombre);
	}
}
