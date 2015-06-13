package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.ItemMateriasPrimas;
import modelo.Proveedores;
import conexion.Conexion;
import dto.ItemMateriaPrimaDTO;
import dto.OrdenPedidoMatPrimaDTO;
import dto.ProveedorDTO;

public class OrdenPedidoMatPrimaDAO
{
	private static final String insert = "INSERT INTO ordenpedidomatprima(idcompra, proveedor,itemmatprima,estado,fecha,costo,enviado,fueeliminado) VALUES(?,?,?,?,?)";
	private static final String delete = "DELETE FROM ordenpedidomatprima WHERE idcompra = ?";
	private static final String readall = "SELECT * FROM ordenpedidomatprima ";
	
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(OrdenPedidoMatPrimaDTO orden)
	{
		PreparedStatement statement;
		try 
		{
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

}
