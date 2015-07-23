package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import propiedades.propiedades;

public class Conexion {
	public static Conexion instancia;
	private final static String driver = "org.postgresql.Driver";
	private Connection conexion;
	
	private Conexion()
	{
		try
		{
			propiedades aux=new propiedades();
			Class.forName(driver).newInstance();
			conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Sistema de Pizzeria", "postgres","postgres");
			//String Direccion="jdbc:postgresql:/"+aux.getDirServidorBase()+"/"+aux.getNombreBase();
			//conexion = DriverManager.getConnection(Direccion,aux.getUsuarioBase(),aux.getContraseñaBase());
			JOptionPane.showMessageDialog(null, "Coneccion Exitosa a la Base de Datos.", "Informacion",JOptionPane.WARNING_MESSAGE);
			System.out.println("Conexion exitosa");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "No se pudo concectar a la Base, verifique los datos y vuelva a iniciar.", "Informacion",JOptionPane.WARNING_MESSAGE);
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
