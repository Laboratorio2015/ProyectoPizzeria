package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.ClienteDTO;
import dto.ProductoDTO;
import conexion.Conexion;

public class ClienteDAO 
{
	private static final String insert = "INSERT INTO clientes(idcliente,dni, nombre,apellido, calle, numeracion,telefono,entrecalle1,entrecalle2,codpostal,email,comentario,fueeliminado) VALUES(?,?,?,?,?,?,?, ?, ?, ?, ?,?,?)";
	private static final String delete = "update clientes set fueeliminado=true WHERE idcliente = ?";
	private static final String readall = "SELECT * FROM clientes";
	private static final String obtenerDatos = "SELECT idcliente,dni, nombre,apellido FROM clientes where fueeliminado=false";
	private static final String actualizarDatos="Update clientes Set dni=?, nombre=?, apellido=?,calle=?,numeracion=?,telefono=?,entrecalle1=?,entrecalle2=?,codpostal=?,email=?,comentario=?,fueeliminado=? where idcliente=?";
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
			statement.setInt(1, cliente_a_eliminar.getIdcliente());
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
				if(resultSet.getBoolean("fueeliminado")==false)
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
					
					String tu= resultSet.getString("calle");
					String calle="";
					for (int i=0; i<tu.length(); i++)
					{
						  if (tu.charAt(i) != ' ' || (tu.charAt(i)==' ' && tu.charAt(i+1)!=' '))
						    calle += tu.charAt(i);
						  else if(tu.charAt(i)==' ' && tu.charAt(i+1)==' ')
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
							nombre,apellido,calle,resultSet.getString("numeracion"),
							resultSet.getString("telefono"),resultSet.getString("entrecalle1"),
							resultSet.getString("entrecalle2"), resultSet.getString("codpostal"),
							resultSet.getString("email"), resultSet.getString("comentario"),
							resultSet.getBoolean("fueeliminado")));
				}
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
	
	public List<ClienteDTO> readAlli()
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
	
	//actualiza los datos de un cliente
		public boolean actualizarCliente(ClienteDTO ClienteActualizar)
		{
			PreparedStatement statement;
			int chequeoUpdate=0;
			try 
			{
				statement = conexion.getSQLConexion().prepareStatement(actualizarDatos);
				//actializar los datos
				statement.setInt(1, ClienteActualizar.getDni());
				statement.setString(2, ClienteActualizar.getNombre());
				statement.setString(3, ClienteActualizar.getApellido());
				statement.setString(4, ClienteActualizar.getCalle());
				statement.setString(5, ClienteActualizar.getNumeracion());
				statement.setString(6, ClienteActualizar.getTelefono());
				statement.setString(7, ClienteActualizar.getEntrecalle1());
				statement.setString(8, ClienteActualizar.getEntrecalle2());
				statement.setString(9, ClienteActualizar.getCodPostal());
				statement.setString(10, ClienteActualizar.getEmail());
				statement.setString(11, ClienteActualizar.getComentario());
				statement.setBoolean(12, ClienteActualizar.getFueeliminado());
				statement.setInt(13, ClienteActualizar.getIdcliente());
				chequeoUpdate = statement.executeUpdate();
				if(chequeoUpdate > 0)
				{
					System.out.println("Actualizacion exitosa de producto");
					return true;
				}
			} 
			catch (SQLException e) 
			{
				System.out.println("Actualizacion fallida de producto");
				e.printStackTrace();
			}
			finally
			{
				conexion.cerrarConexion();
			}
			return false;
		}

		public List<ClienteDTO> obtenerDatos()
		{
			PreparedStatement statement;
			ResultSet resultSet; //Guarda el resultado de la query
			ArrayList<ClienteDTO> clientes = new ArrayList<>();
			try 
			{
				statement = conexion.getSQLConexion().prepareStatement(obtenerDatos);
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
							nombre,apellido,"","",
							"","","","","","",false));
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
}
