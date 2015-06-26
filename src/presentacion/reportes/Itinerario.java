package presentacion.reportes;

import java.io.FileOutputStream;
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
	private static Document documento = new Document();
	
	public Itinerario (HojaItinerarioDTO itinerario)
	{
		this.itinerario = itinerario;
	}
	
	public void generarItinerario()
	{
		try {
			String FILE = "C:/Users/Cele/Documents/UNGS/LAB 2015/Reportes/Itinerario " + this.itinerario.getIdHojaItinerario().toString() + ".pdf";
			PdfWriter.getInstance(documento, new FileOutputStream(FILE));
		    documento.open();
		    addContentPage (documento,itinerario);
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
	 
	private static void addContentPage(Document document, HojaItinerarioDTO itinerario) 
	throws DocumentException {
		
		//Añade info de Intinerario
		documento.add(new Paragraph("Itinerario n°: " + itinerario.getIdHojaItinerario().toString(), FontFactory.getFont("arial",20,Font.BOLD, BaseColor.BLACK)));
		
		//Añade info de Repartidor
		documento.add(new Paragraph("Repartidor: " + itinerario.getRepartidor().getDni().toString(), FontFactory.getFont("arial",20,Font.BOLD, BaseColor.BLACK)));
		
		addEmptyLine (document, 2);
		//Añade Tabla de Pedido
		 PdfPTable table = new PdfPTable(5);
		 
		 addHeaderCell(table, "Cliente");
		 addHeaderCell(table, "Dirección");
		 addHeaderCell(table, "Entre Calles");
		 addHeaderCell(table, "Pedido");
		 addHeaderCell(table, "Total");
		 
		 table.setHeaderRows(1);
		
		 //Añade elementos a la tabla
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
						items= items + i.getProducto().getNombre() + "($" + i.getProducto().getPrecio().toString() + ")" + "; \n" ;
					}
					
					addCell(table, items);						
					addCell(table,"$" + elemento.getTotal()+"");
					
				}
		 document.add(table);
	}


}
