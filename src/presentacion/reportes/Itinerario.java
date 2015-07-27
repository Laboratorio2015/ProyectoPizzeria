package presentacion.reportes;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dto.HojaItinerarioDTO;
import dto.ItemDTO;
import dto.ItemPromocionDTO;
import dto.PedidoDTO;
import dto.PromocionDTO;

public class Itinerario 
{
	
	private HojaItinerarioDTO itinerario;
	private final String fecha;
	private static Document documento;
	private static PdfWriter writer;
	
	public Itinerario (Document documento, HojaItinerarioDTO itinerario)
	{
		this.itinerario = itinerario;
		this.fecha=fechaActual();
		this.documento = documento;
	}
	
	public void generarItinerario()
	{
		try {
			String FILE = "C:/Itinerario " + this.itinerario.getIdHojaItinerario().toString() + ".pdf";
			writer = PdfWriter.getInstance(documento, new FileOutputStream(FILE));
		    documento.open();
		    addContentPage (documento,itinerario, fecha);
		    documento.close();
		 }
		catch (Exception e) {
	     e.printStackTrace();
	    }		
	}
		
	
	private static void addEmptyLine (Document document, int n) throws DocumentException
	{
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
	 
	private static void addContentPage(Document document, HojaItinerarioDTO itinerario, String fecha) 
	throws DocumentException {
		
		//Añade info de Intinerario
		documento.add(new Paragraph("Itinerario n°: " + itinerario.getIdHojaItinerario().toString(), FontFactory.getFont("arial",20,Font.BOLD, BaseColor.BLACK)));
		//Añade info de Repartidor
		documento.add(new Paragraph("Repartidor: " + itinerario.getRepartidor().getNombre() + itinerario.getRepartidor().getApellido(), FontFactory.getFont("arial",20,Font.BOLD, BaseColor.BLACK)));
		
		//Añade info de la Fecha
		documento.add(new Paragraph("Fecha: " + fecha, FontFactory.getFont("arial",11,Font.BOLD, BaseColor.BLACK)));
		
		addEmptyLine (document, 2);
		//Añade Tabla de Pedido
		 PdfPTable table = new PdfPTable(8);
		 
		 addHeaderCell(table, "Cliente");
		 addHeaderCell(table, "Dirección");
		 addHeaderCell(table, "Entre Calles");
		 addHeaderCell(table, "Observaciones");
		 addHeaderCell(table, "Hora de Pedido");
		 addHeaderCell(table, "N° de Pedido");
		 addHeaderCell(table, "Pedido");
		 addHeaderCell(table, "Total");
//		 addHeaderCell(table, "Detalle");
		 
		 table.setHeaderRows(1);
		
		 //Añade elementos a la tabla
		 Iterator<PedidoDTO> Iterador = itinerario.getPedidos().iterator();
			while(Iterador.hasNext())
				{
					PedidoDTO elemento = Iterador.next();
					
					addCell(table, elemento.getCliente().getApellido()+" "+elemento.getCliente().getNombre());
					addCell(table, elemento.getCliente().getCalle()+" "+elemento.getCliente().getNumeracion());
					addCell(table, elemento.getCliente().getEntrecalle1()+" - "+elemento.getCliente().getEntrecalle2());
					addCell(table, elemento.getCliente().getComentario());
					addCell(table, elemento.getHora());
					addCell(table, elemento.getIdpedido().toString());
					String itemProductos = "";
					String itemOfertas = "";
					
					for (ItemDTO i : elemento.getItems())
					{	
						itemProductos= itemProductos + i.getProducto().getNombre() + "(" + i.getCantidad() + ")" + "; \n" ;
					}
					
					for (ItemPromocionDTO p : elemento.getOfertas())
					{	
						itemOfertas= itemOfertas + p.getPromocion().getNombre() + "(" + p.getCantidad() + ")" + "; \n" ;
					}
					
					addCell(table, itemProductos+itemOfertas);						
					addCell(table,"$" + elemento.getTotal()+"");
					
				}
		 document.add(table);
		 documento.add(Chunk.NEWLINE);
		 documento.add(Chunk.NEWLINE);
		 
			PdfContentByte canvas = writer.getDirectContent();
			Font helvetica = new Font(FontFamily.HELVETICA, 12);
		    BaseFont bf_helv = helvetica.getCalculatedBaseFont(false);
			canvas.beginText();
			canvas.setFontAndSize(bf_helv, 12);
			canvas.showTextAligned(Element.ALIGN_CENTER, "____________________", 200, 40, 0);
			canvas.showTextAligned(Element.ALIGN_CENTER, "Firma Repartidor", 205, 20, 0);
			canvas.showTextAligned(Element.ALIGN_CENTER, "____________________", 400, 40, 0);
			canvas.showTextAligned(Element.ALIGN_CENTER, "Firma Cajero", 400, 20, 0);
			canvas.endText();
	}
	
	public String fechaActual()
	{
		Calendar c1 = GregorianCalendar.getInstance();
		String fecha=(c1.getTime().getDate()+"-"+(c1.getTime().getMonth()+1)+"-"+(c1.getTime().getYear()+1900));
		return fecha;
	}
}
