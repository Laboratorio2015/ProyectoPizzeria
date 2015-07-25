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
	
	private static void addProductoTicket (PdfPTable table, Document documento, ItemDTO elemento)
	{
		addCell(table, elemento.getCantidad().toString());
		addCell(table, elemento.getProducto().getNombre());
		addCell(table, elemento.getProducto().getPrecio().toString());
		Integer totalItem = elemento.getCantidad()*elemento.getProducto().getPrecio();
		addCell(table, totalItem.toString());	
	}
	
	private static void addProductoComanda (PdfPTable table2, Document documento, ItemDTO elemento)
	{
		addCell(table2, elemento.getCantidad().toString());
		addCell(table2, elemento.getProducto().getNombre());
		if (elemento.getComentario()!= null)
			addCell(table2, elemento.getComentario());
		else
			addCell(table2, "");
	}
	
    private static void addTicketHeader (Document document, PedidoDTO pedido) throws DocumentException
    {
		/////TICKET DE PEDIDO
		//Agrego n° de Pedido
		document.add(new Paragraph("N° de Pedido: " + pedido.getIdpedido().toString(), FontFactory.getFont("helvetica",12,Font.NORMAL, BaseColor.BLACK)));
		
		//Fecha-Día/Mes/Año
		document.add(new Paragraph("Fecha: " + pedido.getFecha(), FontFactory.getFont("helvetica",12,Font.NORMAL, BaseColor.BLACK)));
		document.add(Chunk.NEWLINE);
		
		//Agrego Datos del Cliente
		document.add(new Paragraph("Datos del Cliente", FontFactory.getFont("helvetica",12,Font.NORMAL, BaseColor.BLACK)));
		document.add(new Paragraph("DNI: " + pedido.getCliente().getDni().toString(), FontFactory.getFont("helvetica",10,Font.NORMAL, BaseColor.BLACK)));
		document.add(new Paragraph("Apellido y Nombre: " + pedido.getCliente().getApellido()+ "  " +pedido.getCliente().getNombre(), FontFactory.getFont("helvetica",10,Font.NORMAL, BaseColor.BLACK)));
		document.add(new Paragraph("Dirección: " + pedido.getCliente().getDireccion() + " " + pedido.getCliente().getNumeracion(), FontFactory.getFont("helvetica",10,Font.NORMAL, BaseColor.BLACK)));
		document.add(new Paragraph("Teléfono: " + pedido.getCliente().getTelefono(), FontFactory.getFont("helvetica",10,Font.NORMAL, BaseColor.BLACK)));
		document.add(Chunk.NEWLINE);
    }
    
    private static void addComandaHeader (Document document, PedidoDTO pedido) throws DocumentException
    {
		/////COMANDA DE PEDIDO
		//Agrego n° de Pedido
	    document.add(new Paragraph("N° de Pedido: " + pedido.getIdpedido().toString(), FontFactory.getFont("helvetica",12,Font.NORMAL, BaseColor.BLACK)));
		
		//Fecha-Día/Mes/Año
	    document.add(new Paragraph("Fecha: " + pedido.getFecha(), FontFactory.getFont("helvetica",12,Font.NORMAL, BaseColor.BLACK)));
	    document.add(Chunk.NEWLINE);
		
		//Agrego Nombre y Apellido del Cliente
	    document.add(new Paragraph("Nombre del Cliente: " + pedido.getCliente().getApellido()+ "  " +pedido.getCliente().getNombre(), FontFactory.getFont("helvetica",10,Font.NORMAL, BaseColor.BLACK)));
	    document.add(Chunk.NEWLINE);
    }
	
    private static PdfPTable newTicketTable ()
    {
		PdfPTable table = new PdfPTable(4);
		addHeaderCell(table, "Cantidad");
		addHeaderCell(table, "Nombre");
		addHeaderCell(table, "Precio Unitario");
		addHeaderCell(table, "Subtotal");
		table.setHeaderRows(1);
		return table;
    }
    
    private static PdfPTable newComandaTable ()
    {
		PdfPTable table2 = new PdfPTable(3);
		addHeaderCell(table2, "Cantidad");
		addHeaderCell(table2, "Nombre");
		addHeaderCell(table2, "Comentario");
		table2.setHeaderRows(1);
		return table2;
    }
    
    private static void addBlankRowTicket (PdfPTable table)
    {
		addHeaderCell(table, "-");
		addHeaderCell(table, "-");
		addHeaderCell(table, "-");
		addHeaderCell(table, "-");
    }
    
    private static void addBlankRowComanda (PdfPTable table)
    {
		addHeaderCell(table, "-");
		addHeaderCell(table, "-");
		addHeaderCell(table, "-");
    }
    
    
	private static void addContentPage(Document document, PedidoDTO pedido) 
	throws DocumentException 
	{				
		//Tabla de Ticket
		PdfPTable table = newTicketTable();
		
		//Tabla de Comanda
		PdfPTable table2 = newComandaTable();
		
		Integer filas = 11;
		
		if(pedido.getProductos()!=null)
		{
			addTicketHeader(document, pedido);
			Iterator<ItemDTO> Iterador = pedido.getProductos().iterator();
			while(Iterador.hasNext())
			{
				ItemDTO elemento = Iterador.next();
				if (filas>0)
				{
					addProductoTicket (table, document, elemento);
					addProductoComanda (table2, document, elemento);
					filas= filas-1;
					
					if (Iterador.hasNext()==false)
					{
						for (int i=filas; i>0; i--)
						{
							addBlankRowTicket (table);
							addBlankRowComanda (table2);
						}
						
						document.add(table);
						document.add(Chunk.NEWLINE);

						document.add(new Paragraph("Total: $" + pedido.getTotal().toString(), FontFactory.getFont("helvetica",12,Font.BOLD, BaseColor.BLACK)));
						document.add(Chunk.NEWLINE);
						
						addComandaHeader (document, pedido);
						document.add(table2);
					}
				}
				else
				{
					document.add(table);
					document.add(Chunk.NEWLINE);

					document.add(new Paragraph("Total: $" + pedido.getTotal().toString(), FontFactory.getFont("helvetica",12,Font.BOLD, BaseColor.BLACK)));
					document.add(Chunk.NEWLINE);
					
					addComandaHeader (document, pedido);
					document.add(table2);
					
					//NUEVA PÁGINA
					document.newPage();
					filas=11;
					table = newTicketTable();
					table2 = newComandaTable();
					//Agregar encabezado de TICKET
					addTicketHeader(document, pedido);
					addProductoTicket (table, document, elemento);
					addProductoComanda (table2, document, elemento);
					filas= filas-1;
				}	
			}
		}
		
		
		//Agregar Items del Pedido a Comanda
	   		
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



