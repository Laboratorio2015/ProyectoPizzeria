package presentacion.reportes;
import java.io.FileOutputStream;
import java.util.Iterator;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;



import dto.ItemDTO;
import dto.PedidoDTO;


public class Ticket {
	
	private static PedidoDTO pedido;
	private static Document documento = new Document();
	private static PdfWriter writer;
	private static Image image;
	
	public void generarTicket()
	{
		try {
			final String FILE = "C:/Pedido " + this.pedido.getNumPedido().toString()+" "+this.pedido.getFecha() + ".pdf";
			writer = PdfWriter.getInstance(documento, new FileOutputStream(FILE));
		    image = Image.getInstance(Ticket.class.getResource("/prototipos/Diseño Factura_Ticket_2.png"));
            image.setAlignment(Element.ALIGN_BOTTOM); 	
            image.setAbsolutePosition(0, 0);
		    documento.open();
		    documento.add(image);
		    addContentPage (documento,pedido);
		    documento.close();
		 } catch (Exception e) {
	     e.printStackTrace();
	    }
	}
	
	public Ticket(PedidoDTO pedido)
	{	
		this.pedido = pedido;
	}
		
	
	private static void addContentPage(Document document, PedidoDTO pedido) 
	throws DocumentException 
	{
		Font helvetica = new Font(FontFamily.HELVETICA, 12);
	    BaseFont bf_helv = helvetica.getCalculatedBaseFont(false);
		PdfContentByte canvas = writer.getDirectContent();
		canvas.beginText();
		
		/////TICKET DE PEDIDO
		//Agrego n° de Pedido
        canvas.setFontAndSize(bf_helv, 17);
		canvas.showTextAligned(Element.ALIGN_LEFT, pedido.getIdpedido().toString(), 350, 745, 0);
		
		//Fecha-Día
		canvas.showTextAligned(Element.ALIGN_LEFT, "19", 360, 715, 0);
		//Fecha-Mes
		canvas.showTextAligned(Element.ALIGN_LEFT, "06", 435, 715, 0);
		//Fecha-Año
		canvas.showTextAligned(Element.ALIGN_LEFT, "2015", 505, 715, 0);
		
		//Agrego DNI de Cliente
		canvas.setFontAndSize(bf_helv, 12);
		canvas.showTextAligned(Element.ALIGN_LEFT, pedido.getCliente().getDni().toString(), 450, 641, 0);
		
		//Agrego Nombre y Apellido del Cliente
		canvas.showTextAligned(Element.ALIGN_LEFT, pedido.getCliente().getApellido()+ "  " +pedido.getCliente().getNombre(), 100, 641, 0);
		
		//Agrego Dirección del Cliente
		canvas.showTextAligned(Element.ALIGN_LEFT, pedido.getCliente().getDireccion() + pedido.getCliente().getNumeracion(), 100, 616, 0);
		
		//Agrego Teléfono del Cliente
		canvas.showTextAligned(Element.ALIGN_LEFT, pedido.getCliente().getTelefono(), 470, 616, 0);

		
		//Agregar Items del Pedido a Ticket
		if(pedido.getProductos()!=null)
		{
			Integer tamaño = pedido.getProductos().size();
			
			writeTicket(canvas, bf_helv, tamaño);
			while (tamaño>0)
			{
				Ticket.documento.newPage();
				Ticket.documento.add(Chunk.NEXTPAGE);
				documento.add(image);
				writeTicket(canvas, bf_helv, tamaño);
			}
		}
		canvas.setFontAndSize(bf_helv, 14);
		canvas.showTextAligned(Element.ALIGN_LEFT, "$" + pedido.getTotal().toString(), 530, 400, 0);
		
		
		/////COMANDA DE PEDIDO
		//Agrego n° de Pedido
	    canvas.setFontAndSize(bf_helv, 17);
		canvas.showTextAligned(Element.ALIGN_LEFT, pedido.getIdpedido().toString(), 350, 305, 0);
		
		//Fecha-Día
		canvas.showTextAligned(Element.ALIGN_LEFT, "19", 360, 275, 0);
		//Fecha-Mes
		canvas.showTextAligned(Element.ALIGN_LEFT, "06", 435, 275, 0);
		//Fecha-Año
		canvas.showTextAligned(Element.ALIGN_LEFT, "2015", 505, 275, 0);
		
		//Agrego Nombre y Apellido del Cliente
		canvas.setFontAndSize(bf_helv, 12);
		canvas.showTextAligned(Element.ALIGN_LEFT, pedido.getCliente().getApellido()+ "  " +pedido.getCliente().getNombre(), 100, 223, 0);
		
		//Agregar Items del Pedido a Comanda
		if(pedido.getProductos()!=null)
		{
		Iterator<ItemDTO> Iterador2 = pedido.getProductos().iterator();
		Integer y2 = 143;
		canvas.setFontAndSize(bf_helv, 12);
		while(Iterador2.hasNext())
			{
				ItemDTO elemento = Iterador2.next();
				canvas.showTextAligned(Element.ALIGN_CENTER, elemento.getCantidad().toString(), 50, y2, 0);
				canvas.showTextAligned(Element.ALIGN_LEFT, elemento.getProducto().getNombre(), 110, y2, 0);
				if (elemento.getComentario()!= null)
				canvas.showTextAligned(Element.ALIGN_LEFT, elemento.getComentario(), 370, y2, 0);
				else
					canvas.showTextAligned(Element.ALIGN_LEFT, "", 370, y2, 0);
				y2=y2-30;
			}	
		canvas.endText();
		}
	}
	
	private static void writeTicket(PdfContentByte canvas, BaseFont bf_helv, Integer size) throws DocumentException
	{
		Iterator<ItemDTO> Iterador = pedido.getProductos().iterator();
		Integer y = 543;
		canvas.setFontAndSize(bf_helv, 12);
		while(Iterador.hasNext())
			{
				ItemDTO elemento = Iterador.next();
				canvas.showTextAligned(Element.ALIGN_CENTER, elemento.getCantidad().toString(), 50, y, 0);
				canvas.showTextAligned(Element.ALIGN_LEFT, elemento.getProducto().getNombre(), 110, y, 0);
				canvas.showTextAligned(Element.ALIGN_CENTER, elemento.getProducto().getPrecio().toString(), 460, y, 0);
				Integer totalItem = elemento.getCantidad()*elemento.getProducto().getPrecio();
				canvas.showTextAligned(Element.ALIGN_CENTER, totalItem.toString(), 545, y, 0);
				y=y-30;
				size= size-1;
			}
	}
}

