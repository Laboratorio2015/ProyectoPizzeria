package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProductoDTO;
import dto.RepartidorDTO;
import conexion.Conexion;


public class ProductoDAO 
{
	private static final String insert = "INSERT INTO productos(idproducto, nombre,precio,tipo,fueeliminado) VALUES(?,?, ?, ?,?)";
	private static final String delete = "update productos set fueeliminado=true WHERE idproducto = ?";
	private static final String readall = "SELECT * FROM productos";
	private static final String actualizarDatos="Update productos Set nombre=?, precio=?, tipo=?,fueeliminado=? where idproducto=?";
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(ProductoDTO producto)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, producto.getIdproducto());
			statement.setString(2, producto.getNombre());
			statement.setInt(3, producto.getPrecio());
			statement.setString(4, producto.getTipo());
			statement.setBoolean(5, producto.getFueeliminado());
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
			{
				System.out.println("inserccion exitosa de producto");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("insercion fallida de producto");
			e.printStackTrace();
		}
		finally 
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(ProductoDTO producto_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, producto_a_eliminar.getIdproducto());
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("borrado exitoso de producto");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("borrado fallido de producto");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	public List<ProductoDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ProductoDTO> productos = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				if(resultSet.getBoolean("fueeliminado")==false)
				{
					String y= resultSet.getString("nombre");
					String nombreProducto="";
					for (int i=0; i<y.length(); i++)
					{
						  if (y.charAt(i) != ' ')
						    nombreProducto += y.charAt(i);
					}
					
					String t= resultSet.getString("tipo");
					String tipoProducto="";
					for (int i=0; i<t.length(); i++)
					{
						  if (t.charAt(i) != ' ')
						    tipoProducto += t.charAt(i);
					}
					
					productos.add(new ProductoDTO(resultSet.getInt("idproducto"),nombreProducto,
							resultSet.getInt("precio"),tipoProducto,resultSet.getBoolean("fueeliminado")));
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
		return productos;
	}
	
	public List<ProductoDTO> readAlli()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ProductoDTO> productos = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				String y= resultSet.getString("nombre");
				String nombreProducto="";
				for (int i=0; i<y.length(); i++)
				{
					  if (y.charAt(i) != ' ')
					    nombreProducto += y.charAt(i);
				}
				
				String t= resultSet.getString("tipo");
				String tipoProducto="";
				for (int i=0; i<t.length(); i++)
				{
					  if (t.charAt(i) != ' ')
					    tipoProducto += t.charAt(i);
				}
				
				productos.add(new ProductoDTO(resultSet.getInt("idproducto"),nombreProducto,
						resultSet.getInt("precio"),tipoProducto,resultSet.getBoolean("fueeliminado")));
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
		return productos;
	}
	
	//actualiza los datos de un producto
	public boolean actualizarProducto(ProductoDTO ProductoActualizar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(actualizarDatos);
			//actializar los datos
			statement.setString(1, ProductoActualizar.getNombre());
			statement.setInt(2, ProductoActualizar.getPrecio());
			statement.setString(3, ProductoActualizar.getTipo());
			statement.setBoolean(4, ProductoActualizar.getFueeliminado());
			statement.setInt(5, ProductoActualizar.getIdproducto());
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
}
