package presentacion.reportes;

import java.util.ArrayList;
import java.util.List;

import dto.ItemDTO;
import dto.ProductoDTO;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteTicket 
{
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;
	
	public ReporteTicket (ArrayList<ItemDTO> arrayList)
	{
		try 
		{
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile( "reportes\\ReporteTicket.jasper" );
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, null, 
					new JRBeanCollectionDataSource(arrayList));
		}
		catch( JRException ex ) 
		{
			ex.printStackTrace();
		}
    }       
    
    public void mostrar()
	{
		this.reporteViewer = new JasperViewer(this.reporteLleno);
		this.reporteViewer.setVisible(true);
	}
	}

