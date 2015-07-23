package presentacion.reportes;

import java.io.FileOutputStream;
import java.util.Iterator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;

import dto.ItemDTO;
import dto.PedidoDTO;

public class Ticket {

	private PedidoDTO pedido;
	private static Document documento;
	private static PdfWriter writer;
	
	public void generarTicket()
	{
		try {
			final String FILE = "C:/ Pedido " + pedido.getNumPedido()+". " +pedido.getFecha() + ".pdf";
			
			writer = PdfWriter.getInstance(documento, new FileOutputStream(FILE));
		    documento.open();
		    addContentPage (documento, pedido);
		    documento.close();
		 } catch (Exception e) {
	     e.printStackTrace();
	    }
	}
	
	public Ticket(PedidoDTO pedido)
	{
		this.pedido = pedido;
		this.documento = new Document();
	}
		
	
	private static void addHeaderCell (PdfPTable table, String texto){
		
		PdfPCell c1 = new PdfPCell(new Phrase(new Paragraph(texto, FontFactory.getFont("helvetica",10,Font.BOLD, BaseColor.BLACK))));
	    c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    table.addCell(c1);
	}
	
	private static void addCell (PdfPTable table, String texto ){
		
		PdfPCell c1 = new PdfPCell(new Phrase(new Paragraph(texto, FontFactory.getFont("helvetica",9,Font.NORMAL, BaseColor.BLACK))));
	    c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    table.addCell(c1);
	}
	
	private static void addContentPage(Document document, PedidoDTO pedido) 
	throws DocumentException 
	{		
		/////TICKET DE PEDIDO
		//Agrego n° de Pedido
		documento.add(new Paragraph("N° de Pedido: " + pedido.getIdpedido().toString(), FontFactory.getFont("helvetica",12,Font.NORMAL, BaseColor.BLACK)));
		
		//Fecha-Día/Mes/Año
		documento.add(new Paragraph("Fecha: " + pedido.getFecha(), FontFactory.getFont("helvetica",12,Font.NORMAL, BaseColor.BLACK)));
		documento.add(Chunk.NEWLINE);
		
		//Agrego Datos del Cliente
		documento.add(new Paragraph("Datos del Cliente", FontFactory.getFont("helvetica",12,Font.NORMAL, BaseColor.BLACK)));
		documento.add(new Paragraph("DNI: " + pedido.getCliente().getDni().toString(), FontFactory.getFont("helvetica",10,Font.NORMAL, BaseColor.BLACK)));
		documento.add(new Paragraph("Apellido y Nombre: " + pedido.getCliente().getApellido()+ "  " +pedido.getCliente().getNombre(), FontFactory.getFont("helvetica",10,Font.NORMAL, BaseColor.BLACK)));
		documento.add(new Paragraph("Dirección: " + pedido.getCliente().getDireccion() + " " + pedido.getCliente().getNumeracion(), FontFactory.getFont("helvetica",10,Font.NORMAL, BaseColor.BLACK)));
		documento.add(new Paragraph("Teléfono: " + pedido.getCliente().getTelefono(), FontFactory.getFont("helvetica",10,Font.NORMAL, BaseColor.BLACK)));
		documento.add(Chunk.NEWLINE);
		
		//Agregar Items del Pedido a Ticket

		PdfPTable table = new PdfPTable(4);
		addHeaderCell(table, "Cantidad");
		addHeaderCell(table, "Nombre");
		addHeaderCell(table, "Precio Unitario");
		addHeaderCell(table, "Subtotal");
		table.setHeaderRows(1);
		
		if(pedido.getProductos()!=null)
		{
			Iterator<ItemDTO> Iterador = pedido.getProductos().iterator();
			while(Iterador.hasNext())
			{
				ItemDTO elemento = Iterador.next();
				addCell(table, elemento.getCantidad().toString());
				addCell(table, elemento.getProducto().getNombre());
				addCell(table, elemento.getProducto().getPrecio().toString());
				Integer totalItem = elemento.getCantidad()*elemento.getProducto().getPrecio();
				addCell(table, totalItem.toString());
			}
		}
		
		document.add(table);
		documento.add(Chunk.NEWLINE);

		documento.add(new Paragraph("Total: $" + pedido.getTotal().toString(), FontFactory.getFont("helvetica",12,Font.BOLD, BaseColor.BLACK)));
		documento.add(Chunk.NEWLINE);
		
		/////COMANDA DE PEDIDO
		//Agrego n° de Pedido
	    documento.add(new Paragraph("N° de Pedido: " + pedido.getIdpedido().toString(), FontFactory.getFont("helvetica",12,Font.NORMAL, BaseColor.BLACK)));
		
		//Fecha-Día/Mes/Año
	    documento.add(new Paragraph("Fecha: " + pedido.getFecha(), FontFactory.getFont("helvetica",12,Font.NORMAL, BaseColor.BLACK)));
	    documento.add(Chunk.NEWLINE);
		
		//Agrego Nombre y Apellido del Cliente
	    documento.add(new Paragraph("Nombre del Cliente: " + pedido.getCliente().getApellido()+ "  " +pedido.getCliente().getNombre(), FontFactory.getFont("helvetica",10,Font.NORMAL, BaseColor.BLACK)));
	    documento.add(Chunk.NEWLINE);
		
		//Agregar Items del Pedido a Comanda
	    
		PdfPTable table2 = new PdfPTable(3);
		addHeaderCell(table2, "Cantidad");
		addHeaderCell(table2, "Nombre");
		addHeaderCell(table2, "Comentario");
		table.setHeaderRows(1);
		
		if(pedido.getProductos()!=null)
		{
			Iterator<ItemDTO> Iterador2 = pedido.getProductos().iterator();
			while(Iterador2.hasNext())
			{
				ItemDTO elemento = Iterador2.next();
				addCell(table2, elemento.getCantidad().toString());
				addCell(table2, elemento.getProducto().getNombre());
				if (elemento.getComentario()!= null)
					addCell(table2, elemento.getComentario());
				else
					addCell(table2, "");
			}
		}
		document.add(table2);
	}
}



