package modelo;

import java.util.ArrayList;
import java.util.Iterator;

import dto.PromocionDTO;

public class PromocionEstadistica implements Comparable<PromocionEstadistica>
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
       Integer a=new Integer(String.valueOf(this.getCantidad()));
       Integer b=new Integer(String.valueOf(o.getCantidad()));
       return a.compareTo(b);
   }
}
