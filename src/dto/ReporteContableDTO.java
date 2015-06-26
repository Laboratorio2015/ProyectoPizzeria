package dto;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.Promociones;

public class ReporteContableDTO {
	
	private ArrayList<PedidoDTO> listadoPedidos;
	private ArrayList<OrdenPedidoMatPrimaDTO> listadoCompras;
	private HashMap<ProductoDTO, Integer> cantEmpVendidas;
	private HashMap<ProductoDTO, Integer> cantPizzaVendidas;
	private Integer ganancia;
	private Integer totalPedidos;
	private Integer totalCompras;
	
	public ReporteContableDTO(){
		listadoPedidos = new ArrayList<PedidoDTO>();
		listadoCompras = new ArrayList<OrdenPedidoMatPrimaDTO>();
		cantEmpVendidas= new HashMap<ProductoDTO, Integer>();
		cantPizzaVendidas = new HashMap<ProductoDTO, Integer>();
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
			//El atributo <ofertas> de la clase PedidoDTO contiene un array de ItemPromocionDTO. Este item hace referencia a un id de PromocionDTO;
			//tambien indica la cantidad de esa promoción (0,1,*). 
			//Idea: Se itera el array de <ofertas>, por cada una guardo la cantidad de veces que lo contiene y luego recorro los items que componen
			//la oferta obtenida. Por cada item, me interesa saber:
			//	el producto y cantidad del mismo: para poder sumar al hashmap. Esta cantidad la multiplico por el atributo <cantidad> del item 
			//q obtuve 
			
			java.util.Iterator<ItemPromocionDTO> iteradorOfertas = elementoPedido.getOfertas().iterator();
			while (iteradorOfertas.hasNext()){
				ItemPromocionDTO elementoPromo = iteradorOfertas.next();
//				PromocionDTO promo = promociones.buscarOferta(elementoPromo.getIditemPromo());
				//Tomo cada item de la promocion seleccionada que es representado por un Id. Con ese Id tengo q obtener la promo del modelo correspon
				//y recien ahi recorrer todos los items de la promo obtenida de la consulta.
				//PromocionDTO promo =  promociones.buscarOferta(elementoPromo.getIditemPromo());
				//System.out.println("Promo nro " + promo.getIdOferta());
				//Ahora recoorro los items dentro de la promo
				java.util.Iterator<ItemDTO> iterItemOferta = elementoPromo.getPromocion().getProductosOfertados().iterator();
				while (iterItemOferta.hasNext()){
					ItemDTO elementoItem = iterItemOferta.next();
					sumarProducto(elementoItem,elementoPromo.getCantidad());
				}
			}

			
		}
		
		//CALCULOS SOBRE ORDENES DE MATERIA PRIMA
		java.util.Iterator<OrdenPedidoMatPrimaDTO> iteradorOrdenesMP = this.listadoCompras.iterator();
		while (iteradorOrdenesMP.hasNext()){
			OrdenPedidoMatPrimaDTO elementoOrden = iteradorOrdenesMP.next();
			this.totalCompras = this.totalCompras + elementoOrden.getCosto();
		}	
		ganancia = totalPedidos - totalCompras;
	}
	
	private void sumarProducto(ItemDTO item, Integer cantPromoRepetida) {
		if (item.getProducto().getTipo().trim().compareTo("otros") != 0){
			HashMap<ProductoDTO, Integer> tablaCorresp = this.cantEmpVendidas;
			if (item.getProducto().getTipo().trim().compareTo("pizza") == 0){
				tablaCorresp = this.cantPizzaVendidas;
			}
			if (tablaCorresp.containsKey(item.getProducto())){
				Integer total = cantEmpVendidas.get(item.getProducto()) + (item.getCantidad()*cantPromoRepetida);
				tablaCorresp.replace(item.getProducto(), total);
			}
			else{
				tablaCorresp.put(item.getProducto(),item.getCantidad()*cantPromoRepetida);
			}	
		}
	}

	public void sumarProducto(ItemDTO item){
		if (item.getProducto().getTipo().trim().compareTo("otros") != 0){
			HashMap<ProductoDTO, Integer> tablaCorresp = this.cantEmpVendidas;
			if (item.getProducto().getTipo().trim().compareTo("pizza") == 0){
				tablaCorresp = this.cantPizzaVendidas;
			}
			if (tablaCorresp.containsKey(item.getProducto())){
				Integer total = cantEmpVendidas.get(item.getProducto()) + item.getCantidad();
				tablaCorresp.replace(item.getProducto(), total);
			}
			else{
				tablaCorresp.put(item.getProducto(),item.getCantidad());
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
	
	public void mostrarDetalle(){
		System.out.println("total pedidos: " + this.totalPedidos);
		System.out.println("total compras: " + this.totalCompras);
		System.out.println("ganancia: " + this.ganancia);
		System.out.println(" ");
		System.out.println("Empanadas vendidas");
		for (ProductoDTO key : cantEmpVendidas.keySet()) {
			System.out.println("Producto: " + key.getNombre() + " -Cantidad vendida: " + cantEmpVendidas.get(key));
		}
		System.out.println("Pizzas vendidas");
		for (ProductoDTO key : cantPizzaVendidas.keySet()) {
			System.out.println("Producto: " + key.getNombre() + " -Cantidad vendida: " + cantPizzaVendidas.get(key));
		}
		System.out.println("Compras realizadas");
		java.util.Iterator<OrdenPedidoMatPrimaDTO> iteradorCompras = this.listadoCompras.iterator();
		while (iteradorCompras.hasNext()){
			OrdenPedidoMatPrimaDTO elemento = iteradorCompras.next();
			System.out.println(elemento.getFecha().trim() + " | " + elemento.getProveedor().getNombre().trim() + "  | Costo: $" + elemento.getCosto());
		}
		
	}
}
