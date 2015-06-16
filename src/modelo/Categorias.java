package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import dao.CategoriaDAO;
import dto.CategoriaDTO;
import dto.ProveedorDTO;

public class Categorias {
	private CategoriaDAO categoria;
	
	public Categorias()
	{
		categoria=new CategoriaDAO();
	}
	
	public Boolean agregarCategoria(CategoriaDTO agregarCategoria)
	{
		return this.categoria.insert(agregarCategoria);
		
	}
	
	public void quitarCategoria(CategoriaDTO quitarCategoria)
	{
		this.categoria.delete(quitarCategoria);
	}
	
	public List<CategoriaDTO> obtenerCategorias()
	{
		return this.categoria.readAll();
	}

	public CategoriaDTO buscarCategoria(int idcategoria) 
	{
		List<CategoriaDTO> categorias=this.obtenerCategorias();
		Iterator<CategoriaDTO> Iterador = categorias.iterator();
		while(Iterador.hasNext())
		{
			CategoriaDTO elemento = Iterador.next();
			if(elemento.getIdCategoria().equals(idcategoria))
				return elemento;
		}
		return null;
	}

	public String iditemsCategorias(ProveedorDTO proveedor) 
	{
		String listaId="";
		Iterator<CategoriaDTO> Iterador = proveedor.getCategoria().iterator();
		while(Iterador.hasNext())
		{
			CategoriaDTO elemento = Iterador.next();
			listaId=listaId +elemento.getIdCategoria()+" ";
		}
		return listaId;
	}

	public ArrayList<CategoriaDTO> pasarDeStringAArray(String listado)
	{
		ArrayList<CategoriaDTO> result=new ArrayList<CategoriaDTO>();
		String a="";
		for (int i=0; i<listado.length(); i++)
		{
			
			  if (listado.charAt(i) != ' '&& listado.charAt(i+1)!=' ')
			  {
				  a=a+listado.charAt(i)+"";
				  
			  }
			  if(listado.charAt(i) != ' '&& listado.charAt(i+1)==' ')
			  {
				  a=a+listado.charAt(i);
				  int elemento=Integer.parseInt(a);
				  CategoriaDTO item=this.buscarCategoria(elemento);
				  result.add(item);
				  a="";
			  }
			  else if(listado.charAt(i)==' ' && listado.charAt(i+1)==' ')
				  break;
		}
		return result;
	}


	public boolean contiene(String nomCategoria){
		Iterator<CategoriaDTO> Iterador = obtenerCategorias().iterator();
		while(Iterador.hasNext())
		{
			CategoriaDTO elemento = Iterador.next();
			if (elemento.getDenominacion().trim().toUpperCase().compareTo(nomCategoria.toUpperCase())==0 && !elemento.getFueEliminado())
				return true;
		}
		return false;
	}
	
	public void actualizarEliminado(Integer idCategoria, boolean eliminado){
		categoria.cambiarEstadoEliminado(idCategoria,eliminado);
	}

	public Integer getNvoId() {
		Iterator<CategoriaDTO> Iterador = obtenerCategorias().iterator();
		Integer nvoId = 0;
		while(Iterador.hasNext())
		{
			CategoriaDTO elemento = Iterador.next();
			if (nvoId < elemento.getIdCategoria())
				nvoId = elemento.getIdCategoria();
		}
		return nvoId+1;
	}

	public boolean contieneEnHabilitadas(String nomCategoria) {
		Iterator<CategoriaDTO> Iterador = obtenerCategorias().iterator();
		while(Iterador.hasNext())
		{
			CategoriaDTO elemento = Iterador.next();
			if (elemento.getDenominacion().trim().toUpperCase().compareTo(nomCategoria.toUpperCase())==0 && !elemento.getFueEliminado())
				return true;
		}
		return false;
	}

	public boolean contieneEnRechazadas(String nomCategoria) {
		Iterator<CategoriaDTO> Iterador = obtenerCategorias().iterator();
		while(Iterador.hasNext())
		{
			CategoriaDTO elemento = Iterador.next();
			if (elemento.getDenominacion().trim().toUpperCase().compareTo(nomCategoria.toUpperCase())==0 && elemento.getFueEliminado())
				return true;
		}
		return false;
	}

	public CategoriaDTO buscarCategoria(String Denominacion) {
		Iterator<CategoriaDTO> Iterador = obtenerCategorias().iterator();
		while(Iterador.hasNext())
		{
			CategoriaDTO elemento = Iterador.next();
			if (elemento.getDenominacion().trim().toUpperCase().compareTo(Denominacion.trim().toUpperCase())==0)
				return elemento;
		}
		return null;
	}
	
	
}
