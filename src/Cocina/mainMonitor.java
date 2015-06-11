package Cocina;

import javax.swing.JFrame;

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
		mainMonitor ventana = new mainMonitor();
		//PadreMonitor padre = new PadreMonitor(); // se asume este el primer llamado, por lo cual 
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
