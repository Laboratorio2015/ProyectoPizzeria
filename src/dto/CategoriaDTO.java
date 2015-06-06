package dto;

public class CategoriaDTO {
	
	Integer idCategoria;
	String denominacion;
	
	public CategoriaDTO(Integer idCategoria, String denominacion) {
		super();
		this.idCategoria = idCategoria;
		this.denominacion = denominacion;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	

}
