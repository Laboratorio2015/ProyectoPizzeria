package modelo;

import java.util.Iterator;
import java.util.List;

import dao.OrdenPedidoMatPrimaDAO;
import dto.OrdenPedidoMatPrimaDTO;

public class OrdenesMateriaPrimas 
{
private OrdenPedidoMatPrimaDAO ordenMatPrima;
	
	public OrdenesMateriaPrimas() 
	{
		ordenMatPrima=new OrdenPedidoMatPrimaDAO();
	}
	
	public Boolean agregarOrdenPedidoMatPrima(OrdenPedidoMatPrimaDTO agregarOrden)
	{
		return this.ordenMatPrima.insert(agregarOrden);
		
	}
	
	public void quitarOrdenPedidoMatPrima(OrdenPedidoMatPrimaDTO quitarOrden)
	{
		this.ordenMatPrima.delete(quitarOrden);
	}
	
	public List<OrdenPedidoMatPrimaDTO> obtenerOrdenPedidoMatPrima()
	{
		return this.ordenMatPrima.readAll();
	}

	
	public OrdenPedidoMatPrimaDTO buscarOrdenPedidoMatPrima(int idordenmatprima) 
	{
		List<OrdenPedidoMatPrimaDTO> categorias=this.obtenerOrdenPedidoMatPrima();
		Iterator<OrdenPedidoMatPrimaDTO> Iterador = categorias.iterator();
		while(Iterador.hasNext())
		{
			OrdenPedidoMatPrimaDTO elemento = Iterador.next();
			if(elemento.getIdCompra().equals(idordenmatprima))
				return elemento;
		}
		return null;
	}
}
