package dto;

import java.util.ArrayList;

public class HojaItinerarioDTO 
{
	private Integer idHojaItinerario;
	//este numero depedido depende de la fecha
	private Integer numItinerario;
	private RepartidorDTO repartidor;
	private ArrayList<PedidoDTO> pedidos;
	private Boolean fueeliminado;
	private String fecha;
	
	public HojaItinerarioDTO (Integer idhojaitinerario,Integer numItinerario ,RepartidorDTO repartidor, ArrayList<PedidoDTO> pedidos, Boolean fueeliminado, String fecha)
	{
		this.idHojaItinerario=idhojaitinerario;
		this.numItinerario=numItinerario;
		this.repartidor=repartidor;
		this.pedidos=pedidos;
		this.fueeliminado=fueeliminado;
		this.fecha=fecha;
	}
	//constructor vacio
	public HojaItinerarioDTO ()
	{
	}
	public Integer getIdHojaItinerario() {
		return idHojaItinerario;
	}
	public void setIdHojaItinerario(Integer idHojaItinerario) {
		this.idHojaItinerario = idHojaItinerario;
	}
	public Integer getNumItinerario() {
		return numItinerario;
	}
	public void setNumItinerario(Integer numItinerario) {
		this.numItinerario = numItinerario;
	}
	public RepartidorDTO getRepartidor() {
		return repartidor;
	}
	public void setRepartidor(RepartidorDTO repartidor) {
		this.repartidor = repartidor;
	}
	public ArrayList<PedidoDTO> getPedidos() {
		return pedidos;
	}
	public void setPedidos(ArrayList<PedidoDTO> pedidos) {
		this.pedidos = pedidos;
	}
	public Boolean getFueeliminado() {
		return fueeliminado;
	}
	public void setFueeliminado(Boolean fueeliminado) {
		this.fueeliminado = fueeliminado;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
