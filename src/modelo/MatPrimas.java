package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dto.ItemDTO;
import dto.MateriaPrimaDTO;
import dto.PedidoDTO;
import dto.ProveedorDTO;
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

}
