package modelo;

import java.awt.Component;
import java.util.Iterator;
import java.util.List;

import dto.RepartidorDTO;
import dao.RepartidorDAO;

public class Repartidores {
	
	private RepartidorDAO repartidor;

	public Repartidores()
	{
		repartidor=new RepartidorDAO();
	}
	
	public void agregarRepartidor(RepartidorDTO clienteNuevo)
	{
		repartidor.insert(clienteNuevo);
	}
	
	
	public void quitarRepartidor(RepartidorDTO clienteQuitar)
	{
		repartidor.delete(clienteQuitar);
	}
	//actualiza un repartidor/////////////////////////////
	public void actualizarRepartidor(RepartidorDTO repartido)
	{
		this.repartidor.actualizarRepartidor(repartido);
	}
	//////////////////////////////////////////////////////////
	public List<RepartidorDTO> obtenerRepartidores()
	{
		return this.repartidor.readAll();
	}
	
	public List<RepartidorDTO> obtenerTodosRepartidores()
	{
		return this.repartidor.readAlli();
	}
	
	public RepartidorDTO buscarRepartidor(Integer dni)
	{
		List<RepartidorDTO> productos=this.obtenerRepartidores();
		Iterator<RepartidorDTO> Iterador = productos.iterator();
		while(Iterador.hasNext())
		{
			RepartidorDTO elemento = Iterador.next();
			if(elemento.getIdRepartidor().equals(dni))
				return elemento;
		}
		return null;		
	}
	
	public RepartidorDTO buscarRepartidorPorDni(Integer dni)
	{
		List<RepartidorDTO> productos=this.obtenerRepartidores();
		Iterator<RepartidorDTO> Iterador = productos.iterator();
		while(Iterador.hasNext())
		{
			RepartidorDTO elemento = Iterador.next();
			if(elemento.getDni().equals(dni))
				return elemento;
		}
		return null;		
	}
}
