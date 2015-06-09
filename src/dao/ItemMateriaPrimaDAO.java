package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.ItemMateriasPrimas;
import modelo.MatPrimas;
import conexion.Conexion;
import dto.ItemMateriaPrimaDTO;
import dto.MateriaPrimaDTO;

public class ItemMateriaPrimaDAO 
{
	private static final String insert = "INSERT INTO itemmatprimas(iditemmatprima,matprima,cantidad ,fueeliminado) VALUES(?,?,?,?)";
	private static final String delete = "DELETE FROM itemmatprimas WHERE iditemmatprima = ?";
	private static final String readall = "SELECT * FROM itemmatprimas";
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(ItemMateriaPrimaDTO itemmatprima)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, itemmatprima.getIdItemMatPrima());
			statement.setInt(2, itemmatprima.getItemMatPrima().getIdMatPrima());
			statement.setInt(3, itemmatprima.getCantidad());
			statement.setBoolean(4, itemmatprima.getFueEliminado());
			
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
			{
				System.out.println("inserccion exitosa de item de materia prima");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("insercion fallida de item de materia prima");
			e.printStackTrace();
		}
		finally 
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(ItemMateriaPrimaDTO materiaPrima_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, materiaPrima_a_eliminar.getIdItemMatPrima());
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("borrado exitoso de item de materia prima");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("borrado fallido de item de materia prima");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	public List<ItemMateriaPrimaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ItemMateriaPrimaDTO> itemsmatprimas = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				MatPrimas ite=new MatPrimas();
				MateriaPrimaDTO item=ite.buscarMatPrima(resultSet.getInt("matprima"));
				itemsmatprimas.add(new ItemMateriaPrimaDTO(resultSet.getInt("iditemmatprima"),
						item,resultSet.getInt("matprima"),resultSet.getBoolean("fueeliminado")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return itemsmatprimas;
	}
}
