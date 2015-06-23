package dto;

import java.util.ArrayList;
import java.util.HashMap;

import com.itextpdf.text.pdf.hyphenation.TernaryTree.Iterator;

import presentacion.reportes.solicitudDeMateriaPrima;


public class ReporteContableDTO {
	
	private ArrayList<PedidoDTO> listadoPedidos;
	private ArrayList<OrdenPedidoMatPrimaDTO> listadoCompras;
	private HashMap<ProductoDTO, Integer> contadorProductosVendidos;
	private Integer ganancia;
	private Integer totalPedidos;
	private Integer totalCompras;
	
	public ReporteContableDTO(){
		listadoPedidos = new ArrayList<PedidoDTO>();
		listadoCompras = new ArrayList<OrdenPedidoMatPrimaDTO>();
		contadorProductosVendidos= new HashMap<ProductoDTO, Integer>();
		totalPedidos = 0;
		totalCompras = 0;
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
	
	public void calcularGanancias(){
		java.util.Iterator<PedidoDTO> iterador = this.listadoPedidos.iterator();
		while (iterador.hasNext()){
			PedidoDTO elementoPedido = iterador.next();
			//CALCULA LA SUMA DE TOTALES DE TODOS LOS PEDIDOS.
			this.totalPedidos = this.totalPedidos + elementoPedido.getTotal();
			///SUMA LOS ITEMS DEL PEDIDO
			java.util.Iterator<ItemDTO> iteradorItems = elementoPedido.getItems().iterator();
			while (iteradorItems.hasNext()){
				ItemDTO elementoItem = iteradorItems.next();
				sumarProducto(elementoItem);
			}
			///SUMA LOS ITEMS DE CADA OFERTA
			//*falta//
		}
		
		//CALCULOS SOBRE ORDENES DE MATERIA PRIMA
		java.util.Iterator<OrdenPedidoMatPrimaDTO> iteradorOrdenesMP = this.listadoCompras.iterator();
		while (iteradorOrdenesMP.hasNext()){
			OrdenPedidoMatPrimaDTO elementoOrden = iteradorOrdenesMP.next();
			this.totalCompras = this.totalCompras + elementoOrden.getCosto();
		}
		
		ganancia = totalPedidos - totalCompras;
		System.out.println("total pedidos: " + this.totalPedidos);
		System.out.println("total compras: " + this.totalCompras);
		System.out.println("ganancia: " + this.ganancia);
	}
	
	public void sumarProducto(ItemDTO item){
		if (item.getProducto().getTipo().trim().compareTo("otros") != 0){
			System.out.println( "Item obtenido" + item.getProducto().getNombre() + " item devuelto de hash: " + contadorProductosVendidos.get(item));
			if (contadorProductosVendidos.get(item)!= null){
				Integer total = contadorProductosVendidos.get(item.getProducto()) + item.getCantidad();
				contadorProductosVendidos.replace(item.getProducto(), total);
			}
			else{
				contadorProductosVendidos.put(item.getProducto(),item.getCantidad());
			}	
		}
	}

	public Integer getTotalPedidos() {
		return totalPedidos;
	}

	public void setTotalPedidos(Integer totalPedidos) {
		this.totalPedidos = totalPedidos;
	}

	public Integer getTotalCompras() {
		return totalCompras;
	}

	public void setTotalCompras(Integer totalCompras) {
		this.totalCompras = totalCompras;
	}
	
	
}
