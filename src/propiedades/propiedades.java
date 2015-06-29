package propiedades;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class propiedades {
  public static void main(String[] args) {

    System.out.println("Ejecutando...");
	Properties propiedades = new Properties();
    InputStream entrada = null;

    try {

        entrada = new FileInputStream("configuracion.properties");

        // cargamos el archivo de propiedades
        propiedades.load(entrada);

        // obtenemos las propiedades y las imprimimos
        System.out.println(propiedades.getProperty("ipServidor"));
        System.out.println(propiedades.getProperty("nroPuerto"));
        System.out.println(propiedades.getProperty("clave"));

    } catch (IOException ex) {
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
}