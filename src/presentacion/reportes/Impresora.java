package presentacion.reportes;
/********************************************************************
*	El siguiente programa es un ejemplo bastante sencillo de 		*
*	impresión con JAVA. 											*
********************************************************************/
import javax.print.Doc;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.*;
import javax.swing.text.Document;

import java.awt.*;
import java.awt.event.*;
 
 
/********************************************************************
*	La siguiente clase llamada "Impresora", es la encargada de  	*
*	establecer la fuente con que se va a imprimir, de obtener el	*
*	trabajo de impresion, la página. En esta clase hay un método	*
*	llamado imprimir, el cual recibe una cadena y la imprime.		*
********************************************************************/
public class Impresora
{
	PrintService defaultPrintService;
	PrintRequestAttributeSet attributeSet;
 
	/********************************************************************
	*	A continuación el constructor de la clase. Aquí lo único que	*
	*	hago es tomar un objeto de impresion.							*
	********************************************************************/
	public Impresora()
	{
		defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
		attributeSet = new HashPrintRequestAttributeSet();
	}
 
	/********************************************************************
	*	A continuación el método "imprimir(String)", el encargado de 	*
	*	colocar en el objeto gráfico la cadena que se le pasa como 		*
	*	parámetro y se imprime.											*
	********************************************************************/
//    public void imprimir(Document documento)
//	{
//		//LO COLOCO EN UN try/catch PORQUE PUEDEN CANCELAR LA IMPRESION
//		try
//		{
//			DocPrintJob printJob = defaultPrintService.createPrintJob();
//			
//			try {
//                printJob.print((Doc) documento, attributeSet);
// 
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//		}catch(Exception e)
//		{
//			System.out.println("LA IMPRESION HA SIDO CANCELADA...");
//		}
//	}//FIN DEL PROCEDIMIENTO imprimir(String...)

	public void imprimir(com.itextpdf.text.Document documento) {
		//LO COLOCO EN UN try/catch PORQUE PUEDEN CANCELAR LA IMPRESION
		try
		{
			DocPrintJob printJob = defaultPrintService.createPrintJob();
			
			try {
                printJob.print((Doc) documento, attributeSet);
 
            } catch (Exception e) {
                e.printStackTrace();
            }
		}catch(Exception e)
		{
			System.out.println("LA IMPRESION HA SIDO CANCELADA...");
		}
		
	}
 
 
}//FIN DE LA CLASE Impresora
 
 
 
//A CONTINUACION LA CLASE PRINCIPAL
