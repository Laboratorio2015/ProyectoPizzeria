package conexion;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	public static Conexion instancia;
	private final static String driver = "org.postgresql.Driver";
	private Connection conexion;
	
	private Conexion()
	{
		try
		{
			Class.forName(driver).newInstance();
			conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","postgres");
			System.out.println("Conexion exitosa");
		}
		catch(Exception e)
		{
			System.out.println("Conexion fallida");
		}
	}
	
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return conexion;
	}
	
	public void cerrarConexion()
	{
		instancia = null;
	}
}
