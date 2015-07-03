package presentacion.reportes;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dto.HojaItinerarioDTO;
import dto.ItemDTO;
import dto.PedidoDTO;

public class Itinerario 
{
	
	private HojaItinerarioDTO itinerario;
	private final String fecha;
	private static Document documento = new Document();
	
	public Itinerario (HojaItinerarioDTO itinerario)
	{
		this.itinerario = itinerario;
		this.fecha=fechaActual();
	}
	
	public void generarItinerario()
	{
		try {
			String FILE = "C:/Itineratio " + this.itinerario.getIdHojaItinerario().toString() + ".pdf";
			PdfWriter.getInstance(documento, new FileOutputStream(FILE));
		    documento.open();
		    addContentPage (documento,itinerario, fecha);
		    documento.close();
		 } catch (Exception e) {
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
		
		//A�ade info de Intinerario
		documento.add(new Paragraph("Itinerario n�: " + itinerario.getIdHojaItinerario().toString(), FontFactory.getFont("arial",20,Font.BOLD, BaseColor.BLACK)));
		//A�ade info de Repartidor
		documento.add(new Paragraph("Repartidor: " + itinerario.getRepartidor().getDni().toString(), FontFactory.getFont("arial",20,Font.BOLD, BaseColor.BLACK)));
		//A�ade info de la Fecha
		documento.add(new Paragraph("Fecha: " + fecha, FontFactory.getFont("arial",11,Font.BOLD, BaseColor.BLACK)));
		
		addEmptyLine (document, 2);
		//A�ade Tabla de Pedido
		 PdfPTable table = new PdfPTable(5);
		 
		 addHeaderCell(table, "Cliente");
		 addHeaderCell(table, "Direcci�n");
		 addHeaderCell(table, "Entre Calles");
		 addHeaderCell(table, "Pedido");
		 addHeaderCell(table, "Total");
		 
		 table.setHeaderRows(1);
		
		 //A�ade elementos a la tabla
		 Iterator<PedidoDTO> Iterador = itinerario.getPedidos().iterator();
			while(Iterador.hasNext())
				{
					PedidoDTO elemento = Iterador.next();
					
					addCell(table, elemento.getCliente().getApellido()+" "+elemento.getCliente().getNombre());
					addCell(table, elemento.getCliente().getCalle()+" "+elemento.getCliente().getNumeracion());
					addCell(table, elemento.getCliente().getEntrecalle1()+" - "+elemento.getCliente().getEntrecalle2());
					String items = "";
					
					for (ItemDTO i : elemento.getItems())
					{	
						items= items + i.getProducto().getNombre() + "($" + (Integer.parseInt(i.getProducto().getPrecio().toString())*Integer.parseInt(i.getCantidad().toString()))+ ")" + "; \n" ;
					}
					
					addCell(table, items);						
					addCell(table,"$" + elemento.getTotal()+"");
					
				}
		 document.add(table);
	}
	
	public String fechaActual()
	{
		Calendar c1 = GregorianCalendar.getInstance();
		String fecha=(c1.getTime().getDate()+"-"+(c1.getTime().getMonth()+1)+"-"+(c1.getTime().getYear()+1900));
		return fecha;
	}
}
