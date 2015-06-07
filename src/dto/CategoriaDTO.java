package dto;

public class CategoriaDTO {
	
	Integer idCategoria;
	String denominacion;
	Boolean fueEliminado;
	
	public CategoriaDTO(Integer idCategoria, String denominacion, Boolean fueeliminado) {
		super();
		this.idCategoria = idCategoria;
		this.denominacion = denominacion;
		this.fueEliminado=fueeliminado;
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

	public Boolean getFueEliminado() {
		return fueEliminado;
	}

	public void setFueEliminado(Boolean fueEliminado) {
		this.fueEliminado = fueEliminado;
	}
	
	

}
