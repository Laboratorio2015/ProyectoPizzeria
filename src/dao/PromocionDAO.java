package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Items;
import conexion.Conexion;
import dto.ItemDTO;
import dto.ProductoDTO;
import dto.PromocionDTO;

public class PromocionDAO {
	private static final String insert = "INSERT INTO ofertas(idoferta, nombre,precio,item,fueeliminado) VALUES(?,?,?,?,?)";
	private static final String delete = "update ofertas set fueeliminado=true WHERE idoferta = ?";
	private static final String readall = "SELECT * FROM ofertas ";
	private static final String actualizarDatos="Update ofertas Set nombre=?, precio=?, item=?,fueeliminado=? where idoferta=?";
	
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(PromocionDTO oferta)
	{
		PreparedStatement statement;
		try 
		{
			Items ite=new Items();
			String iditems= ite.iditemsOfe(oferta);
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, oferta.getIdOferta());
			statement.setString(2, oferta.getNombre());
			statement.setDouble(3, oferta.getPrecio());
			statement.setString(4,iditems);
			statement.setBoolean(5,oferta.getFueeliminado());

			
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
			{
				System.out.println("inserccion exitosa de promocion");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("insercion fallida de promocion");
			e.printStackTrace();
		}
		finally 
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(PromocionDTO oferta_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, oferta_a_eliminar.getIdOferta());
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("borrado exitoso de item");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("borrado fallido de item");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	public List<PromocionDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PromocionDTO> ofertas = new ArrayList<>();
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
				Items a=new Items();
				ArrayList<ItemDTO>listaItems= a.pasarDeStringAArray(resultSet.getString("item"));
				ofertas.add(new PromocionDTO(resultSet.getInt("idoferta"),nombre,
						resultSet.getInt("precio"),listaItems,resultSet.getBoolean("fueeliminado")));
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
		return ofertas;
	}
	public List<PromocionDTO> readAlli()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PromocionDTO> ofertas = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				Items a=new Items();
				ArrayList<ItemDTO>listaItems= a.pasarDeStringAArray(resultSet.getString("item"));
				ofertas.add(new PromocionDTO(resultSet.getInt("idoferta"),resultSet.getString("nombre"),
						resultSet.getInt("precio"),listaItems,resultSet.getBoolean("fueeliminado")));

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
		return ofertas;
	}
	
	//actualiza los datos de un producto
		public boolean actualizarProducto(PromocionDTO PromocionActualizar)
		{
			PreparedStatement statement;
			int chequeoUpdate=0;
			try 
			{
				statement = conexion.getSQLConexion().prepareStatement(actualizarDatos);
				//actializar los datos
				Items ite=new Items();
				String iditems= ite.iditemsOfe(PromocionActualizar);
				statement.setString(1, PromocionActualizar.getNombre());
				statement.setInt(2, PromocionActualizar.getPrecio());
				statement.setString(3, iditems);
				statement.setBoolean(4, PromocionActualizar.getFueeliminado());
				statement.setInt(5, PromocionActualizar.getIdOferta());
				chequeoUpdate = statement.executeUpdate();
				if(chequeoUpdate > 0)
				{
					System.out.println("Actualizacion exitosa de promocion");
					return true;
				}
			} 
			catch (SQLException e) 
			{
				System.out.println("Actualizacion fallida de promocion");
				e.printStackTrace();
			}
			finally
			{
				conexion.cerrarConexion();
			}
			return false;
		}
}
