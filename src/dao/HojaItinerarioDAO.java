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
	private static final String insert = "INSERT INTO hojaitinerarios(idhojaitinerario,numhjaitinerario,repartidor, pedido,fecha,fueeliminado) VALUES(?,?,?,?,?,?)";
	private static final String delete = "DELETE FROM hojaitinerarios WHERE idhojaitinerario = ?";
	private static final String readall = "SELECT * FROM hojaitinerarios";
	private static final String crearTablaTemporal= "CREATE Temporary  table IF NOT EXISTS auxiliar (id integer)";
	private static final String crearTablaTemporal2= "CREATE Temporary table IF NOT EXISTS auxiliar2 (id integer)";
	private static final String agregarFechaTablaTemporal= "insert into auxiliar(Select max(numhjaitinerario) from hojaitinerarios where fecha = ? )";
	private static final String agregaTablaTemporal= "insert into auxiliar2(Select max(idhojaitinerario) from hojaitinerarios)";
	private static final String ultimoedidoFecha= "select * from auxiliar";
	private static final String ultimoedidoFecha2= "select * from auxiliar2";
	private static final String buscarItinerario = "SELECT * FROM hojaitinerarios where idhojaitinerario=?";
	private static final String numItinerarios = "SELECT idhojaitinerario FROM hojaitinerarios";	
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(HojaItinerarioDTO itinerario)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("create table IF NOT EXISTS hojaitinerarios( idhojaitinerario integer not null, numhjaitinerario integer not null,repartidor integer not null, pedido char(200), fecha char(10), fueeliminado boolean, primary key (idhojaitinerario), foreign key (repartidor) references repartidores(idrepartidor))");
			statement.execute();
			
			Pedidos ped=new Pedidos();
			String iditems= ped.iditemsPedido(itinerario);
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, itinerario.getIdHojaItinerario());
			statement.setInt(2, itinerario.getNumItinerario());
			statement.setInt(3, itinerario.getRepartidor().getIdRepartidor());
			statement.setString(4,iditems);
			statement.setString(5, itinerario.getFecha());
			statement.setBoolean(6, itinerario.getFueeliminado());
			
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
				ArrayList<PedidoDTO>listaPedidos= ite.pasarDeStringAArray(resultSet.getString("pedido"),resultSet.getString("fecha"));
				Repartidores rep=new Repartidores();
				RepartidorDTO repartidor=rep.buscarRepartidor(resultSet.getInt("repartidor"));
				categorias.add(new HojaItinerarioDTO(resultSet.getInt("idhojaitinerario"), resultSet.getInt("numhjaitinerario"),
								repartidor,listaPedidos,resultSet.getBoolean("fueeliminado"), resultSet.getString("fecha")));
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

	public HojaItinerarioDTO buscarItinerario(Integer iditinerario)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		HojaItinerarioDTO itinerario = new HojaItinerarioDTO();
		try 
		{
			
			statement = conexion.getSQLConexion().prepareStatement(buscarItinerario);
			statement.setInt(1, iditinerario);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				Pedidos ite=new Pedidos();
				ArrayList<PedidoDTO>listaPedidos= ite.pasarDeStringAArray(resultSet.getString("pedido"),resultSet.getString("fecha"));
				Repartidores rep=new Repartidores();
				RepartidorDTO repartidor=rep.buscarRepartidor(resultSet.getInt("repartidor"));
				itinerario=new HojaItinerarioDTO(resultSet.getInt("idhojaitinerario"),resultSet.getInt("numhjaitinerario"),
								repartidor,listaPedidos,resultSet.getBoolean("fueeliminado"),resultSet.getString("fecha"));
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
		return itinerario;
	}

	
	//obtiene el numero maximo de itinerario dado una fecha 
	public int UltimoItinerarioDeLaFecha(String fecha) {
		int ultimo=0;
		PreparedStatement statement;
		PreparedStatement statement1;
		PreparedStatement statement2;
		ResultSet resultSet; //Guarda el resultado de la query
		try 
		{
			//crea tabla temporal
			statement1= conexion.getSQLConexion().prepareStatement(crearTablaTemporal);
			statement1.execute();
			statement1= conexion.getSQLConexion().prepareStatement("delete from auxiliar;");
			statement1.execute();
			//guarda el valor en la tabla temporal
			statement2 = conexion.getSQLConexion().prepareStatement(agregarFechaTablaTemporal);
			statement2.setString(1, fecha);
			statement2.execute();
			//ejecuta la consulta
			statement = conexion.getSQLConexion().prepareStatement(ultimoedidoFecha);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				ultimo= resultSet.getInt("id");
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
		
		return ultimo;
		
	}

	public Integer UltimoItinerario() {
		int ultimo=0;
		PreparedStatement statement;
		PreparedStatement statement1;
		PreparedStatement statement2;
		ResultSet resultSet; //Guarda el resultado de la query
		try 
		{
			//crea tabla temporal
			statement1= conexion.getSQLConexion().prepareStatement(crearTablaTemporal2);
			statement1.execute();
			statement1= conexion.getSQLConexion().prepareStatement("delete from auxiliar2;");
			statement1.execute();
			//guarda el valor en la tabla temporal
			statement2 = conexion.getSQLConexion().prepareStatement(agregaTablaTemporal);
			statement2.execute();
			//ejecuta la consulta
			statement = conexion.getSQLConexion().prepareStatement(ultimoedidoFecha2);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				ultimo= resultSet.getInt("id");
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
		
		return ultimo;
	}

	public List<HojaItinerarioDTO> ItinerarioDeFecha(String fecha)
	{
		ArrayList<HojaItinerarioDTO> categorias = new ArrayList<>();		
		PreparedStatement statement;
		PreparedStatement statement1;
		PreparedStatement statement2;
		ResultSet resultSet; //Guarda el resultado de la query
		try 
		{
			//crea tabla temporal
			statement1= conexion.getSQLConexion().prepareStatement("CREATE Temporary  table IF NOT EXISTS iti (iditi integer,numiti integer, pedidos char(20))");
			statement1.execute();
			statement1= conexion.getSQLConexion().prepareStatement("delete from iti;");
			statement1.execute();
			//guarda el valor en la tabla temporal
			statement2 = conexion.getSQLConexion().prepareStatement("insert into iti (select idhojaitinerario, numhjaitinerario, pedido from hojaitinerarios where fecha=? order by idhojaitinerario)");
			statement2.setString(1, fecha);
			statement2.execute();
			//ejecuta la consulta
			statement = conexion.getSQLConexion().prepareStatement("select * from iti");
			resultSet = statement.executeQuery();	
			while(resultSet.next())
			{
				Pedidos ite=new Pedidos();
				ArrayList<PedidoDTO>listaPedidos= ite.pasarDeStringAArray(resultSet.getString("pedidos"),fecha);
				categorias.add(new HojaItinerarioDTO(resultSet.getInt("iditi"), resultSet.getInt("numiti"),
								null,listaPedidos,null,fecha));
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

	public List<HojaItinerarioDTO> obtenerItinerariosFechaCompleto(String fecha)
	{
		ArrayList<HojaItinerarioDTO> categorias = new ArrayList<>();		
		PreparedStatement statement1;
		ResultSet resultSet; //Guarda el resultado de la query
		try 
		{
			//crea tabla temporal
			statement1= conexion.getSQLConexion().prepareStatement("select * from hojaitinerarios where fecha=?");
			statement1.setString(1, fecha);			
			resultSet = statement1.executeQuery();	
			while(resultSet.next())
			{
				Pedidos ite=new Pedidos();
				ArrayList<PedidoDTO>listaPedidos= ite.pasarDeStringAArray(resultSet.getString("pedido"),fecha);
				Repartidores rep=new Repartidores();
				RepartidorDTO repartidor=rep.buscarRepartidor(resultSet.getInt("repartidor"));
				categorias.add(new HojaItinerarioDTO(resultSet.getInt("idhojaitinerario"), resultSet.getInt("numhjaitinerario"),
								repartidor,listaPedidos,resultSet.getBoolean("fueeliminado"), fecha));
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
}
