package dto;

import java.util.ArrayList;

public class HojaItinerarioDTO 
{
	private Integer idHojaItinerario;
	private RepartidorDTO repartidor;
	private ArrayList<PedidoDTO> pedidos;
	private Boolean fueeliminado;
	private String fecha;
	
	public HojaItinerarioDTO (Integer idhojaitinerario, RepartidorDTO repartidor, ArrayList<PedidoDTO> pedidos, Boolean fueeliminado, String fecha)
	{
		this.idHojaItinerario=idhojaitinerario;
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
