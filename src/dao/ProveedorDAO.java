package persistencia.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.ProveedorDTO;
import persistencia.conexion.Conexion;

public class ProveedorDAO {
	private static final String insert = "INSERT INTO proveedores(idproveedor, nombre,telefono, direccion) VALUES(?, ?, ?, ?)";
	private static final String delete = "DELETE FROM proveedores WHERE idproveedor = ?";
	private static final String readall = "SELECT * FROM proveedores";
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(ProveedorDTO proveedor)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, proveedor.getId());
			statement.setString(2, proveedor.getNombre());
			statement.setString(3,proveedor.getTelefono());
			statement.setString(4, proveedor.getDireccion());


			
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
			{
				System.out.println("inserccion exitosa de proveedor");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("insercion fallida de proveedor");
			e.printStackTrace();
		}
		finally 
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(ProveedorDTO proveedor_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, proveedor_a_eliminar.getId());
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("borrado exitoso de proveedor");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("borrado fallido de proveedor");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	public List<ProveedorDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				proveedores.add(new ProveedorDTO(resultSet.getInt("id"), resultSet.getString("nombre"),
				resultSet.getString("telefono"),resultSet.getString("direccion")));
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
		return proveedores;
	}
	

}
