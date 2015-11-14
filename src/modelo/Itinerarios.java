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
	
	public List<HojaItinerarioDTO> obtenerItinerariosFecha(String fecha)
	{
		return this.itinerario.ItinerarioDeFecha(fecha);
	}
	
	public HojaItinerarioDTO buscarItinerario(Integer iditinerario,String fecha)
	{
		List<HojaItinerarioDTO> items=itinerario.obtenerItinerariosFechaCompleto(fecha);
		Iterator<HojaItinerarioDTO> Iterador = items.iterator();
		while(Iterador.hasNext())
		{
			HojaItinerarioDTO elemento = Iterador.next();
			if(elemento.getNumItinerario().equals(iditinerario))
				return elemento;
		}
		return null;
	}

	public HojaItinerarioDTO buscarItinerario1(Integer iditinerario)
	{
		
		HojaItinerarioDTO aux= this.itinerario.buscarItinerario(iditinerario);
		return aux;
	}


	public int buscarItinerarioPorPedido(Integer idpedido, String fecha) 
	{
		List<HojaItinerarioDTO> items=this.obtenerItinerariosFecha(fecha);
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
						return elemento.getNumItinerario();
				}	
			}
		}
		return 0;
	}

	//obtener el numero del ultimo itinerario de una fecha
	public Integer obtenerUltimoItinerario(String fecha) 
	{
		Integer ultimo=0;
		ultimo = itinerario.UltimoItinerarioDeLaFecha(fecha);
		return ultimo;
	}
	//obtiene el numero del ultimo itinerario del historial
	public Integer obtenerUltimoItinerario() 
	{
		Integer ultimo=0;
		ultimo = itinerario.UltimoItinerario();
		return ultimo;
	}
}
