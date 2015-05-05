package dto;

public class ComandaDTO 
{
	private Integer idcomanda;
	
	public ComandaDTO(Integer comanda)
	{
		idcomanda=comanda;
	}

	public Integer getIdcomanda() 
	{
		return idcomanda;
	}

	public void setIdcomanda(Integer idcomanda) 
	{
		this.idcomanda = idcomanda;
	}
}
