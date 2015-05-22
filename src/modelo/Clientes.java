package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import persistencia.dao.ClienteDAO;
import dto.ClienteDTO;
import dto.ProductoDTO;


public class Clientes 
{
	private ClienteDAO cliente;

	public Clientes()
	{
		cliente=new ClienteDAO();
	}
	
	public void agregarCliente(ClienteDTO clienteNuevo)
	{
		cliente.insert(clienteNuevo);
	}
	
	
	public void quitarCliente(ClienteDTO clienteQuitar)
	{
		cliente.delete(clienteQuitar);
	}
	
	public List<ClienteDTO> obtenerClientes()
	{
		return this.cliente.readAll();
	}
	
	public ClienteDTO buscarCliente(Integer dni)
	{
		List<ClienteDTO> productos=this.obtenerClientes();
		Iterator<ClienteDTO> Iterador = productos.iterator();
		while(Iterador.hasNext())
		{
			ClienteDTO elemento = Iterador.next();
			if(elemento.getDni().equals(dni))
				return elemento;
		}
		return null;
	}
	
	public ArrayList<Object> dniClientes()
	{
		ArrayList<Object> aux= new ArrayList<>();
		Iterator<ClienteDTO> Iterador = this.cliente.readAll().iterator();
		while(Iterador.hasNext())
		{
			ClienteDTO elemento = Iterador.next();
			String a=Integer.toString(elemento.getDni());
			aux.add(a);
		}
		return aux;
	}
	
/*	
	public ClienteDTO seleccionarCliente(Integer numCliente)
	{
		return cliente.get(numCliente);
	}	
	
	public ArrayList<Object> listaNombres(Clientes lista)
	{
		ArrayList<Object> aux= new ArrayList<>();
		Iterator<ClienteDTO> Iterador = lista.cliente.iterator();
		while(Iterador.hasNext())
		{
			ClienteDTO elemento = Iterador.next();
			String a=Integer.toString(elemento.getDni());
			aux.add(a);
		}
		return aux;
		
	}
	
	public static ClienteDTO buscarCliente(Clientes lista,Integer dni)
	{
		Iterator<ClienteDTO> Iterador = lista.cliente.iterator();
		while(Iterador.hasNext())
		{
			ClienteDTO elemento = Iterador.next();
			if(elemento.getDni().equals(dni))
			{
				return elemento;
			}
		}
		return null;
	}
	*/
}
