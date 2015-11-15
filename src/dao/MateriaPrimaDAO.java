package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Categorias;
import conexion.Conexion;
import dto.CategoriaDTO;
import dto.MateriaPrimaDTO;

public class MateriaPrimaDAO 
{
	private static final String insert = "INSERT INTO matprimas(idmatprima,nombre, categoria,precio ,fueeliminado) VALUES(?,?,?,?,?)";
	private static final String delete = "DELETE FROM matprimas WHERE idmatprima = ?";
	private static final String readall = "SELECT * FROM matprimas";
	private static final String obtenerlistamatprimas="select idmatprima,i.nombre from proveedores P join matprimas I on p.matprima=i.idmatprima and p.idproveedor= ?;";
	private static final String updateCategoria = "UPDATE matprimas SET categoria=";
	private static final String updateFueEliminado= "UPDATE matprimas SET fueeliminado=";
	private String updateNombreMp = "UPDATE matprimas SET nombre='"; 
	private static final Conexion conexion = Conexion.getConexion();

	
	
	public boolean insert(MateriaPrimaDTO materiaPrima)
	{
		PreparedStatement statement;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("create table IF NOT EXISTS matprimas( idmatprima integer not null, nombre char(30), categoria integer, precio integer, fueeliminado boolean, primary key (idmatprima), foreign key (categoria) references categorias(idcategoria))");
			statement.execute();
			
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, materiaPrima.getIdMatPrima());
			statement.setString(2, materiaPrima.getNombre());
			statement.setInt(3, materiaPrima.getCategoria().getIdCategoria());
			statement.setInt(4, materiaPrima.getPrecio());
			statement.setBoolean(5, materiaPrima.getFueeliminado());

			
			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
			{
				System.out.println("inserccion exitosa de materia prima");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("insercion fallida de materia prima");
			e.printStackTrace();
		}
		finally 
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public boolean delete(MateriaPrimaDTO materiaPrima_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, materiaPrima_a_eliminar.getIdMatPrima());
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("borrado exitoso de materia prima");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("borrado fallido de materia prima");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
	}

	public List<MateriaPrimaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<MateriaPrimaDTO> materiaPrima = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				Categorias aux= new Categorias();
				CategoriaDTO categoria=aux.buscarCategoria(resultSet.getInt("categoria"));
				materiaPrima.add(new MateriaPrimaDTO(resultSet.getInt("idmatprima"), resultSet.getString("nombre"),
						categoria,resultSet.getInt("precio"),resultSet.getBoolean("fueeliminado")));
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
		return materiaPrima;
	}

	public ArrayList<MateriaPrimaDTO> obtenerListaMatPrima(Integer numProveedor)
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<MateriaPrimaDTO> matprimas = new ArrayList<>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(obtenerlistamatprimas);
			statement.setInt(1,numProveedor);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				Categorias aux= new Categorias();
				CategoriaDTO categoria=aux.buscarCategoria(resultSet.getInt("categoria"));
				matprimas.add(new MateriaPrimaDTO(resultSet.getInt("idmatprima"), resultSet.getString("nombre"),
						categoria,resultSet.getInt("precio"),resultSet.getBoolean("fueeliminado")));
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
		return matprimas;
	}

	public boolean actualizarDatos(MateriaPrimaDTO rehabilitarMatPrima) {
		
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			//longQuery + updateEmail + provActualizado.getEmail().trim() + "' where idproveedor=" +provActualizado.getId()+";";
			String query = updateCategoria + rehabilitarMatPrima.getCategoria().getIdCategoria() + " where idmatprima=" + rehabilitarMatPrima.getIdMatPrima()+";";
			query = query + updateFueEliminado + rehabilitarMatPrima.getFueeliminado() + " where idmatprima=" + rehabilitarMatPrima.getIdMatPrima()+";";
			query = query + updateNombreMp + rehabilitarMatPrima.getNombre().trim() + "' where idmatprima=" + rehabilitarMatPrima.getIdMatPrima()+";";;
			System.out.println(query);
			statement = conexion.getSQLConexion().prepareStatement(query);
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0)
			{
				System.out.println("Se rehabilito una materia prima ok");
				return true;
			}
		} 
		catch (SQLException e) 
		{
			System.out.println("Se rehabilito una materia prima>fallido");
			e.printStackTrace();
		}
		finally
		{
			conexion.cerrarConexion();
		}
		return false;
		
	}

}
