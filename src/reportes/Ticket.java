package reportes;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;

import dto.ClienteDTO;
import dto.ItemDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;


public class Ticket {
	
	private static String FILE = "C:/Users/leandro gabriel/Documents/reportes/Ticket De Pedido.pdf";
	static Document documento = new Document();
	
	public Ticket()
	{	
	}
		
	public void generarTicket(PedidoDTO pedido)
	{
		try 
		{
		    PdfWriter.getInstance(documento, new FileOutputStream(FILE));
		    documento.open();
		    addContentPage (documento,pedido);
		    documento.close();
		} 
		catch (Exception e) 
		{
	     e.printStackTrace();
		}
		
	}
	private static void addEmptyLine (Document document, int n) throws DocumentException{
		for (int i=0; i<n; i++)
		{
			document.add(new Paragraph ("\n"));
		}
	}
	
	private static void addHeaderCell (PdfPTable table, String texto){
		
		PdfPCell c1 = new PdfPCell(new Phrase(new Paragraph(texto, FontFactory.getFont("arial",10,Font.BOLD, BaseColor.BLACK))));
	    c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    table.addCell(c1);
	}
	
	private static void addCell (PdfPTable table, String texto ){
		
		PdfPCell c1 = new PdfPCell(new Phrase(new Paragraph(texto, FontFactory.getFont("arial",9,Font.NORMAL, BaseColor.BLACK))));
	    c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    table.addCell(c1);
	}
	 
	private static void addContentPage(Document document, PedidoDTO pedido) 
	throws DocumentException 
	{
	
		//A�ade Encabezado de Pizzer�a
		documento.add(new Paragraph("Pizzeria 'WILD'", FontFactory.getFont("arial",20,Font.BOLD, BaseColor.BLACK)));
		documento.add(new Paragraph("Av. Corrientes 555", FontFactory.getFont("arial",11,Font.NORMAL, BaseColor.DARK_GRAY)));
		documento.add(new Paragraph("San Nicol�s, Buenos Aires.", FontFactory.getFont("arial",8,Font.NORMAL, BaseColor.DARK_GRAY)));
		
		addEmptyLine (document, 2);
		
		//A�ade Datos del Cliente (& n� de pedido)
		
		documento.add(new Paragraph("N� de Pedido: "+pedido.getIdpedido(), FontFactory.getFont("arial",11,Font.NORMAL, BaseColor.BLACK)));
		documento.add(new Paragraph("DNI de Cliente: "+pedido.getCliente().getDni(), FontFactory.getFont("arial",11,Font.NORMAL, BaseColor.BLACK)));
		documento.add(new Paragraph("Nombre: "+pedido.getCliente().getApellido()+"  "+pedido.getCliente().getNombre(), FontFactory.getFont("arial",11,Font.NORMAL, BaseColor.BLACK)));
		documento.add(new Paragraph("Direcci�n: "+pedido.getCliente().getDireccion(), FontFactory.getFont("arial",11,Font.NORMAL, BaseColor.BLACK)));
		documento.add(new Paragraph("Telefono: "+pedido.getCliente().getTelefono(), FontFactory.getFont("arial",11,Font.NORMAL, BaseColor.BLACK)));
		
		addEmptyLine (document, 1);
		
		//A�ade Tabla de Pedido
		 PdfPTable table = new PdfPTable(5);
		 
		 addHeaderCell(table, "C�digo");
		 addHeaderCell(table, "Producto");
		 addHeaderCell(table, "Cantidad");
		 addHeaderCell(table, "Subtotal");
		 addHeaderCell(table, "Total");
		 table.setHeaderRows(1);
		 
		 
		Iterator<ItemDTO> Iterador = pedido.getProductos().iterator();
		while(Iterador.hasNext())
			{
				ItemDTO elemento = Iterador.next();
				addCell(table, elemento.getProducto().getNombre());
				addCell(table, elemento.getProducto().getPrecio().toString());
				addCell(table, elemento.getCantidad().toString());
				String total=elemento.getCantidad()*elemento.getProducto().getPrecio()+"";		
				addCell(table,total);
				
		 PdfPCell c1 = new PdfPCell(new Phrase(new Paragraph(pedido.getTotal().toString(), FontFactory.getFont("arial",9,Font.BOLD, BaseColor.RED))));
		 c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
		 table.addCell(c1);
			}
		document.add(table);
	
	}
}
