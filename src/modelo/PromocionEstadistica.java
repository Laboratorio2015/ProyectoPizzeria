package modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import dto.PromocionDTO;

public class PromocionEstadistica implements Comparable<PromocionEstadistica>, Comparator<PromocionEstadistica>
{
	private PromocionDTO promo;
	private Integer cantidad;
	
	public PromocionEstadistica()
	{	
		promo=new PromocionDTO();
		cantidad=0;
	}

	public PromocionDTO getPromo() {
		return promo;
	}

	public void setPromo(PromocionDTO promo) {
		this.promo = promo;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public static PromocionEstadistica buscarPromoEst(ArrayList<PromocionEstadistica> listaProductos, PromocionEstadistica oferta)
	{
		Iterator<PromocionEstadistica> lista=listaProductos.iterator();
		while (lista.hasNext()) 
		{
			PromocionEstadistica elemento= lista.next();
			if(elemento.promo.getNombre().equals(oferta.promo.getNombre()))
				return elemento;	
		}
		return null;
	}
	
	@Override
    public int compareTo(PromocionEstadistica o)
	{
       if(this.cantidad<o.cantidad)
    	   return -1;
       if(this.cantidad>o.cantidad)
    	   return 1;
       return 0;
   }
	@Override
	public int compare(PromocionEstadistica p1, PromocionEstadistica p2) {
		Integer numero=new Integer(p1.getCantidad()).compareTo(new Integer(p2.getCantidad()));
		return numero;
	}
}
