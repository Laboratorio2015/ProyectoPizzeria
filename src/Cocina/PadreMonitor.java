package Cocina;

import java.awt.Frame;
import java.util.ArrayList;
import java.util.Iterator;

import modelo.Pedidos;
import dto.ItemDTO;
import dto.PedidoDTO;



public class PadreMonitor {
	
	private Pedidos listadoDePedidos;
	private ArrayList<ItemDTO> productosFaltantes;
	private ArrayList<PedidoDTO> arrayPedidos;
	private PadreMonitor padre;
	private MonitorThread monitor;
	private Frame frame;

	public PadreMonitor (Pedidos listadoPedidos)
	{
		this.listadoDePedidos = listadoPedidos;
		this.arrayPedidos = (ArrayList<PedidoDTO>) listadoPedidos.obtenerPedidos();
		//productosFaltantes = new ArrayList<ItemDTO>();
		this.padre = this;
		//calcularFaltantes();
		this.monitor = new MonitorThread(); 
		this.monitor.start();
		cargarListadoPedidos();
	}

	private void cargarListadoPedidos(){
		for (int i = 0; i < arrayPedidos.size();i++){
			monitor.agregarPedido(arrayPedidos.get(i));
		}		
	}

	public void nuevoPedido(PedidoDTO nuevoPedido){
		monitor.agregarPedido(nuevoPedido);
		monitor.actualizar();
	}
	
	public void quitarPedido(PedidoDTO borrarPedido)
	{		
		monitor.quitarPedido(borrarPedido);
		monitor.actualizar();
	}
	
	///GETTERS AND SETTER
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
