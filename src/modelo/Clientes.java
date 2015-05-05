package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dto.ClienteDTO;
import dto.ProductoDTO;


public class Clientes 
{
	private ArrayList<ClienteDTO> cliente;

	public Clientes()
	{
		cliente=new ArrayList<>();
		
		ClienteDTO c1= new ClienteDTO(12222333, "juan", "casarez", "centenario 123", "4555544");
		ClienteDTO c2= new ClienteDTO(12222334, "pedro", "sanchez", "centenario 123", "4555544");
		ClienteDTO c3= new ClienteDTO(12222335, "pablo", "baguer", "centenario 123", "4555544");
		ClienteDTO c4= new ClienteDTO(12222336, "ricardo", "martinez", "centenario 123", "4555544");
		ClienteDTO c5= new ClienteDTO(12222337, "marcelo", "cal", "centenario 123", "4555544");
		ClienteDTO c6= new ClienteDTO(12222338, "juan cruz", "torres", "centenario 123", "4555544");
		ClienteDTO c7= new ClienteDTO(12222339, "tomas", "torales", "centenario 123", "4555544");
		cliente.add(c1);
		cliente.add(c7);
		cliente.add(c6);
		cliente.add(c5);
		cliente.add(c4);
		cliente.add(c3);
		cliente.add(c2);
		
	}
	
	public void agregarCliente(ClienteDTO clienteNuevo)
	{
		cliente.add(clienteNuevo);
	}
	
	public ClienteDTO seleccionarCliente(Integer numCliente)
	{
		return cliente.get(numCliente);
	}
	
	public void quitarCliente(ClienteDTO clienteQuitar)
	{
		cliente.remove(clienteQuitar);
	}
	
	public List<ClienteDTO> obtenerCliente()
	{
		return this.cliente;
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
	
	public ClienteDTO buscarCliente(Clientes lista,Integer dni)
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
}
