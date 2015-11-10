package presentacion.reportes;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import dto.ItemDTO;
import dto.PedidoDTO;

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
	
	Iterator<ItemDTO> iterador=pedido.getItems().iterator();
	while(iterador.hasNext())
		{
			datasource.addItems(iterador.next());
		}
	 
	try
	{
		//obtiene el reporte desde el escritorio raiz
		JasperReport reporte = (JasperReport) JRLoader.loadObject(new File("C:/LaboratorioSoftwareTP/trunk/src/plantilla/ticket.jasper"));
        
		//genera el map con los datos del ticket
		Map<String, Object> parametros = new HashMap<String, Object>();
	    parametros.put("pedido_num", pedido.getNumPedido().toString());
	    parametros.put("pedido_fech", pedido.getFecha());
	    parametros.put("cliente_nombre", pedido.getCliente().getApellido() + " "+pedido.getCliente().getNombre());
	    parametros.put("cliente_direccion", pedido.getCliente().getDireccion() + " "+pedido.getCliente().getNumeracion());
	    parametros.put("cliente_telefono", pedido.getCliente().getTelefono());
	    parametros.put("cliente_entreCalles", pedido.getCliente().getEntrecalle1() +" - "+pedido.getCliente().getEntrecalle2());
	    parametros.put("cliente_comentario", pedido.getCliente().getComentario());
	    parametros.put("pedido_total", pedido.getTotal().toString());
		
        //genera el reporte con la plantilla y la coneccion a base de datos
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, datasource);

       //exporta el reporte como pdf
        JasperExportManager.exportReportToPdfFile( jasperPrint, "C:/Users/Usuario/Documents/Pizzeria Wild/Facturas/pedido"+pedido.getNumPedido()+".pdf");
    
        System.out.println("llego");
	}

	catch(Exception e)
	{
		System.out.println("Conexion fallida");
	}
	
}
}
