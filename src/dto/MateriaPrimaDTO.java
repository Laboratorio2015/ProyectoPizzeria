package dto;

public class MateriaPrimaDTO 
{
	private Integer idMatPrima;
	private String nombre;
	private CategoriaDTO categoria;
	private Boolean fueeliminado;

	
	public MateriaPrimaDTO(Integer identificador, String nombre, CategoriaDTO categoria, Boolean fueliminado)
	{
		this.idMatPrima = identificador;
		this.nombre = nombre;
		this.categoria=categoria;
		this.fueeliminado=fueliminado;
	}
	//constructor vacio
	public MateriaPrimaDTO()
	{
	}
	
	public Integer getId() {
		return idMatPrima;
	}
	public void setId(Integer num) 
	{
		this.idMatPrima = num;
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
		
	public CategoriaDTO getCategoria() {
		return categoria;
	}
	@Override
	public String toString()
	{
		return (this.idMatPrima+ "  " + this.nombre);
	}
}
