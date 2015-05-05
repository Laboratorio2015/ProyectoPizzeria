package main;

import java.util.ArrayList;

import dto.ProductoDTO;
import modelo.Clientes;
import modelo.Pedidos;
import modelo.Productos;
import presentacion.controlador.Controlador;
import presentacion.vista.VentanaPrincipal;

public class Main 
{
	public static ArrayList<ProductoDTO> nuevo;
	public static Clientes clientes;
	public static Pedidos listaPedidos=new Pedidos();

	public static void main(String[] args) 
	{
		VentanaPrincipal ventanaPrincial=new VentanaPrincipal();
		Pedidos pedido=new Pedidos();
		
		//creo los prokductos para ser leidos por el sistema
		ProductoDTO p1=new ProductoDTO("carne", 12);
		ProductoDTO p2=new ProductoDTO("pollo", 12);
		ProductoDTO p3=new ProductoDTO("verdura", 15);
		ProductoDTO p4=new ProductoDTO("humita", 14);
		ProductoDTO p5=new ProductoDTO("muzzarella", 66);
		ProductoDTO p6=new ProductoDTO("jamon y morrones", 98);
		ProductoDTO p7=new ProductoDTO("mixta", 100);
		ProductoDTO p8=new ProductoDTO("fugazzeta",75);
		ProductoDTO p9=new ProductoDTO("faena", 120);
		ProductoDTO p10=new ProductoDTO("coca-cola 1.5 lts", 25);
		ProductoDTO p11=new ProductoDTO("pepsi 1.5 lts", 23);
		ProductoDTO p12=new ProductoDTO("sprite 1.5 lts", 25);
		ProductoDTO p13=new ProductoDTO("seven-up 1.5 lts", 23);
		ProductoDTO p14=new ProductoDTO("mirinda 1.5 lts", 23);
		nuevo=new ArrayList<>();
		nuevo.add(p1);
		nuevo.add(p2);
		nuevo.add(p3);
		nuevo.add(p4);
		nuevo.add(p5);
		nuevo.add(p6);
		nuevo.add(p7);
		nuevo.add(p8);
		nuevo.add(p9);
		nuevo.add(p10);
		nuevo.add(p11);
		nuevo.add(p12);
		nuevo.add(p13);
		nuevo.add(p14);
		
		clientes=new Clientes();
		listaPedidos=new Pedidos();
		Controlador controlador=new Controlador(ventanaPrincial, pedido,clientes);
		controlador.inicializar();
		
	}

}
