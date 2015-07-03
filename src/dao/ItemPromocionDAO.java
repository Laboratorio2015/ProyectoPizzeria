package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Productos;
import modelo.Promociones;
import conexion.Conexion;
import dto.ItemDTO;
import dto.ItemPromocionDTO;
import dto.ProductoDTO;
import dto.PromocionDTO;

public class ItemPromocionDAO 
{
	private static final String insert = "INSERT INTO itemPromocion(iditempromo, promocion,cantidad,comentario,fueeliminado) VALUES(?,?,?,?,?)";
	private static final String delete = "DELETE FROM itemPromocion WHERE iditempromo = ?";
	private static final String readall = "SELECT * FROM itemPromocion ";
	private static final String buscarPromo = "SELECT * FROM itemPromocion where iditempromo=?";
	private static final String obtenerlistaitems="select iditempromo,promocion,cantidad, comentario from pedidos P join itemPromocion I on p.oferta=i.iditempromo and p.idpedido= ?;";
	
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(ItemPromocionDTO item)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, item.getIditemPromo());
			statement.setInt(2, item.getPromocion().getIdOferta());
			statement.setInt(3, item.getCantidad());
			statement.setString(4, item.getComentario());
			statement.setBoolean(5, item.getFueeliminado());

			
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
			{
				System.out.println("inserccion exitosa de item  de promocion");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("insercion fallida de item de promocion");
			e.printStackTrace();
		}
		finally 
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(ItemPromocionDTO item_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, item_a_eliminar.getIditemPromo());
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("borrado exitoso de item de promocion");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("borrado fallido de item de promocion");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	public List<ItemPromocionDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ItemPromocionDTO> items = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				String t= resultSet.getString("comentario");
				String comentario="";
				for (int i=0; i<t.length(); i++)
				{
					  if (t.charAt(i) != ' ' || (t.charAt(i)==' ' && t.charAt(i+1)!=' '))
						  comentario += t.charAt(i);
					  else if(t.charAt(i)==' ' && t.charAt(i+1)==' ')
						  break;
				}
				Promociones a=new Promociones();
				PromocionDTO productoAux=a.buscarOferta(resultSet.getInt("promocion"));
				items.add(new ItemPromocionDTO(resultSet.getInt("iditempromo"),productoAux, resultSet.getInt("cantidad"),
						comentario, resultSet.getBoolean("fueeliminado")));

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
	public ArrayList<ItemPromocionDTO> obtenerListaItems(Integer numPedido)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ItemPromocionDTO> items = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(obtenerlistaitems);
			statement.setInt(1,numPedido);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				Promociones a =new Promociones();
				PromocionDTO productoAux=PromocionDTO.buscarProducto(a.obtenerOfertas(),resultSet.getInt("promocion"));
				items.add(new ItemPromocionDTO(resultSet.getInt("iditempromo"),productoAux, resultSet.getInt("cantidad"),
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

	public ItemPromocionDTO buscarPromo(Integer iditem)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ItemPromocionDTO item=new ItemPromocionDTO();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(buscarPromo);
			statement.setInt(1,iditem);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				Promociones a =new Promociones();
				PromocionDTO productoAux=PromocionDTO.buscarProducto(a.obtenerOfertas(),resultSet.getInt("promocion"));
				item=new ItemPromocionDTO(resultSet.getInt("iditempromo"),productoAux, resultSet.getInt("cantidad"),
				resultSet.getString("comentario"), resultSet.getBoolean("fueeliminado"));
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
		return item;
	}

}
