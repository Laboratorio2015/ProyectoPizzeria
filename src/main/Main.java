package main;

import java.io.IOException;
import java.net.UnknownHostException;

import modelo.Categorias;
import modelo.Clientes;
import modelo.ItemMateriasPrimas;
import modelo.Items;
import modelo.ItemsPromociones;
import modelo.Itinerarios;
import modelo.MatPrimas;
import modelo.ProductoEstadistico;
import modelo.Promociones;
import modelo.OrdenesMateriaPrimas;
import modelo.Pedidos;
import modelo.Productos;
import modelo.Proveedores;
import modelo.Repartidores;
import presentacion.controlador.Controlador;
import presentacion.vista.VentanaPrincipal;
import presentacion.vista.establecerConeccion;
import propiedades.propiedades;

public class Main 
{
	public static void main(String[] args) 
	{	
		Clientes cliente=new Clientes();
		Pedidos pedido=new Pedidos();
		Productos producto= new Productos();
		Items item= new Items();
		Promociones oferta=new Promociones();
		Proveedores proveedor=new Proveedores();
		Repartidores repartidor=new Repartidores();
		Categorias categoria= new Categorias();
		ItemMateriasPrimas itemsMateriaPrima = new ItemMateriasPrimas();
		OrdenesMateriaPrimas ordenesMatPrima = new OrdenesMateriaPrimas();
		MatPrimas materiasPrimas = new MatPrimas();
		Itinerarios itinerario=new Itinerarios();
		Promociones promocion=new Promociones();
		ItemsPromociones itempromocion= new ItemsPromociones();
		ProductoEstadistico prodEstadistico=new ProductoEstadistico();
		VentanaPrincipal ventanaPrincial=new VentanaPrincipal();
						
		///agrego entidades de ORDENES DE MAT PRIMA Y MATERIAS PRIMAS, por lo cual uso este constructor
		Controlador controlador=new Controlador(ventanaPrincial,pedido,cliente,producto,item, proveedor, 
				repartidor,oferta,categoria,ordenesMatPrima,materiasPrimas,itemsMateriaPrima,
				itinerario,promocion, itempromocion, prodEstadistico);
		try
		{
			controlador.inicializar();
			
		} catch (IOException e) {
			System.out.println("Problema de conexion con monitor (servidor)");
			e.printStackTrace();
		}
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
