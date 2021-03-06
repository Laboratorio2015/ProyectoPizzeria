package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Categorias;
import dto.CategoriaDTO;
import dto.ProveedorDTO;
import conexion.Conexion;

public class ProveedorDAO {
	private static final String insert = "INSERT INTO proveedores(idproveedor, nombre,nombrecontacto,categoria,telefono, direccion,email,comentario, fueeliminado) VALUES(?,?,?, ?, ?, ?,?,?,?)";
	private static final String delete = "DELETE FROM proveedores WHERE idproveedor = ?";
	private static final String readall = "SELECT * FROM proveedores";
	private static final Conexion conexion = Conexion.getConexion();
	private static final String updateValorEliminado = "UPDATE proveedores SET fueeliminado=";
	private static final String updateNombreContacto = "UPDATE proveedores SET nombrecontacto='";
	private String updateDireccion = "UPDATE proveedores SET direccion='";
	private String updateTelefono = "UPDATE proveedores SET telefono='";
	private String updateEmail = "UPDATE proveedores SET email='";
	private String updateComentario = "UPDATE proveedores SET comentario='";
	private String updateCategorias = "UPDATE proveedores SET categoria='";
	
	public boolean insert(ProveedorDTO proveedor)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("create table IF NOT EXISTS proveedores( idproveedor integer not null, nombre char(30), nombreContacto char(30), categoria char(300), telefono char(20), direccion char(40), email char(30), comentario char(200), fueeliminado boolean, primary key (idproveedor))");
			statement.execute();
			
			Categorias cat=new Categorias();
			String idcategorias= cat.iditemsCategorias(proveedor);
			statement = conexion.getSQLConexion().prepareStatement(insert);
				statement.setInt(1, proveedor.getId());
				statement.setString(2, proveedor.getNombre());
				statement.setString(3, proveedor.getNombrecontacto());
				statement.setString(4,idcategorias);
				statement.setString(5,proveedor.getTelefono());
				statement.setString(6, proveedor.getDireccion());
				statement.setString(7, proveedor.getEmail());
				statement.setString(8, proveedor.getComentario());
				statement.setBoolean(9, proveedor.getFueeliminado());
				statement.executeUpdate();
			System.out.println("inserccion exitosa de proveedor");
			return true;
			
		} 
		catch (SQLException e) 
		{
			System.out.println("insercion fallida de proveedor");
			e.printStackTrace();
		}
		finally 
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(ProveedorDTO proveedor_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, proveedor_a_eliminar.getId());
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("borrado exitoso de proveedor");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("borrado fallido de proveedor");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	public List<ProveedorDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				String y= resultSet.getString("categoria");
				String categoria="";
				for (int i=0; i<y.length(); i++)
				{
					  if (y.charAt(i) != ' ')
					    categoria += y.charAt(i);
				}
				String t= resultSet.getString("nombre");
				String nombre="";
				for (int i=0; i<t.length(); i++)
				{
					  if (t.charAt(i) != ' ' || (t.charAt(i)==' ' && t.charAt(i+1)!=' '))
					    nombre += t.charAt(i);
					  else if(t.charAt(i)==' ' && t.charAt(i+1)==' ')
						  break;
				}
				Categorias cat=new Categorias();
				ArrayList<CategoriaDTO>listaCategoria= cat.pasarDeStringAArray(resultSet.getString("categoria"));
				ProveedorDTO aux=new ProveedorDTO(resultSet.getInt("idproveedor"),nombre,
						resultSet.getString("nombrecontacto"), listaCategoria,
						resultSet.getString("telefono"),resultSet.getString("direccion"),
						resultSet.getString("email"),resultSet.getString("comentario"),
						resultSet.getBoolean("fueeliminado"));
				proveedores.add(aux);
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
		return proveedores;
	}
	
	public boolean cambiarEstadoEliminado(Integer idProveedor, boolean eliminado){
		//el elemento con dicho id debe cambiar su campo 'fue eliminado' a true.
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(updateValorEliminado + eliminado + " where idproveedor=" +idProveedor+";" );
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
	
	public boolean actualizarDatos(ProveedorDTO provActualizado){
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			Categorias cat=new Categorias();
			String idcategorias= cat.iditemsCategorias(provActualizado);
			
			//System.out.println(updateNombreContacto + provActualizado.getNombrecontacto().trim() + "' where idproveedor=" +provActualizado.getId()+";");
			String longQuery = updateNombreContacto + provActualizado.getNombrecontacto().trim() + "' where idproveedor=" +provActualizado.getId()+";";
			longQuery = longQuery + updateDireccion  + provActualizado.getDireccion().trim() + "' where idproveedor=" +provActualizado.getId()+";";
			longQuery = longQuery + updateTelefono + provActualizado.getTelefono().trim() + "' where idproveedor=" +provActualizado.getId()+";";
			longQuery = longQuery + updateEmail + provActualizado.getEmail().trim() + "' where idproveedor=" +provActualizado.getId()+";";
			longQuery = longQuery + updateComentario + provActualizado.getComentario().trim() + "' where idproveedor=" +provActualizado.getId()+";";
			longQuery = longQuery + updateCategorias + idcategorias + "' where idproveedor=" +provActualizado.getId()+";";

			
			statement = conexion.getSQLConexion().prepareStatement(longQuery);
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("Actualizacion de datos de proveedor exitosa");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Actualizacion de datos de proveedor fallo");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}

}
