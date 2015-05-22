package Monitor.Cocina;

import java.util.ArrayList;
import javax.swing.JFrame;

import main.Main;

import dto.ItemDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;

public class mainMonitor {

	private JFrame frame;
	
	public mainMonitor(){
		frame = new JFrame("Duelo");
		frame.setSize(911, 506);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);	
		frame.setVisible(true);
		
	}
	
	//// LA VENTANA Q SE ABRE ACA , QUE DICE DUELO...ES UN EJEMPLO PARA VER SI NO SE MOLESTA CON EL MONITOR. 
	//EL MONITOR NO TIENE DISE;O TODAVIA, PERO ESTUVE PROBANDO AGREGAR
	//// PEDIDOS CON EL METODO DE PEDIDOS NUEVOS, Y DE BORRAR UN PEDIDO...
	//EN CASO DE FINALIZADO, RECHAZADO Y TODO ESTADO DIFERENTE A PENDIENTE
	//// PROBALO A VER SI ENCONTRAR ERROR...
	
	public static void main(String[] args) {
		
		//Debo crear objetos> Pedido, compuesto de Items y esos items,compuestos de productos.		
		//Creo los PRODUCTOS
		ProductoDTO prod1 = new ProductoDTO("Pizza Muzzarella",55);
		ProductoDTO prod2 = new ProductoDTO("Pizza Napolitana",58);
		ProductoDTO prod3 = new ProductoDTO("Pizza Fugazzeta",35);		
		
		ProductoDTO prod = new ProductoDTO("Pizza choclo",25);
		ProductoDTO prod5 = new ProductoDTO("Pizza Napolitana con jamon",58);
		ProductoDTO prod6 = new ProductoDTO("Pizza Fugazza",85);		
		
		//Creo los ITEMS
		ItemDTO i1= new ItemDTO(prod1,1);
		ItemDTO i2= new ItemDTO(prod2,2);
		ItemDTO i3= new ItemDTO(prod3,1);
		

		ItemDTO icuatro= new ItemDTO(prod,3);
		ItemDTO i5= new ItemDTO(prod5,2);
		ItemDTO i6= new ItemDTO(prod6,1);
		
		//Creo el PEDIDO
		ArrayList<ItemDTO> cantProductos = new ArrayList<ItemDTO>();
		cantProductos.add(i1);
		cantProductos.add(i2);
		cantProductos.add(i3);
		
		ArrayList<ItemDTO> cantProductos2 = new ArrayList<ItemDTO>();
		cantProductos2.add(icuatro);
		cantProductos2.add(i5);
		cantProductos2.add(i6);
		
		/*PedidoDTO orden1 = new PedidoDTO(3, cantProductos);
		Pedido orden2 = new Pedido(5,cantProductos2);
		Pedido orden3 = new Pedido(7,cantProductos2);
		Pedido orden5 = new Pedido(8,cantProductos2);
		
		ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
		pedidos.add(orden1);
		pedidos.add(orden2);
		pedidos.add(orden3);*/
		
		//FIN DE CREO LOS PEDIDOS A MOSTRAR
		
		mainMonitor ventana = new mainMonitor();
		
		PadreMonitor padre = new PadreMonitor(Main.listaPedidos.pedidos); // se asume este el primer llamado, por lo cual 
		//son los primeros pedidos existentes, solo recibe pedidos pendientes.
		//	CajeraThread cajera1 = new CajeraThread(pedidos,padre.getProductosFaltantes(), padre);
		// Crea el hilo monitor por primera vez.
		//cajera1.start();

		
		esperarXsegundos(3);
		//padre.nuevoPedido(orden5);
		//esperarXsegundos(3);
		//padre.quitarPedido(orden5);
		
		
	}
	static private void esperarXsegundos(int segundos) 
	{
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

}
