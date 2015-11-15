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
	private static final String updateValorEliminado = "UPDATE categorias SET fueeliminado=";

	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(CategoriaDTO categoria)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("create table IF NOT EXISTS categorias ( idcategoria integer not null, nombre char(30), fueeliminado boolean, primary key (idcategoria))");
			statement.execute();
			
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
				String t= resultSet.getString("nombre");
				String nombre="";
				for (int i=0; i<t.length(); i++)
				{
					  if (t.charAt(i) != ' ' || (t.charAt(i)==' ' && t.charAt(i+1)!=' '))
					    nombre += t.charAt(i);
					  else if(t.charAt(i)==' ' && t.charAt(i+1)==' ')
						  break;
				}
				categorias.add(new CategoriaDTO(resultSet.getInt("idcategoria"),nombre,
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
	
	public boolean cambiarEstadoEliminado(Integer idCategoria, boolean eliminado){
		//el elemento con dicho id debe cambiar su campo 'fue eliminado' a true.
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(updateValorEliminado + eliminado + " where idcategoria=" +idCategoria+";" );
			//	private static final String updateValorEliminado = "UPDATE categorias SET fueeliminado=";
			//"UPDATE categorias SET fueeliminado=true where idcategoria='Federicolopez';"
			//statement.setInt(1, categoria_a_eliminar.getIdCategoria());
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("Actualizacion de categoria exitosa");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Actualizacion de categoria fallo");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}
}
