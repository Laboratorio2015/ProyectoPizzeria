package modelo;

import java.util.Iterator;
import java.util.List;

import dto.CategoriaDTO;
import dto.MateriaPrimaDTO;
import dao.MateriaPrimaDAO;

public class MatPrimas {
	private MateriaPrimaDAO matPrima;
	
	public MatPrimas()
	{
		matPrima=new MateriaPrimaDAO();
	}

	public Boolean agregarMatPrima(MateriaPrimaDTO agregarMat)
	{
		return this.matPrima.insert(agregarMat);
		
	}
	
	public void quitarMatPrima(MateriaPrimaDTO quitarMatPrima)
	{
		this.matPrima.delete(quitarMatPrima);
	}
	
	public List<MateriaPrimaDTO> obtenerMatPrimas()
	{
		return this.matPrima.readAll();
	}
	
	public List<MateriaPrimaDTO> obtenerMatPrimas(Integer idproveedor)
	{
		return this.matPrima.obtenerListaMatPrima(idproveedor);
	}

	public MateriaPrimaDTO buscarMatPrima(int idmatprima)
	{
		List<MateriaPrimaDTO> matprima=this.obtenerMatPrimas();
		Iterator<MateriaPrimaDTO> Iterador = matprima.iterator();
		while(Iterador.hasNext())
		{
			MateriaPrimaDTO elemento = Iterador.next();
			if(elemento.getIdMatPrima().equals(idmatprima))
				return elemento;
		}
		return null;
	}

	public boolean contienenCategoria(String nomCategoria) {
		boolean existeCat = false;
		List<MateriaPrimaDTO> matprima=this.obtenerMatPrimas();
		Iterator<MateriaPrimaDTO> Iterador = matprima.iterator();
		while(Iterador.hasNext())
		{
			MateriaPrimaDTO elemento = Iterador.next();
			if(elemento.getCategoria().getDenominacion().trim().toUpperCase().compareTo(nomCategoria.toUpperCase()) == 0){
				existeCat = true;
				break;
			}
		}
		return existeCat;
				
	}

	public boolean contieneEnHabilitadas(String nomNvaMatPrima) {
		
		boolean repetida = false;
		List<MateriaPrimaDTO> matprima=this.obtenerMatPrimas();
		Iterator<MateriaPrimaDTO> Iterador = matprima.iterator();
		while(Iterador.hasNext())
		{
			MateriaPrimaDTO elemento = Iterador.next();
			if(!elemento.getFueeliminado() && elemento.getNombre().trim().toUpperCase().compareTo(nomNvaMatPrima.toUpperCase()) == 0){
				repetida = true;
				break;
			}
		}
		return repetida;
	}

	public boolean contieneEnRechazadas(String nomNvaMatPrima) {
		boolean repetida = false;
		List<MateriaPrimaDTO> matprima=this.obtenerMatPrimas();
		Iterator<MateriaPrimaDTO> Iterador = matprima.iterator();
		while(Iterador.hasNext())
		{
			MateriaPrimaDTO elemento = Iterador.next();
			if(elemento.getFueeliminado() &&  elemento.getNombre().trim().toUpperCase().compareTo(nomNvaMatPrima.toUpperCase()) == 0){
				repetida = true;
				break;
			}
		}
		return repetida;
	}

	public MateriaPrimaDTO buscarMatPrima(String nomNvaMatPrima) {
		List<MateriaPrimaDTO> matprima=this.obtenerMatPrimas();
		Iterator<MateriaPrimaDTO> Iterador = matprima.iterator();
		while(Iterador.hasNext())
		{
			MateriaPrimaDTO elemento = Iterador.next();
			if(elemento.getNombre().trim().toUpperCase().compareTo(nomNvaMatPrima.toUpperCase()) == 0){
				return elemento;
			}
		}
		return null;
	}

	public void actualizarMatPrima(MateriaPrimaDTO rehabilitarMatPrima) {
		matPrima.actualizarDatos(rehabilitarMatPrima);
	}

	public Integer getNvoId() {
		Iterator<MateriaPrimaDTO> Iterador = obtenerMatPrimas().iterator();
		Integer nvoId = 0;
		while(Iterador.hasNext())
		{
			MateriaPrimaDTO elemento = Iterador.next();
			if (nvoId < elemento.getIdMatPrima() )
				nvoId = elemento.getIdMatPrima();
		}
		return nvoId+1;
	}
}


