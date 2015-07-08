package presentacion.reportes;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import modelo.ProductoEstadistico;

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


public class ReporteProductoEstadistico {

	private static Document documento = new Document();
	private static ArrayList<ProductoEstadistico> producto;
	private static String tipoDeEstadistica;
	private final String fechaInicio;
	private final String fechaFin;
	
	public void generarReporteEstadistico()
	{
		try {
			final String FILE = "C:/Reporte Estadístico " + '(' + fechaInicio + '-' + fechaFin + ')' + producto.hashCode() + ".pdf";
			
			PdfWriter.getInstance(documento, new FileOutputStream(FILE));
		    Image image = Image.getInstance(ReporteProductoEstadistico.class.getResource("/prototipos/Reporte_Contable_Header.png"));
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
	
	public ReporteProductoEstadistico(String tipo, ArrayList<ProductoEstadistico> producto, String fechaInicio, String fechaFin)
	{	
		this.producto=producto;
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
		addCell(table, "Producto");
		addCell(table, "Cantidad");
		
		Iterator<ProductoEstadistico> Iterador = producto.iterator();
    	if (producto.isEmpty()==true)
    	{
    		addCell(table, "-");
        	addCell(table, "-");
    	}
    	else
    	{
			while(Iterador.hasNext())
				{			
					ProductoEstadistico elemento = Iterador.next();
					
					addCell(table, elemento.getProducto().getNombre());
					addCell(table, elemento.getCantidad().toString());
				}
    	}
        document.add(table);
}}
