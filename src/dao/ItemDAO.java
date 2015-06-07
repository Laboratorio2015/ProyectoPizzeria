package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Productos;

import conexion.Conexion;
import dto.ItemDTO;
import dto.ProductoDTO;

public class ItemDAO 

{
	private static final String insert = "INSERT INTO items(iditem, producto,cantidad,comentario,fueeliminado) VALUES(?,?, ?, ?,?)";
	private static final String delete = "DELETE FROM items WHERE iditem = ?";
	private static final String readall = "SELECT * FROM items ";
	private static final String obtenerlistaitems="select iditem,producto, cantidad, comentario from pedidos P join items I on p.item=i.iditem and p.idpedido= ?;";
	private static final String obtenerItem="select * from items where iditem = ?";
	
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(ItemDTO item)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, item.getIditem());
			statement.setInt(2, item.getProducto().getIdproducto());
			statement.setInt(3, item.getCantidad());
			statement.setString(4, item.getComentario());
			statement.setBoolean(5, item.getFueeliminado());

			
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
			{
				System.out.println("inserccion exitosa de item");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("insercion fallida de item");
			e.printStackTrace();
		}
		finally 
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(ItemDTO item_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, item_a_eliminar.getIditem());
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

	public List<ItemDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ItemDTO> items = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				Productos a=new Productos();
				ProductoDTO productoAux=ProductoDTO.buscarProducto(a.obtenerProducto(),resultSet.getInt("iditem"));
				items.add(new ItemDTO(resultSet.getInt("iditem"),productoAux, resultSet.getInt("cantidad"),
						resultSet.getString("comentario"), resultSet.getBoolean("fueeliminado")));

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
		return items;
	}
	/*
	public ItemDTO buscarItem(Integer numItem)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ItemDTO item;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(obtenerItem);
			statement.setInt(1, numItem);
			resultSet = statement.executeQuery();
			ProductoDAO a =new ProductoDAO();
			item=new ItemDTO(resultSet.getInt("iditem"),a.buscarProducto(resultSet.getInt("producto")), resultSet.getInt("cantidad"),
						resultSet.getString("comentario"));
			conexion.cerrarConexion();
			return item;
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
	}
*/
	public ArrayList<ItemDTO> obtenerListaItems(Integer numPedido)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ItemDTO> items = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(obtenerlistaitems);
			statement.setInt(1,numPedido);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				Productos a =new Productos();
				ProductoDTO productoAux=ProductoDTO.buscarProducto(a.obtenerProducto(),resultSet.getInt("producto"));
				items.add(new ItemDTO(resultSet.getInt("iditem"),productoAux, resultSet.getInt("cantidad"),
						resultSet.getString("comentario"), resultSet.getBoolean("fueeliminado")));
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
		return items;
	}



}
