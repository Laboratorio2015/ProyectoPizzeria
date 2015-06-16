package modelo;

import java.util.Iterator;
import java.util.List;

import dto.ProveedorDTO;
import dao.ProveedorDAO;

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
		nombre = nombre.toUpperCase();
		List<ProveedorDTO> productos=this.obtenerProveedor();
		Iterator<ProveedorDTO> Iterador = productos.iterator();
		while(Iterador.hasNext())
		{
			ProveedorDTO elemento = Iterador.next();
			if(elemento.getNombre().toUpperCase().compareTo(nombre)==0)
				return elemento;
		}
		return null;		
	}
	public ProveedorDTO buscarProveedorPorId(int idproveedor)
	{
		List<ProveedorDTO> proveedores=this.obtenerProveedor();
		Iterator<ProveedorDTO> Iterador = proveedores.iterator();
		while(Iterador.hasNext())
		{
			ProveedorDTO elemento = Iterador.next();
			if(elemento.getIdproveedor().equals(idproveedor))
				return elemento;
		}
		return null;
	}
	public Integer ultimoProveedor()
	{
		Integer ultimo=0;
		List<ProveedorDTO> proveedores=this.proveedor.readAll();
		Iterator<ProveedorDTO> Iterador = proveedores.iterator();
		while(Iterador.hasNext())
		{
			ProveedorDTO elemento = Iterador.next();
			if(elemento.getId()>ultimo)
				ultimo=elemento.getId();
		}
		return ultimo;
	}

	public boolean contienenCategoria(String nomCategoria) {
		Boolean existeCategoria = false;
		List<ProveedorDTO> proveedores=this.proveedor.readAll();
		Iterator<ProveedorDTO> Iterador = proveedores.iterator();
		while(Iterador.hasNext())
		{
			ProveedorDTO elemento = Iterador.next();
			if(elemento.isCategoria(nomCategoria)){
				existeCategoria =true;
				break;
			}
		}
		return existeCategoria;
	}

	public void actualizarEliminado(Integer idProveedor, boolean eliminado){
		proveedor.cambiarEstadoEliminado(idProveedor,eliminado);
	}

	public boolean existeEnHabilitados(String nvaDenominacion) {
		boolean existe =false;
		List<ProveedorDTO> proveedores=this.proveedor.readAll();
		Iterator<ProveedorDTO> Iterador = proveedores.iterator();
		while(Iterador.hasNext())
		{
			ProveedorDTO elemento = Iterador.next();
			if(!elemento.getFueeliminado() && elemento.getNombre().toUpperCase().compareTo(nvaDenominacion.toUpperCase())==0){
				existe = true;
				break;
			}
		}
		return existe;
	}

	public boolean existeEnEliminados(String nvaDenominacion) {
		boolean existe =false;
		List<ProveedorDTO> proveedores=this.proveedor.readAll();
		Iterator<ProveedorDTO> Iterador = proveedores.iterator();
		while(Iterador.hasNext())
		{
			ProveedorDTO elemento = Iterador.next();
			if(elemento.getFueeliminado() && elemento.getNombre().toUpperCase().compareTo(nvaDenominacion.toUpperCase())==0){
				existe = true;
				break;
			}
		}
		return existe;
	}

	public void actualizarDatos(ProveedorDTO provSeleccionado) {
		proveedor.actualizarDatos(provSeleccionado);
	}
	
}
