package presentacion.reportes;

import java.io.File;
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
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dto.OrdenPedidoMatPrimaDTO;
import dto.ProductoDTO;
import dto.PromocionDTO;
import dto.ReporteContableDTO;

public class ReporteContable 
{
	static ReporteContableDTO reporte;
	private static Document documento;
	private final String fechaInicio;
//	private final String fechaInicioAlReves;
	private final String fechaFin;
//	private final String fechaFinAlReves;
	
	public void generarReporteContable()
	{
		try {
			//verifica que exista la carpeta de exportacion, sino la crea
	        File carpeta= new File ("C:/Users/Usuario/Documents/Pizzeria Wild/Reporte Contable/");
	        if (!carpeta.exists())
	        {
	        	carpeta.mkdirs();
	        }			
			final String FILE = "C:/Users/Usuario/Documents/Pizzeria Wild/Reporte Contable/Reporte Contable " + '(' + fechaInicio + '_' + fechaFin + ')'+".pdf";// + this.reporte.hashCode() + ".pdf";			
			PdfWriter.getInstance(documento, new FileOutputStream(FILE));
		    Image image = Image.getInstance(ReporteContable.class.getResource("/prototipos/Reporte_Contable_Header.png"));
		    documento.open();
		    documento.add(image);
		    addContentPage (documento,reporte, this.fechaInicio, this.fechaFin);
		    ReporteContable.createTable1(documento);
	        documento.add(Chunk.NEWLINE);
	        ReporteContable.createTable2(documento);
	        documento.add(Chunk.NEWLINE);
	        ReporteContable.createTable4(documento);
	        documento.add(Chunk.NEWLINE);
	        ReporteContable.createTable5(documento);
	        documento.add(Chunk.NEWLINE);
	        ReporteContable.createTable3(documento);
	        documento.add(Chunk.NEWLINE);
		    documento.close();
		 } catch (Exception e) {
	     e.printStackTrace();
	    }
	}
	
	public ReporteContable(Document documento, ReporteContableDTO reporte, String fechaInicio, String fechaFin)
	{	
		this.documento = documento;
		ReporteContable.reporte = reporte;
		if(fechaInicio!=null && fechaFin!=null)
		{
			this.fechaInicio = fechaInicio;
			this.fechaFin = fechaFin;
		}
		else
		{
			this.fechaInicio = this.getFechaActual();
			this.fechaFin = this.getFechaActual();
		}
	}
	
	private static void addCell (PdfPTable table, String texto ){
		
		PdfPCell c1 = new PdfPCell(new Phrase(new Paragraph(texto, FontFactory.getFont("arial",9,Font.NORMAL, BaseColor.BLACK))));
	    c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    table.addCell(c1);}
	
	
	 ///TABLA DE PIZZAS VENDIDAS
	 public static void createTable1(Document document) throws DocumentException {
	        PdfPTable table = new PdfPTable(4);
	        PdfPCell cell;
	        cell = new PdfPCell(new Phrase("Pizzas Vendidas"));
	        cell.setColspan(4);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Producto"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Precio"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Cantidad"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Total"));
	        cell.setRowspan(1);
	        table.addCell(cell);
//	        cell = new PdfPCell(new Phrase("No. Pedido"));
//	        cell.setRowspan(1);
//	        table.addCell(cell);
//	        cell = new PdfPCell(new Phrase("Fecha"));
//	        cell.setRowspan(1);
//	        table.addCell(cell);
	        
			//Agregar Productos (PIZZAS) a la tabla
	        
	        Integer subTotal = 0;
	        
        	if (reporte.getCantPizzaVendidas().keySet().isEmpty()==true)
        	{
        		addCell(table, "-");
	        	addCell(table, "-");
	        	addCell(table, "-");
	        	addCell(table, "-");
        	}
        	else
        	{
		        for (ProductoDTO p : reporte.getCantPizzaVendidas().keySet()) 
		        {
//		        		addCell(table, (reporte.getListadoPedidos().);
			        	addCell(table, p.getNombre());
			        	addCell(table, p.getPrecio().toString());
			        	addCell(table, reporte.getCantPizzaVendidas().get(p).toString());
			        	Integer total= p.getPrecio()*reporte.getCantPizzaVendidas().get(p);
			        	subTotal = subTotal + total;
			        	addCell(table, (total.toString()));
			    }
        	}
        	document.add(table);
        	documento.add(Chunk.NEWLINE);
        	Paragraph text = new Paragraph ("Subtotal de Pizzas Vendidas: " + subTotal, FontFactory.getFont("arial",12,Font.NORMAL, BaseColor.RED));
        	text.setAlignment(Element.ALIGN_CENTER);
        	documento.add(text);
        	documento.add(Chunk.NEWLINE);
        	
	        System.out.println("Agregadas a la tabla las PIZZAS vendidas");
	    }
	 
	 ///TABLA DE EMPANADAS VENDIDAS
	 public static void createTable2(Document document) throws DocumentException {
	        PdfPTable table = new PdfPTable(4);
	        PdfPCell cell;
	        cell = new PdfPCell(new Phrase("Empanadas Vendidas"));
	        cell.setColspan(4);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Producto"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Precio"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Cantidad"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Total"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        
			//Agregar Productos (EMPANADAS) a la tabla
	        Integer subTotal = 0;
	        
        	if (reporte.getCantEmpVendidas().keySet().isEmpty()==true)
        	{
        		addCell(table, "-");
	        	addCell(table, "-");
	        	addCell(table, "-");
	        	addCell(table, "-");
        	}
        	else
        	{
	        
		        for (ProductoDTO p : reporte.getCantEmpVendidas().keySet()) 
		        {
		        	addCell(table, p.getNombre());
		        	addCell(table, p.getPrecio().toString());
		        	addCell(table, reporte.getCantEmpVendidas().get(p).toString());
		        	Integer total= p.getPrecio()*reporte.getCantEmpVendidas().get(p);
		        	subTotal = subTotal + total;
		        	addCell(table, (total.toString()));
				}
	 		}
        	document.add(table);
        	documento.add(Chunk.NEWLINE);
        	Paragraph text = new Paragraph ("Subtotal de Empanadas Vendidas: " + subTotal, FontFactory.getFont("arial",12,Font.NORMAL, BaseColor.RED));
        	text.setAlignment(Element.ALIGN_CENTER);
        	documento.add(text);
        	documento.add(Chunk.NEWLINE);
        	
	        System.out.println("Agregadas a la tabla las EMPANADAS vendidas");
	    }
	 
	///TABLA DE COMPRAS REALIZADAS
	 public static void createTable3(Document document) throws DocumentException {
	        PdfPTable table = new PdfPTable(3);
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
	        Integer subTotal = 0;
	 
        	if (reporte.getListadoCompras().size()==0)
        	{
        		addCell(table, "-");
	        	addCell(table, "-");
	        	addCell(table, "-");
	        	addCell(table, "-");
        	}
        	else
        	{
				Iterator<OrdenPedidoMatPrimaDTO> Iterador = reporte.getListadoCompras().iterator();
				while(Iterador.hasNext())
					{
						OrdenPedidoMatPrimaDTO elemento = Iterador.next();
						
						addCell(table, elemento.getFecha());
						addCell(table, elemento.getProveedor().getNombre());
						addCell(table, elemento.getCosto().toString());
						subTotal = subTotal + elemento.getCosto();
					}
			}
        	document.add(table);
        	documento.add(Chunk.NEWLINE);
        	Paragraph text = new Paragraph ("Subtotal de Compras Realizadas: " + subTotal, FontFactory.getFont("arial",12,Font.NORMAL, BaseColor.RED));
        	text.setAlignment(Element.ALIGN_CENTER);
        	documento.add(text);
        	documento.add(Chunk.NEWLINE);
        	
			System.out.println("Agregadas a la tabla las COMPRAS realizadas");
	    }
	 
	///TABLA DE OTROS PRODUCTOS VENDIDOS
	 public static void createTable4(Document document) throws DocumentException {
	        PdfPTable table = new PdfPTable(4);
	        PdfPCell cell;
	        cell = new PdfPCell(new Phrase("Otros Productos Vendidos"));
	        cell.setColspan(4);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Producto"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Precio"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Cantidad"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Total"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        
			//Agregar Productos (Otros) a la tabla
	        Integer subTotal = 0;
			
        	if (reporte.getCantOtrosProdVendidos().size()==0)
        	{
        		addCell(table, "-");
	        	addCell(table, "-");
	        	addCell(table, "-");
	        	addCell(table, "-");
        	}
        	else
        	{
		        for (ProductoDTO p : reporte.getCantOtrosProdVendidos().keySet()) 
		        {
		        	addCell(table, p.getNombre());
		        	addCell(table, p.getPrecio().toString());
		        	addCell(table, reporte.getCantOtrosProdVendidos().get(p).toString());
		        	Integer total= p.getPrecio()*reporte.getCantOtrosProdVendidos().get(p);
		        	subTotal = subTotal + total;
		        	addCell(table, (total.toString()));
		        }
			}
        	document.add(table);
        	documento.add(Chunk.NEWLINE);
        	Paragraph text = new Paragraph ("Subtotal de Otros Productos Vendidos: " + subTotal, FontFactory.getFont("arial",12,Font.NORMAL, BaseColor.RED));
        	text.setAlignment(Element.ALIGN_CENTER);
        	documento.add(text);
        	documento.add(Chunk.NEWLINE);
        	
	        System.out.println("Agregados a la table de Otros Productos vendidos");
	    }
	 
	 ///TABLA DE PROMOCIONES VENDIDAS
	 public static void createTable5(Document document) throws DocumentException {
	        PdfPTable table = new PdfPTable(4);
	        PdfPCell cell;
	        cell = new PdfPCell(new Phrase("Promociones Vendidas"));
	        cell.setColspan(4);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Promoción"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Precio"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Cantidad"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        cell = new PdfPCell(new Phrase("Total"));
	        cell.setRowspan(1);
	        table.addCell(cell);
	        
			//Agregar Productos (Otros) a la tabla
	        Integer subTotal = 0;
			
        	if (reporte.getCantPromosVendidos().keySet().isEmpty()==true)
        	{
        		addCell(table, "-");
	        	addCell(table, "-");
	        	addCell(table, "-");
	        	addCell(table, "-");
        	}
        	else
        	{
		        for (PromocionDTO p : reporte.getCantPromosVendidos().keySet()) 
		        {
		        	addCell(table, p.getNombre());
		        	addCell(table, p.getPrecio().toString());
		        	addCell(table, reporte.getCantPromosVendidos().get(p).toString());
		        	Integer total= p.getPrecio()*reporte.getCantPromosVendidos().get(p);
		        	subTotal = subTotal + total;
		        	addCell(table, (total.toString()));
		        }
			}
        	document.add(table);
        	documento.add(Chunk.NEWLINE);
        	Paragraph text = new Paragraph ("Subtotal de Promociones Vendidas: " + subTotal, FontFactory.getFont("arial",12,Font.NORMAL, BaseColor.RED));
        	text.setAlignment(Element.ALIGN_CENTER);
        	documento.add(text);
        	
	        System.out.println("Agregados a la table de Otros Productos vendidos");
	    }
	
	private static void addContentPage(Document document, ReporteContableDTO reporte, String fechaInicio, String fechaFin) 
	throws DocumentException 
	{	
		//Agrego INFO de Reporte
		
		documento.add(new Paragraph ("Reporte Contable de la Pizzería Wild " + reporte.getTotalPedidos().toString()));
		documento.add(new Paragraph ("Fecha de Inicio: " + fechaInicio));
		documento.add(new Paragraph ("Fecha de Finalización: " + fechaFin));
		documento.add(Chunk.NEWLINE);
		/////RESUMEN
		//Agrego Total de Pedidos
		documento.add(new Paragraph ("Total Pedidos: " + reporte.getTotalPedidos().toString()));
		
		//Agrego Total de Compras
		documento.add(new Paragraph ("Total Compras: " + reporte.getTotalCompras().toString()));
		
		//Agrego la Ganancia
		documento.add(new Paragraph("Ganancia: " + reporte.getGanancia().toString(), FontFactory.getFont("arial",13,Font.NORMAL, BaseColor.RED)));
		documento.add(Chunk.NEWLINE);
	}
	
	public Document getReporte ()
	{
		return ReporteContable.documento;
	}
	
	private String getFechaActual(){
		Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH)+1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        return new String (dia + "-" + mes + "-" + año);        
	}
}
