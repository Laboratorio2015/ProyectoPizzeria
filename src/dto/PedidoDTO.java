package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import dto.ItemDTO;


public class PedidoDTO implements Serializable, JRDataSource
{
	private static final long serialVersionUID = -58521324047351224L;
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
	//valor para la implemntacion del dataSource
	private int indiceItemActual = -1;
	
	
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
	
	
	//String obtenerPedidosHoy = "SELECT idpedido,item,total,oferta,fecha,estado FROM pedidos WHERE fueeliminado=false AND fecha LIKE '"+ fecha +"%';";
	public PedidoDTO(Integer numpedido, ArrayList<ItemDTO> itemDTO,Integer total,ArrayList<ItemPromocionDTO> ItemPromocionDTO,
			String fecha, String estado) {
		this.numPedido = numpedido;
		this.total = total;
		this.items= itemDTO;
		this.ofertas=ItemPromocionDTO;
		this.fecha = fecha;
		this.estado=estado;	
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
	
	
	//metodos implementados del dataSource
	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		Object valor = null;  
		
		if("pedido_numero".equals(jrField.getName()))
		{
			valor = getNumPedido();
		}
		else if("fecha".equals(jrField.getName())) 
	    { 
	        valor = getFecha(); 
	    }
		else if("nombre".equals(jrField.getName())) 
	    { 
	        valor = cliente.getNombre(); 
	    }
		else if("apellido".equals(jrField.getName())) 
	    { 
	        valor = cliente.getApellido(); 
	    }
		else if("direccion".equals(jrField.getName())) 
	    { 
	        valor = cliente.getDireccion(); 
	    }
		else if("telefono".equals(jrField.getName())) 
	    { 
	        valor = cliente.getTelefono(); 
	    }
		else if("nombreProducto".equals(jrField.getName())) 
	    { 
	        valor = items.get(indiceItemActual).getProducto().getNombre(); 
	    } 
	    else if("precio".equals(jrField.getName())) 
	    { 
	        valor = items.get(indiceItemActual).getProducto().getPrecio(); 
	    } 
	    else if("tipo".equals(jrField.getName())) 
	    { 
	        valor = items.get(indiceItemActual).getProducto().getTipo(); 
	    } 
	    else if("cantidad".equals(jrField.getName())) 
	    { 
	        valor = items.get(indiceItemActual).getCantidad(); 
	    } 
	    else if("comentarioProducto".equals(jrField.getName())) 
	    { 
	        valor = items.get(indiceItemActual).getComentario(); 
	    }
	 
	    return valor;
	}
	@Override
	public boolean next() throws JRException {
		return ++indiceItemActual < items.size();
	}
}
