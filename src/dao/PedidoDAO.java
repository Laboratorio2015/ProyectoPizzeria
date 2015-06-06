package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.Clientes;
import modelo.Items;
import modelo.Pedidos;

import dto.ClienteDTO;
import dto.ItemDTO;
import dto.PedidoDTO;
import conexion.Conexion;


public class PedidoDAO 
{
	private static final String insert = "INSERT INTO pedidos(idpedido, item,fecha, hora, estado, total, ticket,comanda, cliente,delivery) VALUES(?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM pedidos WHERE idpedido = ?";
	private static final String readall = "SELECT * FROM pedidos";
	private static final String pedidosPendientes="select * from pedidos where estado='solicitado'";
	private static final String listaItems="select iditem,producto, cantidad, comentario from pedidos P join items I on p.item=i.iditem and p.idpedido=";
	private static final String ultimoPedido="select max(idpedido) from pedidos;";
	private static final String borrarUltimoPedido="drop table ultimopedido;";
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(PedidoDTO pedido)
	{
		PreparedStatement statement;
		try 
		{
			Items ite=new Items();
			ArrayList<Integer> iditems= ite.iditems(pedido);
			Iterator<Integer> Iterador = iditems.iterator();
			statement = conexion.getSQLConexion().prepareStatement(insert);
			while(Iterador.hasNext())
			{
				Integer elemento = Iterador.next();			
				statement.setInt(1, pedido.getIdpedido());
				statement.setInt(2, elemento);
				statement.setDate(3, pedido.getFecha());
				statement.setTime(4, pedido.getHora());
				statement.setString(5,pedido.get_estado());
				statement.setInt(6, pedido.getTotal());
				statement.setInt(7, pedido.get_ticket());
				statement.setInt(8, pedido.get_comanda());
				statement.setInt(9, pedido.getCliente().getDni());
				statement.setBoolean(10, pedido.getLlevaDelivery());
				statement.executeUpdate();
			}
				System.out.println("inserccion exitosa de pedido");
				return true;
			
		} 
		catch (SQLException e) 
		{
			System.out.println("insercion fallida de pedido");
			e.printStackTrace();
		}
		finally 
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(PedidoDTO pedido_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, pedido_a_eliminar.getIdpedido());
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("borrado exitoso de pedido");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("borrado fallido de pedido");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}

		
	public List<PedidoDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PedidoDTO> pedidos = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				String y= resultSet.getString("estado");
				String estadoPedido="";
				for (int i=0; i<y.length(); i++)
				{
					  if (y.charAt(i) != ' ')
					    estadoPedido += y.charAt(i);
				}
				Items ite=new Items();
				Clientes cli=new Clientes();
				PedidoDTO aux=new PedidoDTO(resultSet.getInt("idpedido"),(ArrayList<ItemDTO>)ite.obtenerItemsPedido(resultSet.getInt("idpedido")),
				resultSet.getDate("fecha"),resultSet.getTime("hora"),estadoPedido,
				resultSet.getInt("total"),resultSet.getInt("ticket"),
				resultSet.getInt("comanda"),ClienteDTO.buscarCliente(cli.obtenerClientes(),(Integer)resultSet.getInt("cliente")),resultSet.getBoolean("delivery"));
				
				if(!PedidoDTO.estaPedido(pedidos, aux.getIdpedido()))
					pedidos.add(aux);

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
		return pedidos;
	}
	
	
	
	public List<PedidoDTO> pedidosPendientes()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PedidoDTO> pedidos = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(pedidosPendientes);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				String y= resultSet.getString("estado");
				String estadoPedido="";
				for (int i=0; i<y.length(); i++)
				{
					  if (y.charAt(i) != ' ')
					    estadoPedido += y.charAt(i);
				}
				Items ite=new Items();
				Clientes cli=new Clientes();
				PedidoDTO aux=new PedidoDTO(resultSet.getInt("idpedido"),(ArrayList<ItemDTO>)ite.obtenerItemsPedido(resultSet.getInt("idpedido")),
				resultSet.getDate("fecha"),resultSet.getTime("hora"),estadoPedido,
				resultSet.getInt("total"),resultSet.getInt("ticket"),
				resultSet.getInt("comanda"),ClienteDTO.buscarCliente(cli.obtenerClientes(),(Integer)resultSet.getInt("cliente")),resultSet.getBoolean("delivery"));
				if(!PedidoDTO.estaPedido(pedidos, aux.getIdpedido()))
					pedidos.add(aux);
				
				//clienteAux.buscarCliente(resultSet.getInt("cliente")
				//itemAux.obtenerListaItems(resultSet.getInt("idpedido"))
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
		return pedidos;
	}
}
