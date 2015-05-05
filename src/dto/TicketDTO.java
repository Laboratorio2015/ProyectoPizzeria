package dto;

public class TicketDTO 
{
	private Integer idticket;
	
	public TicketDTO(Integer ticket)
	{
		idticket=ticket;
	}

	public Integer getIdticket() 
	{
		return idticket;
	}

	public void setIdticket(Integer idticket) 
	{
		this.idticket = idticket;
	}
}
