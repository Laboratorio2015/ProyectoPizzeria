  package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.sf.jasperreports.engine.export.draw.Offset;
import modelo.Clientes;
import modelo.Items;
import modelo.ItemsPromociones;
import modelo.Promociones;
import dto.ClienteDTO;
import dto.ItemDTO;
import dto.ItemPromocionDTO;
import dto.PromocionDTO;
import dto.PedidoDTO;
import conexion.Conexion;


public class PedidoDAO 
{
	private static final String insert = "INSERT INTO pedidos(idpedido, item,fecha, hora, estado, total, ticket,comanda, cliente,llevadelivery,oferta,fueeliminado) VALUES(?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM pedidos WHERE idpedido = ?";
	private static final String readall = "SELECT * FROM pedidos";
	private static final String readItem = "SELECT item FROM pedidos";
	private static final String obtenerPedidosFecha = "SELECT * FROM pedidos where fecha=?";
	private static final String readPromo = "SELECT oferta FROM pedidos";
	private static final String pedidosPendientes="select * from pedidos where estado='solicitado'";
	//SELECT idpedido,item,total,oferta FROM pedidos WHERE estado='entregado' AND fueeliminado=FALSE AND fecha LIKE '%%-6-2015%';
	private static String select = "SELECT idpedido,item,total,oferta FROM pedidos " +
										"WHERE estado='entregado' AND fueeliminado=FALSE AND fecha LIKE '";
	@SuppressWarnings("unused")
	private static final String listaItems="select item from pedidos where idpedido=";
	@SuppressWarnings("unused")
	private static final String listaOfertas="select oferta from pedidos where idpedido=";
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(PedidoDTO pedido)
	{
		PreparedStatement statement;
		try 
		{			
			Items ite=new Items();
			String iditems= ite.iditemsPed(pedido);
			ItemsPromociones ofe=new ItemsPromociones();
			String idofertas= ofe.iditemsOferta(pedido);
			statement = conexion.getSQLConexion().prepareStatement(insert);		
				statement.setInt(1, pedido.getIdpedido());
				statement.setString(2, iditems);
				statement.setString(3, pedido.getFecha());
				statement.setString(4, pedido.getHora());
				statement.setString(5,pedido.get_estado());
				statement.setInt(6, pedido.getTotal());
				statement.setInt(7, pedido.get_ticket());
				statement.setInt(8, pedido.get_comanda());
				statement.setInt(9, pedido.getCliente().getIdcliente());
				statement.setBoolean(10, pedido.getLlevaDelivery());
				statement.setString(11, idofertas);
				statement.setBoolean(12, pedido.getFueeliminado());
				statement.executeUpdate();
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
				Clientes cli=new Clientes();
				ClienteDTO lab=cli.buscarClientePorID(resultSet.getInt("cliente"));
				Items ite=new Items();
				ArrayList<ItemDTO>listaItems= ite.pasarDeStringAArray(resultSet.getString("item"));
				ItemsPromociones ofe=new ItemsPromociones();
				ArrayList<ItemPromocionDTO> listOfertas=ofe.pasarDeStringAArrayItPromo(resultSet.getString("oferta"));
				PedidoDTO aux=new PedidoDTO(resultSet.getInt("idpedido"),listaItems,
				resultSet.getString("fecha"),resultSet.getString("hora"),estadoPedido,
				resultSet.getInt("total"),resultSet.getInt("ticket"),
				resultSet.getInt("comanda"),
				lab,resultSet.getBoolean("llevadelivery"),listOfertas,resultSet.getBoolean("fueeliminado"));
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
	
	public List<ItemDTO> readItem()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ItemDTO> itemPedid = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readItem);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				
				Items ite=new Items();
				ArrayList<ItemDTO>listaItems= ite.pasarDeStringAArray(resultSet.getString("item"));
				Iterator<ItemDTO> iterador=listaItems.iterator();
				while(iterador.hasNext())
				{
					itemPedid.add(iterador.next());
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
		return itemPedid;
	}
	public List<ItemPromocionDTO> readPromo()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ItemPromocionDTO> promoPed = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readPromo);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				ItemsPromociones ofe=new ItemsPromociones();
				ArrayList<ItemPromocionDTO> listOfertas=ofe.pasarDeStringAArrayItPromo(resultSet.getString("oferta"));
				Iterator<ItemPromocionDTO> iterador=listOfertas.iterator();
				while(iterador.hasNext())
				{
					promoPed.add(iterador.next());
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
		return promoPed;
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
				Clientes cli=new Clientes();
				Items ite=new Items();
				ArrayList<ItemDTO>listaItems= ite.pasarDeStringAArray(resultSet.getString("item"));
				ItemsPromociones ofe=new ItemsPromociones();
				ArrayList<ItemPromocionDTO> listOfertas=ofe.pasarDeStringAArrayItPromo(resultSet.getString("oferta"));
				PedidoDTO aux=new PedidoDTO(resultSet.getInt("idpedido"),listaItems,
				resultSet.getString("fecha"),resultSet.getString("hora"),estadoPedido,
				resultSet.getInt("total"),resultSet.getInt("ticket"),
				resultSet.getInt("comanda"),
				ClienteDTO.buscarCliente(cli.obtenerClientes(),resultSet.getInt("cliente")),
				resultSet.getBoolean("llevadelivery"),listOfertas,resultSet.getBoolean("fueeliminado"));
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
		
	public ArrayList<PedidoDTO> getPedidosVendidosPorFecha (String dia,String mes,String año) throws SQLException{
		select = "SELECT idpedido,item,total,oferta FROM pedidos WHERE estado='cobrado' AND fueeliminado=FALSE AND fecha LIKE '" + dia.toString() + "-" + mes.toString() + "-" + año + "%'";
		//System.out.println(select);
		PreparedStatement statement;
		ResultSet resultSet; //Guarda
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(select);
			resultSet = statement.executeQuery();			
			ArrayList<PedidoDTO> resultado = new ArrayList<PedidoDTO>();
			Items items=new Items();
			ItemsPromociones itemPromos = new ItemsPromociones();
			
			while (resultSet.next())
			{					
				// pruebas
				String arrayItems = String.valueOf(resultSet.getObject(2));
				if (resultSet.getObject(2) == null)
					arrayItems = "";				
				String arrayIdPromo = String.valueOf(resultSet.getObject(4));
				if (resultSet.getObject(4) == null)
					arrayIdPromo = "";
				//
				
				resultado.add(new PedidoDTO( (Integer)resultSet.getObject(1),items.pasarDeStringAArray(arrayItems)
											,(Integer)resultSet.getObject(3), itemPromos.pasarDeStringAArrayItPromo(arrayIdPromo) ));
			}
			return resultado;
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

	public List<PedidoDTO> pedidosDadoUnaFecha(String fecha)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PedidoDTO> pedidos = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(obtenerPedidosFecha);
			statement.setString(1,fecha);
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
				if(resultSet.getBoolean("fueeliminado")==false && estadoPedido=="cobrado")
				{	
					Clientes cli=new Clientes();
					ClienteDTO lab=cli.buscarClientePorID(resultSet.getInt("cliente"));
					Items ite=new Items();
					ArrayList<ItemDTO>listaItems= ite.pasarDeStringAArray(resultSet.getString("item"));
					ItemsPromociones ofe=new ItemsPromociones();
					ArrayList<ItemPromocionDTO> listOfertas=ofe.pasarDeStringAArrayItPromo(resultSet.getString("oferta"));
					PedidoDTO aux=new PedidoDTO(resultSet.getInt("idpedido"),listaItems,
					resultSet.getString("fecha"),resultSet.getString("hora"),estadoPedido,
					resultSet.getInt("total"),resultSet.getInt("ticket"),
					resultSet.getInt("comanda"),
					lab,resultSet.getBoolean("llevadelivery"),listOfertas,resultSet.getBoolean("fueeliminado"));
					pedidos.add(aux);
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
		return pedidos;
	}
}
