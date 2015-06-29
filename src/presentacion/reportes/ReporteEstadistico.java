package presentacion.reportes;

import java.io.FileOutputStream;
import java.util.Iterator;

import com.itextpdf.text.BaseColor;
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

import dto.OrdenPedidoMatPrimaDTO;
import dto.ProductoDTO;
import dto.ReporteContableDTO;

public class ReporteEstadistico {

	private static ReporteContableDTO reporte;
	private static Document documento = new Document();
	private static PdfWriter writer;
	private final String fechaInicio;
	private final String fechaFin;
	//private Impresora impresora;
	
	public void generarReporteContable()
	{
		try {
			final String FILE = "D:/Reporte Contable.pdf";			
			writer = PdfWriter.getInstance(documento, new FileOutputStream(FILE));
		    Image image = Image.getInstance(ReporteContable.class.getResource("/prototipos/Reporte_Contable_Header.png"));
		    documento.open();
		    documento.add(image);
		    addContentPage (documento,reporte, this.fechaInicio, this.fechaFin);
		    PdfPTable table = createTable1();
	        documento.add(table);
	        table = createTable2();
	        documento.add(table);
	        table = createTable3();
	        documento.add(table);
		    documento.close();
		 } catch (Exception e) {
	     e.printStackTrace();
	    }
	}
	
	private static void addCell (PdfPTable table, String texto ){
		
		PdfPCell c1 = new PdfPCell(new Phrase(new Paragraph(texto, FontFactory.getFont("arial",9,Font.NORMAL, BaseColor.BLACK))));
	    c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    table.addCell(c1);}
	
	 public static PdfPTable createTable1() throws DocumentException {
	        PdfPTable table = new PdfPTable(2);
	        table.setWidthPercentage(288 / 5.23f);
	        table.setWidths(new int[]{2, 2});
	        PdfPCell cell;
	        cell = new PdfPCell(new Phrase("Pizzas Vendidas"));
	        cell.setColspan(2);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Producto"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Cantidad"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        
			//Agregar Productos (PIZZAS) a la tabla
			
	        for (ProductoDTO p : reporte.getCantPizzaVendidas().keySet()) 
	        {
	        	addCell(table, p.getNombre());
	        	addCell(table, reporte.getCantPizzaVendidas().get(p).toString());
			}
	        System.out.println("Agregadas a la tabla las PIZZAS vendidas");
	        return table;
	    }
	 
	 public static PdfPTable createTable2() throws DocumentException {
	        PdfPTable table = new PdfPTable(2);
	        table.setWidthPercentage(288 / 5.23f);
	        table.setWidths(new int[]{2, 2});
	        PdfPCell cell;
	        cell = new PdfPCell(new Phrase("Empanadas Vendidas"));
	        cell.setColspan(2);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Producto"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Cantidad"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        
			//Agregar Productos (EMPANADAS) a la tabla
			
	        for (ProductoDTO p : reporte.getCantEmpVendidas().keySet()) 
	        {
	        	addCell(table, p.getNombre());
	        	addCell(table, reporte.getCantEmpVendidas().get(p).toString());
			}
	        System.out.println("Agregadas a la tabla las EMPANADAS vendidas");
	        return table;
	    }
	 
	 public static PdfPTable createTable3() throws DocumentException {
	        PdfPTable table = new PdfPTable(3);
	        table.setWidthPercentage(288 / 5.23f);
	        table.setWidths(new int[]{2, 2});
	        PdfPCell cell;
	        cell = new PdfPCell(new Phrase("Compras Realizadas"));
	        cell.setColspan(3);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Fecha"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Proveedor"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Costo"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        
			//Agregar Compras Hechas a la tabla
			
			Iterator<OrdenPedidoMatPrimaDTO> Iterador = reporte.getListadoCompras().iterator();
			while(Iterador.hasNext())
				{
					OrdenPedidoMatPrimaDTO elemento = Iterador.next();
					
					addCell(table, elemento.getFecha());
					addCell(table, elemento.getProveedor().getNombre());
					addCell(table, elemento.getCosto().toString());
				}
			System.out.println("Agregadas a la tabla las COMPRAS realizadas");
	        return table;
	    }

	public ReporteEstadistico(ReporteContableDTO reporte, String fechaInicio, String fechaFin)
	{	
		ReporteContable.reporte = reporte;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		//impresora = new Impresora();
	}
		
	
	private static void addContentPage(Document document, ReporteContableDTO reporte, String fechaInicio, String fechaFin) 
	throws DocumentException 
	{	
		//Agrego INFO de Reporte
		
		documento.add(new Paragraph ("Reporte Contable de la Pizzería Wild " + reporte.getTotalPedidos().toString()));
		documento.add(new Paragraph ("Fecha de Inicio: " + fechaInicio));
		documento.add(new Paragraph ("Fecha de Finalización: " + fechaFin));
		
		/////RESUMEN
		//Agrego Total de Pedidos
		documento.add(new Paragraph ("Total Pedidos: " + reporte.getTotalPedidos().toString()));
		
		//Agrego Total de Compras
		documento.add(new Paragraph ("Total Compras: " + reporte.getTotalCompras().toString()));
		
		//Agrego la Ganancia
		documento.add(new Paragraph ("Ganancia: " + reporte.getGanancia().toString()));
	}
	
	public Document getReporte ()
	{
		return this.documento;
	}
}


