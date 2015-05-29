package modelo;

import java.util.Iterator;
import java.util.List;

import dto.ClienteDTO;
import dto.PedidoDTO;
import dto.ProveedorDTO;
import persistencia.dao.ProveedorDAO;

public class Proveedores 
{
	private ProveedorDAO proveedor;
	
	public Proveedores()
	{
		proveedor=new ProveedorDAO();
	}
	
	public void agregarProveedor(ProveedorDTO proveedorAgregar)
	{
		this.proveedor.insert(proveedorAgregar);
	}
	
	public void quitarProveedor(ProveedorDTO proveedorQuitar)
	{
		this.proveedor.delete(proveedorQuitar);
	}
	public List<ProveedorDTO> obtenerProveedor()
	{
		return this.proveedor.readAll();
	}
	
	public ProveedorDTO buscarProveedor(String nombre)
	{
		List<ProveedorDTO> productos=this.obtenerProveedor();
		Iterator<ProveedorDTO> Iterador = productos.iterator();
		while(Iterador.hasNext())
		{
			ProveedorDTO elemento = Iterador.next();
			if(elemento.getNombre().compareTo(nombre)==0)
				return elemento;
		}
		return null;		
	}
}
