package presentacion.reportes;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import dto.HojaItinerarioDTO;
import dto.ItemDTO;
import dto.ItemPromocionDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;


public class GenerarPDF {

	
	
	//metodo para generar los tickets recibe como parametro un pedido
	public static void GenerarTicket(PedidoDTO pedido)
	{
		
	PedidoDataSource datasource = new PedidoDataSource();
	ComandaDataSource comanda= new ComandaDataSource();
	
	//recorre todos los items para llenar el ticket
	Iterator<ItemDTO> iterador=pedido.getItems().iterator();
	while(iterador.hasNext())
		{
			datasource.addItems(iterador.next());
		}
	//recorre todos las promociones para llenar el ticket
	ItemDTO aux_promo;
	Iterator<ItemPromocionDTO> iterator2= pedido.getOfertas().iterator();
	while(iterator2.hasNext())
	{
		ItemPromocionDTO auxiliar= iterator2.next();
		String nombre= auxiliar.getPromocion().getNombre();
		Integer cantidad= auxiliar.getCantidad();
		Integer precio= auxiliar.getPromocion().getPrecio();
		String tipo =" - ";
		ProductoDTO aux= new ProductoDTO(1, nombre, precio, tipo, false);
		String comentario= auxiliar.getComentario();
		aux_promo=new ItemDTO(1, aux, cantidad, comentario, false);
		datasource.addItems(aux_promo);
	}
	
	//recorre todos los items para llenar la comanda
		Iterator<ItemDTO> iterador3=pedido.getItems().iterator();
		while(iterador3.hasNext())
			{
				ItemDTO auxiliar = iterador3.next();
				if(!auxiliar.getProducto().getTipo().equals("otros"))
					comanda.addItems(auxiliar);
			}
		//recorre todos las promociones para llenar la comanda
		Iterator<ItemPromocionDTO> iterator4= pedido.getOfertas().iterator();
		ItemDTO productoActual;
		while(iterator4.hasNext())
		{
			ItemPromocionDTO auxiliar= iterator4.next();
			Iterator<ItemDTO> productos=auxiliar.getProductosOfertados().iterator();
			while(productos.hasNext())
			{
				ItemDTO nuevo= productos.next();
				productoActual= new ItemDTO(1, nuevo.getProducto(), nuevo.getCantidad()*auxiliar.getCantidad(), auxiliar.getComentario(), false);
				comanda.addItems(productoActual);
			}
		}
	try
	{
		//obtiene el reporte desde el directorio raiz del ticket
		JasperReport ticket = (JasperReport) JRLoader.loadObject(new File("C:/LaboratorioSoftwareTP/trunk/src/plantilla/ticketA6.jasper"));
		//obtiene el reporte desde el directorio raiz de a comanda
		JasperReport comanda1 = (JasperReport) JRLoader.loadObject(new File("C:/LaboratorioSoftwareTP/trunk/src/plantilla/comandaA5.jasper"));
		        
		//genera el map con los datos del ticket
		Map<String, Object> parametros = new HashMap<String, Object>();
	    parametros.put("pedido_num", pedido.getNumPedido().toString());
	    parametros.put("pedido_fech", pedido.getFecha());
	    parametros.put("cliente_nombre", pedido.getCliente().getApellido() + " "+pedido.getCliente().getNombre());
	    parametros.put("cliente_direccion", pedido.getCliente().getDireccion() + " "+pedido.getCliente().getNumeracion());
	    parametros.put("cliente_ telefono", pedido.getCliente().getTelefono());
	    parametros.put("cliente_entreCalles", quitarEspacio(pedido.getCliente().getEntrecalle1()) +" - "+quitarEspacio(pedido.getCliente().getEntrecalle2()));
	    parametros.put("cliente_comentario", pedido.getCliente().getComentario());
	    parametros.put("pedido_total", pedido.getTotal().toString());
		
        //genera el reporte con la plantilla y la coneccion 
        JasperPrint jasperPrint = JasperFillManager.fillReport(ticket, parametros, datasource);
        //genera eñ reporte con la plantilla y la coneccion 
        JasperPrint jasper = JasperFillManager.fillReport(comanda1, parametros, comanda); 

      //verifica que exista la carpeta de exportacion, sino la crea
        File carpeta= new File ("C:/Users/Usuario/Documents/Pizzeria Wild/Pedidos/"+pedido.getFecha()+"/");
        if (!carpeta.exists())
        {
        	carpeta.mkdirs();
        }
        //verifica que exista la carpeta de exportacion, sino la crea
        File carpeta1= new File ("C:/Users/Usuario/Documents/Pizzeria Wild/Comandas/"+pedido.getFecha()+"/");
        if (!carpeta1.exists())
        {
        	carpeta1.mkdirs();
        }
        
       //exporta el reporte como pdf 
        JasperExportManager.exportReportToPdfFile( jasperPrint, "C:/Users/Usuario/Documents/Pizzeria Wild/Pedidos/"+pedido.getFecha()+"/pedido"+pedido.getNumPedido()+" - "+pedido.getFecha()+".pdf");
        JasperExportManager.exportReportToPdfFile( jasper, "C:/Users/Usuario/Documents/Pizzeria Wild/Comandas/"+pedido.getFecha()+"/comanda"+pedido.getNumPedido()+" - "+pedido.getFecha()+".pdf");
        System.out.println("termino exitosamente");
	}

	catch(Exception e)
	{
		System.out.println("Conexion fallida");
	}
	
}

	public static void GenerarItinerario(HojaItinerarioDTO hojaItinerario) 
	{
		ItinerarioDataSource datasource = new ItinerarioDataSource();

		
		//recorre todos los items para llenar el ticket
		Iterator<PedidoDTO> iterador=hojaItinerario.getPedidos().iterator();
		while(iterador.hasNext())
			{
				datasource.addItems(iterador.next());
			}
		try
		{
			//obtiene el reporte desde el directorio raiz del itinerario
			JasperReport itinerario = (JasperReport) JRLoader.loadObject(new File("C:/LaboratorioSoftwareTP/trunk/src/plantilla/itinerarioA5.jasper"));
					        
			//genera el map con los datos del itinerario
			Map<String, Object> parametros = new HashMap<String, Object>();
		    parametros.put("fecha", hojaItinerario.getFecha());
		    parametros.put("repartidor_dni", hojaItinerario.getRepartidor().getDni().toString());
		    parametros.put("repartidor_nombre", hojaItinerario.getRepartidor().getApellido() + " "+hojaItinerario.getRepartidor().getNombre());
		    parametros.put("repartidor_vehiculo ", hojaItinerario.getRepartidor().getVehiculo());
		    parametros.put("repartidor_patente", hojaItinerario.getRepartidor().getPatente().toString());
		    
	        //genera el reporte con la plantilla y la coneccion 
	        JasperPrint jasperPrint = JasperFillManager.fillReport(itinerario, parametros, datasource);
	  

	      //verifica que exista la carpeta de exportacion, sino la crea
	        File carpeta= new File ("C:/Users/Usuario/Documents/Pizzeria Wild/Itinerarios/"+ hojaItinerario.getFecha()+"/");
	        if (!carpeta.exists())
	        {
	        	carpeta.mkdirs();
	        }
	       
	       //exporta el itinerario como pdf 
	        JasperExportManager.exportReportToPdfFile( jasperPrint, "C:/Users/Usuario/Documents/Pizzeria Wild/Itinerarios/"+hojaItinerario.getFecha()+"/Itinerario "+hojaItinerario.getNumItinerario()+" - "+hojaItinerario.getFecha()+".pdf");
	       
	        System.out.println("se genero exitosamente el itinerario");
		}

		catch(Exception e)
		{
			System.out.println("ERROR!!!   no se genero el itinerario");
		}

	}
	
	//quita los espacios de mas en los Strings largos
	public static String quitarEspacio(String palabra)
	{
		String result="";
		for (int i=0; i<palabra.length(); i++)
		{
			  if (palabra.charAt(i) != ' ' || (palabra.charAt(i)==' ' && palabra.charAt(i+1)!=' '))
			    result += palabra.charAt(i);
			  else if(palabra.charAt(i)==' ' && palabra.charAt(i+1)==' ')
				  break;
		}
		return result;
	}
}
