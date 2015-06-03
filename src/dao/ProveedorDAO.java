package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import modelo.MatPrimas;

import dto.ItemDTO;
import dto.MateriaPrimaDTO;
import dto.PedidoDTO;
import dto.ProveedorDTO;
import conexion.Conexion;

public class ProveedorDAO {
	private static final String insert = "INSERT INTO proveedores(idproveedor, nombre,categoria,telefono, direccion,email) VALUES(?,?,?, ?, ?, ?)";
	private static final String delete = "DELETE FROM proveedores WHERE idproveedor = ?";
	private static final String readall = "SELECT * FROM proveedores";
	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(ProveedorDTO proveedor)
	{
		PreparedStatement statement;
		try 
		{
			MatPrimas matAux=new MatPrimas();
			ArrayList<Integer> idMateriasPri= matAux.idmatprimas(proveedor);
			Iterator<Integer> Iterador = idMateriasPri.iterator();
			statement = conexion.getSQLConexion().prepareStatement(insert);
			while(Iterador.hasNext())
			{
				Integer elemento = Iterador.next();
				statement.setInt(1, proveedor.getId());
				statement.setString(2, proveedor.getNombre());
				statement.setString(3, proveedor.getCategoria());
				statement.setString(4,proveedor.getTelefono());
				statement.setString(5, proveedor.getDireccion());
				statement.setString(6, proveedor.getEmail());
				statement.setInt(7, elemento);
				statement.executeUpdate();
			}
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
				
				MatPrimas matAux=new MatPrimas();
				ProveedorDTO aux=new ProveedorDTO(resultSet.getInt("idproveedor"),nombre,
						categoria,resultSet.getString("telefono"),
						resultSet.getString("email"),resultSet.getString("direccion"),(ArrayList<MateriaPrimaDTO>)matAux.obtenerMatPrimas(resultSet.getInt("idproveedor")));
				if(!ProveedorDTO.estaProveedor(proveedores, aux.getId()))
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
	

}