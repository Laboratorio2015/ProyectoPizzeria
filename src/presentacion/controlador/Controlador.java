package presentacion.controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.Component;
import java.awt.Point;
import java.awt.dnd.Autoscroll;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.log.SysoCounter;
import com.mxrck.autocompleter.TextAutoCompleter;
import Cocina.PadreMonitor;
import modelo.Categorias;
import modelo.Clientes;
import modelo.ItemMateriasPrimas;
import modelo.Items;
import modelo.ItemsPromociones;
import modelo.Itinerarios;
import modelo.MatPrimas;
import modelo.PantallaCargando;
import modelo.ProductoEstadistico;
import modelo.PromocionEstadistica;
import modelo.Promociones;
import modelo.OrdenesMateriaPrimas;
import modelo.Pedidos;
import modelo.Productos;
import modelo.Proveedores;
import modelo.Repartidores;
import dto.CategoriaDTO;
import dto.ClienteDTO;
import dto.ItemDTO;
import dto.ItemMateriaPrimaDTO;
import dto.ItemPromocionDTO;
import dto.MateriaPrimaDTO;
import dto.PromocionDTO;
import dto.OrdenPedidoMatPrimaDTO;
import dto.PedidoDTO;
import dto.ProductoDTO;
import dto.ProveedorDTO;
import dto.RepartidorDTO;
import dto.ReporteContableDTO;
import presentacion.reportes.GenerarPDF;
import presentacion.reportes.ReporteContable;
import presentacion.reportes.ReporteProductoEstadistico;
import presentacion.reportes.ReportePromocionEstadistica;
import presentacion.reportes.solicitudDeMateriaPrima;
import presentacion.vista.VentanaPrincipal;
import presentacion.vista.buscadorProveedor;
import presentacion.vista.calendario;
import presentacion.vista.calendarioSelectFecha;
import presentacion.vista.clienteBajaModificacion;
import presentacion.vista.consultoEstadistica;
import presentacion.vista.consultorContabilidad;
import presentacion.vista.establecerConeccion;
import presentacion.vista.gestionCategoria;
import presentacion.vista.matPrimaAlta;
import presentacion.vista.matPrimaBajaModificacion;
import presentacion.vista.opcionesDeConfiguracion;
import presentacion.vista.ordenDePedido;
import presentacion.vista.ordenarMatPrima;
import presentacion.vista.pedidoMenu;
import presentacion.vista.pedidosPendientes;
import presentacion.vista.productoAlta;
import presentacion.vista.productoBajaModificacion;
import presentacion.vista.promocionAlta;
import presentacion.vista.promocionBajaModificacion;
import presentacion.vista.proveedorAlta;
import presentacion.vista.proveedorBajaModificacion;
import presentacion.vista.registrarPagoOrdenMatPrima;
import presentacion.vista.registroDeCliente;
import presentacion.vista.repartidorAlta;
import presentacion.vista.repartidorBajaModificacion;
import presentacion.vista.seleccionDeCliente;
import presentacion.vista.selectorMatPrima;
import presentacion.vista.selectorOpcionesOrdenMatPrima;
import presentacion.vista.gestionarOrdenesMatPrima;
import presentacion.vista.selectMenuReportes;
import propiedades.propiedades;

public class Controlador implements ActionListener
{
	private Socket socket;
	private ObjectOutputStream objectOutputStream;
	private ObjectInputStream objectInputStream;
	//ventanas
	private VentanaPrincipal ventana;
	private ordenDePedido ventanaPedido;
	private pedidosPendientes ventanaPedPendiente;
	private seleccionDeCliente ventanaCliente;
	private ordenarMatPrima ventanaOrdenMatPrima;
	private gestionarOrdenesMatPrima gestorOrdenesMateriasPrimas;
	private establecerConeccion conectar;
	//moficiaciones
	private calendarioSelectFecha selectorFecha;
	private selectMenuReportes ventanaMenuReportes;
	private consultorContabilidad ventanaReportesContables;
	private consultoEstadistica ventanaReportesEstadistica;
	private selectorOpcionesOrdenMatPrima ventanaSelectorOpcOrdenMatPrima;
	private buscadorProveedor ventanaSeleccionProveedor;
	private opcionesDeConfiguracion ventanaConfiguraciones;
	private productoAlta ventanaAgregarProducto;
	private productoBajaModificacion ventanaEditarProducto;
	private proveedorAlta ventanaAgregarProveedor;
	private proveedorBajaModificacion ventanaEditarProveedor;
	private repartidorAlta ventanaAgregarRepartidor;
	private repartidorBajaModificacion ventanaEditarRepartidor;
	private matPrimaAlta ventanaAgregarMatPrima;
	private matPrimaBajaModificacion ventanaEditarMatPrima;
	private promocionAlta ventanaAgregarPromocion;
	private promocionBajaModificacion ventanaEditarPromocion;
	private gestionCategoria ventanaGestionCategoria;
	private calendario ventanaCalendario;
	private PadreMonitor monitorCocina;
	private pedidoMenu ventanamenu;
	private registroDeCliente ventanaRegistrarCliente;
	private clienteBajaModificacion ventanaModificacionCliente;
	private selectorMatPrima ventanaSeleccionMatPrima;
	//private boolean seleccionCte = false;


	//modelo
	private Productos producto;
	private Proveedores proveedor;
	private Pedidos pedido;
	private Clientes cliente;
	private Items item;
	private ItemsPromociones itemPromocion;
	private Promociones promocion;
	private Repartidores repartidor;
	private Promociones oferta;
	private Categorias categoria;
	private OrdenesMateriaPrimas ordenesMatPrimas;
	private MatPrimas materiasPrimas;
	private ItemMateriasPrimas itemsMateriaPrima;
	private Itinerarios itinerario;
	private registrarPagoOrdenMatPrima ventanaRegistrarPagoOrdenMatPrima;
	private ReporteContableDTO reporteContable;
	private ProductoEstadistico prodEstadistico;
	private OutputStream sos;
	private propiedades propConecciones;


	//ESTE CONSTRUCTOR RECIBE DOS PARAMETROS MAS QUE EL OTRO> ORDENES DE PEDIDO Y MATERIAS PRIMAS
	public Controlador(VentanaPrincipal ventana, Pedidos pedido, Clientes cliente,Productos producto, Items item, Proveedores proveedor,
			Repartidores repartidor,Promociones oferta, Categorias categoria,OrdenesMateriaPrimas ordenesMatPrimas, 
			MatPrimas matPrimas, ItemMateriasPrimas itemsMatPrima, Itinerarios itinerario, Promociones promocion,
			ItemsPromociones itemPromocion, ProductoEstadistico prodEstadistico) 
	{
		this.ventana=ventana;
		this.pedido=pedido;
		this.cliente=cliente;
		this.item=item;
		this.producto=producto;
		this.proveedor=proveedor;
		this.repartidor=repartidor;
		this.oferta=oferta;
		this.itemPromocion=itemPromocion;
		this.categoria=categoria;
		this.promocion=promocion;
		this.itinerario=itinerario;
		this.ordenesMatPrimas = ordenesMatPrimas;
		this.materiasPrimas = matPrimas;
		this.itemsMateriaPrima = itemsMatPrima;
		this.prodEstadistico=prodEstadistico;
		this.ventana.getBtnIngresarPedido().addActionListener(this);
		this.ventana.getBtnPedidosPendientes().addActionListener(this);
		this.ventana.getBtnConfiguraciones().addActionListener(this);
		this.ventana.getBtnPedMatPrima().addActionListener(this);
		this.ventana.getBtnReportes().addActionListener(this);
		//inicio con configuracion de las direcciones del monitor y la base de datos
		this.propConecciones= new propiedades();
	}


	public void inicializar() throws IOException
	{
		conectar= new establecerConeccion();
		this.conectar.getBtnAceptar().addActionListener(this);
		this.conectar.getBtnDefault().addActionListener(this);
		conectar.setVisible(true);
	}


	@SuppressWarnings({ "serial", "deprecation" })
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if(e.getSource() == this.ventana.getBtnIngresarPedido())
		{
			ventanaPedido=new ordenDePedido(ventana,this);
			this.ventanaPedido.getBtnOrdenar().addActionListener(this);
			this.ventanaPedido.getBtnVerEmpanadas().addActionListener(this);
			this.ventanaPedido.getBtnVerOtros().addActionListener(this);
			this.ventanaPedido.getBtnVerPizzas().addActionListener(this);
			this.ventanaPedido.getBtnVerPromociones().addActionListener(this);
			this.ventanaPedido.setVisible(true);
		}
		else if(e.getSource() == this.ventana.getBtnPedidosPendientes())
		{
			ventanaPedPendiente=new pedidosPendientes(ventana, this);
			llenarTablaPedPendientes();
			this.ventanaPedPendiente.setVisible(true);
		}
		//acciones para establecer la coneccion al monitor de cocina
		else if(this.conectar!= null && e.getSource()==this.conectar.getBtnAceptar())
		{
			
			try {
				String host= conectar.getTfIpMonitor().getText().toString();
				String puerto=conectar.getTfPuertoMonitor().getText().toString();
				this.conectar.dispose();
				boolean envio=enviarPedidosMonitor(host, puerto);
				if(envio)
					this.ventana.show();
				else
					JOptionPane.showMessageDialog(null, "Problemas al conectarse a el Monitor de Cocina, Verifique que este correctamente ingresado la direccion IP, y el puerto", "Confirmación",JOptionPane.WARNING_MESSAGE);
				
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Problemas al conectarse a el Monitor de Cocina, Verifique que este correctamente ingresado la direccion IP, y el puerto", "Confirmación",JOptionPane.WARNING_MESSAGE);
				System.out.println("Problema surgido al conectarse con monitor.");
				e2.printStackTrace();
			}
			
		}
		//establecer conceccion con valores por dafault
		else if(this.conectar!= null && e.getSource()==this.conectar.getBtnDefault())
		{
			try {
				valoresPredetarminados();
			} catch (UnknownHostException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		/////////////////////////////////////////CodigoJuliet/////////////////////////////////////////////////
		//CONFIGURACIONES: ABRIR VENTANA DE MENU DE REPORTES
		else if(e.getSource()== this.ventana.getBtnReportes())
		{
			ventanaMenuReportes = new selectMenuReportes(ventana,this);
			ventanaMenuReportes.setVisible(true);
			ventanaMenuReportes.getBtnConsultacontable().addActionListener(this);
			ventanaMenuReportes.getBtnConsultaestadisticas().addActionListener(this);
		}
		//VENTANA DE MENU DE REPORTES:ABRIR VENTANA REPORTES CONTABLES 
		else if(this.ventanaMenuReportes!= null && e.getSource()==this.ventanaMenuReportes.getBtnConsultacontable())
		{
			ventanaMenuReportes.dispose();
			ventanaReportesContables = new consultorContabilidad(ventana, this);
			ventanaReportesContables.setVisible(true);
			ventanaReportesContables.getBtnBtnbuscarrangofecha().addActionListener(this);
			ventanaReportesContables.getBtnCalendarffin().addActionListener(this);
			ventanaReportesContables.getBtnCalendfinicio().addActionListener(this);
			ventanaReportesContables.getBtnEnviarxmail().addActionListener(this);
			ventanaReportesContables.getBtnFinalizarconsulta().addActionListener(this);
			ventanaReportesContables.getBtnImprimircons().addActionListener(this);		
			ventanaReportesContables.getCbTipoConsulta().addActionListener(this);
		}
		//VENTANA DE REPORTES CONTABLES: IMPRIMIR RESULTADO DE CONSULTA. GENERA PDF
		else if(this.ventanaReportesContables!= null && e.getSource()==this.ventanaReportesContables.getBtnImprimircons())
		{
			new ReporteContable(new Document(),reporteContable, ventanaReportesContables.getFechaInicio(), ventanaReportesContables.getFechaFin()).generarReporteContable();
			
			JOptionPane.showMessageDialog(null, "Se generó y envió a imprimir  la consulta en formato .pdf", "Confirmación",JOptionPane.WARNING_MESSAGE);

		}
		//VENTANA DE REPORTES CONTABLES: ABRIR Selector fecha calendario INICIO
		else if(this.ventanaReportesContables!= null && e.getSource()==this.ventanaReportesContables.getBtnCalendfinicio())
		{
			selectorFecha = new calendarioSelectFecha(ventana, this, "inicio");
			selectorFecha.setVisible(true);
			selectorFecha.getBtnSeleccionar().addActionListener(this);
		}
		//VENTANA DE REPORTES CONTABLES: ABRIR Selector fecha calendario FIN
		else if(this.ventanaReportesContables!= null && e.getSource()==this.ventanaReportesContables.getBtnCalendarffin())
		{
			selectorFecha = new calendarioSelectFecha(ventana, this,"fin");
			selectorFecha.setVisible(true);
			selectorFecha.getBtnSeleccionar().addActionListener(this);
		}
		//SELECTOR FECHA INICIO:Seleccionar la fecha elegida
		else if(this.selectorFecha!= null && e.getSource()==this.selectorFecha.getBtnSeleccionar())
		{
			if (selectorFecha.getIndicadorFecha().compareTo("inicio")==0){
				ventanaReportesContables.setFechaInicio(selectorFecha.getFechaSeleccionada());
				ventanaReportesContables.setDateFechaInicio(selectorFecha.getFechaDate());
				ventanaReportesContables.getLblFechaInicio().setText(selectorFecha.getFechaSeleccionada());
			}
			else{
				ventanaReportesContables.setFechaFin(selectorFecha.getFechaSeleccionada());
				ventanaReportesContables.setDateFechaFin(selectorFecha.getFechaDate());
				ventanaReportesContables.getLblFechaFin().setText(selectorFecha.getFechaSeleccionada());
			}
			selectorFecha.dispose();
		}
		//VENTANA DE REPORTES CONTABLES: COMBO BOX TIPO CONSULTA
		else if(this.ventanaReportesContables!= null && e.getSource()==this.ventanaReportesContables.getCbTipoConsulta())
		{
			switch (ventanaReportesContables.getCbTipoConsulta().getSelectedItem().toString()) {
			case "Seleccione el tipo de consulta":
			{
				ventanaReportesContables.ocultarRango(true);
				break;
			}
			case "Dia de hoy":
			{	
				ventanaReportesContables.ocultarRango(true);
				try {
					consultaReporteDiario();
					reporteContable.calcularGanancias();
					ventanaReportesContables.mostrarResultados(reporteContable.getTotalCompras(),reporteContable.getTotalPedidos(),reporteContable.getGanancia());
					reporteContable.mostrarDetalle();
				} catch (SQLException e1) {
					System.out.println("No se pudo realizar consulta a la bd.");
					e1.printStackTrace();
				}
				break;
			}
			case "Elegir un rango de fechas":
			{	
				ventanaReportesContables.ocultarRango(false);
				break;
			}
			default:
				break;
			}
		}
		//VENTANA DE REPORTES CONTABLES: BOTON DE REALIZAR CONSULTA
		else if(this.ventanaReportesContables!= null && e.getSource()==this.ventanaReportesContables.getBtnBtnbuscarrangofecha())
		{
			if (!ventanaReportesContables.getLblFechaInicio().getText().isEmpty() 
					&& !ventanaReportesContables.getLblFechaFin().getText().isEmpty() && ventanaReportesContables.controlFechas()){	
				Integer diaCero = 1;
				Integer mesCero = 1;
				Integer mes12 = 12;
				Integer diaIndiceFin = 31;
			
				Integer diaInicio = ventanaReportesContables.getFechaInicio("dia");
				Integer mesInicio = ventanaReportesContables.getFechaInicio("mes");
				Integer añoInicio = ventanaReportesContables.getFechaInicio("año");
				
				Integer diaFin = ventanaReportesContables.getFechaFin("dia");
				Integer mesFin = ventanaReportesContables.getFechaFin("mes");
				Integer añoFin = ventanaReportesContables.getFechaFin("año");

				if (mesInicio==12){
					//elaborar pattern a mano -- (mesInicio==mesFin && añoInicio==añoFin)
				}
				else{
					reporteContable = new ReporteContableDTO();
					ArrayList<PedidoDTO> pedidosResultantes = reporteContable.getListadoPedidos();
					ArrayList<OrdenPedidoMatPrimaDTO> comprasResultantes = reporteContable.getListadoCompras();

					for (int j=añoInicio; j<= añoFin ;j++){
						if (j == añoInicio)
							mesCero = mesInicio;
						else
							mesCero = 1;
						
						if (j == añoFin)
							mes12 = mesFin;
						else
							mes12 = 12;

						for (int i=mesCero; i<= mes12;i++){
							if (i == mesInicio)
								diaCero = diaInicio;
								else
									diaCero = 1;
							if (i == mesFin)
								diaIndiceFin = diaFin;
							else{
								diaIndiceFin=31;
							}
							for (int x=diaCero; x<= diaIndiceFin ;x++){
								try {
									pedidosResultantes.addAll(pedido.reporteDiario(String.valueOf(x), String.valueOf(i),String.valueOf(j)	));
									comprasResultantes.addAll(ordenesMatPrimas.reporteEnRango(String.valueOf(x), String.valueOf(i),String.valueOf(j)));							
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(null, "No se puedo realizar la consulta.", "Confirmación",JOptionPane.WARNING_MESSAGE);
									e1.printStackTrace();								
								}
							}
						}
					}
					reporteContable.calcularGanancias();
					ventanaReportesContables.mostrarResultados(reporteContable.getTotalCompras(),reporteContable.getTotalPedidos(),reporteContable.getGanancia());
					reporteContable.mostrarDetalle();
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "La fecha de inicio no puede ser igual ni mayor a la fecha de fin", "Confirmación",JOptionPane.WARNING_MESSAGE);
			}
		}
		//VENTANA DE REPORTES CONTABLES: Enviar reporte por Email
		else if(this.ventanaReportesContables!= null && e.getSource()==this.ventanaReportesContables.getBtnEnviarxmail() )
		{
			JOptionPane.showMessageDialog(null, "Se ha enviado por email", "Confirmación",JOptionPane.WARNING_MESSAGE);
		}
		//Seleccionar tipos de Estadisticas
		else if(this.ventanaReportesEstadistica!= null && e.getSource()==this.ventanaReportesEstadistica.getCbEstadisticas())
		{
			String tipoProducto = (String) ventanaReportesEstadistica.getCbEstadisticas().getSelectedItem().toString();
			if (tipoProducto.compareTo("(Seleccione una estadistica)")==0)
			{
				ventanaReportesEstadistica.getTfOcultaRangoFechas().setVisible(true);
				ventanaReportesEstadistica.getTfOcultarTop().setVisible(true);
				ventanaReportesEstadistica.getTfFechaInicio().setText("");
				ventanaReportesEstadistica.getTfFechaFin().setText("");
			}
			else if(tipoProducto.compareTo("Empanadas mas compradas")==0 || tipoProducto.compareTo("Empanadas menos compradas")==0 || tipoProducto.compareTo("Pizzas mas compradas")==0 || tipoProducto.compareTo("Pizzas menos compradas")==0 )
			{
				ventanaReportesEstadistica.getTfOcultaRangoFechas().setVisible(false);
				ventanaReportesEstadistica.getTfOcultarTop().setVisible(false);
				ventanaReportesEstadistica.getTfFechaInicio().setText("");
				ventanaReportesEstadistica.getTfFechaFin().setText("");
			}
			else if(tipoProducto.compareTo("Ofertas mas compradas")==0 || tipoProducto.compareTo("Ofertas menos compradas")==0|| tipoProducto.compareTo("Cliente mas comprador")==0 )
			{
				ventanaReportesEstadistica.getTfOcultaRangoFechas().setVisible(false);
				ventanaReportesEstadistica.getTfOcultarTop().setVisible(false);
				ventanaReportesEstadistica.getTfFechaInicio().setText("");
				ventanaReportesEstadistica.getTfFechaFin().setText("");
			}
		}
		//establecer fecha de inicio y fin para ESTADISTICAS
		else if(this.ventanaReportesEstadistica!= null && (e.getSource()==this.ventanaReportesEstadistica.getBtnCalendarioInicio() ||e.getSource()==this.ventanaReportesEstadistica.getBtnCalendarioFin() ))
		{
			if(e.getSource()==this.ventanaReportesEstadistica.getBtnCalendarioInicio())
			{
				ventanaCalendario=new calendario(ventanaReportesEstadistica);
				ventanaCalendario.setTitle("Fecha de Inicio");
				ventanaCalendario.setVisible(true);
			}
			else
			{
				ventanaCalendario=new calendario(ventanaReportesEstadistica);
				ventanaCalendario.setTitle("Fecha de Fin");
				ventanaCalendario.setVisible(true);
			}	
		}
		//BUSCAR ESTADISTICAS EN UN RANGO DE FECHAS
		else if(this.ventanaReportesEstadistica!= null && e.getSource()==this.ventanaReportesEstadistica.getBtnBuscar())
		{
			if(ventanaReportesEstadistica.getTfFechaInicio().getText().compareTo("")!=0 &&ventanaReportesEstadistica.getTfFechaFin().getText().compareTo("")!=0)
			{
				String tipoProducto = (String) ventanaReportesEstadistica.getCbEstadisticas().getSelectedItem().toString();
				switch (tipoProducto)
				{
				case "Empanadas mas compradas":
				{
					ArrayList<PedidoDTO> pedidosResultantes= new ArrayList<PedidoDTO>();
					///Adaptacion///////////////
					Integer diaCero = 1;
					Integer mesCero = 1;
					Integer mes12 = 12;
					Integer diaIndiceFin = 31;
				
					Integer diaInicio = ventanaReportesEstadistica.getDiaInicio();
					Integer mesInicio = ventanaReportesEstadistica.getMesInicio();
					Integer añoInicio = ventanaReportesEstadistica.getAñoInicio();
					String fInicio= añoInicio.toString() + mesInicio.toString() + diaInicio.toString(); 
					
					Integer diaFin = ventanaReportesEstadistica.getDiaFin();
					Integer mesFin = ventanaReportesEstadistica.getMesFin();
					Integer añoFin = ventanaReportesEstadistica.getAñoFin();
					String fFin= añoFin.toString() + mesFin.toString() + diaFin.toString(); 
					
					if (mesInicio==12){
						//elaborar pattern a mano -- (mesInicio==mesFin && añoInicio==añoFin)
					}
					else{
						for (int j=añoInicio; j<= añoFin ;j++){
							if (j == añoInicio)
								mesCero = mesInicio;
							else
								mesCero = 1;
							
							if (j == añoFin)
								mes12 = mesFin;
							else
								mes12 = 12;

							for (int i=mesCero; i<= mes12;i++){
								if (i == mesInicio)
									diaCero = diaInicio;
									else
										diaCero = 1;
								if (i == mesFin)
									diaIndiceFin = diaFin;
								else{
									diaIndiceFin=31;
								}
								for (int x=diaCero; x<= diaIndiceFin ;x++){
									try {
										pedidosResultantes.addAll(pedido.reporteDiario(String.valueOf(x), String.valueOf(i),String.valueOf(j)	));							
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(null, "No se puedo realizar la consulta.", "Confirmación",JOptionPane.WARNING_MESSAGE);
										e1.printStackTrace();								
									}
								}
							}
						}
					}
					///////fin adaptacion///				
					String tipoEstadistica = "Empanadas mas compradas";
					ArrayList<ProductoEstadistico> producto=obtenerTodosProdusctosTodosPedidos(pedidosResultantes,1);
					ArrayList<PromocionEstadistica> promocion=obtenerTodasPromocionesTodosPedidos(pedidosResultantes);
					ArrayList<ProductoEstadistico>produc=obtenerTodasProductostodasPromocionesTodosPedidos(promocion);
					producto=sumarProductos(produc, producto);
					String tipo="empanada";
					producto=filtrarProductoTipo(producto,tipo);
					Collections.sort(producto,new Comparator<ProductoEstadistico>()
							{
								@Override
								public int compare(ProductoEstadistico o1,
										ProductoEstadistico o2) 
								{
									return new Integer(o2.getCantidad()).compareTo(new Integer(o1.getCantidad()));
								}
							});
					
					new ReporteProductoEstadistico (new Document(), tipoEstadistica, tipo, producto, ventanaReportesEstadistica.getTfFechaInicio().getText(), ventanaReportesEstadistica.getTfFechaFin().getText()).generarReporteEstadistico();
					if(ventanaReportesEstadistica.getButtonGroup().isSelected(ventanaReportesEstadistica.getVerTodo().getModel()))
						llenarTablaEstadisticas("producto", null, producto);
					else
						llenarTablaEstadisticasTop5("producto", null, producto);
					System.out.println("termino");
				}
				break;
				
				case "Empanadas menos compradas":
				{
					ArrayList<PedidoDTO> pedidosResultantes= new ArrayList<PedidoDTO>();
					///Adaptacion///////////////
					Integer diaCero = 1;
					Integer mesCero = 1;
					Integer mes12 = 12;
					Integer diaIndiceFin = 31;
				
					Integer diaInicio = ventanaReportesEstadistica.getDiaInicio();
					Integer mesInicio = ventanaReportesEstadistica.getMesInicio();
					Integer añoInicio = ventanaReportesEstadistica.getAñoInicio();
					String fInicio= añoInicio.toString() + mesInicio.toString() + diaInicio.toString();
					
					Integer diaFin = ventanaReportesEstadistica.getDiaFin();
					Integer mesFin = ventanaReportesEstadistica.getMesFin();
					Integer añoFin = ventanaReportesEstadistica.getAñoFin();
					String fFin= añoFin.toString() + mesFin.toString() + diaFin.toString(); 
					

					if (mesInicio==12){
						//elaborar pattern a mano -- (mesInicio==mesFin && añoInicio==añoFin)
					}
					else{
						for (int j=añoInicio; j<= añoFin ;j++){
							if (j == añoInicio)
								mesCero = mesInicio;
							else
								mesCero = 1;
							
							if (j == añoFin)
								mes12 = mesFin;
							else
								mes12 = 12;

							for (int i=mesCero; i<= mes12;i++){
								if (i == mesInicio)
									diaCero = diaInicio;
									else
										diaCero = 1;
								if (i == mesFin)
									diaIndiceFin = diaFin;
								else{
									diaIndiceFin=31;
								}
								for (int x=diaCero; x<= diaIndiceFin ;x++){
									try {
										pedidosResultantes.addAll(pedido.reporteDiario(String.valueOf(x), String.valueOf(i),String.valueOf(j)	));							
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(null, "No se puedo realizar la consulta.", "Confirmación",JOptionPane.WARNING_MESSAGE);
										e1.printStackTrace();								
									}
								}
							}
						}
					}
					///////fin adaptacion///
					ArrayList<ProductoEstadistico> producto=obtenerTodosProdusctosTodosPedidos(pedidosResultantes,1);
					ArrayList<PromocionEstadistica> promocion=obtenerTodasPromocionesTodosPedidos(pedidosResultantes);
					String tipoEstadistica = "Empanadas menos compradas";
					ArrayList<ProductoEstadistico>produc=obtenerTodasProductostodasPromocionesTodosPedidos(promocion);
					producto=sumarProductos(produc, producto);
					String tipo="empanada";
					producto=filtrarProductoTipo(producto,tipo);
					Collections.sort(producto,new Comparator<ProductoEstadistico>() {
						@Override
						public int compare(ProductoEstadistico o1,
								ProductoEstadistico o2) {
							return new Integer(o1.getCantidad()).compareTo(new Integer(o2.getCantidad()));
						}
					});
					
					new ReporteProductoEstadistico (new Document(), tipoEstadistica, tipo, producto, ventanaReportesEstadistica.getTfFechaInicio().getText(), ventanaReportesEstadistica.getTfFechaFin().getText()).generarReporteEstadistico();
					if(ventanaReportesEstadistica.getButtonGroup().isSelected(ventanaReportesEstadistica.getVerTodo().getModel()))
						llenarTablaEstadisticas("producto", null, producto);
					else
						llenarTablaEstadisticasTop5("producto", null, producto);
					System.out.println("termino");
				}
				break;
				case "Pizzas mas compradas":
				{
					ArrayList<PedidoDTO> pedidosResultantes= new ArrayList<PedidoDTO>();
					///Adaptacion///////////////
					Integer diaCero = 1;
					Integer mesCero = 1;
					Integer mes12 = 12;
					Integer diaIndiceFin = 31;
				
					Integer diaInicio = ventanaReportesEstadistica.getDiaInicio();
					Integer mesInicio = ventanaReportesEstadistica.getMesInicio();
					Integer añoInicio = ventanaReportesEstadistica.getAñoInicio();
					String fInicio= añoInicio.toString() + mesInicio.toString() + diaInicio.toString(); 
					
					Integer diaFin = ventanaReportesEstadistica.getDiaFin();
					Integer mesFin = ventanaReportesEstadistica.getMesFin();
					Integer añoFin = ventanaReportesEstadistica.getAñoFin();
					String fFin= añoFin.toString() + mesFin.toString() + diaFin.toString(); 
					
					if (mesInicio==12){
						//elaborar pattern a mano -- (mesInicio==mesFin && añoInicio==añoFin)
					}
					else{
						for (int j=añoInicio; j<= añoFin ;j++){
							if (j == añoInicio)
								mesCero = mesInicio;
							else
								mesCero = 1;
							
							if (j == añoFin)
								mes12 = mesFin;
							else
								mes12 = 12;

							for (int i=mesCero; i<= mes12;i++){
								if (i == mesInicio)
									diaCero = diaInicio;
									else
										diaCero = 1;
								if (i == mesFin)
									diaIndiceFin = diaFin;
								else{
									diaIndiceFin=31;
								}
								for (int x=diaCero; x<= diaIndiceFin ;x++){
									try {
										pedidosResultantes.addAll(pedido.reporteDiario(String.valueOf(x), String.valueOf(i),String.valueOf(j)	));							
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(null, "No se puedo realizar la consulta.", "Confirmación",JOptionPane.WARNING_MESSAGE);
										e1.printStackTrace();								
									}
								}
							}
						}
					}
					///////fin adaptacion///				
					String tipoEstadistica = "Pizzas mas compradas";
					ArrayList<ProductoEstadistico> producto=obtenerTodosProdusctosTodosPedidos(pedidosResultantes,1);
					ArrayList<PromocionEstadistica> promocion=obtenerTodasPromocionesTodosPedidos(pedidosResultantes);
					ArrayList<ProductoEstadistico>produc=obtenerTodasProductostodasPromocionesTodosPedidos(promocion);
					producto=sumarProductos(produc, producto);
					String tipo="pizza";
					producto=filtrarProductoTipo(producto,tipo);
					Collections.sort(producto,new Comparator<ProductoEstadistico>()
							{
								@Override
								public int compare(ProductoEstadistico o1,
										ProductoEstadistico o2) 
								{
									return new Integer(o2.getCantidad()).compareTo(new Integer(o1.getCantidad()));
								}
							});
					
					new ReporteProductoEstadistico (new Document(), tipoEstadistica, tipo, producto, ventanaReportesEstadistica.getTfFechaInicio().getText(), ventanaReportesEstadistica.getTfFechaFin().getText()).generarReporteEstadistico();
					if(ventanaReportesEstadistica.getButtonGroup().isSelected(ventanaReportesEstadistica.getVerTodo().getModel()))
						llenarTablaEstadisticas("producto", null, producto);
					else
						llenarTablaEstadisticasTop5("producto", null, producto);
					System.out.println("termino");
				}
				break;
				case "Pizzas menos compradas":
				{
					ArrayList<PedidoDTO> pedidosResultantes= new ArrayList<PedidoDTO>();
					///Adaptacion///////////////
					Integer diaCero = 1;
					Integer mesCero = 1;
					Integer mes12 = 12;
					Integer diaIndiceFin = 31;
				
					Integer diaInicio = ventanaReportesEstadistica.getDiaInicio();
					Integer mesInicio = ventanaReportesEstadistica.getMesInicio();
					Integer añoInicio = ventanaReportesEstadistica.getAñoInicio();
					String fInicio= añoInicio.toString() + mesInicio.toString() + diaInicio.toString(); 
					
					Integer diaFin = ventanaReportesEstadistica.getDiaFin();
					Integer mesFin = ventanaReportesEstadistica.getMesFin();
					Integer añoFin = ventanaReportesEstadistica.getAñoFin();
					String fFin= añoFin.toString() + mesFin.toString() + diaFin.toString(); 
					
					if (mesInicio==12){
						//elaborar pattern a mano -- (mesInicio==mesFin && añoInicio==añoFin)
					}
					else{
						for (int j=añoInicio; j<= añoFin ;j++){
							if (j == añoInicio)
								mesCero = mesInicio;
							else
								mesCero = 1;
							
							if (j == añoFin)
								mes12 = mesFin;
							else
								mes12 = 12;

							for (int i=mesCero; i<= mes12;i++){
								if (i == mesInicio)
									diaCero = diaInicio;
									else
										diaCero = 1;
								if (i == mesFin)
									diaIndiceFin = diaFin;
								else{
									diaIndiceFin=31;
								}
								for (int x=diaCero; x<= diaIndiceFin ;x++){
									try {
										pedidosResultantes.addAll(pedido.reporteDiario(String.valueOf(x), String.valueOf(i),String.valueOf(j)	));							
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(null, "No se puedo realizar la consulta.", "Confirmación",JOptionPane.WARNING_MESSAGE);
										e1.printStackTrace();								
									}
								}
							}
						}
					}
					///////fin adaptacion///				
					String tipoEstadistica = "Pizzas menos compradas";
					ArrayList<ProductoEstadistico> producto=obtenerTodosProdusctosTodosPedidos(pedidosResultantes,1);
					ArrayList<PromocionEstadistica> promocion=obtenerTodasPromocionesTodosPedidos(pedidosResultantes);
					ArrayList<ProductoEstadistico>produc=obtenerTodasProductostodasPromocionesTodosPedidos(promocion);
					producto=sumarProductos(produc, producto);
					String tipo="pizza";
					producto=filtrarProductoTipo(producto,tipo);
					Collections.sort(producto,new Comparator<ProductoEstadistico>() {
						@Override
						public int compare(ProductoEstadistico o1,
								ProductoEstadistico o2) {
							return new Integer(o1.getCantidad()).compareTo(new Integer(o2.getCantidad()));
						}
					});
					new ReporteProductoEstadistico (new Document(), tipoEstadistica, tipo, producto, ventanaReportesEstadistica.getTfFechaInicio().getText(), ventanaReportesEstadistica.getTfFechaFin().getText()).generarReporteEstadistico();
					if(ventanaReportesEstadistica.getButtonGroup().isSelected(ventanaReportesEstadistica.getVerTodo().getModel()))
						llenarTablaEstadisticas("producto", null, producto);
					else
						llenarTablaEstadisticasTop5("producto", null, producto);
					System.out.println("termino");
				}
				break;

				
				case "Ofertas mas compradas":
				{
					///Adaptacion///////////////
					ArrayList<PedidoDTO> pedidosResultantes= new ArrayList<PedidoDTO>();
					Integer diaCero = 1;
					Integer mesCero = 1;
					Integer mes12 = 12;
					Integer diaIndiceFin = 31;
				
					Integer diaInicio = ventanaReportesEstadistica.getDiaInicio();
					Integer mesInicio = ventanaReportesEstadistica.getMesInicio();
					Integer añoInicio = ventanaReportesEstadistica.getAñoInicio();
					String fInicio= añoInicio.toString() + mesInicio.toString() + diaInicio.toString();
					
					Integer diaFin = ventanaReportesEstadistica.getDiaFin();
					Integer mesFin = ventanaReportesEstadistica.getMesFin();
					Integer añoFin = ventanaReportesEstadistica.getAñoFin();
					String fFin= añoFin.toString() + mesFin.toString() + diaFin.toString(); 

					if (mesInicio==12){
						//elaborar pattern a mano -- (mesInicio==mesFin && añoInicio==añoFin)
					}
					else{
						for (int j=añoInicio; j<= añoFin ;j++){
							if (j == añoInicio)
								mesCero = mesInicio;
							else
								mesCero = 1;
							
							if (j == añoFin)
								mes12 = mesFin;
							else
								mes12 = 12;

							for (int i=mesCero; i<= mes12;i++){
								if (i == mesInicio)
									diaCero = diaInicio;
									else
										diaCero = 1;
								if (i == mesFin)
									diaIndiceFin = diaFin;
								else{
									diaIndiceFin=31;
								}
								for (int x=diaCero; x<= diaIndiceFin ;x++){
									try {
										pedidosResultantes.addAll(pedido.reporteDiario(String.valueOf(x), String.valueOf(i),String.valueOf(j)	));							
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(null, "No se puedo realizar la consulta.", "Confirmación",JOptionPane.WARNING_MESSAGE);
										e1.printStackTrace();								
									}
								}
							}
						}
					}
					///////fin adaptacion///
					ArrayList<PromocionEstadistica> promocion=obtenerTodasPromocionesTodosPedidos(pedidosResultantes);
					String tipoEstadistica = "Ofertas mas compradas";
					Collections.sort(promocion,new Comparator<PromocionEstadistica>() {
						@Override
						public int compare(PromocionEstadistica o1,
								PromocionEstadistica o2) {
							return new Integer(o2.getCantidad()).compareTo(new Integer(o1.getCantidad()));
						}
					});
					new ReportePromocionEstadistica (new Document(), tipoEstadistica, promocion,ventanaReportesEstadistica.getTfFechaInicio().getText(), ventanaReportesEstadistica.getTfFechaFin().getText()).generarReporteEstadistico();
					if(ventanaReportesEstadistica.getButtonGroup().isSelected(ventanaReportesEstadistica.getVerTodo().getModel()))
						llenarTablaEstadisticas("promocion",promocion,null);
					else
						llenarTablaEstadisticasTop5("promocion",promocion,null);
					System.out.println("termino");
				}				
				break;
				case "Ofertas menos compradas":
				{
					///Adaptacion///////////////
					ArrayList<PedidoDTO> pedidosResultantes= new ArrayList<PedidoDTO>();
					Integer diaCero = 1;
					Integer mesCero = 1;
					Integer mes12 = 12;
					Integer diaIndiceFin = 31;
				
					Integer diaInicio = ventanaReportesEstadistica.getDiaInicio();
					Integer mesInicio = ventanaReportesEstadistica.getMesInicio();
					Integer añoInicio = ventanaReportesEstadistica.getAñoInicio();
					String fInicio= añoInicio.toString() + mesInicio.toString() + diaInicio.toString();
					
					Integer diaFin = ventanaReportesEstadistica.getDiaFin();
					Integer mesFin = ventanaReportesEstadistica.getMesFin();
					Integer añoFin = ventanaReportesEstadistica.getAñoFin();
					String fFin= añoFin.toString() + mesFin.toString() + diaFin.toString(); 

					if (mesInicio==12){
						//elaborar pattern a mano -- (mesInicio==mesFin && añoInicio==añoFin)
					}
					else{
						for (int j=añoInicio; j<= añoFin ;j++){
							if (j == añoInicio)
								mesCero = mesInicio;
							else
								mesCero = 1;
							
							if (j == añoFin)
								mes12 = mesFin;
							else
								mes12 = 12;

							for (int i=mesCero; i<= mes12;i++){
								if (i == mesInicio)
									diaCero = diaInicio;
									else
										diaCero = 1;
								if (i == mesFin)
									diaIndiceFin = diaFin;
								else{
									diaIndiceFin=31;
								}
								for (int x=diaCero; x<= diaIndiceFin ;x++){
									try {
										pedidosResultantes.addAll(pedido.reporteDiario(String.valueOf(x), String.valueOf(i),String.valueOf(j)	));							
									} catch (SQLException e1) {
										JOptionPane.showMessageDialog(null, "No se puedo realizar la consulta.", "Confirmación",JOptionPane.WARNING_MESSAGE);
										e1.printStackTrace();								
									}
								}
							}
						}
					}
					///////fin adaptacion///
					String tipoEstadistica = "Ofertas menos compradas";
					List<ItemPromocionDTO> listaPormoPed=this.pedido.obtenerTodosPromos();
					ArrayList<PromocionEstadistica> promocion=obtenerTodasPromocionesTodosPedidos(pedidosResultantes);
					Collections.sort(promocion);
					new ReportePromocionEstadistica (new Document(), tipoEstadistica, promocion, ventanaReportesEstadistica.getTfFechaInicio().getText(), ventanaReportesEstadistica.getTfFechaFin().getText()).generarReporteEstadistico();
					if(ventanaReportesEstadistica.getButtonGroup().isSelected(ventanaReportesEstadistica.getVerTodo().getModel()))
						llenarTablaEstadisticas("promocion",promocion,null);
					else
						llenarTablaEstadisticasTop5("promocion",promocion,null);
					System.out.println("termino");
				}				
				break;
				}
			}
			else
				JOptionPane.showMessageDialog(null, "Error, para hacer la consulta debe seleccionar la fecha de inicio y fin");
		}
		
		//ABRIR SELECTOR MAT PRIMA
		else if(e.getSource()== this.ventana.getBtnPedMatPrima())
		{
			ventanaSelectorOpcOrdenMatPrima = new selectorOpcionesOrdenMatPrima(ventana, this);
			ventanaSelectorOpcOrdenMatPrima.setVisible(true);

			this.ventanaSelectorOpcOrdenMatPrima.getBtnAgregarOrdenMatPrima().addActionListener(this);
			this.ventanaSelectorOpcOrdenMatPrima.btnGestionarOrdenes().addActionListener(this);
		}
		//SELECTOR OPC ORDENES MAT PRIMA> abre ventana agregar orden mat prima
		else if(this.ventanaSelectorOpcOrdenMatPrima!= null && e.getSource()==this.ventanaSelectorOpcOrdenMatPrima.getBtnAgregarOrdenMatPrima())
		{
			ventanaOrdenMatPrima= new ordenarMatPrima(ventana, this);
			ventanaOrdenMatPrima.setVisible(true);

			this.ventanaOrdenMatPrima.getButtonAgregarMateriaPrima().addActionListener(this);
			this.ventanaOrdenMatPrima.getButtonQuitarMatPrima().addActionListener(this);
			this.ventanaOrdenMatPrima.getButtonLimpiarBuscador().addActionListener(this);
			this.ventanaOrdenMatPrima.getBtnCancelar().addActionListener(this);
			this.ventanaOrdenMatPrima.getBtnEnviarform().addActionListener(this);
			this.ventanaOrdenMatPrima.getBtnGuardarform().addActionListener(this);
			this.ventanaOrdenMatPrima.getBtnVerproveedores().addActionListener(this);
			this.ventanaOrdenMatPrima.getBtnVermatprima().addActionListener(this);
			this.ventanaOrdenMatPrima.getComboListaProveedores().addActionListener(this);
			this.ventanaOrdenMatPrima.getComboListaCategorias().addActionListener(this);
			//CARGA PROVEEDORES, CATEGORIA Y MAT PRIMA FILTRADA
			cargarProveedores(ventanaOrdenMatPrima.getComboListaProveedores());
			guardarProveedorSeleccionado();
			generarArrayMatPrimaProveed();
			cargarCategorias();
			prepararMatPrimaParaBusqXCategoria();
			this.ventanaSelectorOpcOrdenMatPrima.dispose();
		}
		//SELECTOR OPC ORDENES MAT PRIMA> Abrir gestor de ordenes de mat prima
		else if(this.ventanaSelectorOpcOrdenMatPrima!= null && e.getSource()==this.ventanaSelectorOpcOrdenMatPrima.getBtnGestionarOrdenes())
		{
			gestorOrdenesMateriasPrimas = new gestionarOrdenesMatPrima(ventana, this);
			this.gestorOrdenesMateriasPrimas.setVisible(true);

			this.gestorOrdenesMateriasPrimas.getBtnAgregarOrdenMatPrima().addActionListener(this);
			this.gestorOrdenesMateriasPrimas.getBtnBorrarorden().addActionListener(this);
			this.gestorOrdenesMateriasPrimas.getBtnCargarorden().addActionListener(this);
			this.gestorOrdenesMateriasPrimas.getBtnEnviarmailorden().addActionListener(this);
			this.gestorOrdenesMateriasPrimas.getBtnPagarorden().addActionListener(this);
			this.gestorOrdenesMateriasPrimas.getComboBoxFiltroOrdenes().addActionListener(this);
			this.gestorOrdenesMateriasPrimas.getBtnBuscar().addActionListener(this);
			this.gestorOrdenesMateriasPrimas.getBtnImprimirOrden().addActionListener(this);
			this.gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent event) {
					// do some actions here, for example
					validarHabilitacionBotonesOrdenesMP();
				}
			});

			filtrarBusquedaOrdenes();
			this.ventanaSelectorOpcOrdenMatPrima.dispose();
		}
		//GESTOR MAT PRIMA> Cargar items de determinada orden
		else if(this.gestorOrdenesMateriasPrimas!= null && e.getSource()==this.gestorOrdenesMateriasPrimas.getBtnCargarorden())
		{
			gestorOrdenesMateriasPrimas.resetearItemsOrdenesMatPrima();
			Integer intRowSelected = this.gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().getSelectedRow();
			if (intRowSelected >=0){
				OrdenPedidoMatPrimaDTO ordenSeleccionada = getOrden(Integer.parseInt(gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().getValueAt(intRowSelected, 0).toString()));
				Iterator<ItemMateriaPrimaDTO> iterItems = ordenSeleccionada.getListadoCompra().iterator();
				while (iterItems.hasNext()){
					ItemMateriaPrimaDTO elementoItem = iterItems.next();
					gestorOrdenesMateriasPrimas.agregarFilaItem(elementoItem);
				}
			}
			gestorOrdenesMateriasPrimas.gettableItemsSolicitados().setModel(gestorOrdenesMateriasPrimas.getmodeloItemsSolicitdos());
		}
		//GESTOR MAT PRIMA>> BUSCAR POR NOMBRE DE PROV LAS ORDENES 
		else if(this.gestorOrdenesMateriasPrimas!= null && e.getSource()==this.gestorOrdenesMateriasPrimas.getBtnBuscar())
		{
			this.gestorOrdenesMateriasPrimas.resetearModeloOrdenesPedido();
			if (this.gestorOrdenesMateriasPrimas.getTextAutoAcompleter().getItemSelected().toString().compareTo("")!=0){
				String nomProvSeleccionado = this.gestorOrdenesMateriasPrimas.getTextAutoAcompleter().getItemSelected().toString

						().trim();
				Iterator<OrdenPedidoMatPrimaDTO> iteradorOrdenes = ordenesMatPrimas.obtenerOrdenPedidoMatPrima().iterator();
				while (iteradorOrdenes.hasNext()){
					OrdenPedidoMatPrimaDTO elementoOrden = iteradorOrdenes.next();
					if (elementoOrden.getProveedor().getNombre().compareTo(nomProvSeleccionado)==0)
						this.gestorOrdenesMateriasPrimas.agregarFilaOrden(elementoOrden);
				}
			}
			this.gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().setModel

			(this.gestorOrdenesMateriasPrimas.getModeloOrdenesMatPrimas());
		}
		//GESTOR ORDENES MAT PRIMA> Accion para filtro de busqeda
		else if(this.gestorOrdenesMateriasPrimas!= null && e.getSource()==this.gestorOrdenesMateriasPrimas.getComboBoxFiltroOrdenes())
		{
			this.gestorOrdenesMateriasPrimas.getTextAutoAcompleter().removeAllItems();
			this.gestorOrdenesMateriasPrimas.resetearModeloOrdenesPedido();
			filtrarBusquedaOrdenes();
			this.gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().setModel(this.gestorOrdenesMateriasPrimas.getModeloOrdenesMatPrimas());
			this.gestorOrdenesMateriasPrimas.resetearItemsOrdenesMatPrima();
			this.gestorOrdenesMateriasPrimas.ocultarCostos();
		}
		//GESTOR ORDENES MAT PRIMA> IMPRIMIR ORDEN 
		else if(this.gestorOrdenesMateriasPrimas!= null && e.getSource()==this.gestorOrdenesMateriasPrimas.getBtnImprimirOrden())
		{
			Integer intRowSelected = this.gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().getSelectedRow();
			if (intRowSelected >-1){
				OrdenPedidoMatPrimaDTO ordenSeleccionada = ordenesMatPrimas.buscarOrdenPedidoMatPrima(Integer.parseInt(gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().getValueAt(gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().getSelectedRow(),0).toString()));
				if (ordenSeleccionada.getEstado().trim().compareTo("guardado") == 0){
					ordenSeleccionada.setFecha(getFechaActual());
					ordenSeleccionada.setEstado("enviado");
					ordenSeleccionada.setEnviado(true);
					gestorOrdenesMateriasPrimas.setOrdenSeleccionada(ordenSeleccionada);
					ordenesMatPrimas.actualizarDatos(ordenSeleccionada);		
					filtrarBusquedaOrdenes();
				}
				prepararParaSobreescribirPDF(ordenSeleccionada);
				generarPDFOrdenMatPrima();
				JOptionPane.showMessageDialog(null, "Se imprimio la orden de compra.", "Confirmación",JOptionPane.WARNING_MESSAGE);

			}
		}
		//GESTOR ORDENES MAT PRIMA> Enviar orden de mat prima x mail
		else if(this.gestorOrdenesMateriasPrimas!= null && e.getSource()==this.gestorOrdenesMateriasPrimas.getBtnEnviarmailorden())
		{
			Integer intRowSelected = this.gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().getSelectedRow();
			if (intRowSelected >-1){
				OrdenPedidoMatPrimaDTO ordenSeleccionada = ordenesMatPrimas.buscarOrdenPedidoMatPrima(Integer.parseInt(gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().getValueAt(gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().getSelectedRow(),0).toString()));
				ordenesMatPrimas.quitarOrdenPedidoMatPrima(ordenSeleccionada);
				ordenSeleccionada.setEstado("enviado");
				ordenSeleccionada.setEnviado(true);
				ordenSeleccionada.setFecha(getFechaActual());
				ordenesMatPrimas.agregarOrdenPedidoMatPrima(ordenSeleccionada);
				JOptionPane.showMessageDialog(null, "Se envió exitosamente el email de solicitud de compra", "Confirmación",JOptionPane.WARNING_MESSAGE);
				filtrarBusquedaOrdenes();
			}
		}
		//GESTOR ORDEN MAT PRIMA> ABRIR VENTANA DE Registrar pago y recepci[on de orden
		else if(this.gestorOrdenesMateriasPrimas!= null && e.getSource()==this.gestorOrdenesMateriasPrimas.getBtnPagarorden())
		{
			//Se debe obtener el objeto seleccionado,en base al nro de id correspondiente. Realizar metodo
			Integer intFilaSeleccionada = gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().getSelectedRow();
			if (intFilaSeleccionada >-1){		
				gestorOrdenesMateriasPrimas.setOrdenSeleccionada(ordenesMatPrimas.buscarOrdenPedidoMatPrima( Integer.parseInt(gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().getValueAt
						(intFilaSeleccionada, 0).toString()) ) );

				ventanaRegistrarPagoOrdenMatPrima = new registrarPagoOrdenMatPrima(ventana, this);
				ventanaRegistrarPagoOrdenMatPrima.setVisible(true);
				ventanaRegistrarPagoOrdenMatPrima.getBtnRegistrarcobro().addActionListener(this);
				
				ventanaRegistrarPagoOrdenMatPrima.cargarDatosOrden(gestorOrdenesMateriasPrimas.getOrdenSeleccionada());
			}
			else
				JOptionPane.showMessageDialog(null, "Primero debe seleccionar una orden de la tabla.", "Confirmación",JOptionPane.WARNING_MESSAGE);
		}
		//GESTOR ORDEN MAT PRIMA: BOTON CONFIRMAR PAGO ORDEN
		else if(this.ventanaRegistrarPagoOrdenMatPrima!= null && e.getSource()==this.ventanaRegistrarPagoOrdenMatPrima.getBtnRegistrarcobro())
			{
				if (!ventanaRegistrarPagoOrdenMatPrima.getTextFieldCosto().getText().isEmpty())
				{
					ordenesMatPrimas.quitarOrdenPedidoMatPrima(gestorOrdenesMateriasPrimas.getOrdenSeleccionada());
					gestorOrdenesMateriasPrimas.getOrdenSeleccionada().setCosto(Integer.parseInt(ventanaRegistrarPagoOrdenMatPrima.getTextFieldCosto().getText().toString()));
					gestorOrdenesMateriasPrimas.getOrdenSeleccionada().setEstado("pagado");
					gestorOrdenesMateriasPrimas.getOrdenSeleccionada().setFecha(getFechaActual());
					ordenesMatPrimas.agregarOrdenPedidoMatPrima(gestorOrdenesMateriasPrimas.getOrdenSeleccionada());
					filtrarBusquedaOrdenes();//para q actualice los estados de las ordenes y no haya problema con la busq x 
					JOptionPane.showMessageDialog(null, "Se ha registrado correctamente la recepción y pago de la orden de materia prima", "Confirmación",JOptionPane.WARNING_MESSAGE); 
					filtrarBusquedaOrdenes();
					ventanaRegistrarPagoOrdenMatPrima.dispose();	
				}
			}
		//GESTOR MAT PRIMA> BORRAR ORDEN DE MATERIA PRIMA.
		else if(this.gestorOrdenesMateriasPrimas!= null && e.getSource()==this.gestorOrdenesMateriasPrimas.getBtnBorrarorden())
		{		
			//Se asume que la orden seleccionada es de estado> GUARDADO 
			Integer intFilaSeleccionada = gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().getSelectedRow();
			if (intFilaSeleccionada >-1){
				int seleccion = JOptionPane.showOptionDialog(gestorOrdenesMateriasPrimas,"¿Confirma el borrado de la orden de materia prima seleccionada?",
						"Advertencia!", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] { "Confirmar", "Cancelar"},"Si");
				if (seleccion == 0){ // dio ok a dialogo de confirmacion
					Integer idOrdenSeleccionada = Integer.parseInt(gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().getValueAt(intFilaSeleccionada, 0).toString());			
					gestorOrdenesMateriasPrimas.setOrdenSeleccionada(ordenesMatPrimas.buscarOrdenPedidoMatPrima( Integer.parseInt(gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().getValueAt(intFilaSeleccionada, 0).toString())));
					ordenesMatPrimas.quitarOrdenPedidoMatPrima(gestorOrdenesMateriasPrimas.getOrdenSeleccionada());
					gestorOrdenesMateriasPrimas.getOrdenSeleccionada().setFueeliminado(true);
					gestorOrdenesMateriasPrimas.getOrdenSeleccionada().setEstado("rechazado");
					ordenesMatPrimas.agregarOrdenPedidoMatPrima(gestorOrdenesMateriasPrimas.getOrdenSeleccionada());
					this.gestorOrdenesMateriasPrimas.resetearModeloOrdenesPedido();
					filtrarBusquedaOrdenes();
				}
			}
		}
		//ORDEN MATERIA PRIMA> COMBO LISTA PROVEEDORES
		else if(this.ventanaOrdenMatPrima!= null && e.getSource()==this.ventanaOrdenMatPrima.getComboListaProveedores())
		{
			guardarProveedorSeleccionado();
			cargarCategorias();
			generarArrayMatPrimaProveed();
			prepararMatPrimaParaBusqXCategoria();
			limpiarCampos();
		}
		//ORDEN MATERIA PRIMA> CATEGORIAS MATERIA PRIMA
		else if(this.ventanaOrdenMatPrima!= null && e.getSource()==this.ventanaOrdenMatPrima.getComboListaCategorias())
		{
			if ( ventanaOrdenMatPrima.getComboListaCategorias().getSelectedItem()!=null){
				prepararMatPrimaParaBusqXCategoria();
				limpiarCampos();
			}
		}
		//ORDEN MATERIA PRIMA> VER PROVEEDORES
		else if(this.ventanaOrdenMatPrima!= null && e.getSource()==this.ventanaOrdenMatPrima.getBtnVerproveedores())
		{
			ventanaSeleccionProveedor = new buscadorProveedor(ventana, this);
			ventanaSeleccionProveedor.setVisible(true);
			preCargarCamposdeBusqueda();
			accionParaCambioFiltro();
			ventanaSeleccionProveedor.getComboFiltroBusqueda().addActionListener(this);
			ventanaSeleccionProveedor.getComboBoxCategorias().addActionListener(this);
			ventanaSeleccionProveedor.getBtnSeleccionarproveedor().addActionListener(this);
			ventanaSeleccionProveedor.getBtnDetallesproveedor().addActionListener(this);
		}
		//ORDEN MATERIA PRIMA> VER MATERIA PRIMA
		else if(this.ventanaOrdenMatPrima!= null && e.getSource()==this.ventanaOrdenMatPrima.getBtnVermatprima())
		{
			ventanaSeleccionMatPrima = new selectorMatPrima(ventana, this);
			ventanaSeleccionMatPrima.setVisible(true);
			ventanaSeleccionMatPrima.cargarMatPrima(ventanaOrdenMatPrima.getComboListaCategorias().getSelectedItem().toString());
			ventanaSeleccionMatPrima.getBtnSeleccionarMateriaPrima().addActionListener(this);
		}
		//ORDEN MATERIA PRIMA> AGREGAR ITEM MATERIA PRIMA
		else if(this.ventanaOrdenMatPrima!= null && e.getSource()==this.ventanaOrdenMatPrima.getButtonAgregarMateriaPrima())
		{
			if (ventanaOrdenMatPrima.getTextFieldCantMatPrima().getText().toString().compareTo("")!=0 && ventanaOrdenMatPrima.getTextFieldBuscadorMatPrima().getText().toString().compareTo("")!=0){
				if (ventanaOrdenMatPrima.getProvBloqueado() == false){
					ventanaOrdenMatPrima.getComboListaProveedores().setEnabled(false);
					ventanaOrdenMatPrima.setProvBloqueado(true);
				}
				Integer idItem = itemsMateriaPrima.obtenerItemMatPrima().size();
				ItemMateriaPrimaDTO itemSeleccionado = new ItemMateriaPrimaDTO(idItem, getMatPrimaSeleccionada
						(ventanaOrdenMatPrima.getTextFieldBuscadorMatPrima().getText().toString()),Integer.parseInt(ventanaOrdenMatPrima.getTextFieldCantMatPrima().getText().toString()),false);
				agregarItemTabla(itemSeleccionado);
				limpiarCampos();
			}
		}
		//ORDEN MATERIA PRIMA> QUITAR ITEM AGREGADO
		else if(this.ventanaOrdenMatPrima!= null && e.getSource()==this.ventanaOrdenMatPrima.getButtonQuitarMatPrima())
		{
			if (ventanaOrdenMatPrima.getTablaItemsMateriaPrima().getSelectedRow() >= 0){
				ventanaOrdenMatPrima.getModeloItemsSolicitados().removeRow(ventanaOrdenMatPrima.getTablaItemsMateriaPrima().getSelectedRow());
				ventanaOrdenMatPrima.getTablaItemsMateriaPrima().setModel(ventanaOrdenMatPrima.getModeloItemsSolicitados());
			}
		}
		//ORDEN MATERIA PRIMA> LIMPIAR BUSCADOR
		else if(this.ventanaOrdenMatPrima!= null && e.getSource()==this.ventanaOrdenMatPrima.getButtonLimpiarBuscador())
		{
			limpiarCampos();
		}
		//ORDEN MATERIA PRIMA>ENVIAR ORDEN X MAIL, PDF.
		else if(this.ventanaOrdenMatPrima!= null && e.getSource()==this.ventanaOrdenMatPrima.getBtnEnviarform())
		{
			persistirOrdenMatPrima(true,"enviado");			
			generarPDFOrdenMatPrima();
			JOptionPane.showMessageDialog(null, "Se ha creado un PDF con la orden de compra y fue enviado con por e-mail a su proveedor.", "Confirmación", JOptionPane.WARNING_MESSAGE);
			ventanaOrdenMatPrima.dispose();
		}
		//ORDEN MATERIA PRIMA> SOLO GUARDAR, GENERAR PDF Y PERSIST	ºIR ORDEN
		else if(this.ventanaOrdenMatPrima!= null && e.getSource()==this.ventanaOrdenMatPrima.getBtnGuardarform())
		{
			persistirOrdenMatPrima(false,"guardado");
			generarPDFOrdenMatPrima();
			JOptionPane.showMessageDialog(null, "Se ha creado un PDF con la orden de compra", "Confirmación", JOptionPane.WARNING_MESSAGE);
			ventanaOrdenMatPrima.dispose();
		}
		//ORDEN MATERIA PRIMA> CANCELAR ORDEN
		else if(this.ventanaOrdenMatPrima!= null && e.getSource()==this.ventanaOrdenMatPrima.getBtnCancelar())
		{
			ventanaOrdenMatPrima.dispose();
		}
		//SELECTOR MATERIA PRIMA> SELECCIONAR MATERIA PRIMA ELEGIDA
		else if(this.ventanaSeleccionMatPrima!= null && e.getSource()==this.ventanaSeleccionMatPrima.getBtnSeleccionarMateriaPrima())
		{
			System.out.println("Se presiona boton de seleccion mat prima");
			String nomMatPrimaSeleccionada = this.ventanaSeleccionMatPrima.getModeloMateriasPrimas().getValueAt

					(ventanaSeleccionMatPrima.getTablaMateriasPrimas().getSelectedRow(), 0).toString();
			ventanaOrdenMatPrima.getTextFieldBuscadorMatPrima().setText(nomMatPrimaSeleccionada);
			ventanaSeleccionMatPrima.dispose();
		}
		//SELECTOR BUSCADOR PROVEEDOR> SELECCIONAR PROVEEDOR ELEGIDO
		else if(this.ventanaSeleccionProveedor!= null && e.getSource()==this.ventanaSeleccionProveedor.getBtnSeleccionarproveedor())
		{
			if (ventanaSeleccionProveedor.getComboBoxCategorias().isVisible() || ventanaSeleccionProveedor.getComboFiltroBusqueda().getSelectedItem().toString().compareTo("Todos")==0 && 
					ventanaSeleccionProveedor.getResultadoBusquedaProv().getSelectedRow()>=0 ){
				String nomProvSeleccionado = (String) ventanaSeleccionProveedor.getResultadoBusquedaProv().getValueAt

						(ventanaSeleccionProveedor.getResultadoBusquedaProv().getSelectedRow(), 0);
				System.out.println("prov seleccionado de taba " + nomProvSeleccionado);
				ventanaOrdenMatPrima.getComboListaProveedores().setSelectedItem(nomProvSeleccionado);
				ventanaSeleccionProveedor.dispose();
			}
			else if ( ventanaSeleccionProveedor.getTextAutoAcompleter().getItemSelected().toString().compareTo("")!=0 && 
					ventanaSeleccionProveedor.getComboFiltroBusqueda().getSelectedItem().toString().compareTo("Nombre")==0)
			{
				ventanaOrdenMatPrima.getComboListaProveedores().setSelectedItem

				(ventanaSeleccionProveedor.getTextAutoAcompleter().getItemSelected().toString());
				ventanaSeleccionProveedor.dispose();
			}		
		}
		//SELECTOR BUSCADOR PROVEEDOR> VER DETALLE DE PROVEEDOR SELECCIONADO
		else if(this.ventanaSeleccionProveedor!= null && e.getSource()==this.ventanaSeleccionProveedor.getBtnDetallesproveedor())
		{
			ProveedorDTO provSeleccionado;
			if(ventanaSeleccionProveedor.getResultadoBusquedaProv().getSelectedRow()>=0 && 

					ventanaSeleccionProveedor.getComboFiltroBusqueda().getSelectedItem().toString().compareTo("Nombre")!=0){
				int intRowSelected = ventanaSeleccionProveedor.getResultadoBusquedaProv().getSelectedRow();
				provSeleccionado = getProveedor(ventanaSeleccionProveedor.getResultadoBusquedaProv().getValueAt

						(intRowSelected, 0).toString());
				mostrarDetallesProv(provSeleccionado);
			}
			else if (ventanaSeleccionProveedor.getTextAutoAcompleter().getItemSelected().toString().compareTo("")!=0){
				provSeleccionado = getProveedor(ventanaSeleccionProveedor.getTextAutoAcompleter().getItemSelected

						().toString());
				mostrarDetallesProv(provSeleccionado);
			}
		}
		//SELECTOR BUSCADOR PROVEEDOR> FILTRAR TIPO DE BUSQUEDA
		else if(this.ventanaSeleccionProveedor!= null && e.getSource()==this.ventanaSeleccionProveedor.getComboFiltroBusqueda())
		{
			ventanaSeleccionProveedor.resetearModeloTablaResultados();
			ventanaSeleccionProveedor.borrarDetalles();
			accionParaCambioFiltro();
		}
		//SELECTOR BUSCADOR PROVEEDOR> FILTRAR POR CATEGORIA
		else if(this.ventanaSeleccionProveedor!= null && e.getSource()==this.ventanaSeleccionProveedor.getComboBoxCategorias())
		{
			ventanaSeleccionProveedor.borrarDetalles();
			accionParaCambioFiltroCategoria();
		}
		/////////////////////ABM CATEGORIAS///////////////////////////////
		//VENTANA CONFIGURACION > Abrir ventana abm categoria
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnGestionarCategorias())
		{
			ventanaGestionCategoria=new gestionCategoria();
			ventanaGestionCategoria.setVisible(true);
			ventanaGestionCategoria.getBtnAgregarCategoria().addActionListener(this);
			ventanaGestionCategoria.getBtnEditarCategoria().addActionListener(this);
			ventanaGestionCategoria.getBtnQuitarCategoria().addActionListener(this);
			ventanaGestionCategoria.getBtnGuardarModificacion().addActionListener(this);
			this.ventanaGestionCategoria.getTablacategorias().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent event) {
					ventanaGestionCategoria.limpiarCampos();
				}
			});
			cargarListadoCategorias();
		}
		///VENTANA ABM CATEGORIAS> Agregar Categoria
		else if(this.ventanaGestionCategoria!= null && e.getSource()==this.ventanaGestionCategoria.getBtnAgregarCategoria())
		{
			/*
			Alta de Categoria.
			Si es que no existe tanto en los eliminados como en los habilitados-Considerar el UPPERCASE y TRIM
				Se debe agrega a la bd, y se actualiza el modelo de la tabla
			Si es una categoria que ya existe con estado fueeliminado:false -Considerar el UPPERCASE y TRIM
				Debe arrojar la ventana de advertencia que ya existe
			Si es una categoria que ya existe con estado fueelminado:true - -Considerar el UPPERCASE y TRIM
				Debe modificar el campo fueeliminado y dejarlo en false.
			 */

			if (ventanaGestionCategoria.getTfDenominacion().getText().toString().compareTo("") != 0){
				String nvaDenominacion = ventanaGestionCategoria.getTfDenominacion().getText().toString().trim();
				if (categoria.contieneEnHabilitadas(nvaDenominacion)){ //
					JOptionPane.showMessageDialog(null, "Ya existe una categoría con ese nombre.", "Confirmación",JOptionPane.WARNING_MESSAGE);
				}
				else if (categoria.contieneEnRechazadas(nvaDenominacion)){
					//Integer intFilaCatSelecc = this.ventanaGestionCategoria.getTablacategorias().getSelectedRow(); 
					CategoriaDTO categoriaAmodificar = categoria.buscarCategoria(nvaDenominacion);
					categoria.actualizarEliminado(categoriaAmodificar.getIdCategoria(), false);
				}
				else{//No existe ni en eliminados ni habilitados
					CategoriaDTO nuevaCategoria = new CategoriaDTO(categoria.getNvoId(),nvaDenominacion, false);
					categoria.agregarCategoria(nuevaCategoria);
				}
				ventanaGestionCategoria.resetearModelo();
				ventanaGestionCategoria.limpiarCampos();
				cargarListadoCategorias();
			}
		}
		///VENTANA ABM CATEGORIAS> Guardar Modifcaciones
		else if(this.ventanaGestionCategoria!= null && e.getSource()==this.ventanaGestionCategoria.getBtnGuardarModificacion())
		{
			/*
			 * MODIFICACION de Categoria
				Si la denominacion ya existe en las categorias con estado fueeliminado:false
					Arrojar cartel de error de categoría existente.
				Si la denominacion ya existe en las categorias con estado fueeliminado:true
					Se debe actualizar el valor fueeliminado en false, desde la bd. y la q se abandona se pregunta si existe
					la relacion con materias primas o proveedores. si hay dependencia, solo pongo su estado en eliminado:true
					y sino dependen, directamente lo elimino de la bd.
				Si la denominacion no existe en categorias con estado fueeliminado:false ni en las fueeliminado:true
					Debo preguntar si la que estoy reemplazando no tienen relacionadas proveed o materias primas
						si tienen, debo actualizar esa categoria con fueeliminado:true y Agregar la nueva denominacion como una cat nueva.
						Si no tiene relacion con ninguna de las dos, directamente la elimino de la bd.	
			 */
			if (ventanaGestionCategoria.getTfDenominacion().getText().compareTo("")!=0){
				String viejaCategoria = ventanaGestionCategoria.getTablacategorias()
						.getValueAt(ventanaGestionCategoria.getTablacategorias().getSelectedRow(), 0).toString();
				String nvaDenominacionSinEsp = ventanaGestionCategoria.getTfDenominacion().getText().trim();
				if (categoria.contieneEnHabilitadas(nvaDenominacionSinEsp))
					JOptionPane.showMessageDialog(null, "Ya existe una categoría con ese nombre.", "Confirmación",JOptionPane.WARNING_MESSAGE);
				else if (categoria.contieneEnRechazadas(nvaDenominacionSinEsp)){
					categoria.actualizarEliminado( categoria.buscarCategoria(nvaDenominacionSinEsp).getIdCategoria(), false);						
					if ( proveedor.contienenCategoria(categoria.buscarCategoria(viejaCategoria).getDenominacion()) || materiasPrimas.contienenCategoria(viejaCategoria))
						categoria.actualizarEliminado( categoria.buscarCategoria(viejaCategoria).getIdCategoria(), true);
					else{
						categoria.quitarCategoria(categoria.buscarCategoria(viejaCategoria));
					}
				}
				else{
					if (proveedor.contienenCategoria(viejaCategoria) || materiasPrimas.contienenCategoria(viejaCategoria)){
						categoria.actualizarEliminado(categoria.buscarCategoria(viejaCategoria).getIdCategoria(), true);
					}
					else{
						categoria.quitarCategoria(categoria.buscarCategoria(viejaCategoria));
					}
					CategoriaDTO nvaCategoria = new CategoriaDTO(categoria.getNvoId(), nvaDenominacionSinEsp, false);
					categoria.agregarCategoria(nvaCategoria);
				}
				ventanaGestionCategoria.resetearModelo();
				cargarListadoCategorias();
			}
		}

		//VENTANA ABM CATEGORIAS> Borrar Categoria
		else if(this.ventanaGestionCategoria!= null && e.getSource()==this.ventanaGestionCategoria.getBtnQuitarCategoria())
		{
			/*
			 * BAJA de Categoria
				Si la Categoria tiene dependencia en la tabla de Proveedores o en la tabla de Materia Prima
					Debe setearse el valor fueeliminado:true de dicha categoria.
				Si no tiene relacion con alguna de esas dos tablas
					Se debe eliminar permanentemente de la bd
			 */
			Integer intCategoriaSelecc = ventanaGestionCategoria.getTablacategorias().getSelectedRow();
			if (intCategoriaSelecc>-1){
				CategoriaDTO categoriaAborrar = categoria.buscarCategoria(Integer.parseInt(ventanaGestionCategoria.getTablacategorias().getValueAt(intCategoriaSelecc,1).toString()));
				if ( proveedor.contienenCategoria(categoriaAborrar.getDenominacion().trim()) || materiasPrimas.contienenCategoria(categoriaAborrar.getDenominacion().trim())){
					categoria.actualizarEliminado(categoriaAborrar.getIdCategoria(), true);
				}
				else{
					categoria.quitarCategoria(categoriaAborrar);
				}
				ventanaGestionCategoria.resetearModelo();
				cargarListadoCategorias();
			}
		}
		//VENTANA ABM CATEGORIAS> Habilitar edicion Categoria
		else if(this.ventanaGestionCategoria!= null && e.getSource()==this.ventanaGestionCategoria.getBtnEditarCategoria())
		{
			ventanaGestionCategoria.getTfDenominacion().setText(ventanaGestionCategoria.getTablacategorias()
					.getValueAt(ventanaGestionCategoria.getTablacategorias().getSelectedRow(), 0).toString());
			ventanaGestionCategoria.ocultarBtnGuardarMod(false);
		}
		////
		//VENTANA CONFIGURACION Abrir alta proveedor
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnAgregarProveedor())
		{
			ventanaAgregarProveedor=new proveedorAlta();
			ventanaAgregarProveedor.getBtnRegistrar().addActionListener(this);
			ventanaAgregarProveedor.getBtnQuitarcateg().addActionListener(this);
			ventanaAgregarProveedor.getBtnaddCategoria().addActionListener(this);
			ventanaAgregarProveedor.setVisible(true);

			ventanaAgregarProveedor.cargarCategorias(categoria.obtenerCategorias());
		}
		//VENTANA  ALTA PROVEEDOR Agregar categoria elegida
		else if (this.ventanaAgregarProveedor!= null && e.getSource()==this.ventanaAgregarProveedor.getBtnaddCategoria())
		{
			//agregar fila en la tabla, incluir id.
			if (ventanaAgregarProveedor.getComboBoxCategorias().getSelectedItem().toString().compareTo("(Agregar Categorias)")!= 0){
				CategoriaDTO catSelecc = categoria.buscarCategoria(ventanaAgregarProveedor.getComboBoxCategorias().getSelectedItem().toString());
				ventanaAgregarProveedor.agregarCategoria(catSelecc);
				ventanaAgregarProveedor.getComboBoxCategorias().removeItem(catSelecc.getDenominacion());
			}
		}
		//VENTANA  ALTA PROVEEDOR Quitar categoria
		else if (this.ventanaAgregarProveedor!= null && e.getSource()==this.ventanaAgregarProveedor.getBtnQuitarcateg())
		{
			Integer indiceCatSelecc = ventanaAgregarProveedor.getTablaCategorias().getSelectedRow();
			if (indiceCatSelecc>-1){
				ventanaAgregarProveedor.getComboBoxCategorias().addItem(ventanaAgregarProveedor.getTablaCategorias().getValueAt(indiceCatSelecc, 0).toString());
				ventanaAgregarProveedor.getModeloCategorias().removeRow(indiceCatSelecc);
				ventanaAgregarProveedor.getTablaCategorias().setModel(ventanaAgregarProveedor.getModeloCategorias());
			}
		}
		//VENTANA ALTA PROVEEDOR Btn Registrar Proveedor
		else if (this.ventanaAgregarProveedor!= null && e.getSource()==this.ventanaAgregarProveedor.getBtnRegistrar())
		{	//chequea q el nom de proov no sea repetido
			if (proveedor.buscarProveedor(ventanaAgregarProveedor.getTfDenominacion().getText().trim())==null && ventanaAgregarProveedor.getTfDenominacion().getText().compareTo("")!=0 ){ 
				ProveedorDTO nuevo= new ProveedorDTO();
				nuevo.setId(proveedor.ultimoProveedor()+1);
				nuevo.setNombre(ventanaAgregarProveedor.getTfDenominacion().getText());
				nuevo.setNombrecontacto(ventanaAgregarProveedor.getTfNombreContacto().getText());
				nuevo.setComentario(ventanaAgregarProveedor.getTfComentario().getText());
				nuevo.setFueeliminado(false);
				//agregar la lista de categorias
				//nuevo.setCategoria(categoria.pasarDeStringAArray(ventanaAgregarProveedor.getTfCategoria().getText()));
				ArrayList<CategoriaDTO> categorias = new ArrayList<CategoriaDTO>();
				for (int i = 0; i < this.ventanaAgregarProveedor.getTablaCategorias().getRowCount();i++){
					System.out.println(categoria.buscarCategoria(ventanaAgregarProveedor.getTablaCategorias().getValueAt(i, 0).toString()).getDenominacion());
					categorias.add(categoria.buscarCategoria(ventanaAgregarProveedor.getTablaCategorias().getValueAt(i, 0).toString()));
				}
				nuevo.setCategoria(categorias);
				nuevo.setDireccion(ventanaAgregarProveedor.getTfDireccion().getText());
				nuevo.setEmail(ventanaAgregarProveedor.getTfEmail().getText());
				nuevo.setTelefono(ventanaAgregarProveedor.getTfTelefono().getText());
				proveedor.agregarProveedor(nuevo);
				JOptionPane.showMessageDialog(null, "Se ha registrado con éxito el proveedor: " + nuevo.getNombre(), "Confirmación",JOptionPane.WARNING_MESSAGE);
				ventanaAgregarProveedor.dispose();
			}
			else
				JOptionPane.showMessageDialog(null, "El nombre de proveedor ya existe, indique otro para continuar", "Confirmación",JOptionPane.WARNING_MESSAGE);

		}
		//VENTANA CONFIGURACIONES > ABRIR Baja Modif de Proveedores
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnEditarProveedor())
		{
			ventanaEditarProveedor=new proveedorBajaModificacion(this);
			llenarTablaProveedor();
			ventanaEditarProveedor.getBtnQuitar().addActionListener(this);
			ventanaEditarProveedor.getBtnGuardar().addActionListener(this);
			ventanaEditarProveedor.getBtnQuitarcat().addActionListener(this);
			ventanaEditarProveedor.getBtnAddcategoria().addActionListener(this);
			ventanaEditarProveedor.setVisible(true);
		}
		//VENTANA BAJA MODIF PROVEEDOR> borrar proveedor.
		else if (this.ventanaEditarProveedor!= null && e.getSource()==this.ventanaEditarProveedor.getBtnQuitar())
		{
			/*
			 * BAJA de PROVEEDOR
				Si el PROVEEDOR tiene dependencia en la tabla de ordenes de pedido
					Debe setearse el valor fueeliminado:true de dicha categoria.
				Si no tiene relacion con alguna de esas dos tablas
					Se debe eliminar permanentemente de la bd
			 */
			Integer indiceFilaProvSelecc = ventanaEditarProveedor.getTableProveedor().getSelectedRow();
			if (indiceFilaProvSelecc>-1){
				ProveedorDTO aux= this.proveedor.buscarProveedor(ventanaEditarProveedor.getTfDenominacion().getText());
				if (ordenesMatPrimas.dependeDe(aux))
					proveedor.actualizarEliminado(aux.getId(), true);
				else{
					proveedor.quitarProveedor(aux);
				}
				llenarTablaProveedor();
				ventanaEditarProveedor.limpiarCampos();
				//limpiar los otros campos.
			}
		}
		////VENTANA BAJA MODIF PROVEEDOR> Modificar un proveedor
		else if (this.ventanaEditarProveedor!= null && e.getSource()==this.ventanaEditarProveedor.getBtnGuardar())
		{
			/*
			 * MODIFICACION de PROVEEDOR
				Si la denominacion ya existe en las PROVEEDOR con estado fueeliminado:false
					Arrojar cartel de error de categoría existente.
				Si la denominacion ya existe en las PROVEEDOR con estado fueeliminado:true
					Se debe actualizar el valor fueeliminado en false, desde la bd. y la q se abandona se pregunta si existe
					la relacion con ordenes mat primas. si hay dependencia, solo pongo su estado en eliminado:true
					y sino dependen, directamente lo elimino de la bd.
				Si la denominacion no existe en PROVEEDOR con estado fueeliminado:false ni en las fueeliminado:true
					Debo preguntar si la que estoy reemplazando no tienen relacionadas con ordenes mat primas
						si tienen, debo actualizar esa PROVEEDOR con fueeliminado:true y Agregar la nueva PROVEEDOR como una PROVEEDOR nueva.
						Si no tiene relacion, directamente la elimino de la bd.	
			 */
			Integer filaProvSelecc = ventanaEditarProveedor.getTableProveedor().getSelectedRow();
			if (filaProvSelecc >-1){
				ProveedorDTO provSeleccionado = proveedor.buscarProveedor(ventanaEditarProveedor.getTableProveedor().getValueAt(filaProvSelecc, 0).toString());
				if (ventanaEditarProveedor.getTfDenominacion().getText().trim().compareTo(provSeleccionado.getNombre())==0){
					provSeleccionado = guardarDatosProveedor(provSeleccionado);
					proveedor.actualizarDatos(provSeleccionado);
				}
				else{
					String nvaDenominacion = ventanaEditarProveedor.getTfDenominacion().getText().trim();
					if (proveedor.existeEnHabilitados(nvaDenominacion)){
						JOptionPane.showMessageDialog(null, "El nombre de proveedor ya existe, indique otro para continuar", "Confirmación",JOptionPane.WARNING_MESSAGE);
					}
					else if (proveedor.existeEnEliminados(nvaDenominacion)){
						//El nombre que ingreso para el proveedor, se encuentra en el historial de proveedores, desea rehabiltarlo?
						//Si > reahabilitar
						//No? > Modifique el nombre del proveedor y vuelva a intentarlo
						int respuesta = JOptionPane.showConfirmDialog( ventanaEditarProveedor, 
								"El nombre que ingreso para el proveedor, se encuentra en el historial de proveedores, desea rehabiltarlo?"
								, "Seleccione una opción", JOptionPane.YES_NO_OPTION);
						if (respuesta==0){
							Integer idProvARehabilitar = proveedor.buscarProveedor(nvaDenominacion).getId();
							proveedor.actualizarEliminado(idProvARehabilitar, false);
							if (ordenesMatPrimas.dependeDe(provSeleccionado))
								proveedor.actualizarEliminado(provSeleccionado.getId(), true);
							else{
								proveedor.quitarProveedor(provSeleccionado);
							}
						}
						else{
							ventanaEditarProveedor.getTfDenominacion().setText("");
						}
					}
					else{
						if (ordenesMatPrimas.dependeDe(provSeleccionado))
							proveedor.actualizarEliminado(provSeleccionado.getId(), true);
						else{	
							proveedor.quitarProveedor(provSeleccionado);
						}
						ProveedorDTO nuevoProv = new ProveedorDTO();
						nuevoProv.setNombre(nvaDenominacion);
						nuevoProv = guardarDatosProveedor(nuevoProv);
						proveedor.agregarProveedor(nuevoProv);
					}
				}
			}
			llenarTablaProveedor();
			ventanaEditarProveedor.limpiarCampos();
		}
		//VENTANA BAJA MODIF PROVEEDOR> Agregar Categoria
		else if (this.ventanaEditarProveedor!= null && e.getSource()==this.ventanaEditarProveedor.getBtnAddcategoria())
		{
			Integer intFilaProvSelecc = ventanaEditarProveedor.getTableProveedor().getSelectedRow();
			if (intFilaProvSelecc > -1){
				CategoriaDTO addedCat = categoria.buscarCategoria((ventanaEditarProveedor.getComboBoxCategorias().getSelectedItem().toString().trim()));
				ventanaEditarProveedor.getModeloCategorias().addRow(new Object[] {addedCat.getDenominacion(),addedCat.getIdCategoria()});
				ventanaEditarProveedor.getComboBoxCategorias().removeItem(addedCat.getDenominacion());
			}
		}
		//VENTANA BAJA MODIF PROVEEDOR> Quitar Categoria
		else if (this.ventanaEditarProveedor!= null && e.getSource()==this.ventanaEditarProveedor.getBtnQuitarcat())
		{
			Integer intFilaCatSelecc = ventanaEditarProveedor.getTablaCategorias().getSelectedRow();
			if (intFilaCatSelecc > -1){
				ventanaEditarProveedor.getComboBoxCategorias().addItem(ventanaEditarProveedor.getTablaCategorias().getValueAt(intFilaCatSelecc, 0).toString());
				ventanaEditarProveedor.getModeloCategorias().removeRow(intFilaCatSelecc);
			}
		}
		//VENTANA CONFIGURACION> Abrir ventana alta materia prima
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnAgregarMatPrima())
		{
			ventanaAgregarMatPrima=new matPrimaAlta(this);
			ventanaAgregarMatPrima.setVisible(true);
			ventanaAgregarMatPrima.getBtnAgregarMatPrima().addActionListener(this);
			ventanaAgregarMatPrima.getBtnGuardar().addActionListener(this);
			ventanaAgregarMatPrima.getButtonBorrarCat().addActionListener(this);
			ventanaAgregarMatPrima.getButtonEditarMP().addActionListener(this);
			ventanaAgregarMatPrima.cargarOpcCategorias();
			ventanaAgregarMatPrima.cargarMateriasPrimas();

		}
		//VENTANA ALTA MAT PRIMA> Guardar Nva Mat Prima
		else if (this.ventanaAgregarMatPrima!= null && e.getSource()==this.ventanaAgregarMatPrima.getBtnAgregarMatPrima())
		{
			/*
			 * Si el nombre de la materia prima no existe
			 * 	agregar directamente a la bd
			 * Si el nombre de la mat prima existe en las mat primas habilitadas
			 * 	arrojo cartel que el nombre ya existe, y se debe elegir otro
			 * Si el nombre de la mat prima existe en las mat primas borradas
			 *	actualizo el campo de fueeliminado a false de dicha mat prima
			 *y tmb actualizo segun la cat nva designada
			 */
			
			String nomNvaMatPrima = ventanaAgregarMatPrima.getTfNombre().getText().trim();
			if (materiasPrimas.contieneEnHabilitadas(nomNvaMatPrima)){
				JOptionPane.showMessageDialog(null, "El nombre de la materia prima ya existe, indique otro para continuar", "Confirmación",JOptionPane.WARNING_MESSAGE);
			}
			else if (materiasPrimas.contieneEnRechazadas(nomNvaMatPrima)){
				///actualizado fueeliminado false
				//actualizar categoria segun seleccion del cliente.
				MateriaPrimaDTO rehabilitarMatPrima =  materiasPrimas.buscarMatPrima(nomNvaMatPrima);
				rehabilitarMatPrima.setFueeliminado(false);
				rehabilitarMatPrima.setCategoria( categoria.buscarCategoria( ventanaAgregarMatPrima.getComboBoxCategoria().getSelectedItem().toString() ) );
				materiasPrimas.actualizarMatPrima(rehabilitarMatPrima);//metodo busca x id recibido y reemplaza 
			}
			else{
				MateriaPrimaDTO nvaMatPrima =  new MateriaPrimaDTO(materiasPrimas.getNvoId(),nomNvaMatPrima,
						categoria.buscarCategoria(ventanaAgregarMatPrima.getComboBoxCategoria().getSelectedItem().toString()),false);
				materiasPrimas.agregarMatPrima(nvaMatPrima);
			}
			ventanaAgregarMatPrima.cargarMateriasPrimas();
		}
		//VENTANA ALTA MAT PRIMA> Borrar Materia Prima
		else if (this.ventanaAgregarMatPrima!= null && e.getSource()==this.ventanaAgregarMatPrima.getButtonBorrarMatPrima())
		{
			//directamente toco el valor de fue eliminado o no
			Integer intFilaCatSelecc = ventanaAgregarMatPrima.getTablaMateriasPrimas().getSelectedRow();
			if (intFilaCatSelecc > -1){
				MateriaPrimaDTO mpAborar = materiasPrimas.buscarMatPrima(ventanaAgregarMatPrima.getTablaMateriasPrimas().getValueAt(intFilaCatSelecc, 0).toString());
				mpAborar.setFueeliminado(true);
				materiasPrimas.actualizarMatPrima(mpAborar);
			}
			ventanaAgregarMatPrima.cargarMateriasPrimas();
		}
		//VENTANA ALTA MAT PRIMA> Editar Mat Prima
		else if (this.ventanaAgregarMatPrima!= null && e.getSource()==this.ventanaAgregarMatPrima.getButtonEditarMP())
		{
			Integer indice = ventanaAgregarMatPrima.getTablaMateriasPrimas().getSelectedRow();
			if (indice > -1){
				System.out.println(ventanaAgregarMatPrima.getTablaMateriasPrimas().getValueAt(indice, 0).toString().trim());
				MateriaPrimaDTO matSelecc = materiasPrimas.buscarMatPrima(ventanaAgregarMatPrima.getTablaMateriasPrimas().getValueAt(indice, 0).toString().trim());
				ventanaAgregarMatPrima.getTfNombre().setText(matSelecc.getNombre().replace("_", " ").trim());
				ventanaAgregarMatPrima.getComboBoxCategoria().setSelectedItem(matSelecc.getCategoria().getDenominacion());
			}
			
		}
		//VENTANA ALTA MAT PRIMA> Guardar modificaciones
		else if (this.ventanaAgregarMatPrima!= null && e.getSource()==this.ventanaAgregarMatPrima.getBtnGuardar())
		{
			Integer intFilaCatSelecc = ventanaAgregarMatPrima.getTablaMateriasPrimas().getSelectedRow();
			if (intFilaCatSelecc > -1){
				MateriaPrimaDTO mpAmodificar = materiasPrimas.buscarMatPrima(ventanaAgregarMatPrima.getTablaMateriasPrimas().getValueAt(intFilaCatSelecc, 0).toString());
				mpAmodificar.setNombre(ventanaAgregarMatPrima.getTfNombre().getText().trim());
				mpAmodificar.setCategoria(categoria.buscarCategoria(ventanaAgregarMatPrima.getComboBoxCategoria().getSelectedItem().toString()));
				materiasPrimas.actualizarMatPrima(mpAmodificar);
				ventanaAgregarMatPrima.cargarMateriasPrimas();
			}
		}
		//CONFIGURACION> ABRIR VENTANA BAJA MODIF MAT PRIMA
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnEditarMatPrima())
		{
			ventanaEditarMatPrima=new matPrimaBajaModificacion(this);
			ventanaEditarMatPrima.setVisible(true);
			
			ventanaEditarMatPrima.getBtnEditar().addActionListener(this);
			ventanaEditarMatPrima.getBtnEliminar().addActionListener(this);
			ventanaEditarMatPrima.getBtnGuardar().addActionListener(this);
			ventanaEditarMatPrima.getBtnGuardarUnCambio().addActionListener(this);
			ventanaEditarMatPrima.getComboBoxCategoriasFiltro().addActionListener(this);
			ventanaEditarMatPrima.getComboBoxCategoriaUnObjeto().addActionListener(this);
			ventanaEditarMatPrima.getTfDenominacion().addActionListener(this);

			ventanaEditarMatPrima.cargarCategoriasFiltro();
			ventanaEditarMatPrima.filtrarTabla(ventanaEditarMatPrima.getComboBoxCategoriasFiltro().getSelectedItem().toString());

		}
		//VENTANA MODFI BAJA MAT PRIMA> Cambiar Filtro Mp
		else if (this.ventanaEditarMatPrima!= null && e.getSource()==this.ventanaEditarMatPrima.getComboBoxCategoriasFiltro())
		{
			ventanaEditarMatPrima.filtrarTabla(ventanaEditarMatPrima.getComboBoxCategoriasFiltro().getSelectedItem().toString());
		}
		//VENTANA MODFI BAJA MAT PRIMA> Boton Editar Mp
		else if (this.ventanaEditarMatPrima!= null && e.getSource()==this.ventanaEditarMatPrima.getBtnEditar())
		{
			Integer intFilaCatSelecc = ventanaEditarMatPrima.getTableMatPrimas().getSelectedRow();
			if (intFilaCatSelecc > -1){
				ventanaEditarMatPrima.getTfDenominacion().setText(ventanaEditarMatPrima.getTableMatPrimas().getValueAt(intFilaCatSelecc, 0).toString());
				ventanaEditarMatPrima.cargarCategorias(ventanaEditarMatPrima.getTableMatPrimas().getValueAt(intFilaCatSelecc, 1).toString());
			}
		}
		//VENTANA MODFI BAJA MAT PRIMA> Guardar modificaciones
		else if (this.ventanaEditarMatPrima!= null && e.getSource()==this.ventanaEditarMatPrima.getBtnGuardarUnCambio())
		{
			Integer intFilaCatSelecc = ventanaEditarMatPrima.getTableMatPrimas().getSelectedRow();
			if (intFilaCatSelecc > -1){
				MateriaPrimaDTO mpAmodificar = materiasPrimas.buscarMatPrima(ventanaEditarMatPrima.getTableMatPrimas().getValueAt(intFilaCatSelecc, 0).toString());
				mpAmodificar.setNombre(ventanaEditarMatPrima.getTfDenominacion().getText().trim());
				mpAmodificar.setCategoria(categoria.buscarCategoria(ventanaEditarMatPrima.getComboBoxCategorias().getSelectedItem().toString()));
				materiasPrimas.actualizarMatPrima(mpAmodificar);
				ventanaEditarMatPrima.getComboBoxCategoriasFiltro().setSelectedItem(mpAmodificar.getCategoria().getDenominacion().trim());
				ventanaEditarMatPrima.filtrarTabla(mpAmodificar.getCategoria().getDenominacion().trim());
				ventanaEditarMatPrima.getTfDenominacion().setText("");
				ventanaEditarMatPrima.getComboBoxCategorias().removeAllItems();
			}	
		}
		//VENTANA MODFI BAJA MAT PRIMA> Boton Borrar MP
		else if (this.ventanaEditarMatPrima!= null && e.getSource()==this.ventanaEditarMatPrima.getBtnEliminar())
		{
			Integer intFilaCatSelecc = ventanaEditarMatPrima.getTableMatPrimas().getSelectedRow();
			if (intFilaCatSelecc > -1){
				MateriaPrimaDTO matABorrar = materiasPrimas.buscarMatPrima(ventanaEditarMatPrima.getTableMatPrimas().getValueAt(intFilaCatSelecc, 0).toString());
				Component a= new Component() {};
				int opcion = JOptionPane.showConfirmDialog(a, "¿Confirma la eliminación de la materia prima: " + matABorrar.getNombre().trim() + "?", "Seleccione una opción", JOptionPane.YES_NO_OPTION);
				if (opcion==0){
					matABorrar.setFueeliminado(true);
					materiasPrimas.actualizarMatPrima(matABorrar);
					ventanaEditarMatPrima.getModeloMatPrima().removeRow(intFilaCatSelecc);
					ventanaEditarMatPrima.getTableMatPrimas().setModel(ventanaEditarMatPrima.getModeloMatPrima());
				}
			}
		}

		///////////////////////////////////FIN//////CodigoJuliet/////////////////////////////////////////////////
		//Ventana de reportes ESTADISTICOS
		else if(this.ventanaMenuReportes!= null && e.getSource()==this.ventanaMenuReportes.getBtnConsultaestadisticas())
		{
			ventanaMenuReportes.dispose();
			ventanaReportesEstadistica=new consultoEstadistica(ventana, this);
			ventanaReportesEstadistica.getBtnBuscar().addActionListener(this);
			ventanaReportesEstadistica.getBtnCalendarioFin().addActionListener(this);
			ventanaReportesEstadistica.getBtnCalendarioInicio().addActionListener(this);
		 	ventanaReportesEstadistica.getBtnEnviarPorEmail().addActionListener(this);
			ventanaReportesEstadistica.getBtnImprimir().addActionListener(this);
			ventanaReportesEstadistica.getCbEstadisticas().addActionListener(this);
			ventanaReportesEstadistica.getTop5().addActionListener(this);
			ventanaReportesEstadistica.getVerTodo().addActionListener(this);
			ventanaReportesEstadistica.getButtonGroup().setSelected(ventanaReportesEstadistica.getTop5().getModel(), true);
			ventanaReportesEstadistica.setVisible(true);
		}
		//Seleccionar tipos de Estadisticas
		else if(this.ventanaReportesEstadistica!= null && e.getSource()==this.ventanaReportesEstadistica.getBtnEnviarPorEmail())
		{
			JOptionPane.showMessageDialog(null, "Se ha enviado por email", "Confirmación",JOptionPane.WARNING_MESSAGE);
		}
		
		else if(this.ventanaReportesEstadistica!= null && e.getSource()==this.ventanaReportesEstadistica.getCbEstadisticas())
		{
			String tipoProducto = (String) ventanaReportesEstadistica.getCbEstadisticas().getSelectedItem().toString();
			if (tipoProducto.compareTo("(Seleccione una estadistica)")==0)
			{
				ventanaReportesEstadistica.getTfOcultaRangoFechas().setVisible(true);
				ventanaReportesEstadistica.getTfOcultarTop().setVisible(true);
				ventanaReportesEstadistica.getTfFechaInicio().setText("");
				ventanaReportesEstadistica.getTfFechaFin().setText("");
			}
			else if(tipoProducto.compareTo("Productos mas comprados")==0 || tipoProducto.compareTo("Productos menos comprados")==0 )
			{
				ventanaReportesEstadistica.getTfOcultaRangoFechas().setVisible(false);
				ventanaReportesEstadistica.getTfOcultarTop().setVisible(false);
				ventanaReportesEstadistica.getButtonGroup().setSelected(ventanaReportesEstadistica.getTop5().getModel(), true);
				ventanaReportesEstadistica.getTfFechaInicio().setText("");
				ventanaReportesEstadistica.getTfFechaFin().setText("");
			}
			else if(tipoProducto.compareTo("Ofertas mas compradas")==0 || tipoProducto.compareTo("Ofertas menos compradas")==0|| tipoProducto.compareTo("Cliente mas comprador")==0 )
			{
				ventanaReportesEstadistica.getTfOcultaRangoFechas().setVisible(false);
				ventanaReportesEstadistica.getTfOcultarTop().setVisible(true);
				ventanaReportesEstadistica.getTfFechaInicio().setText("");
				ventanaReportesEstadistica.getTfFechaFin().setText("");
			}
		}
			
		//establecer fecha de inicio y fin para ESTADISTICAS
		else if(this.ventanaReportesEstadistica!= null && (e.getSource()==this.ventanaReportesEstadistica.getBtnCalendarioInicio() ||e.getSource()==this.ventanaReportesEstadistica.getBtnCalendarioFin() ))
		{
			if(e.getSource()==this.ventanaReportesEstadistica.getBtnCalendarioInicio())
			{
				ventanaCalendario=new calendario(ventanaReportesEstadistica);
				ventanaCalendario.setTitle("Fecha de Inicio");
				ventanaCalendario.setVisible(true);
			}
			else
			{
				ventanaCalendario=new calendario(ventanaReportesEstadistica);
				ventanaCalendario.setTitle("Fecha de Fin");
				ventanaCalendario.setVisible(true);
			}	
		}
		//Generar el pdf  al presionar el boton imprimir
		else if(this.ventanaReportesEstadistica!= null && e.getSource()==this.ventanaReportesEstadistica.getBtnImprimir())
		{
			JOptionPane.showMessageDialog(null, "Se generó y envió a imprimir la consulta en formato .pdf");
		}
		
		else if(e.getSource()==this.ventana.getBtnConfiguraciones())
		{
			ventanaConfiguraciones=new opcionesDeConfiguracion();
			ventanaConfiguraciones.setVisible(true);
			ventanaConfiguraciones.getBtnAgregarProducto().addActionListener(this);
			ventanaConfiguraciones.getBtnAgregarProveedor().addActionListener(this);
			ventanaConfiguraciones.getBtnAgregarRepartidor().addActionListener(this);
			ventanaConfiguraciones.getBtnEditarProducto().addActionListener(this);
			ventanaConfiguraciones.getBtnEditarProveedor().addActionListener(this);
			ventanaConfiguraciones.getBtnEditarRepartidor().addActionListener(this);
			ventanaConfiguraciones.getBtnAgregarMatPrima().addActionListener(this);
			ventanaConfiguraciones.getBtnEditarMatPrima().addActionListener(this);
			ventanaConfiguraciones.getBtnAgregarPromocion().addActionListener(this);
			ventanaConfiguraciones.getBtnEditarPromocion().addActionListener(this);
			ventanaConfiguraciones.getBtnEditarCliente().addActionListener(this);
			ventanaConfiguraciones.getBtnGestionarCategorias().addActionListener(this);
		}
		//al ordenar.... crea un pedido y llama a la ventana para seleccionar un cliente
		else if(this.ventanaPedido!= null && e.getSource()==this.ventanaPedido.getBtnOrdenar())
		{
			Calendar c1 = GregorianCalendar.getInstance();
			String fecha=(c1.getTime().getDate()+"-"+(c1.getTime().getMonth()+1)+"-"+(c1.getTime().getYear()+1900));
			String hora=c1.getTime().getHours()+":"+c1.getTime().getMinutes();
			PedidoDTO nuevoPedido=new PedidoDTO();
			nuevoPedido.setIdpedido(this.pedido.ultimoPedido()+1);
			nuevoPedido.setNumPedido(this.pedido.ultimoPedidoFecha(fecha)+1);
			nuevoPedido.set_estado("solicitado");
			nuevoPedido.setTotal(Integer.parseInt(this.ventanaPedido.getTfTotal().getText()));
			nuevoPedido.set_comanda(nuevoPedido.getIdpedido());
			nuevoPedido.set_ticket(nuevoPedido.getIdpedido());
			//aca
			nuevoPedido.setProductos(generarListaItems());
			nuevoPedido.setOfertas(generarListaOfertas());
			nuevoPedido.setFecha(fecha);
			nuevoPedido.setHora(hora);
			nuevoPedido.setFueeliminado(false);
			
			this.ventanaCliente=new seleccionDeCliente(this,nuevoPedido);
			this.ventanaCliente.getBtnAgregarCliente().addActionListener(this);
			this.ventanaCliente.getBtnEditarCliente().addActionListener(this);
			this.ventanaPedido.dispose();
			this.ventanaCliente.setVisible(true);			
		}
		else if (this.ventanaPedido!= null && e.getSource()==this.ventanaPedido.getBtnVerEmpanadas())
		{
			ventanamenu=new pedidoMenu(this,ventanaPedido);
			ventanamenu.setTitle("empanada");
			ventanamenu.setBounds(400, 100, 347, 540);
			ventanamenu.getBtnSeleccionar().addActionListener(this);
			llenarTablaMenu("empanada");
			ventanamenu.setVisible(true);

		}
		else if (this.ventanaPedido!= null && e.getSource()==this.ventanaPedido.getBtnVerPizzas())
		{
			ventanamenu=new pedidoMenu(this,ventanaPedido);
			ventanamenu.setTitle("pizza");
			ventanamenu.setBounds(400, 100, 347, 540);
			ventanamenu.getBtnSeleccionar().addActionListener(this);
			llenarTablaMenu("pizza");
			ventanamenu.setVisible(true);

		}
		else if (this.ventanaPedido!= null && e.getSource()==this.ventanaPedido.getBtnVerOtros())
		{
			ventanamenu=new pedidoMenu(this,ventanaPedido);
			ventanamenu.setTitle("otros");
			ventanamenu.setBounds(400, 100, 347, 540);
			ventanamenu.getBtnSeleccionar().addActionListener(this);
			llenarTablaMenu("otros");
			ventanamenu.setVisible(true);

		}
		else if (this.ventanaPedido!= null && e.getSource()==this.ventanaPedido.getBtnVerPromociones())
		{
			ventanamenu=new pedidoMenu(this,ventanaPedido);
			ventanamenu.setTitle("promocion");
			ventanamenu.setBounds(400, 100, 670, 540);
			ventanamenu.getBtnSeleccionar().addActionListener(this);
			llenarTablaMenuPromociones();
			ventanamenu.setVisible(true);

		}
		else if (this.ventanamenu!= null && e.getSource()==this.ventanamenu.getBtnSeleccionar())
		{
			switch(ventanamenu.getTitle())
			{
			case("empanada"):
			{
				ventanaPedido.getTfAgregarEmpanada().setText(ventanamenu.getTable().getValueAt(ventanamenu.getTable().getSelectedRow(), 0).toString());
				ventanaPedido.getTfPrecioUniEmpanada().setText(ventanamenu.getTable().getValueAt(ventanamenu.getTable().getSelectedRow(), 1).toString());
				break;
			}
			case("pizza"):
			{
				ventanaPedido.getTfAgregarPizza().setText(ventanamenu.getTable().getValueAt(ventanamenu.getTable().getSelectedRow(), 0).toString());
				ventanaPedido.getTfPrecioUniPizza().setText(ventanamenu.getTable().getValueAt(ventanamenu.getTable().getSelectedRow(), 1).toString());
				break;
			}
			case("otros"):
			{
				ventanaPedido.getTfAgregarOtroProducto().setText(ventanamenu.getTable().getValueAt(ventanamenu.getTable().getSelectedRow(), 0).toString());
				ventanaPedido.getTfPrecioUniOtro().setText(ventanamenu.getTable().getValueAt(ventanamenu.getTable().getSelectedRow(), 1).toString());
				break;
			}
			case("promocion"):
			{
				ventanaPedido.getTfAgregarPromocion().setText(ventanamenu.getTable().getValueAt(ventanamenu.getTable().getSelectedRow(), 0).toString());
				ventanaPedido.getTfPrecioUniPromocion().setText(ventanamenu.getTable().getValueAt(ventanamenu.getTable().getSelectedRow(), 1).toString());
				break;
			}
			}
			ventanamenu.dispose();
		}
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnAgregarProducto())
		{
			ventanaAgregarProducto=new productoAlta();
			llenarTablaProductos();
			ventanaAgregarProducto.getBtnAgregarProducto().addActionListener(this);
			ventanaAgregarProducto.setVisible(true);
		}
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnAgregarRepartidor())
		{
			ventanaAgregarRepartidor=new repartidorAlta();
			ventanaAgregarRepartidor.getBtnCalendario().addActionListener(this);
			ventanaAgregarRepartidor.getBtnRegistrar().addActionListener(this);
			ventanaAgregarRepartidor.setVisible(true);
		}

		//dar de alta a un producto
		else if (this.ventanaAgregarProducto!= null && e.getSource()==this.ventanaAgregarProducto.getBtnAgregarProducto())
		{
			if(!compararProducto(ventanaAgregarProducto.getTfDenominacion().getText().toString(),ventanaAgregarProducto.getCbTipo().getSelectedItem().toString()))
			{
				ProductoDTO nuevo=new ProductoDTO();
				nuevo.setIdproducto(this.producto.ultimoProducto()+1);
				nuevo.setNombre(ventanaAgregarProducto.getTfDenominacion().getText().toString());
				nuevo.setPrecio(Integer.parseInt(ventanaAgregarProducto.getTfPrecio().getText().toString()));
				nuevo.setFueeliminado(false);
				nuevo.setTipo(ventanaAgregarProducto.getCbTipo().getSelectedItem().toString());
				producto.agregarProducto(nuevo);
				llenarTablaProductos();
				ventanaAgregarProducto.getTfDenominacion().setText("");
				ventanaAgregarProducto.getTfPrecio().setText("");
				ventanaAgregarProducto.getCbTipo().setSelectedIndex(0);
				JOptionPane.showMessageDialog(null, "Se ha agregado exitosamente un nuevo producto al final de la lista");
			}
			else
				JOptionPane.showMessageDialog(null, "Error, ya exite este producto, para cambiarle el precio ingrese a la ventana de edicion de productos");
		}

		//acciones asocidas a editar productos
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnEditarProducto())
		{
			ventanaEditarProducto=new productoBajaModificacion(this);
			ventanaEditarProducto=new productoBajaModificacion(this);
			ventanaEditarProducto.getBtnGuardar().addActionListener(this);
			ventanaEditarProducto.getBtnQuitar().addActionListener(this);
			ventanaEditarProducto.getCbTipoTabla().addActionListener(this);
			llenarTablaProductosEditados("todos");
			ventanaEditarProducto.setVisible(true);
		}
		//llenar tabla de productos en relacion al tipo  de producto seleccionado
		else if(this.ventanaEditarProducto!= null && e.getSource()==this.ventanaEditarProducto.getCbTipoTabla())
		{
			String tipoProducto = (String) ventanaEditarProducto.getCbTipoTabla().getSelectedItem().toString();
			if (tipoProducto!= "")
			{
				llenarTablaProductosEditados(tipoProducto);
			}
		}
		//quitar productos
		else if(this.ventanaEditarProducto!= null && e.getSource()==this.ventanaEditarProducto.getBtnQuitar())
		{
			Component a= new Component() {};
			int opcion = JOptionPane.showConfirmDialog(a, "¿Esta seguro que desea quitar el producto de la lista?", "Seleccione una opción", JOptionPane.YES_NO_OPTION);
			if( opcion==0)
			{
				ProductoDTO nuevo= this.producto.buscarProductoPorNombre(ventanaEditarProducto.getTable().getValueAt(this.ventanaEditarProducto.getTable().getSelectedRow(), 0).toString());
				this.producto.quitarProducto(nuevo);
			}
			ventanaEditarProducto.getCbTipoTabla().setSelectedIndex(0);
			ventanaEditarProducto.getCbTipo().setSelectedIndex(0);
			llenarTablaProductosEditados("todos");
			ventanaEditarProducto.getTfNombre().setText("");
			ventanaEditarProducto.getTfPrecio().setText("");
		}
		//modificar producto
		else if(this.ventanaEditarProducto!= null && e.getSource()==this.ventanaEditarProducto.getBtnGuardar())
		{
			Component a= new Component() {};
			int opcion = JOptionPane.showConfirmDialog(a, "¿Desea guardar los cambios realizados?", "Seleccione una opción", JOptionPane.YES_NO_OPTION);
			if( opcion==0)
			{
				ProductoDTO nuevo= this.producto.buscarProductoPorNombre(ventanaEditarProducto.getTable().getValueAt(this.ventanaEditarProducto.getTable().getSelectedRow(), 0).toString());
				nuevo.setNombre(ventanaEditarProducto.getTfNombre().getText().toString());
				nuevo.setPrecio(Integer.parseInt(ventanaEditarProducto.getTfPrecio().getText().toString()));
				nuevo.setTipo(ventanaEditarProducto.getCbTipo().getSelectedItem().toString());
				this.producto.actualizarProducto(nuevo);
			}
			ventanaEditarProducto.getCbTipoTabla().setSelectedIndex(0);
			llenarTablaProductosEditados("todos");
			ventanaEditarProducto.getCbTipo().setSelectedIndex(0);
			ventanaEditarProducto.getTfNombre().setText("");
			ventanaEditarProducto.getTfPrecio().setText("");			
		}

		

		//acciones relacionadas a la baja modificacion de repartidores
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnEditarRepartidor())
		{
			ventanaEditarRepartidor=new repartidorBajaModificacion(this);
			llenarTablaRepartidor();
			ventanaEditarRepartidor.getBtnQuitar().addActionListener(this);
			ventanaEditarRepartidor.getBtnGuardar().addActionListener(this);
			ventanaEditarRepartidor.setVisible(true);
		}
		//quitar un repartidor
		else if (this.ventanaEditarRepartidor!= null && e.getSource()==this.ventanaEditarRepartidor.getBtnQuitar())
		{
			Component a= new Component() {
			};
			int opcion = JOptionPane.showConfirmDialog(a, "¿Esta seguro que desea eliminar el repartidor?", "Seleccione una opción", JOptionPane.YES_NO_OPTION);
			if( opcion==0)
			{
				RepartidorDTO nuevo= this.repartidor.buscarRepartidorPorDni(Integer.parseInt(ventanaEditarRepartidor.getTable().getValueAt(this.ventanaEditarRepartidor.getTable().getSelectedRow(), 0).toString()));
				this.repartidor.quitarRepartidor(nuevo);
				llenarTablaRepartidor();
			}
			ventanaEditarRepartidor.getTfdni().setText("");
			ventanaEditarRepartidor.getTfApellido().setText("");
			ventanaEditarRepartidor.getTfCelular().setText("");
			ventanaEditarRepartidor.getTfdni().setText("");
			ventanaEditarRepartidor.getTfFechaNacimiento().setText("");
			ventanaEditarRepartidor.getTfNombre().setText("");
			ventanaEditarRepartidor.getTfComentario().setText("");
			ventanaEditarRepartidor.getTfPatente().setText("");
			ventanaEditarRepartidor.getTfDescripcion().setText("");		
		}
		//modificar un repartidor
		else if (this.ventanaEditarRepartidor!= null && e.getSource()==this.ventanaEditarRepartidor.getBtnGuardar())
		{
			Component a= new Component() {
			};
			int opcion = JOptionPane.showConfirmDialog(a, "¿Desea guardar los cambios realizados?", "Seleccione una opción", JOptionPane.YES_NO_OPTION);
			if( opcion==0)
			{
				RepartidorDTO rep= this.repartidor.buscarRepartidorPorDni(Integer.parseInt(ventanaEditarRepartidor.getTable().getValueAt(this.ventanaEditarRepartidor.getTable().getSelectedRow(), 0).toString()));
				rep.setIdRepartidor(rep.getIdRepartidor());
				rep.setNombre(ventanaEditarRepartidor.getTfNombre().getText().toString());
				rep.setDni(Integer.parseInt(ventanaEditarRepartidor.getTfdni().getText().toString()));
				rep.setApellido(ventanaEditarRepartidor.getTfApellido().getText().toString());
				rep.setFechaDeNacimiento(ventanaEditarRepartidor.getTfFechaNacimiento().getText().toString());
				rep.setComentario(ventanaEditarRepartidor.getTfComentario().getText());
				rep.setTelefono(ventanaEditarRepartidor.getTfCelular().getText().toString());
				rep.setPatente(ventanaEditarRepartidor.getTfPatente().getText());
				rep.setVehiculo(ventanaEditarRepartidor.getTfDescripcion().getText());
				rep.setEstado(null);
				repartidor.actualizarRepartidor(rep);
				llenarTablaRepartidor();

			}
			ventanaEditarRepartidor.getTfdni().setText("");
			ventanaEditarRepartidor.getTfApellido().setText("");
			ventanaEditarRepartidor.getTfCelular().setText("");
			ventanaEditarRepartidor.getTfdni().setText("");
			ventanaEditarRepartidor.getTfFechaNacimiento().setText("");
			ventanaEditarRepartidor.getTfNombre().setText("");
			ventanaEditarRepartidor.getTfComentario().setText("");
			ventanaEditarRepartidor.getTfPatente().setText("");
			ventanaEditarRepartidor.getTfDescripcion().setText("");
		}
		
		

		//acciones asociadas el alta de repartidores
		else if (this.ventanaAgregarRepartidor!= null && e.getSource()==this.ventanaAgregarRepartidor.getBtnCalendario())
		{
			ventanaCalendario=new calendario(ventanaAgregarRepartidor);
			ventanaCalendario.setVisible(true);
		}
		//registra un repartidor nuevo
		else if (this.ventanaAgregarRepartidor!= null && e.getSource()==this.ventanaAgregarRepartidor.getBtnRegistrar())
		{
			RepartidorDTO rep= new RepartidorDTO();
			rep.setIdRepartidor(repartidor.obtenerTodosRepartidores().size()+1);
			rep.setNombre(ventanaAgregarRepartidor.getTfNombre().getText().toString());
			rep.setDni(Integer.parseInt((ventanaAgregarRepartidor.getTfDni().getText().toString())));
			rep.setApellido(ventanaAgregarRepartidor.getTfApellido().getText().toString());
			rep.setFechaDeNacimiento(ventanaAgregarRepartidor.getTfFechaNacimiento().getText().toString());
			rep.setTelefono(ventanaAgregarRepartidor.getTfCelular().getText().toString());
			rep.setEstado(null);
			rep.setComentario(ventanaAgregarRepartidor.getTfComentario().getText().toString());
			rep.setVehiculo(ventanaAgregarRepartidor.getTfVehiculo().getText().toString());
			rep.setPatente(ventanaAgregarRepartidor.getTfPatente().getText().toString());
			rep.setFueeliminado(false);
			repartidor.agregarRepartidor(rep);
			ventanaAgregarRepartidor.dispose();
		}
		//ventana de configuracion agregar una promocion
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnAgregarPromocion())
		{
			ventanaAgregarPromocion=new promocionAlta(this);
			this.ventanaAgregarPromocion.getModel().setColumnIdentifiers(this.ventanaAgregarPromocion.getNombreColumnas());
			ventanaAgregarPromocion.getBtnAgregarProducto().addActionListener(this);
			ventanaAgregarPromocion.getBtnCrearPromocion().addActionListener(this);
			ventanaAgregarPromocion.getBtnQuitarProducto().addActionListener(this);
			ventanaAgregarPromocion.getComboBox().addActionListener(this);
			ventanaAgregarPromocion.setVisible(true);
		}
		//acciones asociadas al combo box
		else if (this.ventanaAgregarPromocion!= null && e.getSource()==this.ventanaAgregarPromocion.getComboBox())
		{
			String tipoProducto=ventanaAgregarPromocion.getComboBox().getSelectedItem().toString();
			TextAutoCompleter autoCompletar=new TextAutoCompleter(ventanaAgregarPromocion.getTfAgregarProducto());
			autoCompletar.setCaseSensitive(false);
			if(tipoProducto.compareTo("Empanada")==0)
				autoCompletar.addItems(this.producto.buscaNombresProductos("empanada"));
			if(tipoProducto.compareTo("Pizza")==0)
				autoCompletar.addItems(this.producto.buscaNombresProductos("pizza"));
			if(tipoProducto.compareTo("Otros")==0)
				autoCompletar.addItems(this.producto.buscaNombresProductos("otros"));
		}
		//acciones asociadas a crear una promocion
		else if (this.ventanaAgregarPromocion!= null && e.getSource()==this.ventanaAgregarPromocion.getBtnAgregarProducto())
		{
			Object[] fila = {ventanaAgregarPromocion.getTfAgregarProducto().getText(), ventanaAgregarPromocion.getTfCantidad().getText()};
			this.ventanaAgregarPromocion.getModel().addRow(fila);
			actualizarPrecioPromocion();
			ventanaAgregarPromocion.getTfAgregarProducto().setText("");
			ventanaAgregarPromocion.getTfCantidad().setText("");
			ventanaAgregarPromocion.getTfPrecioUnidad().setText("");
			ventanaAgregarPromocion.getTfSubtotal().setText("");
			ventanaAgregarPromocion.getComboBox().setSelectedIndex(0);
		}
		else if (this.ventanaAgregarPromocion!= null && e.getSource()==this.ventanaAgregarPromocion.getBtnQuitarProducto())
		{
			int fila=ventanaAgregarPromocion.getTable().getSelectedRow();
			ProductoDTO borrado=this.producto.buscarProductoPorNombre(ventanaAgregarPromocion.getModel().getValueAt(fila, 0).toString());
			int precioViejo=Integer.parseInt(ventanaAgregarPromocion.getTfPrecioReal().getText().toString());
			int cantidad=Integer.parseInt(ventanaAgregarPromocion.getModel().getValueAt(fila, 1).toString());
			ventanaAgregarPromocion.getTfPrecioReal().setText((precioViejo-(borrado.getPrecio()*cantidad))+"");
			this.ventanaAgregarPromocion.getModel().removeRow(fila);
			ventanaAgregarPromocion.getTfAgregarProducto().setText("");
			ventanaAgregarPromocion.getTfCantidad().setText("");
			ventanaAgregarPromocion.getTfPrecioUnidad().setText("");
			ventanaAgregarPromocion.getTfSubtotal().setText("");
			ventanaAgregarPromocion.getComboBox().setSelectedIndex(0);
		}
		else if (this.ventanaAgregarPromocion!= null && e.getSource()==this.ventanaAgregarPromocion.getBtnCrearPromocion())
		{
			if(ventanaAgregarPromocion.getTfNombre().getText().compareTo("")!=0 &&ventanaAgregarPromocion.getTfPrecio().getText().compareTo("")!=0 )
			{
				Component a= new Component() {};
				int opcion = JOptionPane.showConfirmDialog(a, "¿Esta seguro que desea crear esta promocion?", "Seleccione una opción", JOptionPane.YES_NO_OPTION);
				if( opcion==0)
				{
					PromocionDTO nueva=new PromocionDTO();
					nueva.setFueeliminado(false);
					nueva.setIdOferta(promocion.obtenerUltimoid()+1);
					nueva.setNombre(ventanaAgregarPromocion.getTfNombre().getText());
					nueva.setPrecio(Integer.parseInt(ventanaAgregarPromocion.getTfPrecio().getText().toString()));
					nueva.setProductosOfertados(generarListaItemsOfertados());
					this.promocion.agregarOferta(nueva);
					ventanaAgregarPromocion.dispose();
				}
			}
			else if(ventanaAgregarPromocion.getTfNombre().getText().compareTo("")==0)
				JOptionPane.showMessageDialog(null, "Error, no ha establecido un Nombre para la promocion");
			else if(ventanaAgregarPromocion.getTfPrecio().getText().compareTo("")==0)
				JOptionPane.showMessageDialog(null, "Error, no ha establecido un Precio para la promocion");
				
		}
		
		//ventana de configuracion editar una promocion
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnEditarPromocion())
		{
			ventanaEditarPromocion=new promocionBajaModificacion(this);
			llenarComboBoxEdicionPromo();
			ventanaEditarPromocion.getCbTipoProducto().addActionListener(this);
			ventanaEditarPromocion.getComboBox().addActionListener(this);
			ventanaEditarPromocion.getBtnAceptarProducto().addActionListener(this);
			ventanaEditarPromocion.getBtnReemplazarProducto().addActionListener(this);
			ventanaEditarPromocion.getBtnBorrarProducto().addActionListener(this);
			ventanaEditarPromocion.getBtnEliminarPromocion().addActionListener(this);
			ventanaEditarPromocion.getBtnGuardarCambios().addActionListener(this);
			ventanaEditarPromocion.getTfBuscarProducto().setEnabled(false);
			ventanaEditarPromocion.getTfUnidades().setEnabled(false);
			ventanaEditarPromocion.getTfBuscarProducto().setVisible(false);
			ventanaEditarPromocion.getTfSubTotal().setVisible(false);
			ventanaEditarPromocion.getTfUnidades().setVisible(false);
			ventanaEditarPromocion.getTfSubTotal().setEnabled(false);
			ventanaEditarPromocion.getLbotonAgregarProducto().setVisible(false);
			ventanaEditarPromocion.getTfOcullto().setVisible(true);
			ventanaEditarPromocion.setVisible(true);
		}
		else if (this.ventanaEditarPromocion!= null && e.getSource()==this.ventanaEditarPromocion.getComboBox())
		{
			String promo=ventanaEditarPromocion.getComboBox().getSelectedItem().toString();
			PromocionDTO promocion=this.promocion.buscarOfertaPorNombre(promo);
			if(promocion!=null)
			{
				llenarTablaEditarPromo(promocion);
				ventanaEditarPromocion.getTfNombre().setText(promocion.getNombre());
				ventanaEditarPromocion.getTfPrecioFinal().setText(promocion.getPrecio()+"");
				ventanaEditarPromocion.getTfPrecioReal().setText(calcularPrecioReal(promocion).toString());
			}
		}
		//editar para agregar un producto a una promocion
		else if (this.ventanaEditarPromocion!= null && e.getSource()==this.ventanaEditarPromocion.getBtnReemplazarProducto())
		{
			ventanaEditarPromocion.getTfBuscarProducto().setEnabled(true);
			ventanaEditarPromocion.getTfUnidades().setEnabled(true);
			ventanaEditarPromocion.getTfSubTotal().setEnabled(true);
			ventanaEditarPromocion.getTfUnidades().setVisible(true);
			ventanaEditarPromocion.getTfBuscarProducto().setVisible(true);
			ventanaEditarPromocion.getTfSubTotal().setVisible(true);
			ventanaEditarPromocion.getTfOcullto().setVisible(false);
			ventanaEditarPromocion.getCbTipoProducto().setVisible(true);
			ventanaEditarPromocion.getBtnAceptarProducto().setVisible(true);
			ventanaEditarPromocion.getLbotonAgregarProducto().setVisible(true);
			
		}
		
		//elegir el tipo de producto
		else if (this.ventanaEditarPromocion!= null && e.getSource()==this.ventanaEditarPromocion.getCbTipoProducto())
		{
			String tipoProducto=ventanaEditarPromocion.getCbTipoProducto().getSelectedItem().toString();
			TextAutoCompleter autoCompletar2=new TextAutoCompleter(ventanaEditarPromocion.getTfBuscarProducto());
			autoCompletar2.setCaseSensitive(false);
			if(tipoProducto.compareTo("Empanada")==0)
			autoCompletar2.addItems(this.producto.buscaNombresProductos("empanada"));
			if(tipoProducto.compareTo("Pizza")==0)
				autoCompletar2.addItems(this.producto.buscaNombresProductos("pizza"));
			if(tipoProducto.compareTo("Otros")==0)
				autoCompletar2.addItems(this.producto.buscaNombresProductos("otros"));
		}
		//agregar un producto
		else if (this.ventanaEditarPromocion!= null && e.getSource()==this.ventanaEditarPromocion.getBtnAceptarProducto())
		{
			if(ventanaEditarPromocion.getTfBuscarProducto().getText().compareTo("")!=0 && ventanaEditarPromocion.getTfUnidades().getText().compareTo("")!=0)
			{
				String nombre=ventanaEditarPromocion.getTfBuscarProducto().getText();
				Integer cantidad=Integer.parseInt(ventanaEditarPromocion.getTfUnidades().getText().toString());
				Object[] fila = {nombre,cantidad};
				this.ventanaEditarPromocion.getModel().addRow(fila);
				ventanaEditarPromocion.getTfOcullto().setVisible(true);
				ventanaEditarPromocion.getLbotonAgregarProducto().setVisible(false);
				ventanaEditarPromocion.getTfBuscarProducto().setVisible(false);
				ventanaEditarPromocion.getCbTipoProducto().setSelectedIndex(0);
				ventanaEditarPromocion.getTfBuscarProducto().setText("");
				ventanaEditarPromocion.getTfUnidades().setText("");
				ventanaEditarPromocion.getTfSubTotal().setText("");
				ventanaEditarPromocion.getTfSubTotal().setVisible(false);
				ventanaEditarPromocion.getTfUnidades().setVisible(false);
				ventanaEditarPromocion.getBtnAceptarProducto().setVisible(false);
				ventanaEditarPromocion.getCbTipoProducto().setVisible(false);
				Integer precioReal=Integer.parseInt(ventanaEditarPromocion.getTfPrecioReal().getText().toString());
				ProductoDTO aux=this.producto.buscarProductoPorNombre(nombre);
				Integer costoActual=(precioReal+(aux.getPrecio()*cantidad));
				ventanaEditarPromocion.getTfPrecioReal().setText(costoActual.toString());
			}
		}
		//borra un producto
		else if (this.ventanaEditarPromocion!= null && e.getSource()==this.ventanaEditarPromocion.getBtnBorrarProducto())
		{
			int filaSeleccionada=(ventanaEditarPromocion.getTable().getSelectedRow());
			
			Integer precioReal=Integer.parseInt(ventanaEditarPromocion.getTfPrecioReal().getText().toString());
			Object nombre=(ventanaEditarPromocion.getModel().getValueAt(filaSeleccionada, 0));
			ProductoDTO aux=this.producto.buscarProductoPorNombre(nombre.toString());
			Integer cantidad= Integer.parseInt(ventanaEditarPromocion.getModel().getValueAt(filaSeleccionada, 1).toString());
			Integer costoActual=(precioReal-(aux.getPrecio()*cantidad));
			ventanaEditarPromocion.getTfPrecioReal().setText(costoActual.toString());
			ventanaEditarPromocion.getModel().removeRow(ventanaEditarPromocion.getTable().getSelectedRow());
		}
		//elimina una promocion
		else if (this.ventanaEditarPromocion!= null && e.getSource()==this.ventanaEditarPromocion.getBtnEliminarPromocion())
		{
			Component a= new Component(){};
			int opcion = JOptionPane.showConfirmDialog(a, "¿Esta seguro que desea eliminar la promocion?", "Seleccione una opción", JOptionPane.YES_NO_OPTION);
			if( opcion==0)
			{
				this.ventanaEditarPromocion.getModel().setRowCount(0);
				this.ventanaEditarPromocion.getModel().setColumnCount(0);
				PromocionDTO aux=this.promocion.buscarOfertaPorNombre(ventanaEditarPromocion.getTfNombre().getText());
				this.promocion.quitarPromocion(aux);
				ventanaEditarPromocion.getComboBox().setSelectedIndex(0);
			}
		}
		//guarda los cambios
		else if (this.ventanaEditarPromocion!= null && e.getSource()==this.ventanaEditarPromocion.getBtnGuardarCambios())
		{
			Component a= new Component(){};
			int opcion = JOptionPane.showConfirmDialog(a, "¿Esta seguro que desea modificar la promocion?", "Seleccione una opción", JOptionPane.YES_NO_OPTION);
			if( opcion==0)
			{
				PromocionDTO aux=this.promocion.buscarOfertaPorNombre(ventanaEditarPromocion.getComboBox().getSelectedItem().toString());
				aux.setNombre(ventanaEditarPromocion.getTfNombre().getText());
				aux.setPrecio(Integer.parseInt(ventanaEditarPromocion.getTfPrecioFinal().getText().toString()));
				aux.setProductosOfertados(obtenerListaProductosTabla());
				this.promocion.actualizarPromocion(aux);
			}
			ventanaEditarPromocion.getTfNombre().setText("");
			ventanaEditarPromocion.getTfPrecioFinal().setText("");
			ventanaEditarPromocion.getTfPrecioReal().setText("");
			this.ventanaEditarPromocion.getModel().setRowCount(0);
			this.ventanaEditarPromocion.getModel().setColumnCount(0);
			ventanaEditarPromocion.getComboBox().setSelectedIndex(0);
		}

		//ventana de configuracion para editar un cliente
		else if (this.ventanaConfiguraciones!= null && e.getSource()==this.ventanaConfiguraciones.getBtnEditarCliente())
		{
			ventanaModificacionCliente=new clienteBajaModificacion(this);
			llenarTablaCliente();
			ventanaModificacionCliente.getBtnGuardar().addActionListener(this);
			ventanaModificacionCliente.getBtnBorrarCliente().addActionListener(this);
			ventanaModificacionCliente.getCbClientes().addActionListener(this);
			ventanaModificacionCliente.setVisible(true);

		}
		//llenar autocompletar de clientes en relacion al tipo de filtro seleccionado
		else if(this.ventanaModificacionCliente!= null && e.getSource()==this.ventanaModificacionCliente.getCbClientes())
		{
			String tipoDeFiltro = (String) ventanaModificacionCliente.getCbClientes().getSelectedItem().toString();
			switch (tipoDeFiltro)
			{
			case"DNI":
			{
				TextAutoCompleter autoCompletar=new TextAutoCompleter(ventanaModificacionCliente.getTfBusquedaCliente());
				autoCompletar.setCaseSensitive(false);
				autoCompletar.addItems(this.cliente.dniClientes()); break;
			}
			case"Apellido":
			{
				TextAutoCompleter autoCompletar=new TextAutoCompleter(ventanaModificacionCliente.getTfBusquedaCliente());
				autoCompletar.setCaseSensitive(false);
				autoCompletar.addItems(this.cliente.ApellidoClientes()); break;
			}
			case"Nombre":
			{
				TextAutoCompleter autoCompletar=new TextAutoCompleter(ventanaModificacionCliente.getTfBusquedaCliente());
				autoCompletar.setCaseSensitive(false);
				autoCompletar.addItems(this.cliente.NombreClientes()); break;
			}
			}
		}
		//mofificar un cliente
		else if(this.ventanaModificacionCliente!=null && e.getSource()==this.ventanaModificacionCliente.getBtnGuardar())
		{
			Component a= new Component() {};
			int opcion = JOptionPane.showConfirmDialog(a, "¿Desea guardar los cambios realizados?", "Seleccione una opción", JOptionPane.YES_NO_OPTION);
			if( opcion==0)
			{

				ClienteDTO actu=this.cliente.buscarClientePorDNI(Integer.parseInt(ventanaModificacionCliente.getModel().getValueAt(ventanaModificacionCliente.getTable().getSelectedRow(), 0).toString()));
				actu.setDni(Integer.parseInt(ventanaModificacionCliente.getTfDni().getText().toString()));
				actu.setNombre(ventanaModificacionCliente.getTfNombre().getText().toString());
				actu.setApellido(ventanaModificacionCliente.getTfApellido().getText().toString());
				actu.setCalle(ventanaModificacionCliente.getTfCalle().getText().toString());
				actu.setNumeracion(ventanaModificacionCliente.getTfNumeracion().getText().toString());
				actu.setEntrecalle1(ventanaModificacionCliente.getTfEntreCalle1().getText().toString());
				actu.setEntrecalle2(ventanaModificacionCliente.getTfEntreCalle2().getText().toString());
				actu.setCodPostal(ventanaModificacionCliente.getTfCodPostal().getText().toString());
				actu.setTelefono(ventanaModificacionCliente.getTfTelefono().getText().toString());
				actu.setComentario(ventanaModificacionCliente.getTfComentario().getText().toString());
				actu.setEmail(ventanaModificacionCliente.getTfEmail().getText().toString());
				actu.setFueeliminado(false);
				this.cliente.actualizarCliente(actu);
			}
			ventanaModificacionCliente.getTfApellido().setText("");
			ventanaModificacionCliente.getTfBusquedaCliente().setText("");
			ventanaModificacionCliente.getTfCalle().setText("");
			ventanaModificacionCliente.getTfCodPostal().setText("");
			ventanaModificacionCliente.getTfComentario().setText("");
			ventanaModificacionCliente.getTfDni().setText("");
			ventanaModificacionCliente.getTfEmail().setText("");
			ventanaModificacionCliente.getTfEntreCalle1().setText("");
			ventanaModificacionCliente.getTfEntreCalle2().setText("");
			ventanaModificacionCliente.getTfNombre().setText("");
			ventanaModificacionCliente.getTfNumeracion().setText("");
			ventanaModificacionCliente.getTfTelefono().setText("");
			llenarTablaCliente();
		}

		//borrar un cliente
		else if(this.ventanaModificacionCliente!=null && e.getSource()==this.ventanaModificacionCliente.getBtnBorrarCliente())
		{
			Component a= new Component() {};
			int opcion = JOptionPane.showConfirmDialog(a, "¿Esta seguro que desea eliminar el cliente?", "Seleccione una opción", JOptionPane.YES_NO_OPTION);
			if( opcion==0)
			{
				ClienteDTO actu=this.cliente.buscarClientePorDNI(Integer.parseInt(ventanaModificacionCliente.getModel().getValueAt(ventanaModificacionCliente.getTable().getSelectedRow(), 0).toString()));
				this.cliente.quitarCliente(actu);
			}
			ventanaModificacionCliente.getTfApellido().setText("");
			ventanaModificacionCliente.getTfBusquedaCliente().setText("");
			ventanaModificacionCliente.getTfCalle().setText("");
			ventanaModificacionCliente.getTfCodPostal().setText("");
			ventanaModificacionCliente.getTfComentario().setText("");
			ventanaModificacionCliente.getTfDni().setText("");
			ventanaModificacionCliente.getTfEmail().setText("");
			ventanaModificacionCliente.getTfEntreCalle1().setText("");
			ventanaModificacionCliente.getTfEntreCalle2().setText("");
			ventanaModificacionCliente.getTfNombre().setText("");
			ventanaModificacionCliente.getTfNumeracion().setText("");
			ventanaModificacionCliente.getTfTelefono().setText("");
			llenarTablaCliente();
		}

		//generar ventana para dar de alta a cliente
		else if (this.ventanaCliente!= null && e.getSource()==this.ventanaCliente.getBtnAgregarCliente())
		{
			ventanaRegistrarCliente= new registroDeCliente(ventanaCliente, this);
			ventanaRegistrarCliente.getBtnRegistrar().addActionListener(this);
			ventanaRegistrarCliente.setVisible(true);
		}
		//dar de alta a cliente
		else if (this.ventanaRegistrarCliente!= null && e.getSource()==this.ventanaRegistrarCliente.getBtnRegistrar())
		{
			ClienteDTO nuevo= new ClienteDTO();
			nuevo.setIdcliente(this.cliente.obtenerUltimoIdCliente()+1);
			nuevo.setDni(Integer.parseInt(ventanaRegistrarCliente.getTfdni().getText().toString()));
			nuevo.setApellido(ventanaRegistrarCliente.getTfApellido().getText().toString());
			nuevo.setNombre(ventanaRegistrarCliente.getTfNombre().getText().toString());
			nuevo.setCalle(ventanaRegistrarCliente.getTfCalle().getText().toString());
			nuevo.setNumeracion(ventanaRegistrarCliente.getTfNumeracion().getText().toString());
			nuevo.setTelefono(ventanaRegistrarCliente.getTfTelefono().getText().toString());
			nuevo.setComentario(ventanaRegistrarCliente.getTfComentario().getText().toString());
			nuevo.setFueeliminado(false);
			nuevo.setEntrecalle1(ventanaRegistrarCliente.getTfEntreCalle1().getText().toString());
			nuevo.setEntrecalle2(ventanaRegistrarCliente.getTfEntreCalle2().getText().toString());
			nuevo.setCodPostal(ventanaRegistrarCliente.getTfCodPostal().getText().toString());
			nuevo.setEmail(ventanaRegistrarCliente.getTfEmail().getText().toString());
			cliente.agregarCliente(nuevo);
			ventanaCliente.getTfAgregarDNI().setText(nuevo.getDni().toString());
			ventanaCliente.llenarTablaCliente();
			ventanaCliente.getTfNombrApellido().setText("Apellido y Nombre: "+nuevo.getNombre()+" "+ nuevo.getApellido());
			ventanaCliente.getTfDireccionTelefono().setText("Direccion: "+nuevo.getCalle()+" "+nuevo.getNumeracion());
			ventanaRegistrarCliente.dispose();
		}

		//editar cliente
				else if(this.ventanaCliente!= null && e.getSource()==this.ventanaCliente.getBtnEditarCliente())
				{
					Integer dniCliente=Integer.parseInt(ventanaCliente.getModel().getValueAt(ventanaCliente.getTable().getSelectedRow(), 0).toString());
					if(ventanaCliente.getTfAgregarDNI().getText().length()>7)
					{
						ventanaModificacionCliente=new clienteBajaModificacion(ventanaCliente, this);
						ClienteDTO aux=cliente.buscarClientePorDNI(Integer.parseInt(ventanaCliente.getTfAgregarDNI().getText().toString()));
						ventanaModificacionCliente.getTfDni().setText(aux.getDni().toString());
						ventanaModificacionCliente.getTfNombre().setText(aux.getNombre());
						ventanaModificacionCliente.getTfApellido().setText(aux.getApellido());
						ventanaModificacionCliente.getTfCalle().setText(aux.getCalle());
						ventanaModificacionCliente.getTfNumeracion().setText(aux.getNumeracion());
						ventanaModificacionCliente.getTfEntreCalle1().setText(aux.getEntrecalle1());
						ventanaModificacionCliente.getTfEntreCalle2().setText(aux.getEntrecalle2());
						ventanaModificacionCliente.getTfCodPostal().setText(aux.getCodPostal());
						ventanaModificacionCliente.getTfTelefono().setText(aux.getTelefono());
						ventanaModificacionCliente.getTfComentario().setText(aux.getComentario());
						ventanaModificacionCliente.getTfEmail().setText(aux.getEmail());
						//llenarTablaCliente();
						ventanaModificacionCliente.setVisible(true);
					}
					else if(this.ventanaCliente!= null && e.getSource()==this.ventanaCliente.getBtnEditarCliente())
					{
						ventanaModificacionCliente=new clienteBajaModificacion(ventanaCliente, this);
						ClienteDTO aux=cliente.buscarClientePorDNI(dniCliente);
						ventanaModificacionCliente.getTfDni().setText(aux.getDni().toString());
						ventanaModificacionCliente.getTfNombre().setText(aux.getNombre());
						ventanaModificacionCliente.getTfApellido().setText(aux.getApellido());
						ventanaModificacionCliente.getTfCalle().setText(aux.getCalle());
						ventanaModificacionCliente.getTfNumeracion().setText(aux.getNumeracion());
						ventanaModificacionCliente.getTfEntreCalle1().setText(aux.getEntrecalle1());
						ventanaModificacionCliente.getTfEntreCalle2().setText(aux.getEntrecalle2());
						ventanaModificacionCliente.getTfCodPostal().setText(aux.getCodPostal());
						ventanaModificacionCliente.getTfTelefono().setText(aux.getTelefono());
						ventanaModificacionCliente.getTfComentario().setText(aux.getComentario());
						ventanaModificacionCliente.getTfEmail().setText(aux.getEmail());
						//llenarTablaCliente();
						ventanaModificacionCliente.setVisible(true);
					}
		
					else if(ventanaCliente.getTfAgregarDNI().getText().compareTo("")==0 && !dniCliente.equals(0))
					{
						ventanaModificacionCliente=new clienteBajaModificacion(ventanaCliente, this);
						ClienteDTO aux=cliente.buscarClientePorDNI(dniCliente);
						ventanaModificacionCliente.getTfDni().setText(aux.getDni().toString());
						ventanaModificacionCliente.getTfNombre().setText(aux.getNombre());
						ventanaModificacionCliente.getTfApellido().setText(aux.getApellido());
						ventanaModificacionCliente.getTfCalle().setText(aux.getCalle());
						ventanaModificacionCliente.getTfNumeracion().setText(aux.getNumeracion());
						ventanaModificacionCliente.getTfEntreCalle1().setText(aux.getEntrecalle1());
						ventanaModificacionCliente.getTfEntreCalle2().setText(aux.getEntrecalle2());
						ventanaModificacionCliente.getTfCodPostal().setText(aux.getCodPostal());
						ventanaModificacionCliente.getTfTelefono().setText(aux.getTelefono());
						ventanaModificacionCliente.getTfComentario().setText(aux.getComentario());
						ventanaModificacionCliente.getTfEmail().setText(aux.getEmail());
						llenarTablaCliente();
						ventanaModificacionCliente.setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(null, "Error el debe haber un dni registrado para poder editarlo");
				}
	}

	private ArrayList<ProductoEstadistico> filtrarProductoTipo(ArrayList<ProductoEstadistico> productos, String tipo)
	{
		ArrayList<ProductoEstadistico> result= new ArrayList<ProductoEstadistico>();
		Iterator<ProductoEstadistico> iterador= productos.iterator();
		while(iterador.hasNext())
		{
			ProductoEstadistico elemento= iterador.next();
			if(tipo.compareTo(elemento.getProducto().getTipo())==0)
				result.add(elemento);
		}
		return result;
	}


	private void consultaReporteDiario() throws SQLException {
		reporteContable = new ReporteContableDTO();
		reporteContable.setListadoPedidos( pedido.reporteDiario( getDiaActual(),getMesActual(),getAñoActual() ));
		reporteContable.setListadoCompras( ordenesMatPrimas.reporteEnRango(getDiaActual(),getMesActual(),getAñoActual() ));							
	}

	private String getDiaActual() {
		Calendar fecha = new GregorianCalendar();
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        return String.valueOf(dia);
	}

	private String getAñoActual() {
		Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        return String.valueOf(año);
	}

	private String getMesActual() {
		Calendar fecha = new GregorianCalendar();
        int mes = fecha.get(Calendar.MONTH)+1;
        return String.valueOf(mes);
	}


	private void prepararParaSobreescribirPDF(OrdenPedidoMatPrimaDTO ordenSeleccionada) 
	{
		//String rutaFile = "D:/OrdenDePedidoNro" + ordenSeleccionada.getIdCompra() + ".pdf";
		File fichero = new File("D:/OrdenDePedidoNro" + ordenSeleccionada.getIdCompra() + ".pdf");
		if (fichero.delete())
			   System.out.println("El fichero ha sido borrado satisfactoriamente");
			else
			   System.out.println("El fichero no puede ser borrado");
	}


	private ProveedorDTO guardarDatosProveedor(ProveedorDTO provModificado) {
		provModificado.setDireccion(ventanaEditarProveedor.getTfDireccion().getText());
		provModificado.setEmail(ventanaEditarProveedor.getTfEmail().getText());
		provModificado.setTelefono(ventanaEditarProveedor.getTfTelefono().getText());
		provModificado.setComentario(ventanaEditarProveedor.getTextPaneComentario().getText().trim());

		ArrayList<CategoriaDTO> categorias = new ArrayList<CategoriaDTO>();
		for (int i = 0; i < this.ventanaEditarProveedor.getTablaCategorias().getRowCount();i++){
			//System.out.println(categoria.buscarCategoria(ventanaAgregarProveedor.getTablaCategorias().getValueAt(i, 0).toString()).getDenominacion());
			//System.out.println("nro int fila cat> " + Integer.parseInt(ventanaEditarProveedor.getTablaCategorias().getValueAt(i, 1).toString()));
			CategoriaDTO nvaCat = categoria.buscarCategoria(Integer.parseInt(ventanaEditarProveedor.getTablaCategorias().getValueAt(i, 1).toString()));
			categorias.add(nvaCat);
		}
		provModificado.setCategoria(categorias);
		provModificado.setNombrecontacto(ventanaEditarProveedor.getTfNombreContacto().getText());	
		return provModificado;
	}


	protected void validarHabilitacionBotonesOrdenesMP() {
		gestorOrdenesMateriasPrimas.resetearItemsOrdenesMatPrima();
		Integer indiceFilaOrdenMPSelecc = this.gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().getSelectedRow();
		if (indiceFilaOrdenMPSelecc>-1){
			OrdenPedidoMatPrimaDTO ordenSeleccionada = this.ordenesMatPrimas.buscarOrdenPedidoMatPrima
					(Integer.parseInt(this.gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().getValueAt(indiceFilaOrdenMPSelecc,0).toString()));
			if (ordenSeleccionada.getEstado().trim().compareTo("pagado")==0)
				gestorOrdenesMateriasPrimas.mostrarCostos(ordenSeleccionada.getCosto());
			else{
				gestorOrdenesMateriasPrimas.ocultarCostos();
			}
			if (ordenSeleccionada.getFueeliminado()){
				gestorOrdenesMateriasPrimas.ocultarOpcPagoBorrarEnviar(true,true,true);
			}
			else if(ordenSeleccionada.getEnviado() && ordenSeleccionada.getEstado().trim().compareTo("pagado")!=0){
				gestorOrdenesMateriasPrimas.ocultarOpcPagoBorrarEnviar(false,true,true);
			}
			else if(ordenSeleccionada.getEstado().trim().compareTo("guardado")==0){
				gestorOrdenesMateriasPrimas.ocultarOpcPagoBorrarEnviar(true,false,false);
			}
			else if(ordenSeleccionada.getEstado().trim().compareTo("pagado")==0){
				gestorOrdenesMateriasPrimas.ocultarOpcPagoBorrarEnviar(true,true,true);
			}

		}

	}


	private void cargarListadoCategorias() {
		Iterator<CategoriaDTO> Iterador = categoria.obtenerCategorias().iterator();
		while(Iterador.hasNext())
		{
			CategoriaDTO elemento = Iterador.next();
			if (!elemento.getFueEliminado())
				this.ventanaGestionCategoria.addCategoriaTabla(elemento);
		}
		ventanaGestionCategoria.getTablacategorias().setModel(ventanaGestionCategoria.getModeloCategorias());
	}

	private void filtrarBusquedaOrdenes() {
		this.gestorOrdenesMateriasPrimas.getTextAutoAcompleter().removeAllItems();
		this.gestorOrdenesMateriasPrimas.resetearModeloOrdenesPedido();
		//String filtroSeleccionado = this.gestorOrdenesMateriasPrimas.getComboBoxFiltroOrdenes().getSelectedItem().toString();
		Iterator<OrdenPedidoMatPrimaDTO> iteradorOrdenes = ordenesMatPrimas.obtenerOrdenPedidoMatPrima().iterator();
		switch(this.gestorOrdenesMateriasPrimas.getComboBoxFiltroOrdenes().getSelectedItem().toString())
		{
		case("Todas las ordenes"):
		{
			while (iteradorOrdenes.hasNext()){
				OrdenPedidoMatPrimaDTO elementoOrden = iteradorOrdenes.next();
				this.gestorOrdenesMateriasPrimas.agregarFilaOrden(elementoOrden);
				if (!this.gestorOrdenesMateriasPrimas.getTextAutoAcompleter().itemExists(elementoOrden.getProveedor().getNombre()))
					this.gestorOrdenesMateriasPrimas.getTextAutoAcompleter().addItem(elementoOrden.getProveedor().getNombre());
			}
			break;
		}
		case("Ordenes guardadas"):
		{
			while (iteradorOrdenes.hasNext()){
				OrdenPedidoMatPrimaDTO elementoOrden = iteradorOrdenes.next();
				if (elementoOrden.getEstado().trim().compareTo("guardado")==0){
					this.gestorOrdenesMateriasPrimas.agregarFilaOrden(elementoOrden);
					if (!this.gestorOrdenesMateriasPrimas.getTextAutoAcompleter().itemExists(elementoOrden.getProveedor().getNombre()))
						this.gestorOrdenesMateriasPrimas.getTextAutoAcompleter().addItem(elementoOrden.getProveedor().getNombre());
				}
			}
			break;
		}
		case("Ordenes enviadas pendientes de pago"):
		{
			while (iteradorOrdenes.hasNext()){
				OrdenPedidoMatPrimaDTO elementoOrden = iteradorOrdenes.next();
				if (elementoOrden.getEstado().trim().compareTo("enviado")==0){
					this.gestorOrdenesMateriasPrimas.agregarFilaOrden(elementoOrden);
					if (!this.gestorOrdenesMateriasPrimas.getTextAutoAcompleter().itemExists(elementoOrden.getProveedor().getNombre()))
						this.gestorOrdenesMateriasPrimas.getTextAutoAcompleter().addItem(elementoOrden.getProveedor().getNombre());
				}
			}
			break;
		}
		case("Ordenes pagadas"):
		{
			while (iteradorOrdenes.hasNext()){
				OrdenPedidoMatPrimaDTO elementoOrden = iteradorOrdenes.next();
				if (elementoOrden.getEstado().trim().compareTo("pagado")==0){
					this.gestorOrdenesMateriasPrimas.agregarFilaOrden(elementoOrden);
					if (!this.gestorOrdenesMateriasPrimas.getTextAutoAcompleter().itemExists(elementoOrden.getProveedor().getNombre()))
						this.gestorOrdenesMateriasPrimas.getTextAutoAcompleter().addItem(elementoOrden.getProveedor().getNombre());
				}
			}
			break;
		}
		case("Ordenes rechazadas"):
		{
			while (iteradorOrdenes.hasNext()){
				OrdenPedidoMatPrimaDTO elementoOrden = iteradorOrdenes.next();
				if (elementoOrden.getEstado().trim().compareTo("rechazado")==0){
					this.gestorOrdenesMateriasPrimas.agregarFilaOrden(elementoOrden);
					if (!this.gestorOrdenesMateriasPrimas.getTextAutoAcompleter().itemExists(elementoOrden.getProveedor().getNombre()))
						this.gestorOrdenesMateriasPrimas.getTextAutoAcompleter().addItem(elementoOrden.getProveedor().getNombre());
				}
			}
			break;
		}
		}
		this.gestorOrdenesMateriasPrimas.gettableOrdenesMatPrimas().setModel(this.gestorOrdenesMateriasPrimas.getModeloOrdenesMatPrimas());
		this.gestorOrdenesMateriasPrimas.resetearItemsOrdenesMatPrima();
	}


	private OrdenPedidoMatPrimaDTO getOrden(Integer nroId) {
		Iterator<OrdenPedidoMatPrimaDTO> Iterador = ordenesMatPrimas.obtenerOrdenPedidoMatPrima().iterator();
		while(Iterador.hasNext())
		{
			OrdenPedidoMatPrimaDTO elementoOrden = Iterador.next();
			if (elementoOrden.getIdCompra() == nroId)
				return elementoOrden;
		}
		System.out.println("No se hayo la orden de materia prima con el id: " + nroId);
		return null;
	}


	//METODOS PARA VENTANA ORDEN DE MATERIA PRIMA
	private void preCargarCamposdeBusqueda() {
		//cargamos categorias existenets
		Iterator<CategoriaDTO> Iterador = this.categoria.obtenerCategorias().iterator();
		while(Iterador.hasNext())
		{
			CategoriaDTO elementoCategoria = Iterador.next();
			ventanaSeleccionProveedor.getComboBoxCategorias().addItem(elementoCategoria.getDenominacion());
		}
		//cargamos todos los proveedores
		ventanaSeleccionProveedor.getTextAutoAcompleter().removeAllItems();
		Iterator<ProveedorDTO> IteradorProveedores = this.proveedor.obtenerProveedor().iterator();
		while(IteradorProveedores.hasNext())
		{
			ProveedorDTO elementoProveedor = IteradorProveedores.next();
			ventanaSeleccionProveedor.getTextAutoAcompleter().addItem(elementoProveedor.getNombre());
		}
	}
	@SuppressWarnings("serial")
	private void cargarResultados() {
		switch(ventanaSeleccionProveedor.getComboFiltroBusqueda().getSelectedItem().toString())
		{
		case("Seleccione una categoria..."):
		{
			ventanaSeleccionProveedor.setModeloResultados(new DefaultTableModel(new Object[][] {},new String[] {"Proveedor", "Direcci\u00F3n", "Tel\u00E9fono", "E-mail"
			}
					) {
				@SuppressWarnings("rawtypes")
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class
				};
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			ventanaSeleccionProveedor.getResultadoBusquedaProv().setModel(ventanaSeleccionProveedor.getModeloResultados());
		}
		case("Todos"):
		{
			//Carga direcamente en el jtable todos los proveedores
			ventanaSeleccionProveedor.getComboBoxCategorias().setVisible(false);
			ventanaSeleccionProveedor.getTextFieldBuscadorProv().setVisible(false);

			Iterator<ProveedorDTO> IteradorProveedores = this.proveedor.obtenerProveedor().iterator();
			while(IteradorProveedores.hasNext())
			{
				ProveedorDTO elementoProveedor = IteradorProveedores.next();
				//"Proveedor", "Direcci\u00F3n", "Tel\u00E9fono", "E-mail"
				ventanaSeleccionProveedor.getModeloResultados().addRow(new Object[] {elementoProveedor.getNombre().trim(),elementoProveedor.getDireccion().trim()
						,elementoProveedor.getTelefono().trim(),elementoProveedor.getEmail().trim()});
			}
			ventanaSeleccionProveedor.setModeloResultados(ventanaSeleccionProveedor.getModeloResultados());
			break;
		}
		case("Categoría"):
		{
			ventanaSeleccionProveedor.getComboBoxCategorias().setVisible(true);
			ventanaSeleccionProveedor.getTextFieldBuscadorProv().setVisible(false);
			//Seleccione de un combobox la categoria. En este caso el campo textfield de busqued deberia ponerse en visible(false) y el combo en
			//visible true. Puede comenzar ya precargado con todas las categorias existentes.

			Iterator<ProveedorDTO> IteradorProveedores = this.proveedor.obtenerProveedor().iterator();
			while(IteradorProveedores.hasNext())
			{
				ProveedorDTO elementoProveedor = IteradorProveedores.next();
				if (elementoProveedor.isCategoria(ventanaSeleccionProveedor.getComboBoxCategorias().getSelectedItem().toString()));
				{
					ventanaSeleccionProveedor.getModeloResultados().addRow(new Object[] {elementoProveedor.getNombre().trim(),elementoProveedor.getDireccion().trim()
							,elementoProveedor.getTelefono().trim(),elementoProveedor.getEmail().trim()});
				}
			}
			ventanaSeleccionProveedor.setModeloResultados(ventanaSeleccionProveedor.getModeloResultados());
			break;
		}
		case("Nombre"):
		{
			ventanaSeleccionProveedor.getComboBoxCategorias().setVisible(false);
			ventanaSeleccionProveedor.getTextFieldBuscadorProv().setVisible(true);
			//Escriba el nombre del proveedor. Se pograma el autocomplete con todos los nombres de los proveedores, esto tmb puede estar pre cargado.
			break;
		}
		}
	}	
	private void borrarTodo() {
		limpiarCampos();
		ventanaOrdenMatPrima.getComboListaProveedores().setEnabled(true);
		ventanaOrdenMatPrima.setModeloItemsSolicitados(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Materia Prima", "Cantidad"
				}
				) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, Integer.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		ventanaOrdenMatPrima.actualizarModelo();
	}
	private void generarPDFOrdenMatPrima() {
		try {
			solicitudDeMateriaPrima orden = new solicitudDeMateriaPrima(ventanaOrdenMatPrima.getNuevaOrden());
		} catch (Exception e2) {
			e2.printStackTrace();
		}		
	}
	private void persistirOrdenMatPrima(boolean enviado,String estado)
	{
		generarListadoCompra();
		Integer id = this.ordenesMatPrimas.generarNvoId();

		/*Integer idCompra, ProveedorDTO proveedor,
			ArrayList<ItemMateriaPrimaDTO> listadoCompra, String estado,
			String fecha, Integer costo,Boolean enviado,Boolean fueeliminado)
		 * 
		 * */
		ventanaOrdenMatPrima.setNuevaOrden(new OrdenPedidoMatPrimaDTO(id,ventanaOrdenMatPrima.getProvSeleccionado(),
				ventanaOrdenMatPrima.getListadoItemsOrdenados(),estado,getFechaActual(),0,enviado,false));
		ordenesMatPrimas.agregarOrdenPedidoMatPrima(ventanaOrdenMatPrima.getNuevaOrden());
		//this.ordenesMatPrimas.agregarOrdenPedidoMatPrima(ventanaOrdenMatPrima.getNuevaOrden());

	}

	private void limpiarCampos() {
		ventanaOrdenMatPrima.getTextFieldBuscadorMatPrima().setText("");
		ventanaOrdenMatPrima.getTextFieldCantMatPrima().setText("");
	}
	private void generarListadoCompra() {
		ArrayList<ItemMateriaPrimaDTO> listadoCompra = new ArrayList<ItemMateriaPrimaDTO>();
		MateriaPrimaDTO materiaPrima;
		ItemMateriaPrimaDTO itemMatPrima;
		for (int i=0; i < ventanaOrdenMatPrima.getModeloItemsSolicitados().getRowCount(); i++){
			//Este metodo genera un array list de items se materia prima, por lo cual recorre la tabla creada.
			materiaPrima = getMatPrimaSeleccionada(ventanaOrdenMatPrima.getModeloItemsSolicitados().getValueAt(i,0).toString());
			Integer idAsignadoItemMarPrima = this.itemsMateriaPrima.getNuevoId();
			itemMatPrima = new ItemMateriaPrimaDTO(idAsignadoItemMarPrima+i, materiaPrima,
					Integer.parseInt(ventanaOrdenMatPrima.getModeloItemsSolicitados().getValueAt(i,1).toString()), false);
			System.out.println("NRO DE ITEM DESIGNADO> " + this.ordenesMatPrimas.obtenerOrdenPedidoMatPrima().size());
			this.itemsMateriaPrima.agregarItemMatPrima(itemMatPrima);
			listadoCompra.add(itemMatPrima);
		}
		ventanaOrdenMatPrima.setListadoItemsOrdenados(listadoCompra);
	}
	private void agregarItemTabla(ItemMateriaPrimaDTO itemSolicitado) {
		Integer indice = getIntFilaItem(itemSolicitado);
		if (indice==-1){
			ventanaOrdenMatPrima.getModeloItemsSolicitados().addRow(new Object[] {itemSolicitado.getItemMatPrima().getNombre(), String.valueOf(itemSolicitado.getCantidad())});
		}
		else{
			Integer cantTotal = Integer.parseInt(ventanaOrdenMatPrima.getModeloItemsSolicitados().getValueAt(indice, 1).toString()) + itemSolicitado.getCantidad();
			ventanaOrdenMatPrima.getModeloItemsSolicitados().setValueAt(cantTotal, indice, 1);
		}
		ventanaOrdenMatPrima.getTablaItemsMateriaPrima().setModel(ventanaOrdenMatPrima.getModeloItemsSolicitados());		
	}
	private Integer getIntFilaItem(ItemMateriaPrimaDTO itemSolicitado) {
		for (int i = 0; i < ventanaOrdenMatPrima.getModeloItemsSolicitados().getRowCount();i++){
			if (((String) ventanaOrdenMatPrima.getModeloItemsSolicitados().getValueAt(i, 0)).compareTo(itemSolicitado.getItemMatPrima().getNombre())==0 ){
				return i;
			}
		}
		return -1;
	}
	private MateriaPrimaDTO getMatPrimaSeleccionada(String text) {
		for (int  i= 0; i < ventanaOrdenMatPrima.getMateriasPrimasFiltradas().size();i++){
			if ( text.compareTo(ventanaOrdenMatPrima.getMateriasPrimasFiltradas().get(i).getNombre())==0 ){
				return ventanaOrdenMatPrima.getMateriasPrimasFiltradas().get(i);
			}
		}
		return null;
	}
	public ArrayList<ItemDTO> generarListaItems() 
	{
		ArrayList<ItemDTO> listaAux= new ArrayList<ItemDTO>();
		String nombre="";
		for(int i=0; i<this.ventanaPedido.getTablaItems().getRowCount(); i++)
		{
			nombre=this.ventanaPedido.getModel().getValueAt(i, 0).toString();
			if(producto.buscarProductoPorNombre(nombre)!=null)
			{
				ItemDTO aux=new ItemDTO(this.item.ultimoItem()+1,this.getProducto().buscarProductoPorNombre(this.ventanaPedido.getModel().getValueAt(i, 0).toString()), Integer.parseInt((String)this.ventanaPedido.getModel().getValueAt(i, 1)), (String)(this.ventanaPedido.getModel().getValueAt(i, 3)),false);
				item.agregarItem(aux);
				listaAux.add(aux);
			}
		}
		return listaAux;
	}
	public ArrayList<ItemDTO> generarListaItemsOfertados() 
	{
		ArrayList<ItemDTO> listaAux= new ArrayList<ItemDTO>();

		for(int i=0; i<this.ventanaAgregarPromocion.getTable().getRowCount(); i++)
		{
			if(producto.buscaNombresProductos(this.ventanaAgregarPromocion.getModel().getValueAt(i, 0).toString())!=null)
			{
				ItemDTO aux=new ItemDTO(this.item.ultimoItem()+1,this.getProducto().buscarProductoPorNombre(this.ventanaAgregarPromocion.getModel().getValueAt(i, 0).toString()), Integer.parseInt((String)this.ventanaAgregarPromocion.getModel().getValueAt(i, 1)), null,false);
				item.agregarItem(aux);
				listaAux.add(aux);
			}
		}
		return listaAux;
	}
	
	private void cargarProveedores(JComboBox<String> comboBox){
	
		ArrayList<ProveedorDTO> listaProveedores;// = (ArrayList<ProveedorDTO>) this.proveedor.obtenerProveedor();
		listaProveedores = quitarEspacioArray((ArrayList<ProveedorDTO>) this.proveedor.obtenerProveedor());
		for (int i=0; i< listaProveedores.size();i++){
			comboBox.addItem(listaProveedores.get(i).getNombre());
		}
	}

	/////////////////////////////Metodos //////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////
	private void cargarPromociones(JComboBox<String> comboBox){
		ArrayList<PromocionDTO> listaPromociones;// = (ArrayList<ProveedorDTO>) this.proveedor.obtenerProveedor();
		listaPromociones = (ArrayList<PromocionDTO>) this.promocion.obtenerOfertas();
		comboBox.addItem("(Seleccine una promocion)");
		for (int i=0; i< listaPromociones.size();i++)
		{
			comboBox.addItem(listaPromociones.get(i).getNombre());
		}
	}
	
	private ArrayList<ProveedorDTO> quitarEspacioArray(ArrayList<ProveedorDTO> listaProveedores) {
		//ArrayList<ProveedorDTO> proveedoresArreglado = new ArrayList<ProveedorDTO>();

		for (int i=0; i < listaProveedores.size();i++){
			listaProveedores.get(i).setNombre(listaProveedores.get(i).getNombre().trim());
			listaProveedores.get(i).setNombre(listaProveedores.get(i).getNombre().replace("_"," "));
			for (int x=0; x < listaProveedores.get(i).getCategoria().size();x++){
				listaProveedores.get(i).getCategoria().get(x).setDenominacion(
						listaProveedores.get(i).getCategoria().get(x).getDenominacion().trim());	
			}
		}
		return listaProveedores;		
	}	
	private void cargarCategorias() {
		ventanaOrdenMatPrima.getComboListaCategorias().removeAllItems();
		for (int i = 0; i < ventanaOrdenMatPrima.getProvSeleccionado().getCategoria().size();i++){
			ventanaOrdenMatPrima.getComboListaCategorias().addItem(ventanaOrdenMatPrima.getProvSeleccionado().getCategoria().get(i).getDenominacion());
		}
	}
	private void guardarProveedorSeleccionado(){
		String nomProveedor = (String) ventanaOrdenMatPrima.getComboListaProveedores().getSelectedItem().toString();
		if (getProveedor(nomProveedor)!= null){
			ventanaOrdenMatPrima.setProvSeleccionado(getProveedor(nomProveedor));
		}
	}	
	private ProveedorDTO getProveedor(String nomProveedor){
		ArrayList<ProveedorDTO> listaProveedores = (ArrayList<ProveedorDTO>) this.proveedor.obtenerProveedor();
		for (int i= 0; i < listaProveedores.size();i++){
			if (nomProveedor.compareTo(listaProveedores.get(i).getNombre()) == 0){
				return listaProveedores.get(i);
			}
		}
		return null;
	}
	private void prepararMatPrimaParaBusqXCategoria() {
		ventanaOrdenMatPrima.getTextAutoAcompleter().removeAllItems();
		for (int i=0; i < ventanaOrdenMatPrima.getMateriasPrimasFiltradas().size(); i++){
			if(ventanaOrdenMatPrima.getMateriasPrimasFiltradas().get(i).getCategoria().getDenominacion().compareTo
					(ventanaOrdenMatPrima.getComboListaCategorias().getSelectedItem().toString()) == 0){
				ventanaOrdenMatPrima.getTextAutoAcompleter().addItem(ventanaOrdenMatPrima.getMateriasPrimasFiltradas().get(i).getNombre());
			}
		}
	}
	private void generarArrayMatPrimaProveed(){
		/** Tomo cada elemento q compone el comboBox categorias, con cada nombre de categoria pregunto por cada item*/
		ventanaOrdenMatPrima.getMateriasPrimasFiltradas().clear();
		ventanaOrdenMatPrima.getTextAutoAcompleter().removeAllItems();
		ArrayList<MateriaPrimaDTO> materiasPrimas = (ArrayList<MateriaPrimaDTO>) this.materiasPrimas.obtenerMatPrimas();
		materiasPrimas = quitarEspacios(materiasPrimas);

		for (int i = 0; i < materiasPrimas.size();i++){
			if ( contieneCategoria(materiasPrimas.get(i).getCategoria().getDenominacion())){
				ventanaOrdenMatPrima.getMateriasPrimasFiltradas().add(materiasPrimas.get(i));
			}
		}
	}
	private ArrayList<MateriaPrimaDTO> quitarEspacios(ArrayList<MateriaPrimaDTO> materiasPrimas2) {
		//ArrayList<MateriaPrimaDTO> materiasPrimas = new ArrayList<MateriaPrimaDTO>();
		for (int i = 0 ; i < materiasPrimas2.size();i++){
			materiasPrimas2.get(i).setNombre(materiasPrimas2.get(i).getNombre().trim());
			materiasPrimas2.get(i).setNombre(materiasPrimas2.get(i).getNombre().replace("_", " "));
		}
		return materiasPrimas2;
	}
	private boolean contieneCategoria(String nomCategoria)  {
		for (int  i= 0; i < ventanaOrdenMatPrima.getProvSeleccionado().getCategoria().size();i++){
			if (ventanaOrdenMatPrima.getProvSeleccionado().getCategoria().get(i).getDenominacion().compareTo(nomCategoria)== 0){
				return true;
			}
		}
		return false;
	}
	//FIN METODOS PARA VENTANA ORDEN DE MATERIA PRIMA

	private ArrayList<ItemPromocionDTO> generarListaOfertas() 
	{
		ArrayList<ItemPromocionDTO> listaAux= new ArrayList<ItemPromocionDTO>();
		for(int i=0; i<this.ventanaPedido.getTablaItems().getRowCount(); i++)
		{
			if((oferta.buscarOfertaPorNombre(this.ventanaPedido.getModel().getValueAt(i, 0).toString()))!=null)
			{
				ItemPromocionDTO nuevo= new ItemPromocionDTO ();
				PromocionDTO aux=oferta.buscarOfertaPorNombre(this.ventanaPedido.getModel().getValueAt(i, 0).toString());
				nuevo.setIditemPromo(this.itemPromocion.ultimaOferta()+1);
				nuevo.setPromocion(aux);
				nuevo.setCantidad(Integer.parseInt(this.ventanaPedido.getModel().getValueAt(i, 1).toString()));
				if(this.ventanaPedido.getModel().getValueAt(i, 3)!=null)
					nuevo.setComentario(this.ventanaPedido.getModel().getValueAt(i, 3).toString());
				else
					nuevo.setComentario("");
				nuevo.setFueeliminado(false);
				listaAux.add(nuevo);
				itemPromocion.agregarItemPromo(nuevo);
			}
		}
		return listaAux;
	}

	public Repartidores getRepartidor() 
	{
		return repartidor;
	}

	public void setRepartidor(Repartidores repartidor) {
		this.repartidor = repartidor;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}


	public ItemsPromociones getItemPromocion() {
		return itemPromocion;
	}


	public void setItemPromocion(ItemsPromociones itemPromocion) {
		this.itemPromocion = itemPromocion;
	}


	public Promociones getPromocion() {
		return promocion;
	}


	public void setPromocion(Promociones promocion) {
		this.promocion = promocion;
	}


	public Pedidos getPedido() {
		return pedido;
	}

	public void setPedido(Pedidos pedido) {
		this.pedido = pedido;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Items getItem() {
		return item;
	}

	public void setItem(Items item) {
		this.item = item;
	}

	public Promociones getOferta() {
		return oferta;
	}


	public void setOferta(Promociones oferta) {
		this.oferta = oferta;
	}


	public Categorias getCategoria() {
		return categoria;
	}


	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}


	public OrdenesMateriaPrimas getOrdenesMatPrimas() {
		return ordenesMatPrimas;
	}


	public void setOrdenesMatPrimas(OrdenesMateriaPrimas ordenesMatPrimas) {
		this.ordenesMatPrimas = ordenesMatPrimas;
	}


	public MatPrimas getMateriasPrimas() {
		return materiasPrimas;
	}


	public void setMateriasPrimas(MatPrimas materiasPrimas) {
		this.materiasPrimas = materiasPrimas;
	}


	public ItemMateriasPrimas getItemsMateriaPrima() {
		return itemsMateriaPrima;
	}


	public void setItemsMateriaPrima(ItemMateriasPrimas itemsMateriaPrima) {
		this.itemsMateriaPrima = itemsMateriaPrima;
	}


	public Itinerarios getItinerario() {
		return itinerario;
	}


	public void setItinerario(Itinerarios itinerario) {
		this.itinerario = itinerario;
	}


	public Proveedores getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedores proveedor) {
		this.proveedor = proveedor;
	}

	public ordenDePedido getVentanaPedido() {
		return ventanaPedido;
	}

	public void setVentanaPedido(ordenDePedido ventanaPedido) {
		this.ventanaPedido = ventanaPedido;
	}


	private void vaciarFormulario() 
	{
		this.ventanaPedido.getTfAgregarEmpanada().setText("");
		this.ventanaPedido.getTfPrecioUniEmpanada().setText("");
		this.ventanaPedido.getTfSubTotalEmpanada().setText("");
		this.ventanaPedido.getTfUnidadEmpanada().setText("");
		this.ventanaPedido.getTfAgregarPizza().setText("");
		this.ventanaPedido.getTfPrecioUniPizza().setText("");
		this.ventanaPedido.getTfSubTotalPizza().setText("");
		this.ventanaPedido.getTfUnidadPizza().setText("");
		this.ventanaPedido.getTfAgregarOtroProducto().setText("");
		this.ventanaPedido.getTfPrecioUniOtro().setText("");
		this.ventanaPedido.getTfSubTotalOtro().setText("");
		this.ventanaPedido.getTfUnidadOtro().setText("");
	}

	private void llenarTablaMenu(String tipo) 
	{
		this.ventanamenu.getModel().setRowCount(0); //Para vaciar la tabla
		this.ventanamenu.getModel().setColumnCount(0);
		this.ventanamenu.getModel().setColumnIdentifiers(this.ventanamenu.getNombreColumnas());

		Iterator<ProductoDTO> Iterador = this.producto.obtenerProducto().iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			if(elemento.getTipo().compareTo(tipo)==0)
			{
				Object[] fila = {elemento.getNombre(), elemento.getPrecio()};
				this.ventanamenu.getModel().addRow(fila);			
			}
		}
	}
	private void llenarTablaMenuPromociones()
	{
		this.ventanamenu.getModel().setRowCount(0); //Para vaciar la tabla
		this.ventanamenu.getModel().setColumnCount(0);
		this.ventanamenu.getModel().setColumnIdentifiers(this.ventanamenu.getNombreColumnas());
		Iterator<PromocionDTO> Iterador = this.promocion.obtenerOfertas().iterator();
		while(Iterador.hasNext())
		{
			PromocionDTO elemento = Iterador.next();
			{
				Object[] fila = {elemento.getNombre(), elemento.getPrecio()};
				this.ventanamenu.getModel().addRow(fila);			
			}
		}
	}
	
	private void llenarTablaCliente()
	{
		this.ventanaModificacionCliente.getModel().setRowCount(0);
		this.ventanaModificacionCliente.getModel().setColumnCount(0);
		this.ventanaModificacionCliente.getModel().setColumnIdentifiers(this.ventanaModificacionCliente.getNombreColumnas());
		Iterator<ClienteDTO> Iterador = this.cliente.obtenerClientes().iterator();
		while(Iterador.hasNext())
		{
			ClienteDTO elemento = Iterador.next();
			Object[] fila = {elemento.getDni(),elemento.getApellido(),elemento.getNombre()};
			this.ventanaModificacionCliente.getModel().addRow(fila);			
		}
	}
	public void llenarTablaPedPendientes() 
	{
		Calendar c1 = GregorianCalendar.getInstance();
		String fecha=(c1.getTime().getDate()+"-"+(c1.getTime().getMonth()+1)+"-"+(c1.getTime().getYear()+1900));
		ventanaPedPendiente.getModel().setRowCount(0); //Para vaciar la tabla
		ventanaPedPendiente.getModel().setColumnCount(0);
		ventanaPedPendiente.getModel().setColumnIdentifiers(ventanaPedPendiente.getNombreColumnas());
		Iterator<PedidoDTO> Iterador = this.pedido.obtenerPedidoDeFecha(fecha).iterator();
		while(Iterador.hasNext())
		{
			PedidoDTO elemento = Iterador.next();
			if(elemento.getEstado().compareTo("rechazado")!=0 && elemento.getEstado().compareTo("cobrado")!=0)
			{
				if(elemento.getEstado().compareTo("en delivery")==0)
				{
					int itinerario=this.itinerario.buscarItinerarioPorPedido(elemento.getIdpedido(), fechaActual());
					ventanaPedPendiente.getModel().addRow(new String[] {elemento.getNumPedido().toString(),elemento.getTotal().toString(),elemento.get_estado(),Delivery(elemento),itinerario+""});
				}
				else
					ventanaPedPendiente.getModel().addRow(new String[] {elemento.getNumPedido().toString(),elemento.getTotal().toString(),elemento.get_estado(),Delivery(elemento),""});
			}
		}
		ventanaPedPendiente.getTable().setModel(ventanaPedPendiente.getModel());		
	}
	
	private String Delivery(PedidoDTO aux)
	{
		if(aux.getLlevaDelivery())
			return "true";
		else
			return  "-";
	}
	
	private void llenarTablaProveedor()
	{
		this.ventanaEditarProveedor.getModel().setRowCount(0);
		this.ventanaEditarProveedor.getModel().setColumnCount(0);
		this.ventanaEditarProveedor.getModel().setColumnIdentifiers(this.ventanaEditarProveedor.getNombreColumnasProveedor());
		Iterator<ProveedorDTO> Iterador = this.proveedor.obtenerProveedor().iterator();
		while(Iterador.hasNext())
		{
			ProveedorDTO elemento = Iterador.next();
			if (!elemento.getFueeliminado()){
				Object[] fila = {elemento.getNombre()};
				this.ventanaEditarProveedor.getModel().addRow(fila);		
			}
		}
	}

	private void llenarTablaRepartidor()
	{
		this.ventanaEditarRepartidor.getModel().setRowCount(0);
		this.ventanaEditarRepartidor.getModel().setColumnCount(0);
		this.ventanaEditarRepartidor.getModel().setColumnIdentifiers(this.ventanaEditarRepartidor.getNombreColumnasRepartidor());
		Iterator<RepartidorDTO> Iterador = this.repartidor.obtenerRepartidores().iterator();
		while(Iterador.hasNext())
		{
			RepartidorDTO elemento = Iterador.next();
			Object[] fila = {elemento.getDni(), elemento.getNombre(), elemento.getApellido()};
			this.ventanaEditarRepartidor.getModel().addRow(fila);			
		}
	}

	private void llenarTablaProductos()
	{
		this.ventanaAgregarProducto.getModel().setRowCount(0);
		this.ventanaAgregarProducto.getModel().setColumnCount(0);
		this.ventanaAgregarProducto.getModel().setColumnIdentifiers(this.ventanaAgregarProducto.getNombreColumnasProducto());
		Iterator<ProductoDTO> Iterador = this.producto.obtenerProducto().iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			Object[] fila = {elemento.getNombre(), elemento.getPrecio(), elemento.getTipo()};
			this.ventanaAgregarProducto.getModel().addRow(fila);			
		}
	}
	
	private void llenarTablaEditarPromo(PromocionDTO promo)
	{
		this.ventanaEditarPromocion.getModel().setRowCount(0);
		this.ventanaEditarPromocion.getModel().setColumnCount(0);
		this.ventanaEditarPromocion.getModel().setColumnIdentifiers(this.ventanaEditarPromocion.getNombreColumnas());
		Iterator<ItemDTO> Iterador = promo.getProductosOfertados().iterator();
		while(Iterador.hasNext())
		{
			ItemDTO elemento = Iterador.next();
			Object[] fila = {elemento.getProducto().getNombre(), elemento.getCantidad()};
			this.ventanaEditarPromocion.getModel().addRow(fila);			
		}
	}

	private Integer calcularPrecioReal(PromocionDTO promocion)
	{
		Iterator<ItemDTO> Iterador = promocion.getProductosOfertados().iterator();
		Integer costoReal=0;
		while(Iterador.hasNext())
		{
			ItemDTO elemento = Iterador.next();
				costoReal=costoReal+(elemento.getProducto().getPrecio()*elemento.getCantidad());
		}
		return costoReal;
	}
	
	private void llenarTablaProductosEditados(String tipo)
	{
		this.ventanaEditarProducto.getModel().setRowCount(0);
		this.ventanaEditarProducto.getModel().setColumnCount(0);
		this.ventanaEditarProducto.getModel().setColumnIdentifiers(this.ventanaEditarProducto.getNombreColumnasProducto());
		Iterator<ProductoDTO> Iterador = this.producto.obtenerProducto().iterator();
		while(Iterador.hasNext())
		{
			ProductoDTO elemento = Iterador.next();
			if(elemento.getTipo().compareTo(tipo)==0)
			{
				Object[] fila = {elemento.getNombre(), elemento.getPrecio(), elemento.getTipo()};
				this.ventanaEditarProducto.getModel().addRow(fila);
			}
			else if (tipo.compareTo("todos")==0)
			{
				Object[] fila = {elemento.getNombre(), elemento.getPrecio(), elemento.getTipo()};
				this.ventanaEditarProducto.getModel().addRow(fila);
			}
		}
	}

	private void accionParaCambioFiltroCategoria() {
		ventanaSeleccionProveedor.resetearModeloTablaResultados();
		String nomCatSeleccionada = ventanaSeleccionProveedor.getComboBoxCategorias().getSelectedItem().toString();

		if (nomCatSeleccionada.compareTo("Seleccione una categoria...")!=0)
		{
			Iterator<ProveedorDTO> IteradorProv = this.proveedor.obtenerProveedor().iterator();
			while(IteradorProv.hasNext())
			{
				ProveedorDTO elementoProveed = IteradorProv.next();
				if (elementoProveed.isCategoria(nomCatSeleccionada)){
					ventanaSeleccionProveedor.getModeloResultados().addRow( new String[] {elementoProveed.getNombre().trim(),
							elementoProveed.getDireccion().trim(),elementoProveed.getTelefono().trim(),elementoProveed.getEmail().trim()});
				}
			}
			ventanaSeleccionProveedor.getResultadoBusquedaProv().setModel(ventanaSeleccionProveedor.getModeloResultados());
		}
	}

	private void mostrarDetallesProv(ProveedorDTO provSeleccionado){

		ventanaSeleccionProveedor.getLabelMuestraNomContacto().setText(provSeleccionado.getNombrecontacto());
		ventanaSeleccionProveedor.getTextPaneComentario().setText(provSeleccionado.getComentario());
		ventanaSeleccionProveedor.getTextPanecategorias().setText("");
		Iterator<CategoriaDTO> IterCategorias = provSeleccionado.getCategoria().iterator();
		while(IterCategorias.hasNext()){
			CategoriaDTO elemCategoria = IterCategorias.next();
			ventanaSeleccionProveedor.getTextPanecategorias().setText(ventanaSeleccionProveedor.getTextPanecategorias().getText() + elemCategoria.getDenominacion().trim() + "; ");
		}
	}
	
	private void actualizarPrecioPromocion()
	{
		int valor=Integer.parseInt(ventanaAgregarPromocion.getTfSubtotal().getText().toString());
		int precioViejo=0;
		String valore=ventanaAgregarPromocion.getTfPrecioReal().getText().toString();
		if(valore.compareTo("")!=0)
			precioViejo=Integer.parseInt(ventanaAgregarPromocion.getTfPrecioReal().getText().toString());
		ventanaAgregarPromocion.getTfPrecioReal().setText((valor+precioViejo)+"");
	}
	
	@SuppressWarnings("serial")
	private void accionParaCambioFiltro() {
		switch(ventanaSeleccionProveedor.getComboFiltroBusqueda().getSelectedItem().toString())
		{
		case("Todos"):
		{
			//Carga direcamente en el jtable todos los proveedores
			ventanaSeleccionProveedor.getComboBoxCategorias().setVisible(false);
			ventanaSeleccionProveedor.getTextFieldBuscadorProv().setVisible(false);
			Iterator<ProveedorDTO> IteradorProveedores = this.proveedor.obtenerProveedor().iterator();
			while(IteradorProveedores.hasNext())
			{
				ProveedorDTO elementoProveedor = IteradorProveedores.next();	
				ventanaSeleccionProveedor.getModeloResultados().addRow(new Object[] {elementoProveedor.getNombre(),elementoProveedor.getDireccion().trim()
						,elementoProveedor.getTelefono().trim(),elementoProveedor.getEmail().trim()});
			}
			ventanaSeleccionProveedor.setModeloResultados(ventanaSeleccionProveedor.getModeloResultados());
			break;
		}
		case("Categoría"):
		{
			ventanaSeleccionProveedor.resetearModeloTablaResultados();
			ventanaSeleccionProveedor.getComboBoxCategorias().setVisible(true);
			break;
		}
		case("Nombre"):
		{
			ventanaSeleccionProveedor.getComboBoxCategorias().setVisible(false);
			ventanaSeleccionProveedor.getTextFieldBuscadorProv().setVisible(true);
			break;
		}
		}
	}
	
	private String getFechaActual(){
		Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH)+1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
//      int hora = fecha.get(Calendar.HOUR_OF_DAY);
//      int minuto = fecha.get(Calendar.MINUTE);
//      int segundo = fecha.get(Calendar.SECOND);
        //System.out.printf("Hora Actual: %02d:%02d:%02d %n",hora, minuto, segundo);
        return new String (dia + "-" + mes + "-" + año);// + " "
        //+ String.format("%02d", hora) + ":" + String.format("%02d", minuto) + ":" + String.format("%02d", segundo);           
        
	}
	private void llenarComboBoxEdicionPromo()
	{
		Iterator<PromocionDTO> promociones=this.promocion.obtenerOfertas().iterator();
		ventanaEditarPromocion.getComboBox().addItem("(Seleccione una Promocion)");
		while(promociones.hasNext())
		{
			PromocionDTO elemPormo = promociones.next();
			ventanaEditarPromocion.getComboBox().addItem(elemPormo.getNombre());
		}
	}
	private ArrayList<ItemDTO> obtenerListaProductosTabla()
	{
		ArrayList<ItemDTO> ofertas=new ArrayList<ItemDTO>();
		for(int i=0; i<ventanaEditarPromocion.getTable().getRowCount();i++)
		{
			String nombreProducto=ventanaEditarPromocion.getModel().getValueAt(i, 0).toString();
			ProductoDTO producto=this.producto.buscarProductoPorNombre(nombreProducto);
			ItemDTO item=new ItemDTO(this.item.ultimoItem()+1, producto, Integer.parseInt(ventanaEditarPromocion.getModel().getValueAt(i, 1).toString()),"", false);
			this.item.agregarItem(item);
			ofertas.add(item);
		}
		return ofertas;
	}
	
//	public void enviarPedidoMonitor(PedidoDTO nuevoPedido) throws IOException{
//
//		this.socket = new Socket("localhost",5000);
//
//		objectOutputStream= new ObjectOutputStream(socket.getOutputStream());
//		objectInputStream = new ObjectInputStream(socket.getInputStream());
//		
//		//ENVIO DE PEDIDO
//		this.objectOutputStream.writeObject(nuevoPedido);
//		//RETORNO POSIBLE. persona returnHumano = (persona)cliente.objectInputStream.readObject();
//		System.out.println("Pedido enviado");
//		socket.close();			
//	}
	
	
	public void enviarPedidoMonitor(PedidoDTO nuevoPedido) throws IOException
	{
		this.objectOutputStream.writeObject(nuevoPedido);
	}
	
	private boolean enviarPedidosMonitor(String host, String puerto) throws IOException{
		final String HOST= host;
		final int PUERTO = Integer.parseInt(puerto);
		this.socket = new Socket(HOST,PUERTO);
		if(this.socket.isConnected())
		{
			System.out.println("se conecto");
		}
		new propiedades().getDirServidor();
		//	this.socket = new Socket(new propiedades().getDirServidor(),9000); // linea par activar cuando se tenga monitor en otra pc.
		sos = socket.getOutputStream(); 
		//
		objectOutputStream= new ObjectOutputStream(socket.getOutputStream());
		//Levanta de la base todos los pedidos en estado solicitado, no eliminados y del dia de la fecha de hoy.
		Iterator<PedidoDTO> pedidos = pedido.obtenerPedidosHoy(getFechaActual()).iterator();
		while (pedidos.hasNext()){
			PedidoDTO pedido = pedidos.next();		
				try {//
					this.objectOutputStream.writeObject(pedido);
					System.out.println("Pedido enviado a monitor.");
				} catch (IOException e) {//
		            e.printStackTrace(System.err);//
		            return false;
				}//
				try {//
		            Thread.sleep(50);//
		        } catch (Exception e) {//
		            e.printStackTrace();//
		            return false;
		        }//
		}
		return true;
	}


	private ArrayList<ProductoEstadistico> obtenerTodosProdusctosTodosPedidos(List<PedidoDTO> listaPed,Integer cant)
	{
		ArrayList<ItemDTO>listaItems =new ArrayList<ItemDTO>();
		Iterator<PedidoDTO> pedidos=listaPed.iterator();
		while(pedidos.hasNext())
		{
			PedidoDTO element=pedidos.next();
			listaItems.addAll(element.getProductos());
		}
		ArrayList<ProductoEstadistico> producto=new ArrayList<ProductoEstadistico>();
		Iterator<ItemDTO> items=listaItems.iterator();
		ProductoEstadistico pEstadistico=new ProductoEstadistico();
		while (items.hasNext())
		{
			ItemDTO elemento = items.next();
			ProductoEstadistico p=new ProductoEstadistico();
			p.setProducto(elemento.getProducto());
			p.setCantidad(elemento.getCantidad());
			pEstadistico=ProductoEstadistico.buscarProductoEst(producto, p);
			if(pEstadistico!=null)
			{
				pEstadistico.setCantidad((pEstadistico.getCantidad()*cant)+p.getCantidad());
}
			else
			{
				p.setCantidad(elemento.getCantidad()*cant);
				producto.add(p);
			}
		}
		return producto;
	}
	
	public ArrayList<PromocionEstadistica> obtenerTodasPromocionesTodosPedidos(List<PedidoDTO> listaPed)
	{
		ArrayList<ItemPromocionDTO>listaPromos =new ArrayList<ItemPromocionDTO>();
		Iterator<PedidoDTO> pedidos=listaPed.iterator();
		while(pedidos.hasNext())
		{
			PedidoDTO element=pedidos.next();
			listaPromos.addAll(element.getOfertas());
		}
		ArrayList<PromocionEstadistica> producto=new ArrayList<PromocionEstadistica>();
		Iterator<ItemPromocionDTO> items=listaPromos.iterator();
		PromocionEstadistica pEstadistico=new PromocionEstadistica();
		while (items.hasNext())
		{
			ItemPromocionDTO elemento = items.next();
			PromocionEstadistica p=new PromocionEstadistica();
			p.setPromo(elemento.getPromocion());
			p.setCantidad(elemento.getCantidad());

			pEstadistico=PromocionEstadistica.buscarPromoEst(producto, p);
			if(pEstadistico!=null)
			{
				pEstadistico.setCantidad(pEstadistico.getCantidad()+p.getCantidad());
			}
			else
			{
				producto.add(p);
			}			
		}
		return producto;
	}
	
	public ArrayList<ProductoEstadistico> obtenerTodasProductostodasPromocionesTodosPedidos(ArrayList<PromocionEstadistica> listaPed)
	{
		ArrayList<ProductoEstadistico> producto=new ArrayList<ProductoEstadistico>();
		ArrayList<ProductoEstadistico> result= new ArrayList<ProductoEstadistico>();
		Iterator<PromocionEstadistica> items=listaPed.iterator();
		while (items.hasNext())
		{
			PromocionEstadistica elemento = items.next();
			producto=obtenerTodosProdusctosTodosPedidosOf(elemento.getPromo().getProductosOfertados(),elemento.getCantidad());
			result=sumarProductos(producto,result);
		}
		return producto;
	}
	
	


	private ArrayList<ProductoEstadistico> obtenerTodosProdusctosTodosPedidosOf(ArrayList<ItemDTO> productosOfertados, Integer cant)
	{
		ArrayList<ProductoEstadistico> producto=new ArrayList<ProductoEstadistico>();
		Iterator<ItemDTO> items=productosOfertados.iterator();
		ProductoEstadistico pEstadistico=new ProductoEstadistico();
		while (items.hasNext())
		{
			ItemDTO elemento = items.next();
			ProductoEstadistico p=new ProductoEstadistico();
			p.setProducto(elemento.getProducto());
			p.setCantidad(elemento.getCantidad());
			pEstadistico=ProductoEstadistico.buscarProductoEst(producto, p);
			if(pEstadistico!=null)
			{
				pEstadistico.setCantidad((pEstadistico.getCantidad()*cant)+p.getCantidad());
}
			else
			{
				p.setCantidad(elemento.getCantidad()*cant);
				producto.add(p);
			}
		}
		return producto;
	}


	private void llenarTablaEstadisticas(String tipo,ArrayList<PromocionEstadistica> promocion,ArrayList<ProductoEstadistico> productos)
	{
		this.ventanaReportesEstadistica.getModel().setRowCount(0);
		this.ventanaReportesEstadistica.getModel().setColumnCount(0);
		if(tipo.compareTo("promocion")==0)
		{
			String[] nombreColum=new String[2];
			nombreColum[0]="Oferta";
			nombreColum[1]="Cantidad";
			this.ventanaReportesEstadistica.setNombreColumnas(nombreColum);
			this.ventanaReportesEstadistica.getModel().setColumnIdentifiers(this.ventanaReportesEstadistica.getNombreColumnas());
			Iterator<PromocionEstadistica> Iterador = promocion.iterator();
			while(Iterador.hasNext())
			{
				PromocionEstadistica elemento = Iterador.next();
				Object[] fila = {elemento.getPromo().getNombre(), elemento.getCantidad()};
				this.ventanaReportesEstadistica.getModel().addRow(fila);
			}
		}
		else
		{
			String[] nombreColum=new String[3];
			nombreColum[0]="Producto";
			nombreColum[1]="Tipo";			
			nombreColum[2]="Cantidad";
			this.ventanaReportesEstadistica.setNombreColumnas(nombreColum);
			this.ventanaReportesEstadistica.getModel().setColumnIdentifiers(this.ventanaReportesEstadistica.getNombreColumnas());
			Iterator<ProductoEstadistico> Iterador = productos.iterator();
			while(Iterador.hasNext())
			{
				ProductoEstadistico elemento = Iterador.next();
					Object[] fila = {elemento.getProducto().getNombre(),elemento.getProducto().getTipo() ,elemento.getCantidad()};
					this.ventanaReportesEstadistica.getModel().addRow(fila);
	
			}
		}
	}
	
	private void llenarTablaEstadisticasTop5(String tipo,ArrayList<PromocionEstadistica> promocion,ArrayList<ProductoEstadistico> productos)
	{
		this.ventanaReportesEstadistica.getModel().setRowCount(0);
		this.ventanaReportesEstadistica.getModel().setColumnCount(0);
		int cont=0;
		if(tipo.compareTo("promocion")==0)
		{
			String[] nombreColum=new String[2];
			nombreColum[0]="Oferta";
			nombreColum[1]="Cantidad";
			this.ventanaReportesEstadistica.setNombreColumnas(nombreColum);
			this.ventanaReportesEstadistica.getModel().setColumnIdentifiers(this.ventanaReportesEstadistica.getNombreColumnas());
			Iterator<PromocionEstadistica> Iterador = promocion.iterator();
			while(Iterador.hasNext() && cont<5)
			{
				PromocionEstadistica elemento = Iterador.next();
				Object[] fila = {elemento.getPromo().getNombre(), elemento.getCantidad()};
				this.ventanaReportesEstadistica.getModel().addRow(fila);
				cont++;
			}
		}
		else
		{
			String[] nombreColum=new String[3];
			nombreColum[0]="Producto";
			nombreColum[1]="Tipo";			
			nombreColum[2]="Cantidad";
			this.ventanaReportesEstadistica.setNombreColumnas(nombreColum);
			this.ventanaReportesEstadistica.getModel().setColumnIdentifiers(this.ventanaReportesEstadistica.getNombreColumnas());
			Iterator<ProductoEstadistico> Iterador = productos.iterator();
			while(Iterador.hasNext() && cont<5)
			{
				ProductoEstadistico elemento = Iterador.next();
				Object[] fila = {elemento.getProducto().getNombre(),elemento.getProducto().getTipo() ,elemento.getCantidad()};
				this.ventanaReportesEstadistica.getModel().addRow(fila);
				cont++;
			}
		}
	}
	
	private ArrayList<ProductoEstadistico> sumarProductos(ArrayList<ProductoEstadistico> acumulador,ArrayList<ProductoEstadistico> productos)
	{
		Iterator<ProductoEstadistico> iterador=productos.iterator();
		while(iterador.hasNext())
		{
			ProductoEstadistico elemento=iterador.next();
			ProductoEstadistico nuevo= ProductoEstadistico.buscarProductoEst(acumulador, elemento);
			if(nuevo!=null)
			{
				acumulador.remove(nuevo);
				nuevo.setCantidad(nuevo.getCantidad()+elemento.getCantidad());
				acumulador.add(nuevo);
			}
			else
				acumulador.add(elemento);
		}
		return acumulador;
	}

	private Integer[] descomponerFecha(String fInicio)
	{
		Integer[] fechaFinal=new Integer[3];
		String t= fInicio;
		String fecha="";
		int num=0;
		for (int i=0; i<t.length(); i++)
		{
			  if (t.charAt(i) != '-' || (t.charAt(i)=='-' && t.charAt(i+1)!='-'))
				  fecha += t.charAt(i);
			  else if(t.charAt(i)=='-' )//&& t.charAt(i+1)==' ')
				  {
				  fechaFinal[num]=Integer.parseInt(fecha.toString());
				  num++;
				  }
		}
		return fechaFinal;
	}
	
		public boolean compararProducto(String nombre, String tipo)
		{
			Iterator<ProductoDTO> productos= producto.obtenerProducto().iterator();
			while(productos.hasNext())
			{
				ProductoDTO actual=productos.next();
				if(tipo.compareTo(actual.getTipo())==0 && nombre.compareTo(actual.getNombre())==0)
					return true;
			}
			return false;
		}
	
	  public ReporteContableDTO getReporteContable ()
	  {
		  return this.reporteContable;
	  }
	  
	  public String fechaActual()
		{
			Calendar c1 = GregorianCalendar.getInstance();
			String fecha=(c1.getTime().getDate()+"-"+(c1.getTime().getMonth()+1)+"-"+(c1.getTime().getYear()+1900));
			return fecha;
		}
	  
	  
		private void valoresPredetarminados() throws UnknownHostException 
		{
//			
			conectar.getTfIpMonitor().setText(propConecciones.getDirServidor().getHostAddress());
//			conectar.getTfIPBaseDeDatos().setText(propConecciones.getDirServidorBase().getHostAddress());
//			conectar.getTfNombreBase().setText(propConecciones.getNombreBase());
			conectar.getTfPuertoMonitor().setText("5000");
//			conectar.getTfUsusarioBase().setText(propConecciones.getUsuarioBase());
//			conectar.getpContraseñaBase().setText(propConecciones.getContraseñaBase());
		}
}
	

