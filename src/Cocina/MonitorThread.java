package Monitor.Cocina;

import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import dto.ItemDTO;
import dto.PedidoDTO;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import modelo.Pedidos;




public class MonitorThread extends Thread {

	private JFrame frame;
	private JTextPane visor1;
	private JTextPane visor2;
	private Pedidos listadoDePedidos;
	private ArrayList<ItemDTO> productosFaltantes = new ArrayList<ItemDTO>();
	private PadreMonitor padre;

	public MonitorThread(Pedidos listadoPedidos, ArrayList<ItemDTO> productosFaltantes, PadreMonitor padre) {
		this.listadoDePedidos = listadoPedidos;
		this.productosFaltantes = productosFaltantes;
		this.padre = padre;
		
		/// GENERO FRAME Y LABEL CONTENIDO
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(204, 204, 0));
		frame.setBounds(250, 150, 911, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		visor1 = new JTextPane();
		visor1.setBounds(10, 11, 352, 445);
		frame.getContentPane().add(visor1);
		
		visor2 = new JTextPane();
		visor2.setBounds(540, 11, 336, 445);
		frame.getContentPane().add(visor2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MonitorThread.class.getResource("/prototipos/pizzeria.png")));
		lblNewLabel.setBounds(382, 11, 128, 445);
		frame.getContentPane().add(lblNewLabel);
		//FIN/// GENERO FRAME Y LABEL CONTENIDO			
		}

	@Override
	public void run() {
		frame.setVisible(true);
		
		escribirVisor((ArrayList<PedidoDTO>)listadoDePedidos.pedido.pedidosPendientes(), productosFaltantes);
		
		esperarXsegundos(5);
//		if (padre.nuevosPedidos()){
//			escribirVisor(pedidos,faltantes);
//		}
	}

	private void esperarXsegundos(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
	private void escribirVisor(ArrayList<PedidoDTO>pedidos, ArrayList<ItemDTO> faltantes){
		/////////// ANOTACION DE LOS PEDIDOS Y DETALLE
		
		visor1.setText("Listado de Pedidos \n\n");
		Iterator<PedidoDTO> iteradorPedidos = pedidos.iterator(); ///
		while(iteradorPedidos.hasNext()){					 //// RECORRO LOS PEDIDOS
			PedidoDTO elementoPedido = iteradorPedidos.next(); //// 
			visor1.setText(visor1.getText() +"\n"+ "Numero orden de pedido: " + elementoPedido.getIdpedido()+"\n");
			Iterator<ItemDTO> iteradorItem = elementoPedido.getProductos().iterator();   ///
			while(iteradorItem.hasNext())
			{										 //// RECORRO LOS items
				ItemDTO elementoItem = iteradorItem.next(); /////
				System.out.println(elementoItem);
				visor1.setText(visor1.getText() + elementoItem.getProducto().getNombre() + " Cantidad: " + elementoItem.getCantidad() + "\n");
			}
		}
		
		/////////// ANOTACION DE LOS PEDIDOS Y DETALLE	
		visor2.setText("Productos Faltantes \n\n\n");
		Iterator<ItemDTO> iteradorItem = productosFaltantes.iterator(); ///
		while(iteradorItem.hasNext())
		{					 //// RECORRO LOS PEDIDOS
			ItemDTO elementoItem = iteradorItem.next(); //// 
			//visor1.setText(visor2.getText() + "Numero orden de pedido> " + elementoPedido.getNroPedido() + " \n\n");
			visor2.setText(visor2.getText() + elementoItem.getProducto().getNombre() + "-  Cantidad: " + elementoItem.getCantidad() + "\n");
			
		}
		
	}

	
	
	public void actualizarPedidos()
	{
		visor1.setText("");
		visor2.setText("");
		escribirVisor(padre.getListadoPedidos(), padre.getProductosFaltantes());
	}
}
