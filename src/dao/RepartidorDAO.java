package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dto.ProveedorDTO;
import dto.RepartidorDTO;
import conexion.Conexion;

public class RepartidorDAO 
{
		private static final String insert = "INSERT INTO repartidores(idrepartidor,dni,nombre,apellido,fechaNacimiento,telefono,estado) VALUES(?, ?, ?, ?, ?, ?, ?)";
		private static final String delete = "DELETE FROM repartidores WHERE idrepartidor = ?";
		private static final String readall = "SELECT * FROM repartidores";
		private static final Conexion conexion = Conexion.getConexion();
		
		
		public boolean insert(RepartidorDTO repartidor)
		{
			PreparedStatement statement;
			try 
			{
				statement = conexion.getSQLConexion().prepareStatement(insert);
				statement.setInt(1, repartidor.getId());
				statement.setInt(2, repartidor.getDni());
				statement.setString(3, repartidor.getNombre());
				statement.setString(4, repartidor.getApellido());
				statement.setString(5, repartidor.getFechaNacimiento());
				statement.setString(6,repartidor.getTelefono());
				statement.setString(7, repartidor.getEstado());


				
				if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
				{
					System.out.println("inserccion exitosa de repartidor");
					return true;
				}
			} 
			catch (SQLException e) 
			{
				System.out.println("insercion fallida de repartidor");
				e.printStackTrace();
			}
			finally 
			{
				conexion.cerrarConexion();
			}
			return false;
		}
		
		public boolean delete(RepartidorDTO repartidor_a_eliminar)
		{
			PreparedStatement statement;
			int chequeoUpdate=0;
			try 
			{
				statement = conexion.getSQLConexion().prepareStatement(delete);
				statement.setInt(1, repartidor_a_eliminar.getId());
				chequeoUpdate = statement.executeUpdate();
				if(chequeoUpdate > 0)
				{
					System.out.println("borrado exitoso de repartidor");
					return true;
				}
			} 
			catch (SQLException e) 
			{
				System.out.println("borrado fallido de repartidor");
				e.printStackTrace();
			}
			finally
			{
				conexion.cerrarConexion();
			}
			return false;
		}

		public List<RepartidorDTO> readAll()
		{
			PreparedStatement statement;
			ResultSet resultSet; //Guarda el resultado de la query
			ArrayList<RepartidorDTO> repartidores = new ArrayList<>();
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
					String e= resultSet.getString("apellido");
					String apellido="";
					for (int i=0; i<e.length(); i++)
					{
						  if (e.charAt(i) != ' ' || (e.charAt(i)==' ' && e.charAt(i+1)!=' '))
						    apellido += e.charAt(i);
						  else if(e.charAt(i)==' ' && e.charAt(i+1)==' ')
							  break;
					}
					repartidores.add(new RepartidorDTO(resultSet.getInt("idrepartidor"), resultSet.getInt("dni"),nombre,
					apellido, resultSet.getString("fechanacimiento"), resultSet.getString("telefono"),resultSet.getString("estado")));
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
			return repartidores;
		}
	
}
