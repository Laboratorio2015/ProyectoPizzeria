package main;

import modelo.Categorias;
import modelo.Clientes;
import modelo.ItemMateriasPrimas;
import modelo.Items;
import modelo.MatPrimas;
import modelo.Ofertas;
import modelo.OrdenesMateriaPrimas;
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
		Ofertas oferta=new Ofertas();
		Proveedores proveedor=new Proveedores();
		Repartidores repartidor=new Repartidores();
		Categorias categoria= new Categorias();
		ItemMateriasPrimas itemsMateriaPrima = new ItemMateriasPrimas();
		OrdenesMateriaPrimas ordenesMatPrima = new OrdenesMateriaPrimas();
		MatPrimas materiasPrimas = new MatPrimas();
		VentanaPrincipal ventanaPrincial=new VentanaPrincipal();
		//Controlador controlador=new Controlador(ventanaPrincial,pedido,cliente,producto,item, proveedor, repartidor,oferta,categoria);
		///agrego entidades de ORDENES DE MAT PRIMA Y MATERIAS PRIMAS, por lo cual uso este constructor
		Controlador controlador=new Controlador(ventanaPrincial,pedido,cliente,producto,item, proveedor, repartidor,oferta,categoria,ordenesMatPrima,materiasPrimas,itemsMateriaPrima);
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
