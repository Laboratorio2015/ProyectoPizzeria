package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.ClienteDTO;
import conexion.Conexion;

public class ClienteDAO 
{
	private static final String insert = "INSERT INTO clientes(idcliente,dni, nombre,apellido, calle, numeracion,telefono,entrecalle1,entrecalle2,codpostal,email,comentario,fueeliminado) VALUES(?,?,?,?,?,?,?, ?, ?, ?, ?,?,?)";
	private static final String delete = "DELETE FROM clientes WHERE idcliente = ?";
	private static final String readall = "SELECT * FROM clientes";
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(ClienteDTO cliente)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, cliente.getIdcliente());
			statement.setInt(2, cliente.getDni());
			statement.setString(3, cliente.getNombre());
			statement.setString(4, cliente.getApellido());
			statement.setString(5, cliente.getCalle());
			statement.setString(6,cliente.getNumeracion());
			statement.setString(7, cliente.getTelefono());
			statement.setString(8, cliente.getEntrecalle1());
			statement.setString(9, cliente.getEntrecalle2());
			statement.setString(10, cliente.getCodPostal());
			statement.setString(11, cliente.getEmail());
			statement.setString(12, cliente.getComentario());
			statement.setBoolean(13, cliente.getFueeliminado());
			
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
			{
				System.out.println("inserccion exitosa de cliente");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("insercion fallida de cliente");
			e.printStackTrace();
		}
		finally 
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(ClienteDTO cliente_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, cliente_a_eliminar.getDni());
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("borrado exitoso de cliente");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("borrado fallido de cliente");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	public List<ClienteDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ClienteDTO> clientes = new ArrayList<>();
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
				String ti= resultSet.getString("apellido");
				String apellido="";
				for (int i=0; i<ti.length(); i++)
				{
					  if (ti.charAt(i) != ' ' || (ti.charAt(i)==' ' && ti.charAt(i+1)!=' '))
					    apellido += ti.charAt(i);
					  else if(ti.charAt(i)==' ' && ti.charAt(i+1)==' ')
						  break;
				}
				clientes.add(new ClienteDTO(resultSet.getInt("idcliente"),resultSet.getInt("dni"),
						nombre,apellido,resultSet.getString("calle"),resultSet.getString("numeracion"),
						resultSet.getString("telefono"),resultSet.getString("entrecalle1"),
						resultSet.getString("entrecalle2"), resultSet.getString("codpostal"),
						resultSet.getString("email"), resultSet.getString("comentario"),
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
		return clientes;
	}
	
	/*public ClienteDTO buscarCliente(Integer dni)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ClienteDTO cliente;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(obtenercliente);
			statement.setInt(1, 12333222);
			resultSet = statement.executeQuery();
			cliente=new ClienteDTO(resultSet.getInt("dni"), resultSet.getString("nombre"),
			resultSet.getString("apellido"),resultSet.getString("direccion"),resultSet.getString("telefono"));
			conexion.cerrarConexion();
			return cliente;
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return null;
	}*/

}
