package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.ItemMateriasPrimas;
import modelo.Items;
import modelo.Promociones;
import modelo.Proveedores;
import conexion.Conexion;
import dto.ItemMateriaPrimaDTO;
import dto.OrdenPedidoMatPrimaDTO;
import dto.PedidoDTO;
import dto.ProveedorDTO;

public class OrdenPedidoMatPrimaDAO
{
	private static final String insert = "INSERT INTO ordenpedidomatprima(idcompra, proveedor,itemmatprima,estado,fecha,costo,enviado,fueeliminado) VALUES(?,?,?,?,?,?,?,?)";
	private static final String delete = "DELETE FROM ordenpedidomatprima WHERE idcompra = ?";
	private static final String readall = "SELECT * FROM ordenpedidomatprima ";
	private String updateEstado = "UPDATE ordenpedidomatprima SET estado='";
	private String updateEnviado = "UPDATE ordenpedidomatprima SET enviado=";
	private String updateFecha = "UPDATE ordenpedidomatprima SET fecha='";
	private String select;


	
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(OrdenPedidoMatPrimaDTO orden)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("create table IF NOT EXISTS ordenpedidomatprima( idcompra integer, proveedor integer, itemmatprima char(300), estado char(30), fecha char(30), costo integer, enviado boolean, fueeliminado boolean, primary key (idcompra), foreign key (proveedor) references proveedores(idproveedor))");
			statement.execute();
			
			ItemMateriasPrimas ite=new ItemMateriasPrimas();
			String idordenes= ite.iditemMatPrima(orden);
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, orden.getIdCompra());
			statement.setInt(2, orden.getProveedor().getIdproveedor());
			statement.setString(3, idordenes);
			statement.setString(4, orden.getEstado());
			statement.setString(5, orden.getFecha());
			statement.setInt(6, orden.getCosto());
			statement.setBoolean(7,orden.getEnviado());
			statement.setBoolean(8,orden.getFueeliminado());
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
			{
				System.out.println("inserccion exitosa de orden de materia prima");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("insercion fallida de orden de materia prima");
			e.printStackTrace();
		}
		finally 
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(OrdenPedidoMatPrimaDTO orden_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, orden_a_eliminar.getIdCompra());
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("borrado exitoso de orden de materia prima");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("borrado fallido de orden de materia prima");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	public List<OrdenPedidoMatPrimaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<OrdenPedidoMatPrimaDTO> ofertas = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				//obtiene lista de items de materias primas
				ItemMateriasPrimas a=new ItemMateriasPrimas();
				ArrayList<ItemMateriaPrimaDTO>listaItems= a.pasarDeStringAArray(resultSet.getString("itemmatprima"));
				//obtiene proveedores
				Proveedores prov=new Proveedores();
				ProveedorDTO proveedor=prov.buscarProveedorPorId(resultSet.getInt("proveedor"));
				ofertas.add(new OrdenPedidoMatPrimaDTO(resultSet.getInt("idcompra"),proveedor,
						listaItems,resultSet.getString("estado"),resultSet.getString("fecha"),
						resultSet.getInt("costo"),resultSet.getBoolean("enviado"),
						resultSet.getBoolean("fueeliminado")));

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

	public boolean actualizarDatos(OrdenPedidoMatPrimaDTO ordenSeleccionada) {
		//el elemento con dicho id debe cambiar su campo 'fue eliminado' a true.
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(updateEnviado + ordenSeleccionada.getEnviado() + " where idcompra=" +ordenSeleccionada.getIdCompra()+";"+
		updateEstado + ordenSeleccionada.getEstado() + "' where idcompra=" +ordenSeleccionada.getIdCompra()+";"
		+ updateFecha + ordenSeleccionada.getFecha() + "' where idcompra=" +ordenSeleccionada.getIdCompra()+";");
			//	private static final String updateValorEliminado = "UPDATE categorias SET fueeliminado=";
			//"UPDATE categorias SET fueeliminado=true where idcategoria='Federicolopez';"
			//statement.setInt(1, categoria_a_eliminar.getIdCategoria());
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("Actualizacion de categoria exitosa");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Actualizacion de categoria fallo");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	public ArrayList<OrdenPedidoMatPrimaDTO> reporteEnRango(String dia,String mes, String año) {
		select = "SELECT idcompra,proveedor,fecha,costo " +
		"FROM ordenpedidomatprima WHERE estado='pagado' AND fueeliminado=FALSE " +
		"AND fecha LIKE '" + dia.toString() + "-" + mes.toString() + "-" + año + "%'";
		System.out.println(select);
		PreparedStatement statement;
		ResultSet resultSet; 
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(select);
			resultSet = statement.executeQuery();			
			ArrayList<OrdenPedidoMatPrimaDTO> resultado = new ArrayList<OrdenPedidoMatPrimaDTO>();
			Proveedores proveedores = new Proveedores();
			while (resultSet.next())
			{
				resultado.add(new OrdenPedidoMatPrimaDTO( (Integer)resultSet.getObject(1), 
						proveedores.buscarProveedorPorId((Integer)resultSet.getObject(2)),
						(String)resultSet.getObject(3), (Integer)resultSet.getObject(4)));
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
}
