package Monitor.Cocina;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.Iterator;

import modelo.Pedidos;
import dto.ItemDTO;
import dto.PedidoDTO;



public class PadreMonitor {
	
	Pedidos listadoDePedidos;
	ArrayList<ItemDTO> productosFaltantes;
	PadreMonitor padre;
	MonitorThread monitor;
	Frame frame;

	/**
	 * @wbp.parser.entryPoint
	 */
	public PadreMonitor (Pedidos listadoPedidos)
	{
		listadoDePedidos = listadoPedidos;
		productosFaltantes = new ArrayList<ItemDTO>();
		padre = this;
		calcularFaltantes();
		monitor = new MonitorThread(listadoPedidos,productosFaltantes, padre); 
		monitor.start();
	}

	@SuppressWarnings("unused")
	private void mostrarProductosFaltantes() 
	{
		Iterator<ItemDTO> iteradorItem = productosFaltantes.iterator();
		while(iteradorItem.hasNext())
		{
			ItemDTO elementoItem = iteradorItem.next();
			System.out.println(elementoItem.getProducto().getNombre() + " - Cantidad faltante: " + elementoItem.getCantidad() + "\n" );
		}
	}

	private void calcularFaltantes() {
		
		Iterator<PedidoDTO> iteradorPedidos = listadoDePedidos.pedido.pedidosPendientes().iterator();
		while(iteradorPedidos.hasNext()){
			PedidoDTO elemento = iteradorPedidos.next();
			System.out.print("Numero de pedido> " + elemento.getIdpedido() + "\n");
			
			//RECORRO AHORA EL LISTADO DE PRODUCTOS DENTRO DEL PRIMER PEDIDO
			Iterator<ItemDTO> iteradorItem = elemento.getProductos().iterator();
			while(iteradorItem.hasNext()){
				ItemDTO elementoItem = iteradorItem.next();
				if (getIntDeFaltante(elementoItem.getProducto().getNombre())!= -1)
				{
					int faltante = getIntDeFaltante(elementoItem.getProducto().getNombre());
					ItemDTO actualizado = new ItemDTO(elementoItem.getIditem(), elementoItem.getProducto(), elementoItem.getCantidad() + productosFaltantes.get(faltante).getCantidad(),elementoItem.getComentario());
					productosFaltantes.set(faltante, actualizado);
				}
				else{
					productosFaltantes.add(elementoItem);  // si no esta este producto entre los faltantes, lo agrego
				}
			}
			
		}
		
	}

	public void nuevoPedido(PedidoDTO nuevoPedido)
	{
		calcularFaltante(nuevoPedido);
		listadoDePedidos.agregarPedido(nuevoPedido);
		monitor.actualizarPedidos();
	}
	
	private void calcularFaltante(PedidoDTO nuevoPedido) {
		Iterator<ItemDTO> iteradorItem = nuevoPedido.getProductos().iterator();
		while(iteradorItem.hasNext()){
			ItemDTO elementoItem = iteradorItem.next();
			if (getIntDeFaltante(elementoItem.getProducto().getNombre())!= -1)
			{
				int faltante = getIntDeFaltante(elementoItem.getProducto().getNombre());
				ItemDTO actualizado = new ItemDTO(elementoItem.getIditem(),elementoItem.getProducto(), elementoItem.getCantidad() + productosFaltantes.get(faltante).getCantidad(),elementoItem.getComentario());
				productosFaltantes.set(faltante, actualizado);
			}
			else
			{
				productosFaltantes.add(elementoItem);  // si no esta este producto entre los faltantes, lo agrego
			}
		}
	}

	private int getIntDeFaltante(String nombre)
	{	
		for(int i=0; i < productosFaltantes.size(); i++){
			if (productosFaltantes.get(i).getProducto().getNombre().compareTo(nombre)==0){
				return i;
			}
		}
		return -1;
	}

	public void quitarPedido(PedidoDTO borrarPedido)
	{		
		
		Iterator<ItemDTO> iteradorItem = borrarPedido.getProductos().iterator();
		while(iteradorItem.hasNext()){
			ItemDTO elementoItem = iteradorItem.next();
			int indexAborrar = getIntDeFaltante(elementoItem.getProducto().getNombre());
			ItemDTO correcionFaltantes = new ItemDTO(elementoItem.getIditem(),elementoItem.getProducto(), productosFaltantes.get(indexAborrar).getCantidad()-elementoItem.getCantidad(),elementoItem.getComentario());
			productosFaltantes.set(indexAborrar, correcionFaltantes);
		}
		if (listadoDePedidos.obtenerPedidos().contains(borrarPedido)){
			listadoDePedidos.quitarPedido(borrarPedido);
		}
		monitor.actualizarPedidos();
	}
	///GETTERS AND SETTER
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public ArrayList<PedidoDTO> getListadoPedidos() {
		return (ArrayList<PedidoDTO>) listadoDePedidos.pedido.pedidosPendientes();
	}

	public void setListadoPedidos(Pedidos listadoPedidos) {
		this.listadoDePedidos = listadoPedidos;
	}

	public ArrayList<ItemDTO> getProductosFaltantes() {
		return productosFaltantes;
	}

	public void setProductosFaltantes(ArrayList<ItemDTO> productosFaltantes) {
		this.productosFaltantes = productosFaltantes;
	}
	
}
