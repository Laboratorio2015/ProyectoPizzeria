package main;

import modelo.Clientes;
import modelo.Items;
import modelo.Pedidos;
import modelo.Productos;
import modelo.Proveedores;
import modelo.Repartidores;
import presentacion.controlador.Controlador;
import presentacion.vista.VentanaPrincipal;

public class Main 
{
	public static void main(String[] args) 
	{
		
		Clientes cliente=new Clientes();
		Pedidos pedido=new Pedidos();
		Productos producto= new Productos();
		Items item= new Items();
		Proveedores proveedor=new Proveedores();
		Repartidores repartidor=new Repartidores();
		VentanaPrincipal ventanaPrincial=new VentanaPrincipal();
		Controlador controlador=new Controlador(ventanaPrincial,pedido,cliente,producto,item, proveedor, repartidor);
		controlador.inicializar();
	}
		
	public static void esperarXsegundos(int segundos) 
	{
		try {
			Thread.sleep(segundos * 1000);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

}
