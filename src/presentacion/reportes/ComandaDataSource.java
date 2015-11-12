package presentacion.reportes;

import java.util.ArrayList;

import dto.ItemDTO;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ComandaDataSource  implements JRDataSource
{
	//valor para la implemntacion del dataSource
		private int indiceItemActual = -1;
		private ArrayList<ItemDTO> listaItems= new ArrayList<ItemDTO>();
		
		
		//metodos implementados del dataSource
		public Object getFieldValue(JRField jrField ) throws JRException {
			Object valor = null;  
			
			 if("cantidad".equals(jrField.getName())) 
			    { 
				 valor = listaItems.get(indiceItemActual).getCantidad().toString();
			    } 
			    else if("producto".equals(jrField.getName())) 
			    { 
			    	valor = listaItems.get(indiceItemActual).getProducto().getTipo()+" de " + listaItems.get(indiceItemActual).getProducto().getNombre();
			    } 
			    else if("observaciones".equals(jrField.getName())) 
			    { 
			    	if(listaItems.get(indiceItemActual).getComentario()!= null)
			    		valor = listaItems.get(indiceItemActual).getComentario();
			    	else
			    		valor = " - ";
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
