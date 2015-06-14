package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dao.ClienteDAO;
import dto.ClienteDTO;


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
	public List<ClienteDTO> obtenerTodoClientes()
	{
		return this.cliente.readAlli();
	}
	
	public void actualizarCliente(ClienteDTO clienteActualizar)
	{
		this.cliente.actualizarCliente(clienteActualizar);
	}
	
	public ClienteDTO buscarClientePorID(Integer idcliente)
	{
		List<ClienteDTO> clientes=this.obtenerClientes();
		Iterator<ClienteDTO> Iterador = clientes.iterator();
		while(Iterador.hasNext())
		{
			ClienteDTO elemento = Iterador.next();
			if(elemento.getIdcliente().equals(idcliente))
				return elemento;
		}
		return null;
	}
	public ClienteDTO buscarClientePorDNI(Integer dni)
	{
		List<ClienteDTO> clientes=this.obtenerClientes();
		Iterator<ClienteDTO> Iterador = clientes.iterator();
		while(Iterador.hasNext())
		{
			ClienteDTO elemento = Iterador.next();
			if(elemento.getDni().equals(dni))
				return elemento;
		}
		return null;
	}
	public ClienteDTO buscarClientePorDNI(int dni)
	{
		List<ClienteDTO> clientes=this.obtenerClientes();
		Iterator<ClienteDTO> Iterador = clientes.iterator();
		while(Iterador.hasNext())
		{
			ClienteDTO elemento = Iterador.next();
			if(elemento.getDni().equals(dni))
				return elemento;
		}
		return null;
	}
	public ClienteDTO buscarClientePorNombre(String nombre)
	{
		List<ClienteDTO> clientes=this.obtenerClientes();
		Iterator<ClienteDTO> Iterador = clientes.iterator();
		while(Iterador.hasNext())
		{
			ClienteDTO elemento = Iterador.next();
			if(elemento.getNombre().compareTo(nombre)==0)
				return elemento;
		}
		return null;	
	}

	public ClienteDTO buscarClientePorApellido(String apellido)
	{
		List<ClienteDTO> clientes=this.obtenerClientes();
		Iterator<ClienteDTO> Iterador = clientes.iterator();
		while(Iterador.hasNext())
		{
			ClienteDTO elemento = Iterador.next();
			if(elemento.getApellido().compareTo(apellido)==0)
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
	public ArrayList<Object> ApellidoClientes() 
	{
		ArrayList<Object> aux= new ArrayList<>();
		Iterator<ClienteDTO> Iterador = this.cliente.readAll().iterator();
		while(Iterador.hasNext())
		{
			ClienteDTO elemento = Iterador.next();
			aux.add(elemento.getApellido());
		}
		return aux;
	}
	
	public ArrayList<Object> NombreClientes()
	{
		ArrayList<Object> aux= new ArrayList<>();
		Iterator<ClienteDTO> Iterador = this.cliente.readAll().iterator();
		while(Iterador.hasNext())
		{
			ClienteDTO elemento = Iterador.next();
			aux.add(elemento.getNombre());
		}
		return aux;
	}
}
