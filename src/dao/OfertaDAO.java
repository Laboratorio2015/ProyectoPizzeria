package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Items;
import modelo.Productos;
import conexion.Conexion;
import dto.ItemDTO;
import dto.OfertaDTO;
import dto.ProductoDTO;

public class OfertaDAO {
	private static final String insert = "INSERT INTO ofertas(idoferta, nombre,precio,item,fueeliminado) VALUES(?,?,?,?,?)";
	private static final String delete = "DELETE FROM ofertas WHERE idoferta = ?";
	private static final String readall = "SELECT * FROM ofertas ";
	
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(OfertaDTO oferta)
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
	
	public boolean delete(OfertaDTO oferta_a_eliminar)
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

	public List<OfertaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<OfertaDTO> ofertas = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				Items a=new Items();
				ArrayList<ItemDTO>listaItems= a.pasarDeStringAArray(resultSet.getString("item"));
				ofertas.add(new OfertaDTO(resultSet.getInt("idoferta"),resultSet.getString("nombre"),
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
}
