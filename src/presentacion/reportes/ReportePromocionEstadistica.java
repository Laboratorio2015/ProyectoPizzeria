package presentacion.reportes;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import modelo.ProductoEstadistico;
import modelo.PromocionEstadistica;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class ReportePromocionEstadistica {

	private static Document documento;
	private static ArrayList<PromocionEstadistica> promocion;
	private static String tipoDeEstadistica;
	private final String fechaInicio;
	private final String fechaFin;
	
	public void generarReporteEstadistico()
	{
		try {
			//verifica que exista la carpeta de exportacion, sino la crea
	        File carpeta= new File ("C:/Users/Usuario/Documents/Pizzeria Wild/Reporte Estadistico/Promociones/");
	        if (!carpeta.exists())
	        {
	        	carpeta.mkdirs();
	        }
			final String FILE = "C:/Users/Usuario/Documents/Pizzeria Wild/Reporte Estadistico/Promociones/Reporte Estadístico_Oferta " + '(' + fechaInicio + '-' + fechaFin + ')' + promocion.hashCode() + ".pdf";			
			PdfWriter.getInstance(documento, new FileOutputStream(FILE));
		    Image image = Image.getInstance(ReportePromocionEstadistica.class.getResource("/prototipos/Reporte_Contable_Header.png"));
		    documento.open();
		    documento.add(image);
		    addContentPage (documento, this.fechaInicio, this.fechaFin);
		    documento.close();
		 } catch (Exception e) {
	     e.printStackTrace();
	    }
	}
	
	private static void addCell (PdfPTable table, String texto ){
		
		PdfPCell c1 = new PdfPCell(new Phrase(new Paragraph(texto, FontFactory.getFont("arial",9,Font.NORMAL, BaseColor.BLACK))));
	    c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    table.addCell(c1);}
	
	public ReportePromocionEstadistica(Document documento, String tipo, ArrayList<PromocionEstadistica> promocion, String fechaInicio, String fechaFin)
	{	
		this.documento = documento;
		ReportePromocionEstadistica.promocion=promocion;
		tipoDeEstadistica = tipo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
		

	private static void addContentPage(Document document, String fechaInicio, String fechaFin) 
	throws DocumentException 
	{	
		//Agrego INFO de Reporte
		
		documento.add(new Paragraph ("Reporte Estadístico de la Pizzería Wild"));
		documento.add(new Paragraph ("Tipo de Reporte: " + tipoDeEstadistica));
		documento.add(new Paragraph ("Fecha de Inicio: " + fechaInicio));
		documento.add(new Paragraph ("Fecha de Finalización: " + fechaFin));
		documento.add(new Paragraph (Chunk.NEWLINE));
		
		PdfPTable table = new PdfPTable(2);
		addCell(table, "Promoción");
		addCell(table, "Cantidad");
		
		Iterator<PromocionEstadistica> Iterador = promocion.iterator();
    	if (promocion.isEmpty()==true)
    	{
    		addCell(table, "-");
        	addCell(table, "-");
    	}
		while(Iterador.hasNext())
			{
				PromocionEstadistica elemento = Iterador.next();
				
				addCell(table, elemento.getPromo().getNombre());
				addCell(table, elemento.getCantidad().toString());
			}
		
        document.add(table);

}}
