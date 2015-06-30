package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dto.ItemDTO;


public class PedidoDTO implements Serializable
{
	private static final long serialVersionUID = -796849469951998455L;
	//public enum estadosPedido{solicitado, preparado, endelivery,entregado, cancelado};
	private Integer idpedido;
	//este numero depedido depende de la fecha
	private Integer numPedido;
	private ArrayList<ItemDTO> items;
	private ArrayList<ItemPromocionDTO> ofertas;
	private String fecha;
	private String hora;
	private Integer total;
	private String estado;
	private Integer ticket;
	private Integer comanda;
	private ClienteDTO cliente;
	private Boolean llevaDelivery;
	private Boolean fueeliminado;
	
	
	public PedidoDTO(Integer pedido,Integer numPedido,ArrayList<ItemDTO> items ,String fecha, String hora, String estado,
			Integer total, Integer ticket, Integer comanda, ClienteDTO cliente,
			Boolean llevaDelivery,ArrayList<ItemPromocionDTO> ofertas,	Boolean fueeliminado) 
	{
		this.idpedido=pedido;
		this.items=items;
		this.numPedido=numPedido;
		this.ofertas=ofertas;
		this.fecha=fecha;
		this.hora=hora;
		this.estado=estado;
		this.total=total;
		this.ticket=ticket;
		this.comanda=comanda;
		this.cliente=cliente;
		this.llevaDelivery=llevaDelivery;
		this.fueeliminado=fueeliminado;
		
	}
	public PedidoDTO()
	{
		
	}

	
	public PedidoDTO(Integer idOrden, ArrayList<ItemDTO> items,Integer totalCosto,ArrayList<ItemPromocionDTO> promos, String fecha) {
		this.numPedido = idOrden;
		this.total = totalCosto;
		this.items= items;
		this.ofertas=promos;
		this.fecha = fecha;
		
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
	
	public String getFecha() 
	{
		return fecha;
	}
	public void setFecha(String fecha) 
	{
		this.fecha = fecha;
	}
	public String getHora() 
	{
		return hora;
	}
	public void setHora(String hora) 
	{
		this.hora = hora;
	}

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
	
	public Boolean getLlevaDelivery() {
		return llevaDelivery;
	}
	public void setLlevaDelivery(Boolean llevaDelivery) {
		this.llevaDelivery = llevaDelivery;
	}
		
	public ArrayList<ItemDTO> getItems() {
		return items;
	}
	public void setItems(ArrayList<ItemDTO> items) {
		this.items = items;
	}
	public ArrayList<ItemPromocionDTO> getOfertas() {
		return ofertas;
	}
	public void setOfertas(ArrayList<ItemPromocionDTO> ofertas) {
		this.ofertas = ofertas;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getTicket() {
		return ticket;
	}
	public void setTicket(Integer ticket) {
		this.ticket = ticket;
	}
	public Integer getComanda() {
		return comanda;
	}
	public void setComanda(Integer comanda) {
		this.comanda = comanda;
	}
	public Boolean getFueeliminado() {
		return fueeliminado;
	}
	public void setFueeliminado(Boolean fueeliminado) {
		this.fueeliminado = fueeliminado;
	}
	
	public Integer getNumPedido() {
		return numPedido;
	}
	public void setNumPedido(Integer numPedido) {
		this.numPedido = numPedido;
	}
	
	//esta funcion verifica al pedido para no cargarlo dos veces
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
