package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexion.Conexion;
import dto.CategoriaDTO;


public class CategoriaDAO 
{
	private static final String insert = "INSERT INTO categorias(idcategoria,nombre, fueeliminado) VALUES(?,?,?)";
	private static final String delete = "DELETE FROM categorias WHERE idcategoria = ?";
	private static final String readall = "SELECT * FROM categorias";
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(CategoriaDTO categoria)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, categoria.getIdCategoria());
			statement.setString(2, categoria.getDenominacion());
			statement.setBoolean(3, categoria.getFueEliminado());
			
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
			{
				System.out.println("inserccion exitosa de categoria");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("insercion fallida de categoria");
			e.printStackTrace();
		}
		finally 
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(CategoriaDTO categoria_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, categoria_a_eliminar.getIdCategoria());
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("borrado exitoso de categoria");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("borrado fallido de categoria");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	public List<CategoriaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<CategoriaDTO> categorias = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				categorias.add(new CategoriaDTO(resultSet.getInt("idcategoria"),resultSet.getString("nombre"),
						resultSet.getBoolean("fueeliminado")));
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
		return categorias;
	}
}
