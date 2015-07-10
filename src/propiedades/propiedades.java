package propiedades;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class propiedades {
	private Properties propiedades;

	public propiedades(){
		propiedades = new Properties();
		InputStream entrada = null;
		try {
			entrada = new FileInputStream("configuracion.properties");
			propiedades.load(entrada);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (entrada != null) {
				try {
					entrada.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void actualizarValores()
	{
		
	}

	public InetAddress getDirServidor() throws UnknownHostException
	{
		return InetAddress.getByName(propiedades.getProperty("ipServidor"));
	}
	
	public InetAddress getDirServidorBase() throws UnknownHostException
	{
		return InetAddress.getByName(propiedades.getProperty("ipServidorBase"));
	}
	
	public  String getNombreBase()
	{
		return propiedades.getProperty("nombreBase");
	}
	public  String getUsuarioBase()
	{
		return propiedades.getProperty("usuarioBase");
	}
	public  String getContraseñaBase()
	{
		return propiedades.getProperty("contraseñaBase");
	}
	
	//	public static void main(String[] args) {
	//
	//		System.out.println("Ejecutando...");
	//		Properties propiedades = new Properties();
	//		InputStream entrada = null;
	//
	//		try {
	//
	//			entrada = new FileInputStream("configuracion.properties");
	//
	//			// cargamos el archivo de propiedades
	//			propiedades.load(entrada);
	//
	//			// obtenemos las propiedades y las imprimimos
	//			System.out.println(propiedades.getProperty("ipServidor"));
	//
	//			InetAddress ip = InetAddress.getByName(propiedades.getProperty("ipServidor"));
	//			byte[] bytes = ip.getAddress();
	//			for (byte b : bytes) {
	//				System.out.println(b & 0xFF);
	//			}
	//
	//			System.out.println(propiedades.getProperty("nroPuerto"));
	//			System.out.println(propiedades.getProperty("clave"));
	//
	//		} catch (IOException ex) {
	//			ex.printStackTrace();
	//		} finally {
	//			if (entrada != null) {
	//				try {
	//					entrada.close();
	//				} catch (IOException e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		}
}

