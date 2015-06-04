package dto;

public class MateriaPrimaDTO 
{
	private Integer id;
	private String nombre;

	
	public MateriaPrimaDTO(Integer identificador, String nombr)
	{
		this.id = identificador;
		this.nombre = nombr;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer num) 
	{
		this.id = num;
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	@Override
	public String toString()
	{
		return (this.id+ "  " + this.nombre);
	}

	public String getCategoria() {
		return "categoriaEjemplo";
	}

}
