package presentacion.reportes;
import java.util.ArrayList;

import dto.ItemDTO;
import dto.ItemPromocionDTO;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class PedidoDataSource implements JRDataSource
{
	//valor para la implemntacion del dataSource
	private int indiceItemActual = -1;
	private ArrayList<ItemDTO> listaItems= new ArrayList<ItemDTO>();
	private ArrayList<ItemPromocionDTO> listaItemPromo= new ArrayList<ItemPromocionDTO>();
	
	//metodos implementados del dataSource
	public Object getFieldValue(JRField jrField ) throws JRException {
		Object valor = null;  
		
		 if("cantidad".equals(jrField.getName())) 
		    { 
			 valor = listaItems.get(indiceItemActual).getCantidad().toString();
		    } 
		    else if("nombre".equals(jrField.getName())) 
		    { 
		    	valor = listaItems.get(indiceItemActual).getProducto().getNombre();
		    } 
		    else if("tipo".equals(jrField.getName())) 
		    { 
		    	valor = listaItems.get(indiceItemActual).getProducto().getTipo();
		    }
		    else if("comentario".equals(jrField.getName())) 
		    { 
		    	if(listaItems.get(indiceItemActual).getComentario()!=null)
		    		valor = listaItems.get(indiceItemActual).getComentario();
		    	else
		    		valor="-";
		    }
		    else if("precio".equals(jrField.getName())) 
		    { 
		    	valor = listaItems.get(indiceItemActual).getProducto().getPrecio().toString();
		    }
		    else if("subtotal".equals(jrField.getName())) 
		    { 
		    	Integer aux= (listaItems.get(indiceItemActual).getProducto().getPrecio() * listaItems.get(indiceItemActual).getCantidad());
		    	valor=aux.toString();
		    }
		 return valor;
	}
	
	public boolean next() throws JRException {
		return ++indiceItemActual < listaItems.size();
	}
	
	public void addItems(ItemDTO item)
	{
	    this.listaItems.add(item);
	}
	
}
