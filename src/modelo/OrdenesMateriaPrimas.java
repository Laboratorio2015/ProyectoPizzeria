package modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import dao.OrdenPedidoMatPrimaDAO;
import dto.OrdenPedidoMatPrimaDTO;
import dto.ProveedorDTO;

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

	public Integer generarNvoId() {
		Iterator<OrdenPedidoMatPrimaDTO> Iterador = obtenerOrdenPedidoMatPrima().iterator();
		Integer NvoId=0;
		while(Iterador.hasNext())
		{
			OrdenPedidoMatPrimaDTO elemento = Iterador.next();
			if (elemento.getIdCompra()>NvoId)
				NvoId=elemento.getIdCompra();
		}
		return (NvoId+1);
				
	}

	public boolean dependeDe(ProveedorDTO aux) {
		boolean depende =false;
		Iterator<OrdenPedidoMatPrimaDTO> Iterador = obtenerOrdenPedidoMatPrima().iterator();
		while(Iterador.hasNext())
		{
			OrdenPedidoMatPrimaDTO elemento = Iterador.next();
			if (elemento.getProveedor().getId() == aux.getId()){
				depende = true;
				break;
			}
		}
		return depende;
	}

	public void actualizarDatos(OrdenPedidoMatPrimaDTO ordenSeleccionada) {
		this.ordenMatPrima.actualizarDatos(ordenSeleccionada);
		
	}

	public ArrayList<OrdenPedidoMatPrimaDTO> reporteEnRango(String dia, String mes, String año) {
		return this.ordenMatPrima.reporteEnRango(dia,mes,año);
	}
}