package dto;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dto.ItemDTO;


public class PedidoDTO 
{
	public enum estadosPedido{solicitado, preparado, endelivery,entregado, cancelado};
	private Integer idpedido;
	private ArrayList<ItemDTO> items;
	private Date fecha;
	private Time hora;
	private Integer total;
	private String estado;
	private Integer ticket;
	private Integer comanda;
	private ClienteDTO cliente;
	private Boolean llevaDelivery;
	
	
	public PedidoDTO(Integer pedido,ArrayList<ItemDTO> items , Date fecha, Time hora, String estado, Integer total,
			Integer ticket, Integer comanda, ClienteDTO cliente, Boolean llevaDelivery) 
	{
		this.idpedido=pedido;
		this.items=items;
		this.fecha=fecha;
		this.hora=hora;
		this.estado=estado;
		this.total=total;
		this.ticket=ticket;
		this.comanda=comanda;
		this.cliente=cliente;
		this.llevaDelivery=llevaDelivery;
		
	}
	public PedidoDTO()
	{
		
	}

	
	public ArrayList<ItemDTO> getProductos()
	{
		return items;
	}


	public void setProductos(ArrayList<ItemDTO> productos) 
	{
		this.items = productos;
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
	
	public ClienteDTO getCliente() 
	{
		return this.cliente;
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
	
	
	/*
	public static PedidoDTO buscarPedido(Integer num, List<PedidoDTO> pedidos) 
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
	*/
	
	public Boolean getLlevaDelivery() {
		return llevaDelivery;
	}
	public void setLlevaDelivery(Boolean llevaDelivery) {
		this.llevaDelivery = llevaDelivery;
	}
	//esta funcion verifica si el pedido para no cargarlo dos veces
	public static boolean estaPedido(List<PedidoDTO> pedidos, Integer num)
	{
		Iterator<PedidoDTO> Iterador = pedidos.iterator();
		while(Iterador.hasNext())
		{
			PedidoDTO elemento = Iterador.next();
			if(elemento.getIdpedido()==num)
				return true;
		}
		return false;
	}

	@Override
	public String toString()
	{
		return (this.idpedido+"  "+ this.estado+"  "+ this.comanda+"  "+ 
	this.cliente+"     total: "+ this.total+"  "+ this.ticket);
	}
}
