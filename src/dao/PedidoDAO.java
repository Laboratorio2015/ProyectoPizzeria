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
import dto.ProductoDTO;
import dto.PromocionDTO;
import dto.PedidoDTO;
import conexion.Conexion;


public class PedidoDAO 
{
	private static final String insert = "INSERT INTO pedidos(idpedido,numpedido ,item,fecha, hora, estado, total, ticket,comanda, cliente,llevadelivery,oferta,fueeliminado) VALUES(?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String delete = "DELETE FROM pedidos WHERE idpedido = ?";
	private static final String readall = "SELECT * FROM pedidos";
	private static final String readItem = "SELECT item FROM pedidos";
	private static final String obtenerPedidosFecha = "SELECT * FROM pedidos where fecha =?";
	private static final String obtenerPedidosUnaFecha = "SELECT idpedido,numpedido,fueeliminado FROM pedidos where fecha =?";
	private static final String readPromo = "SELECT oferta FROM pedidos";
	private static final String actualizarEstado="Update pedidos Set estado=? where idpedido=?";
	private static final String pedidosPendientes="select * from pedidos where estado='solicitado'";
	private static final String crearTablaTemporal= "CREATE Temporary  table IF NOT EXISTS ped (idped integer,num integer, valor integer, estado char(20), delivery boolean)";
	private static final String llenarTablaTemporal= "insert into ped (select idpedido, numpedido, total, estado,llevadelivery from pedidos where fecha=? order by idpedido)";
	private static final String consultarTablaTemporal= "select * from ped";
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
			statement = conexion.getSQLConexion().prepareStatement("create table IF NOT EXISTS pedidos ( idpedido integer,numpedido integer,item char(200),fecha char(10),hora char(5), estado char(20),total integer,ticket integer,comanda integer,cliente integer,llevadelivery boolean,oferta char(200),fueeliminado boolean,primary key (idpedido),foreign key (cliente) references clientes(idcliente))");
			statement.execute();
			
			Items ite=new Items();
			String iditems= ite.iditemsPed(pedido);
			ItemsPromociones ofe=new ItemsPromociones();
			String idofertas= ofe.iditemsOferta(pedido);
			statement = conexion.getSQLConexion().prepareStatement(insert);		
				statement.setInt(1, pedido.getIdpedido());
				statement.setInt(2, pedido.getNumPedido());
				statement.setString(3, iditems);
				statement.setString(4, pedido.getFecha());
				statement.setString(5, pedido.getHora());
				statement.setString(6,pedido.get_estado());
				statement.setInt(7, pedido.getTotal());
				statement.setInt(8, pedido.get_ticket());
				statement.setInt(9, pedido.get_comanda());
				statement.setInt(10, pedido.getCliente().getIdcliente());
				statement.setBoolean(11, pedido.getLlevaDelivery());
				statement.setString(12, idofertas);
				statement.setBoolean(13, pedido.getFueeliminado());
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
				PedidoDTO aux=new PedidoDTO(resultSet.getInt("idpedido"),resultSet.getInt("numpedido"),listaItems,
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
				PedidoDTO aux=new PedidoDTO(resultSet.getInt("idpedido"),resultSet.getInt("numpedido"),listaItems,
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
		select = "SELECT idpedido,item,total,oferta,fecha FROM pedidos WHERE estado='cobrado' AND fueeliminado=FALSE AND fecha LIKE '" + dia.toString() + "-" + mes.toString() + "-" + año + "%'";
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
											,(Integer)resultSet.getObject(3),
											itemPromos.pasarDeStringAArrayItPromo(arrayIdPromo), (String)resultSet.getObject(5)) );
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
					  if(y.charAt(i)==' ' && y.charAt(i+1)==' ')
						  break;
				}
				if(resultSet.getBoolean("fueeliminado")==false)
				{	
					Clientes cli=new Clientes();
					ClienteDTO lab=cli.buscarClientePorID(resultSet.getInt("cliente"));
					Items ite=new Items();
					ArrayList<ItemDTO>listaItems= ite.pasarDeStringAArray(resultSet.getString("item"));
					ItemsPromociones ofe=new ItemsPromociones();
					ArrayList<ItemPromocionDTO> listOfertas=ofe.pasarDeStringAArrayItPromo(resultSet.getString("oferta"));
					PedidoDTO aux=new PedidoDTO(resultSet.getInt("idpedido"),resultSet.getInt("numpedido"),listaItems,
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
	
	public List<PedidoDTO> pedidosDadoFecha(String fecha)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PedidoDTO> pedidos = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(obtenerPedidosUnaFecha);
			statement.setString(1,fecha);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				
				if(resultSet.getBoolean("fueeliminado")==false)
				{	
					PedidoDTO aux=new PedidoDTO(resultSet.getInt("idpedido"),resultSet.getInt("numpedido"),null,
					"","","",0,0,0,null,false,null,false);
					
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
	
	public boolean actualizarEstadoPedido(PedidoDTO PedidodActualizar, String estado)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(actualizarEstado);
			statement.setString(1, estado);
			statement.setInt(2, PedidodActualizar.getIdpedido());
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("Actualizacion exitosa de pedido");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Actualizacion fallida de pedido");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	public ArrayList<PedidoDTO> pedidosHoy(String fecha) {
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PedidoDTO> pedidos = new ArrayList<>();
		String obtenerPedidosHoy = "SELECT numpedido,item,total,oferta,fecha,estado FROM pedidos WHERE fueeliminado=false AND fecha LIKE '"+ fecha +"%';";
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(obtenerPedidosHoy);
			//statement.setString(1,fecha);
			resultSet = statement.executeQuery();
			ItemsPromociones itemPromos = new ItemsPromociones();
			Items items = new Items();
			while(resultSet.next())
			{		
				if(resultSet.getString("estado").trim().compareTo("solicitado")==0)
				{	
					// 
					String arrayItems = String.valueOf(resultSet.getObject(2));
					if (resultSet.getObject(2) == null)
						arrayItems = "";				
					String arrayIdPromo = String.valueOf(resultSet.getObject(4));
					if (resultSet.getObject(4) == null)
						arrayIdPromo = "";
					//

					pedidos.add(new PedidoDTO( (Integer)resultSet.getObject(1),items.pasarDeStringAArray(arrayItems)
							,(Integer)resultSet.getObject(3),itemPromos.pasarDeStringAArrayItPromo(arrayIdPromo),
							(String)resultSet.getObject(5),(String)resultSet.getObject(6) ));
				}
			}
		} 
		catch (SQLException e) 
		{
			PreparedStatement statement1;
			try {
				statement1 = conexion.getSQLConexion().prepareStatement("create table IF NOT EXISTS clientes ( idcliente integer, dni integer, nombre char(20), apellido char (20), calle char (30), numeracion char(20), telefono char(20), entrecalle1 char(30), entrecalle2 char(30), codpostal char(20), email char(30), comentario char(200), fueeliminado boolean, primary key (idcliente))");
				statement1.execute();
				
				statement1 = conexion.getSQLConexion().prepareStatement("create table IF NOT EXISTS pedidos ( idpedido integer,numpedido integer,item char(200),fecha char(10),hora char(5), estado char(20),total integer,ticket integer,comanda integer,cliente integer,llevadelivery boolean,oferta char(200),fueeliminado boolean,primary key (idpedido),foreign key (cliente) references clientes(idcliente))");
				statement1.execute();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return pedidos;
	}

	// es usa para llenar la tabla pedidos pendientes
	public ArrayList<PedidoDTO> pedidoPendientesFecha(String fecha)
	{
		ArrayList<PedidoDTO> pedidos = new ArrayList<>();
		PreparedStatement statement;
		PreparedStatement statement1;
		PreparedStatement statement2;
		ResultSet resultSet; //Guarda el resultado de la query
		try 
		{
			//crea tabla temporal
			statement1= conexion.getSQLConexion().prepareStatement(crearTablaTemporal);
			statement1.execute();
			//bacia la tabla temporal
			statement1= conexion.getSQLConexion().prepareStatement("delete from ped");
			statement1.execute();
			//guarda el valor en la tabla temporal
			statement2 = conexion.getSQLConexion().prepareStatement(llenarTablaTemporal);
			statement2.setString(1, fecha);
			statement2.execute();
			//ejecuta la consulta
			statement = conexion.getSQLConexion().prepareStatement(consultarTablaTemporal);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				String palabra= resultSet.getString("estado");
				String result="";
				for (int i=0; i<palabra.length(); i++)
				{
					  if (palabra.charAt(i) != ' ' || (palabra.charAt(i)==' ' && palabra.charAt(i+1)!=' '))
					    result += palabra.charAt(i);
					  else if(palabra.charAt(i)==' ' && palabra.charAt(i+1)==' ')
						  break;
				}
				
				if(result.compareTo("rechazado")!=0 || result.compareTo("cobrado")!=0)
				{
					pedidos.add(new PedidoDTO( (Integer)resultSet.getInt("idped"),(Integer)resultSet.getInt("num"),null,
							fecha,null,result,(Integer)resultSet.getInt("valor"),null,null,
							null,resultSet.getBoolean("delivery"),null,null));
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
