package modelo;

import java.util.Iterator;
import java.util.List;

import dao.HojaItinerarioDAO;
import dto.HojaItinerarioDTO;
import dto.ItemDTO;
import dto.PedidoDTO;

public class Itinerarios 
{
	private HojaItinerarioDAO itinerario;
	
	public Itinerarios()
	{
		itinerario= new HojaItinerarioDAO();
	}
	
	public Boolean agregarItinerario(HojaItinerarioDTO agregarItinerario)
	{
		return this.itinerario.insert(agregarItinerario);
		
	}
	
	public void quitarItem(HojaItinerarioDTO quitarItinerario)
	{
		this.itinerario.delete(quitarItinerario);
	}
	
	public List<HojaItinerarioDTO> obtenerItinerarios()
	{
		return this.itinerario.readAll();
	}
	public HojaItinerarioDTO buscarItinerario(Integer iditinerario)
	{
		List<HojaItinerarioDTO> items=this.obtenerItinerarios();
		Iterator<HojaItinerarioDTO> Iterador = items.iterator();
		while(Iterador.hasNext())
		{
			HojaItinerarioDTO elemento = Iterador.next();
			if(elemento.getIdHojaItinerario().equals(iditinerario))
				return elemento;
		}
		return null;
	}

	public HojaItinerarioDTO buscarItinerario1(Integer iditinerario)
	{
		
		HojaItinerarioDTO aux= this.itinerario.buscarItinerario(iditinerario);
		return aux;
	}


	public int buscarItinerarioPorPedido(Integer idpedido) 
	{
		List<HojaItinerarioDTO> items=this.obtenerItinerarios();
		Iterator<HojaItinerarioDTO> Iterador = items.iterator();
		while(Iterador.hasNext())
		{
			HojaItinerarioDTO elemento = Iterador.next();
			{
				Iterator<PedidoDTO> Iterado = elemento.getPedidos().iterator();
				while(Iterado.hasNext())
				{
					PedidoDTO element = Iterado.next();
					if(element.getIdpedido().equals(idpedido))
						return elemento.getIdHojaItinerario();
				}	
			}
		}
		return 0;
	}

	public Integer obtenerUltimoItinerario() 
	{
		Integer ultimo=0;
		List<Integer> items=this.itinerario.obtenerIdItinerarios();
		Iterator<Integer> Iterador = items.iterator();
		while(Iterador.hasNext())
		{
			Integer elemento = Iterador.next();
			if(elemento>ultimo)
				ultimo=elemento;
		}
		return ultimo;
	}
}
