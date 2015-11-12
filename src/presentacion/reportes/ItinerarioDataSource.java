package presentacion.reportes;

import java.util.ArrayList;
import java.util.Iterator;

import dto.ItemDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ItinerarioDataSource implements JRDataSource{

	//valor para la implemntacion del dataSource
		private int indiceItemActual = -1;
		private ArrayList<PedidoDTO> listaPedidos= new ArrayList<PedidoDTO>();
		
		//metodos implementados del dataSource
		public Object getFieldValue(JRField jrField ) throws JRException {
			Object valor = null;  
			
			 if("cliente".equals(jrField.getName())) 
			    { 
				 valor = listaPedidos.get(indiceItemActual).getCliente().getApellido()+" " + listaPedidos.get(indiceItemActual).getCliente().getNombre();
			    } 
			    else if("direccion".equals(jrField.getName())) 
			    { 
			    	valor = listaPedidos.get(indiceItemActual).getCliente().getDireccion()+" " + listaPedidos.get(indiceItemActual).getCliente().getNumeracion();
			    } 
			    else if("entre Calles".equals(jrField.getName())) 
			    { 
			    	valor = listaPedidos.get(indiceItemActual).getCliente().getEntrecalle1()+" - " +listaPedidos.get(indiceItemActual).getCliente().getEntrecalle2();
			    }
			    else if("observaciones".equals(jrField.getName())) 
			    { 
			    		valor = listaPedidos.get(indiceItemActual).getCliente().getComentario();
			    }
			    else if("num_pedido".equals(jrField.getName())) 
			    { 
			    	valor = listaPedidos.get(indiceItemActual).getNumPedido().toString();
			    }
			    else if("hora_pedido".equals(jrField.getName())) 
			    { 
			    	valor = listaPedidos.get(indiceItemActual).getHora();
			    }
			    else if("detalle_pedido".equals(jrField.getName())) 
			    { 
			    	Iterator<ItemDTO> iterador= listaPedidos.get(indiceItemActual).getProductos().iterator();
			    	String listaProductos="";
			    	while (iterador.hasNext())
			    	{
			    		ItemDTO auxiliar= iterador.next();
			    		listaProductos= listaProductos + auxiliar.getProducto().getNombre() + auxiliar.getCantidad();
			    	}
			    }
			    else if("total".equals(jrField.getName())) 
			    { 
			    	valor = listaPedidos.get(indiceItemActual).getTotal();
			    }
			 
			 return valor;
		}
		
		public boolean next() throws JRException {
			return ++indiceItemActual < listaPedidos.size();
		}
		
		public void addItems(PedidoDTO pedido)
		{
		    this.listaPedidos.add(pedido);
		}
}
