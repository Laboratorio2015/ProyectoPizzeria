package dto;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;

import modelo.Pedidos;

import dto.ItemDTO;


public class PedidoDTO 
{
	public enum estadosPedido{solicitado, preparado, endelivery,entregado, cancelado};
	private Integer idpedido;
	//private String observaciones;
	private Date fecha;
	private Time hora;
	private Integer total;
	private String estado;
	private Integer ticket;
	private Integer comanda;
	private ClienteDTO cliente;
	public ArrayList<ItemDTO> productos;
	
	
	
	public PedidoDTO(Integer pedido, String observacion, Date fecha, Time hora, String estado, Integer total,
			Integer ticket, Integer comanda, ClienteDTO cliente, ArrayList<ItemDTO> productos) 
	{
		this.idpedido=pedido;
		this.fecha=fecha;
		this.hora=hora;
		this.estado=estado;
		this.total=total;
		this.ticket=ticket;
		this.comanda=comanda;
		this.cliente=cliente;
		this.productos=productos;
	}
	public PedidoDTO()
	{
		
	}

	
	public ArrayList<ItemDTO> getProductos() {
		return productos;
	}


	public void setProductos(ArrayList<ItemDTO> productos) {
		this.productos = productos;
	}


	public Integer getIdpedido() 
	{
		return idpedido;
	}
	public void setIdpedido(Integer idpedido) 
	{
		this.idpedido = idpedido;
	}
	
	public Date getFecha() 
	{
		return fecha;
	}
	public void setFecha(Date fecha) 
	{
		this.fecha = fecha;
	}
	public Time getHora() 
	{
		return hora;
	}
	public void setHora(Time hora) 
	{
		this.hora = hora;
	}
	/*
	public estadosPedido getEstado() 
	{
		return estado;
	}
	public void setEstado(estadosPedido estado) 
	{
		this.estado = estado;
	}
	*/
	public Integer getTotal() 
	{
		return total;
	}
	public void setTotal(Integer total) 
	{
		this.total = total;
	}
	/*
	public TicketDTO getTicket() 
	{
		return ticket;
	}
	public void setTicket(TicketDTO ticket) 
	{
		this.ticket = ticket;
	}
	
	public ComandaDTO getComanda() 
	{
		return comanda;
	}
	public void setComanda(ComandaDTO comanda) 
	{
		this.comanda = comanda;
	}*/
	public ClienteDTO getCliente() 
	{
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) 
	{
		this.cliente = cliente;
	}
	
	public String get_estado() {
		return estado;
	}

	public void set_estado(String _estado) {
		this.estado = _estado;
	}

	public Integer get_ticket() {
		return ticket;
	}

	public void set_ticket(Integer _ticket) {
		this.ticket = _ticket;
	}

	public Integer get_comanda() {
		return comanda;
	}

	public void set_comanda(Integer _comanda) {
		this.comanda = _comanda;
	}
	
	public static PedidoDTO buscarPedido(Integer num, ArrayList<PedidoDTO> pedidos) 
	{
		Iterator<PedidoDTO> Iterador = pedidos.iterator();
		while(Iterador.hasNext())
		{
			PedidoDTO elemento = Iterador.next();
			if(elemento.getIdpedido()==num)
				return elemento;
		}
		return null;
	}

	@Override
	public String toString()
	{
		return (this.idpedido+"  "+ this.estado+"  "+ this.comanda+"  "+ 
	this.cliente+"     total: "+ this.total+"  "+ this.ticket);
	}
}
