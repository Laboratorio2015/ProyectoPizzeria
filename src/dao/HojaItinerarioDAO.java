package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Pedidos;
import modelo.Repartidores;

import conexion.Conexion;
import dto.HojaItinerarioDTO;
import dto.PedidoDTO;
import dto.RepartidorDTO;


public class HojaItinerarioDAO 
{
	private static final String insert = "INSERT INTO hojaitinerarios(idhojaitinerario,repartidor, pedido,fueeliminado) VALUES(?,?,?,?)";
	private static final String delete = "DELETE FROM hojaitinerarios WHERE idhojaitinerario = ?";
	private static final String readall = "SELECT * FROM hojaitinerarios";
	private static final String numItinerarios = "SELECT idhojaitinerario FROM hojaitinerarios";	
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(HojaItinerarioDTO itinerario)
	{
		PreparedStatement statement;
		try 
		{
			Pedidos ped=new Pedidos();
			String iditems= ped.iditemsPedido(itinerario);
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, itinerario.getIdHojaItinerario());
			statement.setInt(2, itinerario.getRepartidor().getIdRepartidor());
			statement.setString(3,iditems);
			statement.setBoolean(4, itinerario.getFueeliminado());
			
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
			{
				System.out.println("inserccion exitosa de itinerario");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("insercion fallida de itinerario");
			e.printStackTrace();
		}
		finally 
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(HojaItinerarioDTO itinerario_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, itinerario_a_eliminar.getIdHojaItinerario());
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("borrado exitoso de itinerario");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("borrado fallido de itinerario");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	public List<HojaItinerarioDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<HojaItinerarioDTO> categorias = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				Pedidos ite=new Pedidos();
				ArrayList<PedidoDTO>listaPedidos= ite.pasarDeStringAArray(resultSet.getString("pedido"));
				Repartidores rep=new Repartidores();
				RepartidorDTO repartidor=rep.buscarRepartidor(resultSet.getInt("repartidor"));
				categorias.add(new HojaItinerarioDTO(resultSet.getInt("idhojaitinerario"),
								repartidor,listaPedidos,resultSet.getBoolean("fueeliminado")));
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

	public List<Integer> obtenerIdItinerarios()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<Integer> itinerarios = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(numItinerarios);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				itinerarios.add(resultSet.getInt("idhojaitinerario"));
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
		return itinerarios;
	}
}
