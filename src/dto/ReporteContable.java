package dto;

import java.util.ArrayList;

import presentacion.reportes.solicitudDeMateriaPrima;


public class ReporteContable {
	
	ArrayList<PedidoDTO> listadoPedidos;// = new Arraylist<PedidoDTO>;
	ArrayList<OrdenPedidoMatPrimaDTO> listadoCompras;
	Integer ganancia;
	
	public ReporteContable(){
		listadoPedidos = new ArrayList<PedidoDTO>();
	}

	public ArrayList<PedidoDTO> getListadoPedidos() {
		return listadoPedidos;
	}

	public void setListadoPedidos(ArrayList<PedidoDTO> listadoPedidos) {
		this.listadoPedidos = listadoPedidos;
	}

	public ArrayList<OrdenPedidoMatPrimaDTO> getListadoCompras() {
		return listadoCompras;
	}

	public void setListadoCompras(ArrayList<OrdenPedidoMatPrimaDTO> listadoCompras) {
		this.listadoCompras = listadoCompras;
	}

	public Integer getGanancia() {
		return ganancia;
	}

	public void setGanancia(Integer ganancia) {
		this.ganancia = ganancia;
	}

	
}
